package GraphCopy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.UUID;

public class NodeCopy {
	private UUID id;
	private String label;
	private int x;
	private int y;
	private int size;
	private static ArrayList<Point> list ;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public NodeCopy(UUID id, String label, int x, int y, int size) {
		super();
		this.id = id;
		this.label = label;
		this.x = x;
		this.y = y;
		this.size = size;
	}
	public NodeCopy() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static void randomPos(NodeCopy n){
		if(list==null){
			list = new ArrayList();
			for(int i=0;i<10;i++){
				for(int j=0;j<10;j++){
					list.add(new Point(i,j));
				}
			}	
		}
		Point p = list.remove((int)(Math.random()*(list.size())));
		n.setX((int)p.getX());
		n.setY((int)p.getY());
	}
}
