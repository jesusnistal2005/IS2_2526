package es.unican.is2.seguros.common;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class ClienteTest {

	private static final double DELTA = 0.001;

	@Test
	public void totalSegurosEsCeroCuandoNoTieneSeguros() {
		Cliente cliente = new Cliente();
		cliente.setSeguros(Collections.emptyList());

		assertEquals(0.0, cliente.totalSeguros(), DELTA);
	}

	@Test
	public void totalSegurosSumaImportesSinDescuentoCuandoNoHayMinusvalia() {
		Cliente cliente = new Cliente();
		cliente.setMinusvalia(false);
		cliente.setSeguros(Arrays.asList(new SeguroStub(100.0), new SeguroStub(50.5)));

		assertEquals(150.5, cliente.totalSeguros(), DELTA);
	}

	@Test
	public void totalSegurosAplicaDescuentoPorMinusvaliaASeguro() {
		Cliente cliente = new Cliente();
		cliente.setMinusvalia(true);
		cliente.setSeguros(Arrays.asList(new SeguroStub(100.0), new SeguroStub(200.0)));

		assertEquals(225.0, cliente.totalSeguros(), DELTA);
	}

	private static class SeguroStub extends Seguro {
		private final double precio;

		SeguroStub(double precio) {
			this.precio = precio;
		}

		@Override
		public double precio() {
			return precio;
		}
	}
}
