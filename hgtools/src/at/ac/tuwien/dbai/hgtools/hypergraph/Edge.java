package at.ac.tuwien.dbai.hgtools.hypergraph;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Edge {
   String name;
   Set<String> vertices;

   public Edge() {
	   vertices = new HashSet<String>();
   }

   public Edge(String[] strings) {
       vertices = new HashSet<String>();
       for (String s : strings) 
    	   vertices.add(s);
       
   }

public boolean contains(String v) {
	   return vertices.contains(v);
   }
   
   public void addVertex(String v) {
	   vertices.add(v);
   }
}
