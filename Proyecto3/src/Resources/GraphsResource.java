package Resources;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Graph.Builder;
import Graph.CSVPost;
import Graph.Djsktra;
import Graph.Edge;
import Graph.Graph;
import Graph.Node;
import Graph.Phone;
import GraphCopy.EdgeCopy;
import GraphCopy.GraphCopy;
import GraphCopy.NodeCopy;



@Path("/graphs")
public class GraphsResource {	

	private static Graph g= new Graph();
	
	@POST
	@Produces("application/json")
	public Response createGraphs() {
		Graph g = new Graph();
		Builder.graphs.put(g.getId(), g);
		return Response.status(200)
				.entity(g)
				.build();
	}
	
	@GET
	@Produces("application/json")
	public Response getGraphs() {
		return Response.status(200)
				.entity(Builder.graphs.values().toArray())
				.build();
	}
	

	
	@DELETE
	public void deleteGraphs() {
		Builder.graphs.clear();
		Builder.edges.clear();
		Builder.nodes.clear();

	}
	
	
	@Path("{id}")
	@Produces("application/json")
	@GET
	public Response getGraph(@PathParam("id") UUID graphId) {
		Graph g = Builder.graphs.get(graphId);
		if(g!=null){
			return Response.status(200)
					.entity(g)
					.build();
		}else{
			return Response.status(404)
					.entity("No graph found error")
					.build();
		}
	}
	
	@Path("{id}")
	@Produces("application/json")
	@DELETE
	public Response deleteGraph(@PathParam("id") UUID graphId) {
		Graph g = Builder.graphs.get(graphId);
		if(g!=null){
			Builder.graphs.remove(graphId);
			return Response.status(200)
					.entity("Success")
					.build();
		}else{
			return Response.status(404)
					.entity("No graph found error")
					.build();
		}
	}
	

	
	@Path("/{id}/nodes")
	@Produces("application/json")
	@GET
	public Response getNodes(@PathParam("id") UUID graphId) {
	 	Graph g = Builder.graphs.get(graphId);
	 	if(g!=null){
	 		return Response.status(200)
	 				.entity(g.getNodes())
	 				.build();
	 	}else{
	 		return Response.status(500)
	 				.entity("No graph found error")
	 				.build();
	 	} 
	}
	
	@Path("/{id}/nodes")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addNode(@PathParam("id") UUID graphId, Phone p) {
		Graph g = Builder.graphs.get(graphId);
	 	if(g!=null){
	 		Node n=new Node();
	 		n.setPhone(p);
	 		g.getNodes().add(n);
	 		Builder.nodes.put(n.getId(), n);
	 		return Response.status(200)
	 				.entity(n)
	 				.build();
	 	}else{
	 		return Response.status(500)
	 				.entity("No graph found error")
	 				.build();
	 	} 
	}
	
	@Path("{id}/nodes/{id}")
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateNode(@PathParam("id") UUID graphId, @PathParam("id") UUID nodeId, Phone p) {
		Node n = Builder.nodes.get(nodeId);
		if(n!=null){
			n.setPhone(p);
			return Response.status(200)
					.entity("Success")
					.build();
		}else{
			return Response.status(500)
					.entity("No node found error")
					.build();
		} 
	}

	 
	
	@Path("{id1}/nodes/{id2}")
	@Produces("application/json")
	@DELETE
	public Response deleteNode(@PathParam("id1") UUID graphId,@PathParam("id2") UUID nodeId) {
		Node n = Builder.nodes.get(nodeId);
		Graph g = Builder.graphs.get(graphId);
		if(n!=null){
			Builder.nodes.remove(nodeId);
			if(g!=null){
				g.getNodes().remove(n);
			}
			return Response.status(200)
					.entity("Success")
					.build();
		}else{
			return Response.status(404)
					.entity("No node found error")
					.build();
		}
	}
	
	@Path("{id}/nodes")
	@Produces("application/json")
	@DELETE
	public Response deleteNodes(@PathParam("id") UUID graphId) {
		Graph g = Builder.graphs.get(graphId);
		if(g!=null){
			for(Node n : g.getNodes()){
				Builder.nodes.remove(n);
			}
			g.setNodes(new ArrayList<Node>());
			return Response.status(200)
					.entity("Success")
					.build();
		}else{
			return Response.status(500)
					.entity("No graph found error")
					.build();
		}
	}
	
	@Path("{id}/edges")
	@Produces("application/json")
	@GET
	public Response getEdges(@PathParam("id") UUID graphId) {
		Graph g = Builder.graphs.get(graphId);
		if(g!=null){
			return Response.status(200)
					.entity(g.getEdges())
					.build();
		}else{
			return Response.status(404)
					.entity("No graph found error")
					.build();
		} 
	}

	@Path("{id}/edges")
	@Produces("application/json")
	@DELETE
	public Response deleteEdges(@PathParam("id") UUID graphId) {
		Graph g = Builder.graphs.get(graphId);
		if(g!=null){
			for(Edge e : g.getEdges()){
				Builder.edges.remove(e);
			}
			g.setEdges(new ArrayList<Edge>());
			return Response.status(200)
					.entity("Success")
					.build();
		}else{
			return Response.status(500)
					.entity("No graph found error")
					.build();
		}
	}
	
	@Path("{id}/edges")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createEdge(@PathParam("id") UUID graphId, Edge e) {
		Builder.edges.put(e.getId(), e);
		Graph g = Builder.graphs.get(graphId);
		if(g!=null){
			e.setId(UUID.randomUUID());
			Builder.edges.put(e.getId(), e);
			g.getEdges().add(e);
			e.updateNodes();
			return Response.status(200)
					.entity(e)
					.build();
		}else{
			return Response.status(500)
					.entity("No graph found error")
					.build();
		} 
	}
	
