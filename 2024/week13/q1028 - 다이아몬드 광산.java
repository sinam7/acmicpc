// https://www.acmicpc.net/problem/31427

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.min;


class Main {

    static int R, C;
    static char[][] arr;
    static int[][] ld, rd;

    private static void solve() {
        R = sc.nextInt();
        C = sc.nextInt();

        arr = new char[R][C];
        ld = new int[R][C];
        rd = new int[R][C];
        for (int i = 0; i < R; i++) System.arraycopy(sc.next().toCharArray(), 0, arr[i], 0, C);

        // O(log(RC))?
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                calculate(i, j);
            }
        }

        final int MAX_DIAMOND = (min(R, C) + 1) / 2;
        int ans = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                int maxLength = min(ld[r][c], rd[r][c]);
                for (int i = maxLength - 1; i >= 0; i--) {
                    if (i <= rd[r + i][c - i] - 1 && i <= ld[r + i][c + i] - 1) {
                        ans = Math.max(ans, i + 1);
                        if (ans == MAX_DIAMOND) {
                            System.out.println(ans);
                            return;
                        }
                    }
                }
            }
        }

        System.out.println(ans);
    }

    private static void calculate(int r, int c) {
        if (arr[r][c] == '0') return;

        if (isValidIndex(r - 1, c + 1) && arr[r - 1][c + 1] == '1') {
            ld[r][c] = ld[r - 1][c + 1] - 1;
        } else {
            for (int i = 0; i < R; i++) {
                if (!isValidIndex(r + i, c - i) || arr[r + i][c - i] != '1') break;
                ld[r][c]++;
            }
        }

        if (isValidIndex(r - 1, c - 1) && arr[r - 1][c - 1] == '1') {
            rd[r][c] = rd[r - 1][c - 1] - 1;
        } else {
            for (int i = 0; i < R; i++) {
                if (!isValidIndex(r + i, c + i) || arr[r + i][c + i] != '1') break;
                rd[r][c]++;
            }
        }
    }

    private static boolean isValidIndex(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }


    public static void main(String[] args) {
        sc.init();
        Main.solve();
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