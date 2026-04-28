package model;

public abstract class Vehiculo {
    private String matricula;
    private String marca;
    private String modelo;
    private boolean disponible; // Estado actual (true si se puede alquilar, false si está reservado);

    public Vehiculo(String matricula, String marca, String modelo, boolean disponible) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        String disponible = this.disponible ? "Disponible" : "Reservado";
        return '[' + matricula + ']' + marca + " " + modelo + " - " + disponible;
    }

    public abstract String obtenerDetalles();

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
