package test;

import org.junit.jupiter.api.Test;
import p2Grafos.Graph;

import static org.junit.jupiter.api.Assertions.*;

// Para sustituir en toString() si dan error los assert de toString
// 				cadena += (edges[i][j]?df.format(weights[i][j]):df.format(0)) + "\t";

/**
 * @author UO250708
 *
 */
public class GraphTestV0Prof {

	private class MiNodoPrivado {
		private int numero;
		private String nombre;

		public MiNodoPrivado(int num, String nom) {
			numero = num;
			nombre = nom;
		}

		int getNumero() {
			return numero;
		}

		public String toString() {
			return "Contenido: [" + numero + "-" + nombre + "]";
		}

		public boolean equals(Object n) {
			if (n == null)
				return false;
			if (n instanceof MiNodoPrivado)
				return ((MiNodoPrivado) n).getNumero() == numero;
			return false;
		}

		@SuppressWarnings("unused")
		public int compareTo(MiNodoPrivado n) {
			if (n == null)
				return -1;
			return numero - n.getNumero();
		}
	}

	@Test
	void testInteger() {
		Graph<Integer> g = new Graph<>(3);

		assertEquals(0, g.addNode(1));
		assertEquals(0, g.addNode(2));
		assertEquals(-1, g.addNode(1)); // Ya existe
		assertEquals(-1, g.addNode(2)); // Ya existe
		assertEquals(0, g.addEdge(1, 2, 1.2));
		assertEquals(0, g.addEdge(2, 1, 2.1));

		assertEquals(1.2, g.getEdge(1, 2), 0.001);
		assertEquals(2.1, g.getEdge(2, 1), 0.001);
		assertEquals(-1, g.getEdge(1, 1), 0.001); // No existe

		assertTrue(g.existNode(2));
		assertTrue(g.existNode(1));
		assertFalse(g.existNode(3));

		assertTrue(g.existEdge(1, 2));
		assertTrue(g.existEdge(2, 1));
		assertFalse(g.existEdge(1, 1));
		assertFalse(g.existEdge(1, 7));
		assertFalse(g.existEdge(6, 9));

		assertEquals(-1, g.removeNode(3)); // No existe

		assertEquals(0, g.addNode(3));
		assertEquals(-1, g.addNode(4)); // no cabe

		assertEquals(-1, g.getEdge(1, 3), 0.001); // No existe la arista

		assertEquals(-1, g.getEdge(1, 4), 0.001); // No existe nodo destino
		assertEquals(-1, g.getEdge(5, 1), 0.001); // No existe nodo origen
		assertEquals(-1, g.getEdge(5, 4), 0.001); // No existe nodo origen ni destino
		assertEquals(1.2, g.getEdge(1, 2), 0.0001);
		assertEquals(2.1, g.getEdge(2, 1), 0.0001);
		assertEquals(-1, g.getEdge(2, 2), 0.001);
		assertEquals(-1, g.getEdge(1, 1), 0.001);

		assertEquals(0, g.removeNode(3));
		assertEquals(-1, g.removeNode(3)); // No existe, recien borrado

		System.out.println(g);

		assertEquals(0, g.addNode(4));
		assertEquals(0, g.addEdge(4, 4, 4.4));
		assertEquals(0, g.addEdge(4, 2, 4.2));
		assertEquals(0, g.addEdge(2, 4, 2.4));

		assertEquals(0, g.removeNode(1));
		assertEquals(-1, g.removeNode(1)); // No existe, recien borrado

		System.out.println(g);

		assertEquals(0, g.addNode(5));
		assertEquals(0, g.addEdge(5, 5, 5.5));
		assertEquals(0, g.addEdge(5, 2, 5.2));
		assertEquals(0, g.addEdge(2, 5, 2.5));

		System.out.println(g);

		assertEquals(0, g.removeNode(2));
		assertEquals(-1, g.removeNode(2)); // No existe, recien borrado

		System.out.println(g);

		assertEquals(0, g.removeNode(5));
		assertEquals(-1, g.removeNode(5)); // No existe, recien borrado

		System.out.println(g);
		assertEquals(0, g.removeNode(4));
		assertEquals(-1, g.removeNode(4)); // No existe, recien borrado

		System.out.println(g);
	}

