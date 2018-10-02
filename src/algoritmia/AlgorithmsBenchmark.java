package algoritmia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author uo250708
 */
public class AlgorithmsBenchmark {

	/**
	 * Metodo para obtener tiempos de algoritmo linear
	 *
	 * @param output
	 *            Archivo de salida para los tiempos.
	 */
	public void test0(String output) {

		FileWriter file = null;
		PrintWriter pw;
		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);

			// pw.println("1, 10");
			for (int i = 1; i <= 4; i++) {
				pw.println(i + ", " + i * 10);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	/**
	 * Metodo para obtener tiempos de distintos tipos de algoritmos entre un StartN
	 * y un EndN
	 *
	 * @param output
	 *            Archivo de salida para los tiempos.
	 * @param StartN
	 *            Numero de iteraciones minimo para el algoritmo a probar
	 * @param EndN
	 *            Numero de iteraciones maximo para el algoritmo a probar
	 */
	public void test1(String output, int StartN, int EndN) {

		Algorithms alg = new Algorithms();
		long tInicial = 0, tFinal = 0;
		FileWriter file = null;
		PrintWriter pw;

		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);

			for (int i = StartN; i <= EndN; i++) {

				tInicial = System.currentTimeMillis();

				alg.linear(i);
				// alg.logarithmic(i);
				// alg.cuadratic(i);
				// alg.cubic(i);
				// alg.pow2iter(i);

				tFinal = System.currentTimeMillis();

				pw.println(i + ", " + (tFinal - tInicial));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

	/**
	 * Metodo para obtener tiempos de distintos tipos de algoritmos entre un StartN
	 * y un EndN y dandole el metodo a probar usando reflectividad.
	 *
	 * @param output
	 *            Archivo de salida para los tiempos.
	 * @param StartN
	 *            Numero de iteraciones minimo para el algoritmo a probar
	 * @param EndN
	 *            Numero de iteraciones maximo para el algoritmo a probar
	 * @param nombreClase
	 *            Clase que contiene el metodo que se va aprobar
	 * @param nombreMetodo
	 *            Metodo de la clase antes indicada que se probara
	 */
	public void test2(String output, int StartN, int EndN, String nombreClase, String nombreMetodo) {

		FileWriter file = null;
		PrintWriter pw;

		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);

			for (int i = StartN; i <= EndN; i++) {

				pw.println(i + ", " + testAlgorithm(nombreClase, nombreMetodo, i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

	public void testFinal(String output, int StartN, int EndN, int times, String nombreClase, String nombreMetodo) {

		FileWriter file = null;
		PrintWriter pw;

		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);

			for (int j = StartN; j <= EndN; j++) {
				long tiempoTotal = 0;
				for (int i = 0; i < times; i++) {
					tiempoTotal += testAlgorithm(nombreClase, nombreMetodo, j);
				}
				pw.println(j + ", " + tiempoTotal / times);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

	/**
	 * Metodo que dada una clase y un metodo lo ejecuta mediante reflexion
	 * 
	 * @param className
	 *            Clase que contiene el metodo a ejecutar
	 * @param methodName
	 *            Metodo que se quiere ejecutar
	 * @param n
	 *            numero de veces que se desea ejecutar el metodo
	 * @return tiempo que se ha tardado en realizar el algoritmo
	 */
	public long testAlgorithm(String className, String methodName, int n) {

		long tInicial = 0, tFinal = 0;

		Class<?> cl;

		try {

			cl = Class.forName(className);

			Object o = cl.newInstance();

			Method m = cl.getMethod(methodName, int.class);

			tInicial = System.currentTimeMillis();

			m.invoke(o, n);

			tFinal = System.currentTimeMillis();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return tFinal - tInicial;

	}

	/**
	 * Metodo para obtener tiempos de distintos tipos de algoritmos entre un StartN
	 * y un EndN
	 *
	 * @param output
	 *            Archivo de salida para los tiempos.
	 * @param StartN
	 *            Numero de iteraciones minimo para el algoritmo a probar
	 * @param EndN
	 *            Numero de iteraciones maximo para el algoritmo a probar
	 * @param algorithm
	 *            Algoritmo que se usara en el test
	 */
	public void test1(String output, int StartN, int EndN, String algorithm) {

		Algorithms alg = new Algorithms();
		long tInicial = 0, tFinal = 0;
		FileWriter file = null;
		PrintWriter pw;

		try {
			file = new FileWriter(output);
			pw = new PrintWriter(file);

			for (int i = StartN; i <= EndN; i++) {

				tInicial = System.currentTimeMillis();

				switch (algorithm) {
				case "linear":
					alg.linear(i);
					break;
				case "logarithmic":
					alg.logarithmic(i);
					break;
				case "cuadratic":
					alg.cuadratic(i);
					break;
				case "cubic":
					alg.cubic(i);
					break;
				case "pow2iter":
					alg.pow2iter(i);
					break;
				default:
					break;
				}

				tFinal = System.currentTimeMillis();

				pw.println(i + ", " + (tFinal - tInicial));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
}
