package chapter5;

public class FiveTwoPrintDouble {

	public FiveTwoPrintDouble() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean represent(double rep) {
		if(rep < 0 || rep > 1) {
			System.out.println("Error");
			return false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(rep > 0) {
			if(sb.length() > 32) {
				System.out.println("ERROR");
				System.out.println(sb.toString());
				return false;
			}
			double bit = rep * 2;
			if(bit >= 1) {
				sb.append(1);
				rep = bit - 1;
				System.out.println(rep);
			} else {
				sb.append(0);
				rep = bit;
				System.out.println(rep);
			}
			
		}
		System.out.println(sb.toString());
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(represent(.2));
	}
}