	@Test
	void testAddNode() {
		Graph<Character> g = new Graph<>(4);

		assertEquals(-1, g.addNode(null));

		assertEquals(0, g.addNode('d'));
		assertEquals(0, g.addNode('c'));
		assertEquals(0, g.addNode('b'));
		assertEquals(0, g.addNode('a'));

		assertEquals(-1, g.addNode('a'));
		assertEquals(-1, g.addNode('b'));
		assertEquals(-1, g.addNode('c'));
		assertEquals(-1, g.addNode('d'));

		assertEquals(-1, g.addNode('e'));

		assertEquals(-1, g.addNode(null));
		assertEquals("NODES\nd\tc\tb\ta\t\n\n" + "EDGES\nF\tF\tF\tF\t\nF\tF\tF\tF\t\nF\tF\tF\tF\t\nF\tF\tF\tF\t\n\n"
				+ "WEIGHTS\n-\t-\t-\t-\t\n-\t-\t-\t-\t\n" + "-\t-\t-\t-\t\n-\t-\t-\t-\t\n", g.toString());
	}

	@Test
	void testAddEdge() {
		Graph<Character> g = new Graph<>(4);

		assertEquals(0, g.addNode('d'));
		assertEquals(0, g.addNode('c'));
		assertEquals(0, g.addNode('b'));
		assertEquals(0, g.addNode('a'));

		assertEquals(-1, g.addEdge('a', null, 5));
		assertEquals(-1, g.addEdge(null, 'a', 0.3));
		assertEquals(-1, g.addEdge(null, null, 1.1));
		assertEquals(-1, g.addEdge('a', 'a', -1.1));

		assertEquals(-1, g.addEdge('a', 'e', 1.1));
		assertEquals(-1, g.addEdge('f', 'a', 1.1));
		assertEquals(-1, g.addEdge('g', 'h', -1.1));

		assertEquals(0, g.addEdge('a', 'a', 65.65));
		assertEquals(0, g.addEdge('a', 'b', 65.66));
		assertEquals(0, g.addEdge('a', 'c', 65.67));
		assertEquals(0, g.addEdge('a', 'd', 65.68));
		assertEquals(0, g.addEdge('b', 'a', 66.65));
		assertEquals(0, g.addEdge('b', 'b', 66.66));
		assertEquals(0, g.addEdge('b', 'c', 66.67));
		assertEquals(0, g.addEdge('b', 'd', 66.68));
		assertEquals(0, g.addEdge('c', 'a', 67.65));
		assertEquals(0, g.addEdge('c', 'b', 67.66));
		assertEquals(0, g.addEdge('c', 'c', 67.67));
		assertEquals(0, g.addEdge('c', 'd', 67.68));
		assertEquals(0, g.addEdge('d', 'a', 68.65));
		assertEquals(0, g.addEdge('d', 'b', 68.66));
		assertEquals(0, g.addEdge('d', 'c', 68.67));
		assertEquals(0, g.addEdge('d', 'd', 68.68));

		assertEquals("NODES\nd\tc\tb\ta\t\n\n" + "EDGES\nT\tT\tT\tT\t\nT\tT\tT\tT\t\nT\tT\tT\tT\t\nT\tT\tT\tT\t\n\n"
				+ "WEIGHTS\n68,68\t68,67\t68,66\t68,65\t\n67,68\t67,67\t67,66\t67,65\t\n"
				+ "66,68\t66,67\t66,66\t66,65\t\n65,68\t65,67\t65,66\t65,65\t\n", g.toString());

		assertEquals(0, g.addEdge('a', 'a', 0.0));
		assertEquals(0, g.addEdge('b', 'b', 0.0));
		assertEquals(0, g.addEdge('c', 'c', 0.0));
		assertEquals(0, g.addEdge('d', 'd', 0.0));
		assertEquals("NODES\nd\tc\tb\ta\t\n\n" + "EDGES\nT\tT\tT\tT\t\nT\tT\tT\tT\t\nT\tT\tT\tT\t\nT\tT\tT\tT\t\n\n"
				+ "WEIGHTS\n0\t68,67\t68,66\t68,65\t\n67,68\t0\t67,66\t67,65\t\n"
				+ "66,68\t66,67\t0\t66,65\t\n65,68\t65,67\t65,66\t0\t\n", g.toString());

	}

