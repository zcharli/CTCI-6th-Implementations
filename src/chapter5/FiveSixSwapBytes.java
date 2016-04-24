package chapter5;

public class FiveSixSwapBytes {

	public FiveSixSwapBytes() {
		// TODO Auto-generated constructor stub
	}

	public static int swapEvenOddBytes(int n) {
		int odd = n & 0x55555555;
		int even = n & (0xaaaaaaaa);
		return (odd << 1) | (even >> 1);
	}

	public static void main(String[] args) {
		System.out.println(swapEvenOddBytes(5));
	}
}
