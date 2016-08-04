package chapter10;

import lib.AssortedMethods;
import lib.Coordinate;

/**
 * Created by czl on 13/07/16.
 */
public class TenNineSortedMatrixSearch {



    public static Coordinate findElement(int[][] m, int e) {
        if (m == null) {
            return null;
        }
        int r = m.length - 1;
        int c = m[0].length - 1;
        if (e < m[0][0] || e > m[r][c]) {
            return null;
        }

        Coordinate origin = new Coordinate(0,0);
        Coordinate max = new Coordinate(r, c);
        return findElement(m, origin, max, e);
    }
    
    public static Coordinate findElement(int[][] m, Coordinate min, Coordinate max, int e) {

        if (!min.inbounds(m) || !max.inbounds(m) || m[max.row][max.column] < e ) {
            return null;
        }
        if (m[min.row][min.column] == e) {
            return min;
        }
        if (m[max.row][max.column] == e) {
            return max;
        }

        // find a diagonal coord s.t. it is greater than e. Everything bottom right can be skipped
        Coordinate pivot = (Coordinate) min.clone();
        int diagDist = Math.min(max.row - min.row, max.column - min.column);
        Coordinate end = new Coordinate(pivot.row + diagDist, pivot.column + diagDist);
        Coordinate mid = new Coordinate(0, 0);

        while (pivot.isBefore(end)) {
            mid.setToAverage(pivot, end);
            if (e > m[mid.row][mid.column]) {
                pivot.row = mid.row + 1;
                pivot.column = mid.column + 1;
            } else {
                end.row = mid.row - 1;
                end.column = mid.column - 1;
            }
        }

        return findElementInPartition(m, min, max, pivot, e);
    }

    public static Coordinate findElementInPartition(int[][] m, Coordinate origin, Coordinate max, Coordinate pivot, int e) {
        Coordinate bl_min = new Coordinate(pivot.row, 0);
        Coordinate bl_max = new Coordinate(max.row, pivot.column - 1);
        Coordinate tr_min = new Coordinate(origin.row, pivot.column);
        Coordinate tr_max = new Coordinate(pivot.row - 1, max.column);

        Coordinate element = findElement(m, bl_min, bl_max, e);
        if (element == null) {
            // must be top right
            element = findElement(m, tr_min, tr_max, e);
        }
        return element;
    }

    public static void main(String[] args) {
        int[][] matrix =   {{15, 30,  50,  70,  73},
                            {35, 40, 100, 102, 120},
                            {36, 42, 105, 110, 125},
                            {46, 72, 106, 111, 130},
                            {71, 83, 109, 140, 150}};

        AssortedMethods.printMatrix(matrix);
        int m = matrix.length;
        int n = matrix[0].length;

        int count = 0;
        int littleOverTheMax = matrix[m - 1][n - 1] + 10;
        for (int i = 0; i < littleOverTheMax; i++) {
            Coordinate c = findElement(matrix, i);
            if (c != null) {
                System.out.println(i + ": (" + c.row + ", " + c.column + ")");
                count++;
            }
        }
        System.out.println("Found " + count + " unique elements.");
    }

}
