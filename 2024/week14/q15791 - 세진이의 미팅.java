// https://www.acmicpc.net/problem/15791

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Main {

    private static final int MOD = 1000000007;
    static int N, M;

    private static long fastPow(long val, int pow) {
        long result = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                result *= val;
                result %= MOD;
            }
            pow >>= 1;
            val *= val;
            val %= MOD;
        }
        return result;
    }

    private static void solve() {
        N = sc.nextInt();
        M = sc.nextInt();


        // Fermat's little theorem; a^(p-1) === 1 mod p; a^(p-2) === a^(-1) mod p
        // nCm mod p == n!/{(n-m)!m!} mod p == n!{(n-m)!m!}^(-1) mod p == n!{(n-m)!m!}^(p-2) mod p
        // a = (n-m)!m!, a^(-1) = 1/{(n-m)!m!}
        long a = 1;
        for (int i = 1; i <= N; i++) {
            a *= i;
            a %= MOD;
        }
        long b = 1;
        for (int i = 1; i <= N - M; i++) {
            b *= i;
            b %= MOD;
        }
        for (int i = 1; i <= M; i++) {
            b *= i;
            b %= MOD;
        }
        System.out.println((a * fastPow(b, MOD - 2)) % MOD);
    }


    private static boolean isValidIndex(int y, int x, int maxY, int maxX) {
        return 0 <= y && y < maxY && 0 <= x && x < maxX;
    }


    public static void main(String[] args) {
        sc.init();
//        int t = sc.nextInt();
        int t = 1;
        while (t-- > 0) Main.solve();
    }

    static class Edge {
        Pair<Integer> e;
        int univ;

        public Edge(Pair<Integer> e, int univ) {
            this.e = e;
            this.univ = univ;
        }
    }

    static class Pair<T> {
        T first;
        T second;

        Pair(T first, T second) {
            this.first = first;
            this.second = second;
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