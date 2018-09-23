package algoritmiaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import algoritmia.Algorithms;

class AlgorithmsTest {

	@Test
	void testPow2Iter() {
		Algorithms algorithm = new Algorithms();
		
		//Caso de exponente 0
		assertEquals(1, algorithm.pow2iter(0));
		
		//Caso de exponente positivo
		assertEquals(4, algorithm.pow2iter(2));
		assertEquals(1048576, algorithm.pow2iter(20));
	}

}
