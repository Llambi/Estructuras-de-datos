package algoritmiaTest;

import algoritmia.AlgorithmsBenchmark;
import org.junit.jupiter.api.Test;

class AlgorithmsBenchmarkTest {

	private static final int TIMES_2_REPEAT = 5;
	private static final String ALGORITMIA_ALGORITHMS_JAVA = "algoritmia.Algorithms";

	@Test
	void test() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.test0("Salida0.txt");
	}
	
	@Test
	void testLinear() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.test2("output/Salida1.txt", 0, 100, ALGORITMIA_ALGORITHMS_JAVA, "linear");
	}
	
	@Test
	void testLinearFinal() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.testFinal("output/Salida1Final.txt", 0, 100, TIMES_2_REPEAT, ALGORITMIA_ALGORITHMS_JAVA, "linear");
	}
	
	@Test
	void testCuadratic() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.test2("output/Salida2.txt", 1, 100, ALGORITMIA_ALGORITHMS_JAVA, "cuadratic");
	}
	
	@Test
	void testCuadraticfinal() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.testFinal("output/Salida2Final.txt", 1, 100, TIMES_2_REPEAT, ALGORITMIA_ALGORITHMS_JAVA, "cuadratic");
	}
	
	@Test
	void testLogarithmic() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.test2("output/Salida3.txt", 1, 100, ALGORITMIA_ALGORITHMS_JAVA, "logarithm");
	}
	
	@Test
	void testLogarithmicFinal() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.testFinal("output/Salida3Final.txt", 1, 100, TIMES_2_REPEAT, ALGORITMIA_ALGORITHMS_JAVA, "logarithm");
	}
	
	@Test
	void testCubic() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.test2("output/Salida4.txt", 1, 50, ALGORITMIA_ALGORITHMS_JAVA, "cubic");
	}
	
	@Test
	void testCubicFinal() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.testFinal("output/Salida4Final.txt", 1, 50, TIMES_2_REPEAT, ALGORITMIA_ALGORITHMS_JAVA, "cubic");
	}

	@Test
	void testPow2Iter() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.test2("output/Salida5.txt", 100, 100000000, ALGORITMIA_ALGORITHMS_JAVA, "pow2iter");
	}
	
	@Test
	void testPow2IterFinal() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.testFinal("output/Salida5Final.txt", 100, 100000000, TIMES_2_REPEAT, ALGORITMIA_ALGORITHMS_JAVA, "pow2iter");
	}
	
	@Test
	void testPow2Rec1Final() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.testFinal("output/Salida6Final.txt", 1, 100, TIMES_2_REPEAT, ALGORITMIA_ALGORITHMS_JAVA, "pow2Rec1");
	}
	
	@Test
	void testPow2Rec2Final() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.testFinal("output/Salida7Final.txt", 1, 10, TIMES_2_REPEAT, ALGORITMIA_ALGORITHMS_JAVA, "pow2Rec2");
	}
	
	@Test
	void testPow2Rec3Final() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.testFinal("output/Salida8Final.txt", 1, 100, TIMES_2_REPEAT, ALGORITMIA_ALGORITHMS_JAVA, "pow2Rec3");
	}
	
	@Test
	void testPow2Rec4Final() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.testFinal("output/Salida9Final.txt", 1, 100, TIMES_2_REPEAT, ALGORITMIA_ALGORITHMS_JAVA, "pow2Rec4");
	}
	
	@Test
	void testFact() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.testFinal("output/Salida10Final.txt", 1, 100, TIMES_2_REPEAT, ALGORITMIA_ALGORITHMS_JAVA, "fact");
	}


}
