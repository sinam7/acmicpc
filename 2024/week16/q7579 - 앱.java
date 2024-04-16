// https://www.acmicpc.net/problem/7579

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        int[] memory = Arrays.stream(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] cost = Arrays.stream(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        // dp[i][j] = 1~i번 프로그램들을 적절히 종료해 j만큼의 비용을 감당할 때 확보하는 최대 메모리 양.
        final int COST_SUM = Arrays.stream(cost).sum();
        int[][] dp = new int[n + 1][COST_SUM + 1];

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= COST_SUM; j++) {
                if (cost[i] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i]);
                    if (dp[i][j] >= m) ans = Math.min(ans, j);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(ans);

    }

}