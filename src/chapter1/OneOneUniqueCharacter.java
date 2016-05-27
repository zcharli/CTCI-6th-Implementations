package chapter1;

import java.util.Arrays;

public class OneOneUniqueCharacter {

	public OneOneUniqueCharacter() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean isUniqueString(String a) {
		int[] m = new int[128];
		Arrays.fill(m, -1);
		for(int i =0; i< a.length(); i++) {
			char c = a.charAt(i);
			int index = (int)c;
			if(m[index] != -1) // 0 in ascii is null anyways, not a real character
				return false;
						
			m[index] = index;
		}
		return true;
	}
	
	public static void main(String[] args) {
		String[] words = {"abcde", "hello", "apple", "kite", "padle"};
		for (String word : words) {
			boolean wordA = isUniqueString(word);
			boolean wordB = isUniqueString(word);
			if (wordA == wordB) {
				System.out.println(word + ": " + wordA);
			} else {
				System.out.println(word + ": " + wordA + " vs " + wordB);
			}
		}	
	}

}
