// https://www.acmicpc.net/problem/1057

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    void solve() {
        int n = sc.nextInt(), a = sc.nextInt(), b = sc.nextInt();
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        int answer = 0;
        while (true) {
            answer++;
            if (a % 2 == 1 && a + 1 == b) break;
            a = (a + 1) / 2;
            b = (b + 1) / 2;
        }
        System.out.println(answer);
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

