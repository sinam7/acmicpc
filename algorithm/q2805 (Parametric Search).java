// https://www.acmicpc.net/problem/2805

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    private void solve() {
        int N = sc.nextInt(), M = sc.nextInt();
        int[] trees = new int[N];
        for (int i = 0; i < N; i++) {
            trees[i] = sc.nextInt();
        }

        int accept = 0, decline = Arrays.stream(trees).max().getAsInt();
        while (accept < decline) {
            // mid: cut height
            int mid = (accept + decline) / 2;

            // cut trees
            long log = 0;
            for (int tree : trees) {
                if (tree > mid) log += tree - mid;
            }

            // check condition
            if (log >= M) accept = mid + 1; // if accept + 1 == decline, loop breaks
            else decline = mid;
        }

        System.out.println(accept - 1); // answer is accept - 1 because of Line 30
    }

    public static void main(String[] args) {
        sc.init();

        new Main().solve();
    }

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