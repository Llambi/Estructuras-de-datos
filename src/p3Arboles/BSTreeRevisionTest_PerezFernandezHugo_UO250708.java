package p3Arboles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BSTreeRevisionTest_PerezFernandezHugo_UO250708 {
	BSTree<Double> g;

	// Se debe rellenar este test siguiendo las instrucciones y poniendo comentarios
	// de para qué se hacen las pruebas
	// y qué se espera probar con ellas.
	// Si tienes que crregir tu BSTree, pon un comentario junto a la prueba que no
	// te ha funcionado e indica el problema
	@Test
	void testParaEntregar() {
		g = new BSTree<Double>();
		int orden = 0;

		dejaEsteMetodoVacio(orden++);

		// fail("Not yet implemented");

		// Añadir 7 nodos para que quede un arbol completo. Id comprobando que el arbol
		// es correcto tras cada addNode
		assertEquals(0, g.addNode(5.0));
		assertEquals(0, g.addNode(6.0));
		assertEquals(0, g.addNode(7.0));
		assertEquals(0, g.addNode(8.0));
		assertEquals(0, g.addNode(9.0));
		assertEquals(0, g.addNode(10.0));
		assertEquals(0, g.addNode(11.0));

		// mostramos el arbol para ver si es correcto
		System.out.println("PRUEBAS ADD\n");
		System.out.println(g + "\n");
		System.out.println(g.toStringParaPruebas() + "\n");
		// Guardamos el recorrido para comparar que no se modifica si se añaden despues
		// los mismos nodos.
		String recorrido1 = g.toStringParaPruebas();

		dejaEsteMetodoVacio(orden++);

		// Añadir los mismos 7 nodos en orden inverso a como se hizo antes. Id
		// comprobando que el arbol es correcto tras cada addNode
		assertEquals(-1, g.addNode(11.0));
		assertEquals(-1, g.addNode(10.0));
		assertEquals(-1, g.addNode(9.0));
		assertEquals(-1, g.addNode(8.0));
		assertEquals(-1, g.addNode(7.0));
		assertEquals(-1, g.addNode(6.0));
		assertEquals(-1, g.addNode(5.0));
		String recorrido2 = g.toStringParaPruebas();

		// Comprobar que el arbol es correcto (¿se han añadido?)
		assertEquals(recorrido1, recorrido2); // Ambos recorridos deben ser iguales porque no se deben de haber añadido.
		dejaEsteMetodoVacio(orden++);

		// Añadir los nodos que estimes oportunos para probar los borrados en los 3
		// casos en que se puede borar un nodo
		// Hacedlo sin que el grafo baje de 7 nodos y comporbando tras cada operación
		// que el arbol es correcto

		dejaEsteMetodoVacio(orden++);
		assertEquals(-1, g.addNode(6.0));
		// primer caso: borrar elemento hoja
		assertEquals(0, g.addNode(4.0)); // Añadimos otro nodo para que haya 7 siempre
		assertEquals(0, g.removeNode(11.0));
		assertEquals("5.0	4.0	_	_	6.0	_	7.0	_	8.0	_	9.0	_	10.0	_	_	", g.toStringParaPruebas());
		System.out.println("PRUEBAS REMOVE\n");
		System.out.println("PRUEBAS REMOVE (HOJA)\n");
		System.out.println(g + "\n");
		System.out.println(g.toStringParaPruebas() + "\n");

		assertEquals(0, g.addNode(7.5)); // Añadimos otro nodo para que haya 7 siempre y preparar el 3 tipo de borrado
		assertEquals(0, g.removeNode(9.0)); // El nodo 9 tiene un hijo solo
		assertEquals("5.0	4.0	_	_	6.0	_	7.0	_	8.0	7.5	_	_	10.0	_	_	", g.toStringParaPruebas());
		System.out.println("PRUEBAS REMOVE (1 HIJO)\n");
		System.out.println(g + "\n");
		System.out.println(g.toStringParaPruebas() + "\n");

		assertEquals(0, g.addNode(4.5)); // Añadimos otro nodo para que haya 7 siempre
		assertEquals(0, g.removeNode(8.0)); // Este nodo tiene 2 hijos por lo que realizara el tercer caso
		assertEquals("5.0	4.0	_	4.5	_	_	6.0	_	7.0	_	7.5	_	10.0	_	_	", g.toStringParaPruebas());
		System.out.println("PRUEBAS REMOVE (2 HIJOS)\n");
		System.out.println(g + "\n");
		System.out.println(g.toStringParaPruebas() + "\n");

		assertEquals(0, g.addNode(4.2)); // Añadimos otro nodo para que haya 7 siempre
		assertEquals(0, g.removeNode(5.0)); // Este nodo tiene 2 hijos por lo que realizara el tercer caso
		assertEquals("4.5	4.0	_	4.2	_	_	6.0	_	7.0	_	7.5	_	10.0	_	_	", g.toStringParaPruebas());
		System.out.println("PRUEBAS REMOVE (2 HIJOS - RAIZ)\n");
		System.out.println(g + "\n");
		System.out.println(g.toStringParaPruebas() + "\n");

	}

	void dejaEsteMetodoVacio(int n) {

	}

}
