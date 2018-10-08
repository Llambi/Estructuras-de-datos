package p2Grafos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

	<T> GraphTest() {
		T[] nodes = (T[]) new Object[10];
	}

	@Test
	void testAddNode() {
		Graph<Integer> g = new Graph<>(10);

		// Casos positivos:

		// Caso 1: Añadir un nodo a una lista de nodos
		for (int i = 0; i <= 5; i++) {
			assertEquals(0, g.addNode(i));
		}

		// Casos Negativos:

		// Caso 1: null
		assertEquals(-1, g.addNode(null));

		// Caso 2: Ya existente
		assertEquals(-1, g.addNode(1));

		// Rellenamos
		for (int i = 6; i < 10; i++) {
			g.addNode(i);
		}

		// Caso 3: Ya llena
		assertEquals(-1, g.addNode(10));

	}

	@Test
	void testRemoveNode() {
		Graph<Integer> g = new Graph<>(4);

		for (int i = 0; i < 4; i++) {
			g.addNode(i);
		}

		// Pruebas Positivas:

		// Caso 1: Eliminamos nodos
		for (int i = 0; i < 4; i++) {
			assertEquals(0, g.removeNode(i));
		}

		// Pruebas Negativas:

		// Caso 2: Eliminar inexistente
		assertEquals(-1, g.removeNode(5));

		// Caso 3: Elimianr null
		assertEquals(-1, g.removeNode(null));
	}

	@Test
	void testExistNode() {
		Graph<Integer> g = new Graph<>(4);
		for (int i = 0; i < 4; i++) {
			g.addNode(i);
		}

		// Pruebas Positivas:

		// Caso 1: Existe
		for (int i = 0; i < 4; i++) {
			assertTrue(g.existNode(i));
		}

		// Pruebas Negativas:

		// Caso 1: No existe
		assertFalse(g.existNode(5));

		// Caso 2: null
		assertFalse(g.existNode(null));
	}

	@Test
	void testExistEdge() {
		Graph<Integer> g = new Graph<>(4);
		g.addNode(1);
		g.addNode(2);

		// Pruebas Positivas:

		// Caso 1: Existe
		g.addEdge(1, 2, 10);
		assertTrue(g.existEdge(1, 2));

		// Pruebas Negativas:

		// Caso 1: no existe
		assertFalse(g.existEdge(2, 3));

		// Caso 2: null -> y
		assertFalse(g.existEdge(null, 2));

		// Caso 3: y <- null
		assertFalse(g.existEdge(1, null));

		// Caso 4: null -> null
		assertFalse(g.existEdge(null, null));
	}

	@Test
	void testAddEdge() {
		Graph<Integer> g = new Graph<>(4);
		g.addNode(1);
		g.addNode(2);

		// Pruebas Positivas:

		// Caso 1: x -> y
		assertEquals(0, g.addEdge(1, 2, 10));

		// Pruebas Negativas:

		// Caso 2: no existe -> existe
		assertEquals(-1, g.addEdge(3, 1, 10));

		// Caso 3: existe -> no existe
		assertEquals(-1, g.addEdge(1, 3, 10));

		// Caso 4: no existe -> no existe
		assertEquals(-1, g.addEdge(3, 5, 10));

		// Caso 5: null -> existe
		assertEquals(-1, g.addEdge(null, 1, 10));

		// Caso 6: existe -> null
		assertEquals(-1, g.addEdge(1, null, 10));

		// Caso 7: null -> no existe
		assertEquals(-1, g.addEdge(null, 3, 10));

		// Caso 8: no existe -> null
		assertEquals(-1, g.addEdge(3, null, 10));

		// Caso 9: null -> null
		assertEquals(-1, g.addEdge(null, null, 10));

	}

	@Test
	void testRemoveEdge() {
		Graph<Integer> g = new Graph<>(10);
		g.addNode(1);
		g.addNode(2);

		g.addEdge(1, 2, 10);

		// Pruebas Positivas:

		// Caso 1: x -> y
		assertEquals(0, g.removeEdge(1, 2));

		// Pruebas Negativas:

		// Caso 1: no existe -> existe
		assertEquals(-1, g.removeEdge(3, 1));

		// Caso 2: existe -> no existe
		assertEquals(-1, g.removeEdge(1, 3));

		// Caso 3: no existe -> no existe
		assertEquals(-1, g.removeEdge(3, 5));

		// Caso 4: existente -> null
		assertEquals(-1, g.removeEdge(1, null));

		// Caso 5: null -> existe
		assertEquals(-1, g.removeEdge(null, 1));

		// Caso 6: no existe -> null
		assertEquals(-1, g.removeEdge(3, null));

		// Caso 7: null -> no existe
		assertEquals(-1, g.removeEdge(null, 3));

		// Caso 8: null -> null
		assertEquals(-1, g.removeEdge(null, null));

	}

	@Test
	void testGetEdge() {
		Graph<Integer> g = new Graph<>(3);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);

		g.addEdge(1, 2, 10);
		g.addEdge(1, 3, 5);

		// Pruebas Positivas:

		// Caso 1: existe
		assertEquals(10.0, g.getEdge(1, 2), 0.1);
		assertEquals(5.0, g.getEdge(1, 3), 0.1);

		// Pruebas Negativas:

		// Caso 1: no existe
		assertEquals(-1.0, g.getEdge(2, 1), 0.1);
	}

}
