package io.arkvaer.algorithm.basic.day21;

/**
 * 给定一个二维数组matrix，
 * 一个人必须从左上角出发，最后到达右下角沿途只可以向下或者向右走，
 * 沿途的数字都累加就是距离累加和返回最小距离累加和
 * 动态规划 空间压缩
 */
public class MinPathSum {
    public static int minPathSum1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + matrix[i][j];
            }
        }

        return dp[row - 1][col - 1];
    }


    public static int minPathSum2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int col = matrix.length;
        int row = matrix[0].length;
        int[] dp = new int[col];
        dp[0] = matrix[0][0];
        for (int i = 1; i < col; i++) {
            dp[i] = dp[i - 1] + matrix[0][i];
        }
        for (int i = 1; i < row; i++) {
            dp[0] +=matrix[i][0];
            for (int j = 1; j < col; j++) {
                dp[j] = Math.min(dp[j-1], dp[j]) + matrix[i][j];
            }
        }
        return dp[col - 1];
    }

    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));
    }
}
