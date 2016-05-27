package chapter1;

import java.util.Arrays;

public class OneSevenRotateMatrix {

	public OneSevenRotateMatrix() {
		// TODO Need to complete rotate matrix
	}

	public static void rotate(int[][] matrix) {
		if(matrix[0].length != matrix.length)
			return;
		int n = matrix.length;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				int temp = matrix[i][n-j-1];
				
				matrix[j][n-j-1] = matrix[i][n-i-1];
				
				int temp2 = matrix[n-i-1][n-j-1];
				
				matrix[n-i-1][n-j-1] = temp;
				
				temp = matrix[n-i-1][j];
				
				matrix[n-i-1][j] = temp2;
				
				matrix[i][j] = temp;
			}
		}
		
	}
	
	public static void main(String[] args) {
		int[][] matrix = new int[][] {
			{1,2,3},
			{4,5,6},
			{7,8,9}
		};
		rotate(matrix);
		for(int[] i : matrix) {
			System.out.println(Arrays.toString(i));
		}
	}
}
