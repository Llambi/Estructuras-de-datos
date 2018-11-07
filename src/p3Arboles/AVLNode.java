package p3Arboles;

/**
 * Clase derivada de BSTNode añadiendo funcionalidad de AVL
 *
 * @param <T>
 */
public class AVLNode<T extends Comparable<T>> extends BSTNode<T> {
	
	/**
	 * Para almacenar la altura del arbol
	 */
	private int height;

	/**
	 * Para almacenar al Factor de balance. Puede no existir y calcularse cada vez a
	 * partir de la altura de los hijos.
	 */
	private int balanceFactor;

	/**
	 * Llama al padre y a�ade la informacion propia
	 * 
	 * @param info
	 *            la informacion que se mete en el nuevo nodo
	 */
	public AVLNode(T info) {
		super(info);
		this.balanceFactor = 0;
		this.height = 1;
	}

	/**
	 * @return devuelve la altura del arbol del cual es raiz el nodo en cuestion
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Devuelve el factor de balance segun equilibrio del arbol del cual es raiz.S
	 * 
	 * @return int con el factor de balance del nodo.
	 */
	public int getBF() {

		this.updateHeight();

		int alturaDerecha = 0;
		int alturaIzquierda = 0;

		if (this.getLeft() != null) // si es null no hay altura
			alturaIzquierda = getLeft().getHeight();

		if (getRight() != null)
			alturaDerecha = getRight().getHeight();

		balanceFactor = (byte) (alturaDerecha - alturaIzquierda);

		return balanceFactor;

	}

	/**
	 * Actualiza la altura del nodo en el arbol utilizando la altura de los hijos,
	 * teniendo en cuenta si no tiene hijo, si tiene 1 a derecha o izquierda, o tiene dos hijos.
	 */
	protected void updateHeight() {
		if (this.getLeft() == null && this.getRight() == null) { // cuando no tiene hijos
			height = 1;
		} else if (this.getRight() == null) { // cuando tiene un hijo por la izquierda la
			height = 1 + this.getLeft().getHeight();
		} else if (this.getLeft() == null) { // cuando tiene un hijo por la derecha la altura
			height = 1 + this.getRight().getHeight();
		} else { // Si tiene ambos, altura = la altura mayor de los dos + 1
			height = 1 + Math.max(getRight().getHeight(), getLeft().getHeight());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTNode#getLeft()
	 */
	public AVLNode<T> getLeft() {
		return (AVLNode<T>) super.getLeft();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTNode#getRight()
	 */
	public AVLNode<T> getRight() {
		return (AVLNode<T>) super.getRight();
	}
	// No se necesitan los setters, valen los heredados

	/*
	 * (non-Javadoc)
	 * 
	 * @see BSTNode#toString() A�ade factor de balance
	 */
	public String toString() {
		return super.toString() + ":FB=" + getBF();
	}
}
