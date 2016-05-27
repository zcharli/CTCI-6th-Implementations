package chapter1;

public class OneThreeUrlify {

	public OneThreeUrlify() {
		// TODO Auto-generated constructor stub
	}
	
	public static void urlify(char[] a, int length) {
		for (int i = 0; i < a.length; i++) {
			if(a[i] == 32) {
				length = length + 2 >= a.length ? a.length -1 : length + 2;
				for(int j=length; j > i; j--) {
					a[j] = a[j-2];
				}
				a[i] = '%';
				a[i+1] = '2';
				a[i+2] = '0';
				i = i+2;
			}
		}
	}
	
	public static int findLastCharacter(char[] str) {
		for (int i = str.length - 1; i >= 0; i--) {
			if (str[i] != ' ') {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String str = "Mr John Smith    ";
		char[] arr = str.toCharArray();
		int trueLength = findLastCharacter(arr) + 1;
		urlify(arr, trueLength);	
		System.out.println("\\" + new String(arr));
	}
}
