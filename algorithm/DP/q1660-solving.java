// https://www.acmicpc.net/problem/1660

import java.io.*;
import java.util.StringTokenizer;

class Main {

    private int solve(int n) {
        // input: parameter n

            // early-solve
            if (n <= 3) System.out.println(n);

        /* algorithm (n starts at 1)
        *  dp[1] = 1, dp[2] = 4
        *  dp[n] = 2 * dp[n-1] - dp[n-2] + n
        *
        * (!) 잘못된 알고리즘: 가장 큰 사면체부터 만드는 것이 최적해가 아님 (반례: 40 -> 제출:35+4+1=3, 정답:20+20=2)        *
        */

        int[] d = new int[300001];
        d[1] = 1; d[2] = 4;

        int i = 2;
        while (d[i] < n) {
            ++i;
            d[i] = 2 * d[i-1] - d[i-2] + i;
        }

        int answer = 0;
        for(;n > 3;i--) {
            int tmp = n / d[i];
            answer += tmp;
            n -= tmp * d[i];
        }
        answer += n;


        // output
        return answer;
    }

    public static void main(String[] args) {
        sc.init();

        System.out.println(new Main().solve(sc.nextInt()));

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