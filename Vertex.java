package bfsWithWeights;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import bfsWithWeights.Graph.Totals;

public class Vertex<I, V> {
	private I id;
	private V value;
	private Totals totals;
	private Comparator edgeComparator;
	/*The keys are vertexes that this Vertex has an outgoing edge to. The
	 * value of each key is the edges to the key.*/
	public HashMap<I, SumAndEdges> adjacentVertexes = new HashMap<>();
	//The number of outgoing edges from this Vertex.
	private int numOfEdges = 0;
	//The sum of the weights of outgoing edges from this Vertex.
	private int sumOfWeights = 0;
	
	
	public Vertex(I idPar, V valuePar, Totals totalsPar, Comparator edgeComparatorPar) {
		id = idPar;
		value = valuePar;
		totals = totalsPar;
		edgeComparator = edgeComparatorPar;
	}
	
	
	/*Users who don't want parallel edges will use this method before
	 * adding an edge.*/
	public boolean isThereAnEdgeTo(I idOfAdjacentVertex) {
		return adjacentVertexes.get(idOfAdjacentVertex).edges.isEmpty();
	}
	
	
	public void addEdge(I idOfAdjacentVertex, Edge edge) {
		boolean isANewAdjacentVertex = !(adjacentVertexes.containsKey(idOfAdjacentVertex));
		if(isANewAdjacentVertex) {
			SumAndEdges newSumAndEdges = new SumAndEdges();
			newSumAndEdges.edges.add(edge);
			newSumAndEdges.sumOfWeights = edge.getWeight();
			adjacentVertexes.put(idOfAdjacentVertex, newSumAndEdges);
		}
		else {
			adjacentVertexes.get(idOfAdjacentVertex).sumOfWeights += edge.getWeight();
			adjacentVertexes.get(idOfAdjacentVertex).edges.add(edge);
		}
		sumOfWeights += edge.getWeight();
		totals.sumOfWeights += edge.getWeight();
		
		numOfEdges++;
		totals.numOfEdges++;
	}
	
	
	public void removeEdge(I idOfAdjacentVertex, Edge edge) {
		//Locate the edge to remove, remove it, and update the graph's data.
		for(Edge e : adjacentVertexes.get(idOfAdjacentVertex).edges) {
			if(edgeComparator.compare(edge, e) == 0) {
				adjacentVertexes.get(idOfAdjacentVertex).sumOfWeights -= edge.getWeight();
				totals.sumOfWeights -= edge.getWeight();
				adjacentVertexes.get(idOfAdjacentVertex).edges.remove(edge);
				
				numOfEdges--;
				totals.numOfEdges--;
				
				break;
			}
		}
	}
	
	
	public void removeAllEdgesTo(I idOfOtherVertex) {
		totals.sumOfWeights -= sumOfWeights;
		adjacentVertexes.remove(idOfOtherVertex);
		totals.numOfEdges -= numOfEdges;
	}
	
	
	public ArrayList<Edge> getEdgesTo(I idOfAdjacentVertex) {
		return adjacentVertexes.get(idOfAdjacentVertex).edges;
	}
	

	public I getId() {
		return id;
	}
	public void setId(I idPar) {
		id = idPar;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V newValue) {
		value = newValue;
	}
	public int getNumOfEdges() {
		return numOfEdges;
	}
	public int getSumOfWeights() {
		return sumOfWeights;
	}
}