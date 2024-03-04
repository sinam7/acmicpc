// https://www.acmicpc.net/problem/31434

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Math.*;

class Main {

    static int n;
    static int k;
    static int[] cost;
    static int[] boost;
    static int[][] dp; // dp[i][j] : i초 경과 시 수익이 j일 때 보유 가능한 최대 당근 수

    private static void solve() {
        n = sc.nextInt();
        k = sc.nextInt();
        if (k <= 2) {
            System.out.println(k);
            return;
        }

        cost = new int[n + 1];
        boost = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cost[i] = sc.nextInt();
            boost[i] = sc.nextInt();
        }

        // 남은 시간 | 현재 버튼 수익 (최대 Income = 5001 (1 + 50 * 100))
        dp = new int[k + 1][5002];
        Arrays.stream(dp).forEach(arr -> Arrays.fill(arr, -1));

        dp[0][1] = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < 5002; j++) {
                if (dp[i][j] == -1) continue;
                dp[i + 1][j] = max(dp[i + 1][j], dp[i][j] + j);
                for (int idx = 1; idx <= n; idx++) {
//                    if (dp[i][j] < cost[idx]) continue;  // 구매 불가능한 speed 구매 시 잔고가 음수로 가, 초기값 -1 max로 되돌아오므로 굳이 체크할 필요 없음.
                    dp[i + 1][j + boost[idx]] = max(dp[i + 1][j + boost[idx]], dp[i][j] - cost[idx]);
                }
            }
        }

        int answer = Arrays.stream(dp[k]).max().getAsInt();
        System.out.println(answer);

    }

    public static void main(String[] args) {
        sc.init();
        Main.solve();
    }


    // fast input
    static class sc {
        private static BufferedReader br;
        private static StringTokenizer st;

        static void init() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        static String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException ignored) {
                }
            }
            return st.nextToken();
        }

        static int nextInt() {
            return Integer.parseInt(next());
        }

    }
}