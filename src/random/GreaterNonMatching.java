package random;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author cli
 * @since 6/27/17
 */
public class GreaterNonMatching
{
	
	public static void main(String[] args)
	{
		System.out.println(Arrays.toString(solution2(new int[] {1,2,3,5,9,6,3,2,1}, 3)));
	}
	
	private static final int[] NO_RESULT = {};
	public static int[] solution2(int[] A, int K) {
		// write your code in Java SE 8
		int size = A.length;
		
		if(K<1 || K>size) {
			// does not make sense
			return NO_RESULT;
		}

		for (int i=0; i<K; i++) {
			// start checking
			if(A[i] > A[size-K+i]) {
				//first one is bigger
				return Arrays.copyOfRange(A, 0, K);
			}
			else if(A[i] < A[size-K+i]) {
				//second
				return Arrays.copyOfRange(A, size-K, size);
			}
		}
		return Arrays.copyOfRange(A, 0, K);
	}
}


