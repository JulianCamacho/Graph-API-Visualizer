package Graph;

import java.util.UUID;
public class Edge {
	private UUID id;
	private String start;
	private String end;
	private int weigth;

	public Edge() {
		super();
	}
	
	public Edge(String start, String end, int weigth) {
		super();
		this.id = UUID.randomUUID();
		this.start = start;
		this.end = end;
		this.weigth = weigth;
	}
	
	public void updateNodes(){
		Node start = Builder.nodes.get(UUID.fromString(this.start));
		Node end = Builder.nodes.get(UUID.fromString(this.end));
		int outVal = start.getOutDegree() +1;
		int inVal = end.getInDegree() +1;
		start.setOutDegree(outVal);
		end.setInDegree(inVal);
	}
	
	public void restToNodes(){
		Node start = Builder.nodes.get(UUID.fromString(this.start));
		Node end = Builder.nodes.get(UUID.fromString(this.end));
		int outVal = start.getOutDegree() -1;
		int inVal = end.getInDegree() -1;
		start.setOutDegree(outVal);
		end.setInDegree(inVal);
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getWeigth() {
		return weigth;
	}

	public void setWeigth(int weigth) {
		this.weigth = weigth;
	}




}