	@Path("{id1}/edges/{id2}")
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateEdge(@PathParam("id1") UUID graphId, @PathParam("id2") UUID edgeId, Edge newEdge) {
		Edge e = Builder.edges.get(edgeId);
		if(e!=null){
			e.restToNodes();
			e.setStart(newEdge.getStart());
			e.setEnd(newEdge.getEnd());
			e.setWeigth(newEdge.getWeigth());
			e.updateNodes();
			return Response.status(200)
					.entity("Success")
					.build();
		}else{
			return Response.status(404)
					.entity("No arista found error")
					.build();
		} 
	}
	
	@Path("{id1}/edges/{id2}")
	@Produces("application/json")
	@DELETE
	public Response deleteEdge(@PathParam("id1") UUID graphId,@PathParam("id2") UUID edgeId) {
		Edge e = Builder.edges.get(edgeId);
		Graph g = Builder.graphs.get(graphId);
		if(e!=null){
			Builder.edges.remove(edgeId);
			
			if(g!=null){
				g.getEdges().remove (e);
			}
			return Response.status(200)
					.entity("Success")
					.build();
		}else{
			return Response.status(404)
					.entity(e)
					.build();
		}
	}
	
	@Path("{id}/dijkstra")
	@GET
	@Produces("application/json")
	public Response dijkstra(@QueryParam("startNode") UUID startId, @QueryParam("endNode") UUID endId, @PathParam("id2") UUID graphId) {
		Node n1 = Builder.nodes.get(startId);
		Node n2 = Builder.nodes.get(endId);
		Graph g = Builder.graphs.get(graphId);
		
		if(n1!=null && n2!=null && g!=null){
			ArrayList<UUID> path= Djsktra.dijkstra(g,n1.getId(),n2.getId());
			if(path.size()>0){
				return Response.status(200)
						.entity(path)
						.build();
			}else{
				return Response.status(500)
						.entity("No path found")
						.build();
			}
		}else{
			return Response.status(500)
					.entity("No edges or graph found error")
					.build();
		} 
	}
		
	@Path("{id}/degree")
	@GET
	@Produces("application/json")
	public Response sort(@PathParam("id1") UUID graphId,@QueryParam("sort") String sort) {
		Graph g = Builder.graphs.get(graphId);
		if(g!=null){
			if(sort.equals("ASC")){
				g.sorted2();
				return Response.status(200)
						.entity(g.getNodes())
						.build();
			}else{
				g.sorted1();
				return Response.status(500)
						.entity(g.getNodes())
						.build();
			}
		}else{
			return Response.status(500)
					.entity("No edges or graph found error")
					.build();
		} 
	}
	
	@POST
	@Path("generate")
	@Consumes("application/json")
	public void generate(CSVPost csv) {
		Graph g=GraphsResource.g;
		Builder.graphs.put(g.getId(),g);

		Node origin=null;
		Node end=null;
		
		
		Boolean n1=false;
		Boolean n2=false;
		System.out.println("+"+csv.getNumber1());

		for(Node n:g.getNodes()){
			System.out.println(n.getPhone().getNumber());
			System.out.println(n.getPhone().getNumber()==csv.getNumber1());

			if(n.getPhone().getNumber()==csv.getNumber1()){
				n1=true;
				origin=n;
			}
			if(n.getPhone().getNumber()==csv.getNumber2()){
				n2=true;
				end=n;
			}
			System.out.println("");
		}
		
		if(!n1){
			origin=new Node(new Phone(csv.getNumber1()));
			g.getNodes().add(origin);
		}
		if(!n2){
			end=new Node(new Phone(csv.getNumber2()));
			g.getNodes().add(end);
		}
		
		Edge edge=null;
		Boolean e1=false;
		for(Edge e:g.getEdges()){
			if(e.getStart().equals(origin.getId().toString()) && e.getEnd().equals(end.getId().toString()) ){
				e1=true;
				edge=e;
			}

		}
		
		if(!e1){
			Edge e =new Edge(origin.getId().toString(),end.getId().toString(),csv.getWeigth());
			g.getEdges().add(e);

		}else{
			edge.setWeigth(edge.getWeigth()+csv.getWeigth());
		}
	}
	
	@Path("generate")
	@GET
	@Produces("application/json")
	public Response getEveryThing() {
		Graph g = GraphsResource.g;
		if(g!=null){
			EdgeCopy[] edges = new EdgeCopy[g.getEdges().size()];
			NodeCopy[] nodes = new NodeCopy[g.getNodes().size()];
			
			for(int i=0;i<edges.length;i++){
				Edge org=g.getEdges().get(i);
				edges[i]=new EdgeCopy(org.getId(),org.getStart(),org.getEnd());
			}
			
			for(int i=0;i<nodes.length;i++){
				Node org=g.getNodes().get(i);
				System.out.print(g);
				nodes[i]=new NodeCopy();
				nodes[i].setId(org.getId());
				nodes[i].setLabel(String.valueOf(org.getPhone().getNumber()));
				nodes[i].setSize(3);
				NodeCopy.randomPos(nodes[i]);
			}

			GraphCopy g2 = new GraphCopy(nodes, edges);
						
			return Response.status(200)
					.entity(g2)
					.build();
		
		}else{
			return Response.status(500)
					.entity("No graph found error")
					.build();
		} 
	}


}
