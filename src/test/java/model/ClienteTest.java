package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testToString() {
        Cliente c = new Cliente("12345678A", "Carlos", "600000001");
        assertTrue(c.toString().contains("Carlos"));
        assertTrue(c.toString().contains("12345678A"));
        assertTrue(c.toString().contains("600000001"));
    }

    @Test
    void getDni() {
        Cliente c = new Cliente("12345678A", "Carlos", "600000001");
        assertEquals("12345678A", c.getDni());
    }

    @Test
    void getNombre() {
        Cliente c = new Cliente("12345678A", "Carlos", "600000001");
        assertEquals("Carlos", c.getNombre());
    }

    @Test
    void getTelefono() {
        Cliente c = new Cliente("12345678A", "Carlos", "600000001");
        assertEquals("600000001", c.getTelefono());
    }
}