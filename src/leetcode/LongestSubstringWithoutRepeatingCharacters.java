package leetcode;

/**
 * @author cli
 * @since 1/14/18
 */
public class LongestSubstringWithoutRepeatingCharacters
{
	
	public static int lengthOfLongestSubstring2(String s)
	{
		int cLong = 0;
		
		final char[] cString = s.toCharArray();
		
		for (int i = 0; i < cString.length; ++i)
		{
			final byte[] charMap = new byte[128]; // ascii char map
			int iterLong = 0;
			for (int j = i; j < cString.length; ++j)
			{
				if (charMap[cString[j]] > 0)
				{
					break;
				}
				charMap[cString[j]] = 1;
				iterLong++;
			}
			
			cLong = (iterLong > cLong) ? iterLong : cLong;
		}
		
		return cLong;
	}
	
	public static int lengthOfLongestSubstring(String s)
	{
		final char[] cString = s.toCharArray();
		int cLong = 0, i = 0, j = 0, n = cString.length;
		final byte[] charMap = new byte[128]; // ascii char map
		
		while (i < n && j < n)
		{
			if (charMap[cString[j]] == 0)
			{
				charMap[cString[j++]] = 1;
				cLong = Math.max(cLong, j - i);
			}
			else
			{
				charMap[cString[i++]] = 0;
			}
		}
		
		return cLong;
	}
	
	public static void main(String[] args)
	{
		System.out.println(lengthOfLongestSubstring("pwwkew"));
	}
}
