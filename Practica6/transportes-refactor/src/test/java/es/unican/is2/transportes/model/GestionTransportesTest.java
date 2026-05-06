package es.unican.is2.transportes.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GestionTransportesTest {

    @Test
    void testGestionConductor() {
        GestionTransportes sut = new GestionTransportes();
        assertNull(sut.buscaConductor("x"));
        assertNull(sut.buscaConductor(null));

        assertTrue(sut.anhadeConductor("123A", "Ana", "Perez", "Lopez", "Calle 1"));
        assertFalse(sut.anhadeConductor("123A", "Ana", "Perez", "Lopez", "Calle 1"));

        assertEquals(1, sut.conductores().size());
        assertEquals("Ana", sut.buscaConductor("123A").getNombre());
    }
}

