package algoritmia;

/**
 * @author uo250708
 */
public class Algorithms {

    private final static int SLEEP_TIME = 5;

    /**
     * Algoritmo de complejidad n
     *
     * @param n numero de iteraciones a realizar
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
     * @param n numero de iteraciones a realizar
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
     * @param n numero de iteraciones a realizar
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
     * @param n numero de iteraciones a realizar
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
     * @param n exponente al que se quiere elevar 2
     * @return valor de 2^n
     */
    public long pow2iter(int n) {
        System.out.println(n + " times.");
        if (n == 0)
            return 1;
        else {
            long valor = 2;
            for (int i = 1; i < n; i++) {
                doNothing();  //Descomentar si tarda demasiado poco
                valor += valor;
            }
            return valor;
        }
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