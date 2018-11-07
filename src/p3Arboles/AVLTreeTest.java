package p3Arboles;

import static org.junit.Assert.*;

import org.junit.Test;

public class AVLTreeTest {

	AVLTree<Integer> arbol = new AVLTree<>();

	@Test
	public void addTest() {

		// CASOS POSITIVOS
		assertEquals(0, arbol.addNode(13));
		assertEquals("13:FB=0	_	_	", arbol.toStringParaPruebas());
		assertEquals(0, arbol.addNode(5));
		assertEquals("13:FB=-1	5:FB=0	_	_	_	", arbol.toStringParaPruebas());
		assertEquals(0, arbol.addNode(4));
		assertEquals("5:FB=0	4:FB=0	_	_	13:FB=0	_	_	", arbol.toStringParaPruebas());
		assertEquals(0, arbol.addNode(2));
		assertEquals("5:FB=-1	4:FB=-1	2:FB=0	_	_	_	13:FB=0	_	_	", arbol.toStringParaPruebas());
		assertEquals(0, arbol.addNode(8));
		assertEquals("5:FB=0	4:FB=-1	2:FB=0	_	_	_	13:FB=-1	8:FB=0	_	_	_	",
				arbol.toStringParaPruebas());
		assertEquals(0, arbol.addNode(34));
		assertEquals("5:FB=0	4:FB=-1	2:FB=0	_	_	_	13:FB=0	8:FB=0	_	_	34:FB=0	_	_	",
				arbol.toStringParaPruebas());
		assertEquals(0, arbol.addNode(94));
		assertEquals("5:FB=1	4:FB=-1	2:FB=0	_	_	_	13:FB=1	8:FB=0	_	_	34:FB=1	_	94:FB=0	_	_	",
				arbol.toStringParaPruebas());
		assertEquals(0, arbol.addNode(7));
		assertEquals(
				"5:FB=1	4:FB=-1	2:FB=0	_	_	_	13:FB=0	8:FB=-1	7:FB=0	_	_	_	34:FB=1	_	94:FB=0	_	_	",
				arbol.toStringParaPruebas());
		// Nos muestra el arbol con los nodos a�adidos
		System.out.println("ARBOL AVL(tumbado) \n" + arbol.toString());

		// CASOS NEGATIVOS

		// nodo repetido
		assertEquals(-1, arbol.addNode(4));

		// nodo null
		assertEquals(-2, arbol.addNode(null));

		// Tras los casos negativos el arbol no deberia cambiar
		assertEquals(
				"5:FB=1	4:FB=-1	2:FB=0	_	_	_	13:FB=0	8:FB=-1	7:FB=0	_	_	_	34:FB=1	_	94:FB=0	_	_	",
				arbol.toStringParaPruebas());
	}

	@Test
	public void rotacionesSimplesTest() {

		// ROTACION SIMPLE IZQUIERDA
		System.out.println("------------------------------------------------------------------------");
		System.out.println("ROTACION SIMPLE IZQUIERDA\n");
		assertEquals(0, arbol.addNode(14));
		// System.out.println("A�adimos 14\n\n" + arbol.toString());
		assertEquals(0, arbol.addNode(5));
		System.out.println("Arbol antes de la rotacion\n\n" + arbol.toString());
		assertEquals(0, arbol.addNode(4));
		System.out.println("Al a�adir 4 se produce una rotaci�n simple a la izquierda \n\n" + arbol.toString());

		// ROTACION SIMPLE DERECHA
		System.out.println("---------------------------------------------------------------");
		System.out.println("ROTACION SIMPLE DERECHA\n");
		assertEquals(0, arbol.addNode(16));
		System.out.println("Arbol antes de la rotacion\n\n" + arbol.toString());
		assertEquals(0, arbol.addNode(17));
		System.out.println("Al a�adir 17 se produce una rotaci�n simple a la derecha\n\n" + arbol.toString());

	}

	@Test
	public void rotacionesDobleIzquierdaTest() {

		// ROTACION DOBLE IZQUIERDA
		System.out.println("------------------------------------------------------------------------");
		System.out.println("ROTACION DOBLE IZQUIERDA\n");
		assertEquals(0, arbol.addNode(14));
		// System.out.println("A�adimos 14\n\n" + arbol.toString());
		assertEquals(0, arbol.addNode(15));
		// System.out.println("A�adimos 15\n\n" + arbol.toString());
		assertEquals(0, arbol.addNode(7));
		// System.out.println("A�adimos 7 \n\n" + arbol.toString());
		assertEquals(0, arbol.addNode(6));
		// System.out.println("A�adimos 6 \n\n" + arbol.toString());
		assertEquals(0, arbol.addNode(9));
		System.out.println("Arbol antes de la rotacion \n\n" + arbol.toString());
		assertEquals(0, arbol.addNode(10));
		System.out.println("Al a�adir 10 se produce una doble rotacion a la izquierda\n\n" + arbol.toString());
	}

