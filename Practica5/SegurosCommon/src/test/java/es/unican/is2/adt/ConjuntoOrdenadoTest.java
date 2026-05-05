package es.unican.is2.adt;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase de pruebas unitarias para ConjuntoOrdenado
 * Implementa técnicas de caja negra (partición equivalente, AVL) 
 * y caja blanca (cobertura de sentencias y decisiones)
 */
public class ConjuntoOrdenadoTest {

	private IConjuntoOrdenado<Integer> conjunto;

	@Before
	public void setUp() {
		conjunto = new ConjuntoOrdenado<>();
	}

	// ===== PRUEBAS DE CAJA NEGRA - PARTICIÓN EQUIVALENTE =====
	
	// Tests para el método add()
	
	@Test
	public void testAddUnElemento() {
		assertTrue(conjunto.add(5));
		assertEquals(1, conjunto.size());
		assertEquals(Integer.valueOf(5), conjunto.get(0));
	}

	@Test
	public void testAddVariosElementos() {
		conjunto.add(5);
		conjunto.add(3);
		conjunto.add(7);
		assertEquals(3, conjunto.size());
		assertEquals(Integer.valueOf(3), conjunto.get(0));
		assertEquals(Integer.valueOf(5), conjunto.get(1));
		assertEquals(Integer.valueOf(7), conjunto.get(2));
	}

	@Test
	public void testAddElementosOrdenados() {
		assertTrue(conjunto.add(1));
		assertTrue(conjunto.add(2));
		assertTrue(conjunto.add(3));
		assertEquals(3, conjunto.size());
		assertEquals(Integer.valueOf(1), conjunto.get(0));
		assertEquals(Integer.valueOf(2), conjunto.get(1));
		assertEquals(Integer.valueOf(3), conjunto.get(2));
	}

	@Test
	public void testAddElementosDesOrdenados() {
		assertTrue(conjunto.add(3));
		assertTrue(conjunto.add(1));
		assertTrue(conjunto.add(2));
		assertEquals(3, conjunto.size());
		assertEquals(Integer.valueOf(1), conjunto.get(0));
		assertEquals(Integer.valueOf(2), conjunto.get(1));
		assertEquals(Integer.valueOf(3), conjunto.get(2));
	}

	@Test
	public void testAddElementoDuplicado() {
		assertTrue(conjunto.add(5));
		assertFalse(conjunto.add(5));
		assertEquals(1, conjunto.size());
	}

	@Test
	public void testAddNullThrowsException() {
		try {
			conjunto.add(null);
			fail("Debería lanzar NullPointerException");
		} catch (NullPointerException e) {
			// Comportamiento esperado
		}
	}

	// Tests para el método get()
	
	@Test
	public void testGetElementoValido() {
		conjunto.add(5);
		conjunto.add(3);
		conjunto.add(7);
		assertEquals(Integer.valueOf(3), conjunto.get(0));
		assertEquals(Integer.valueOf(5), conjunto.get(1));
		assertEquals(Integer.valueOf(7), conjunto.get(2));
	}

