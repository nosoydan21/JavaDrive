package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehiculoTest {

    // Vehiculo es abstracto, usamos Coche para testearlo
    Coche vehiculo = new Coche("1234ABC", "Seat", "Ibiza", true,
            Coche.TipoCoche.FAMILIAR, 5);

    @Test
    void testToString() {
        String str = vehiculo.toString();
        assertTrue(str.contains("1234ABC"));
        assertTrue(str.contains("Seat"));
        assertTrue(str.contains("Ibiza"));
        assertTrue(str.contains("Disponible"));
    }

    @Test
    void obtenerDetalles() {
        assertNotNull(vehiculo.obtenerDetalles());
        assertTrue(vehiculo.obtenerDetalles().contains("5"));
    }

    @Test
    void getMatricula() {
        assertEquals("1234ABC", vehiculo.getMatricula());
    }

    @Test
    void setMatricula() {
        vehiculo.setMatricula("9999ZZZ");
        assertEquals("9999ZZZ", vehiculo.getMatricula());
    }

    @Test
    void getMarca() {
        assertEquals("Seat", vehiculo.getMarca());
    }

    @Test
    void setMarca() {
        vehiculo.setMarca("Toyota");
        assertEquals("Toyota", vehiculo.getMarca());
    }

    @Test
    void getModelo() {
        assertEquals("Ibiza", vehiculo.getModelo());
    }

    @Test
    void setModelo() {
        vehiculo.setModelo("Corolla");
        assertEquals("Corolla", vehiculo.getModelo());
    }

    @Test
    void isDisponible() {
        assertTrue(vehiculo.isDisponible());
    }

    @Test
    void setDisponible() {
        vehiculo.setDisponible(false);
        assertFalse(vehiculo.isDisponible());
        // También testea el toString con Reservado
        assertTrue(vehiculo.toString().contains("Reservado"));
    }
}