package model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {

    Cliente cliente = new Cliente("12345678A", "Carlos", "600000001");
    Coche coche = new Coche("1234ABC", "Seat", "Ibiza", true,
            Coche.TipoCoche.FAMILIAR, 5);
    LocalDate inicio = LocalDate.of(2025, 1, 10);
    LocalDate fin    = LocalDate.of(2025, 1, 15);
    Reserva reserva  = new Reserva(1, cliente, coche, inicio, fin);

    @Test
    void generarLineaTicket() {
        String ticket = reserva.generarLineaTicket();
        assertNotNull(ticket);
        assertTrue(ticket.contains("Carlos"));
        assertTrue(ticket.contains("12345678A"));
        assertTrue(ticket.contains("1234ABC"));
        assertTrue(ticket.contains("5"));
    }

    @Test
    void getIdReserva() {
        assertEquals(1, reserva.getIdReserva());
    }

    @Test
    void getCliente() {
        assertEquals(cliente, reserva.getCliente());
    }

    @Test
    void getVehiculo() {
        assertEquals(coche, reserva.getVehiculo());
    }

    @Test
    void getFechaInicio() {
        assertEquals(inicio, reserva.getFechaInicio());
    }

    @Test
    void getFechaFin() {
        assertEquals(fin, reserva.getFechaFin());
    }
}