package p3Arboles;

/**
 * Clase arbol binario
 *
 * @param <T>
 */
public class BSTree<T extends Comparable<T>> {

	protected BSTNode<T> root; // Nodo root del árbol

	// setter de la raiz del arbol BST que se debe copiar si no existe ya
	// Establece el parametro como nodo raiz del arbol
	protected void setRoot(BSTNode<T> nodo) {
		this.root = nodo;
	}

	/**
	 * @return La altura del arbol teniendo en cuenta que un arbol sin nodos tiene
	 *         altura 0
	 */
	public int getHeight() {
		if(root==null){
	        return 0;
	    }
	    else{
	        return findHeight(root);
	    }
	}

	private int findHeight(BSTNode<T> aNode){
	    int heightLeft = 0;
	    int heightRight = 0;
	    if(aNode.left!=null)
	        heightLeft = findHeight(aNode.left);
	    if(aNode.right!=null)
	        heightRight = findHeight(aNode.right);
	    if(heightLeft > heightRight){
	        return heightLeft+1;
	    }
	    else{
	        return heightRight+1;
	    }
	}
	
	/**
	 * Método que añade un nodo al árbol
	 *
	 * @param node
	 *            El objeto comparable que tiene que insertar
	 * @return true cuando lo inserta 0, -1 cuando no lo hace ya existía y cualquier
	 *         otro problema -2.
	 */
	public int addNode(T node) {
		if ((node == null))
			return -2;
		if (searchNode(node) != null)
			return -1;
		root = addNodeRec(root, node);
		return 0;

	}

	/**
	 * Método recursivo que añade el nodo que pasamos como parámetro
	 *
	 * @param node
	 *            El nodo que tiene que insertar
	 * @param theRoot
	 *            Nodo root del árbol
	 * @return Nodo root del árbol
	 */

	private BSTNode<T> addNodeRec(BSTNode<T> theRoot, T node) {
		if (theRoot == null)
			return new BSTNode<T>(node);

		if (node.compareTo(theRoot.getInfo()) < 0)
			theRoot.setLeft(addNodeRec(theRoot.getLeft(), node));

		if (node.compareTo(theRoot.getInfo()) > 0)
			theRoot.setRight(addNodeRec(theRoot.getRight(), node));

		return theRoot;

	}

	/**
	 * Método que busca un nodo
	 *
	 * @param node
	 *            El objeto comparable que se busca
	 * @return El objeto que se busca (completo) si lo encuentra - (null) si no lo
	 *         encuentra
	 */
	public T searchNode(T node) {
		if (node == null)
			return null;

		else if (search(root, node) != null) {

			return (search(root, node));
		}

		return null;
	}

	/**
	 * Método recursivo que busca un nodo
	 *
	 * @param nodo
	 *            El objeto comparable que se busca
	 * @param raiz
	 *            Nodo root del árbol
	 * @return true si lo encuentra, false si no lo encuentra
	 */

	private T search(BSTNode<T> raiz, T nodo) {
		if (raiz == null)
			return null;

		else {
			if (nodo.compareTo(raiz.getInfo()) < 0)
				return search(raiz.getLeft(), nodo);
			else if (nodo.compareTo(raiz.getInfo()) > 0)
				return search(raiz.getRight(), nodo);
			else
				return raiz.getInfo();
		}

	}

	/**
	 * Método que muestra por pantalla el recorrido en pre-orden
	 * (izquierda-derecha). Lo devuelve en un String (separados por tabuladores)
	 */
	public String preOrder() {
		return preOrderRec(root);

	}

	/**
	 * Método que genera un String con el recorrido en pre-orden (izquierda-derecha)
	 *
	 * @param theRoot
	 *            Nodo root del árbol
	 * @return un string con el recorrido pre-orden
	 */
	private String preOrderRec(BSTNode<T> theRoot) {
		if (theRoot == null)
			return "";

		String recorrido = theRoot.toString() + "\t" + preOrderRec(theRoot.getLeft()) + preOrderRec(theRoot.getRight());
		return recorrido;

	}

	/**
	 * Muestra por pantalla el recorrido en post-orden (izquierda-derecha) y lo
	 * devuelve en un String (separados por tabuladores)
	 */
	public String postOrder() {
		return postOrderRec(root);

	}

	/**
	 * Método que genera un String con el recorrido en post-orden
	 * (izquierda-derecha)
	 *
	 * @param theRoot
	 *            Nodo root del árbol
	 * @return un string con el recorrido post-orden
	 */
	private String postOrderRec(BSTNode<T> theRoot) {
		if (theRoot == null)
			return "";

		return postOrderRec(theRoot.getLeft()) + postOrderRec(theRoot.getRight()) + theRoot + "\t";

	}