	@Test
	void testRemoveNode() {
		Graph<Character> g = new Graph<>(4);

		assertEquals(-1, g.removeNode('h'));
		assertEquals(-1, g.removeNode(null));

		assertEquals(0, g.addNode('d'));
		assertEquals(0, g.removeNode('d'));
		assertEquals(-1, g.removeNode('d'));

		assertEquals(0, g.addNode('d'));
		assertEquals(-1, g.removeNode('h'));
		assertEquals(0, g.addNode('c'));
		assertEquals(-1, g.removeNode('h'));
		assertEquals(0, g.addNode('b'));
		assertEquals(-1, g.removeNode('h'));
		assertEquals(0, g.addNode('a'));
		assertEquals(-1, g.removeNode('e'));

		assertEquals(-1, g.removeNode(null));

		assertEquals(0, g.addEdge('d', 'a', 1));
		assertEquals(0, g.addEdge('d', 'b', 2));
		assertEquals(0, g.addEdge('d', 'c', 3));
		assertEquals(0, g.addEdge('d', 'd', 4));

		assertEquals(0, g.addEdge('c', 'a', 5));
		assertEquals(0, g.addEdge('c', 'b', 6));
		assertEquals(0, g.addEdge('c', 'c', 7));
		assertEquals(0, g.addEdge('c', 'd', 8));

		assertEquals(0, g.addEdge('b', 'a', 9));
		assertEquals(0, g.addEdge('b', 'b', 0));
		assertEquals(0, g.addEdge('b', 'c', 1));
		assertEquals(0, g.addEdge('b', 'd', 2));

		assertEquals(0, g.addEdge('a', 'a', 3));
		assertEquals(0, g.addEdge('a', 'b', 4));
		assertEquals(0, g.addEdge('a', 'c', 5));
		assertEquals(0, g.addEdge('a', 'd', 6));

		assertEquals("NODES\nd\tc\tb\ta\t\n\n" + "EDGES\nT\tT\tT\tT\t\nT\tT\tT\tT\t\nT\tT\tT\tT\t\nT\tT\tT\tT\t\n\n"
				+ "WEIGHTS\n4\t3\t2\t1\t\n8\t7\t6\t5\t\n" + "2\t1\t0\t9\t\n6\t5\t4\t3\t\n", g.toString());

		assertEquals(0, g.removeNode('a'));
		assertEquals(-1, g.removeNode('a'));

		assertEquals("NODES\nd\tc\tb\t\n\n" + "EDGES\nT\tT\tT\t\nT\tT\tT\t\nT\tT\tT\t\n\n"
				+ "WEIGHTS\n4\t3\t2\t\n8\t7\t6\t\n" + "2\t1\t0\t\n", g.toString());

		assertEquals(0, g.removeNode('c'));
		assertEquals(-1, g.removeNode('c'));

		assertEquals("NODES\nd\tb\t\n\n" + "EDGES\nT\tT\t\nT\tT\t\n\n" + "WEIGHTS\n4\t2\t\n" + "2\t0\t\n",
				g.toString());

		assertEquals(0, g.removeNode('d'));
		assertEquals(-1, g.removeNode('d'));

		System.out.println(g);
		assertEquals("NODES\nb\t\n\n" + "EDGES\nT\t\n\n" + "WEIGHTS\n0\t\n", g.toString());

		assertEquals(0, g.removeNode('b'));
		assertEquals(-1, g.removeNode('b'));

		assertEquals(-1, g.removeNode('h'));
		assertEquals(-1, g.removeNode(null));

		assertEquals(0, g.addNode('d'));
		assertEquals(0, g.removeNode('d'));
		assertEquals(-1, g.removeNode('d'));

		assertEquals(0, g.addNode('d'));
		assertEquals(-1, g.removeNode('h'));
		assertEquals(0, g.addNode('c'));
		assertEquals(-1, g.removeNode('h'));
		assertEquals(0, g.addNode('b'));
		assertEquals(-1, g.removeNode('h'));
		assertEquals(0, g.addNode('a'));
		assertEquals(-1, g.removeNode('e'));

		assertEquals(-1, g.removeNode(null));

		assertEquals(0, g.addEdge('d', 'a', 1));
		assertEquals(0, g.addEdge('d', 'b', 2));
		assertEquals(0, g.addEdge('d', 'c', 3));
		assertEquals(0, g.addEdge('d', 'd', 4));

		assertEquals(0, g.addEdge('c', 'a', 5));
		assertEquals(0, g.addEdge('c', 'b', 6));
		assertEquals(0, g.addEdge('c', 'c', 7));
		assertEquals(0, g.addEdge('c', 'd', 8));

		assertEquals(0, g.addEdge('b', 'a', 9));
		assertEquals(0, g.addEdge('b', 'b', 0));
		assertEquals(0, g.addEdge('b', 'c', 1));
		assertEquals(0, g.addEdge('b', 'd', 2));

		assertEquals(0, g.addEdge('a', 'a', 3));
		assertEquals(0, g.addEdge('a', 'b', 4));
		assertEquals(0, g.addEdge('a', 'c', 5));
		assertEquals(0, g.addEdge('a', 'd', 6));

		assertEquals("NODES\nd\tc\tb\ta\t\n\n" + "EDGES\nT\tT\tT\tT\t\nT\tT\tT\tT\t\nT\tT\tT\tT\t\nT\tT\tT\tT\t\n\n"
				+ "WEIGHTS\n4\t3\t2\t1\t\n8\t7\t6\t5\t\n" + "2\t1\t0\t9\t\n6\t5\t4\t3\t\n", g.toString());

		assertEquals(0, g.removeNode('a'));
		assertEquals(-1, g.removeNode('a'));

		assertEquals("NODES\nd\tc\tb\t\n\n" + "EDGES\nT\tT\tT\t\nT\tT\tT\t\nT\tT\tT\t\n\n"
				+ "WEIGHTS\n4\t3\t2\t\n8\t7\t6\t\n" + "2\t1\t0\t\n", g.toString());

		assertEquals(0, g.removeNode('c'));
		assertEquals(-1, g.removeNode('c'));

		assertEquals("NODES\nd\tb\t\n\n" + "EDGES\nT\tT\t\nT\tT\t\n\n" + "WEIGHTS\n4\t2\t\n" + "2\t0\t\n",
				g.toString());

		assertEquals(0, g.removeNode('d'));
		assertEquals(-1, g.removeNode('d'));

		System.out.println(g);
		assertEquals("NODES\nb\t\n\n" + "EDGES\nT\t\n\n" + "WEIGHTS\n0\t\n", g.toString());

		assertEquals(0, g.removeNode('b'));
		assertEquals(-1, g.removeNode('b'));

		assertEquals(-1, g.removeNode(null));
	}

