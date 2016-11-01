package at.ac.tuwien.dbai.hgtools;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		String type = args[0];
		for (int i = 0; i < args.length-1; i++)
			args[i] = args[i+1];
		if (type.equals("-sql")) {
			MainSQL.main(Arrays.copyOf(args, args.length-1));
		} else if (type.equals("-csp")) {
			MainCSP.main(Arrays.copyOf(args, args.length-1));
		}
	}

}
