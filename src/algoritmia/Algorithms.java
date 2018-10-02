package algoritmia;

/**
 * @author uo250708
 */
public class Algorithms {

	private final static int SLEEP_TIME = 1;
	private long cache = 0;

	/**
	 * Algoritmo de complejidad n
	 *
	 * @param n
	 *            numero de iteraciones a realizar
	 */
	public void linear(int n) {
		System.out.println(n + " times.");
		for (int i = 0; i < n; i++) {
			doNothing();
		}
	}

	/**
	 * Algoritmo de complejidad n^2
	 *
	 * @param n
	 *            numero de iteraciones a realizar
	 */
	public void cuadratic(int n) {
		System.out.println(n + " times.");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				doNothing();
			}
		}
	}

	/**
	 * Algoritmo de complejidad log(n)
	 *
	 * @param n
	 *            numero de iteraciones a realizar
	 */
	public void logarithmic(int n) {
		System.out.println(n + " times.");
		for (int i = 1; i <= n; i *= 2) {
			doNothing();
		}
	}

	/**
	 * Algoritmo de complejidad n^3
	 *
	 * @param n
	 *            numero de iteraciones a realizar
	 */
	public void cubic(int n) {
		System.out.println(n + " times.");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					doNothing();
				}
			}
		}
	}

	/**
	 * Algoritmo para el calculo de la enesima potencia de 2
	 *
	 * @param n
	 *            exponente al que se quiere elevar 2
	 * @return valor de 2^n
	 */
	public long pow2iter(int n) {
		System.out.println(n + " times.");
		if (n < 0) {
			return -1;
		} else if (n == 0) {
			return 1;
		} else {
			long valor = 2;
			for (int i = 1; i < n; i++) {
				doNothing(); // Descomentar si tarda demasiado poco
				valor += valor;
			}
			return valor;
		}
	}

	/**
	 * Metodo que calcula la enesima potencia de 2 mediante recursividad.
	 * 
	 * @param n
	 *            exponente al que se quiere elevar 2
	 * @return valor de 2^n
	 */
	public long pow2Rec1(int n) {
		System.out.println(n + " times.");
		doNothing();
		if (n < 0) {
			return -1;
		} else if (n == 0) {
			// System.out.println("_____________DONE_____________");
			return 1;
		} else {
			return 2 * pow2Rec1(--n);
		}
	}

	public long pow2Rec2(int n) {
		System.out.println(n + " times.");
		doNothing();
		if (n == 0)
			return 1;
		else if (n == 1) {
			return 2;
		} else {
			return pow2Rec2(--n) + pow2Rec2(n);
		}
	}

	public long pow2Rec3(int n) {
		System.out.println(n + " times.");
		doNothing();
		if (n == 0)
			return 1;
		else if (n % 2 == 0)
			return pow2Rec3(n / 2) * pow2Rec3(n / 2);
		else
			return 2 * pow2Rec3(n / 2) * pow2Rec3(n / 2);
	}

	public long pow2Rec4(int n) {
		System.out.println(n + " times.");
		doNothing();
		if (n == 0)
			return 1;
		cache = pow2Rec4(n / 2);
		if (n % 2 == 0)
			return cache * cache;
		else
			return 2 * cache * cache;
	}

	/**
	 * Metodo que calcula el factorial de n
	 * 
	 * @param n
	 *            Valor del que se quiere calcular el factorial
	 * @return Resultado de factorial de n
	 */
	public int fact(int n) {
		System.out.println(n + " times.");
		boolean negative = n < 0;
		int result;
		if (negative) {
			n = Math.abs(n);
		}
		if (n == 0 || n == 1) {
			doNothing(); // Descomentar si tarda demasiado poco
			return 1;
		}
		doNothing(); // Descomentar si tarda demasiado poco
		result = fact(n - 1) * n;
		return negative ? result * 1 : result;
	}

	/**
	 * Metodo que duerme el proceso para que los tiempos de los algoritmos sea
	 * representativos.
	 */
	public void doNothing() {
		try {
			Thread.sleep(SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}