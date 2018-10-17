//  NO MODIFICAR NOMBRE DE ESTE PAQUETE, crear (si no existe ya) un paquete nuevo en vuestro proyecto que se llame as�: "evalNestor"
//  y meted en �l esta clase
package evalNestor;

import p2Grafos.Graph;

public class EvalGraph<T> extends Graph<T> {
	
	Alumno alum=new Alumno();

	public EvalGraph(int i) {
		super(i);
	}
	
	public EvalGraph(int i,T initialNodes[], boolean[][] initialEdges, double [][] initialWeights){
		super(i,initialNodes,initialEdges,initialWeights);
	}
	
	public EvalGraph(int i,T initialNodes[], boolean[][] initialEdges, double [][] initialWeights, double [][] initialAfloyd, int [][] initialPfloyd){
		super(i,initialNodes,initialEdges,initialWeights,initialAfloyd,initialPfloyd);
	}

	public String getNombreFicheroAlumno(){
		return alum.getNombreFicheroAlumno();
	}
}
/*  Tambi�n se debe a�adir este constructor en vuestra clase Graph

	public Graph (int tam, T initialNodes[], boolean[][] initialEdges, double [][] initialWeights, double [][] initialAfloyd, int [][] initialPfloyd) {
		// Llama al constructor anterior de inicializaci�n
		this(tam, initialNodes,initialEdges,initialWeights); 
		
		// Pero modifica los atributos que almacenan las matrices de Floyd con los par�metros para hacer pruebas...
		
		if (initialAfloyd!=null && initialPfloyd!=null){
			// Si la matriz A de floyd se llama de otra forma (distinto de "aFloyd"), cambiad el nombre en la linea de abajo
			aFloyd=initialAfloyd;
			// Si la matriz P de floyd se llama de otra forma (distinto de "pFloyd"), cambiad el nombre en la linea de abajo
			pFloyd=initialPfloyd;
		}

	}



*/
