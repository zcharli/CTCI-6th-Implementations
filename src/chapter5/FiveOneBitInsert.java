package chapter5;

public class FiveOneBitInsert {

	public FiveOneBitInsert() {
		// TODO Auto-generated constructor stub
	}

	public static int insertBits(int template, int insert, int startIdx, int endIdx) {
		int trailing = template & ((1 << startIdx) - 1);
		int inserting = insert << startIdx;
		System.out.println(template);
		int clearbits = ~((1 << (endIdx+1))-1);
		return (template & clearbits) | trailing | inserting;
	}
	
	public static void main(String args[]) {
		int template = Integer.parseInt("10010101001", 2);
		int insert = Integer.parseInt("10011", 2);
		System.out.println(template);
		// Insert 10011 where i = 2, j = 6
		// Result = 10001001100
		
		System.out.println(insertBits(template, insert, 3, 7));
		System.out.println(Integer.parseInt("10010011001", 2));
	}
}
