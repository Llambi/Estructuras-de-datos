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
     * @param tam Numero de nodos del grafo.
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
     * @param tam            Numero de nodos del grafo.
     * @param initialNodes   Nodos iniciales.
     * @param initialEdges   Aristas iniciales.
     * @param initialWeights Pesos iniciales.
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
     * @param source nodo origen para el calculo de Dijkstra
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
     *
     * @param origen  Nodo de origen
     * @param destino Nodo de destino
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
     * Destino Si no hay camino: Origen<tab>(Infinity)<tab>Destino
     * Si Origen y Destino coinciden: Origen
     *
     * @param origen  Nodo de origen
     * @param destino Nodo de destino
     * @return El String con lo indicado
     */
    public String path(T origen, T destino) {
        if ((getNode(origen) == -1) || (getNode(destino) == -1)) {
            return "";
        }
        if (this.aFloyd == null) {
            floyd();
        }
        if (origen.equals(destino)) {
            return origen.toString() + "\t";
        } else if (this.aFloyd[getNode(origen)][getNode(destino)] == Double.POSITIVE_INFINITY) {
            return origen.toString() + "\t" + "(" + Double.POSITIVE_INFINITY + ")" + "\t" + destino.toString() + "\t";
        }
        return (origen.toString() + recursivePath(getNode(origen), getNode(destino))) + "\t";
    }

    /**
     * Metodo auxiliar recursivo para el calculo del camino entre dos nodos, que ademas da el coste entre estos
     *
     * @param i Indice del nodo de inicio
     * @param j Indice del nodo de destino
     * @return String con el camino entre un nodo y otro y el coste de salto entre ellos
     */
    private String recursivePath(int i, int j) {

        int k = this.pFloyd[i][j];
        String cadena = "";

        if (k != -1) { // mientras haya un intermedio se llama recursivamente
            cadena += (recursivePath(i, k) + (recursivePath(k, j)));
        } else {
            cadena += ("\t" + "(" + this.aFloyd[i][j] + ")" + "\t" + this.nodos[j].toString());
        }

        return cadena;
    }


    /**
     * Lanza el recorrido en profundidad de un grafo desde un nodo determinado,
     * No necesariamente recorre todos los nodos.
     * Alrecorrer cada nodo añade el toString del nodo
     * Se puede usar un metodo privado recursivo...
     * Que recorran vecinos empezando por el principio del vector de nodos(antes indices bajos)
     *
     * @param nodo El nodo por el que se quiere empezar el recorrido en profundidad.
     * @return Un String con el toString de los nodos del recorrido separados por tabulaciones. Si no existe el nodo devuelve un String vacio.
     */
    public String recorridoProfundidad(T nodo) {

        boolean[] visited = new boolean[numNodes];

        if ((nodo == null) || (getNode(nodo) == -1)) {
            return "";
        }
        if (this.aFloyd == null) {
            floyd();
        }

        return recursiveDF(getNode(nodo), visited);

    }

    /**
     * Metedo recursivo auxiliar para el calculo del recorrido en profundidad del grafo desde un nodo dado.
     *
     * @param i         Indice del nodo de inicio para el recorrido en profundidad.
     * @param visitados Array con los nodos ya visitados por el algoritmo
     * @return Cadena con los toString de los nodos que recorre en el orden que lo hace.
     */
    private String recursiveDF(int i, boolean[] visitados) {

        visitados[i] = true;

        String df = this.nodos[i].toString() + "\t";

        for (int j = 0; j < numNodes; j++) {
            if ((edges[i][j]) && (!visitados[j]))
                df += recursiveDF(j, visitados);
        }
        return df;
    }

    /**
     * Inserta un nuevo nodo que se le pasa como parametro, en el vector de nodos,
     *
     * @param node el nodo que se quiere insertar
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
     * @param source     nodo origen
     * @param target     nodo destino
     * @param edgeWeight peso de la arista, debe ser > 0
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
     * @param node que se quiere borrar
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
     * @param source nodo origen
     * @param target nodo destino
     * @return 0 si la borra y -1 si no lo hace (tambien si no existe alguno de sus
     * nodos)
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
     * @param node Nodo que se quiere consultar
     * @return si existe o no el nodo
     */
    public boolean existNode(T node) {
        return getNode(node) != -1;
    }

    /**
     * Comprueba si existe una arista entre dos nodos que se pasan como parametro
     *
     * @param source nodo origen
     * @param target nodo destino
     * @return verdadero o falso si existe o no, si alguno de los nodos no existe
     * tambien falso
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
     * @param source Nodo origen
     * @param target Nodo destino
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
     * Metodo que comprueba si un nodo es nodo fuente
     *
     * @param node, el nodo
     * @return true si el nodo es fuente y false en caso contrario
     */
    public boolean esNodoFuente(T node) {    //grado Salida > 0 y grado Entrada == 0
        boolean flag = false;
        int i = getNode(node);

        for (int v = 0; v < this.nodos.length; v++) {
            // si existe al menos una arista desde node hasta X
            // y si no existe al menos una arista desde X hasta el nodo
            if (edges[i][v] && !edges[v][i])
                flag = true;
        }

        return flag;
    }

    /**
     * Metodo para comprobar si un nodo es sumidero
     * Hacemos uso del método esNodoFuente puesto que ahora si grado Salida == 0 y grado entrada >0
     * es sumidero
     *
     * @param node
     * @return
     */
    public boolean esNodoSumidero(T node) {
        if (!esNodoFuente(node))
            return true;
        else
            return false;

    }

    /**
     * Metodo que comprueba si un nodo es aislado
     * Comprueba que: grado salida = grado entrada = 0
     *
     * @param node
     * @return
     */
    public boolean esNodoAislado(T node) {
        boolean flag = false;
        int i = getNode(node);

        for (int v = 0; v < this.nodos.length; v++) {
            // no pueden existir aristas que salgan o entren del nodo
            if (!edges[i][v] && !edges[v][i])
                flag = true;
        }

        return flag;
    }

    //Inaccesibles de un nodo

    /**
     * Método que calcula todos los nodos inaccesibles desde un nodo
     *
     * @param node, el nodo desde el que parte
     * @return un vector de nodos inaccesibles
     */
    public int[] inaccesiblesDesdeUnNodo(T node) {
        if ((node == null || getNode(node) == -1) && (pFloyd == null || pFloyd.length == 0)) return null;

        int i = getNode(node);
        floyd();
        int[] res = new int[numNodes];
        if (node == null || i == -1) return null;

        for (int j = 0; j < numNodes; j++) {
            if (!existEdge(this.nodos[i], this.nodos[j]))
                res[j] = j;

        }

        return res;
    }

    /**
     * Método que calcula todos los accesibles desde un nodo siempre y con grado salida == 0
     *
     * @return devuelva un vector de nodos accesibles y con grado de salida igual a 0
     */
    public int[] accesiblesDesdeUnNodo(T node) {
        if ((node == null || getNode(node) == -1) && (pFloyd == null || pFloyd.length == 0)) return null;

        int i = getNode(node);
        floyd();
        int[] res = new int[numNodes];

        for (int j = 0; j < numNodes; j++) {
            if (existEdge(this.nodos[i], this.nodos[j]) && gradoSalida(node) == 0)
                res[j] = j;

        }

        return res;

    }

    /**
     * Devuelve la excentricidad del nodo que se le pasa por parámetro
     * La excentricidad de un nodo es el mayor de los costes mínimos que tienen a ese nodo como destino, desde cualquier otro
     *
     * @return Si el nodo no existe devuelve -1
     */
    private double excentricidad(T nodo) {
        int j = getNode(nodo);

        //Comprueba si el nodo existe
        if (j >= 0 && nodo != null) {
            //Calcula la matriz A de floyd
            floyd();
            double excentricidad = 0.0;

            //Comprueba cual es el peso máximo en la columna del nodo pasado por parámetro
            for (int i = 0; i < numNodes; i++) {
                if (aFloyd[i][j] > excentricidad) {
                    excentricidad = aFloyd[i][j];
                }
            }

            return excentricidad;
        } else {
            return -1.0;
        }
    }

    /**
     * Metodo que devuelve la excentricidad más alta del grafo,
     * si hay varios debe devolver el que este en una posición más baja en el vector de nodos
     *
     * @return Nodo mas excentrico del grafo
     */
    public T getMoreEccentricNode() {
        if (numNodes == 0) return null; // el grafo esta vacio
        if (numNodes == 1) return this.nodos[0];

        double max = 0;
        T node = null;

        for (int i = numNodes - 1; i >= 0; i--) {
            if (excentricidad(this.nodos[i]) >= max) {
                System.out.println(i);
                max = excentricidad(this.nodos[i]);
                node = this.nodos[i];
            }
        }

        return node;
    }


    /**
     * Método que calcula el grado de salida de un nodo
     *
     * @param nodo , el nodo
     * @return el grado de salida
     */
    public int gradoSalida(T nodo) {
        int counter = 0;
        if (getNode(nodo) == -1 || nodo == null) return -1;

        for (int i = 0; i < numNodes; i++) {
            if (existEdge(nodo, this.nodos[i])) {
                counter += 1;
            }
        }

        return counter;
    }

    /**
     * Método que calcula el grado de entrada de un nodo
     *
     * @param nodo , el nodo
     * @return el grado de entrada
     */
    public int gradoEntrada(T nodo) {
        int counter = 0;
        if (getNode(nodo) == -1 || nodo == null) return -1;

        for (int i = 0; i < numNodes; i++) {
            if (existEdge(this.nodos[i], nodo))
                counter += 1;
        }

        return counter;
    }

    /**
     * Devuelve un String con la informacion del grafo (incluyendo matrices de
     * Floyd)
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
     * Metodo que inicializa las matrices de A y P para Floyd.
     */
    private void initFloyd() {
        for (int i = 0; i < this.numNodes; i++) {
            for (int j = 0; j < this.numNodes; j++) {
                if (i == j) {
                    this.aFloyd[i][j] = 0; // Diagonal a 0
                    this.pFloyd[i][j] = -1;

                } else {
                    if (edges[i][j]) {
                        this.aFloyd[i][j] = weights[i][j];
                    } else {
                        this.aFloyd[i][j] = Double.POSITIVE_INFINITY;
                    }
                    this.pFloyd[i][j] = -1;
                }
            }
        }
    }

    /**
     * Metodo para eliminar las matrices A y P de Floyd cuando el grafo sea
     * modificado.
     */
    private void destroyFloyd() {
        this.aFloyd = null;
        this.pFloyd = null;
    }

    /**
     * Busca el nodo con distancia mínima en D al resto de nodos.
     *
     * @param d       Dector d de costes minimos.
     * @param visited vector con nodos visitados.
     * @return índice del nodo buscado o -1 si el grafo es no conexo o no quedan
     * nodos sin visitar
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
     * @param initNode Nodo apartir del cual se va a realizar Dijkstra.
     * @param d        Matriz de costes minimos.
     * @param p        Matriz de paso para llegar a un nodo.
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
     * tienen peso positivo
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
     * @param tam tamaño de la matriz cuadrada
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
     * @param node el nodo que se busca
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
