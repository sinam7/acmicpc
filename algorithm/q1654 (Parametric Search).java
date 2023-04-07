// https://www.acmicpc.net/problem/1654

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    private void solve() {
        int K, N;
        K = sc.nextInt(); // K: 주어진 랜선의 개수
        N = sc.nextInt(); // N: 만들어야 할 동일한 길이의 랜선의 개수

        long[] length = new long[K]; // 주어진 각 랜선의 길이
        for (int i = 0; i < K; i++) length[i] = sc.nextLong();

        // parametric search;
        // accept: 랜선의 개수 >= N을 만족하는 최소 길이
        // decline: 랜선의 개수 < N을 만족하는 최소 길이
        long accept = 0, decline = Arrays.stream(length).max().getAsLong() + 1, mid;

        while (accept < decline) {
            mid = (accept + decline) / 2;

            // 랜선을 동일한 길이(mid)로 잘라 얻은 수 계산
            long cut = 0;
            for (int i = 0; i < K; i++) cut += length[i] / mid;

            // 목표 개수 N개보다 많거나 같게 얻으면 accept를 수정, 아니면 decline을 수정
            if (cut >= N) accept = mid + 1;
            else /*(cut < N)*/ decline = mid;
        }

        // N개의 랜선을 동일한 길이의 랜선 K개로 잘랐을 때 랜선의 최대 길이
        System.out.println(decline - 1);
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

    }
}