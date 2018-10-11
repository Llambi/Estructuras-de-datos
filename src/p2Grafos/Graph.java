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
	 * Algoritmo de Dijkstra para encontrar el camino de coste mínimo desde
	 * nodoOrigen hasta el resto de nodos
	 * 
	 * @param source
	 *            nodo origen para el calculo de Dijkstra
	 * @return vector D de dijkstra para comprobar funcionamiento
	 */

	public double[] dijkstra(T source) {
		// TODO: Algoritmo dijkstra
		int initNode = getNode(source);
		double[] d = new double[numNodes];
		int[] p = new int[numNodes];
		boolean[] s = new boolean[numNodes];

		if ((initNode == -1) || (source == null) || (existNegativeEdges() == -1)) {
			return null; // No exite el nodo inicial o existen aristas negativas
		}

		initDijkstraEstructures(initNode, d, p, s);

		int nodoPivote = minCost(d, s);

		// Cuerpo del algoritmo
		while (nodoPivote != -1) {
			s[nodoPivote] = true;
			for (int nodoIter = 0; nodoIter < this.numNodes; nodoIter++) {
				if (this.aristas[nodoPivote][nodoIter] == true) {
					if (d[nodoPivote] + this.pesos[nodoPivote][nodoIter] < d[nodoIter]) {
						d[nodoIter] = d[nodoPivote] + this.pesos[nodoPivote][nodoIter];
						p[nodoIter] = nodoPivote;
					}
				}
			}
			nodoPivote = minCost(d, s);
		}

		return d;
	}

	/**
	 * Metodo que crea el grafo, inicializando el vector de nodos, y las matrices de
	 * aristas y pesos a un estado concreto para realizar pruebas.
	 *
	 * @param tam
	 *            Numero de nodos del grafo.
	 * @param initialNodes
	 *            Nodos iniciales.
	 * @param initialEdges
	 *            Aristas iniciales.
	 * @param initialWeights
	 *            Pesos iniciales.
	 */
	public Graph(int tam, T initialNodes[], boolean[][] initialEdges, double[][] initialWeights) {
		// Llama al constructor original
		this(tam);

		// Pero modifica los atributos con los par�metros para hacer pruebas...
		numNodes = initialNodes.length;

		for (int i = 0; i < numNodes; i++) {
			// Si el vector de nodos se llama de otra forma (distinto de "nodes"), cambiad
			// el nombre en la l�nea de abajo
			this.nodos[i] = initialNodes[i];
			for (int j = 0; j < numNodes; j++) {
				// Si la matriz de aristas se llama de otra forma (distinto de "edges"), cambiad
				// el nombre en la l�nea de abajo
				this.aristas[i][j] = initialEdges[i][j];
				// Si la matriz de pesos se llama de otra forma (distinto de "weights"), cambiad
				// el nombre en la l�nea de abajo
				this.pesos[i][j] = initialWeights[i][j];
			}
		}

	}

	/**
	 * Inserta un nuevo nodo que se le pasa como parametro, en el vector de nodos,
	 *
	 * @param node
	 *            el nodo que se quiere insertar
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
	 * Inserta una arista con el peso indicado (> 0) entre dos nodos, uno origen y
	 * otro destino. Devuelve 0 si la inserta y -1 si no lo hace (si existe no la
	 * inserta)
	 *
	 * @param source
	 *            nodo origen
	 * @param target
	 *            nodo destino
	 * @param edgeWeight
	 *            peso de la arista, debe ser > 0
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
	 * @param target
	 *            nodo destino
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
	 * @return si existe o no el nodo
	 */
	public boolean existNode(T node) {
		return getNode(node) != -1;
	}

	/**
	 * Comprueba si existe una arista entre dos nodos que se pasan como parametro
	 *
	 * @param source
	 *            nodo origen
	 * @param target
	 *            nodo destino
	 * @return verdadero o falso si existe o no, si alguno de los nodos no existe
	 *         tambien falso
	 */
	public boolean existEdge(T source, T target) {
		int i=getNode(source);
		int j=getNode(target);

		if(!existNode(source) && !existNode(target))
			return false;

		if(i>=0 && j>=0)
			return(this.aristas[i][j]);
		else
			return false;
	}

	/**
	 * Devuelve el peso de la arista que conecta dos nodos, si no existe la arista,
	 * devuelve -1 (tambien si no existe alguno de los nodos)
	 *
	 * @param source
	 *            Nodo origen
	 * @param target
	 *            Nodo destino
	 * @return El peso de la arista o -1 si no existe
	 */
	public double getEdge(T source, T target) {
		if (existEdge(source, target)) {
			int i = getNode(source);
			int j = getNode(target);
			return this.pesos[i][j];
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

	/**
	 * Busca el nodo con distancia mínima en D al resto de nodos.
	 * 
	 * @param d
	 *            Dector d de costes minimos.
	 * @param visited
	 *            vector con nodos visitados.
	 * @return índice del nodo buscado o -1 si el grafo es no conexo o no quedan
	 *         nodos sin visitar
	 */
	private int minCost(double[] d, boolean[] visited) {
		int nextPos = -1;
		double min = Double.MAX_VALUE;

		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				if (d[i] <= min) {
					nextPos = i;
					min = d[i];
				}
			}
		}

		return nextPos;
	}

	/**
	 * Metodo que inicializa las estructuras necesarias para calcular Dijkstra para
	 * un nodo dado.
	 * 
	 * @param initNode
	 *            Nodo apartir del cual se va a realizar Dijkstra.
	 * @param d
	 *            Matriz de costes minimos.
	 * @param p
	 *            Matriz de paso para llegar a un nodo.
	 */
	private void initDijkstraEstructures(int initNode, double[] d, int[] p, boolean[] s) {
		for (int i = 0; i < numNodes; i++) {

			if (!this.aristas[initNode][i]) {
				d[i] = Double.POSITIVE_INFINITY;
				p[i] = -1;

			} else {
				d[i] = this.pesos[initNode][i];
				p[i] = initNode;
			}
		}
		s[initNode] = true;
		d[initNode] = 0;
	}

	/**
	 * Método privado que comprueba si hay alguna arista con peso negativo, ya que
	 * si es así, tanto el método de floyd como el de dijkstra no pueden funcionar
	 * 
	 * @return devuelve -1 si hay alguna arista con peso negativo, y 0 si todas
	 *         tienen peso positivo
	 */
	private int existNegativeEdges() {
		for (int i = 0; i < this.numNodes; i++) {
			for (int j = 0; j < this.numNodes; j++) {
				if (this.pesos[i][j] < 0) {
					return -1; // peso negativo
				}
			}
		}
		return 0;
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
	 * Obtiene el indice de un nodo en el vector de nodos
	 *
	 * @param node
	 *            el nodo que se busca
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

}
