package model;

public class Coche extends Vehiculo {

    public enum TipoCoche {
        PEQUENIO("Pequeño"),
        FAMILIAR("Familiar"),
        DEPORTIVO("Deportivo");

        private final String descripcion;

        TipoCoche(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    private TipoCoche tipoCoche;
    private int numPlazas;


    public Coche(String matricula, String marca, String modelo, boolean disponible, TipoCoche tipoCoche, int numPlazas) {
        super(matricula, marca, modelo, disponible);
        this.tipoCoche = tipoCoche;
        this.numPlazas = numPlazas;
    }
    @Override
    public String obtenerDetalles() {
        return " Coche [" + tipoCoche.getDescripcion() + "], Plazas: " + numPlazas;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public TipoCoche getTipo() {
        return tipoCoche;
    }

    public void setTipo(TipoCoche tipo) {
        this.tipoCoche = tipo;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }
}
