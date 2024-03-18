// https://www.acmicpc.net/contest/view/1256

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;


class Main {

    static int N;
    static int[] lis;
    static int topIdx;

    private static void solve() {
        N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = sc.nextInt();

        lis = new int[N];
        topIdx = 0;
        lis[0] = arr[0];
        for (int i = 1; i < N; i++) {
            if (lis[topIdx] < arr[i]) {
                lis[++topIdx] = arr[i];
            } else {
                bsearch(arr[i]);
            }
        }

        System.out.println(topIdx + 1);

    }

    private static void bsearch(int target) {
        int left = 0;
        int right = topIdx;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (lis[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        lis[right] = target;
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

    }

}