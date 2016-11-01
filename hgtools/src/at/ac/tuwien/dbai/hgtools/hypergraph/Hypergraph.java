package at.ac.tuwien.dbai.hgtools.hypergraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import at.ac.tuwien.dbai.hgtools.Util.CombinationIterator;
import at.ac.tuwien.dbai.hgtools.Util.PowerSetIterator;
import lombok.Data;

@Data
public class Hypergraph {
   Set<Edge> edges;
   Set<String> vertices;
   
   public Hypergraph () {
	   edges = new HashSet<Edge>();
	   vertices = new HashSet<String>();
   }
   
   public void addEdge(Edge e) {
	   edges.add(e);
	   vertices.addAll(e.getVertices());
   }
   
   public int cntVertices() {
	   return vertices.size();
   }
   
   public int cntEdges() {
	   return edges.size();
   }
   
   public int degree() {
	   int maxDegree = 0;
	   
	   for (String v : vertices) {
		  int degree = 0;
	      for (Edge e : edges)
	         if (e.contains(v)) degree++;
	      if (degree > maxDegree) maxDegree = degree;
	   }
	   
	   return maxDegree;
   }
   
   public int cntBip(int k) {
	   int maxBip = 0;
	   
	   if (k <= edges.size()) {
	      CombinationIterator<Edge> cit = new CombinationIterator<Edge>(edges,k);
	      while (cit.hasNext()) {
	    	  Collection<Edge> subset = cit.next();
	    	  HashSet<String> inter = new HashSet<String>();
	    	  
	    	  Iterator<Edge> it = subset.iterator();
	    	  Edge e = it.next();
	    	  inter = new HashSet<String>(e.getVertices());
	    	  
	    	  while (it.hasNext()) {
	    		  inter.retainAll(it.next().getVertices());
	    	  }
	    	  
	    	  if (inter.size() > maxBip) 
	    		  maxBip = inter.size();
	      }
	   } 
	   
	   return maxBip;
   }
   
   public int VCdimension() {
	   boolean found = false;
	   int maxVC = (int) Math.floor(((Math.log(cntEdges())/Math.log(2))));
	   int vc;
	   
	   for (vc = 1; vc <= maxVC; vc++ ) {
		   found = false;
		   CombinationIterator<String> cit = new CombinationIterator<String>(vertices,vc);
		   while (cit.hasNext() && !found) {
              PowerSetIterator<String> psetX = new PowerSetIterator<String>(cit.next());            	      
		      LinkedList<Integer> usedE = new LinkedList<Integer>();
		      ArrayList<Edge> edges = new ArrayList<Edge>(this.getEdges());
		      if (VCdimHelper(psetX,edges,usedE))
		    	  found = true;
		   }
		   if (!found)
			   return vc-1;
	   }
	   
	   
	   return vc-1;
   }
   
   private boolean VCdimHelper(PowerSetIterator<String> itPSetX, ArrayList<Edge> edges, LinkedList<Integer> usedEdges) {
	   if (itPSetX.hasNext()) {
		   Set<String> setX = itPSetX.next();
		   for (int i=0; i<edges.size() && !usedEdges.contains(i); i++) {
			   if (edges.get(i).getVertices().containsAll(setX)) {
				   usedEdges.push(i);
				   if (VCdimHelper(itPSetX,edges,usedEdges)) {
					   return true;
				   } else {
					   usedEdges.pop();
				   }
			   }
		   }
		   return false;
	   } else
		   return true;
   }
}
