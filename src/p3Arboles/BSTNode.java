package p3Arboles;

/**
 * Clase nodo para arboles binarios
 *
 * @param <T> Objeto comparable que contendra el nodo.
 */
public class BSTNode<T extends Comparable<T>> {
    private T info;
    protected BSTNode<T> left;
    BSTNode<T> right;

    /**
     * Constructor de la clase.
     *
     * @param info Un objeto comparable.
     */
    public BSTNode(T info) {
        this.info = info;
        setLeft(null);
        setRight(null);
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj == null || !(obj instanceof BSTNode)) {
			return false;
		}
		return this.getInfo().equals(((BSTNode)obj).getInfo());
    	
    }

    /**
     * Metodo para obtener la informacion del nodo.
     *
     * @return La información que almacena el nodo.
     */
    public T getInfo() {
        return info;
    }

    /**
     * Metodo para modificar la informacion del nodo.
     *
     * @param info La información que se quiere meter en el nodo.
     */
    protected void setInfo(T info) {
        this.info = info;
    }

    /**
     * Metodo para obtener el subarbol izquierdo del nodo.
     *
     * @return El subárbol izquierdo.
     */
    protected BSTNode<T> getLeft() {
        return left;
    }

    /**
     * Metodo para modificar el subarbol izquierdo del nodo.
     *
     * @param x El nodo que se quiere enlazar en el subárbol izquierdo.
     */
    public void setLeft(BSTNode<T> x) {
        this.left = x;
    }

    /**
     * Metodo para obtener el subarbol derecho del nodo.
     *
     * @return El subárbol derecho
     */
    protected BSTNode<T> getRight() {
        return right;
    }

    /**
     * Metodo para modificar el subarbol derecho del nodo.
     *
     * @param x El nodo que se quiere enlazar en el subárbol derecho
     */
    public void setRight(BSTNode<T> x) {
        this.right = x;
    }

    public String toString() {
        return info.toString();
    }
}

