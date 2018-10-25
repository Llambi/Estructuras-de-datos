//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package test;


import evalNestor.EvalGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class GraphTestV1Prof {
    private static final double Inf = 1.0D / 0.0;
    private static final boolean T = true;
    private static final boolean F = false;

    GraphTestV1Prof() {
    }

    @Test
    void testDijkstra() {
        EvalGraph<Integer> g = new EvalGraph(6, new Integer[]{0, 1, 2, 3, 4, 5}, new boolean[][]{{false, true, false, true, false, true}, {false, false, false, false, false, true}, {false, false, false, false, true, false}, {false, false, true, false, true, false}, new boolean[6], {false, false, true, false, false, false}}, new double[][]{{0.0D, 5.0D, 0.0D, 20.0D, 0.0D, 13.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 6.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 7.0D, 0.0D}, {0.0D, 0.0D, 2.0D, 0.0D, 9.0D, 0.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D}, {0.0D, 0.0D, 10.0D, 0.0D, 0.0D, 0.0D}});
        assertArrayEquals(new double[]{0.0D, 5.0D, 21.0D, 20.0D, 28.0D, 11.0D}, g.dijkstra(0), 1.0E-4D);
        assertArrayEquals(new double[]{1.0D / 0.0, 0.0D, 16.0D, 1.0D / 0.0, 23.0D, 6.0D}, g.dijkstra(1), 1.0E-4D);
        assertArrayEquals(new double[]{1.0D / 0.0, 1.0D / 0.0, 0.0D, 1.0D / 0.0, 7.0D, 1.0D / 0.0}, g.dijkstra(2), 1.0E-4D);
        assertArrayEquals(new double[]{1.0D / 0.0, 1.0D / 0.0, 2.0D, 0.0D, 9.0D, 1.0D / 0.0}, g.dijkstra(3), 1.0E-4D);
        assertArrayEquals(new double[]{1.0D / 0.0, 1.0D / 0.0, 1.0D / 0.0, 1.0D / 0.0, 0.0D, 1.0D / 0.0}, g.dijkstra(4), 1.0E-4D);
        assertArrayEquals(new double[]{1.0D / 0.0, 1.0D / 0.0, 10.0D, 1.0D / 0.0, 17.0D, 0.0D}, g.dijkstra(5), 1.0E-4D);
    }

    @Test
    void testFloyd() {
        EvalGraph<Integer> g = new EvalGraph(6, new Integer[]{0, 1, 2, 3, 4, 5}, new boolean[][]{{false, true, false, true, false, true}, {false, false, false, false, false, true}, {false, false, false, false, true, false}, {false, false, true, false, true, false}, new boolean[6], {false, false, true, false, false, false}}, new double[][]{{0.0D, 5.0D, 0.0D, 20.0D, 0.0D, 13.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 6.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 7.0D, 0.0D}, {0.0D, 0.0D, 2.0D, 0.0D, 9.0D, 0.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D}, {0.0D, 0.0D, 10.0D, 0.0D, 0.0D, 0.0D}});
        Assertions.assertEquals(0, g.floyd());
        Assertions.assertEquals("NODES\n0\t1\t2\t3\t4\t5\t\n\nEDGES\nF\tT\tF\tT\tF\tT\t\nF\tF\tF\tF\tF\tT\t\nF\tF\tF\tF\tT\tF\t\nF\tF\tT\tF\tT\tF\t\nF\tF\tF\tF\tF\tF\t\nF\tF\tT\tF\tF\tF\t\n\nWEIGHTS\n-\t5\t-\t20\t-\t13\t\n-\t-\t-\t-\t-\t6\t\n-\t-\t-\t-\t7\t-\t\n-\t-\t2\t-\t9\t-\t\n-\t-\t-\t-\t-\t-\t\n-\t-\t10\t-\t-\t-\t\n\nAFloyd\n0\t5\t21\t20\t28\t11\t\n∞\t0\t16\t∞\t23\t6\t\n∞\t∞\t0\t∞\t7\t∞\t\n∞\t∞\t2\t0\t9\t∞\t\n∞\t∞\t∞\t∞\t0\t∞\t\n∞\t∞\t10\t∞\t17\t0\t\n\nPFloyd\n-\t-\t5\t-\t5\t1\t\n-\t-\t5\t-\t5\t-\t\n-\t-\t-\t-\t-\t-\t\n-\t-\t-\t-\t-\t-\t\n-\t-\t-\t-\t-\t-\t\n-\t-\t-\t-\t2\t-\t\n", g.toString());
    }

    @Test
    void testPath() {
        EvalGraph<Integer> g = new EvalGraph(6, new Integer[]{0, 1, 2, 3, 4, 5}, new boolean[][]{{false, true, false, true, false, true}, {false, false, false, false, false, true}, {false, false, false, false, true, false}, {false, false, true, false, true, false}, new boolean[6], {false, false, true, false, false, false}}, new double[][]{{0.0D, 5.0D, 0.0D, 20.0D, 0.0D, 13.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 6.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 7.0D, 0.0D}, {0.0D, 0.0D, 2.0D, 0.0D, 9.0D, 0.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D}, {0.0D, 0.0D, 10.0D, 0.0D, 0.0D, 0.0D}}, new double[][]{{0.0D, 5.0D, 21.0D, 20.0D, 28.0D, 11.0D}, {1.0D / 0.0, 0.0D, 16.0D, 1.0D / 0.0, 23.0D, 6.0D}, {1.0D / 0.0, 1.0D / 0.0, 0.0D, 1.0D / 0.0, 7.0D, 1.0D / 0.0}, {1.0D / 0.0, 1.0D / 0.0, 2.0D, 0.0D, 9.0D, 1.0D / 0.0}, {1.0D / 0.0, 1.0D / 0.0, 1.0D / 0.0, 1.0D / 0.0, 0.0D, 1.0D / 0.0}, {1.0D / 0.0, 1.0D / 0.0, 10.0D, 1.0D / 0.0, 17.0D, 0.0D}}, new int[][]{{-1, -1, 5, -1, 5, 1}, {-1, -1, 5, -1, 5, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, 2, -1}});
        Assertions.assertEquals("0\t(5.0)\t1\t", g.path(0, 1));
        Assertions.assertEquals("0\t(5.0)\t1\t(6.0)\t5\t(10.0)\t2\t", g.path(0, 2));
        Assertions.assertEquals("0\t(20.0)\t3\t", g.path(0, 3));
        Assertions.assertEquals("0\t(5.0)\t1\t(6.0)\t5\t(10.0)\t2\t(7.0)\t4\t", g.path(0, 4));
        Assertions.assertEquals("0\t(5.0)\t1\t(6.0)\t5\t", g.path(0, 5));
        Assertions.assertEquals("0\t", g.path(0, 0));
        Assertions.assertEquals("5\t(Infinity)\t0\t", g.path(5, 0));
        Assertions.assertEquals("", g.path(0, 6));
    }

    @Test
    void testMinCostPath() {
        EvalGraph<Integer> g = new EvalGraph(6, new Integer[]{0, 1, 2, 3, 4, 5}, new boolean[][]{{false, true, false, true, false, true}, {false, false, false, false, false, true}, {false, false, false, false, true, false}, {false, false, true, false, true, false}, new boolean[6], {false, false, true, false, false, false}}, new double[][]{{0.0D, 5.0D, 0.0D, 20.0D, 0.0D, 13.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 6.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 7.0D, 0.0D}, {0.0D, 0.0D, 2.0D, 0.0D, 9.0D, 0.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D}, {0.0D, 0.0D, 10.0D, 0.0D, 0.0D, 0.0D}}, new double[][]{{0.0D, 5.0D, 21.0D, 20.0D, 28.0D, 11.0D}, {1.0D / 0.0, 0.0D, 16.0D, 1.0D / 0.0, 23.0D, 6.0D}, {1.0D / 0.0, 1.0D / 0.0, 0.0D, 1.0D / 0.0, 7.0D, 1.0D / 0.0}, {1.0D / 0.0, 1.0D / 0.0, 2.0D, 0.0D, 9.0D, 1.0D / 0.0}, {1.0D / 0.0, 1.0D / 0.0, 1.0D / 0.0, 1.0D / 0.0, 0.0D, 1.0D / 0.0}, {1.0D / 0.0, 1.0D / 0.0, 10.0D, 1.0D / 0.0, 17.0D, 0.0D}}, new int[][]{{-1, -1, 5, -1, 5, 1}, {-1, -1, 5, -1, 5, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, 2, -1}});
        Assertions.assertEquals(5.0D, g.minCostPath(0, 1), 0.001D);
        Assertions.assertEquals(21.0D, g.minCostPath(0, 2), 0.001D);
        Assertions.assertEquals(20.0D, g.minCostPath(0, 3), 0.001D);
        Assertions.assertEquals(28.0D, g.minCostPath(0, 4), 0.001D);
        Assertions.assertEquals(11.0D, g.minCostPath(0, 5), 0.001D);
        Assertions.assertEquals(-1.0D, g.minCostPath(0, 6), 0.001D);
        Assertions.assertEquals(1.0D / 0.0, g.minCostPath(5, 0), 0.001D);
    }

	@Test
	void testProfundidad() {
		EvalGraph<Integer> g = new EvalGraph(6, new Integer[]{0, 1, 2, 3, 4, 5}, new boolean[][]{{false, true, false, true, false, true}, {false, false, false, false, false, true}, {false, false, false, false, true, false}, {false, false, true, false, true, false}, new boolean[6], {false, false, true, false, false, false}}, new double[][]{{0.0D, 5.0D, 0.0D, 20.0D, 0.0D, 13.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 6.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 7.0D, 0.0D}, {0.0D, 0.0D, 2.0D, 0.0D, 9.0D, 0.0D}, {0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D}, {0.0D, 0.0D, 10.0D, 0.0D, 0.0D, 0.0D}});
		Assertions.assertEquals("0\t1\t5\t2\t4\t3\t", g.recorridoProfundidad(0));
		Assertions.assertEquals("", g.recorridoProfundidad(6));
	}
}
