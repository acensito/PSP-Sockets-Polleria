
package servidor;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {
    
    //atributos de la clase
    private final Socket skClient;
    private static final int PORT = 2000;
    private static int pollos = 100;
    private static int patatas = 100;

    //constructor de la clase
    public Servidor(Socket skClient) {
        this.skClient = skClient;
    }  

    /**
     * metodo main de la clase
     * @param args 
     */
    public static void main(String[] args) {
        //lanza el servidor
        try (ServerSocket skServer = new ServerSocket(PORT)) {
            //muestra mensaje de inicio del servidor
            System.out.println("Servidor iniciado en el puerto " + PORT);
            //bucle para recibir conexiones
            while (true) {                
                //existe una conexion entrante
                Socket skt = skServer.accept();
                //lanzamos un hilo de ejecucion
                new Servidor(skt).start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * metodo de ejecucion del hilo
     */
    @Override
    public void run() {
        //creamos un canal de entrada de datos
        try (DataInputStream dataIN = new DataInputStream(skClient.getInputStream())){
            
            //recibimos el pedido del cliente
            String cliente = dataIN.readUTF();
            //recibimos numero de pollos que quiere
            int pollosPedidos = dataIN.readInt();
            //recibimos numero de raciones de patatas que quiere
            int patatasPedidas = dataIN.readInt();
            //preparamos el pedido
            prepararPedido(cliente, pollosPedidos, patatasPedidas);
            
        } catch (Exception e) {
        }
    }


    /**
     * devuelve el estado de la polleria
     */
    private void getEstado() {
        System.out.println("Tras el encargo, nos quedan: " + pollos + " pollos y " + patatas + " raciones de patatas.");
    }

    /**
     * metodo que prepara el pedido
     * @param cliente pedido, que resulta ser el nombre del cliente
     * @param pollosPedidos numero de pollos que se ha pedido
     * @param patatasPedidas numero de patatas que se ha pedido
     */
    private synchronized void prepararPedido(String cliente, int pollosPedidos, int patatasPedidas) {
        //comprobamos si tenemos pollos, si no hay pollos, le anulamos el pedido
        if (pollos < pollosPedidos) {
            System.err.println("Lo sentimos" + cliente + ", no nos quedan pollos suficientes para su encargo. Pedido anulado.");
            return;
        }
        
        //determinando cuantas patatas se pueden servir
        int patatasServidas = Math.min(patatasPedidas, patatas);
        
        //mostramos mensaje del pedido que se sirve
        System.out.println("Encargo " + cliente + ": "
            + pollosPedidos + (pollosPedidos == 1 ? " pollo" : " pollos") + " y "
            + patatasServidas + (patatasServidas == 1 ? " ración" : " raciones") + " de patatas.");
        
        //descontamos el numero de pollos y patatas del grueso total
        pollos -= pollosPedidos;
        patatas -= patatasServidas;
        
        //mostramos mensaje del estado de la polleria
        getEstado();

    }

}
