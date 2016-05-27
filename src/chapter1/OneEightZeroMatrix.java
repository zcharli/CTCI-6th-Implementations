package chapter1;

import java.util.Arrays;

public class OneEightZeroMatrix {

	public OneEightZeroMatrix() {
		// TODO Auto-generated constructor stub
	}
	
	public static void zeroMatrix(int[][] matrix) {
		if(matrix.length == 0 || matrix[0].length == 0)
			return;
		int n =	matrix.length;
		int m = matrix[0].length;
		
		for(int i=0;i<n;i++) {
			for(int j =0; j<m;j++) {
				if(matrix[i][j] == 0) {
					for(int k=0; k< m;k++) {
						matrix[i][k] = 0;
					}
					for(int k=0; k<n;k++) {
						matrix[k][j] = 0;
					}
					return;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		int[][] matrix = new int[][] {
			{1,2,3},
			{4,0,6},
			{7,8,9},
			{10,11,12}
		};
		zeroMatrix(matrix);
		for(int[] i : matrix) {
			System.out.println(Arrays.toString(i));
		}
		
	}
}
