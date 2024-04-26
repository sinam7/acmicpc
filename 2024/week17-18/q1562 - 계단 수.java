// https://www.acmicpc.net/problem/1562

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n < 10) {
            System.out.println(0);
            return;
        }

        final int MOD = 1000000000;
        // dp[i][j][k] = 길이가 i인 j로 끝나는 수. k: 비트마스크. (1 << l != 0) == 숫자 l이 등장했음.
        int[][][] dp = new int[n + 1][10][1 << 10];

        for (int i = 1; i < 10; i++) {
            dp[1][i][1 << i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k < 1024; k++) {
                    // 각 비트마스크 상태에서 인접한 숫자에 값을 더하며, 비트마스크 마킹.
                    if (j > 0) dp[i][j][k | (1 << j)] += dp[i - 1][j - 1][k];
                    if (j < 9) dp[i][j][k | (1 << j)] += dp[i - 1][j + 1][k];
                    dp[i][j][k | (1 << j)] %= MOD;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 10; i++) {
            ans = (ans + dp[n][i][1023]) % MOD;
        }

        System.out.println(ans);
    }
}