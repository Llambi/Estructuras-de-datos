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
	
	@Test
	void testPow2Rec1() {
		Algorithms algorithm = new Algorithms();
		
		//Caso de exponente 0
		assertEquals(1, algorithm.pow2Rec1(0));
		
		//Caso de exponente positivo
		assertEquals(4, algorithm.pow2Rec1(2));
		assertEquals(1024, algorithm.pow2Rec1(10));
	}
	
	@Test
	void testPow2Rec2() {
		Algorithms algorithm = new Algorithms();
		
		//Caso de exponente 0
		assertEquals(1, algorithm.pow2Rec2(0));
		
		//Caso de exponente positivo
		assertEquals(4, algorithm.pow2Rec2(2));
		assertEquals(1024, algorithm.pow2Rec2(10));
	}
	
	@Test
	void testPow2Rec3() {
		Algorithms algorithm = new Algorithms();
		
		//Caso de exponente 0
		assertEquals(1, algorithm.pow2Rec3(0));
		
		//Caso de exponente positivo
		assertEquals(4, algorithm.pow2Rec3(2));
		assertEquals(1024, algorithm.pow2Rec3(10));
	}
	
	@Test
	void testPow2Rec4() {
		Algorithms algorithm = new Algorithms();
		
		//Caso de exponente 0
		assertEquals(1, algorithm.pow2Rec4(0));
		
		//Caso de exponente positivo
		assertEquals(4, algorithm.pow2Rec4(2));
		assertEquals(1024, algorithm.pow2Rec4(10));
	}


}
