// https://www.acmicpc.net/problem/1912
package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private void solve() {
        // input
        int n = sc.nextInt();
        int[] dp = new int[n];

            // early-solve
            if (n == 1) {
                System.out.println(sc.nextInt());
                return;
            }

        for (int i = 0; i < n; i++) {
            dp[i] = sc.nextInt();
        }

        /* algorithm
        이전 값이 음수이면 이전 값을 무시한다(합산하지 않는다. 음수인 이전 값을 합하면 연속합이 낮아지므로).
        그렇지 않다면 다음 점화식을 적용한다.

            dp[i] = dp[i-1] + dp[i]
            max = MAX(max, dp[i])

         */
        int max = dp[0];

        for (int i = 1; i < n; i++) {
            if (dp[i-1] > 0) dp[i] += dp[i-1];
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);

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