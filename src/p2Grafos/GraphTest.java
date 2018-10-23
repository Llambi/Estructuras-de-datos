package p2Grafos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    public static final double INFINITY = Double.POSITIVE_INFINITY;

    @Test
    public void testAddNode() {
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
    public void testRemoveNode() {
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
    public void testExistNode() {
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
    public void testExistEdge() {
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
    public void testAddEdge() {
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
    public void testRemoveEdge() {
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
    public void testGetEdge() {
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

    @Test
    public void testDijsktra() {
        // Pruebas positivas

        // Case 1: Dijkstra integer
        Graph<Integer> g = new Graph<Integer>(5);
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);

        g.addEdge(0, 4, 3);
        g.addEdge(0, 1, 12);
        g.addEdge(0, 2, 18);

        g.addEdge(1, 3, 4);

        g.addEdge(4, 1, 2);

        g.addEdge(3, 2, 1);
        g.addEdge(3, 0, 7);

        double[] result = new double[]{0.0, 5.0, 10.0, 9.0, 3.0};
        double[] dijskCall = g.dijkstra(0);

        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i], dijskCall[i], 0.1);
        }

        // Case 2: Dijkstra con String
        Graph<String> g2 = new Graph<String>(4);
        g2.addNode("A");
        g2.addNode("B");
        g2.addNode("C");
        g2.addNode("D");
        g2.addEdge("A", "B", 1);
        g2.addEdge("A", "C", 5);
        g2.addEdge("B", "C", 2);
        g2.addEdge("B", "D", 3);
        g2.addEdge("D", "A", 4);

        assertArrayEquals(new double[]{0.0, 1.0, 3.0, 4.0}, g2.dijkstra("A"), 0.01);
        assertArrayEquals(new double[]{7.0, 0.0, 2.0, 3.0}, g2.dijkstra("B"), 0.01);
        assertArrayEquals(new double[]{INFINITY, Double.POSITIVE_INFINITY, 0.0, Double.POSITIVE_INFINITY},
                g2.dijkstra("C"), 0.01);
        assertArrayEquals(new double[]{4.0, 5.0, 7.0, 0.0}, g2.dijkstra("D"), 0.01);

        // Pruebas Negativas:

        // Case 1: Init node doesnt exist
        assertNull(g.dijkstra(null));

        // Case 2: Dijkstra con aristas de pesos negativos.

        // Este caso no se dara puesto que en el addEdge() no se aceptan pesos
        // negativos.

        // Graph<Integer> g3 = new Graph<Integer>(3);
        // g3.addNode(1);
        // g3.addNode(2);
        // g3.addNode(3);
        // g3.addEdge(1, 2, 2.0);
        // g3.addEdge(2, 3, -2.0);
        //
        // assertNull(g3.dijkstra(1));
    }

    @Test
    public void testGraphNestor() {

        double[] sol0 = {0, 5, 21, 20, 28, 11};
        double[] sol1 = {INFINITY, 0, 16, INFINITY, 23, 6};
        double[] sol2 = {INFINITY, INFINITY, 0, INFINITY, 7, INFINITY};
        double[] sol3 = {INFINITY, INFINITY, 2, 0, 9, INFINITY};
        double[] sol4 = {INFINITY, INFINITY, INFINITY, INFINITY, 0, INFINITY};
        double[] sol5 = {INFINITY, INFINITY, 10, INFINITY, 17, 0};

        Graph<Integer> g = new Graph<Integer>(6);
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addNode(5);

        g.addEdge(0, 1, 5);
        g.addEdge(0, 3, 20);
        g.addEdge(0, 5, 13);

        g.addEdge(1, 5, 6);

        g.addEdge(2, 4, 7);

        g.addEdge(3, 2, 2);
        g.addEdge(3, 4, 9);

        g.addEdge(5, 2, 10);

        assertArrayEquals(sol0, g.dijkstra(0), 0.1);
        assertArrayEquals(sol1, g.dijkstra(1), 0.1);
        assertArrayEquals(sol2, g.dijkstra(2), 0.1);
        assertArrayEquals(sol3, g.dijkstra(3), 0.1);
        assertArrayEquals(sol4, g.dijkstra(4), 0.1);
        assertArrayEquals(sol5, g.dijkstra(5), 0.1);

        g.floyd();
        System.out.println(g);

        assertArrayEquals(sol0, g.getAFloyd()[0], 0.1);
        assertArrayEquals(sol1, g.getAFloyd()[1], 0.1);
        assertArrayEquals(sol2, g.getAFloyd()[2], 0.1);
        assertArrayEquals(sol3, g.getAFloyd()[3], 0.1);
        assertArrayEquals(sol4, g.getAFloyd()[4], 0.1);
        assertArrayEquals(sol5, g.getAFloyd()[5], 0.1);

        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||\n");

        // Añadimos una arista: (4) -[300]-> (0)
        g.addEdge(4, 0, 300);

        sol0 = new double[]{0, 5, 21, 20, 28, 11};
        sol1 = new double[]{323, 0, 16, 343, 23, 6};
        sol2 = new double[]{307, 312, 0, 327, 7, 318};
        sol3 = new double[]{309, 314, 2, 0, 9, 320};
        sol4 = new double[]{300, 305, 321, 320, 0, 311};
        sol5 = new double[]{317, 322, 10, 337, 17, 0};

        assertArrayEquals(sol0, g.dijkstra(0), 0.1);
        assertArrayEquals(sol1, g.dijkstra(1), 0.1);
        assertArrayEquals(sol2, g.dijkstra(2), 0.1);
        assertArrayEquals(sol3, g.dijkstra(3), 0.1);
        assertArrayEquals(sol4, g.dijkstra(4), 0.1);
        assertArrayEquals(sol5, g.dijkstra(5), 0.1);

        g.floyd();
        System.out.println(g);

        assertArrayEquals(sol0, g.getAFloyd()[0], 0.1);
        assertArrayEquals(sol1, g.getAFloyd()[1], 0.1);
        assertArrayEquals(sol2, g.getAFloyd()[2], 0.1);
        assertArrayEquals(sol3, g.getAFloyd()[3], 0.1);
        assertArrayEquals(sol4, g.getAFloyd()[4], 0.1);
        assertArrayEquals(sol5, g.getAFloyd()[5], 0.1);
    }

    @Test
    public void floydTest() {
        Graph<Integer> g = new Graph<Integer>(5);
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);

        g.addEdge(0, 4, 3);
        g.addEdge(0, 1, 12);
        g.addEdge(0, 2, 18);
        g.addEdge(1, 3, 4);
        g.addEdge(4, 1, 2);
        g.addEdge(3, 2, 1);
        g.addEdge(3, 0, 7);

        // Casos Positivos
        // funciona bien
        double[][] result = new double[][]{
                {0.0, 5.0, 10.0, 9.0, 3.0}, {11.0, 0.0, 5.0, 4.0, 14.0}, {Double.POSITIVE_INFINITY,
                Double.POSITIVE_INFINITY, 0.0, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
                {7.0, 12.0, 1.0, 0.0, 10.0}, {13.0, 2.0, 7.0, 6.0, 0.0}};

        g.floyd();
        double[][] floydd = g.getAFloyd();

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                assertEquals(result[i][j], floydd[i][j], 0.1);
            }
        }

        // Casos negativos
        // Pesos negativos
        Graph<Integer> g2 = new Graph<Integer>(3);
        g2.addNode(1);
        g2.addNode(2);
        g2.addNode(3);
        g2.addEdge(1, 2, 2.0);
        g2.addEdge(2, 3, -2.0); // No va a entrar por ser negativa

        assertEquals(0, g2.floyd());
        assertNotNull(g2.getAFloyd());
        assertNotNull(g2.getPFloyd());

        // CGrafo vacio
        Graph<Integer> g3 = new Graph<Integer>(3);
        assertEquals(-1, g3.floyd());
        assertNull(g3.getAFloyd());
        assertNull(g3.getPFloyd());

        // Grafo modificado
        assertEquals(0, g2.removeNode(1));

        assertNull(g2.getAFloyd());
        assertNull(g2.getPFloyd());
        assertEquals(0, g2.floyd());
        assertNotNull(g2.getAFloyd());
        assertNotNull(g2.getPFloyd());

        assertEquals(0, g2.addNode(1));

        assertNull(g2.getAFloyd());
        assertNull(g2.getPFloyd());
        assertEquals(0, g2.floyd());
        assertNotNull(g2.getAFloyd());
        assertNotNull(g2.getPFloyd());

        assertEquals(0, g2.addEdge(1, 2, 2.0));

        assertNull(g2.getAFloyd());
        assertNull(g2.getPFloyd());
        assertEquals(0, g2.floyd());
        assertNotNull(g2.getAFloyd());
        assertNotNull(g2.getPFloyd());

        assertEquals(0, g2.removeEdge(1, 2));

        assertNull(g2.getAFloyd());
        assertNull(g2.getPFloyd());
        assertEquals(0, g2.floyd());
        assertNotNull(g2.getAFloyd());
        assertNotNull(g2.getPFloyd());
    }

    @Test
    public void minCostPathTest() {
        Graph<Integer> graph = new Graph<Integer>(6);
        // CASOS NEGATIVOS
        assertEquals(-1, graph.minCostPath(0, 0), 0.1);

        assertEquals(-1, graph.minCostPath(null, 0), 0.1);
        assertEquals(-1, graph.minCostPath(0, null), 0.1);

        assertEquals(-1, graph.minCostPath(0, -1), 0.1);
        assertEquals(-1, graph.minCostPath(80, 0), 0.1);

        for (int i = 0; i < 6; i++)
            graph.addNode(i);

        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 3, 2);
        graph.addEdge(0, 5, 3);
        graph.addEdge(1, 4, 4);
        graph.addEdge(2, 1, 5);
        graph.addEdge(2, 3, 6);
        graph.addEdge(3, 4, 7);
        graph.addEdge(4, 2, 8);
        graph.addEdge(5, 1, 9);
        graph.addEdge(5, 4, 10);


        // CASOS POSITIVOS

        // Calculamos todos los caminos de coste mínimo desde 0
        assertEquals(0.0, graph.minCostPath(0, 0), 0.1);
        assertEquals(1.0, graph.minCostPath(0, 1), 0.1);
        assertEquals(13.0, graph.minCostPath(0, 2), 0.1);
        assertEquals(2.0, graph.minCostPath(0, 3), 0.1);
        assertEquals(5.0, graph.minCostPath(0, 4), 0.1);
        assertEquals(3.0, graph.minCostPath(0, 5), 0.1);


    }

    @Test
    public void recorridoProfundidadTest() {
        Graph<Integer> graph = new Graph<Integer>(6);
        // CONSTRUIMOS GRAFO
        // añadimos los nodos correspondientes
        for (int i = 0; i < 6; i++)
            graph.addNode(i);

        // añadimos las aristas con los pesos correspondientes
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 4, 4);
        graph.addEdge(2, 3, 6);
        graph.addEdge(3, 4, 7);
        graph.addEdge(4, 2, 8);
        graph.addEdge(5, 4, 10);

        // CASOS POSITIVOS
        assertEquals("0	1	4	2	3	", graph.recorridoProfundidad(0));
        assertEquals("1	4	2	3	", graph.recorridoProfundidad(1));
        assertEquals("2	3	4	", graph.recorridoProfundidad(2));
        assertEquals("3	4	2	", graph.recorridoProfundidad(3));
        assertEquals("4	2	3	", graph.recorridoProfundidad(4));
        assertEquals("5	4	2	3	", graph.recorridoProfundidad(5));

        // CASOS NEGATIVOS
        // No existe el nodo o es null
        assertEquals("", graph.recorridoProfundidad(15));
        assertEquals("", graph.recorridoProfundidad(null));

    }

    @Test
    public void pathTest() {
        Graph<Integer> graph = new Graph<Integer>(6);
        for (int i = 0; i < 6; i++)
            graph.addNode(i);

        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 4, 4);
        graph.addEdge(2, 1, 5);
        graph.addEdge(3, 4, 7);
        graph.addEdge(4, 2, 8);
        graph.addEdge(5, 1, 9);


        // CASOS NEGATIVOS
        assertEquals("", graph.path(null, 0));
        assertEquals("", graph.path(0, null));
        assertEquals("", graph.path(0, -1));
        assertEquals("", graph.path(20, 5));

        // CASOS POSITIVOS

        // Calculo de nodo 0 a otros
        assertEquals("0\t(1.0)\t1\t", graph.path(0, 1));
        assertEquals("0\t(1.0)\t1\t(4.0)\t4\t(8.0)\t2\t", graph.path(0, 2));
        assertEquals("0\t(Infinity)\t3\t", graph.path(0, 3));
        assertEquals("0\t(1.0)\t1\t(4.0)\t4\t", graph.path(0, 4));
        assertEquals("0\t(Infinity)\t5\t", graph.path(0, 5));

        // Origen y Destino coinciden
        assertEquals("0\t", graph.path(0, 0));


//        System.out.println(graph.toString());

    }
}
