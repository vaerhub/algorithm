package io.arkvaer.algorithm.basic.day32;

public class IndexTree2D {
    private int[][] tree;
    private int[][] nums;
    private int row;
    private int col;

    public IndexTree2D(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        row = matrix.length;
        col = matrix[0].length;
        tree = new int[row + 1][col + 1];
        nums = new int[row][col];

    }


    private int sum(int row, int col) {
        int sum = 0;
        for (int i = row + 1; i > 0; i -= i & (-i)) {
            for (int j = col + 1; j > 0; j -= j & (-j)) {
                sum += tree[i][j];
            }
        }
        return sum;
    }

    private void update(int row, int col, int val) {
        if (this.row == 0 || this.col == 0) {
            return;
        }
        int add = val - nums[row][col];
        nums[row][col] = val;
        for (int i = row + 1; i <= this.row; i += i & (-i)) {
            for (int j = col + 1; j <= this.col; j += j & (-j)) {
                tree[row][col] += add;
            }
        }
    }

    private int sumRegion(int row1, int col1, int row2, int col2) {
        if (this.row == 0 || this.col == 0) {
            return 0;
        }
        return sum(row2, col2) + sum(row1 - 1, col1 - 1) - sum(row1 - 1, col2) - sum(row2, col1 - 1);
    }

}
