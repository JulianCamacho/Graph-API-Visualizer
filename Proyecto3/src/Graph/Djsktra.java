package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Djsktra {
	
	private static Map<Integer,UUID> getUUIDByPos(Graph g){
		 ArrayList<Node> nodes = g.getNodes();
		 Map<Integer,UUID> positions = new HashMap();
		 
		 int i=0;
		 
		 for(Node n: nodes){
			positions.put(i, n.getId());
			i +=1;
		 } 
		 return positions;
	}
	
	
	private static Map<UUID,Integer> getPosByUUID(Graph g){
		 ArrayList<Node> nodes = g.getNodes();
		 Map<UUID,Integer> positions = new HashMap();
		 
		 int i=0;
		 
		 for(Node n: nodes){
			positions.put(n.getId(),i );
			i +=1;
		 } 
		 return positions;
	}
		
	public static ArrayList<UUID> dijkstra(Graph g, UUID n1, UUID n2){
		ArrayList<Integer>[][] paths= Djsktra.generateInitialMatrix(g);
		Map<Integer,UUID> map1= Djsktra.getUUIDByPos(g);
		Map<UUID,Integer> map2= Djsktra.getPosByUUID(g);
		
		ArrayList<Integer> path = paths[map2.get(n1)][map2.get(n2)];
		ArrayList<UUID> finalPath = new ArrayList();
		for(Integer e: path){
			finalPath.add(map1.get(e));
		}
		
		return finalPath;	
		
	}
	


	private static ArrayList<Integer>[][] generateInitialMatrix(Graph g){
		 ArrayList<Edge> edges = g.getEdges(); 
		 ArrayList<Node> nodes = g.getNodes();
		 
		 int[][] dist = new int[g.getNodes().size()][g.getNodes().size()];
		 ArrayList<Integer>[][] paths= new ArrayList[g.getNodes().size()][g.getNodes().size()];
		 
		 Map<UUID,Integer> positions = Djsktra.getPosByUUID(g);
		 
		 int vert= nodes.size();
		 
		 for(int m= 0; m<vert;m++){
			 for(int h=0; h<vert; h++){
				 dist[m][h]=Integer.MAX_VALUE;
			 }
		 }
		 
		 for(Edge e: edges){
			 int row = positions.get(e.getStart());
			 int column = positions.get(e.getEnd());
			 dist[row][column]=e.getWeigth();
			 paths[row][column].add(column);
		 }

		 for (int k = 0; k < vert; k++){ 
	         for (int i = 0; i < vert; i++) { 
	              for (int j = 0; j < vert; j++) {
	                  if (dist[i][k] + dist[k][j] < dist[i][j]) {
	                      dist[i][j] = dist[i][k] + dist[k][j]; 
	                      paths[i][j]=new ArrayList<Integer>();
	                  	  paths[i][j].addAll(paths[i][k]);
	                  	  paths[i][j].addAll(paths[k][j]);
	                  } 
	              }
	          }  
	     }
		 
		 return paths;
		 
	}
}
