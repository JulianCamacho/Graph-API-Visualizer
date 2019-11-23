package Resources;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import Graph.Builder;
import Graph.Graph;

public class GraphResource {
	private UUID currentId;
	
	public GraphResource(UUID graphId) {
		this.currentId = graphId;
	}

	@GET
	@Produces("application/json")
	public Response getGraphData() {
		Graph g = Builder.graphs.get(currentId);
		if (g != null) {
			return Response.status(200)
					.entity(g)
					.build();
		}
		return Response.status(404)
				.entity("NO ESTA")
				.build();
	}
}
