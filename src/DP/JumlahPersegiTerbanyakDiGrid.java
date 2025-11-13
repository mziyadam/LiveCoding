package DP;


class JumlahPersegiTerbanyakDiGrid {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int max = 0;
        int [][] dp = new int [rows+1][cols+1];

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = 1 + Math.min(
                            Math.min(dp[i-1][j], dp[i][j-1]),
                            dp[i-1][j-1]
                    );
                }
                max = Math.max(dp[i][j], max);
            }
        }

        return max * max;
    }
}