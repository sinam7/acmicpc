// https://www.acmicpc.net/problem/31235

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Math.max;


class Main {

    private static void solve() {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int[] sol = new int[n];
        sol[0] = 1;
        int mx = arr[0];
        for (int i = 1; i < n; i++) {
            if (mx > arr[i]) {
                sol[i] = sol[i - 1] + 1;
            } else {
                mx = max(mx, arr[i]);
                sol[i] = 1;
            }
        }

        System.out.println(Arrays.stream(sol).max().getAsInt());
    }


    public static void main(String[] args) {
        sc.init();
        Main.solve();
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