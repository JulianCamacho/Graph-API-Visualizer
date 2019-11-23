package GraphCopy;

import java.util.UUID;

public class EdgeCopy {
	private UUID id;
	private String source;
	private String target;
	public EdgeCopy(UUID id, String source, String target) {
		super();
		this.id = id;
		this.source = source;
		this.target = target;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	
}
