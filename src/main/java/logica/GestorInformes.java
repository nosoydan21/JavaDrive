package logica;

import model.Cliente;
import model.Coche;
import model.Furgoneta;
import model.Reserva;
import model.Vehiculo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author nosoydan21, jander-13
 * @version 1.0.0 --> especifica la version del codigo
 */

public class GestorInformes {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Vehiculo> flota = new ArrayList<>();
    static ArrayList<Cliente> cliente = new ArrayList<>();
    static ArrayList<Reserva> reservas = new ArrayList<>();

    public static void cargarDatos(){
        try (BufferedReader br = new BufferedReader(new FileReader("clientes.txt"))){
            String linea;
            int i = 0;

            while ((linea = br.readLine())!= null){
                String[] partes = linea.split(";");
                if (partes.length == 3){
                    String dni = partes[0];
                    String nombre = partes[1];
                    String telefono = partes[2];

                    Cliente c = new Cliente(dni, nombre, telefono);
                    cliente.add(c);
                    i++;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: El archivo 'cliente.txt' no se encuentra!");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo 'cliente.txt'!");
        }


        try (BufferedReader br = new BufferedReader(new FileReader("vehiculos.txt"))){
            String linea;
            int i = 0;

            while((linea = br.readLine())!=null){
                String[] partes = linea.split(";");
                if (partes[0].equals("COCHE")) {
                    String matricula = partes[1];
                    String marca = partes[2];
                    String modelo = partes[3];
                    boolean disponible = Boolean.parseBoolean(partes[4]);
                    Coche.TipoCoche tipoCoche = Coche.TipoCoche.valueOf(partes[5].toUpperCase());
                    int numPlazas = Integer.parseInt(partes[6]);

                    Coche c = new Coche(matricula, marca, modelo, disponible, tipoCoche, numPlazas);
                    flota.add(c);
                    i++;
                } else if (partes[0].equals("FURGONETA")) {
                    String matricula = partes[1];
                    String marca = partes[2];
                    String modelo = partes[3];
                    boolean disponible = Boolean.parseBoolean(partes[4]);
                    boolean esDeCarga = Boolean.parseBoolean(partes[5]);
                    int capacidad = Integer.parseInt(partes[6]);
                    Furgoneta f = new Furgoneta(matricula, marca, modelo, disponible, esDeCarga, capacidad);
                    flota.add(f);
                    i++;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: El archivo 'vehiculos.txt' no se encuentra!");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo 'vehiculos.txt'!");
        }
    }

    public static void guardarDatos(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("clientes.txt"))){
            for (Cliente c : cliente) {
                String linea = c.getDni() + ";"+ c.getNombre() + ";" + c.getTelefono();
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar clientes" + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("vehiculos.txt"))){
            for (Vehiculo v : flota){
                String linea;
                if (v instanceof Coche c){
                    linea = "COCHE;" +  v.getMatricula() + ";" + v.getMarca() + ";" + v.getModelo()
                            + ";" + v.isDisponible() + ";" + c.getTipo() + ";" + c.getNumPlazas();
                    bw.write(linea);
                    bw.newLine();
                }

                if (v instanceof Furgoneta f){
                    linea = "FURGONETA;" + v.getMatricula() + ";" + v.getMarca() + ";" + v.getModelo()
                            + ";" + v.isDisponible() + ";" + f.isEsDeCarga() + ";" + f.getCapacidad();
                    bw.write(linea);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar vehiculos" + e.getMessage());;
        }
    }

    public static void exportarTicket(Reserva r) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("reserva " + r.getIdReserva() + ".txt"));
            pw.println(r.generarLineaTicket());
            pw.close();
        } catch (IOException e) {
            System.out.println("Error al exportar el ticket: " + e.getMessage());
        }
    }
}
