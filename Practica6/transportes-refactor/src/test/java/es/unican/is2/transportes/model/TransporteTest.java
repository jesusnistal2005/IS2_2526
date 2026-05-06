package es.unican.is2.transportes.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TransporteTest {

    @Test
    void testMercancias() {
        TransporteMercancias sut = new TransporteMercancias(1, 1);
        assertEquals(1, sut.getHoras());
        assertEquals(CategoriaTransporte.MERCANCIAS, sut.getCategoria());
        assertEquals(1, sut.getToneladas());
        assertEquals(7, sut.calcularPago());

        assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(10, 0));
    }

    @Test
    void testMercanciasPeligrosas() {
        TransporteMercanciasPeligrosas sut = new TransporteMercanciasPeligrosas(10, 1000);
        assertEquals(CategoriaTransporte.MERCANCIAS, sut.getCategoria());
        assertEquals(2100, sut.calcularPago());
    }

    @Test
    void testPersonas() {
        TransportePersonas sut = new TransportePersonas(10, 10);
        assertEquals(10, sut.getHoras());
        assertEquals(CategoriaTransporte.PERSONAS, sut.getCategoria());
        assertEquals(10, sut.getPersonas());
        assertEquals(60, sut.calcularPago());

        TransportePersonas sutNoColectivo = new TransportePersonas(1, 1);
        assertEquals(5.5, sutNoColectivo.calcularPago());

        assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(10, 0));
    }

    @Test
    void testFactory() {
        TransporteFactory factory = new TransporteFactory();
        assertInstanceOf(TransportePersonas.class, factory.crear("P", 1, 1, 0));
        assertInstanceOf(TransporteMercancias.class, factory.crear("M", 1, 0, 1));
        assertInstanceOf(TransporteMercanciasPeligrosas.class, factory.crear("MP", 1, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> factory.crear("X", 1, 0, 1));
    }
}