	@Test
	public void rotacionesDobleDerechaTest() {

		// ROTACION DOBLE DERECHA
		System.out.println("------------------------------------------------------------------------");
		System.out.println("ROTACION DOBLE DERECHA\n");
		assertEquals(0, arbol.addNode(14));
		// System.out.println("A�adimos 14\n\n" + arbol.toString());
		assertEquals(0, arbol.addNode(7));
		// System.out.println("A�adimos 7\n\n" + arbol.toString());
		assertEquals(0, arbol.addNode(18));
		// System.out.println("A�adimos 18 \n\n" + arbol.toString());
		assertEquals(0, arbol.addNode(16));
		// System.out.println("A�adimos 16 \n\n" + arbol.toString());
		assertEquals(0, arbol.addNode(20));
		System.out.println("Arbol antes de la rotacion \n\n" + arbol.toString());
		assertEquals(0, arbol.addNode(15));
		System.out.println("Al a�adir 15 se produce una doble rotacion a la derecha\n\n" + arbol.toString());

	}

	@Test
	public void generalRemoveTest() {

		// Intentamos borrar con un parametro null

		assertEquals(-1, arbol.removeNode(null)); // se lanzar�a la excepcion--false

		// Intentamos borrar con un arbol vac�o
		assertEquals(-2, arbol.removeNode(3));

		// borramos el �nico elemento del arbol
		assertEquals(0, arbol.addNode(14));
		assertEquals(0, arbol.removeNode(14));
		assertEquals(null, arbol.searchNode(14));

		// A�adimos nodos
		assertEquals(0, arbol.addNode(14));
		assertEquals(0, arbol.addNode(5));
		assertEquals(0, arbol.addNode(4));
		assertEquals(0, arbol.addNode(2));
		assertEquals(0, arbol.addNode(8));
		assertEquals(0, arbol.addNode(34));
		assertEquals(0, arbol.addNode(94));
		assertEquals(0, arbol.addNode(7));

		// Borramos un nodo que no existe
		assertEquals(-1, arbol.removeNode(78));

		System.out.println("------------------------------------------------------------------------");
		System.out.println("TEST REMOVE \n");
		System.out.println("ARBOL AVL(tumbado) \n" + arbol.toString());

		// BORRAMOS NODO SIN HIJOS
		System.out.println("Borramos un nodo sin hijos (2) que produce una rotaci�n simple dch\n");
		assertEquals(0, arbol.removeNode(2));
		assertEquals(null, arbol.searchNode(2));
		System.out.println(arbol.toString());

	}

	@Test
	public void removeDoshijosTest() {

		// A�adimos varios nodos al arbol
		assertEquals(0, arbol.addNode(14));
		assertEquals(0, arbol.addNode(5));
		assertEquals(0, arbol.addNode(4));
		assertEquals(0, arbol.addNode(2));
		assertEquals(0, arbol.addNode(8));
		assertEquals(0, arbol.addNode(34));
		assertEquals(0, arbol.addNode(94));
		assertEquals(0, arbol.addNode(7));

		System.out.println("------------------------------------------------------------------------");
		System.out.println("TEST REMOVE CON DOS HIJOS \n");
		System.out.println("ARBOL AVL(tumbado) \n" + arbol.toString());

		// borramos un nodo con dos hijos
		System.out.println("Borramos un nodo con dos hijos (14) que no produce rotaciones\n");
		assertEquals(0, arbol.removeNode(14));
		assertEquals(null, arbol.searchNode(14));
		System.out.println(arbol.toString());

	}

	@Test
	public void removeHijoIzquierdaTest() {
		// A�adimos varios nodos al arbol
		assertEquals(0, arbol.addNode(14));
		assertEquals(0, arbol.addNode(5));
		assertEquals(0, arbol.addNode(4));
		assertEquals(0, arbol.addNode(2));
		assertEquals(0, arbol.addNode(8));
		assertEquals(0, arbol.addNode(34));
		assertEquals(0, arbol.addNode(94));
		assertEquals(0, arbol.addNode(7));

		System.out.println("------------------------------------------------------------------------");
		System.out.println("TEST REMOVE CON UN HIJO POR LA IZQUIERDA \n");
		System.out.println("ARBOL AVL(tumbado) \n" + arbol.toString());
		// borramos un nodo con un hijo por la izquierda
		System.out.println("Borramos un nodo con un hijo (4) que produce una rotaci�n simple dch\n");
		assertEquals(0, arbol.removeNode(4));
		assertEquals(null, arbol.searchNode(4));
		System.out.println(arbol.toString());

	}

	@Test
	public void removeHijoDerechaTest() {
		// A�adimos varios nodos al arbol
		assertEquals(0, arbol.addNode(14));
		assertEquals(0, arbol.addNode(6));
		assertEquals(0, arbol.addNode(4));
		assertEquals(0, arbol.addNode(5));
		assertEquals(0, arbol.addNode(8));
		assertEquals(0, arbol.addNode(34));
		assertEquals(0, arbol.addNode(94));
		assertEquals(0, arbol.addNode(7));

		System.out.println("------------------------------------------------------------------------");
		System.out.println("TEST REMOVE CON UN HIJO POR LA DERECHA \n");
		System.out.println("ARBOL AVL(tumbado) \n" + arbol.toString());
		// borramos un nodo con un hijo por la izquierda
		System.out.println("Borramos un nodo con un hijo (4) que produce una rotaci�n simple dch\n");
		assertEquals(0, arbol.removeNode(4));
		assertEquals(null, arbol.searchNode(4));
		System.out.println(arbol.toString());

	}

}
