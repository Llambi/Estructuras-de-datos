package p3Arboles;

/**
 * Clase que simula un monticulo binario.
 */
public class EDBinaryHeap<T extends Comparable<T>> implements EDPriorityQueue<T> {
	protected T[] elementos;
	protected int numElementos;

	@SuppressWarnings("unchecked")
	public EDBinaryHeap(int numMaxElementos) {

		elementos = (T[]) new Comparable[numMaxElementos];
		numElementos = 0;

	}

	@Override
	public int add(T info) {

		if (info == null) {
			return -2;
		} else if (numElementos > elementos.length - 1) {
			return -1;
		} else {
			elementos[numElementos] = info; // si hay sitio ponemos el elemento
			filtradoAscendente(numElementos); // pero obviamente hay que reordenarlo
			numElementos++; // aumentamos el numero de elementos
			return 0;
		}

	}

	@Override
	public T getTop() {
		if (numElementos <= 0)
			return null;

		T primero = elementos[0];
		remove(primero);
		filtradoDescendente(0);
		return primero;
	}

	@Override
	public int remove(T info) {
		if (info == null)
			return -2;
		int posicion = getPos(info);
		if (posicion == -1)
			return -1;

		elementos[posicion] = elementos[numElementos - 1];
		numElementos--;
		filtradoDescendente(posicion);
		filtradoAscendente(posicion);
		return 0;
	}

	/**
	 * Metodo que da la posicion en la array de un elemento dado.
	 * 
	 * @param info
	 *            Elemento del que se quiere conocer la posicion.
	 * @return La posicion del elemento a buscar o -1 si no se a encontrado el
	 *         elemento.
	 */
	private int getPos(T info) {
		if (info == null)
			return -1;
		for (int i = 0; i < numElementos; i++) {
			if (elementos[i].equals(info))
				return i;
		}

		return -1;
	}

	@Override
	public boolean isEmpty() {
		if (numElementos == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		numElementos = 0;
	}

	/**
	 * Devuelve una cadena con la informacion de los elementos que contiene el
	 * monticulo en forma visible (recomendado inorden-derecha-izquierda tabulado)
	 */
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		cadena.append(inOrdenDerechaTabulado(0, 0));
		return cadena.toString();
	}

	/**
	 * Metodo que devuelve una cadea del arbol tumbado con raiz en p y esp
	 * tabulaciones de separacion.
	 * 
	 * @param p
	 *            Raiz del arbol.
	 * @param esp
	 *            Numero de tabulaciones.
	 * @return Cadenas con el arbol tumabado
	 */
	private String inOrdenDerechaTabulado(int p, int esp) {
		String cadena = "";
		if (p < numElementos) {
			int izq = Math.abs(2 * p + 1);
			int der = Math.abs(2 * p + 2);
			cadena += inOrdenDerechaTabulado(der, esp + 1);
			for (int i = 0; i < esp; i++)
				cadena += "\t";
			cadena += elementos[p] + "\n";
			cadena += inOrdenDerechaTabulado(izq, esp + 1);
		}
		return cadena;
	}

	/**
	 * Realiza una filtrado ascendente de minimos en el monticulo, para ello: -
	 * Obtendremos la direccion del padre. - Guardaremos la referencia al padre. -
	 * Asignaremos en la posicion del padre la del hijo. - Asignaremos en la
	 * posicion del hijo la del padre. - Volveremos a realizar todo lo anterior
	 * hasta que la prioridad sea igual o menor.
	 * 
	 * @param p
	 *            el indice del elemento a filtrar
	 */
	protected void filtradoAscendente(int p) {

		int posPadre = (p - 1) / 2; // direcion de la posicion del padre

		if ((elementos[p].compareTo(elementos[posPadre]) < 0)) { // comparar si el padre es mayor
			T referencia = elementos[posPadre]; // guardamos la referencia al padre
			elementos[posPadre] = elementos[p]; // asignamos en la posicion del padre la del hijo
			elementos[p] = referencia; // y la del padre a la del hijo
			filtradoAscendente(posPadre);// se llamara con la del "nuevo padre"
		}
	}

	/**
	 * Realiza una filtrado descendente de minimos en el monticulo
	 * 
	 * @param p
	 *            el indice del elemento a filtrar
	 */
	protected void filtradoDescendente(int p) {

		int little = (2 * p) + 1; // asignamos el valor del hijo izq

		if ((2 * p) + 1 < elementos.length && (2 * p) + 2 < elementos.length && elementos[(2 * p) + 1] != null
				&& elementos[(2 * p) + 2] != null) { // comprobacion: dentro de array

			if ((elementos[p].compareTo(elementos[little]) > 0)) { // si es mayor que su hijo

				if (elementos[little].compareTo(elementos[(2 * p) + 2]) > 0) {
					little = (2 * p) + 2;
				}
				// Intercambio
				T referencia = elementos[little];
				elementos[little] = elementos[p];
				elementos[p] = referencia;
				// recursiveCall
				filtradoDescendente(little);
			}
		} else {
			if (numElementos > 1) {
				if (elementos[p].compareTo(elementos[numElementos - 1]) > 0) {
					T referencia = elementos[p];
					elementos[p] = elementos[numElementos - 1];
					elementos[numElementos - 1] = referencia;
				}
			}
		}
	}

}
