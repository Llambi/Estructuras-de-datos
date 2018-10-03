package grafo;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Graph<T> {

	protected T[] nodos; // Vector de nodos

	protected boolean[][] aristas; // matriz de aristas

	protected double[][] pesos; // matriz de pesos

	protected int numNodes; // nÃºmero de elementos en un momento dado

	/**
	 * Metodo que crea el grafo, inicializando el vector de nodos, y las matrices de
	 * aristas y pesos.
	 * 
	 * @param tam
	 *            Numero de nodos del grafo.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Graph(int tam) {
		if (tam > 0) {
			// Inicializado vector de nodos.
			this.nodos = (T[]) new Object[tam];
			// Inicializada matriz de aristas.
			this.aristas = new boolean[tam][tam];
			Arrays.fill(this.aristas, false);
			// inicializada matriz de pesos
			this.pesos = new double[tam][tam];
			// inicializado numero de nodos
			this.numNodes = 0;
		}
	}

	/**
	 * Inserta un nuevo nodo que se le pasa como parÃ¡metro, en el vector de nodos,
	 * (si existe no lo inserta pero lo implementaremos mÃ¡s adelanteâ€¦)
	 * 
	 * @param node
	 *            el nodo que se quiere insertar
	 * 
	 * @return 0 si lo inserta, -1 si no puede insertarlo
	 */
	public int addNode(T node) {
		if (getNode(node) == -1) {
			this.nodos[this.numNodes++] = node;
			return 0;
		}
		return -1;
	}

	/**
	 * 
	 * Obtiene el Ã­ndice de un nodo en el vector de nodos Â¡Â¡Â¡ OJO que es privado
	 * porque depende de la implementaciÃ³n !!!
	 * 
	 * @param node
	 *            el nodo que se busca
	 * 
	 * @return la posiciÃ³n del nodo en el vector Ã³ -1 si no lo encuentra
	 */
	private int getNode(T node) {
		if (node != null) {
			for (int pos = 0; pos < this.numNodes; pos++) {
				if (this.nodos[pos].equals(node)) {
					return pos;
				}
			}
		}
		return -1;
	}

	/**
	 * 
	 * Inserta una arista con el peso indicado (> 0) entre dos nodos, uno origen y
	 * otro destino. Devuelve 0 si la inserta y -1 si no lo hace (si existe no la
	 * inserta)
	 * 
	 * @param source
	 *            nodo origen
	 * 
	 * @param target
	 *            nodo destino
	 * 
	 * @param edgeWeight
	 *            peso de la arista, debe ser > 0
	 * 
	 * @return 0 si lo hace y -1 si no lo hace (tambiÃ©n si el peso es <= 0)
	 * 
	 */

	public int addEdge(T source, T target, double edgeWeight) {
		if (!existEdge(source, target)) {
			this.aristas[getNode(source)][getNode(target)] = true;
			this.pesos[getNode(source)][getNode(target)] = edgeWeight;
			return 0;
		}
		return -1;
	}

	/**
	 * Borra el nodo deseado del vector de nodos asÃ­ como las aristas de las que
	 * forma parte
	 * 
	 * @param node
	 *            que se quiere borrar
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
	 * @param source
	 *            nodo origen
	 * 
	 * @param target
	 *            nodo destino
	 * 
	 * @return 0 si la borra y -1 si no lo hace (tambiÃ©n si no existe alguno de sus
	 *         nodos)
	 */
	public int removeEdge(T source, T target) {
		// TODO:
		return -1;
	}

	/**
	 * Metodo que comprueba si existe, o no, un nodo en el grafo.
	 * 
	 * @param node
	 *            Nodo que se quiere consultar
	 * 
	 * @return si existe o no el nodo
	 */
	public boolean existNode(T node) {
		return getNode(node) == -1 ? false : true;
	}

	/**
	 * Comprueba si existe una arista entre dos nodos que se pasan como parÃ¡metro
	 * 
	 * @param source
	 *            nodo origen
	 * 
	 * @param target
	 *            nodo destino
	 * 
	 * @return verdadero o falso si existe o no, si alguno de los nodos no existe
	 *         tambiÃ©n falso
	 */
	public boolean existEdge(T source, T target) {
		return getEdge(source, target) == -1d ? false : true;
	}

	/**
	 * Devuelve el peso de la arista que conecta dos nodos, si no existe la arista,
	 * devuelve -1 (tambiÃ©n si no existe alguno de los nodos)
	 * 
	 * @param source
	 * 
	 * @param target
	 * 
	 * @return El peso de la arista o -1 si no existe
	 */
	public double getEdge(T source, T target) {
		if (source != null && target != null && this.aristas[getNode(source)][getNode(target)]) {
			return this.pesos[getNode(source)][getNode(target)];
		}
		return -1d;
	}

	/**
	 * Metodo que dado un grafo saca por consola la lista de nodos, la matriz de
	 * pesos y de aristas.
	 * 
	 * @return String con la informacion del grafo
	 */
	@Override
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
