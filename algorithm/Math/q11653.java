package Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private void solve(int n) {
        if (n == 1) return;
        int val = n;

        for (int i = 2; i <= Math.sqrt(n) + 1; i++) {
            while (val % i == 0) {
                System.out.println(i);
                val /= i;
            }
        }
        if (val != 1) System.out.println(val);

    }

    public static void main(String[] args) {
        sc.init();
        int n = sc.nextInt();

        new Main().solve(n);
    }

    private <T extends Comparable<T>> T max(T[] arr) {
        if (arr.length == 0) return null;
        if (arr.length == 1) return arr[0];

        T max = arr[0];
        for (T element : arr) {
            if (element.compareTo(max) > 0) max = element;
        }

        return max;
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