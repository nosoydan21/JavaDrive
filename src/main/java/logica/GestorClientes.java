package logica;

import model.Cliente;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author nosoydan21, jander-13
 * @version 1.0.0 --> especifica la version del codigo
 */

public class GestorClientes {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Cliente> cliente = new ArrayList<>();

    public static Cliente buscarCliente(String dni){
        for (Cliente c : cliente){
            if (c.getDni().equals(dni)){
                return c;
            }
        }
        return null;
    }

    public static void crearCliente() {
        System.out.println("Creando un nuevo cliente...");

        String dni;
        do {
            System.out.println("Ingrese su DNI: ");
            dni = sc.nextLine();

            if (!dni.matches("[0-9]{8}[A-Za-z]")) {
                System.out.println("DNI no válido. Debe tener 8 números seguidos de una letra.");
            } else if (buscarCliente(dni) != null) {
                System.out.println("El cliente con ese DNI ya existe. Por favor, ingrese un DNI diferente.");
            }
        } while (!dni.matches("[0-9]{8}[A-Za-z]") || buscarCliente(dni) != null);


        System.out.println("Ingrese su nombre: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese su telefono: ");
        String telefono = sc.nextLine();

        System.out.println("Cliente creado");
        Cliente c = new Cliente(dni, nombre, telefono);
        cliente.add(c);
    }
}
