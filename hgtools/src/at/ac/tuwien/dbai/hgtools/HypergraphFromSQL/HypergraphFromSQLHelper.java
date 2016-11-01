package at.ac.tuwien.dbai.hgtools.HypergraphFromSQL;

import java.util.HashMap;
import java.util.Map;

import at.ac.tuwien.dbai.hgtools.hypergraph.Edge;
import at.ac.tuwien.dbai.hgtools.hypergraph.Hypergraph;
import lombok.Data;

@Data
public class HypergraphFromSQLHelper {
   Map<String,BasePredicate> atoms;
   Map<String,String> varAlias;
   int iVar = 1;
   
   public HypergraphFromSQLHelper() {
	   atoms = new HashMap<String,BasePredicate>();
	   varAlias = new HashMap<String,String>();
   }
   
   public void addAtom(BasePredicate b, String alias) {
	   atoms.put(alias,b);
	   for ( String lit : b.getLiterals()) {
		   varAlias.put(alias+"."+lit, "X" + iVar);
		   iVar++;
	   }
   }
   
   public void addJoin(SameColumn sc) {
	   Map<String,String> tmpSet = new HashMap<String,String>();
	   String var = "X"+iVar;
	   iVar++;
	   String varA = varAlias.get(sc.getA().getTable()+"."+sc.getA().getColumnName());
	   String varB = varAlias.get(sc.getB().getTable()+"."+sc.getB().getColumnName());
	   for (String key : varAlias.keySet()) {
		   if (varAlias.get(key).equals(varA)) 
			   tmpSet.put(key, var);
		   if (varAlias.get(key).equals(varB)) 
			   tmpSet.put(key, var);
	   }
	   varAlias.putAll(tmpSet);
   }
   

   public Hypergraph getHypergraph() {
	   Hypergraph H = new Hypergraph();
	   
	   for (String atom : atoms.keySet()) {
		   Edge e = new Edge();
		   e.setName(atom);
		   for (String var : varAlias.keySet()) {
			   if (var.startsWith(atom)) {
				   e.addVertex(varAlias.get(var));
			   }
		   }
		   H.addEdge(e);
	   }
	   
	   return H;
   }
}
