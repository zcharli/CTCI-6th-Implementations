package chapter5;

public class FiveFiveConvertNumber {

	public FiveFiveConvertNumber() {
		// TODO Auto-generated constructor stub
	}
	
	public static int numBitsToConvertNumber(int a, int b) {
		int i = a ^ b;
		i = (i & 0x55555555) + ((i >> 1) & 0x55555555);
	    i = (i & 0x33333333) + ((i >> 2) & 0x33333333);
	    i = (i & 0x0F0F0F0F) + ((i >> 4) & 0x0F0F0F0F);
	    i = (i & (0x00ff00ff)) + ((i >> 8) & (0x00ff00ff));
	    i = (i & (0x0000ffff)) + ((i >> 16) & (0x0000ffff));
	    return i;
	}
	
	public static void main(String[] args) {
		int a = 31;
		int b = 14;

		System.out.println(numBitsToConvertNumber(31, 14));
		
	}
}
