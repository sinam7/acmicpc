// https://www.acmicpc.net/problem/31233

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.max;
import static java.lang.Integer.min;


class Main {

    private static void solve() {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n - 2; i++) {
            int tmp = arr[i] + arr[i + 1] + arr[i + 2] - max(arr[i], max(arr[i + 1], arr[i + 2])) - min(arr[i], min(arr[i + 1], arr[i + 2]));
            ans = max(ans, tmp);
        }
        ans = max(ans, min(arr[n - 2], arr[n - 1]));

        System.out.println(ans);
    }


    public static void main(String[] args) {
        sc.init();
        do {
            Main.solve();
        } while (false);
    }

    @SuppressWarnings("unused")
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