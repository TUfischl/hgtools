package at.ac.tuwien.dbai.hgtools.hypergraph;

import java.util.HashSet;
import java.util.Set;

import at.ac.tuwien.dbai.hgtools.Util.Util;
import lombok.Data;

@Data
public class Edge {
   String name;
   Set<String> vertices;

   public Edge() {
	   vertices = new HashSet<String>();
   }

   public Edge(String name, String[] strings) {
	   this.name = name;
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
   
   public String toString () {
	   String s = "";
	   
	   s += Util.stringify(name)+"(";
	   for (String v : getVertices())
		   s += Util.stringify(v)+",";
	   s = s.substring(0, s.length()-1);
	   s += ")";
	   
	   return s;
   }
}
