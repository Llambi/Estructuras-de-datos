package p3Arboles;

/**
 * Interfaz para una cola de prioridad.
 * 
 * @author Nestor
 * @version 2018-19
 */
public interface EDPriorityQueue<T extends Comparable<T>> {

	/**
	 * Metodo para añadir un elemento dado
	 * 
	 * @param elemento
	 *            Elemento que se quiere insertar en la cola
	 * @return 0 si consigue insertarlo, negativo en caso contrario ((-1 si no cabe,
	 *         -2 otras causas)
	 */
	public int add(T elemento);

	/**
	 * Devuelve y elimina el elemento con más prioridad
	 * 
	 * @return El elemento con mayor prioridad, o null si no hay elementos
	 */
	public T getTop();

	/**
	 * Borra un elemento de la cola
	 * 
	 * @param elemento
	 *            El elemento que se quiere eliminar de la cola
	 * 
	 * @return 0 si estaba y lo elimina, negativo en caso contrario (-1 si no
	 *         estaba, -2 otras causas)
	 */
	public int remove(T elemento);

	/**
	 * @return true si no hay ningun elemento
	 */
	public boolean isEmpty();

	/**
	 * Elimina todos los elementos de la cola
	 */
	public void clear();

	/**
	 * Devuelve una cadena con la informacion de los elementos que contiene el
	 * monticulo en forma visible (recomendado inorden-derecha-izquierda tabulado)
	 *
	 * @return Un string con la cola de alguna forma visible
	 */
	public String toString();
}