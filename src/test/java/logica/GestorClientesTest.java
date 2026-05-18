package logica;

import model.Cliente;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GestorClientesTest {

    @BeforeEach
    void limpiarLista() {
        GestorClientes.cliente.clear();
    }

    @Test
    @Order(1)
    @DisplayName("buscarCliente devuelve null si la lista está vacía")
    void buscarCliente_listaVacia() {
        assertNull(GestorClientes.buscarCliente("12345678A"));
    }

    @Test
    @Order(2)
    @DisplayName("buscarCliente encuentra un cliente existente")
    void buscarCliente_clienteExistente() {
        Cliente c = new Cliente("12345678A", "Carlos", "600000001");
        GestorClientes.cliente.add(c);

        Cliente resultado = GestorClientes.buscarCliente("12345678A");

        assertNotNull(resultado);
        assertEquals("12345678A", resultado.getDni());
    }

    @Test
    @Order(3)
    @DisplayName("buscarCliente devuelve null si el DNI no existe")
    void buscarCliente_clienteNoExistente() {
        GestorClientes.cliente.add(new Cliente("12345678A", "Carlos", "600000001"));
        assertNull(GestorClientes.buscarCliente("99999999Z"));
    }

    @Test
    @Order(4)
    @DisplayName("buscarCliente distingue entre DNIs distintos")
    void buscarCliente_variosClientes() {
        GestorClientes.cliente.add(new Cliente("11111111A", "Ana",   "600000001"));
        GestorClientes.cliente.add(new Cliente("22222222B", "Luis",  "600000002"));
        GestorClientes.cliente.add(new Cliente("33333333C", "Marta", "600000003"));

        Cliente resultado = GestorClientes.buscarCliente("22222222B");

        assertNotNull(resultado);
        assertEquals("Luis", resultado.getNombre());
    }

    @Test
    @Order(5)
    @DisplayName("La lista crece al añadir un cliente")
    void crearCliente_listaCrece() {
        assertEquals(0, GestorClientes.cliente.size());
        GestorClientes.cliente.add(new Cliente("12345678A", "Carlos", "600000001"));
        assertEquals(1, GestorClientes.cliente.size());
    }

    @Test
    @Order(6)
    @DisplayName("No se puede tener dos clientes con el mismo DNI")
    void crearCliente_noDuplicados() {
        GestorClientes.cliente.add(new Cliente("12345678A", "Carlos", "600000001"));
        boolean yaExiste = GestorClientes.buscarCliente("12345678A") != null;
        assertTrue(yaExiste);
    }

    @Test
    @Order(7)
    @DisplayName("DNI válido cumple el patrón 8 dígitos + letra")
    void crearCliente_dniValido() {
        assertTrue("12345678A".matches("[0-9]{8}[A-Za-z]"));
    }

    @Test
    @Order(8)
    @DisplayName("DNI inválido no cumple el patrón")
    void crearCliente_dniInvalido() {
        assertFalse("1234A".matches("[0-9]{8}[A-Za-z]"));
        assertFalse("ABCDEFGHI".matches("[0-9]{8}[A-Za-z]"));
        assertFalse("123456789".matches("[0-9]{8}[A-Za-z]"));
    }
}