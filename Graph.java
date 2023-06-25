package bfsWithWeights;

import java.util.Comparator;
import java.util.HashMap;

public class Graph<I, V> {
	private Comparator edgeComparator;
	public Graph(Comparator edgeComparatorPar) {
		edgeComparator = edgeComparatorPar;
	}	
	
	private HashMap<I, Vertex<I, V>> vertexes = new HashMap<>();
	
	public AdjacentVertexGetter adjacentVertexGetter = new AdjacentVertexGetter();
	public class AdjacentVertexGetter{
		public HashMap<I, SumAndEdges> getAdjacentVertexesOf(I id){
			return vertexes.get(id).adjacentVertexes;
		}
	}
	
	private Totals totals = new Totals(); 
	public class Totals{
		public int numOfEdges = 0;
		public int sumOfWeights = 0;
	}
	
	public int getNumOfVertexes() {
		return vertexes.size();
	}
	
	public void createAndAddVertex(I id, V value) {
		vertexes.put(id, new Vertex<I, V>(id, value, totals, edgeComparator));
	}
	
	public void removeVertex(I id) {
		/*Removes the vertex from the vertexes that have an
		 * edge directed to it, and updates the graph's data.*/
		vertexes.forEach((i, v) -> {
			if(v.adjacentVertexes.containsKey(id)) {
				totals.sumOfWeights -= v.adjacentVertexes.get(id).sumOfWeights;
				totals.numOfEdges -= vertexes.get(id).getNumOfEdges();
				v.adjacentVertexes.remove(id);
			}
		});

		//Removes the vertex from the graph and updates the graph's data.
		totals.sumOfWeights -= vertexes.get(id).getSumOfWeights();
		totals.numOfEdges -= vertexes.get(id).getNumOfEdges();
		vertexes.remove(id);
	}
	
	public void addEdge(I idOfV1, I idOfV2, int weight) throws InvalidWeightException{
		vertexes.get(idOfV1).addEdge(idOfV2, new Edge(weight));
	}
	
	public boolean isThereAnEdgeTo(I idOfV1, I idOfV2) {
		return vertexes.get(idOfV1).isThereAnEdgeTo(idOfV2);
	}
	
	public void removeEdge(I idOfV1, I idOfV2, Edge edge) {
		vertexes.get(idOfV1).removeEdge(idOfV2, edge);
	}
	
	public void removeAllEdgesTo(I idOfV1, I idOfV2) {
		vertexes.get(idOfV1).removeAllEdgesTo(idOfV2);
	}
}















