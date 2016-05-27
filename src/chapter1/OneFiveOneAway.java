package chapter1;

public class OneFiveOneAway {

	public OneFiveOneAway() {
		// TODO Auto-generated constructor stub
	}

	public static boolean oneAway(String a, String b) {
		if(Math.abs(a.length() - b.length()) > 1)  
			return false;
		if(a.length() + b.length() == 1)
			return true;
		boolean len = a.length() == b.length();
		boolean aLonger = a.length() > b.length();
		boolean oneErrorFound = false;
		
		// compare against only whats smaller
		String smaller = "";
		String larger = "";
		if (a.length() > b.length()) {
			smaller = b;
			larger = a;
		} else {
			smaller = a;
			larger = b;
		}
		
		
		int j = 0;
		for (int i = 0; i < smaller.length(); i++) {
			char ac = smaller.charAt(i);
			char bc = larger.charAt(j);
			if(ac != bc) {
				if(oneErrorFound) 
					return false;
				oneErrorFound = true;
				if (!len) {
					//Synchronize the two strings at the first index that is differnt
					j++;
					i--;
					continue;
				}

			}
			j++;
		}
		return true;
	}
	public static void test(String a, String b, boolean expected) {
		boolean resultA = oneAway(a, b);
		boolean resultB = oneAway(a, b);		
		
		if (resultA == expected && resultB == expected) {
			System.out.println(a + ", " + b + ": success");
		} else {
			System.out.println(a + ", " + b + ": error");
		}
	}
	public static void main(String[] args) {
		String[][] tests = {
				{"a", "b", "true"}, 
				{"", "d", "true"},
				{"d", "de", "true"},
				{"pale", "pse", "false"},
				{"acdsfdsfadsf", "acdsgdsfadsf", "true"},
				{"acdsfdsfadsf", "acdsfdfadsf", "true"},
				{"acdsfdsfadsf", "acdsfdsfads", "true"},
				{"acdsfdsfadsf", "cdsfdsfadsf", "true"},
				{"adfdsfadsf", "acdfdsfdsf", "false"},
				{"adfdsfadsf", "bdfdsfadsg", "false"},
				{"adfdsfadsf", "affdsfads", "false"},
				{"pale", "pkle", "true"},
				{"pkle", "pable", "false"}
				};
		for (int i = 0; i < tests.length; i++) {
			String[] test = tests[i];
			String a = test[0];
			String b = test[1];
			boolean expected = test[2].equals("true");
			
			test(a, b, expected);
			test(b, a, expected);
		}
	}
	
}
