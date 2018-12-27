package leetcode;


/**
 * @author cli
 * @since 1/13/18
 */
public class ReverseInteger
{
	
	public static int reverse2(int x)
	{
		String intString = String.valueOf(x);
		int stringLength = intString.length();
		boolean isSigned = x < 0;
		
		int maxLength = isSigned ? 11 : 10;
		if (intString.length() == maxLength)
		{
			String compare = isSigned ? String.valueOf(Integer.MIN_VALUE) : String.valueOf(Integer.MAX_VALUE);
			int j = compare.length() - 1;
			boolean fine = true;
			for (int i = isSigned ? 1 : 0; i < compare.length(); ++i)
			{
				if (intString.charAt(j) == compare.charAt(i))
				{
					j -= 1;
					continue;
				}
				else if (intString.charAt(j) < compare.charAt(i))
				{
					break;
				}
				else if (intString.charAt(j) > compare.charAt(i))
				{
					fine = false;
					break;
				}
	
			}
			if (!fine)
			{
				return 0;
			}
		}
		
		char[] charString = new char[isSigned ? stringLength - 1 : stringLength];
		int greaterEqThan = isSigned ? 1 : 0;
		int j = 0;
		for (int i = stringLength - 1; i >= greaterEqThan; i--)
		{
			charString[j++] = intString.charAt(i);
		}
		
		return Integer.valueOf(String.valueOf(charString)) * (isSigned ? -1 : 1);
	}
	
	public static int reverse(int x)
	{
		long answer = 0l;
		boolean isSigned = x < 0;
		x = isSigned ? -x : x;
		int iter = (int) Math.floor(Math.log10(x));
		while (x > 0)
		{
			answer += (x % 10) * Math.pow(10, iter);
			x = x / 10;
			iter--;
		}
		
		if (answer > (long) Integer.MAX_VALUE || answer < (long) Integer.MIN_VALUE)
		{
			return 0;
		}
		answer = isSigned ? -answer : answer;
		return (int) answer;
	}
	
	public static void main(String[] args)
	{
		
		System.out.println(reverse(-2047489412));
		int a = 1322222212;
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MAX_VALUE);
	}
}