	@Test
	void testRemoveEdge() {
		Graph<Character> g = new Graph<>(4);

		assertEquals(-1, g.removeEdge('d', 'd'));
		assertEquals(-1, g.removeEdge(null, null));
		assertEquals(-1, g.removeEdge('d', null));
		assertEquals(-1, g.removeEdge(null, 'd'));

		assertEquals(0, g.addNode('d'));
		assertEquals(-1, g.removeEdge('d', 'd'));
		assertEquals(-1, g.removeEdge(null, null));
		assertEquals(-1, g.removeEdge('d', null));
		assertEquals(-1, g.removeEdge(null, 'd'));

		System.out.println(g);
		assertEquals("NODES\nd\t\n\n" + "EDGES\nF\t\n\n" + "WEIGHTS\n-\t\n", g.toString());

		assertEquals(0, g.addEdge('d', 'd', 1));
		assertEquals(-1, g.removeEdge(null, null));
		assertEquals(-1, g.removeEdge('d', null));
		assertEquals(-1, g.removeEdge(null, 'd'));

		assertEquals("NODES\nd\t\n\n" + "EDGES\nT\t\n\n" + "WEIGHTS\n1\t\n", g.toString());

		assertEquals(0, g.removeEdge('d', 'd'));

		assertEquals(0, g.addNode('c'));
		assertEquals(0, g.addNode('b'));
		assertEquals(0, g.addNode('a'));

		assertEquals(-1, g.removeEdge('d', 'a'));
		assertEquals(0, g.addEdge('d', 'a', 1));
		assertEquals(0, g.removeEdge('d', 'a'));
		assertEquals(0, g.addEdge('d', 'a', 1));

		assertEquals(-1, g.removeEdge('d', 'b'));
		assertEquals(0, g.addEdge('d', 'b', 2));
		assertEquals(0, g.removeEdge('d', 'b'));
		assertEquals(0, g.addEdge('d', 'b', 2));

		assertEquals(-1, g.removeEdge('d', 'c'));
		assertEquals(0, g.addEdge('d', 'c', 3));
		assertEquals(0, g.removeEdge('d', 'c'));
		assertEquals(0, g.addEdge('d', 'c', 3));

		assertEquals(-1, g.removeEdge('d', 'd'));
		assertEquals(0, g.addEdge('d', 'd', 4));
		assertEquals(0, g.removeEdge('d', 'd'));
		assertEquals(0, g.addEdge('d', 'd', 4));

		assertEquals(0, g.addEdge('c', 'a', 5));
		assertEquals(0, g.addEdge('c', 'b', 6));
		assertEquals(0, g.addEdge('c', 'c', 7));
		assertEquals(0, g.addEdge('c', 'd', 8));

		assertEquals(0, g.addEdge('b', 'a', 9));
		assertEquals(0, g.addEdge('b', 'b', 0));
		assertEquals(0, g.addEdge('b', 'c', 1));
		assertEquals(0, g.addEdge('b', 'd', 2));

		assertEquals(0, g.addEdge('a', 'a', 3));
		assertEquals(0, g.addEdge('a', 'b', 4));
		assertEquals(0, g.addEdge('a', 'c', 5));
		assertEquals(0, g.addEdge('a', 'd', 6));

		assertEquals("NODES\nd\tc\tb\ta\t\n\n" + "EDGES\nT\tT\tT\tT\t\nT\tT\tT\tT\t\nT\tT\tT\tT\t\nT\tT\tT\tT\t\n\n"
				+ "WEIGHTS\n4\t3\t2\t1\t\n8\t7\t6\t5\t\n" + "2\t1\t0\t9\t\n6\t5\t4\t3\t\n", g.toString());

		for (char i = 'a'; i < 'e'; i++)
			for (char j = 'a'; j < 'e'; j++) {
				assertEquals(0, g.removeEdge(i, j));
				assertEquals(-1, g.removeEdge(i, j));
			}

		for (char i = 'a'; i < 'e'; i++)
			for (char j = 'a'; j < 'e'; j++)
				assertEquals(-1, g.removeEdge(i, j));

	}

