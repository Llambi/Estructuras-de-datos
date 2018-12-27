package p4Hash;

public abstract class AbstractHash<T> {

	/**
	 * Devuelve el número de elementos que contiene la tabla Hash en el momento de
	 * la invocación
	 * 
	 * @return El número de elementos.
	 */

	abstract public int getNumOfElems();

	/**
	 * Devuelve el tamaño de la tabla Hash
	 * 
	 * @return El tamaño de la tabla, conveniente que sea un número primo
	 */

	abstract public int getSize();

	/**
	 * Inserta un nuevo elemento en la tabla hash
	 * 
	 * @param elem
	 *            Elemento que se inserta
	 * @return 0 si lo ha insertado o negativo en caso contrario (-1 si no encuentra
	 *         sitio y -2 si el elemento no es válido)
	 */

	abstract public int add(T elem);

	/**
	 * Busca y devuelve un elemento de la tabla hash
	 * 
	 * @param elem
	 *            La clave que se busca,
	 * @return El elemento encontrado si lo encuentra o null en caso contrario
	 */

	abstract public T find(T elem);

	/**
	 * Borra un elemento que se encuentra en la tabla hash
	 * 
	 * @param elem
	 *            elemento para borrar
	 * @return 0 si lo ha borrado o negativo en caso contrario (-1 si no existe, y
	 *         –2 en otros casos)
	 */

	abstract public int remove(T elem);

	/**
	 * Realiza una redispersión (aumentando el tamaño) al número primo mayor que el
	 * doble del tamaño actual, y recolocando los elementos
	 * 
	 * @return true si la realiza, false en caso contrario
	 */
	abstract protected boolean reDispersion();

	/**
	 * Realiza una redispersión inversa (disminuyendo el tamaño) al número primo
	 * menor que la mitad del tamaño actual, y recolocando los elementos
	 * 
	 * @return true si la realiza, false en caso contrario
	 */
	abstract protected boolean inverseReDispersion();

	/**
	 * Convierte la tabla a una String que se pueda mostrar de forma "visible"
	 * 
	 * @return el String con la información de la tabla hash
	 */
	abstract public String toString();

	/**
	 * Calcula el resultado de aplicar la función hash sobre el parámetro (versión
	 * 2017-18) Utiliza hashCode() Convierte posibles negativos a positivos
	 * 
	 * @return el resultado correspondiente
	 * 
	 */
	protected int fHash(T elem) {
		int primo = getSize();
		return (elem.hashCode() % primo + primo) % primo;
	}

	/**
	 * Calcula si un número positivo es un número primo, los negativos devuelve que
	 * no
	 * 
	 * @param n
	 *            El número que queremos comprobar
	 * @return true si es primo, false en caso contrario
	 * 
	 */
	protected boolean isPositivePrime(int n) {
		if (n < 2 || (n > 2 && n % 2 == 0))
			return false;
		if (n <= 7)
			return true;
		for (int i = 3; i * i <= n; i += 2) // impares
			if (n % i == 0)
				return false; // no es primo
		return true;
	}

	/**
	 * @param n
	 *            El número de partida
	 * @return Si el numero es primo, el mismo numero; y sino, el primer primo
	 *         encontrado MAYOR que el numero de partida
	 */
	protected int nextPrimeNumber(int n) {
		if (n <= 3)
			return 3; // No permite primos menores de 3
		for (; !isPositivePrime(n); n++)
			;
		return n;
	}

	/**
	 * @param n
	 *            El número de partida
	 * @return Si el numero es primo, el mismo numero; y sino, el primer primo
	 *         encontrado MENOR que el numero de partida
	 */
	protected int previousPrimeNumber(int n) {
		if (n <= 3) // No permite primos menores de 3
			return 3;
		for (; !isPositivePrime(n); n--)
			;
		return n;
	}
}