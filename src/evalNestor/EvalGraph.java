//  NO MODIFICAR NOMBRE DE ESTE PAQUETE, crear (si no existe ya) un paquete nuevo en vuestro proyecto que se llame as�: "evalNestor"
//  y meted en �l esta clase
package evalNestor;

import p2Grafos.Graph;

public class EvalGraph<T> extends Graph<T> {
	
	Alumno alum=new Alumno();

	public EvalGraph(int i) {
		super(i);
	}
	
	public EvalGraph(int i,T initialNodes[], boolean[][] initialEdges, double[][] initialWeights){
		super(i,initialNodes,initialEdges,initialWeights);
	}
	
	public String getNombreFicheroAlumno(){
		return alum.getNombreFicheroAlumno();
	}
}
/*  Tambi�n se debe a�adir este constructor en vuestra clase Graph

	public Graph (int tam, T initialNodes[], boolean[][] initialEdges, double [][] initialWeights) {
		// Llama al constructor original
		this(tam); 
		
		// Pero modifica los atributos con los par�metros para hacer pruebas...
		numNodes = initialNodes.length;
		
		for (int i=0;i<numNodes;i++) {
			// Si el vector de nodos se llama de otra forma (distinto de "nodes"), cambiad el nombre en la l�nea de abajo
			nodes[i]=initialNodes[i];
			for (int j=0;j<numNodes;j++){
				// Si la matriz de aristas se llama de otra forma (distinto de "edges"), cambiad el nombre en la l�nea de abajo
				edges[i][j]=initialEdges[i][j];
				// Si la matriz de pesos se llama de otra forma (distinto de "weights"), cambiad el nombre en la l�nea de abajo
				weights[i][j]=initialWeights[i][j];
			}
		}

	}



*/
