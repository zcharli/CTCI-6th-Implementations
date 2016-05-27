package chapter1;

public class OneSixStringCompression {

	public OneSixStringCompression() {
		// TODO Auto-generated constructor stub
	}

	public static String compress(String a) {
		char[] s = a.toCharArray();
		StringBuilder sb = new StringBuilder();
		
		char cur = s[0];
		int count = 1;
		for (int i = 0; i < s.length; i++) {
			if(sb.length() == 0) {
				sb.append(s[i]);
			} else {
				if(s[i] == cur){
					count += 1;
				} else {
					sb.append(count);
					sb.append(s[i]);
					cur = s[i];
					count = 1;
				}
			}
		}
		sb.append(count);
		return sb.length() > a.length() ? a : sb.toString();
	}
	
	public static void main(String[] args) {
		String str = "aaaaabbbbaaaabbddc";
		System.out.println(str);
		System.out.println(compress(str));
	}
}
