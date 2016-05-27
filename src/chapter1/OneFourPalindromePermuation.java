package chapter1;

public class OneFourPalindromePermuation {

	public OneFourPalindromePermuation() {
		// TODO Auto-generated constructor stub
	}

	public static boolean isPalendrome(String a) {
		int len = a.length();
		for (int i = 0; i < len / 2; i++) {
			if (a.charAt(i) != a.charAt(len - i - 1)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isPalendromePermuation(String a) {
		// A string is a palen-permutaion if it has an even amount of every
		// character except for 1 if string is odd
		boolean even = (a.length() % 2) == 0;
		a = a.toLowerCase();
		int[] counter = new int[128];
		for (int i = 0; i < a.length(); i++) {
			int idx = (int) a.charAt(i);
			counter[idx] = counter[idx] + 1;
		}

		boolean foundOdd = false;
		for (int i = 0; i < counter.length; i++) {
			if (counter[i] != 0) {
				if (counter[i] % 2 == 1) {
					if (foundOdd)
						return false;
					foundOdd = true;
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		String[] strings = { "Rats live on no evil star", "A man, a plan, a canal, panama", "Lleve", "Tacotac",
				"asda" };
		for (String s : strings) {
			boolean a = isPalendromePermuation(s);
			boolean b = isPalendromePermuation(s);
			boolean c = isPalendromePermuation(s);
			System.out.println(s);
			if (a == b && b == c) {
				System.out.println("Agree: " + a);
			} else {
				System.out.println("Disagree: " + a + ", " + b + ", " + c);
			}
			System.out.println();
		}
	}
}
