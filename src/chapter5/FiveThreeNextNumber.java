package chapter5;

public class FiveThreeNextNumber {

	public FiveThreeNextNumber() {
		// TODO Auto-generated constructor stub
	}

	public static int nextSmallest(int i) {
		// find the first 10 sequence then shift right
		String bs = Integer.toBinaryString(i);
		for(int j = bs.length() - 1; j >= 1; j--) {
			if(bs.charAt(j) == '0' && bs.charAt(j-1) == '1') {
				char bsArray[] = bs.toCharArray();
				char temp = bs.charAt(j-1);
				bsArray[j-1] = bsArray[j];
				bsArray[j] = temp;
				//bsArray[j-1] = '0';
				String bitString = new String(bsArray);
				return Integer.parseInt(bitString, 2);
			}
		}
		System.out.println("Cannot be found");
		return i;
	}
	
	public static int nextLargest(int i) {
		// Find the first 01 sequence then shift left
		String bs = "0" + Integer.toBinaryString(i);
		for(int j = bs.length() - 1; j >= 1; j--) {
			if(bs.charAt(j) == '1' && bs.charAt(j-1) == '0') {
				char bsArray[] = bs.toCharArray();
				char temp = bs.charAt(j-1);
				bsArray[j-1] = bsArray[j];
				bsArray[j] = temp;
				//bsArray[j-1] = '0';
				String bitString = new String(bsArray);
				return Integer.parseInt(bitString, 2);
			}
		}
		System.out.println("Cannot be found");
		return i;
	}
	
	public static void main(String[] args) {
		System.out.println(nextLargest(326879));
		System.out.println(nextSmallest(10));
	}
}
