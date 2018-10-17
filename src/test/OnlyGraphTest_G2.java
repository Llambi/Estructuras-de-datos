package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import p2Grafos.Graph;

/**
 * @author UO250708
 *
 */
public class OnlyGraphTest_G2 {
	Graph<Character> g;
	Graph<String> gs;
	Graph<Integer> gInteger;
	Graph<String> gStrings;
	Graph<Integer> gp;

	@Test
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

	@Before
	public void createGraph2() {

		gInteger = new Graph<Integer>(11);
		for (int i = 1; i <= 11; i++)
			assertEquals("addNode(" + i + ")", 0, gInteger.addNode(i));
		int arista = 14;
		for (int i = 2; i <= 4; i++)
			assertEquals("addEdge(1," + i + ")", 0, gInteger.addEdge(1, i, arista--));

		for (int i = 5; i <= 6; i++)
			assertEquals("addEdge(2," + i + ")", 0, gInteger.addEdge(2, i, arista--));

		for (int i = 7; i <= 8; i++)
			assertEquals("addEdge(3," + i + ")", 0, gInteger.addEdge(3, i, arista--));

		assertEquals(0, gInteger.addEdge(5, 6, arista--));
		for (int i = 9; i <= 10; i++)
			assertEquals("addEdge(5," + i + ")", 0, gInteger.addEdge(5, i, arista--));

		assertEquals(0, gInteger.addEdge(6, 1, arista--));
		assertEquals(0, gInteger.addEdge(7, 8, arista--));
		assertEquals(0, gInteger.addEdge(8, 1, arista--));
		assertEquals(0, gInteger.addEdge(9, 10, arista--));
		assertEquals(0, gInteger.addEdge(10, 1, 20));

		gStrings = new Graph<String>(100);
		assertEquals(0, gStrings.addNode("V1"));
		assertEquals(0, gStrings.addNode("V2"));
		assertEquals(0, gStrings.addNode("V3"));
		assertEquals(0, gStrings.addNode("V4"));
		assertEquals(0, gStrings.addNode("V5"));
		assertEquals(0, gStrings.addNode("V6"));
		assertEquals(0, gStrings.addEdge("V1", "V2", 3));
		assertEquals(0, gStrings.addEdge("V1", "V3", 4));
		assertEquals(0, gStrings.addEdge("V1", "V5", 8), 0);
		assertEquals(0, gStrings.addEdge("V2", "V5", 5), 0);
		assertEquals(0, gStrings.addEdge("V3", "V5", 3), 0);
		assertEquals(0, gStrings.addEdge("V5", "V4", 7), 0);
		assertEquals(0, gStrings.addEdge("V5", "V6", 3), 0);
		assertEquals(0, gStrings.addEdge("V6", "V4", 2), 0);

		gp = new Graph<Integer>(10);
		assertEquals(0, gp.addNode(0));
		assertEquals(0, gp.addNode(1));
		assertEquals(0, gp.addNode(2));
		assertEquals(0, gp.addNode(3));
		assertEquals(0, gp.addNode(4));
		assertEquals(0, gp.addEdge(0, 1, 21));
		assertEquals(0, gp.addEdge(0, 3, 3));
		assertEquals(0, gp.addEdge(1, 2, 12));
		assertEquals(0, gp.addEdge(3, 2, 32));
		assertEquals(0, gp.addEdge(4, 0, 10));
		assertEquals(0, gp.addEdge(4, 1, 41));
		assertEquals(0, gp.addEdge(4, 3, 43));

		gs = new Graph<String>(5);
		assertEquals(0, gs.addNode("N0"));
		assertEquals(0, gs.addNode("N1"));
		assertEquals(0, gs.addNode("N2"));
		assertEquals(0, gs.addNode("N3"));
		assertEquals(0, gs.addNode("N4"));
		assertEquals(0, gs.addEdge("N0", "N1", 12));
		assertEquals(0, gs.addEdge("N0", "N2", 18));
		assertEquals(0, gs.addEdge("N0", "N4", 3));
		assertEquals(0, gs.addEdge("N1", "N3", 4));
		assertEquals(0, gs.addEdge("N3", "N0", 7));
		assertEquals(0, gs.addEdge("N3", "N2", 1));
		assertEquals(0, gs.addEdge("N4", "N1", 2));

	}

