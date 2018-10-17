package p2Grafos;

import java.text.DecimalFormat;

public class Graph<T> {

	protected T[] nodos; // Vector de nodos
	protected boolean[][] edges; // matriz de aristas
	protected double[][] weights; // matriz de pesos
	protected int numNodes; // numero de elementos en un momento dado
	private double[][] aFloyd; // matriz de aristas para Floyd
	private int[][] pFloyd; // matriz de pesos para Floyd

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
			this.edges = new boolean[tam][tam];
			initAristas(tam);
			// inicializada matriz de pesos
			this.weights = new double[tam][tam];
			// inicializado numero de nodos
			this.numNodes = 0;
		}
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
				this.edges[i][j] = initialEdges[i][j];
				// Si la matriz de pesos se llama de otra forma (distinto de "weights"), cambiad
				// el nombre en la l�nea de abajo
				this.weights[i][j] = initialWeights[i][j];
			}
		}

	}

	public Graph(int tam, T initialNodes[], boolean[][] initialEdges, double[][] initialWeights,
			double[][] initialAfloyd, int[][] initialPfloyd) {
		// Llama al constructor anterior de inicializaci�n
		this(tam, initialNodes, initialEdges, initialWeights);

		// Pero modifica los atributos que almacenan las matrices de Floyd con los
		// par�metros para hacer pruebas...

		if (initialAfloyd != null && initialPfloyd != null) {
			// Si la matriz A de floyd se llama de otra forma (distinto de "aFloyd"),
			// cambiad el nombre en la linea de abajo
			aFloyd = initialAfloyd;
			// Si la matriz P de floyd se llama de otra forma (distinto de "pFloyd"),
			// cambiad el nombre en la linea de abajo
			pFloyd = initialPfloyd;
		}

	}

	/**
	 * Aplica el algoritmo de Floyd al grafo
	 * 
	 * @return 0 si lo aplica y genera matrices A y P; y –1 si no lo hace
	 */
	public int floyd() {

		if (this.numNodes == 0 || existNegativeEdges() == -1) {
			return -1;
		}

		this.aFloyd = new double[this.numNodes][this.numNodes];
		this.pFloyd = new int[this.numNodes][this.numNodes];

		// Inicializacion Matrices aFloyd y pFloyd
		initFloyd();

		// Algoritmo Floyd:
		for (int k = 0; k < this.numNodes; k++) {
			for (int i = 0; i < this.numNodes; i++) {
				for (int j = 0; j < this.numNodes; j++) {

					if (this.aFloyd[i][k] + this.aFloyd[k][j] < this.aFloyd[i][j]) {
						this.aFloyd[i][j] = this.aFloyd[i][k] + this.aFloyd[k][j];
						this.pFloyd[i][j] = k;
					}

				}
			}
		}

		return 0;
	}

	/**
	 * Devuelve la matriz A de Floyd, con infinito si no hay camino Si no se ha
	 * invocado a Floyd debe devolver null
	 * 
	 * @return la matriz P de Floyd
	 */
	protected double[][] getAFloyd() {
		return this.aFloyd;
	}

	/**
	 * Devuelve la matriz P de Floyd, con -1 en las posiciones en las que no hay
	 * nodo intermedio Si no se ha invocado a Floyd debe devolver null
	 * 
	 * @return la matriz P de Floyd
	 */
	protected int[][] getPFloyd() {
		return this.pFloyd;
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
				if (this.edges[nodoPivote][nodoIter] == true) {
					if (d[nodoPivote] + this.weights[nodoPivote][nodoIter] < d[nodoIter]) {
						d[nodoIter] = d[nodoPivote] + this.weights[nodoPivote][nodoIter];
						p[nodoIter] = nodoPivote;
					}
				}
			}
			nodoPivote = minCost(d, s);
		}

		return d;
	}

	/**
	 * Devuelve el coste del camino de coste mínimo entre origen y destino según
	 * Floyd Si no están generadas las matrices de Floyd, las genera. Si no puede
	 * obtener el valor por alguna razón, devuelve –1 (OJO que es distinto de
	 * infinito)
	 **/
	public double minCostPath(T origen, T destino) {
		if (origen == null || destino == null || !existNode(origen) || !existNode(destino)) {
			return -1d;
		}
		floyd();
		double minCost = getAFloyd()[getNode(origen)][getNode(destino)];
		return minCost == Double.MAX_VALUE ? -1 : minCost;
	}

	/**
	 * Indica el camino entre los nodos que se le pasan como parámetros de esta
	 * forma:
	 * Origen<tab>(coste0)<tab>Intermedio1<tab>(coste1)….IntermedioN<tab>(costeN)
	 * Destino Si no hay camino: Origen<tab>(Infinity)<tab>Destino Si Origen y
	 * Destino coinciden: Origen
	 * 
	 * @param origen
	 * @param destino
	 * @return El String con lo indicado
	 */
	public String path(T origen, T destino) {
		
		if (origen == null || destino == null || !existNode(origen) || !existNode(destino)) {
			return "";
		}
		
		int i = getNode(origen);
		int j = getNode(destino);
		
		if (origen.equals(destino)) {
			return origen + "coinciden:" + destino;
		} else if (this.pFloyd[i][j] == -1) {
			return origen + "\tcoinciden\t(Infinity)\t" + destino;
		}else {
			DecimalFormat df = new DecimalFormat("#.##");
			String cadena = "";
			
			
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
			destroyFloyd();
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

			this.edges[i][j] = true;
			this.weights[i][j] = edgeWeight;
			destroyFloyd();
			return 0;

		} else if (existEdge(source, target)) {
			int i = getNode(source);
			int j = getNode(target);
			this.edges[i][j] = true;
			this.weights[i][j] = edgeWeight;
			destroyFloyd();
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
					this.edges[j][i] = this.edges[j][this.numNodes];
					this.edges[i][j] = this.edges[this.numNodes][j];
					this.weights[i][j] = this.weights[this.numNodes][j];
					this.weights[j][i] = this.weights[j][this.numNodes];
				}
				for (int j = 0; j < this.numNodes; j++) {
					this.edges[i][i] = this.edges[this.numNodes][this.numNodes];
					this.weights[i][i] = this.weights[this.numNodes][this.numNodes];
				}
			}
			destroyFloyd();
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
			this.edges[i][j] = false;
			this.weights[i][j] = 0.0;
			destroyFloyd();
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
		int i = getNode(source);
		int j = getNode(target);

		if (!existNode(source) || !existNode(target))
			return false;

		if (i >= 0 && j >= 0)
			return (this.edges[i][j]);
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
			return this.weights[i][j];
		}
		return -1d;
	}

	/**
	 * 
	 * Devuelve un String con la informacion del grafo (incluyendo matrices de
	 * Floyd)
	 * 
	 */
	public String toString() {

		DecimalFormat df = new DecimalFormat("#.##");
		String cadena = "";

		cadena += "NODES\n";

		for (int i = 0; i < numNodes; i++) {
			cadena += this.nodos[i].toString() + "\t";
		}

		cadena += "\n\nEDGES\n";

		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {
				if (edges[i][j])
					cadena += "T\t";
				else
					cadena += "F\t";
			}
			cadena += "\n";
		}

		cadena += "\nWEIGHTS\n";

		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < numNodes; j++) {
				cadena += (edges[i][j] ? df.format(weights[i][j]) : "-") + "\t";
			}
			cadena += "\n";
		}

		double[][] aFloyd = getAFloyd();

		if (aFloyd != null) {
			cadena += "\nAFloyd\n";
			for (int i = 0; i < numNodes; i++) {
				for (int j = 0; j < numNodes; j++) {
					cadena += df.format(aFloyd[i][j]) + "\t";
				}
				cadena += "\n";
			}
		}

		int[][] pFloyd = getPFloyd();

		if (pFloyd != null) {
			cadena += "\nPFloyd\n";
			for (int i = 0; i < numNodes; i++) {
				for (int j = 0; j < numNodes; j++) {
					cadena += (pFloyd[i][j] >= 0 ? df.format(pFloyd[i][j]) : "-") + "\t";
				}
				cadena += "\n";
			}
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

			if (!this.edges[initNode][i]) {
				d[i] = Double.POSITIVE_INFINITY;
				p[i] = -1;

			} else {
				d[i] = this.weights[initNode][i];
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
				if (this.weights[i][j] < 0) {
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
				this.edges[i][j] = false;
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
		if (this.nodos == null)
			return -1;
		for (int i = 0; i < this.numNodes; i++) {
			if (this.nodos[i].equals(node))
				return i;
		}
		return -1;
	}

}
