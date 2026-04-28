package model;

public class Furgoneta extends Vehiculo {
    private boolean esDeCarga;  // true si es para mercancía, false si es para pasajeros
    private int capacidad;   //Si esDeCarga es true, representa los kilos máximos.
    // Si esDeCarga es false, representa el número de personas.


    public Furgoneta(String matricula, String marca, String modelo, boolean disponible, boolean esDeCarga, int capacidad) {
        super(matricula, marca, modelo, disponible);
        this.esDeCarga = esDeCarga;
        this.capacidad = capacidad;
    }

    @Override
    public String obtenerDetalles(){
        return ("Furgoneta de " + (esDeCarga ? "Carga (" + capacidad + "kg)" : "pasajeros (" + capacidad + " personas)"));
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean isEsDeCarga() {
        return esDeCarga;
    }

    public int getCapacidad() {
        return capacidad;
    }
}
