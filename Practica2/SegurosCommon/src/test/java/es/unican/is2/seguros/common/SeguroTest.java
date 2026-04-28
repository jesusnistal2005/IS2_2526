package es.unican.is2.seguros.common;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class SeguroTest {

	private static final double DELTA = 0.001;

	@Test
	public void precioEsCeroSiFechaInicioEsNula() {
		Seguro seguro = new Seguro();
		seguro.setCobertura(Cobertura.TERCEROS);
		seguro.setPotencia(100);

		assertEquals(0.0, seguro.precio(), DELTA);
	}

	@Test
	public void precioEsCeroSiCoberturaEsNula() {
		Seguro seguro = new Seguro();
		seguro.setFechaInicio(LocalDate.now().minusYears(2));
		seguro.setPotencia(100);

		assertEquals(0.0, seguro.precio(), DELTA);
	}

	@Test
	public void precioEsCeroSiFechaInicioEsPosteriorAHoy() {
		Seguro seguro = seguro(Cobertura.TERCEROS, 100, LocalDate.now().plusDays(1));

		assertEquals(0.0, seguro.precio(), DELTA);
	}

	@Test
	public void precioTercerosSinIncrementoConPotenciaMenorA90() {
		Seguro seguro = seguro(Cobertura.TERCEROS, 89, LocalDate.now().minusYears(2));

		assertEquals(400.0, seguro.precio(), DELTA);
	}

	@Test
	public void precioTercerosConIncrementoCincoPorcientoEnLimiteInferior() {
		Seguro seguro = seguro(Cobertura.TERCEROS, 90, LocalDate.now().minusYears(2));

		assertEquals(420.0, seguro.precio(), DELTA);
	}

	@Test
	public void precioTercerosConIncrementoCincoPorcientoEnLimiteSuperior() {
		Seguro seguro = seguro(Cobertura.TERCEROS, 110, LocalDate.now().minusYears(2));

		assertEquals(420.0, seguro.precio(), DELTA);
	}

	@Test
	public void precioTercerosConIncrementoVeintePorcientoCuandoPotenciaMayorA110() {
		Seguro seguro = seguro(Cobertura.TERCEROS, 111, LocalDate.now().minusYears(2));

		assertEquals(480.0, seguro.precio(), DELTA);
	}

	@Test
	public void precioTodoRiesgoConDescuentoDurantePrimerAnho() {
		Seguro seguro = seguro(Cobertura.TODO_RIESGO, 80, LocalDate.now().minusMonths(6));

		assertEquals(800.0, seguro.precio(), DELTA);
	}

	@Test
	public void precioTercerosLunasSinDescuentoEnAniversario() {
		Seguro seguro = seguro(Cobertura.TERCEROS_LUNAS, 80, LocalDate.now().minusYears(1));

		assertEquals(600.0, seguro.precio(), DELTA);
	}

	private Seguro seguro(Cobertura cobertura, int potencia, LocalDate fechaInicio) {
		Seguro seguro = new Seguro();
		seguro.setCobertura(cobertura);
		seguro.setPotencia(potencia);
		seguro.setFechaInicio(fechaInicio);
		return seguro;
	}
}
