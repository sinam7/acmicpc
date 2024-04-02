// https://www.acmicpc.net/problem/15791

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Main {

    static int N, M;
    static int[] arr;
    static int[] segtree;


    static void solve() {
        N = sc.nextInt();
        arr = new int[N + 1];
        segtree = new int[(N + 1) * 4];
        for (int i = 1; i <= N; i++) arr[i] = sc.nextInt();
        init(1, N, 1);

        M = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            if (sc.nextInt() == 1) {
                int idx = sc.nextInt();
                int value = sc.nextInt();
                arr[idx] = value;
                update(1, N, 1, idx);
            } else {
                // search the minimum's index
                sb.append(segtree[1]).append("\n");
            }
        }
        System.out.println(sb);
    }

    // segment tree
    private static int init(int start, int end, int idx) {
        if (start == end) {
            segtree[idx] = start;
            return start;
        }

        int mid = (start + end) / 2;
        int leftIdx = init(start, mid, idx * 2);
        int rightIdx = init(mid + 1, end, idx * 2 + 1);

        if (arr[leftIdx] <= arr[rightIdx]) segtree[idx] = leftIdx;
        else segtree[idx] = rightIdx;

        return segtree[idx]; // segtree[idx] = 세그트리 노드 범위 내 최소 arr 값의 index
    }

    private static int update(int start, int end, int idx, int target) {
        if (target < start || end < target) return segtree[idx];
        if (start == end) {
            segtree[idx] = target;
            return segtree[idx];
        }

        int mid = (start + end) / 2;
        int leftIdx = update(start, mid, idx * 2, target);
        int rightIdx = update(mid + 1, end, idx * 2 + 1, target);

        if (arr[leftIdx] <= arr[rightIdx]) segtree[idx] = leftIdx;
        else segtree[idx] = rightIdx;

        return segtree[idx];
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

        static int nextInt() {
            return Integer.parseInt(next());
        }

    }

}