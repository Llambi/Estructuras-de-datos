package p2Grafos;

import java.text.DecimalFormat;

public class Graph<T> {

	protected T[] nodos; // Vector de nodos
	protected boolean[][] aristas; // matriz de aristas
	protected double[][] pesos; // matriz de pesos
	protected int numNodes; // numero de elementos en un momento dado

	/**
	 * Metodo que crea el grafo, inicializando el vector de nodos, y las matrices de
	 * aristas y pesos.
	 * 
	 * @param tam
	 *            Numero de nodos del grafo.
	 */
	@SuppressWarnings("unchecked")
	public Graph(int tam) {
		if (tam > 0) {
			// Inicializado vector de nodos.
			this.nodos = (T[]) new Object[tam];
			// Inicializada matriz de aristas.
			this.aristas = new boolean[tam][tam];
			initAristas(tam);
			// inicializada matriz de pesos
			this.pesos = new double[tam][tam];
			// inicializado numero de nodos
			this.numNodes = 0;
		}
	}

	/**
	 * Metodo que inicializa una matriz de tam*tam a false en todas sus posiciones
	 * 
	 * @param tam
	 *            tamaño de la matriz cuadrada
	 */
	private void initAristas(int tam) {
		for (int i = 0; i < tam; i++) {
			for (int j = 0; j < tam; j++) {
				this.aristas[i][j] = false;
			}
		}
	}

	/**
	 * Inserta un nuevo nodo que se le pasa como parametro, en el vector de nodos,
	 * (si existe no lo inserta pero lo implementaremos mas adelante)
	 * 
	 * @param node
	 *            el nodo que se quiere insertar
	 * 
	 * @return 0 si lo inserta, -1 si no puede insertarlo
	 */
	public int addNode(T node) {
		if (this.numNodes != this.nodos.length && node != null && getNode(node) == -1) {
			this.nodos[this.numNodes] = node;
			this.numNodes++;
			return 0;
		}
		return -1;
	}

	/**
	 * Obtiene el indice de un nodo en el vector de nodos
	 * 
	 * @param node
	 *            el nodo que se busca
	 * 
	 * @return la posicion del nodo en el vector Ã³ -1 si no lo encuentra
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
	 * @return 0 si lo hace y -1 si no lo hace (tambien si el peso es <= 0)
	 */

	public int addEdge(T source, T target, double edgeWeight) {
		if (edgeWeight < 0)
			return -1;

		if (!existEdge(source, target) && (existNode(source)) && (existNode(target))) {
			int i = getNode(source);
			int j = getNode(target);

			this.aristas[i][j] = true;
			this.pesos[i][j] = edgeWeight;
			return 0;

		} else if (existEdge(source, target)) {
			int i = getNode(source);
			int j = getNode(target);
			this.aristas[i][j] = true;
			this.pesos[i][j] = edgeWeight;
			return 0;

		} else {
			return -1;
		}
	}

	/**
	 * Borra el nodo deseado del vector de nodos asi como las aristas de las que
	 * forma parte
	 * 
	 * @param node
	 *            que se quiere borrar
	 * 
	 * @return 0 si lo borra y -1 si no lo hace
	 */
	public int removeNode(T node) {
		int i = getNode(node);
		if (i >= 0) {
			--this.numNodes; // Si es el ultimo nodo no hace falta mas por que sera info basura
			if (i != this.numNodes + 1) {
				this.nodos[i] = this.nodos[this.numNodes]; // Cambio si no es el ultimo nodo
				// Cambio de valores en los vectores correspondientes de aristas y pesos
				for (int j = 0; j <= this.numNodes; j++) {
					this.aristas[j][i] = this.aristas[j][this.numNodes];
					this.aristas[i][j] = this.aristas[this.numNodes][j];
					this.pesos[i][j] = this.pesos[this.numNodes][j];
					this.pesos[j][i] = this.pesos[j][this.numNodes];
				}
				for (int j = 0; j < this.numNodes; j++) {
					this.aristas[i][i] = this.aristas[this.numNodes][this.numNodes];
					this.pesos[i][i] = this.pesos[this.numNodes][this.numNodes];
				}
			}
			return 0;
		}
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
	 * @return 0 si la borra y -1 si no lo hace (tambien si no existe alguno de sus
	 *         nodos)
	 */
	public int removeEdge(T source, T target) {
		if (existEdge(source, target)) {
			int i = getNode(source);
			int j = getNode(target);
			this.aristas[i][j] = false;
			this.pesos[i][j] = 0.0;
			return 0;
		} else
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
	 * Comprueba si existe una arista entre dos nodos que se pasan como parametro
	 * 
	 * @param source
	 *            nodo origen
	 * 
	 * @param target
	 *            nodo destino
	 * 
	 * @return verdadero o falso si existe o no, si alguno de los nodos no existe
	 *         tambien falso
	 */
	public boolean existEdge(T source, T target) {
		return getEdge(source, target) == -1d ? false : true;
	}

	/**
	 * Devuelve el peso de la arista que conecta dos nodos, si no existe la arista,
	 * devuelve -1 (tambien si no existe alguno de los nodos)
	 * 
	 * @param source
	 * 
	 * @param target
	 * 
	 * @return El peso de la arista o -1 si no existe
	 */
	public double getEdge(T source, T target) {
		if (source != null && target != null && existNode(source) && existNode(target)
				&& this.aristas[getNode(source)][getNode(target)]) {
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
