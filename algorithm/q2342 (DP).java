// https://www.acmicpc.net/problem/2342
/*
    q2342 - Dance Dance Revolution
    DP
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;
    final int MAX = 400000;
    int ans = MAX;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] move = new int[input.length];
        System.arraycopy(input, 0, move, 1, input.length - 1);

        int[][][] dp = new int[move.length][5][5];
        for (int[][] ints : dp) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, MAX);
            }
        }
        dp[0][0][0] = 0; // 초기 0,0에서 시작하므로 값 초기화

        for (int i = 1; i < move.length; i++) { // 각 움직임
            for (int r = 0; r < 5; r++) { // 왼발 움직임; 오른발 고정
                for (int k = 0; k < 5; k++) { // 이전 왼발 상태
                    dp[i][move[i]][r] = Math.min(dp[i - 1][k][r] + cost(k, move[i]),
                            dp[i][move[i]][r]);
                }
            }

            for (int l = 0; l < 5; l++) { // 오른발 움직임; 왼발 고정
                for (int k = 0; k < 5; k++) { // 이전 오른발 상태
                    dp[i][l][move[i]] = Math.min(dp[i - 1][l][k] + cost(k, move[i]),
                            dp[i][l][move[i]]);
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ans = Math.min(ans, dp[move.length - 1][i][j]);
            }
        }

        System.out.println(ans);

    }

    private int cost(int from, int to) {
        if (from == 0) {
            if (to == 0) return 0;
            else return 2;
        }
        if (from == to) return 1;
        if ((from + to) % 2 == 1) return 3;
        else return 4;
    }

}