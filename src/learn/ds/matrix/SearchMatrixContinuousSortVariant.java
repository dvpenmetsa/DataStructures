package learn.ds.matrix;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * Example 1:
 *
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 * Example 2:
 *
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 *
 * https://leetcode.com/problems/search-a-2d-matrix/
 * https://www.youtube.com/watch?v=FOa55B9Ikfg&t=581s
 */
public class SearchMatrixContinuousSortVariant {

    /**
     *   Time Complexity : log(m n)
     *
     *   Here is a very pure 2D matrix to exemplify our mapping system. each cell has its conceptual
     *   index if we flattened the 2D matrix into a 1D sorted array.
     *   [ 0, 1, 2 ]
     *   [ 3, 4, 5 ]
     *   [ 6, 7, 8 ]
     *   totalRows = 3
     *   totalColumns = 3
     *   Coordinate Mappings -> (row, col)
     *   [ (0, 0), (0, 1), (0, 2) ]
     *   [ (1, 0), (1, 1), (1, 2) ]
     *   [ (2, 0), (2, 1), (2, 2) ]
     *   Given a 1D index position .. we declare our mapping equations:
     *   row in 2D matrix: 1DIndex / totalColumns
     *   col in 2D matrix: 1DIndex % totalColumns
     *   Example:
     *   We want to find the coordinate for element 6.
     *   row in 2D matrix: (6) / 3 = 2
     *   col in 2D matrix: (6) % 3 = 0
     *
     *   Output: (2, 0)
     *   We are correct.
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean search(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }

        int totalRows = matrix.length;
        int totalColumns = matrix[0].length;

    /*
      The left and right pointer to our search. We imagine a visualization
      of a 1D array and will use our mapping system to convert these indices
      into concrete locations in the 2D matrix.
      The the last position in the matrix if we consider it as array will be
      rows * cols - 1 indices.
      Same as an array...an array's last index is arr.length - 1.
    */
        int left = 0;
        int right = totalRows * totalColumns - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;

      /*
        We need to map from our conceptualized 1D array back to our 2D matrix to index
        into it.
        Look above for the mapping explanation
      */
            int middleElementValue = matrix[middle / totalColumns][middle % totalColumns];

      /*
        3 possibilities:
          1.) We found the target
          2.) We went too high in value...go "left"
          3.) We went too low in value...go "right"
      */
            if (middleElementValue == target) {
                return true; // element found
            } else if (middleElementValue < target) {
                left = middle + 1; // go right
            } else {
                right = middle - 1; // go left
            }
        }

        return false;
    }
}
