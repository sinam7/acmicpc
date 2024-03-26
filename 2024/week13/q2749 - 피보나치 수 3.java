// https://www.acmicpc.net/problem/2749

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static long N;
    static final long MOD = 1_000_000;

    private static long[][] matrixMultply(long[][] a, long[][] b) {
        long[][] result = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                    result[i][j] %= MOD;
                }
            }
        }
        return result;
    }
    private static void solve() {
        N = sc.nextLong();

        long[][] matrix = {{1, 1},
                           {1, 0}};
        long[][] result = {{1, 0},
                           {0, 1}};
        while (N > 0) {
            if (N % 2 == 1) {
                result = matrixMultply(result, matrix);
            }
            matrix = matrixMultply(matrix, matrix);
            N /= 2;
        }
        System.out.println(result[1][0]);

    }

    public static void main(String[] args) {
        sc.init();
        int T = 0;
        boolean nowDebugging = false;
//        T = sc.nextInt();
        do {
            Main.solve();
        } while (nowDebugging || --T > 0);
    }

    @SuppressWarnings("unused")
    static class Pair<T> {
        T first;
        T second;

        Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?> pair = (Pair<?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
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

        public static long nextLong() {
            return Long.parseLong(next());
        }
    }

}