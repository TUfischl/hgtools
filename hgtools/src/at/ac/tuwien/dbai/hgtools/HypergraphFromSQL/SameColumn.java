package at.ac.tuwien.dbai.hgtools.HypergraphFromSQL;

import lombok.Data;
import net.sf.jsqlparser.schema.Column;

@Data
public class SameColumn {
   private Column a;
   private Column b;
}
