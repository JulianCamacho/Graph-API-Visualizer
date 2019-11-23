package Graph;

import java.util.UUID;

public class Node {
	private UUID id;
	public int getProm() {
		return prom;
	}
	
	public void setProm(int prom) {
		this.prom = prom;
	}

	private int inDegree;
	private int outDegree;
	private Phone phone;
    private int prom;
	
	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Node( ) {
		super();
		this.id = UUID.randomUUID();
		this.inDegree=0;
		this.outDegree=0;
	    this.prom = (inDegree + outDegree)/2;
	}
	

	public Node(Phone p) {
		super();
		this.id = UUID.randomUUID();
		this.inDegree = 0;
		this.outDegree = 0;
		this.phone=p;
	    this.prom = (inDegree + outDegree)/2;
	}

	public Node( int inDegree, int outDegree) {
		super();
		this.id = UUID.randomUUID();
		this.inDegree = inDegree;
		this.outDegree = outDegree;
	    this.prom = (inDegree + outDegree)/2;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getInDegree() {
		return inDegree;
	}

	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}

	public int getOutDegree() {
		return outDegree;
	}

	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}

}