	/**
	 * Muestra por pantalla el recorrido en in-orden (izquierda-derecha) y lo
	 * devuelve en un String (separados por tabuladores)
	 */
	public String inOrder() {
		return inOrderRec(root);

	}

	/**
	 * Método que genera un String con el recorrido en in-orden (izquierda-derecha)
	 *
	 * @param theRoot
	 *            Nodo root del árbol
	 * @return un string con el recorrido in orden
	 */
	private String inOrderRec(BSTNode<T> theRoot) {
		if (theRoot == null)
			return "";

		return inOrderRec(theRoot.getLeft()) + theRoot + "\t" + inOrderRec(theRoot.getRight());

	}

	/**
	 * Método que borra un nodo
	 *
	 * @param node
	 *            El objeto que se quiere borrar
	 * @return 0 si lo ha borrado, -1 en caso de no existír y -2 en cualquier otro
	 *         caso.
	 */
	public int removeNode(T node) {

		if ((node == null) || (root == null))
			return -2;

		if (this.searchNode(node) == null)
			return -1;

		try {
			root = (remove(root, node));
			return 0;
		} catch (Exception e) {
			return -2;
		}

	}

	/**
	 * Método recursivo que borra un nodo
	 *
	 * @param theRoot
	 *            El objeto comparable que se quiere borrar
	 * @return nodo root del arbol despues del borrado, si lo ha borrado o lanza una
	 *         excepcion si el node es null
	 */
	private BSTNode<T> remove(BSTNode<T> theRoot, T element) {
		if (theRoot == null)
			throw new RuntimeException("element does not exist!");
		else if (element.compareTo(theRoot.getInfo()) < 0)
			theRoot.setLeft(remove(theRoot.getLeft(), element));
		else if (element.compareTo(theRoot.getInfo()) > 0)
			theRoot.setRight(remove(theRoot.getRight(), element));
		else {
			if (theRoot.getLeft() == null)
				return theRoot.getRight();
			else if (theRoot.getRight() == null)
				return theRoot.getLeft();
			else {
				theRoot.setInfo(getMax(theRoot.getLeft()));
				theRoot.setLeft(remove(theRoot.getLeft(), theRoot.getInfo()));
			}
		}
		return theRoot;
	}

	/**
	 * Método recursivo que devuelve el nodo máximo
	 *
	 * @param theRoot
	 *            root del arbol
	 * @return el máximo
	 */
	protected T getMax(BSTNode<T> theRoot) {
		if (theRoot == null)
			return null;

		if (theRoot.getRight() == null) // si el de la derecha que se supone que es el mayor es null es que es
			// el mayor, pues lo devolvemos
			return theRoot.getInfo();

		else
			return getMax(theRoot.getRight()); // si no seguimos buscando por la derecha
	}

	/**
	 * Muestra por pantalla el rocorrido en pre-orden y lo devuelve en un String,
	 * ademas los hijos null se deben sustituir por un subrayado seguido de un
	 * tabulador.
	 * 
	 * @return
	 */
	public String toStringParaPruebas() {
		return preOrderPruebas(root);
	}

	/**
	 * Preorder recursivo para el toString para las pruebas en donde los nodos null
	 * se sustituyen por _
	 * 
	 * @param theRoot
	 *            nodo que se evalua
	 * @return cadena del recorrido Preorder.
	 */
	private String preOrderPruebas(BSTNode<T> theRoot) {
		if (theRoot == null)
			return "_\t";

		String recorrido = theRoot.toString() + "\t" + preOrderPruebas(theRoot.getLeft())
				+ preOrderPruebas(theRoot.getRight());
		return recorrido;

	}

	/**
	 * Devuelve un String con la información del árbol del metodo tumbarArbol
	 */
	public String toString() {
		return tumbarArbol(root, 0);
	}

	/**
	 * Genera un String con el árbol "tumbado" (la raíz a la izquierda y las ramas
	 * hacia la derecha) Es un recorrido InOrden-derecha-izquierda, tabulando para
	 * mostrar los distintos niveles Utiliza el toString de la información
	 * almacenada
	 *
	 * @param p
	 *            La raíz del árbol a mostrar tumbado
	 * @param esp
	 *            El espaciado en número de tabulaciones para indicar la profundidad
	 * @return El String generado
	 */
	protected String tumbarArbol(BSTNode<T> p, int esp) {
		StringBuilder cadena = new StringBuilder();

		if (p != null) {
			cadena.append(tumbarArbol(p.getRight(), esp + 1));
			for (int i = 0; i < esp; i++)
				cadena.append("\t");
			cadena.append(p + "\n");
			cadena.append(tumbarArbol(p.getLeft(), esp + 1));
		}
		return cadena.toString();
	}

}
