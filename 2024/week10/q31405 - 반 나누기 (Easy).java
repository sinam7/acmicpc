// https://www.acmicpc.net/problem/31405

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Main {

    static double[][] p;

    private static double area(int a, int b, int c) {
        return Math.abs(p[a][0] * p[b][1] + p[b][0] * p[c][1] + p[c][0] * p[a][1]
                - p[a][1] * p[b][0] - p[b][1] * p[c][0] - p[c][1] * p[a][0]) / 2.0;
    }


    private static void solve() {
        int n = sc.nextInt();
        p = new double[n][2];

        for (int i = 0; i < n; i++) {
            p[i][0] = sc.nextInt();
            p[i][1] = sc.nextInt();
        }

        double[] acc = new double[n]; // accumulated area
        for (int i = 1; i < n - 1; i++) {
            acc[i] = acc[i - 1] + area(0, i, i + 1);
        }

        // find the index when the size is half of the full size
        int idx = 0;
        double half = acc[n - 2] / 2;
        while (acc[idx] <= half) {
            idx++;
        }

        double trg = acc[idx] - acc[idx - 1];
        double portion = (acc[idx] - half) / trg;

        System.out.println("YES");
        System.out.printf("%d %.15f\n", 1, 0.0);
        System.out.printf("%d %.15f\n", idx + 1, 1 - portion);

    }


    public static void main(String[] args) {
        sc.init();
        Main.solve();
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