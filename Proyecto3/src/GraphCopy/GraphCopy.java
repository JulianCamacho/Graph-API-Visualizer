package GraphCopy;

import Graph.Edge;
import Graph.Node;

public class GraphCopy {
	private NodeCopy[] nodes;
	private EdgeCopy[] edges;
	public GraphCopy(NodeCopy[] nodes, EdgeCopy[] edges) {
		super();
		this.nodes = nodes;
		this.edges = edges;
	}
	public NodeCopy[] getNodes() {
		return nodes;
	}
	public void setNodes(NodeCopy[] nodes) {
		this.nodes = nodes;
	}
	public EdgeCopy[] getEdges() {
		return edges;
	}
	public void setEdges(EdgeCopy[] edges) {
		this.edges = edges;
	}
	
}