	@Test
	void testExistNode() {
		Graph<Character> g = new Graph<>(4);

		assertFalse(g.existNode(null));

		for (char i = 'a'; i < 'e'; i++)
			assertFalse(g.existNode(i));

		for (char i = 'a'; i < 'e'; i++) {
			assertEquals(0, g.addNode(i));
			assertTrue(g.existNode(i));
		}

		for (char i = 'a'; i < 'z'; i++) {
			if (i < 'e')
				assertTrue(g.existNode(i));
			else
				assertFalse(g.existNode(i));
		}
	}

	@Test
	void testExistEdge() {
		Graph<Character> g = new Graph<>(4);

		for (char i = 'a'; i < 'e'; i++)
			for (char j = 'a'; j < 'e'; j++)
				assertFalse(g.existEdge(i, j));

		for (char i = 'a'; i < 'e'; i++) {
			assertEquals(0, g.addNode(i));
		}

		assertEquals(0, g.addEdge('d', 'd', 1));
		assertTrue(g.existEdge('d', 'd'));

		assertFalse(g.existEdge(null, null));
		assertFalse(g.existEdge('d', null));
		assertFalse(g.existEdge(null, 'd'));

		for (char i = 'a'; i < 'e'; i++)
			for (char j = 'a'; j < 'e'; j++) {
				if (i == 'd' && j == 'd')
					assertTrue(g.existEdge(i, j));
				else
					assertFalse(g.existEdge(i, j));
			}

		assertEquals(0, g.addEdge('d', 'a', 1));

		assertEquals(0, g.addEdge('d', 'b', 2));
		assertEquals(0, g.addEdge('d', 'c', 3));

		assertEquals(0, g.addEdge('c', 'a', 5));
		assertEquals(0, g.addEdge('c', 'b', 6));
		assertEquals(0, g.addEdge('c', 'c', 7));
		assertEquals(0, g.addEdge('c', 'd', 8));

		assertEquals(0, g.addEdge('b', 'a', 9));
		assertEquals(0, g.addEdge('b', 'b', 0));
		assertEquals(0, g.addEdge('b', 'c', 1));
		assertEquals(0, g.addEdge('b', 'd', 2));

		assertEquals(0, g.addEdge('a', 'a', 3));
		assertEquals(0, g.addEdge('a', 'b', 4));
		assertEquals(0, g.addEdge('a', 'c', 5));
		assertEquals(0, g.addEdge('a', 'd', 6));

		for (char i = 'a'; i < 'e'; i++)
			for (char j = 'a'; j < 'e'; j++)
				assertTrue(g.existEdge(i, j));

		assertEquals("NODES\na\tb\tc\td\t\n\n" + "EDGES\nT\tT\tT\tT\t\nT\tT\tT\tT\t\nT\tT\tT\tT\t\nT\tT\tT\tT\t\n\n"
				+ "WEIGHTS\n3\t4\t5\t6\t\n9\t0\t1\t2\t\n" + "5\t6\t7\t8\t\n1\t2\t3\t1\t\n", g.toString());
	}

