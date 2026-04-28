package logica;

import model.Furgoneta;
import model.Coche;
import model.Vehiculo;

import java.util.ArrayList;
import java.util.Scanner;

public class GestorFlota {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Vehiculo> flota = new ArrayList<>();

    public static Vehiculo buscarVehiculo(String matricula){
        for (Vehiculo v : flota){
            if (v.getMatricula().equals(matricula)){
                return v;
            }
        }
        return null;
    }

    public static void crearVehiculo(){
        System.out.println("Creando nuevo vehiculo...");

        System.out.println("Ingrese la matricula: ");
        String matricula = sc.nextLine();
        if (buscarVehiculo(matricula) != null){
            System.out.println("El vehiculo ya existe");
            return;
        }

        System.out.println("Ingrese la marca: ");
        String marca = sc.nextLine();

        System.out.println("Ingrese el modelo: ");
        String modelo = sc.nextLine();

        System.out.println("Ingrese el tipo de vehiculo (COCHE o FURGONETA):");
        String tipoVehiculo = sc.nextLine();

        if (tipoVehiculo.equalsIgnoreCase("COCHE")){
            System.out.println("Ingrese el tipo de coche (Pequeño, familiar o deportivo): ");
            String tipo = sc.nextLine();

            System.out.println("Ingrese el numero de plazas: ");
            int numPlazas = Integer.parseInt(sc.nextLine());
            if (numPlazas < 2 || numPlazas > 7){
                System.out.println("El numero de plazas debe estar entre 2 y 7");
                return;
            }
            Coche.TipoCoche tipoCoche = Coche.TipoCoche.valueOf(tipo.toUpperCase());
            Coche c = new Coche(matricula, marca, modelo, true, tipoCoche, numPlazas);
            flota.add(c);

        } else if (tipoVehiculo.equalsIgnoreCase("FURGONETA")){
            System.out.println("Ingrese si es de carga (true/false): ");
            boolean esDeCarga = Boolean.parseBoolean(sc.nextLine());

            System.out.println("Ingrese la capacidad (kilos o numero de personas): ");
            int capacidad = Integer.parseInt(sc.nextLine());

            Furgoneta f = new Furgoneta(matricula, marca, modelo, true, esDeCarga, capacidad);
            flota.add(f);
        } else {
            System.out.println("Tipo de vehiculo no valido. Por favor, ingrese 'COCHE' o 'FURGONETA'.");
        }
    }

    public static void listarVehiculosDisponibles(){
        for (Vehiculo v : flota){
            if (v.isDisponible()){
                System.out.println(v);
            }
        }
    }
}
