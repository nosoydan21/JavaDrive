package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FurgonetaTest {

    Furgoneta furgoCarga = new Furgoneta("5678DEF", "Mercedes", "Sprinter",
            true, true, 1000);
    Furgoneta furgoPasajeros = new Furgoneta("9999ZZZ", "Ford", "Transit",
            true, false, 8);

    @Test
    void obtenerDetalles() {
        assertTrue(furgoCarga.obtenerDetalles().contains("1000"));
        assertTrue(furgoCarga.obtenerDetalles().contains("Carga"));
        assertTrue(furgoPasajeros.obtenerDetalles().contains("8"));
        assertTrue(furgoPasajeros.obtenerDetalles().contains("pasajeros"));
    }

    @Test
    void testToString() {
        String str = furgoCarga.toString();
        assertTrue(str.contains("5678DEF"));
        assertTrue(str.contains("Mercedes"));
        assertTrue(str.contains("Sprinter"));
    }

    @Test
    void isEsDeCarga() {
        assertTrue(furgoCarga.isEsDeCarga());
        assertFalse(furgoPasajeros.isEsDeCarga());
    }

    @Test
    void getCapacidad() {
        assertEquals(1000, furgoCarga.getCapacidad());
        assertEquals(8, furgoPasajeros.getCapacidad());
    }
}