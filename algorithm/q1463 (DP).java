// https://www.acmicpc.net/problem/1660

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    /*
    Bottom-up 방식
    1에서 N까지 역산했을때 최소한의 연산으로 올라가는 방식을 계산한다.
    일반적으로 (3)번 연산을 사용한다고 가정하며,
    현재 연산중인 수가 2나 3으로 나누어 떨어지면 그 연산을 수행한 것과 (3)번 연산 중 최솟값을 취한다.
     */
    private void solve() {
        int n = sc.nextInt();

        int[] dp = new int[1000001];
        for (int i = 2; i <= n; i++) { // dp[1] = 0. 1은 연산이 필요없음에 주의
            dp[i] = dp[i-1] + 1;
            if (i % 2 == 0) dp[i] = Math.min(dp[i / 2] + 1, dp[i]);
            if (i % 3 == 0) dp[i] = Math.min(dp[i / 3] + 1, dp[i]);
        }
        System.out.println(dp[n]);
    }

    public static void main(String[] args) {
        sc.init();

        new Main().solve();
    }

    @SuppressWarnings("unused")
    static class sc {
        private static BufferedReader br;
        private static StringTokenizer st;

        static void init() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        static String readLine() {
            try {
                return br.readLine();
            } catch (IOException ignored) {
            }
            return null;
        }

        static String readLineReplace() {
            try {
                return br.readLine().replaceAll("\\s+", "");
            } catch (IOException ignored) {
            }
            return null;
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

        static long nextLong() {
            return Long.parseLong(next());
        }

        static int nextInt() {
            return Integer.parseInt(next());
        }

        static double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}