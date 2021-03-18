// https://www.acmicpc.net/problem/1072

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



class Main {

    /*
    z = y * 100 / x일때,
    z + 1 = (y + k) * 100 / (x + k)를 만족하는 k를 찾는 문제
     */
    void solve() {
        long x = sc.nextInt(), y = sc.nextInt();

        long z = y * 100 / x;
        if (z >= 99) {
            System.out.println(-1);
            return;
        }

        long i1 = x * z + x - 100 * y;
        long i2 = 99 - z;

        int x1 = (int) Math.ceil((double) i1 / i2);
        System.out.println(x1);

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

