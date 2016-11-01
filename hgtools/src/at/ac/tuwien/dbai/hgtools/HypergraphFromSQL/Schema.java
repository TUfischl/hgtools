package at.ac.tuwien.dbai.hgtools.HypergraphFromSQL;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Schema {
	Map<String,BasePredicate> preds;
	
	public Schema () {
		preds = new HashMap<String,BasePredicate>();
	}
	
	public void addPredicate(BasePredicate pred) {
		preds.put(pred.getName(), pred);
	}
	
	public BasePredicate getPredicate(String name) {
		return preds.get(name);
	}
}
