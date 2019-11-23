package Graph;

import java.util.ArrayList;
import java.util.UUID;

public class Graph {
	private UUID id;
	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;
	
	public Graph(){
		this.id = UUID.randomUUID();
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Edge>();
	}
	
	public Graph(ArrayList<Node> nodes, ArrayList<Edge> edges) {
		super();	
		this.id = UUID.randomUUID();
		this.nodes = nodes;
		this.edges = edges;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
	
	public void sorted1() {
		int i = 0;
		while(nodes.get(i+1) != null) {
			if(nodes.get(i).getProm() >= nodes.get(i+1).getProm()) {
				i+=1;
			}
			else {
				Node tem = nodes.get(i);
				Node next =nodes.get(i+1);
				nodes.set(i,next)
				;
				nodes.set(i+1,tem);
				i = 0;
			}
		}
	}
	public void sorted2() {
		int i = 0;
		while(nodes.get(i+1) != null) {
			if(nodes.get(i).getProm() <= nodes.get(i+1).getProm()) {
				i+=1;
			}
			else {
				Node tem = nodes.get(i);
				Node next =nodes.get(i+1);
				nodes.set(i,next)
				;
				nodes.set(i+1,tem);
				i = 0;
			}
		}
	}
	
}