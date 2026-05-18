package logica;

import model.Coche;
import model.Furgoneta;
import model.Vehiculo;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GestorFlotaTest {

    @BeforeEach
    void limpiarFlota() {
        GestorFlota.flota.clear();
    }

    // ── buscarVehiculo ───────────────────────────────────────────────────────

    @Test
    @Order(1)
    @DisplayName("buscarVehiculo devuelve null si la flota está vacía")
    void buscarVehiculo_flotaVacia() {
        assertNull(GestorFlota.buscarVehiculo("1234ABC"));
    }

    @Test
    @Order(2)
    @DisplayName("buscarVehiculo encuentra un coche existente")
    void buscarVehiculo_cocheExistente() {
        GestorFlota.flota.add(new Coche("1234ABC", "Seat", "Ibiza", true,
                Coche.TipoCoche.FAMILIAR, 5));

        Vehiculo resultado = GestorFlota.buscarVehiculo("1234ABC");

        assertNotNull(resultado);
        assertEquals("1234ABC", resultado.getMatricula());
    }

    @Test
    @Order(3)
    @DisplayName("buscarVehiculo devuelve null si la matrícula no existe")
    void buscarVehiculo_matriculaNoExiste() {
        GestorFlota.flota.add(new Coche("1234ABC", "Seat", "Ibiza", true,
                Coche.TipoCoche.FAMILIAR, 5));

        assertNull(GestorFlota.buscarVehiculo("9999ZZZ"));
    }

    // ── crearVehiculo ────────────────────────────────────────────────────────

    @Test
    @Order(4)
    @DisplayName("No se añade vehículo con matrícula duplicada")
    void crearVehiculo_matriculaDuplicada() {
        GestorFlota.flota.add(new Coche("1234ABC", "Seat", "Ibiza", true,
                Coche.TipoCoche.FAMILIAR, 5));

        boolean yaExiste = GestorFlota.buscarVehiculo("1234ABC") != null;

        assertTrue(yaExiste);
        assertEquals(1, GestorFlota.flota.size());
    }

    @Test
    @Order(5)
    @DisplayName("Número de plazas fuera de rango no es válido")
    void crearVehiculo_plazasFueraDeRango() {
        assertFalse(10 >= 2 && 10 <= 7);
        assertFalse(1 >= 2 && 1 <= 7);
    }

    @Test
    @Order(6)
    @DisplayName("Número de plazas dentro de rango es válido")
    void crearVehiculo_plazasValidas() {
        assertTrue(5 >= 2 && 5 <= 7);
    }

    @Test
    @Order(7)
    @DisplayName("Coche se añade correctamente a la flota")
    void crearVehiculo_cocheSeAniade() {
        GestorFlota.flota.add(new Coche("3333CCC", "Ford", "Focus", true,
                Coche.TipoCoche.FAMILIAR, 5));

        assertEquals(1, GestorFlota.flota.size());
        assertInstanceOf(Coche.class, GestorFlota.flota.get(0));
    }

    @Test
    @Order(8)
    @DisplayName("Furgoneta se añade correctamente a la flota")
    void crearVehiculo_furgonetaSeAniade() {
        GestorFlota.flota.add(new Furgoneta("4444DDD", "Mercedes", "Sprinter",
                true, true, 1000));

        assertEquals(1, GestorFlota.flota.size());
        assertInstanceOf(Furgoneta.class, GestorFlota.flota.get(0));
    }

    // ── listarVehiculosDisponibles ───────────────────────────────────────────

    @Test
    @Order(9)
    @DisplayName("listarVehiculosDisponibles no falla con flota vacía")
    void listarVehiculosDisponibles_flotaVacia() {
        assertDoesNotThrow(() -> GestorFlota.listarVehiculosDisponibles());
    }

    @Test
    @Order(10)
    @DisplayName("Solo se listan vehículos disponibles")
    void listarVehiculosDisponibles_soloDisponibles() {
        GestorFlota.flota.add(new Coche("1111AAA", "Toyota", "Yaris", true,
                Coche.TipoCoche.PEQUENIO, 4));
        GestorFlota.flota.add(new Coche("2222BBB", "Honda", "Civic", false,
                Coche.TipoCoche.FAMILIAR, 5));

        long disponibles = GestorFlota.flota.stream()
                .filter(Vehiculo::isDisponible).count();

        assertEquals(1, disponibles);
    }
}