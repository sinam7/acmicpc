// https://www.acmicpc.net/problem/1725

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.max;


class Main {

    static int N;
    static int[] arr;
    static int[] segtree; // 구간 내 가장 낮은 직사각형의 높이를 가진 인덱스

    static void solve() {
        N = sc.nextInt();
        arr = new int[N + 1];
        segtree = new int[N * 4];
        for (int i = 1; i <= N; i++) arr[i] = sc.nextInt();
        init(1, N, 1);

        System.out.println(bsearch(1, N));

    }

    // 가장 낮은 인덱스를 기준으로 삼분 탐색
    private static int bsearch(int low, int high) {
        if (low == high) return arr[low]; // width = 1
        int shortestIdx = find(1, N, low, high, 1);

        int size = (high - low + 1) * arr[shortestIdx]; // 가로 폭은 파라미터로 계산 가능
        if (low <= shortestIdx - 1) size = max(size, bsearch(low, shortestIdx - 1)); // 가장 낮은 값 인덱스를 포함하지 않는 범위 탐색
        if (shortestIdx + 1 <= high) size = max(size, bsearch(shortestIdx + 1, high)); // 위와 같음

        return size;
    }

    private static int find(int start, int end, int rstart, int rend, int idx) {
        if (rend < start || end < rstart) return -1;
        if (rstart <= start && end <= rend) return segtree[idx];

        int mid = (start + end) / 2;
        int leftIdx = find(start, mid, rstart, rend, idx * 2);
        int rightIdx = find(mid + 1, end, rstart, rend, idx * 2 + 1);

        int ret;
        if (leftIdx == -1) ret = rightIdx;
        else if (rightIdx == -1) ret = leftIdx;
        else if (arr[leftIdx] <= arr[rightIdx]) ret = leftIdx;
        else ret = rightIdx;

        return ret;
    }

    private static int init(int start, int end, int idx) {
        if (start == end) {
            return segtree[idx] = start;
        }

        int mid = (start + end) / 2;
        int leftIdx = init(start, mid, idx * 2);
        int rightIdx = init(mid + 1, end, idx * 2 + 1);
        return segtree[idx] = (arr[leftIdx] <= arr[rightIdx] ? leftIdx : rightIdx);
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