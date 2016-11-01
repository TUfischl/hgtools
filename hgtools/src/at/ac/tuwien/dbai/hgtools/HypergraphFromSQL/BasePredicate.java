package at.ac.tuwien.dbai.hgtools.HypergraphFromSQL;


import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class BasePredicate {
   String name;
   Set<String> literals;
   
   public BasePredicate(String name) {
	   this.name = name;
	   literals = new HashSet<String>();
   }
   
   public void addLiteral(String lit) {
	   literals.add(lit);
   }
   
   public void removeLiteral(String lit){
	   literals.remove(lit);
   }
}
