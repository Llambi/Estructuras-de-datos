package algoritmia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author uo250708
 */
public class AlgorithmsBenchmark {

    /**
     * Metodo para obtener tiempos de algoritmo linear
     *
     * @param output Archivo de salida para los tiempos.
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
     * @param output Archivo de salida para los tiempos.
     * @param StartN Numero de iteraciones minimo para el algoritmo a probar
     * @param EndN   Numero de iteraciones maximo para el algoritmo a probar
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
     * y un EndN
     *
     * @param output    Archivo de salida para los tiempos.
     * @param StartN    Numero de iteraciones minimo para el algoritmo a probar
     * @param EndN      Numero de iteraciones maximo para el algoritmo a probar
     * @param algorithm Algoritmo que se usara en el test
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
