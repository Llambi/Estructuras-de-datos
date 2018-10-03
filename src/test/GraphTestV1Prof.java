package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import p2Grafos.Graph;

/**
 * @author UO250708
 *
 */
public class GraphTestV1Prof {
	Graph<Character> g;
	Graph<String> gs;
	Graph<Integer> gInteger;
	Graph<String> gStrings;
	Graph<Integer> gp;

	double[][] resAFloyd = new double[][] { { 0, 14, 13, 12, 25, 24, 22, 21, 31, 30, Double.POSITIVE_INFINITY },
			{ 14, 0, 27, 26, 11, 10, 36, 35, 17, 16, Double.POSITIVE_INFINITY },
			{ 10, 24, 0, 22, 35, 34, 9, 8, 41, 40, Double.POSITIVE_INFINITY },
			{ Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0, Double.POSITIVE_INFINITY,
					Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
					Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY },
			{ 11, 25, 24, 23, 0, 7, 33, 32, 6, 5, Double.POSITIVE_INFINITY },
			{ 4, 18, 17, 16, 29, 0, 26, 25, 35, 34, Double.POSITIVE_INFINITY },
			{ 5, 19, 18, 17, 30, 29, 0, 3, 36, 35, Double.POSITIVE_INFINITY },
			{ 2, 16, 15, 14, 27, 26, 24, 0, 33, 32, Double.POSITIVE_INFINITY },
			{ 21, 35, 34, 33, 46, 45, 43, 42, 0, 1, Double.POSITIVE_INFINITY },
			{ 20, 34, 33, 32, 45, 44, 42, 41, 51, 0, Double.POSITIVE_INFINITY },
			{ Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
					Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
					Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0 }, };

