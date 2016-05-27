package chapter1;

public class OneTwoStringPermutation {

	public OneTwoStringPermutation() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean isPermutation(String a, String b) {
		if(a.length() != b.length())
			return false;
		int[] ac = countChars(a);
		int[] bc = countChars(b);
		
		for (int i = 0; i < bc.length; i++) {
			if(ac[i] != bc[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static int[] countChars(String a) {
		int[] counter = new int[128];
		
		for(int i = 0; i< a.length(); i++) {
			char c = a.charAt(i);
			int index = (int)c;
			counter[index] = counter[index] + 1;
		}
		return counter;
	}
	
	public static void main(String[] args) {
		String[][] pairs = {{"apple", "papel"}, {"carrot", "tarroc"}, {"hello", "llloh"}};
		for (String[] pair : pairs) {
			String word1 = pair[0];
			String word2 = pair[1];
			boolean anagram = isPermutation(word1, word2);
			System.out.println(word1 + ", " + word2 + ": " + anagram);
		}
	}
}