	@Test
	void testGetEdge() {
		Graph<Character> g = new Graph<>(4);

		for (char i = 'a'; i < 'e'; i++)
			for (char j = 'a'; j < 'e'; j++)
				assertEquals(-1, g.getEdge(i, j), 0.1);

		for (char i = 'a'; i < 'e'; i++) {
			assertEquals(0, g.addNode(i));
		}

		assertEquals(0, g.addEdge('d', 'd', 4));
		assertEquals(4, g.getEdge('d', 'd'), 0.1);

		assertEquals(-1, g.getEdge(null, null), 0.1);
		assertEquals(-1, g.getEdge('d', null), 0.1);
		assertEquals(-1, g.getEdge(null, 'd'), 0.1);

		for (char i = 'a'; i < 'e'; i++)
			for (char j = 'a'; j < 'e'; j++) {
				if (i == 'd' && j == 'd')
					assertEquals(4, g.getEdge(i, j), 0.1);
				else
					assertEquals(-1, g.getEdge(i, j), 0.1);
			}

		assertEquals(0, g.addEdge('d', 'a', 1));

		assertEquals(0, g.addEdge('d', 'b', 2));
		assertEquals(0, g.addEdge('d', 'c', 3));

		assertEquals(0, g.addEdge('c', 'a', 5));
		assertEquals(0, g.addEdge('c', 'b', 6));
		assertEquals(0, g.addEdge('c', 'c', 7));
		assertEquals(0, g.addEdge('c', 'd', 8));

		assertEquals(0, g.addEdge('b', 'a', 9));
		assertEquals(0, g.addEdge('b', 'b', 0));
		assertEquals(0, g.addEdge('b', 'c', 1));
		assertEquals(0, g.addEdge('b', 'd', 2));

		assertEquals(0, g.addEdge('a', 'a', 3));
		assertEquals(0, g.addEdge('a', 'b', 4));
		assertEquals(0, g.addEdge('a', 'c', 5));
		assertEquals(0, g.addEdge('a', 'd', 6));

		for (char i = 'a'; i < 'e'; i++)
			for (char j = 'a'; j < 'e'; j++)
				switch (i) {
				case 'a':
					assertEquals(j - i + 3, g.getEdge(i, j), 0.1);
					break;
				case 'b':
					assertEquals((j - i + 10) % 10, g.getEdge(i, j), 0.1);
					break;
				case 'c':
					assertEquals((j - i + 7) % 10, g.getEdge(i, j), 0.1);
					break;
				case 'd':
					System.out.println(j - i);
					assertEquals((j - i + 4) % 10, g.getEdge(i, j), 0.1);
				}

		assertEquals("NODES\na\tb\tc\td\t\n\n" + "EDGES\nT\tT\tT\tT\t\nT\tT\tT\tT\t\nT\tT\tT\tT\t\nT\tT\tT\tT\t\n\n"
				+ "WEIGHTS\n3\t4\t5\t6\t\n9\t0\t1\t2\t\n" + "5\t6\t7\t8\t\n1\t2\t3\t4\t\n", g.toString());
	}

//	@Test
//	public void testString() {
//		Graph<String> g = new Graph<String>(100);
//
//		for (char i = 'A'; i <= 'Z'; i++) {
//			assertEquals("[" + "Nodo " + i, 0, g.addNode("Nodo " + i));
//			assertEquals("[" + "Nodo " + i, -1, g.addNode("Nodo " + i));
//		}
//
//		System.out.println(g);
//
//		for (char i = 'A'; i <= 'Z'; i++)
//			for (char j = 'A'; j <= 'Z'; j++) {
//				assertFalse("[" + "Nodo " + i + "--" + "Nodo " + j + "]", g.existEdge("Nodo " + i, "Nodo " + j));
//				if (i == j)
//					assertEquals("[" + "Nodo " + i + "--" + "Nodo " + j + "]", 0,
//							g.addEdge("Nodo " + i, "Nodo " + j, 0D));
//				else
//					assertEquals("[" + "Nodo " + i + "--" + "Nodo " + j + "]", 0,
//							g.addEdge("Nodo " + i, "Nodo " + j, i * j));
//				assertTrue("[" + "Nodo " + i + "--" + "Nodo " + j + "]", g.existEdge("Nodo " + i, "Nodo " + j));
//			}
//
//		System.out.println(g);
//
//		for (char i = 'A'; i <= 'Z'; i++)
//			for (char j = 'A'; j <= 'Z'; j++) {
//				if (i == j)
//					assertEquals("[" + "Nodo " + i + "--" + "Nodo " + j + "]", 0, g.getEdge("Nodo " + i, "Nodo " + j),
//							0.0001);
//				else
//					assertEquals("[" + "Nodo " + i + "--" + "Nodo " + j + "]", i * j,
//							g.getEdge("Nodo " + i, "Nodo " + j), 0.0001);
//
//				if ((i % 2 == 0 || j % 2 == 0) && i != j) {
//					assertEquals("[" + "Nodo " + i + "--" + "Nodo " + j + "]", 0,
//							g.removeEdge("Nodo " + i, "Nodo " + j));
//					assertEquals("[" + "Nodo " + i + "--" + "Nodo " + j + "]", -1,
//							g.removeEdge("Nodo " + i, "Nodo " + j));
//				}
//			}
//
//		System.out.println(g);
//
//	}
//
//	@Test
//	public void testMiNodo() {
//		Graph<MiNodoPrivado> g = new Graph<MiNodoPrivado>(10);
//
//		MiNodoPrivado nodos[] = new MiNodoPrivado[20];
//		String nombres[] = { "Cero", "Uno", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", "Diez",
//				"Once", "Doce" };
//
//		for (int i = 0; i < nombres.length; i++)
//			nodos[i] = new MiNodoPrivado(i, nombres[i]);
//
//		for (int i = 1; i <= 4; i++)
//			assertEquals(0, g.addNode(nodos[i]));
//
//		System.out.println(g);
//
//		assertEquals(-1, g.addNode(new MiNodoPrivado(4, "Four"))); // Repetido porque sólo compara el número
//
//		for (int i = 5; i <= 10; i++)
//			assertEquals(0, g.addNode(nodos[i]));
//
//		assertEquals(-1, g.addNode(nodos[11])); // no cabe
//		assertEquals(-1, g.addNode(nodos[12])); // No cabe
//
//		for (int i = 1; i <= 10; i++)
//			for (int j = 1; j <= 10; j++) {
//				assertFalse("[" + nodos[i] + "---" + nodos[j] + "]", g.existEdge(nodos[i], nodos[j]));
//				if (i == j)
//					assertEquals("[" + nodos[i] + "---" + nodos[j] + "]", 0, g.addEdge(nodos[i], nodos[j], i + j));
//				else
//					assertEquals("[" + nodos[i] + "---" + nodos[j] + "]", 0, g.addEdge(nodos[i], nodos[j], i * j));
//				assertTrue(g.existEdge(nodos[i], nodos[j]));
//			}
//
//		for (int i = 11; i < 13; i++)
//			for (int j = 11; j < 13; j++)
//				assertEquals("[" + nodos[i] + "---" + nodos[j] + "]", -1, g.addEdge(nodos[i], nodos[j], 99.9));
//
//		System.out.println(g);
//		for (int i = 0; i < nombres.length; i++)
//			for (int j = 0; j < nombres.length; j++)
//				if (i < 1 || j < 1 || i > 10 || j > 10)
//					assertEquals("[" + nodos[i] + "---" + nodos[j] + "]", -1, g.getEdge(nodos[i], nodos[j]), 0.0001);
//				else if (i == j)
//					assertEquals("[" + nodos[i] + "---" + nodos[j] + "]", i + j, g.getEdge(nodos[i], nodos[j]), 0.0001);
//				else
//					assertEquals("[" + nodos[i] + "---" + nodos[j] + "]", i * j, g.getEdge(nodos[i], nodos[j]), 0.0001);
//
//		System.out.println(g);
//
//		assertEquals(0, g.addEdge(nodos[10], nodos[10], 999.9)); // cambia el valor de la arista
//		assertEquals(0, g.removeNode(nodos[1]));
//
//		assertEquals(999.9, g.getEdge(nodos[10], nodos[10]), 0.0001);
//		System.out.println(g);
//
//		for (int i = 1; i < 8; i++)
//			if (i > 1)
//				assertEquals("nodos[" + i + "] ", 0, g.removeEdge(nodos[i], nodos[i]));
//			else
//				assertEquals("nodos[" + i + "] ", -1, g.removeEdge(nodos[i], nodos[i]));
//
//		System.out.println(g);
//
//		for (int i = 1; i <= 10; i += 2)
//			if (i > 1) {
//				assertEquals("nodos[" + i + "] ", 0, g.removeEdge(nodos[i], nodos[i + 1]));
//				assertEquals("nodos[" + i + "] ", 0, g.removeEdge(nodos[i + 1], nodos[i]));
//			} else {
//				assertEquals("nodos[" + i + "] ", -1, g.removeEdge(nodos[i], nodos[i + 1]));
//				assertEquals("nodos[" + i + "] ", -1, g.removeEdge(nodos[i + 1], nodos[i]));
//			}
//
//		System.out.println(g);
//
//		assertEquals(0, g.removeNode(nodos[9]));
//		assertEquals(-1, g.removeNode(nodos[9]));
//		assertEquals(0, g.removeNode(nodos[2]));
//
//		System.out.println(g);
//	}

}