package app;

import logica.*;
import model.*;

import java.util.Scanner;

public class Main {
    
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GestorInformes.cargarDatos();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerInt();
            switch (opcion) {
                case 1 -> menuClientes();
                case 2 -> menuFlota();
                case 3 -> menuReservas();
                case 4 -> menuInformes();
                case 0 -> {
                    GestorInformes.guardarDatos();
                    System.out.println("Datos guardados. ¡Hasta luego!");
                }
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // ── MENÚS ────────────────────────────────────────────────────────────────

    static void mostrarMenu() {
        System.out.println("\n==========================================");
        System.out.println("         JAVADRIVE - MENÚ PRINCIPAL");
        System.out.println("==========================================");
        System.out.println("1. Gestión de clientes");
        System.out.println("2. Gestión de flota");
        System.out.println("3. Gestión de reservas");
        System.out.println("4. Informes");
        System.out.println("0. Salir");
        System.out.print("Selecciona una opción: ");
    }

    static void menuClientes() {
        System.out.println("\n--- CLIENTES ---");
        System.out.println("1. Nuevo cliente");
        System.out.println("2. Buscar cliente por DNI");
        System.out.print("Opción: ");
        int op = leerInt();
        switch (op) {
            case 1 -> GestorClientes.crearCliente();
            case 2 -> {
                System.out.print("Introduce el DNI: ");
                String dni = sc.nextLine();
                Cliente c = GestorClientes.buscarCliente(dni);
                if (c != null) System.out.println("Cliente encontrado: " + c);
                else System.out.println("No existe ningún cliente con ese DNI.");
            }
            default -> System.out.println("Opción no válida.");
        }
    }

    static void menuFlota() {
        System.out.println("\n--- FLOTA ---");
        System.out.println("1. Añadir vehículo");
        System.out.println("2. Ver vehículos disponibles");
        System.out.print("Opción: ");
        int op = leerInt();
        switch (op) {
            case 1 -> GestorFlota.crearVehiculo();
            case 2 -> {
                System.out.println("\nVehículos disponibles:");
                GestorFlota.listarVehiculosDisponibles();
            }
            default -> System.out.println("Opción no válida.");
        }
    }

    static void menuReservas() {
        System.out.println("\n--- RESERVAS ---");
        System.out.println("1. Nueva reserva");
        System.out.print("Opción: ");
        int op = leerInt();
        if (op == 1) GestorReservas.pedirDatosReserva();
        else System.out.println("Opción no válida.");
    }

    static void menuInformes() {
        System.out.println("\n--- INFORMES ---");
        System.out.println("1. Guardar datos manualmente");
        System.out.println("2. Recargar datos desde archivos");
        System.out.print("Opción: ");
        int op = leerInt();
        switch (op) {
            case 1 -> {
                GestorInformes.guardarDatos();
                System.out.println("Datos guardados correctamente.");
            }
            case 2 -> {
                GestorInformes.cargarDatos();
                System.out.println("Datos recargados correctamente.");
            }
            default -> System.out.println("Opción no válida.");
        }
    }

    // ── UTILIDADES ───────────────────────────────────────────────────────────

    static int leerInt() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
