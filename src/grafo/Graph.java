package grafo;

import java.text.DecimalFormat;

public class Graph<T> {

	protected T[] nodos; // Vector de nodos

	protected boolean[][] aristas; // matriz de aristas

	protected double[][] pesos; // matriz de pesos

	protected int numNodes; // número de elementos en un momento dado

	/**
	 * @param tam Número máximo de nodos del grafo
	 */

	@SuppressWarnings("unchecked")
	public Graph(int tam) {
		// TODO:
		nodos = (T[]) new Object[tam];

	}

	/**
	 * 
	 * Inserta un nuevo nodo que se le pasa como parámetro, en el vector de
	 * 
	 * nodos, (si existe no lo inserta pero lo implementaremos más adelante…)
	 * 
	 * 
	 * 
	 * @param node el nodo que se quiere insertar
	 * 
	 * @return 0 si lo inserta, -1 si no puede insertarlo
	 */
	public int addNode(T node) {
		// TODO:
		return -1;
	}

	/**
	 * 
	 * Obtiene el índice de un nodo en el vector de nodos ¡¡¡ OJO que es privado
	 * porque depende de la implementación !!!
	 * 
	 * @param node el nodo que se busca
	 * 
	 * @return la posición del nodo en el vector ó -1 si no lo encuentra
	 */
	private int getNode(T node) {
		// TODO:
		return -1;
	}

	/**
	 * 
	 * Inserta una arista con el peso indicado (> 0) entre dos nodos, uno origen y
	 * otro destino. Devuelve 0 si la inserta y -1 si no lo hace (si existe no la
	 * inserta)
	 * 
	 * @param source     nodo origen
	 * 
	 * @param target     nodo destino
	 * 
	 * @param edgeWeight peso de la arista, debe ser > 0
	 * 
	 * @return 0 si lo hace y -1 si no lo hace (también si el peso es <= 0)
	 * 
	 */

	public int addEdge(T source, T target, double edgeWeight) {
		// TODO:
		return -1;
	}

	/**
	 * Borra el nodo deseado del vector de nodos así como las aristas de las que
	 * forma parte
	 * 
	 * @param node que se quiere borrar
	 * 
	 * @return 0 si lo borra y -1 si no lo hace
	 * 
	 */

	public int removeNode(T node) {
		// TODO:
		return -1;
	}

	/**
	 * Borra una arista del grafo que conecta dos nodos
	 * 
	 * @param source nodo origen
	 * 
	 * @param target nodo destino
	 * 
	 * @return 0 si la borra y -1 si no lo hace (también si no existe alguno de sus
	 *         nodos)
	 */
	public int removeEdge(T source, T target) {
		// TODO:
		return -1;
	}

	/**
	 * @param node Nodo que se quiere consultar
	 * 
	 * @return si existe o no el nodo
	 */
	public boolean existNode(T node) {
		// TODO:
		return false;
	}

	/**
	 * Comprueba si existe una arista entre dos nodos que se pasan como parámetro
	 * 
	 * @param source nodo origen
	 * 
	 * @param target nodo destino
	 * 
	 * @return verdadero o falso si existe o no, si alguno de los nodos no existe
	 *         también falso
	 */
	public boolean existEdge(T source, T target) {
		// TODO:
		return false;
	}

	/**
	 * Devuelve el peso de la arista que conecta dos nodos, si no existe la arista,
	 * devuelve -1 (también si no existe alguno de los nodos)
	 * 
	 * @param source
	 * 
	 * @param target
	 * 
	 * @return El peso de la arista o -1 si no existe
	 */
	public double getEdge(T source, T target) {
		// TODO:
		return -1d;
	}

	/**
	 * Metodo que dado un grafo saca por consola la lista de nodos, la matriz de
	 * pesos y de aristas.
	 * 
	 * @return String con la informacion del grafo
	 */
	public String toString() {

		DecimalFormat df = new DecimalFormat("#.##");

		String cadena = "";

		cadena += "NODES\n";

		for (int i = 0; i < numNodes; i++) {
			cadena += nodos[i].toString() + "\t";
		}

		cadena += "\n\nEDGES\n";

		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {

				if (aristas[i][j]) {
					cadena += "T\t";
				} else {
					cadena += "F\t";
				}
			}
			cadena += "\n";
		}

		cadena += "\nWEIGHTS\n";

		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {
				cadena += (aristas[i][j] ? df.format(pesos[i][j]) : "-") + "\t";
			}
			cadena += "\n";
		}

		return cadena;
	}

}