	int[][] resPFloyd = new int[][] { { -1, -1, -1, -1, 1, 1, 2, 2, 4, 4, -1 }, { 5, -1, 5, 5, -1, -1, 5, 5, 4, 4, -1 },
			{ 7, 7, -1, 7, 7, 7, -1, -1, 7, 7, -1 }, { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
			{ 5, 5, 5, 5, -1, -1, 5, 5, -1, -1, -1 }, { -1, 0, 0, 0, 1, -1, 2, 2, 4, 4, -1 },
			{ 7, 7, 7, 7, 7, 7, -1, -1, 7, 7, -1 }, { -1, 0, 0, 0, 1, 1, 2, -1, 4, 4, -1 },
			{ 9, 9, 9, 9, 9, 9, 9, 9, -1, -1, -1 }, { -1, 0, 0, 0, 1, 1, 2, 2, 4, -1, -1 },
			{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 } };

	// @Test
	public void createGraph1() {
		g = new Graph<Character>(10);

		assertEquals(0, g.addNode('A'));
		assertEquals(0, g.addNode('B'));
		assertEquals(0, g.addNode('C'));
		assertEquals(0, g.addNode('D'));
		assertEquals(0, g.addNode('E'));

		assertEquals(0, g.addEdge('A', 'B', 10));
		assertEquals(0, g.addEdge('A', 'D', 30));
		assertEquals(0, g.addEdge('A', 'E', 100));
		assertEquals(0, g.addEdge('B', 'C', 50));
		assertEquals(0, g.addEdge('C', 'E', 10));
		assertEquals(0, g.addEdge('D', 'C', 20));
		assertEquals(0, g.addEdge('D', 'E', 60));
	}

	// @Test
	// public void testDijkstra() {
	// double ds[][];
	//
	// double ds1[][]={
	// {0,10,50,30,60},
	// {Double.POSITIVE_INFINITY,0,50,Double.POSITIVE_INFINITY,60},
	// {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0,Double.POSITIVE_INFINITY,10},
	// {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,20,0,30},
	// {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0}
	// };
	//
	// double ds2[][]={
	// {0,10,50,30,60},
	// {1060,0,50,1090,60},
	// {1010,1020,0,1040,10},
	// {1030,1040,20,0,30},
	// {1000,1010,1050,1030,0}
	// };
	//
	// double ds3[][]={
	// {0,10,11,30,21},
	// {1011,0,1,1041,11},
	// {1010,1020,0,1040,10},
	// {1030,1040,20,0,30},
	// {1000,1010,1011,1030,0}
	// };
	//
	// double ds4[][]={
	// { 0 , 10 , 90 , 30 },
	// {Double.POSITIVE_INFINITY, 0 ,Double.POSITIVE_INFINITY ,
	// Double.POSITIVE_INFINITY },
	// { 1060 , 1070 , 60 , 0 },
	// { 1000 , 1010 , 0 , 1030 }
	// };
	//
	// double ds5[][]={
	// {0,10,11,30},
	// {Double.POSITIVE_INFINITY,0,1,Double.POSITIVE_INFINITY},
	// {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0,Double.POSITIVE_INFINITY},
	// {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,20,0}
	// };
	//
	// createGraph1();
	// ds=ds1;
	//
	// for (char i='A';i<='E';i++) {
	// assertArrayEquals("Dijkstra["+i+"]: ",ds[i-'A'],g.dijkstra(i),0);
	// }
	//
	// assertEquals(0,g.addEdge('E', 'A', 1000));
	// assertTrue(g.existEdge('E', 'A'));
	//
	// ds=ds2;
	//
	// for (char i='A';i<='E';i++) {
	// assertArrayEquals("Dijkstra["+i+"]: ",ds[i-'A'],g.dijkstra(i),0);
	// }
	//
	// assertEquals(0,g.addEdge('B','C',1));
	// assertTrue(g.existEdge('B', 'C'));
	//
	// ds=ds3;
	//
	// for (char i='A';i<='E';i++) {
	// assertArrayEquals("Dijkstra["+i+"]: ",ds[i-'A'],g.dijkstra(i),0);
	// }
	//
	//
	// assertEquals(0,g.removeNode('C'));
	// assertFalse(g.existNode('C'));
	//
	// ds=ds4;
	// for (char i='A';i<='E';i++) {
	// assertArrayEquals("Dijkstra["+i+"]:
	// ",i=='C'?null:i>'C'?ds[i-'A'-1]:ds[i-'A'],g.dijkstra(i),0);
	// }
	//
	// assertEquals(-1,g.addNode('A'));
	// assertEquals(-1,g.addNode('B'));
	// assertEquals(0,g.addNode('C'));
	// assertEquals(-1,g.addNode('D'));
	// assertEquals(-1,g.addNode('E'));
	//
	// assertEquals(0,g.addEdge('B', 'C', 1));
	// assertEquals(0,g.addEdge('D', 'C', 20));
	//
	// assertEquals(0,g.removeNode('E'));
	// assertFalse(g.existNode('E'));
	// assertEquals(0,g.addNode('E'));
	// assertEquals(0,g.addEdge('C', 'E', 10));
	// assertEquals(0,g.addEdge('D', 'E', 60));
	// assertEquals(0,g.addEdge('A', 'E', 100));
	// assertEquals(0,g.addEdge('E', 'A', 1000));
	//
	// ds=ds3;
	//
	// for (char i='A';i<='E';i++) {
	// assertArrayEquals("Dijkstra["+i+"]: ",ds[i-'A'],g.dijkstra(i),0);
	// }
	//
	// assertEquals(0,g.removeNode('E'));
	// assertFalse(g.existNode('E'));
	//
	// assertEquals(-1,g.addEdge('A', 'E', 1));
	// assertEquals(-1,g.addEdge('E', 'A', 1));
	// assertEquals(-1,g.addEdge('C', 'E', 10));
	// assertEquals(-1,g.addEdge('D', 'E', 60));
	//
	// ds=ds5;
	//
	// for (char i='A';i<='D';i++) {
	// assertArrayEquals("Dijkstra["+i+"]: ",ds[i-'A'],g.dijkstra(i),0);
	// }
	//
	// dijkstradEjemploClasePracticas1516();
	// dijkstraEjemploClasePracticas1415();
	// dijkstraEjemploClaseTeoria();
	// dijkstraOtro();
	//
	// System.err.println("Dijkstra pasado");
	// }
	//
	//
	// @Test
	// public void testFloyd() {
	// double ds[] = new double[4];
	//
	// double ds1[][]={
	// {0,10,50,30,60},
	// {Double.POSITIVE_INFINITY,0,50,Double.POSITIVE_INFINITY,60},
	// {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0,Double.POSITIVE_INFINITY,10},
	// {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,20,0,30},
	// {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0}
	// };
	//
	// double ds2[][]={
	// {0,10,50,30,60},
	// {1060,0,50,1090,60},
	// {1010,1020,0,1040,10},
	// {1030,1040,20,0,30},
	// {1000,1010,1050,1030,0}
	// };
	//
	// double ds3[][]={
	// {0,10,11,30,21},
	// {1011,0,1,1041,11},
	// {1010,1020,0,1040,10},
	// {1030,1040,20,0,30},
	// {1000,1010,1011,1030,0}
	// };
	//
	// double ds4[][]={
	// { 0 , 10 , 90 , 30 },
	// {Double.POSITIVE_INFINITY, 0 ,Double.POSITIVE_INFINITY ,
	// Double.POSITIVE_INFINITY },
	// { 1060 , 1070 , 60 , 0 },
	// { 1000 , 1010 , 0 , 1030 }
	// };
	//
	// double ds5[][]={
	// {0,10,11,30},
	// {Double.POSITIVE_INFINITY,0,1,Double.POSITIVE_INFINITY},
	// {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0,Double.POSITIVE_INFINITY},
	// {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,20,0}
	// };
	//
	// createGraph1();
	// assertArrayEquals(ds1,g.floyd());
	//
	// assertEquals(0,g.addEdge('E', 'A', 1000));
	// assertTrue(g.existEdge('E', 'A'));
	//
	// assertArrayEquals(ds2,g.floyd());
	//
	// assertEquals(0,g.addEdge('B','C',1));
	// assertTrue(g.existEdge('B', 'C'));
	//
	// assertArrayEquals(ds3,g.floyd());
	//
	// assertEquals(0,g.removeNode('C'));
	// assertFalse(g.existNode('C'));
	//
	// ds=ds4[2];
	// ds4[2]=ds4[3];
	// ds4[3]=ds;
	// assertArrayEquals(ds4,g.floyd());
	//
	// assertEquals(-1,g.addNode('A'));
	// assertEquals(-1,g.addNode('B'));
	// assertEquals(0,g.addNode('C'));
	// assertEquals(-1,g.addNode('D'));
	// assertEquals(-1,g.addNode('E'));
	//
	// assertEquals(0,g.addEdge('B', 'C', 1));
	// assertEquals(0,g.addEdge('D', 'C', 20));
	//
	// assertEquals(0,g.removeNode('E'));
	// assertFalse(g.existNode('E'));
	// assertEquals(0,g.addNode('E'));
	// assertEquals(0,g.addEdge('C', 'E', 10));
	// assertEquals(0,g.addEdge('D', 'E', 60));
	// assertEquals(0,g.addEdge('A', 'E', 100));
	// assertEquals(0,g.addEdge('E', 'A', 1000));
	//
	//
	// assertArrayEquals(ds3,g.floyd());
	//
	// assertEquals(0,g.removeNode('E'));
	// assertFalse(g.existNode('E'));
	//
	// assertEquals(-1,g.addEdge('A', 'E', 1));
	// assertEquals(-1,g.addEdge('E', 'A', 1));
	// assertEquals(-1,g.addEdge('C', 'E', 10));
	// assertEquals(-1,g.addEdge('D', 'E', 60));
	//
	//
	// assertArrayEquals(ds5,g.floyd());
	//
	// floydEjemploClasePracticas1415();
	// floydEjemploClasePracticas1516();
	// floydEjemploClaseTeoria();
	// floydOtro();
	//
	// System.err.println("Floyd pasado");
	//
	// }
	//
	// @Test
	// public void testPath() {
	// createGraph1();
	//
	// /*
	// A > B: A (10.0) B
	// A > C: A (30.0) D (20.0) C
	// A > D: A (30.0) D
	// A > E: A (30.0) D (20.0) C (10.0) E
	// B > C: B (50.0) C
	// B > E: B (50.0) C (10.0) E
	// C > E: C (10.0) E
	// D > C: D (20.0) C
	// D > E: D (20.0) C (10.0) E
	// */
	// ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	// PrintStream current = System.out;
	// System.setOut(new PrintStream(outContent));
	//
	//
	// assertEquals(10,g.path('A', 'B'),0);
	// assertEquals("A\t(10.0)\tB\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(50,g.path('A', 'C'),0);
	// assertEquals("A\t(30.0)\tD\t(20.0)\tC\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(30,g.path('A', 'D'),0);
	// assertEquals("A\t(30.0)\tD\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(60,g.path('A', 'E'),0);
	// assertEquals("A\t(30.0)\tD\t(20.0)\tC\t(10.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('B', 'A'),0);
	// assertEquals("B\t(Infinity)\tA\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(50,g.path('B', 'C'),0);
	// assertEquals("B\t(50.0)\tC\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('B', 'D'),0);
	// assertEquals("B\t(Infinity)\tD\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(60,g.path('B', 'E'),0);
	// assertEquals("B\t(50.0)\tC\t(10.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('C', 'A'),0);
	// assertEquals("C\t(Infinity)\tA\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('C', 'B'),0);
	// assertEquals("C\t(Infinity)\tB\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('C', 'D'),0);
	// assertEquals("C\t(Infinity)\tD\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(10,g.path('C', 'E'),0);
	// assertEquals("C\t(10.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('D', 'A'),0);
	// assertEquals("D\t(Infinity)\tA\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('D', 'B'),0);
	// assertEquals("D\t(Infinity)\tB\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(20,g.path('D', 'C'),0);
	// assertEquals("D\t(20.0)\tC\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(30,g.path('D', 'E'),0);
	// assertEquals("D\t(20.0)\tC\t(10.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('E', 'A'),0);
	// assertEquals("E\t(Infinity)\tA\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('E', 'B'),0);
	// assertEquals("E\t(Infinity)\tB\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('E', 'C'),0);
	// assertEquals("E\t(Infinity)\tC\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('E', 'D'),0);
	// assertEquals("E\t(Infinity)\tD\t", outContent.toString());
	// outContent.reset();
	//
	// /*
	// A > B: A (10.0) B
	// A > C: A (30.0) D (20.0) C
	// A > D: A (30.0) D
	// A > E: A (30.0) D (20.0) C (10.0) E
	// B > A: B (50.0) C (10.0) E (1000.0) A
	// B > C: B (50.0) C
	// B > D: B (50.0) C (10.0) E (1000.0) A (30.0) D
	// B > E: B (50.0) C (10.0) E
	// C > A: C (10.0) E (1000.0) A
	// C > B: C (10.0) E (1000.0) A (10.0) B
	// C > D: C (10.0) E (1000.0) A (30.0) D
	// C > E: C (10.0) E
	// D > A: D (20.0) C (10.0) E (1000.0) A
	// D > B: D (20.0) C (10.0) E (1000.0) A (10.0) B
	// D > C: D (20.0) C
	// D > E: D (20.0) C (10.0) E
	// E > A: E (1000.0) A
	// E > B: E (1000.0) A (10.0) B
	// E > C: E (1000.0) A (30.0) D (20.0) C
	// E > D: E (1000.0) A (30.0) D
	// */
	//
	// g.addEdge('E', 'A', 1000);
	//
	// assertEquals(10,g.path('A', 'B'),0);
	// assertEquals("A\t(10.0)\tB\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(50,g.path('A', 'C'),0);
	// assertEquals("A (30.0) D (20.0) C\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(30,g.path('A', 'D'),0);
	// assertEquals("A (30.0) D\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(60,g.path('A', 'E'),0);
	// assertEquals("A\t(30.0)\tD\t(20.0)\tC\t(10.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1060,g.path('B', 'A'),0);
	// assertEquals("B\t(50.0)\tC\t(10.0)\tE\t(1000.0)\tA\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(50,g.path('B', 'C'),0);
	// assertEquals("B\t(50.0)\tC\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1090,g.path('B', 'D'),0);
	// assertEquals("B\t(50.0)\tC\t(10.0)\tE\t(1000.0)\tA\t(30.0)\tD\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(60,g.path('B', 'E'),0);
	// assertEquals("B\t(50.0)\tC\t(10.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1010,g.path('C', 'A'),0);
	// assertEquals("C\t(10.0)\tE\t(1000.0)\tA\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1020,g.path('C', 'B'),0);
	// assertEquals("C\t(10.0)\tE\t(1000.0)\tA\t(10.0)\tB\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1040,g.path('C', 'D'),0);
	// assertEquals("C\t(10.0)\tE\t(1000.0)\tA\t(30.0)\tD\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(10,g.path('C', 'E'),0);
	// assertEquals("C\t(10.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1030,g.path('D', 'A'),0);
	// assertEquals("D\t(20.0)\tC\t(10.0)\tE\t(1000.0)\tA\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1040,g.path('D', 'B'),0);
	// assertEquals("D\t(20.0)\tC\t(10.0)\tE\t(1000.0)\tA\t(10.0)\tB\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(20,g.path('D', 'C'),0);
	// assertEquals("D\t(20.0)\tC\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(30,g.path('D', 'E'),0);
	// assertEquals("D\t(20.0)\tC\t(10.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1000,g.path('E', 'A'),0);
	// assertEquals("E\t(1000.0)\tA\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1010,g.path('E', 'B'),0);
	// assertEquals("E\t(1000.0)\tA\t(10.0)\tB\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1050,g.path('E', 'C'),0);
	// assertEquals("E\t(1000.0)\tA\t(30.0)\tD\t(20.0)\tC\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1030,g.path('E', 'D'),0);
	// assertEquals("E\t(1000.0)\tA\t(30.0)\tD\t", outContent.toString());
	// outContent.reset();
	//
	// g.addEdge('B','C',1);
	//
	// /*
	// A > B: A (10.0) B
	// A > C: A (10.0) B (1.0) C
	// A > D: A (30.0) D
	// A > E: A (10.0) B (1.0) C (10.0) E
	// B > A: B (1.0) C (10.0) E (1000.0) A
	// B > C: B (1.0) C
	// B > D: B (1.0) C (10.0) E (1000.0) A (30.0) D
	// B > E: B (1.0) C (10.0) E
	// C > A: C (10.0) E (1000.0) A
	// C > B: C (10.0) E (1000.0) A (10.0) B
	// C > D: C (10.0) E (1000.0) A (30.0) D
	// C > E: C (10.0) E
	// D > A: D (20.0) C (10.0) E (1000.0) A
	// D > B: D (20.0) C (10.0) E (1000.0) A (10.0) B
	// D > C: D (20.0) C
	// D > E: D (20.0) C (10.0) E
	// E > A: E (1000.0) A
	// E > B: E (1000.0) A (10.0) B
	// E > C: E (1000.0) A (10.0) B (1.0) C
	// E > D: E (1000.0) A (30.0) D
	// */
	//
	// assertEquals(10,g.path('A', 'B'),0);
	// assertEquals("A\t(10.0)\tB\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(11,g.path('A', 'C'),0);
	// assertEquals("A\t(10.0)\tB\t(1.0)\tC\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(30,g.path('A', 'D'),0);
	// assertEquals("A\t(30.0)\tD\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(21,g.path('A', 'E'),0);
	// assertEquals("A\t(10.0)\tB\t(1.0)\tC\t(10.0)\tE ", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1011,g.path('B', 'A'),0);
	// assertEquals("B\t(1.0)\tC\t(10.0)\tE\t(1000.0)\tA\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1,g.path('B', 'C'),0);
	// assertEquals("B\t(1.0)\tC\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1041,g.path('B', 'D'),0);
	// assertEquals("B\t(1.0)\tC\t(10.0)\tE\t(1000.0)\tA\t(30.0)\tD\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(11,g.path('B', 'E'),0);
	// assertEquals("B\t(1.0)\tC\t(10.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1010,g.path('C', 'A'),0);
	// assertEquals("C\t(10.0)\tE\t(1000.0)\tA\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1020,g.path('C', 'B'),0);
	// assertEquals("C\t(10.0)\tE\t(1000.0)\tA\t(10.0)\tB\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1040,g.path('C', 'D'),0);
	// assertEquals("C\t(10.0)\tE\t(1000.0)\tA\t(30.0)\tD\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(10,g.path('C', 'E'),0);
	// assertEquals("C\t(10.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1030,g.path('D', 'A'),0);
	// assertEquals("D\t(20.0)\tC\t(10.0)\tE\t(1000.0)\tA\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1040,g.path('D', 'B'),0);
	// assertEquals("D\t(20.0)\tC\t(10.0)\tE\t(1000.0)\tA\t(10.0)\tB\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(20,g.path('D', 'C'),0);
	// assertEquals("D\t(20.0)\tC\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(30,g.path('D', 'E'),0);
	// assertEquals("D\t(20.0)\tC\t(10.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1000,g.path('E', 'A'),0);
	// assertEquals("E\t(1000.0)\tA\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1010,g.path('E', 'B'),0);
	// assertEquals("E\t(1000.0)\tA\t(10.0)\tB\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1011,g.path('E', 'C'),0);
	// assertEquals("E\t(1000.0)\tA\t(10.0)\tB\t(1.0)\tC\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1030,g.path('E', 'D'),0);
	// assertEquals("E\t(1000.0)\tA\t(30.0)\tD\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(0,g.removeNode('C'));
	// assertFalse(g.existNode('C'));
	//
	// assertEquals(-1,g.addNode('A'));
	// assertEquals(-1,g.addNode('B'));
	// assertEquals(0,g.addNode('C'));
	// assertEquals(-1,g.addNode('D'));
	// assertEquals(-1,g.addNode('E'));
	//
	// assertEquals(0,g.addEdge('B', 'C', 1));
	// assertEquals(0,g.addEdge('C', 'E', 10));
	// assertEquals(0,g.addEdge('D', 'C', 20));
	//
	// /*
	// A > B: A (10.0) B
	// A > C: A (10.0) B (1.0) C
	// A > D: A (30.0) D
	// A > E: A (10.0) B (1.0) C (10.0) E
	// B > A: B (1.0) C (10.0) E (1000.0) A
	// B > C: B (1.0) C
	// B > D: B (1.0) C (10.0) E (1000.0) A (30.0) D
	// B > E: B (1.0) C (10.0) E
	// C > A: C (10.0) E (1000.0) A
	// C > B: C (10.0) E (1000.0) A (10.0) B
	// C > D: C (10.0) E (1000.0) A (30.0) D
	// C > E: C (10.0) E
	// D > A: D (20.0) C (10.0) E (1000.0) A
	// D > B: D (20.0) C (10.0) E (1000.0) A (10.0) B
	// D > C: D (20.0) C
	// D > E: D (20.0) C (10.0) E
	// E > A: E (1000.0) A
	// E > B: E (1000.0) A (10.0) B
	// E > C: E (1000.0) A (10.0) B (1.0) C
	// E > D: E (1000.0) A (30.0) D
	// */
	//
	// assertEquals(10,g.path('A', 'B'),0);
	// assertEquals("A\t(10.0)\tB\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(11,g.path('A', 'C'),0);
	// assertEquals("A\t(10.0)\tB\t(1.0)\tC\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(30,g.path('A', 'D'),0);
	// assertEquals("A\t(30.0)\tD\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(21,g.path('A', 'E'),0);
	// assertEquals("A\t(10.0)\tB\t(1.0)\tC\t(10.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1011,g.path('B', 'A'),0);
	// assertEquals("B\t(1.0)\tC\t(10.0)\tE\t(1000.0)\tA\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1,g.path('B', 'C'),0);
	// assertEquals("B\t(1.0)\tC\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1041,g.path('B', 'D'),0);
	// assertEquals("B\t(1.0)\tC\t(10.0)\tE\t(1000.0)\tA\t(30.0)\tD\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(11,g.path('B', 'E'),0);
	// assertEquals("B\t(1.0)\tC\t(10.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1010,g.path('C', 'A'),0);
	// assertEquals("C\t(10.0)\tE\t(1000.0)\tA\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1020,g.path('C', 'B'),0);
	// assertEquals("C\t(10.0)\tE\t(1000.0)\tA\t(10.0)\tB\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1040,g.path('C', 'D'),0);
	// assertEquals("C\t(10.0)\tE\t(1000.0)\tA\t(30.0)\tD\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(10,g.path('C', 'E'),0);
	// assertEquals("C\t(10.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1030,g.path('D', 'A'),0);
	// assertEquals("D\t(20.0)\tC\t(10.0)\tE\t(1000.0)\tA\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1040,g.path('D', 'B'),0);
	// assertEquals("D\t(20.0)\tC\t(10.0)\tE\t(1000.0)\tA\t(10.0)\tB\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(20,g.path('D', 'C'),0);
	// assertEquals("D\t(20.0)\tC\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(30,g.path('D', 'E'),0);
	// assertEquals("D\t(20.0)\tC\t(10.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1000,g.path('E', 'A'),0);
	// assertEquals("E\t(1000.0)\tA\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1010,g.path('E', 'B'),0);
	// assertEquals("E\t(1000.0)\tA\t(10.0)\tB\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1011,g.path('E', 'C'),0);
	// assertEquals("E\t(1000.0)\tA\t(10.0)\tB\t(1.0)\tC\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1030,g.path('E', 'D'),0);
	// assertEquals("E\t(1000.0)\tA\t(30.0)\tD\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(0,g.removeNode('C'));
	// assertFalse(g.existNode('C'));
	//
	// assertEquals(-1,g.addEdge('B', 'C', 1));
	// assertEquals(-1,g.addEdge('C', 'E', 10));
	// assertEquals(-1,g.addEdge('D', 'C', 20));
	//
	// /*
	// A > B: A (10.0) B
	// A > D: A (30.0) D
	// A > E: A (30.0) D (60.0) E
	// D > A: D (60.0) E (1000.0) A
	// D > B: D (60.0) E (1000.0) A (10.0) B
	// D > E: D (60.0) E
	// E > A: E (1000.0) A
	// E > B: E (1000.0) A (10.0) B
	// E > D: E (1000.0) A (30.0) D
	// */
	//
	// assertEquals(10,g.path('A', 'B'),0);
	// assertEquals("A\t(10.0)\tB\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(-1,g.path('A', 'C'),0);
	// assertEquals("No Procede", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(30,g.path('A', 'D'),0);
	// assertEquals("A\t(30.0)\tD\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(90,g.path('A', 'E'),0);
	// assertEquals("A\t(30.0)\tD\t(60.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('B', 'A'),0);
	// assertEquals("B\t(Infinity)\tA\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(-1,g.path('B', 'C'),0);
	// assertEquals("No Procede", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('B', 'D'),0);
	// assertEquals("B\t(Infinity)\tD\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('B', 'E'),0);
	// assertEquals("B\t(Infinity)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(-1,g.path('C', 'A'),0);
	// assertEquals("No Procede", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(-1,g.path('C', 'B'),0);
	// assertEquals("No Procede", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(-1,g.path('C', 'D'),0);
	// assertEquals("No Procede", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(-1,g.path('C', 'E'),0);
	// assertEquals("No Procede", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1060,g.path('D', 'A'),0);
	// assertEquals("D\t(60.0)\tE\t(1000.0)\tA\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1070,g.path('D', 'B'),0);
	// assertEquals("D\t(60.0)\tE\t(1000.0)\tA\t(10.0)\tB\t",
	// outContent.toString());
	// outContent.reset();
	//
	// assertEquals(-1,g.path('D', 'C'),0);
	// assertEquals("No Procede", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(60,g.path('D', 'E'),0);
	// assertEquals("D\t(60.0)\tE\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1000,g.path('E', 'A'),0);
	// assertEquals("E\t(1000.0)\tA\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1010,g.path('E', 'B'),0);
	// assertEquals("E\t(1000.0)\tA\t(10.0)\tB\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(-1,g.path('E', 'C'),0);
	// assertEquals("No Procede", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(1030,g.path('E', 'D'),0);
	// assertEquals("E\t(1000.0)\tA\t(30.0)\tD\t", outContent.toString());
	// outContent.reset();
	//
	// pathEjemploClasePracticas1516();
	// assertEquals("N0\t"+
	// "N0\t(3.0)\tN4\t(2.0)\tN1\t"+
	// "N0\t(3.0)\tN4\t(2.0)\tN1\t(4.0)\tN3\t(1.0)\tN2\t"+
	// "N0\t(3.0)\tN4\t(2.0)\tN1\t(4.0)\tN3\t"+
	// "N0\t(3.0)\tN4\t"+
	// "N1\t(4.0)\tN3\t(7.0)\tN0\t"+
	// "N1\t"+
	// "N1\t(4.0)\tN3\t(1.0)\tN2\t"+
	// "N1\t(4.0)\tN3\t"+
	// "N1\t(4.0)\tN3\t(7.0)\tN0\t(3.0)\tN4\t"+
	// "N2\t(Infinity)\tN0\t"+
	// "N2\t(Infinity)\tN1\t"+
	// "N2\t"+
	// "N2\t(Infinity)\tN3\t"+
	// "N2\t(Infinity)\tN4\t"+
	// "N3\t(7.0)\tN0\t"+
	// "N3\t(7.0)\tN0\t(3.0)\tN4\t(2.0)\tN1\t"+
	// "N3\t(1.0)\tN2\t"+
	// "N3\t"+
	// "N3\t(7.0)\tN0\t(3.0)\tN4\t"+
	// "N4\t(2.0)\tN1\t(4.0)\tN3\t(7.0)\tN0\t"+
	// "N4\t(2.0)\tN1\t"+
	// "N4\t(2.0)\tN1\t(4.0)\tN3\t(1.0)\tN2\t"+
	// "N4\t(2.0)\tN1\t(4.0)\tN3\t"+
	// "N4\t"
	// ,outContent.toString());
	//
	// System.setOut(current);
	//
	// System.err.println("Path pasado");
	//
	//
	// }
	//
	// @Test
	// public void testPathCasos() {
	// createGraph1();
	//
	// ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	// PrintStream current = System.out;
	// System.setOut(new PrintStream(outContent));
	//
	//
	// assertEquals(-1,g.path('B', 'F'),0);
	// assertEquals("No Procede", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(-1,g.path('F', 'C'),0);
	// assertEquals("No Procede", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(-1,g.path('F', 'G'),0);
	// assertEquals("No Procede", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('E', 'A'),0);
	// assertEquals("E\t(Infinity)\tA\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('E', 'B'),0);
	// assertEquals("E\t(Infinity)\tB\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('E', 'C'),0);
	// assertEquals("E\t(Infinity)\tC\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(Double.POSITIVE_INFINITY,g.path('E', 'D'),0);
	// assertEquals("E\t(Infinity)\tD\t", outContent.toString());
	// outContent.reset();
	//
	// assertEquals(0,g.path('E', 'E'),0);
	// assertEquals("E\t", outContent.toString());
	// outContent.reset();
	//
	// System.setOut(current);
	//
	// System.err.println("Path Casos especiales pasado");
	//
	// }
	//
	// @Before
	// public void createGraph2(){
	//
	//
	// gInteger = new Graph<Integer>(11);
	// for (int i=1;i<=11;i++)
	// assertEquals("addNode("+i+")",0, gInteger.addNode(i));
	// int arista=14;
	// for (int i=2;i<=4;i++)
	// assertEquals("addEdge(1,"+i+")",0,gInteger.addEdge(1, i, arista--));
	//
	// for (int i=5; i<=6;i++)
	// assertEquals("addEdge(2,"+i+")",0,gInteger.addEdge(2, i, arista--));
	//
	// for (int i=7; i<=8;i++)
	// assertEquals("addEdge(3,"+i+")",0,gInteger.addEdge(3, i, arista--));
	//
	// assertEquals(0,gInteger.addEdge(5, 6, arista--));
	// for (int i=9; i<=10;i++)
	// assertEquals("addEdge(5,"+i+")",0,gInteger.addEdge(5, i, arista--));
	//
	// assertEquals(0,gInteger.addEdge(6, 1, arista--));
	// assertEquals(0,gInteger.addEdge(7, 8, arista--));
	// assertEquals(0,gInteger.addEdge(8, 1, arista--));
	// assertEquals(0,gInteger.addEdge(9, 10, arista--));
	// assertEquals(0,gInteger.addEdge(10, 1, 20));
	//
	// gStrings=new Graph<String>(100);
	// assertEquals(0, gStrings.addNode("V1"));
	// assertEquals(0, gStrings.addNode("V2"));
	// assertEquals(0, gStrings.addNode("V3"));
	// assertEquals(0, gStrings.addNode("V4"));
	// assertEquals(0, gStrings.addNode("V5"));
	// assertEquals(0, gStrings.addNode("V6"));
	// assertEquals(0, gStrings.addEdge("V1", "V2", 3));
	// assertEquals(0, gStrings.addEdge("V1", "V3", 4));
	// assertEquals(0, gStrings.addEdge("V1", "V5", 8), 0);
	// assertEquals(0, gStrings.addEdge("V2", "V5", 5), 0);
	// assertEquals(0, gStrings.addEdge("V3", "V5", 3), 0);
	// assertEquals(0, gStrings.addEdge("V5", "V4", 7), 0);
	// assertEquals(0, gStrings.addEdge("V5", "V6", 3), 0);
	// assertEquals(0, gStrings.addEdge("V6", "V4", 2), 0);
	//
	// gp=new Graph<Integer>(10);
	// assertEquals(0,gp.addNode(0));
	// assertEquals(0,gp.addNode(1));
	// assertEquals(0,gp.addNode(2));
	// assertEquals(0,gp.addNode(3));
	// assertEquals(0,gp.addNode(4));
	// assertEquals(0, gp.addEdge(0, 1, 21));
	// assertEquals(0, gp.addEdge(0, 3, 3));
	// assertEquals(0, gp.addEdge(1, 2, 12));
	// assertEquals(0, gp.addEdge(3, 2, 32));
	// assertEquals(0, gp.addEdge(4, 0, 10));
	// assertEquals(0, gp.addEdge(4, 1, 41));
	// assertEquals(0, gp.addEdge(4, 3, 43));
	//
	// gs=new Graph<String>(5);
	// assertEquals(0, gs.addNode("N0"));
	// assertEquals(0, gs.addNode("N1"));
	// assertEquals(0, gs.addNode("N2"));
	// assertEquals(0, gs.addNode("N3"));
	// assertEquals(0, gs.addNode("N4"));
	// assertEquals(0, gs.addEdge("N0", "N1", 12));
	// assertEquals(0, gs.addEdge("N0", "N2", 18));
	// assertEquals(0, gs.addEdge("N0", "N4", 3));
	// assertEquals(0, gs.addEdge("N1", "N3", 4));
	// assertEquals(0, gs.addEdge("N3", "N0", 7));
	// assertEquals(0, gs.addEdge("N3", "N2", 1));
	// assertEquals(0, gs.addEdge("N4", "N1", 2));
	//
	// }
	//
	//// @Test
	// public void dijkstraEjemploClasePracticas1415() {
	//// System.out.println(gp.toString());
	// assertArrayEquals(new double[]{10.0, 31.0, 43.0, 13.0, 0.0}, gp.dijkstra(4),
	// 0.0001);
	// assertArrayEquals(new double[]{Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY, 32.0, 0.0, Double.POSITIVE_INFINITY},
	// gp.dijkstra(3), 0.0001);
	// assertArrayEquals(new double[]{Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY, 0.0, Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY}, gp.dijkstra(2), 0.0001);
	// assertArrayEquals(new double[]{Double.POSITIVE_INFINITY, 0.0, 12.0,
	// Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY}, gp.dijkstra(1), 0.0001);
	// assertArrayEquals(new double[]{0.0, 21.0, 33.0, 3.0,
	// Double.POSITIVE_INFINITY}, gp.dijkstra(0), 0.0001);
	// }
	//
	//// @Test
	// public void dijkstraEjemploClaseTeoria() {
	//// System.out.println(gStrings.toString());
	// assertArrayEquals(new double[]{0.0, 3.0, 4.0, 12.0, 7.0, 10.0},
	// gStrings.dijkstra("V1"), 0.0001);
	// assertArrayEquals(new double[]{Double.POSITIVE_INFINITY, 0.0,
	// Double.POSITIVE_INFINITY, 10.0, 5.0, 8.0}, gStrings.dijkstra("V2"), 0.0001);
	// assertArrayEquals(new double[]{Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY, 0.0, 8.0, 3.0, 6.0}, gStrings.dijkstra("V3"),
	// 0.0001);
	// assertArrayEquals(new double[]{Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0,
	// Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY}, gStrings.dijkstra("V4"),
	// 0.0001);
	// assertArrayEquals(new double[]{Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 5.0, 0.0, 3.0},
	// gStrings.dijkstra("V5"), 0.0001);
	// assertArrayEquals(new double[]{Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 2.0,
	// Double.POSITIVE_INFINITY, 0.0}, gStrings.dijkstra("V6"), 0.0001);
	//
	// }
	//// @Test
	// public void dijkstraOtro () {
	//
	// double[][] resAFloyd=new double[][]{
	// {0, 14, 13, 12, 25, 24, 22, 21, 31, 30, Double.POSITIVE_INFINITY},
	// {14, 0, 27, 26, 11, 10, 36, 35, 17, 16, Double.POSITIVE_INFINITY},
	// {10, 24, 0, 22, 35, 34, 9, 8, 41, 40, Double.POSITIVE_INFINITY},
	// {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY, 0, Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY},
	// {11, 25, 24, 23, 0, 7, 33, 32, 6, 5, Double.POSITIVE_INFINITY},
	// {4, 18, 17, 16, 29, 0, 26, 25, 35, 34, Double.POSITIVE_INFINITY},
	// {5, 19, 18, 17, 30, 29, 0, 3, 36, 35, Double.POSITIVE_INFINITY},
	// {2, 16, 15, 14, 27, 26, 24, 0, 33, 32, Double.POSITIVE_INFINITY},
	// {21, 35, 34, 33, 46, 45, 43, 42, 0, 1, Double.POSITIVE_INFINITY},
	// {20, 34, 33, 32, 45, 44, 42, 41, 51, 0, Double.POSITIVE_INFINITY},
	// {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0},
	// };
	//
	// for (int i=1;i<=11;i++)
	// assertArrayEquals(resAFloyd[i-1], gInteger.dijkstra(i), 0.0001);
	//
	// }
	//
	//// @Test
	// public void floydEjemploClasePracticas1415() {
	// assertArrayEquals(new double[][]{
	// {0.0, 21.0, 33.0, 3.0, Double.POSITIVE_INFINITY},
	// {Double.POSITIVE_INFINITY, 0.0, 12.0, Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY},
	// {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0,
	// Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
	// {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 32.0, 0.0,
	// Double.POSITIVE_INFINITY},
	// {10.0, 31.0, 43.0, 13.0, 0.0}
	// }, gp.floyd());
	// assertArrayEquals(new int[][]{
	// {-1,-1,1,-1,-1},
	// {-1,-1,-1,-1,-1},
	// {-1,-1,-1,-1,-1},
	// {-1,-1,-1,-1,-1},
	// {-1,0,1,0,-1}
	// }, gp.getpFloyd());
	// }
	//
	//
	// public void dijkstradEjemploClasePracticas1516() {
	//// System.out.println(gChars.toString());
	// assertArrayEquals(new double[]{0.0, 5.0, 10.0, 9.0, 3 }
	// ,gs.dijkstra("N0"),0.001);
	// assertArrayEquals(new double[]{11.0, 0.0, 5.0, 4.0, 14.0}
	// ,gs.dijkstra("N1"),0.001);
	// assertArrayEquals(new double[]{Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY, 0.0, Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY}
	// ,gs.dijkstra("N2"),0.001);
	// assertArrayEquals(new double[]{7.0, 12.0, 1.0, 0.0, 10.0}
	// ,gs.dijkstra("N3"),0.001);
	// }
	//
	//// @Test
	// public void floydEjemploClasePracticas1516() {
	//// System.out.println(gChars.toString());
	// assertArrayEquals(new double[][]{
	// {0.0, 5.0, 10.0, 9.0, 3},
	// {11.0, 0.0, 5.0, 4.0, 14.0},
	// {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0,
	// Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
	// {7.0, 12.0, 1.0, 0.0, 10.0},
	// {13.0, 2.0, 7.0, 6.0, 0.0}
	// }, gs.floyd());
	// assertArrayEquals(new int[][]{
	// {-1,4,4,4,-1},
	// {3,-1,3,-1,3},
	// {-1,-1,-1,-1,-1},
	// {-1,4,-1,-1,0},
	// {3,-1,3,1,-1}
	// }, gs.getpFloyd());
	//
	// }
	//
	// public void pathEjemploClasePracticas1516() {
	//
	// assertEquals(0, gs.path("N0", "N0"),0.001);
	// assertEquals(5, gs.path("N0", "N1"),0.001);
	// assertEquals(10, gs.path("N0", "N2"),0.001);
	// assertEquals(9, gs.path("N0", "N3"),0.001);
	// assertEquals(3, gs.path("N0", "N4"),0.001);
	// assertEquals(11, gs.path("N1", "N0"),0.001);
	// assertEquals(0, gs.path("N1", "N1"),0.001);
	// assertEquals(5, gs.path("N1", "N2"),0.001);
	// assertEquals(4, gs.path("N1", "N3"),0.001);
	// assertEquals(14, gs.path("N1", "N4"),0.001);
	// assertEquals(Double.POSITIVE_INFINITY, gs.path("N2", "N0"),0.001);
	// assertEquals(Double.POSITIVE_INFINITY, gs.path("N2", "N1"),0.001);
	// assertEquals(0, gs.path("N2", "N2"),0.001);
	// assertEquals(Double.POSITIVE_INFINITY, gs.path("N2", "N3"),0.001);
	// assertEquals(Double.POSITIVE_INFINITY, gs.path("N2", "N4"),0.001);
	// assertEquals(7, gs.path("N3", "N0"),0.001);
	// assertEquals(12, gs.path("N3", "N1"),0.001);
	// assertEquals(1, gs.path("N3", "N2"),0.001);
	// assertEquals(0, gs.path("N3", "N3"),0.001);
	// assertEquals(10, gs.path("N3", "N4"),0.001);
	// assertEquals(13, gs.path("N4", "N0"),0.001);
	// assertEquals(2, gs.path("N4", "N1"),0.001);
	// assertEquals(7, gs.path("N4", "N2"),0.001);
	// assertEquals(6, gs.path("N4", "N3"),0.001);
	// assertEquals(0, gs.path("N4", "N4"),0.001);
	// }
	//
	//// @Test
	// public void floydEjemploClaseTeoria() {
	//
	// assertArrayEquals(new double[][]{
	// {0, 3, 4, 12, 7, 10},
	// {Double.POSITIVE_INFINITY, 0, Double.POSITIVE_INFINITY, 10, 5, 8},
	// {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0, 8, 3, 6},
	// {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY, 0, Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY},
	// {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY, 5, 0, 3},
	// {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
	// Double.POSITIVE_INFINITY, 2, Double.POSITIVE_INFINITY, 0}
	// }, gStrings.floyd());
	//// System.out.println(gStrings.toString());
	// assertArrayEquals(new int[][]{
	// {-1, -1, -1, 5, 2, 4},
	// {-1, -1, -1, 5, -1, 4},
	// {-1, -1, -1, 5, -1, 4},
	// {-1, -1, -1, -1, -1, -1},
	// {-1, -1, -1, 5, -1, -1},
	// {-1, -1, -1, -1, -1, -1}
	// }, gStrings.getpFloyd());
	//
	//
	//
	// }
	//
	//
	//// @Test
	// public void floydOtro () {
	//
	// assertArrayEquals(resAFloyd, gInteger.floyd());
	//// System.out.print(gInteger);
	// assertArrayEquals(resPFloyd,gInteger.getpFloyd());
	// }
	//
	//
	// @Test
	// public void testRecorridoProfundidad () {
	// ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	// PrintStream current = System.out;
	// System.setOut(new PrintStream(outContent));
	//
	// recorridoProfundidadOtro();
	//
	// assertEquals("Empezando en nodo 1: 1 2 5 6 9 10 3 7 8 4 "+
	// "Empezando en nodo 2: 2 5 6 1 3 7 8 4 9 10 "+
	// "Empezando en nodo 3: 3 7 8 1 2 5 6 9 10 4 "+
	// "Empezando en nodo 4: 4 "+
	// "Empezando en nodo 5: 5 6 1 2 3 7 8 4 9 10 "+
	// "Empezando en nodo 6: 6 1 2 5 9 10 3 7 8 4 "+
	// "Empezando en nodo 7: 7 8 1 2 5 6 9 10 3 4 "+
	// "Empezando en nodo 8: 8 1 2 5 6 9 10 3 7 4 "+
	// "Empezando en nodo 9: 9 10 1 2 5 6 3 7 8 4 "+
	// "Empezando en nodo 10: 10 1 2 5 6 9 3 7 8 4 "+
	// "Empezando en nodo 11: 11 "+
	// "Empezando en nodo 12: "+
	// "Empezando en nodo 1:[1-2-5-6-9-10-3-7-8-4-11]: 1 2 5 6 9 10 3 7 8 4 11 "+
	// "Empezando en nodo 2:[2-5-6-1-3-7-8-4-11-9-10]: 2 5 6 1 3 7 8 4 11 9 10 "+
	// "Empezando en nodo 3:[3-7-8-1-2-5-6-9-10-4-11]: 3 7 8 1 2 5 6 9 10 4 11 "+
	// "Empezando en nodo 4:[4-11]: 4 11 "+
	// "Empezando en nodo 5:[5-6-1-2-3-7-8-4-11-9-10]: 5 6 1 2 3 7 8 4 11 9 10 "+
	// "Empezando en nodo 6:[6-1-2-5-9-10-3-7-8-4-11]: 6 1 2 5 9 10 3 7 8 4 11 "+
	// "Empezando en nodo 7:[7-8-1-2-5-6-9-10-3-4-11]: 7 8 1 2 5 6 9 10 3 4 11 "+
	// "Empezando en nodo 8:[8-1-2-5-6-9-10-3-7-4-11]: 8 1 2 5 6 9 10 3 7 4 11 "+
	// "Empezando en nodo 9:[9-10-1-2-5-6-3-7-8-4-11]: 9 10 1 2 5 6 3 7 8 4 11 "+
	// "Empezando en nodo 10:[10-1-2-5-6-9-3-7-8-4-11]: 10 1 2 5 6 9 3 7 8 4 11 "
	// , outContent.toString());
	// outContent.reset();
	//
	// recorridoProfundidadPracticas();
	//
	// assertEquals("Empezando en nodo 0: 0 1 2 3 "+
	// "Empezando en nodo 1: 1 2 "+
	// "Empezando en nodo 2: 2 "+
	// "Empezando en nodo 3: 3 2 "+
	// "Empezando en nodo 4: 4 0 1 2 3 "+
	// "Empezando en nodo 5: "+
	// "Empezando en nodo 6: "+
	// "Empezando en nodo 7: "+
	// "Empezando en nodo 8: "+
	// "Empezando en nodo 9: "+
	// "Empezando en nodo 10: "+
	// "Empezando en nodo 0:[0-1-2-4-3]: 0 1 2 4 3 "+
	// "Empezando en nodo 1:[1-2-4-0-3]: 1 2 4 0 3 "+
	// "Empezando en nodo 2:[2-4-0-1-3]: 2 4 0 1 3 "+
	// "Empezando en nodo 3:[3-2-4-0-1]: 3 2 4 0 1 "+
	// "Empezando en nodo 4:[4-0-1-2-3]: 4 0 1 2 3 "
	// , outContent.toString());
	// outContent.reset();
	//
	// recorridoProfundidadTeoria();
	// assertEquals("Empezando en nodo 1: V1 V2 V5 V4 V6 V3 "+
	// "Empezando en nodo 2: V2 V5 V4 V6 "+
	// "Empezando en nodo 3: V3 V5 V4 V6 "+
	// "Empezando en nodo 4: V4 "+
	// "Empezando en nodo 5: V5 V4 V6 "+
	// "Empezando en nodo 6: V6 V4 "+
	// "Empezando en nodo 7: "+
	// "Empezando en nodo 8: "+
	// "Empezando en nodo 9: "+
	// "Empezando en nodo 10: "+
	// "Empezando en nodo 11: "+
	// "Empezando en nodo 12: "+
	// "Empezando en nodo 1:[V1-V2-V5-V4-V6-V3]: V1 V2 V5 V4 V6 V3 "+
	// "Empezando en nodo 2:[V2-V5-V4-V6-V1-V3]: V2 V5 V4 V6 V1 V3 "+
	// "Empezando en nodo 3:[V3-V5-V4-V6-V1-V2]: V3 V5 V4 V6 V1 V2 "+
	// "Empezando en nodo 4:[V4]: V4 "+
	// "Empezando en nodo 5:[V5-V4-V6-V1-V2-V3]: V5 V4 V6 V1 V2 V3 "+
	// "Empezando en nodo 6:[V6-V1-V2-V5-V4-V3]: V6 V1 V2 V5 V4 V3 "
	// , outContent.toString());
	// outContent.reset();
	//
	// System.setOut(current);
	//
	// System.err.println("Recorrido en profundidad pasado");
	//
	// }
	//
	//// @Test
	// public void recorridoProfundidadPracticas () {
	// String[] resRecProf=new String[]{
	// "0-1-2-4-3",
	// "1-2-4-0-3",
	// "2-4-0-1-3",
	// "3-2-4-0-1",
	// "4-0-1-2-3"
	// };
	//
	// for (int i=0;i<11;i++){
	// System.out.print("Empezando en nodo "+i+": ");
	// assertEquals("recorridoProfundidad("+i+") ",i==4?0:-1,
	// gp.recorridoProfundidad(i));
	//// System.out.println();
	// }
	//
	// assertEquals(0,gp.addEdge(2, 4, 77));
	//
	// for (int i=0;i<5;i++) {
	// System.out.print("Empezando en nodo "+i+":["+resRecProf[i]+"]:\t");
	// assertEquals("recorridoProfundidad ("+i+") ",0, gp.recorridoProfundidad(i));
	//// System.out.println();
	// }
	//
	// }
	//
	//// @Test
	// public void recorridoProfundidadTeoria () {
	// String[] resRecProf=new String[]{
	// "V1-V2-V5-V4-V6-V3",
	// "V2-V5-V4-V6-V1-V3",
	// "V3-V5-V4-V6-V1-V2",
	// "V4",
	// "V5-V4-V6-V1-V2-V3",
	// "V6-V1-V2-V5-V4-V3"
	// };
	//
	// for (int i=1;i<=12;i++){
	// System.out.print("Empezando en nodo "+i+": ");
	// assertEquals("recorridoProfundidad (V"+i+") ",i==1?0:-1,
	// gStrings.recorridoProfundidad("V"+i));
	//// System.out.println();
	// }
	//
	// assertEquals(0,gStrings.addEdge("V6", "V1", 79));
	//
	// for (int i=1;i<7;i++) {
	// System.out.print("Empezando en nodo "+i+":["+resRecProf[i-1]+"]:\t");
	// assertEquals("recorridoProfundidad (V"+i+") ",i==4?-1:0,
	// gStrings.recorridoProfundidad("V"+i));
	//// System.out.println();
	// }
	//
	// }
	//
	//// @Test
	// public void recorridoProfundidadOtro () {
	// String[] resRecProf=new String[]{
	// "1-2-5-6-9-10-3-7-8-4-11",
	// "2-5-6-1-3-7-8-4-11-9-10",
	// "3-7-8-1-2-5-6-9-10-4-11",
	// "4-11",
	// "5-6-1-2-3-7-8-4-11-9-10",
	// "6-1-2-5-9-10-3-7-8-4-11",
	// "7-8-1-2-5-6-9-10-3-4-11",
	// "8-1-2-5-6-9-10-3-7-4-11",
	// "9-10-1-2-5-6-3-7-8-4-11",
	// "10-1-2-5-6-9-3-7-8-4-11",
	// "11"
	// };
	//
	// for (int i=1;i<=12;i++){
	// System.out.print("Empezando en nodo "+i+": ");
	// assertEquals(-1, gInteger.recorridoProfundidad(i));
	//// System.out.println();
	// }
	//
	// assertEquals(0,gInteger.addEdge(4, 11, 411));
	//
	// for (int i=1;i<11;i++) {
	// System.out.print("Empezando en nodo "+i+":["+resRecProf[i-1]+"]:\t");
	// assertEquals("recorridoProfundidad ("+i+") ",i==4?-1:0,
	// gInteger.recorridoProfundidad(i));
	//// System.out.println();
	// }
	//
	// }
}