	// @Test
	// public void testExamenG2 () {
	// createGraph1();
	//
	// assertEquals((Character)'A',g.getMoreEccentricNode());
	// assertEquals(0,g.addEdge('E','A',13.0));
	// assertEquals(0,g.removeNode('D'));
	// assertEquals((Character)'A',g.getMoreEccentricNode());
	// assertEquals(0,g.removeNode('A'));
	// assertEquals((Character)'B',g.getMoreEccentricNode());
	// assertEquals(0,g.removeNode('B'));
	// assertEquals((Character)'C',g.getMoreEccentricNode());
	// assertEquals(0,g.addEdge('E','C',23.0));
	// assertEquals((Character)'C',g.getMoreEccentricNode());
	// assertEquals(0,g.removeNode('C'));
	// assertEquals((Character)'E',g.getMoreEccentricNode());
	// assertEquals(0,g.removeNode('E'));
	// assertEquals(null,g.getMoreEccentricNode());
	//
	// assertEquals("N0",gs.getMoreEccentricNode());
	// assertEquals(0,gs.addEdge("N2","N1",13.0));
	// assertEquals("N4",gs.getMoreEccentricNode());
	// assertEquals(0,gs.removeNode("N0"));
	// assertEquals("N4",gs.getMoreEccentricNode());
	// assertEquals(0,gs.addEdge("N3","N4",130.0));
	// assertEquals("N4",gs.getMoreEccentricNode());
	// assertEquals(0,gs.removeNode("N4"));
	// assertEquals("N3",gs.getMoreEccentricNode());
	// assertEquals(-1,gs.removeNode(null));
	// assertEquals(0,gs.removeNode("N3"));
	// assertEquals("N2",gs.getMoreEccentricNode());
	// assertEquals(0,gs.removeNode("N2"));
	// assertEquals("N1",gs.getMoreEccentricNode());
	// assertEquals(null,g.getMoreEccentricNode());
	//
	// assertEquals((Integer)1,gInteger.getMoreEccentricNode());
	// assertEquals(0,gInteger.addEdge(4,2,17.0));
	// assertEquals((Integer)1,gInteger.getMoreEccentricNode());
	// assertEquals(0,gInteger.addEdge(11,2,11.0));
	// assertEquals((Integer)11,gInteger.getMoreEccentricNode());
	// assertEquals(0,gInteger.removeNode(11));
	// assertEquals((Integer)7,gInteger.getMoreEccentricNode());
	// assertEquals(0,gInteger.removeNode(7));
	// assertEquals((Integer)8,gInteger.getMoreEccentricNode());
	// assertEquals(0,gInteger.removeNode(3));
	// assertEquals((Integer)8,gInteger.getMoreEccentricNode());
	// assertEquals(0,gInteger.removeNode(8));
	// assertEquals((Integer)9,gInteger.getMoreEccentricNode());
	// assertEquals(0,gInteger.removeNode(9));
	// assertEquals((Integer)5,gInteger.getMoreEccentricNode());
	// assertEquals(0,gInteger.removeNode(2));
	// assertEquals((Integer)1,gInteger.getMoreEccentricNode());
	// assertEquals(-1,gInteger.removeNode(15));
	// assertEquals(0,gInteger.removeNode(4));
	// assertEquals((Integer)6,gInteger.getMoreEccentricNode());
	// assertEquals(0,gInteger.removeNode(6));
	// assertEquals(0,gInteger.addEdge(1,5,21.0));
	// assertEquals((Integer)5,gInteger.getMoreEccentricNode());
	// assertEquals(0,gInteger.removeNode(5));
	// assertEquals((Integer)10,gInteger.getMoreEccentricNode());
	// assertEquals(0,gInteger.addEdge(1,10,20.0));
	// assertEquals((Integer)1,gInteger.getMoreEccentricNode());
	// assertEquals(0,gInteger.removeNode(1));
	// assertEquals((Integer)10,gInteger.getMoreEccentricNode());
	// assertEquals(0,gInteger.removeNode(10));
	// assertEquals(null,gInteger.getMoreEccentricNode());
	//
	// assertEquals((Integer)0,gp.getMoreEccentricNode());
	// assertEquals(0,gp.addEdge(2,4,19.0));
	// assertEquals(0,gp.removeNode(1));
	// assertEquals((Integer)0,gp.getMoreEccentricNode());
	// assertEquals(0,gp.removeNode(0));
	// assertEquals((Integer)2,gp.getMoreEccentricNode());
	// assertEquals(0,gp.removeNode(2));
	// assertEquals((Integer)4,gp.getMoreEccentricNode());
	// assertEquals(0,gp.addEdge(3,4,42.0));
	// assertEquals((Integer)3,gp.getMoreEccentricNode());
	// assertEquals(0,gp.removeNode(3));
	// assertEquals((Integer)4,gp.getMoreEccentricNode());
	// assertEquals(0,gp.removeNode(4));
	// assertEquals(null,gp.getMoreEccentricNode());
	//
	// assertEquals("V1",gStrings.getMoreEccentricNode());
	// assertEquals(0,gStrings.addEdge("V4","V1",10.0));
	// assertEquals("V3",gStrings.getMoreEccentricNode());
	// assertEquals(0,gStrings.removeNode("V3"));
	// assertEquals("V6",gStrings.getMoreEccentricNode());
	// assertEquals(0,gStrings.removeNode("V6"));
	// assertEquals("V1",gStrings.getMoreEccentricNode());
	// assertEquals(0,gStrings.removeNode("V1"));
	// assertEquals("V2",gStrings.getMoreEccentricNode());
	// assertEquals(0,gStrings.addEdge("V4","V2",12.0));
	// assertEquals("V2",gStrings.getMoreEccentricNode());
	// assertEquals(0,gStrings.addEdge("V5","V2",5.0));
	// assertEquals("V5",gStrings.getMoreEccentricNode());
	// assertEquals(0,gStrings.removeNode("V4"));
	// assertEquals("V5",gStrings.getMoreEccentricNode());
	// assertEquals(0,gStrings.removeNode("V5"));
	// assertEquals("V2",gStrings.getMoreEccentricNode());
	// assertEquals(0,gStrings.removeNode("V2"));
	// assertEquals(null,gStrings.getMoreEccentricNode());
	//
	// System.err.println("Examen G2 pasado");
	//
	// }

}