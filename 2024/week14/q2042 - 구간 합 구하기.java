// https://www.acmicpc.net/problem/2042

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Main {

    static int N, Q;
    static long[] arr;
    static long[] segtree;

    static void solve() {
        N = sc.nextInt();
        Q = sc.nextInt() + sc.nextInt();

        arr = new long[N + 1];
        segtree = new long[N * 4];

        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextLong();
        }

        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int queryType = sc.nextInt();
            int a = sc.nextInt();
            long b = sc.nextLong();
            if (queryType == 1) {
                update(1, N, 1, a, b - arr[a]);
                arr[a] = b;
            } else {
                sb.append(find(1, N, 1, a, (int) b)).append('\n');
            }
        }
        System.out.println(sb);
    }

    public static long find(int start, int end, int idx, int rstart, int rend) {
        if (rend < start || end < rstart) return 0;
        if (rstart <= start && end <= rend) return segtree[idx];

        int mid = (start + end) / 2;
        return find(start, mid, idx * 2, rstart, rend) + find(mid + 1, end, idx * 2 + 1, rstart, rend);
    }

    private static void update(int start, int end, int idx, int target, long diff) {
        if (target < start || end < target) return;

        segtree[idx] += diff;

        if (start == end) return;

        int mid = (start + end) / 2;
        update(start, mid, idx * 2, target, diff);
        update(mid + 1, end, idx * 2 + 1, target, diff);
    }

    private static long init(int start, int end, int idx) {
        if (start == end) {
            return segtree[idx] = arr[start];
        }

        int mid = (start + end) / 2;
        return segtree[idx] = init(start, mid, idx * 2) + init(mid + 1, end, idx * 2 + 1);
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

        static void init(InputStreamReader in) {
            br = new BufferedReader(in);
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