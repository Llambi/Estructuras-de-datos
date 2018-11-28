package p4Hash;

public class ClosedHashTable<T> extends AbstractHash<T> {
	// IMPORTANTE
	// No cambiar el nombre ni visibilidad de los atributos protected

	protected HashNode<T> associativeArray[];

	private int hashSize; // Tama�o de la tabla, debe ser un n�mero primo (B de teor�a)
	protected int numElems; // N�mero de elementos en la tabla en cada momento.

	static final byte LINEAL = 0; // Tipo de exploraci�n en caso de colisi�n, por defecto ser� LINEAL
	static final byte CUADRATICA = 1;
	static final byte DOBLEHASH = 2;

	private double fcUP; // L�mite superior para aumentar tama�o de tabla
	private double fcDOWN; // L�mite inferior para disminuir el tama�o de tabla

	private int exploracion; // Exploraci�n que se realizar� en caso de colisi�n (LINEAL por defecto)

	/**
	 * Constructor para fijar el tamaño al número primo >= que el parámetro
	 * 
	 * @param tam
	 *            tamaño del Hash, si no es un número primo lo ajusta al primo
	 *            superior
	 * @param expl
	 *            tipo de exploración (LINEAL=0, CUADRATICA=1, ...), si inválido
	 *            LINEAL
	 */

	@SuppressWarnings("unchecked")

	public ClosedHashTable(int tam, int expl) {

		hashSize = nextPrimeNumber(tam); // Establece un tamaño valido si tam no es primo
		associativeArray = (HashNode<T>[]) new HashNode[hashSize];
		for (int i = 0; i < hashSize; i++) {
			associativeArray[i] = new HashNode<T>();
		}
		this.fcUP = 0.5;
		this.fcDOWN = 0.16;
		this.exploracion = expl;
		this.numElems = 0;
	}

