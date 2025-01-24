
package polleria;

import cliente.Cliente;
import java.util.ArrayList;
import java.util.Scanner;

public class SimuladorClientes {
    //atributos de la clase
    private static final Scanner teclado = new Scanner(System.in);
    private static boolean activo = true;
    private static final ArrayList<Cliente> clientes = new ArrayList<>();
    private static int contador = 1;

    /**
     * Metodo main
     * @param args 
     */
    public static void main(String[] args) {
        // mientras este activo el programa, muestra el menu
        while (activo) {
            //lanzamos mostrar menu
            mostrarMenu();
            //obtenemos la opcion seleccionada
            int seleccion = leerOpcion();
            //procesamos la opcion (el menu saldra cuando se termine
            //procesar al completo
            procesarOpcion(seleccion);
        }
    }
    
    private static void mostrarMenu() {
        System.out.println("--- LOS POLLOS HERMANOS ---");
        System.out.println("0. Simular pedidos de clientes");
        System.out.println("1. Finalizar programa");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * metodo que lee una opcion y en caso de invalidar, solicita de nuevo
     * @return 
     */
    private static int leerOpcion() {
        while (!teclado.hasNextInt()) {
            System.out.print("Entrada inválida. Introduzca un número válido: ");
            teclado.next();
        }
        return teclado.nextInt();
    }
    
    /**
     * metodo que recibe la opcion seleccionada, y procede segun lo seleccionado
     * @param opcion 
     */
    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 0 -> lanzarClientes();
            case 1 -> {
                activo = false;
                System.out.println("Finalizando ejecucion de clientes. Cerrando.");
            }
            default -> System.err.println("Opción incorrecta!!!");
        }
    }
    
    /**
     * metodo que lanza los clientes
     * pedira el numero de clientes a lanzar y procederá
     */
    private static void lanzarClientes() {
        //pregunta cuantos clientes quiere lanzar
        System.out.print("¿Cuantos clientes desea lanzar?: ");
        int cantidad = leerOpcion();
        
        //crea objetos cliente y los añade al array
        for (int i = 0; i < cantidad; i++) {
            Cliente cliente = new Cliente("Cliente" + contador);
            clientes.add(cliente);
            //se suma el contador para que sean consecutivos los clientes 
            // en dicha ejecucion
            contador++;
        }
        
        //lanzamos los clientes
        for (Cliente cliente : clientes) {
            cliente.start();
        }
        //esperamos a que los hilos finalicen para poder continuar
        for (Cliente cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        //limpiamos el array para un siguiente uso
        clientes.clear();
    }
}
