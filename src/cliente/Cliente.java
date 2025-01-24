
package cliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class Cliente extends Thread {

    private static final String HOST = "localhost";
    private static final int PORT = 2000;
    private final Random random = new Random();

    public Cliente(String name) {
        super(name);
    }

    @Override
    public void run() {
        try (Socket skClient = new Socket(HOST, PORT);
            DataOutputStream dataOut = new DataOutputStream(skClient.getOutputStream())) {
            
            try {
                //generamos el pedido con el nombre del cliente/hilo
                String nombre = getName();
                //generamos el numero de pollos que queremos
                int pollos = random.nextInt(1,4);
                //generamos el numero de patatas que queremos
                int patatas = random.nextInt(0,5);
                //enviamos el pedido al servidor cuando conectamos
                dataOut.writeUTF(nombre);
                //enviamos el numero de pollos que queremos
                dataOut.writeInt(pollos);
                //enviamos el numero de patatas que queremos
                dataOut.writeInt(patatas);
                //mostramos por pantalla del cliente lo que ha pedido
                System.out.println(getName() 
                    + " solicita el encargo de " 
                        //depende de la cantidad mostramos mensaje
                    + pollos + (pollos == 1 ? " pollo" : " pollos") 
                        //depende de la cantidad mostramos mensaje
                    + (patatas == 0 ? "" : " y " + patatas + (patatas == 1 ? " ración" : " raciones") + " de patatas"));
            } catch (IOException e) {
                //lanzamos mensaje en caso de error
                System.err.println(e.getMessage());
            }
            
        } catch (Exception e) {
            //lanzamos mensaje en caso de error
            System.out.println(e.getMessage());
        }
    }
    
    

}