package bfsWithWeights;

import java.util.Comparator;
import bfsWithWeights.Graph.AdjacentVertexGetter;

public abstract class ChooseAlgoAndFindShortestPath<I, V> {
	public Node<I> chooseAlgoAndFindShortestPath(int totalSumOfWeights, int numOfVertexes, int numOfEdges, Comparator<I> idComparator, AdjacentVertexGetter adjacentVertexGetter, I idOfSource, I idOfTarget) {
		if(useBfsWithWeights(totalSumOfWeights, numOfVertexes, numOfEdges)) {
			return new BfsWithWeights<I>().findShortestPath(idOfSource, idOfTarget, idComparator, adjacentVertexGetter);
		}
		return dijkstrasFindShortestPath(idOfSource, idOfTarget, idComparator, adjacentVertexGetter);
	}
	
	public boolean useBfsWithWeights(int totalSumOfWeights, int numOfVertexes, int numOfEdges) {
		double dijkstrasAlgoComplexity = getDijkstrasAlgoComplexity(numOfVertexes, numOfEdges);
		int numOfSpacerNodes = totalSumOfWeights - numOfEdges;
		int bfsWithWeightsComplexity = numOfVertexes + numOfSpacerNodes + totalSumOfWeights;
		return bfsWithWeightsComplexity < dijkstrasAlgoComplexity;
	}
	
	public double getDijkstrasAlgoComplexity(int v, int e) {
		//Math.log(v) / Math.log(2) is the way to do logv with a base of 2 in java.
		return (v + e) * (Math.log(v) / Math.log(2));
	}
	
	public abstract Node<I> dijkstrasFindShortestPath(I idOfSource, I idOfTarget, Comparator<I> idComparator, AdjacentVertexGetter adjacentVertexGetter);
}











