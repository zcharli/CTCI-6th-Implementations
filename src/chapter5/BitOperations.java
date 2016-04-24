package chapter5;

public class BitOperations {

	public static int getBit(int num, int i) {
		return (num & (1 << i)) != 0 ? 1 : 0;
	}
	
	public static int setBit(int num, int i) {
		return num | (1 << i);
	}
	
	public static int clearBit(int num, int i) {
		return num & ~(1 << i);
	}
	
	public static void main(String[] arg) {
		System.out.println(getBit(9,2));
		System.out.println(setBit(8,0));
		System.out.println(clearBit(12,2));
	}

}
