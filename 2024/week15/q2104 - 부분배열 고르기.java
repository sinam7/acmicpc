// https://www.acmicpc.net/problem/2104

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.max;

class Main {

    static int N;
    static int[] arr;
    static long[] sumtree;
    static int[] minIdxtree;

    static void solve() {
        N = sc.nextInt();
        arr = new int[N + 1];
        sumtree = new long[N * 4];
        minIdxtree = new int[N * 4];
        for (int i = 1; i <= N; i++) arr[i] = sc.nextInt();

        // ============================

        initSumtree(1, N, 1);
        initMinIdxtree(1, N, 1);

        System.out.println(bsearch(1, N));

    }

    private static long bsearch(int start, int end) {
        int minIdx = findMinIdx(1, N, 1, start, end);

        long score = findSum(1, N, 1, start, end) * arr[minIdx];
        if (start <= minIdx - 1) score = max(score, bsearch(start, minIdx - 1));
        if (minIdx + 1 <= end) score = max(score, bsearch(minIdx + 1, end));

        return score;
    }

    private static long initSumtree(int start, int end, int idx) {
        if (start == end) {
            return sumtree[idx] = arr[start];
        }

        int mid = (start + end) / 2;
        return sumtree[idx] = initSumtree(start, mid, idx * 2) + initSumtree(mid + 1, end, idx * 2 + 1);
    }

    private static int initMinIdxtree(int start, int end, int idx) {
        if (start == end) {
            return minIdxtree[idx] = start;
        }

        int mid = (start + end) / 2;
        int left = initMinIdxtree(start, mid, idx * 2);
        int right = initMinIdxtree(mid + 1, end, idx * 2 + 1);

        return minIdxtree[idx] = (arr[left] <= arr[right]) ? left : right;
    }

    private static long findSum(int start, int end, int idx, int rstart, int rend) {
        if (rend < start || end < rstart) return 0;
        if (rstart <= start && end <= rend) return sumtree[idx];

        int mid = (start + end) / 2;
        return findSum(start, mid, idx * 2, rstart, rend) + findSum(mid + 1, end, idx * 2 + 1, rstart, rend);
    }

    private static int findMinIdx(int start, int end, int idx, int rstart, int rend) {
        if (rend < start || end < rstart) return -1;
        if (rstart <= start && end <= rend) return minIdxtree[idx];

        int mid = (start + end) / 2;
        int left = findMinIdx(start, mid, idx * 2, rstart, rend);
        int right = findMinIdx(mid + 1, end, idx * 2 + 1, rstart, rend);

        int ret;
        if (left == -1) ret = right;
        else if (right == -1) ret = left;
        else if (arr[left] <= arr[right]) ret = left;
        else ret = right;

        return ret;
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