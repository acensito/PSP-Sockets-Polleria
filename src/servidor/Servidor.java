
package servidor;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {
    
    //atributos de la clase
    private final Socket skClient;
    private static final int PORT = 2000;
    private static final Polleria polleria = new Polleria();

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
            System.out.println("ERROR servidor corriendo entrada/salida datos");
        }
    }

    /**
     * metodo que prepara el pedido
     * @param cliente pedido, que resulta ser el nombre del cliente
     * @param pollosPedidos numero de pollos que se ha pedido
     * @param patatasPedidas numero de patatas que se ha pedido
     */
    private void prepararPedido(String cliente, int pollosPedidos, int patatasPedidas) {
        
        synchronized (polleria) {
            //comprobamos si tenemos pollos, si no hay pollos, le anulamos el pedido
            if (polleria.getPollos() < pollosPedidos) {
                System.err.println("Lo sentimos " + cliente + ", no nos quedan pollos suficientes para su encargo. Pedido anulado.");
                return;
            }
            
            //determinando cuantas patatas se pueden servir
            int patatasServidas = Math.min(patatasPedidas, polleria.getPatatas());

            //descontamos el numero de pollos y patatas del grueso total
            polleria.setPollos(pollosPedidos);
            polleria.setPatatas(patatasServidas);

            //mostramos mensaje del pedido que se sirve
            System.out.println("Encargo " + cliente + ": "
                + pollosPedidos + (pollosPedidos == 1 ? " pollo" : " pollos") + " y "
                + patatasServidas + (patatasServidas == 1 ? " ración" : " raciones") + " de patatas.");

            //mostramos mensaje del estado de la polleria
            System.out.println("Tras el encargo, nos quedan: " + polleria.getPollos() + " pollos y " + polleria.getPatatas() + " raciones de patatas.");
        }

    }

}
