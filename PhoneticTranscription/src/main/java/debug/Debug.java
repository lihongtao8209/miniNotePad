package debug;

import java.util.List;

public class Debug {
	
	static public void debug(String info) {
		System.out.println(info);
	}

	static public void debug(String[] info) {
		for (int i = 0; i < info.length; i++) {
			if (i % 10 == 0) {
				System.out.println();
			} else {
				System.out.print("[" + info[i] + "]" + " ");
			}
		}

	}
	
	static public void debug(List list) {
		System.out.println(list);
	}
	
	static public void debugInfo(String info) {
		String formatInfo=String.format("[%s]", info);
		debug(formatInfo);
	}
	
	
	private void addQualifier(String inputStr,String qualifier) {
		String [][]qualifierArray = {
										{"(",")"},
										{"[","]"},
										{"{","}"},
				                     };
		
	}
}
