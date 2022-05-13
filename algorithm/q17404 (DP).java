// https://www.acmicpc.net/problem/17404
/*
    q17404 - RGB거리 2
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

    final static int INF = 1000 * 1000 + 1;
    int N, ans = INF;
    int[][] input;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        input = new int[N][];
        for (int i = 0; i < N; i++) {
            input[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        solve();

    }

    private void solve() {
        int[][] dp = new int[N][3];

        for (int k = 0; k < 3; k++) { // 1st house's color
            for (int i = 0; i < 3; i++) {
                if (k == i) dp[0][i] = input[0][i];
                else dp[0][i] = INF; // other house's color are fixed to INF to represent Not Used
            }
            // each ith house's coloring total cost will be
            // the cheapest total cost between two available i-1th house's coloring cost plus
            // current(ith) house's coloring cost.
            for (int i = 1; i < N; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + input[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + input[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + input[i][2];
            }
            for (int i = 0; i < 3; i++) { // Nth house's color
                if (k == i) continue; // skip if 1st house's color equals to Nth house's color
                ans = Math.min(ans, dp[N - 1][i]); // apply to global answer (6 cases; 2 cases per 1st color * 3)
            }
        }

        System.out.println(ans);
    }

}