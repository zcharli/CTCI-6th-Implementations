package random;

import java.util.Arrays;

/**
 * @author cli
 * @since 6/27/17
 */
public class StringSmaller
{
	
	public static int[] solution(String a, String b)
	{
		String[] M = a.split(" ");
		String[] N = b.split(" ");
		
		int[] r = new int[N.length];
		
		int[] mValues = new int[M.length];
		for (int i = 0; i < M.length; ++i)
		{
			int smallestCharMj = _getSmallestChar(M[i]);
			mValues[i] = smallestCharMj;
		}
		
		for (int i = 0; i < N.length; ++i)
		{
			int smallestNi = _getSmallestChar(N[i]);
			int count = 0;
			for (int j = 0; j < mValues.length; ++j)
			{
				int smallestCharMj = mValues[j];
				if (smallestCharMj < smallestNi)
				{
					count++;
				}
			}
			
			r[i] = count;
		}
		
		return r;
	}
	
	private static int _getSmallestChar(String s)
	{
		char[] a = s.toCharArray();
		Arrays.sort(a);
		char smallestChar = a[0];
		
		int count = 1;
		for (int i = 1; i < a.length; ++i)
		{
			if (a[i] != smallestChar)
			{
				break;
			}
			
			count++;
		}
		
		return count;
	}
	
	public static void main(String[] args)
	{
		 //String A = "abcd aabc bd";
		 //String B = "aaa aa";
		
		String A = "aabc aa bb aaap";
		String B = "a podaaa bbbk";
		
		//String A = "";
		//String B = "";
		
		int[] ret = solution(A, B);
		
		System.out.println(Arrays.toString(ret));
	}
}
