package bfsWithWeights;

public class Edge {
	private int weight;
	
	public Edge() {}
	public Edge(int weightPar) throws InvalidWeightException {
		setWeight(weightPar);
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weightPar) throws InvalidWeightException{
		if(weightPar < 1) {
			throw new InvalidWeightException("weight value: " + weightPar);
		}
		weight = weightPar;
	}
}