	@Test
	public void testGetIndiceNegativoThrowsException() {
		conjunto.add(5);
		try {
			conjunto.get(-1);
			fail("Debería lanzar IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Comportamiento esperado
		}
	}

	@Test
	public void testGetIndiceFueraDeRangoThrowsException() {
		conjunto.add(5);
		try {
			conjunto.get(1);
			fail("Debería lanzar IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Comportamiento esperado
		}
	}

	@Test
	public void testGetEnConjuntoVacioThrowsException() {
		try {
			conjunto.get(0);
			fail("Debería lanzar IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Comportamiento esperado
		}
	}

	// Tests para el método remove()
	
	@Test
	public void testRemoveElementoValido() {
		conjunto.add(1);
		conjunto.add(2);
		conjunto.add(3);
		Integer removed = conjunto.remove(1);
		assertEquals(Integer.valueOf(2), removed);
		assertEquals(2, conjunto.size());
		assertEquals(Integer.valueOf(1), conjunto.get(0));
		assertEquals(Integer.valueOf(3), conjunto.get(1));
	}

	@Test
	public void testRemovePrimerElemento() {
		conjunto.add(1);
		conjunto.add(2);
		conjunto.add(3);
		Integer removed = conjunto.remove(0);
		assertEquals(Integer.valueOf(1), removed);
		assertEquals(2, conjunto.size());
		assertEquals(Integer.valueOf(2), conjunto.get(0));
	}

	@Test
	public void testRemoveUltimoElemento() {
		conjunto.add(1);
		conjunto.add(2);
		conjunto.add(3);
		Integer removed = conjunto.remove(2);
		assertEquals(Integer.valueOf(3), removed);
		assertEquals(2, conjunto.size());
		assertEquals(Integer.valueOf(2), conjunto.get(1));
	}

	@Test
	public void testRemoveIndiceNegativoThrowsException() {
		conjunto.add(5);
		try {
			conjunto.remove(-1);
			fail("Debería lanzar IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Comportamiento esperado
		}
	}

	@Test
	public void testRemoveIndiceFueraDeRangoThrowsException() {
		conjunto.add(5);
		try {
			conjunto.remove(1);
			fail("Debería lanzar IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			// Comportamiento esperado
		}
	}

	@Test
	public void testRemoveUnicoElemento() {
		conjunto.add(5);
		Integer removed = conjunto.remove(0);
		assertEquals(Integer.valueOf(5), removed);
		assertEquals(0, conjunto.size());
	}

	// Tests para el método size()
	
	@Test
	public void testSizeConjuntoVacio() {
		assertEquals(0, conjunto.size());
	}

	@Test
	public void testSizeUnElemento() {
		conjunto.add(5);
		assertEquals(1, conjunto.size());
	}

	@Test
	public void testSizeVariosElementos() {
		conjunto.add(1);
		conjunto.add(2);
		conjunto.add(3);
		assertEquals(3, conjunto.size());
	}

	@Test
	public void testSizeAfterRemove() {
		conjunto.add(1);
		conjunto.add(2);
		conjunto.add(3);
		conjunto.remove(1);
		assertEquals(2, conjunto.size());
	}

	// Tests para el método clear()
	
	@Test
	public void testClearConjuntoVacio() {
		conjunto.clear();
		assertEquals(0, conjunto.size());
	}

	@Test
	public void testClearConjuntoConElementos() {
		conjunto.add(1);
		conjunto.add(2);
		conjunto.add(3);
		conjunto.clear();
		assertEquals(0, conjunto.size());
	}

	@Test
	public void testClearYAgregarNuevamente() {
		conjunto.add(1);
		conjunto.add(2);
		conjunto.clear();
		conjunto.add(5);
		assertEquals(1, conjunto.size());
		assertEquals(Integer.valueOf(5), conjunto.get(0));
	}

	// ===== PRUEBAS DE CAJA BLANCA - ANÁLISIS DE VALOR LÍMITE (AVL) =====
	
	@Test
	public void testAVLAddEnListaVacia() {
		assertTrue(conjunto.add(10));
		assertEquals(1, conjunto.size());
	}

	@Test
	public void testAVLAddMenorAlPrimero() {
		conjunto.add(10);
		conjunto.add(5);
		assertEquals(Integer.valueOf(5), conjunto.get(0));
		assertEquals(Integer.valueOf(10), conjunto.get(1));
	}

	@Test
	public void testAVLAddMayorAlUltimo() {
		conjunto.add(5);
		conjunto.add(15);
		assertEquals(Integer.valueOf(5), conjunto.get(0));
		assertEquals(Integer.valueOf(15), conjunto.get(1));
	}

	@Test
	public void testAVLAddEnMedio() {
		conjunto.add(5);
		conjunto.add(15);
		conjunto.add(10);
		assertEquals(Integer.valueOf(5), conjunto.get(0));
		assertEquals(Integer.valueOf(10), conjunto.get(1));
		assertEquals(Integer.valueOf(15), conjunto.get(2));
	}

	@Test
	public void testAVLGetIndice0() {
		conjunto.add(1);
		conjunto.add(2);
		assertEquals(Integer.valueOf(1), conjunto.get(0));
	}

	@Test
	public void testAVLGetUltimoIndice() {
		conjunto.add(1);
		conjunto.add(2);
		conjunto.add(3);
		assertEquals(Integer.valueOf(3), conjunto.get(conjunto.size() - 1));
	}

	@Test
	public void testAVLRemoveIndice0() {
		conjunto.add(1);
		conjunto.add(2);
		conjunto.add(3);
		Integer removed = conjunto.remove(0);
		assertEquals(Integer.valueOf(1), removed);
		assertEquals(Integer.valueOf(2), conjunto.get(0));
	}

	@Test
	public void testAVLRemoveUltimoIndice() {
		conjunto.add(1);
		conjunto.add(2);
		conjunto.add(3);
		int lastIndex = conjunto.size() - 1;
		Integer removed = conjunto.remove(lastIndex);
		assertEquals(Integer.valueOf(3), removed);
		assertEquals(Integer.valueOf(2), conjunto.get(conjunto.size() - 1));
	}

	// ===== PRUEBAS CON STRINGS PARA VERIFICAR ORDENAMIENTO NATURAL =====
	
	@Test
	public void testConjuntoConStrings() {
		IConjuntoOrdenado<String> conjuntoStrings = new ConjuntoOrdenado<>();
		conjuntoStrings.add("zebra");
		conjuntoStrings.add("apple");
		conjuntoStrings.add("mango");
		
		assertEquals(3, conjuntoStrings.size());
		assertEquals("apple", conjuntoStrings.get(0));
		assertEquals("mango", conjuntoStrings.get(1));
		assertEquals("zebra", conjuntoStrings.get(2));
	}

	@Test
	public void testConjuntoConStringsNullThrowsException() {
		IConjuntoOrdenado<String> conjuntoStrings = new ConjuntoOrdenado<>();
		try {
			conjuntoStrings.add(null);
			fail("Debería lanzar NullPointerException");
		} catch (NullPointerException e) {
			// Comportamiento esperado
		}
	}

	// ===== PRUEBAS DE COBERTURA DE SENTENCIAS Y CONDICIONES =====
	
	@Test
	public void testCondicionComparacionMenor() {
		// Prueba la rama: elemento.compareTo(...) < 0
		conjunto.add(10);
		conjunto.add(5);
		assertEquals(Integer.valueOf(5), conjunto.get(0));
	}

	@Test
	public void testCondicionComparacionIgual() {
		// Prueba la rama: elemento.compareTo(...) == 0 (duplicado)
		assertTrue(conjunto.add(10));
		assertFalse(conjunto.add(10));
	}

	@Test
	public void testCondicionComparacionMayor() {
		// Prueba la rama: elemento.compareTo(...) > 0
		conjunto.add(5);
		conjunto.add(10);
		assertEquals(Integer.valueOf(10), conjunto.get(1));
	}

	@Test
	public void testCondicionListaNoVacia() {
		// Prueba la rama: lista.size() != 0
		conjunto.add(5);
		conjunto.add(3);
		assertTrue(conjunto.size() > 0);
	}

	@Test
	public void testMultiplesIteracionesDelLoop() {
		// Prueba múltiples iteraciones del loop while en add()
		conjunto.add(100);
		conjunto.add(50);
		conjunto.add(75);
		conjunto.add(25);
		conjunto.add(10);
		
		assertEquals(5, conjunto.size());
		assertEquals(Integer.valueOf(10), conjunto.get(0));
		assertEquals(Integer.valueOf(25), conjunto.get(1));
		assertEquals(Integer.valueOf(50), conjunto.get(2));
		assertEquals(Integer.valueOf(75), conjunto.get(3));
		assertEquals(Integer.valueOf(100), conjunto.get(4));
	}

	// ===== PRUEBAS DE INTEGRACIÓN =====
	
	@Test
	public void testFlujoCompleto() {
		// Agregar elementos
		assertTrue(conjunto.add(5));
		assertTrue(conjunto.add(3));
		assertTrue(conjunto.add(7));
		assertTrue(conjunto.add(1));
		assertTrue(conjunto.add(9));
		
		assertEquals(5, conjunto.size());
		
		// Verificar orden
		for (int i = 0; i < conjunto.size() - 1; i++) {
			assertTrue(conjunto.get(i).compareTo(conjunto.get(i + 1)) < 0);
		}
		
		// Remover elementos
		conjunto.remove(0);
		assertEquals(4, conjunto.size());
		assertEquals(Integer.valueOf(3), conjunto.get(0));
		
		// Limpiar
		conjunto.clear();
		assertEquals(0, conjunto.size());
	}
}
