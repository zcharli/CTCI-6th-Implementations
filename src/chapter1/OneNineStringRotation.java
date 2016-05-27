package chapter1;

public class OneNineStringRotation {

	public OneNineStringRotation() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean isSubstring(String big, String small) {
		if (big.indexOf(small) >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean rotate(String a, String b) {
		if(a.length() == 0 || (a.length() != b.length())) {
			return false;
		}
		return isSubstring(b+b, a);
	}
	
	public static void main(String[] args) {
		String[][] pairs = {{"apple", "pleap"}, {"waterbottle", "erbottlewat"}, {"camera", "macera"}};
		for (String[] pair : pairs) {
			String word1 = pair[0];
			String word2 = pair[1];
			boolean is_rotation = rotate(word1, word2);
			System.out.println(word1 + ", " + word2 + ": " + is_rotation);
		}
	}
}
