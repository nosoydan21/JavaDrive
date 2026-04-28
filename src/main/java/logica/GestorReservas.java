package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import model.Cliente;
import model.Reserva;
import model.Vehiculo;

import static logica.GestorClientes.buscarCliente;
import static logica.GestorFlota.buscarVehiculo;
import static logica.GestorInformes.exportarTicket;

public class GestorReservas {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Vehiculo> flota = new ArrayList<>();
    static ArrayList<Cliente> cliente = new ArrayList<>();
    static ArrayList<Reserva> reservas = new ArrayList<>();

    public static void pedirDatosReserva() {
        String dni;
        Cliente c;
        do {
            System.out.println("Ingrese el DNI del cliente: ");
            dni = sc.nextLine();
            c = buscarCliente(dni);
            if (c == null) {
                System.out.println("El cliente no existe, intentelo de nuevo.");
            }
        } while (c == null);

        String matricula;
        Vehiculo v;
        do {
            System.out.println("Ingrese la matricula del vehiculo: ");
            matricula = sc.nextLine();
            v = buscarVehiculo(matricula);
            if (v == null || !v.isDisponible()) {
                System.out.println("El vehiculo no existe o no esta disponible");
            }
        } while (v == null || !v.isDisponible());

        LocalDate fechaInicio;
        LocalDate fechaFin;

        do {
            System.out.println("Ingrese la fecha de inicio (AAAA-MM-DD): ");
            fechaInicio = LocalDate.parse(sc.nextLine());
            System.out.println("Ingrese la fecha de fin (AAAA-MM-DD): ");
            fechaFin = LocalDate.parse(sc.nextLine());

            if (fechaFin.isBefore(fechaInicio)) {
                System.out.println("La fecha de fin debe ser posterior a la de inicio.");
            }
        } while (fechaFin.isBefore(fechaInicio));
        realizarReserva(c, v, fechaInicio, fechaFin);
    }

    public static void realizarReserva(Cliente c, Vehiculo v, LocalDate fechaInicio, LocalDate fechaFin){
        v.setDisponible(false);
        Reserva r = new Reserva(reservas.size() + 1, c, v, fechaInicio, fechaFin);
        reservas.add(r);
        exportarTicket(r);
    }
}
