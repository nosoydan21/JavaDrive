package logica;

import model.Cliente;
import model.Coche;
import model.Furgoneta;
import model.Reserva;
import model.Vehiculo;
import org.junit.jupiter.api.*;
import java.io.*;
import java.time.LocalDate;
import java.nio.file.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GestorInformesTest {

    @BeforeEach
    void limpiar() {
        GestorInformes.cliente.clear();
        GestorInformes.flota.clear();
        GestorInformes.reservas.clear();
    }

    // ── guardarDatos ─────────────────────────────────────────────────────────

    @Test
    @Order(1)
    @DisplayName("guardarDatos crea el archivo clientes.txt")
    void guardarDatos_creaArchivoClientes() throws IOException {
        GestorInformes.cliente.add(new Cliente("12345678A", "Carlos", "600000001"));

        GestorInformes.guardarDatos();

        assertTrue(Files.exists(Paths.get("clientes.txt")));
    }

    @Test
    @Order(2)
    @DisplayName("guardarDatos crea el archivo vehiculos.txt")
    void guardarDatos_creaArchivoVehiculos() throws IOException {
        GestorInformes.flota.add(new Coche("1234ABC", "Seat", "Ibiza", true,
                Coche.TipoCoche.FAMILIAR, 5));

        GestorInformes.guardarDatos();

        assertTrue(Files.exists(Paths.get("vehiculos.txt")));
    }

    @Test
    @Order(3)
    @DisplayName("guardarDatos escribe los clientes correctamente")
    void guardarDatos_escribeClientes() throws IOException {
        GestorInformes.cliente.add(new Cliente("12345678A", "Carlos", "600000001"));

        GestorInformes.guardarDatos();

        String contenido = Files.readString(Paths.get("clientes.txt"));
        assertTrue(contenido.contains("12345678A"));
        assertTrue(contenido.contains("Carlos"));
    }

    // ── cargarDatos ──────────────────────────────────────────────────────────

    @Test
    @Order(4)
    @DisplayName("cargarDatos lee los clientes correctamente")
    void cargarDatos_clientes() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("clientes.txt"))) {
            bw.write("12345678A;Carlos;600000001");
            bw.newLine();
            bw.write("87654321B;Ana;600000002");
            bw.newLine();
        }

        GestorInformes.cargarDatos();

        assertEquals(2, GestorInformes.cliente.size());
        assertEquals("Carlos", GestorInformes.cliente.get(0).getNombre());
    }

    @Test
    @Order(5)
    @DisplayName("cargarDatos lee los vehículos correctamente")
    void cargarDatos_vehiculos() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("vehiculos.txt"))) {
            bw.write("COCHE;1234ABC;Seat;Ibiza;true;FAMILIAR;5");
            bw.newLine();
            bw.write("FURGONETA;5678DEF;Mercedes;Sprinter;true;true;1000");
            bw.newLine();
        }

        GestorInformes.cargarDatos();

        assertEquals(2, GestorInformes.flota.size());
        assertInstanceOf(Coche.class, GestorInformes.flota.get(0));
        assertInstanceOf(Furgoneta.class, GestorInformes.flota.get(1));
    }

    @Test
    @Order(6)
    @DisplayName("cargarDatos no lanza excepción si los archivos no existen")
    void cargarDatos_sinArchivos() {
        new File("clientes.txt").delete();
        new File("vehiculos.txt").delete();

        assertDoesNotThrow(() -> GestorInformes.cargarDatos());
    }

    // ── exportarTicket ───────────────────────────────────────────────────────

    @Test
    @Order(7)
    @DisplayName("exportarTicket crea el archivo del ticket")
    void exportarTicket_creaArchivo() {
        Cliente c = new Cliente("12345678A", "Carlos", "600000001");
        Vehiculo v = new Coche("1234ABC", "Seat", "Ibiza", true,
                Coche.TipoCoche.FAMILIAR, 5);
        Reserva r = new Reserva(1, c, v,
                LocalDate.of(2025, 1, 10),
                LocalDate.of(2025, 1, 15));

        GestorInformes.exportarTicket(r);

        assertTrue(Files.exists(Paths.get("reserva 1.txt")));
    }

    @Test
    @Order(8)
    @DisplayName("exportarTicket genera contenido en el archivo")
    void exportarTicket_conContenido() throws IOException {
        Cliente c = new Cliente("12345678A", "Carlos", "600000001");
        Vehiculo v = new Coche("1234ABC", "Seat", "Ibiza", true,
                Coche.TipoCoche.FAMILIAR, 5);
        Reserva r = new Reserva(2, c, v,
                LocalDate.of(2025, 1, 10),
                LocalDate.of(2025, 1, 15));

        GestorInformes.exportarTicket(r);

        String contenido = Files.readString(Paths.get("reserva 2.txt"));
        assertFalse(contenido.isBlank());
    }

    @AfterEach
    void limpiarArchivos() {
        for (int i = 1; i <= 5; i++) {
            new File("reserva " + i + ".txt").delete();
        }
    }
}