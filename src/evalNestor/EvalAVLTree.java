//  NO MODIFICAR NOMBRE DE ESTE PAQUETE, crear un paquete nuevo (si no existe ya) en vuestro proyecto que se llame as�: "evalNestor"
//  y meted en �l esta clase
package evalNestor;


import p3Arboles.AVLNode;
import p3Arboles.AVLTree;

public class EvalAVLTree <T extends Comparable<T>> extends AVLTree <T> {
	private int minimoNumeroNodos=0;

	@SuppressWarnings("hiding")
	protected class EvalAVLNode <T extends Comparable<T>> extends AVLNode <T> {
		public EvalAVLNode (T node) {
			super(node);
		}

		// Por si no funciona el vuestro.
		public int getHeight(){
			return height;
		}
		
		public void setHeight(int h) {
			height=h;
		}

		//
		public void setBF(byte bf) {
			balanceFactor=bf;
		}
		
	}


	public EvalAVLTree() {
		super();
		if (minimoNumeroNodos>0) //  Para los examenes
			System.err.println("Limitado el namero minimo de nodos en remove a: "+minimoNumeroNodos);
	}
	
	public EvalAVLTree(T[] serie)  {
		super();
		try {
			setRoot(reconstruirArbolAVL(0, serie));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	// Necesita que se cambie la visibilidad del atributo height a protected
	private AVLNode<T> reconstruirArbolAVL(int i, T[] serie) throws Exception {
		if (i<serie.length && serie[i]!=null) {
			EvalAVLNode<T> r = new EvalAVLNode<T>(serie[i]);
			r.setLeft(reconstruirArbolAVL(i*2+1, serie));
			r.setRight(reconstruirArbolAVL(i*2+2, serie));
			int altI=r.getLeft()!=null?r.getLeft().getHeight():r.getHeight()-1;// Cojo la altura del nodo reci�n creado como se la hayas puesto si no tiene hijos por la rama
			int altR=r.getRight()!=null?r.getRight().getHeight():r.getHeight()-1; // Idem y adem�s no depende de vuestro getHeight()
			r.setHeight((altI>altR?altI:altR)+1);
			r.setBF((byte) (altR-altI));// Comentar esto si no es un atributo el factor de balance y cambiarlo a protected si lo es
			if (Math.abs(altR-altI)>1) throw new Exception("El �rbol que se intentaba crear NO es un AVL: "+serializar(serie));
			return r;
		}
		return null;
	}
	
	private String serializar(T[] serie){
		StringBuilder cad=new StringBuilder();
		for (T i:serie) {
			cad.append((i==null?"null":i.toString())+"\t");
		}
		return cad.toString();
	}
	
	public int removeNode(T nodo){
		int borrado = super.removeNode(nodo);
		if (borrado==0) {
			int numNodos=getNumNodes();
			if (numNodos<minimoNumeroNodos)
					System.err.println("\n��������� Menos de "+minimoNumeroNodos+" nodos al borrar el "+ nodo+" !!!!!!!!!");
//			else
//				System.err.println("N�mero de nodos tras borrado: "+numNodos);
		}
		return borrado;
	}
	
	public AVLNode<T> getRoot(){
		return super.getRoot();
	}
	
//  
	 public int getNumNodes() {

		 return getNumNodes(getRoot());
	 }

	public int getNumNodes(AVLNode<T> rootNode) {
		if (rootNode==null)
			return 0;
		return 1 + getNumNodes(rootNode.getLeft()) + getNumNodes(rootNode.getRight());
	}


}
