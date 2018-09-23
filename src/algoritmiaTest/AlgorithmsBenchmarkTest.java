package algoritmiaTest;

import algoritmia.AlgorithmsBenchmark;
import org.junit.jupiter.api.Test;

class AlgorithmsBenchmarkTest {

	@Test
	void test() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		algorithm.test0("Salida0.txt");
	}
	
	@Test
	void testLinear() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		//Recordar cambiar el metodo usado en el test1
		algorithm.test1("Salida1.txt", 100, 200, "linear");
	}
	
	@Test
	void testCuadratic() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		//Recordar cambiar el metodo usado en el test1
		algorithm.test1("Salida2.txt", 1, 100, "cuadratic");
	}
	
	@Test
	void testLogarithmic() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		//Recordar cambiar el metodo usado en el test1
		algorithm.test1("Salida3.txt", 1, 100, "logarithmic");
	}
	
	@Test
	void testCubic() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		//Recordar cambiar el metodo usado en el test1
		algorithm.test1("Salida4.txt", 1, 50, "cubic");
	}

	@Test
	void testPow2Iter() {
		AlgorithmsBenchmark algorithm = new AlgorithmsBenchmark();
		//Recordar cambiar el metodo usado en el test1
		algorithm.test1("Salida5.txt", 100, 100000000, "pow2iter");
	}

}
