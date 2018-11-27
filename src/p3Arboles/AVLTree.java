package p3Arboles;

/**
 * Clase derivada de BSTree añadiendo funcionalidad de AVL
 * 
 */
public class AVLTree<T extends Comparable<T>> extends BSTree<T> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTree#add(java.lang.Comparable) Redefine inserci�n para funcionalidad
	 * AVL
	 */
	@Override
	public int addNode(T info) {

		if (info == null)
			return -2;

		try {
			this.root = addRec((AVLNode<T>) this.root, info);
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTree#add(java.lang.Comparable) Redefine eliminacion para funcionalidad
	 * AVL
	 */
	@Override
	public int removeNode(T info) {

		if (info == null)
			return -2;
		if (root == null)
			return -2;
		if (super.searchNode(info) == null)
			return -1;

		try {
			this.root = (removeRec((AVLNode<T>) root, info));
			return 0;
		} catch (Exception e) {
			return -2;
		}
	}

	/**
	 * Metodo recursivo para añadir el nodo donde corresponda.
	 * 
	 * @param thisRoot
	 *            Raiz del subarbol que estamos comprobando
	 * @param node
	 *            Nodo que se quiere añadir
	 * @return Nuevo arbol es estado correcto (balanceado)
	 */
	private AVLNode<T> addRec(AVLNode<T> thisRoot, T node) {

		if (thisRoot == null) {
			return new AVLNode<T>(node);
		}
		if (node.compareTo(thisRoot.getInfo()) == 0) {
			throw new RuntimeException("El nodo ya existe");
		}
		if (node.compareTo(thisRoot.getInfo()) < 0) {
			thisRoot.setLeft(addRec(thisRoot.getLeft(), node));
		} else {
			thisRoot.setRight(addRec(thisRoot.getRight(), node));
		}
		return (updateAndBalanceIfNecesary(thisRoot));
	}

	/**
	 * Metodo que comprueba si un subarbol esta balanceado y en el caso de que no lo
	 * este balancea su subarbol.
	 * 
	 * @param nodo
	 *            el arbol que se quiere actualizar Height, BF y balancear si fuese
	 *            necesario
	 * @return la raiz del arbol por si ha cambiado
	 */
	private AVLNode<T> updateAndBalanceIfNecesary(AVLNode<T> nodo) {

		nodo.updateHeight();

		if (nodo.getBF() == -2) {
			if (nodo.getLeft().getBF() == -1) {
				nodo = singleLeftRotation(nodo);
				System.out.println("Rotacion Simple Izquierda [-2][-1]");
			} else if (nodo.getLeft().getBF() == 0) {
				nodo = singleLeftRotation(nodo);
				System.out.println("Rotacion Simple Izquierda [-2][0]");
			} else {
				nodo = doubleLeftRotation(nodo);
				System.out.println("Rotacion Doble Izquierda [-2][1]");
			}
		} else if (nodo.getBF() == 2) {
			if (nodo.getRight().getBF() == 1) {
				nodo = singleRightRotation(nodo);
				System.out.println("Rotacion Simple Derecha [2][1]");
			} else if (nodo.getRight().getBF() == 0) {
				nodo = singleRightRotation(nodo);
				System.out.println("Rotacion Simple Derecha [2][0]");
			} else {
				nodo = doubleRightRotation(nodo);
				System.out.println("Rotacion Doble Derecha [2][-1]");
			}
		}

		nodo.updateHeight();

		return nodo;

	}

	/**
	 * Metodo de rotacion simple a la izquierda
	 * 
	 * @param thisRoot
	 *            la ra�z del �rbol a balancear con rotaci�n simple
	 * @return la ra�z del nuevo �rbol que ha cambiado
	 */
	private AVLNode<T> singleLeftRotation(AVLNode<T> thisRoot) {

		AVLNode<T> referencia = thisRoot.getLeft();
		thisRoot.setLeft(referencia.getRight());
		referencia.setRight(thisRoot);
		thisRoot.updateHeight();
		referencia.updateHeight();
		return referencia;
	}

	/**
	 * Metodo de rotacion doble a la izquierda
	 * 
	 * @param nodo
	 *            la ra�z del �rbol a balancear con rotaci�n doble
	 * @return la ra�z del nuevo �rbol que ha cambiado
	 */

	private AVLNode<T> doubleLeftRotation(AVLNode<T> nodo) {
		nodo.setLeft(singleRightRotation(nodo.getLeft()));
		return singleLeftRotation(nodo);

	}

	/**
	 * Metodo de rotacion simple a la derecha
	 * 
	 * @param nodo
	 *            la ra�z del �rbol a balancear con rotaci�n simple
	 * @return la ra�z del nuevo �rbol que ha cambiado
	 */
	private AVLNode<T> singleRightRotation(AVLNode<T> nodo) {

		AVLNode<T> referencia = nodo.getRight();
		nodo.setRight(referencia.getLeft());
		referencia.setLeft(nodo);
		nodo.updateHeight();
		nodo = referencia;
		return nodo;
	}

	/**
	 * Metodo de rotacion doble a derecha
	 * 
	 * @param nodo
	 *            la ra�z del �rbol a balancear con rotaci�n doble
	 * @return la ra�z del nuevo �rbol que ha cambiado
	 */
	private AVLNode<T> doubleRightRotation(AVLNode<T> nodo) {
		nodo.setRight(singleLeftRotation(nodo.getRight()));
		return singleRightRotation(nodo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTree#removeNode(java.lang.Comparable) Redefinición para incluir
	 * características AVL
	 */
	private AVLNode<T> removeRec(AVLNode<T> theRoot, T node) {
		if (theRoot == null)
			throw new RuntimeException("El elemento no puede ser null");

		else if (node.compareTo(theRoot.getInfo()) < 0)
			theRoot.setLeft(removeRec(theRoot.getLeft(), node));

		else if (node.compareTo(theRoot.getInfo()) > 0)
			theRoot.setRight(removeRec(theRoot.getRight(), node));

		else if (theRoot.getLeft() == null)

			return theRoot.getRight();
		else if (theRoot.getRight() == null)
			return theRoot.getLeft();

		else {
			theRoot.setInfo(super.getMax(theRoot.getLeft()));
			theRoot.setLeft(removeRec(theRoot.getLeft(), theRoot.getInfo()));
		}

		return (updateAndBalanceIfNecesary(theRoot));

	}

	/**
	 * Metodo que devulve el AVLTree
	 * 
	 * @return root del arbol.
	 */
	public AVLNode<T> getRoot() {
		return (AVLNode<T>) root;
	}

}
