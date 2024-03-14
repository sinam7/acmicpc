// https://www.acmicpc.net/problem/22983
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

import static java.lang.Math.min;


class Main {

    static int N, M;
    static char[][] arr;
    static long[][] dp; // (!) 최대 답이 9004500500이므로, int로는 표현할 수 없다. long으로 선언해야 한다.

    private static void solve() {
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new char[N][M];
        for (int i = 0; i < N; i++) arr[i] = sc.next().toCharArray();

        dp = new long[N][M];
        for (int i = 0; i < N; i++) dp[i][0] = 1;
        for (int i = 0; i < M; i++) dp[0][i] = 1;

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                check(i, j, arr[i][j]);
            }
        }

        System.out.println(Arrays.stream(dp).mapToLong(x -> Arrays.stream(x).sum()).sum());

    }

    private static void check(int i, int j, char c) {
        if (arr[i - 1][j] != c && arr[i][j - 1] != c && arr[i - 1][j - 1] == c) {
            dp[i][j] = min(dp[i - 1][j], min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
        } else dp[i][j] = 1;
    }


    public static void main(String[] args) {
        sc.init();
        int T;
//        T = sc.nextInt();
        do {
            Main.solve();
        } while (true ? false : --T > 0);
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

    }

}