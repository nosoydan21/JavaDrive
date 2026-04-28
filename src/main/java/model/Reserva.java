package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private int idReserva;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Reserva(int idReserva, Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    //todo metodo GenerarLineaTicket()

    // no entiendo el consejo del PrintWriter si realmente el ticket se hace aqui, luego usar el Print en el main es muy sencillo, apenas son 3 o 4 lineas de codigo
    public String generarLineaTicket(){
        long diasTotales = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        StringBuilder sb = new StringBuilder();
        sb.append("==================================================\n");
        sb.append("CONTRATO DE ALQUILER - JAVADRIVE\n");
        sb.append("==================================================\n");
        sb.append("ID RESERVA: " + idReserva + "\n");
        sb.append("FECHA EMISIÓN: " + LocalDate.now() + "\n");
        sb.append("--------------------------------------------------\n");
        sb.append("DATOS DEL CLIENTE:\n");
        sb.append("Nombre: " + cliente.getNombre() + "\n");
        sb.append("DNI: " + cliente.getDni() + "\n");
        sb.append("Télefono: " + cliente.getTelefono() + "\n");
        sb.append("--------------------------------------------------\n");
        sb.append("DATOS DEL VEHÍCULO:\n");
        sb.append("Marca y modelo: " + vehiculo.getMarca() + " " + vehiculo.getModelo() + "\n");
        sb.append("Matrícula: " + vehiculo.getMatricula() + "\n");
        sb.append("Detalles: " + vehiculo.obtenerDetalles() + "\n");
        sb.append("--------------------------------------------------\n");
        sb.append("PERIODO DE ALQUILER:\n");
        sb.append("Fecha de recogida: " + fechaInicio + "\n");
        sb.append("Fecha de devolucion: " + fechaFin + "\n");
        sb.append("Total de días: " + diasTotales + "\n");
        sb.append("--------------------------------------------------\n");
        sb.append("Estado: Confirmado y pendiente de pago\n");
        return sb.toString();
    }

    public int getIdReserva() {
        return idReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
}