	/**
	 * Constructor para fijar el tama�o al n�mero primo >= que el par�metro
	 * 
	 * @param tam
	 *            tama�o del Hash, si no es un n�mero primo lo ajusta al primo
	 *            superior
	 * @param fcUP
	 *            Factor de carga l�mite, por encima del cual hay que redispersar
	 *            (directa)
	 * @param fcDOWN
	 *            Factor de carga l�mite, por debajo del cual hay que redispersar
	 *            (inversa)
	 * @param expl
	 *            tipo de exploraci�n (LINEAL=0, CUADRATICA=1, ...), si inv�lido
	 *            LINEAL
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, double fcUP, double fcDOWN, int expl) {

		hashSize = nextPrimeNumber(tam); // Establece un tama�o v�lido si tam no es primo

		associativeArray = (HashNode<T>[]) new HashNode[hashSize];
		for (int i = 0; i < hashSize; i++) {
			associativeArray[i] = new HashNode<T>();
		}
		this.fcUP = fcUP;
		this.fcDOWN = fcDOWN;
		this.exploracion = expl;
		this.numElems = 0;

	}

	protected int fExploracion(int iter, int posicion) {

		int result = -1;

		if (exploracion == LINEAL) {
			result = (posicion + iter) % getSize();
		}

		else if (exploracion == DOBLEHASH) {
			int r = previousPrimeNumber(posicion) - (posicion % getSize());
			result = (posicion + iter * r) % getSize();
		} else
			result = (posicion + iter * iter) % getSize();

		return result;
	}

	@Override
	public int getNumOfElems() {
		return numElems;
	}

	@Override
	public int getSize() {
		return hashSize;
	}

	@Override
	public int add(T elem) {

		if (elem == null) {
			return -2;
		} else if (this.exploracion == LINEAL && this.numElems == this.hashSize) {
			return -1;
		} else if (this.exploracion == CUADRATICA && getLF() > this.fcUP) {
			return -1;
		} else {

			int posicion = fHash(elem); // Siempre devolvera un valor positivo
			int iter = 1;

			while (associativeArray[posicion].getStatus() == HashNode.LLENO) {
				posicion = fExploracion(iter, fHash(elem));
				iter++;
				System.out.println("Se ha producido una colision al añadir: " + elem + ".");
			}

			associativeArray[posicion].setInfo(elem);
			numElems++;

			reDispersion();

			return 0;
		}

	}

	@Override
	public T find(T elem) {

		if (elem != null) {
			int posicion = fHash(elem);
			int iter = 1;

			int con = 0;
			while (posicion >= 0 && con <= getSize() && associativeArray[posicion].getStatus() != HashNode.VACIO) {
				if (associativeArray[posicion].getStatus() == HashNode.LLENO) {
					if (associativeArray[posicion].getInfo().equals(elem)) {
						return associativeArray[posicion].getInfo();
					}
				}

				if (associativeArray[posicion].getStatus() == HashNode.BORRADO)
					System.out.println("Se ha producido una colision al buscar en borrado el " + elem + ".");

				if (associativeArray[posicion].getStatus() == HashNode.LLENO)
					System.out.println("Se ha producido una colision al buscar en lleno el " + elem + ".");

				posicion = fExploracion(iter, fHash(elem));
				iter++;
				con++;
			}

			return null;
		}

		return null;

	}

	@Override
	public int remove(T elem) {

		if (elem == null)
			return -2;

		else if (find(elem) != null) {
			int posicion = fHash(elem);
			int iter = 0;

			while (iter < getSize() && associativeArray[posicion] != null) {
				if (associativeArray[posicion].getStatus() == HashNode.LLENO) {
					if (associativeArray[posicion].getInfo().equals(elem)) {
						associativeArray[posicion].remove();
						numElems--;

						if (numElems != 0)
							inverseReDispersion();
						return 0;
					}
				}

				if (associativeArray[posicion].getStatus() == HashNode.BORRADO)
					System.out.println("Se ha producido una colision al eliminar en borrado el " + elem + ".");

				if (associativeArray[posicion].getStatus() == HashNode.LLENO)
					System.out.println("Se ha producido una colision al eliminar en lleno el " + elem + ".");

				posicion = fExploracion(iter, fHash(elem));
				iter++;
			}
		}

		return -1;
	}

	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		for (int i = 0; i < getSize(); i++) {
			cadena.append(associativeArray[i]);
			cadena.append(";");
		}
		cadena.append("[Size: ");
		cadena.append(getSize());
		cadena.append(" Num.Elems.: ");
		cadena.append(getNumOfElems());
		cadena.append("]");
		return cadena.toString();
	}

	protected double getLF() {

		double num = Double.valueOf(numElems);
		double tam = Double.valueOf(getSize());
		return (num / tam);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean reDispersion() {
		if (getLF() >= fcUP) {

			HashNode<T>[] old = associativeArray;

			int oldSize = hashSize;

			int tam = nextPrimeNumber(getSize() * 2);

			associativeArray = new HashNode[tam];
			numElems = 0;
			hashSize = tam;

			for (int i = 0; i < hashSize; i++)
				associativeArray[i] = new HashNode<T>();

			for (int i = 0; i < oldSize; i++) {
				if (old[i].getStatus() == HashNode.LLENO) {
					add(old[i].getInfo());
				}
			}

			return true;
		}

		return false;

	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean inverseReDispersion() {
		if (getLF() < fcDOWN) {
			HashNode<T>[] old = associativeArray;
			int oldSize = hashSize;

			int tam = previousPrimeNumber(getSize() / 2);

			associativeArray = new HashNode[tam];
			numElems = 0;
			hashSize = tam;

			for (int i = 0; i < hashSize; i++)
				associativeArray[i] = new HashNode<T>();

			for (int i = 0; i < oldSize; i++) {
				if (old[i].getStatus() == HashNode.LLENO)
					add(old[i].getInfo());
			}

			return true;
		}

		return false;

	}
}
