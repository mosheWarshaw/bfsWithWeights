package bfsWithWeights;

/*The following is an example that demonstrates how spacer nodes
  are created so that bfs can be used on a weighted graph.
  (The arrows are edges, and the numbers above them are their weight).
   
	   1	     3
   A ------> B ------> C
    
    
    
   The node's fields for B would be:
   isASpacerNode = false;
   idOfVertex = B;
   prevNode = A;
   edgeNum = 0;
   the rest of the values would be null.
   
   When the node for B is popped from the queue, you
   see that the weight to C is more than 1, so spacer nodes
   are created in order to have more edges between B and C.
   You would then have to create 2 nodes so that there
   will be 3 edges. So you create spacerNode1 and add it to
   the queue.
   spacerNode1's values would be:
   startNode = B;
   idOfEndVertex = C;
   spacerNum = 1;
   outOf = 2;
   edgeNum = 0;
   The rest of the values would be null.
   
   When spacerNode1 gets popped from the queue, you see that
   the nodeNum is less than the outOf number, so you have to create
   another spacer node.
   You create spacerNode2 and its values would be:
   startNode = B;
   idOfEndVertex = C;
   spacerNum = 2;
   outOf = 2;
   edgeNum = 0;
   The rest of the values would be null.
   
   When you pop spacerNode2 off the queue you see that you created as many
   spacer nodes as you should have (because spacerNum == outOf),
   so you can move onto C, and you create a node for it.
   
   At the end, the graph was treated as if it looked like:
   
   A ------> B ---> spacerNode1 ---> spacerNode2 ---> C
   
   
   Note, spacer nodes never have multiple adjacent vertexes.
   They just connect to another spacer node or to the next
   vertex. They have only one outgoing edge.*/

public class Node<I> {
	/*It's the index of the edge in the arraylist of edges.
	 * It's the edge that was used to get to this node's vertex.
	 * If there aren't any parallel edges then this will be index 0.*/
	private int edgeIndex;
	private int spacerNum;
	/*If the weight is w, then there are w - 1 spacer nodes.
	 * eg: if the weight is 3, then three edges need to be created,
	 * and 2 spacer nodes need to be created in order to create those 3
	 * edges.*/
	private int outOf;
	private boolean isASpacerNode;
	private I idOfVertex;
	private Node<I> prevNode;
	private Node<I> startNode;
	private I idOfEndVertex;
	public Node<I> nextNodeInQueue;
	
	public Node(int edgeIndexPar, I idOfVertexPar, Node<I> prevNodePar) {
		isASpacerNode = false;
		edgeIndex = edgeIndexPar;
		idOfVertex = idOfVertexPar;
		prevNode = prevNodePar;
	}
	
	public Node(int edgeIndexPar, Node<I> startNodePar, I idOfEndVertexPar, int spacerNumPar, int outOfPar) {
		isASpacerNode = true;
		edgeIndex = edgeIndexPar;
		startNode = startNodePar;
		idOfEndVertex = idOfEndVertexPar;
		spacerNum = spacerNumPar;
		outOf = outOfPar;
	}
	
	public boolean getIsASpacerNode() {
		return isASpacerNode;
	}
	public int getEdgeIndex() {
		return edgeIndex;
	}
	public int getSpacerNum() {
		return spacerNum;
	}
	public int getOutOf() {
		return outOf;
	}
	public I getIdOfVertex() {
		return idOfVertex;
	}
	public Node<I> getPrevNode() {
		return prevNode;
	}
	public Node<I> getStartNode(){
		return startNode;
	}
	public I getIdOfEndVertex() {
		return idOfEndVertex;
	}
}