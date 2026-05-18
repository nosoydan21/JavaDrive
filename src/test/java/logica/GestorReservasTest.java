package logica;

import model.Cliente;
import model.Coche;
import model.Furgoneta;
import model.Reserva;
import model.Vehiculo;
import org.junit.jupiter.api.*;
import java.io.File;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GestorReservasTest {

    private Cliente clienteTest;
    private Vehiculo vehiculoTest;

    @BeforeEach
    void preparar() {
        GestorClientes.cliente.clear();
        GestorFlota.flota.clear();
        GestorReservas.reservas.clear();

        clienteTest = new Cliente("12345678A", "Carlos", "600000001");
        vehiculoTest = new Coche("1234ABC", "Seat", "Ibiza", true,
                Coche.TipoCoche.FAMILIAR, 5);

        GestorClientes.cliente.add(clienteTest);
        GestorFlota.flota.add(vehiculoTest);
    }

    // ── pedirDatosReserva ────────────────────────────────────────────────────

    @Test
    @Order(1)
    @DisplayName("Fecha fin anterior a inicio no es válida")
    void pedirDatosReserva_fechasInvalidas() {
        LocalDate inicio = LocalDate.of(2025, 1, 15);
        LocalDate fin    = LocalDate.of(2025, 1, 10);

        assertTrue(fin.isBefore(inicio));
    }

    @Test
    @Order(2)
    @DisplayName("Fecha fin posterior a inicio es válida")
    void pedirDatosReserva_fechasValidas() {
        LocalDate inicio = LocalDate.of(2025, 1, 10);
        LocalDate fin    = LocalDate.of(2025, 1, 15);

        assertFalse(fin.isBefore(inicio));
    }

    @Test
    @Order(3)
    @DisplayName("Vehículo no disponible no puede reservarse")
    void pedirDatosReserva_vehiculoNoDisponible() {
        vehiculoTest.setDisponible(false);
        assertFalse(vehiculoTest.isDisponible());
    }

    @Test
    @Order(4)
    @DisplayName("Cliente inexistente devuelve null")
    void pedirDatosReserva_clienteNoExiste() {
        assertNull(GestorClientes.buscarCliente("99999999Z"));
    }

    // ── realizarReserva ──────────────────────────────────────────────────────

    @Test
    @Order(5)
    @DisplayName("realizarReserva añade la reserva a la lista")
    void realizarReserva_seAniade() {
        GestorReservas.realizarReserva(clienteTest, vehiculoTest,
                LocalDate.of(2025, 1, 10), LocalDate.of(2025, 1, 15));

        assertEquals(1, GestorReservas.reservas.size());
    }

    @Test
    @Order(6)
    @DisplayName("realizarReserva marca el vehículo como no disponible")
    void realizarReserva_vehiculoNoDisponible() {
        GestorReservas.realizarReserva(clienteTest, vehiculoTest,
                LocalDate.of(2025, 1, 10), LocalDate.of(2025, 1, 15));

        assertFalse(vehiculoTest.isDisponible());
    }

    @Test
    @Order(7)
    @DisplayName("realizarReserva guarda el cliente correcto")
    void realizarReserva_clienteCorrecto() {
        GestorReservas.realizarReserva(clienteTest, vehiculoTest,
                LocalDate.of(2025, 1, 10), LocalDate.of(2025, 1, 15));

        assertEquals("12345678A", GestorReservas.reservas.get(0).getCliente().getDni());
    }

    @Test
    @Order(8)
    @DisplayName("realizarReserva guarda el vehículo correcto")
    void realizarReserva_vehiculoCorrecto() {
        GestorReservas.realizarReserva(clienteTest, vehiculoTest,
                LocalDate.of(2025, 1, 10), LocalDate.of(2025, 1, 15));

        assertEquals("1234ABC", GestorReservas.reservas.get(0).getVehiculo().getMatricula());
    }

    @Test
    @Order(9)
    @DisplayName("realizarReserva exporta el ticket en un archivo")
    void realizarReserva_exportaTicket() {
        GestorReservas.realizarReserva(clienteTest, vehiculoTest,
                LocalDate.of(2025, 1, 10), LocalDate.of(2025, 1, 15));

        Reserva r = GestorReservas.reservas.get(0);
        assertTrue(new File("reserva " + r.getIdReserva() + ".txt").exists());
    }

    @Test
    @Order(10)
    @DisplayName("IDs de reserva se asignan de forma incremental")
    void realizarReserva_idIncremental() {
        Vehiculo v2 = new Coche("9999ZZZ", "Ford", "Focus", true,
                Coche.TipoCoche.FAMILIAR, 5);
        GestorFlota.flota.add(v2);

        GestorReservas.realizarReserva(clienteTest, vehiculoTest,
                LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 5));
        GestorReservas.realizarReserva(clienteTest, v2,
                LocalDate.of(2025, 1, 6), LocalDate.of(2025, 1, 10));

        assertEquals(1, GestorReservas.reservas.get(0).getIdReserva());
        assertEquals(2, GestorReservas.reservas.get(1).getIdReserva());
    }

    @AfterEach
    void limpiarTickets() {
        for (int i = 1; i <= 10; i++) {
            new File("reserva " + i + ".txt").delete();
        }
    }
}