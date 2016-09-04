package chapter8;

import java.util.ArrayList;

public class EightSevenPermutationUnique {

	public static ArrayList<String> findPermutations(String s, int i) {
		ArrayList<String> perms = new ArrayList<>();
		
		if (i == s.length() - 1) {
			perms.add(s);
			return perms;
		}
		
		ArrayList<String> currPerms = findPermutations(s, i + 1);
		
		for (String string : currPerms) {
			for (int j = i; j < string.length() - 1; j++) {
				char[] strDecomp = string.toCharArray();
				char temp = strDecomp[i];
				// swap chars
				strDecomp[i] = strDecomp[j + 1];
				strDecomp[j + 1] = temp;
				perms.add(new String(strDecomp));
			}
		}
		perms.addAll(currPerms);
		return perms;
	}
	
	public static void main(String[] args) {
		String s = "back";
		ArrayList<String> perms = findPermutations(s, 0);
		
		for (int i = 0; i < perms.size(); i++) {
			System.out.println(perms.get(i));
		}
		
		System.out.println(perms.size());
	}
}
