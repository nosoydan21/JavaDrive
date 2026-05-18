package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CocheTest {

    Coche coche = new Coche("1234ABC", "Seat", "Ibiza", true,
            Coche.TipoCoche.FAMILIAR, 5);

    @Test
    void obtenerDetalles() {
        String detalles = coche.obtenerDetalles();
        assertNotNull(detalles);
        assertTrue(detalles.contains("5"));
        assertTrue(detalles.contains("Familiar"));
    }

    @Test
    void testToString() {
        String str = coche.toString();
        assertTrue(str.contains("1234ABC"));
        assertTrue(str.contains("Seat"));
        assertTrue(str.contains("Ibiza"));
    }

    @Test
    void getTipo() {
        assertEquals(Coche.TipoCoche.FAMILIAR, coche.getTipo());
    }

    @Test
    void setTipo() {
        coche.setTipo(Coche.TipoCoche.DEPORTIVO);
        assertEquals(Coche.TipoCoche.DEPORTIVO, coche.getTipo());
    }

    @Test
    void getNumPlazas() {
        assertEquals(5, coche.getNumPlazas());
    }

    @Test
    void setNumPlazas() {
        coche.setNumPlazas(7);
        assertEquals(7, coche.getNumPlazas());
    }
}