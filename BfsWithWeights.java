package bfsWithWeights;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import bfsWithWeights.Graph.AdjacentVertexGetter;

public class BfsWithWeights<I> {
	/*Returns the node of the target vertex, unless the target vertex
	 * isn't in the graph.*/
	public Node<I> findShortestPath(I idOfSource, I idOfTarget, Comparator<I> idComparator, AdjacentVertexGetter adjacentVertexGetter) {
		HashSet<I> visitedVertexes = new HashSet<>();
		Node<I> sourceNode = new Node<>(-1, idOfSource, null);
		
		if(idComparator.compare(idOfSource, idOfTarget) == 0) {
			return sourceNode;
		}
		
		Node<I> headOfQueue = sourceNode;
		Node<I> tailOfQueue = headOfQueue;
		while(headOfQueue != null) {	
			Node<I> newNode;
			if(headOfQueue.getIsASpacerNode()) {
				if(headOfQueue.getSpacerNum() == headOfQueue.getOutOf()) {
					newNode = new Node<>(headOfQueue.getEdgeIndex(), headOfQueue.getIdOfEndVertex(), headOfQueue.getStartNode()); 
					if(idComparator.compare(newNode.getIdOfVertex(), idOfTarget) == 0) {
						return newNode;
					}
				}
				else {
					newNode = new Node<>(headOfQueue.getEdgeIndex(), headOfQueue.getStartNode(), headOfQueue.getIdOfEndVertex(), headOfQueue.getSpacerNum() + 1, headOfQueue.getOutOf());
				}
				tailOfQueue.nextNodeInQueue = newNode;
				tailOfQueue = newNode;
			}
			else {
				HashMap<I, SumAndEdges> adjacentVertexes = adjacentVertexGetter.getAdjacentVertexesOf(headOfQueue.getIdOfVertex());
				Set<I> theVertexes = adjacentVertexes.keySet();
				/*I don't use the forEach method for iterating because I woudln't
				 * be able to return the target node if i foud it while iterating.
				 * the Consumer lambda has a return type of void.*/
				Iterator<I> iterator = theVertexes.iterator();
				I idOfAdjacentVertex;
				ArrayList<Edge> theEdges;
				while(iterator.hasNext()) {
					idOfAdjacentVertex = (I) iterator.next();
					theEdges = adjacentVertexes.get(idOfAdjacentVertex).edges;
					
					/*explanantion for why i use contains(), instead of usig add() and seeing what the return bool value is:  i don't add the vertex to visitiedVerexesyet because if i need to create spacer nodes then the adjacent vertex won
					t be visitied yet.*/
					boolean vertexHasntAlreadyBeenVisited = !(visitedVertexes.contains(idOfAdjacentVertex));
					if(vertexHasntAlreadyBeenVisited) {
						for(int edgeIndex = 0; edgeIndex < theEdges.size(); edgeIndex++) {
							Edge edge = theEdges.get(edgeIndex);
							if(edge.getWeight() == 1) {	//don't create a spacer node.							
								newNode = new Node<>(edgeIndex, idOfAdjacentVertex, headOfQueue);
								if(idComparator.compare(newNode.getIdOfVertex(), idOfTarget) == 0) {
									return newNode;
								}
								
								visitedVertexes.add((I) newNode.getIdOfVertex());
							}
							else { //create a spacer node.
								newNode = new Node<>(edgeIndex, headOfQueue, idOfAdjacentVertex, 1, edge.getWeight() - 1);
							}							
							tailOfQueue.nextNodeInQueue = newNode;
							tailOfQueue = newNode;
						}
					}
				}
			}
			headOfQueue = headOfQueue.nextNodeInQueue;
		}
		return null;
	}
}
