// https://www.acmicpc.net/problem/1041

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.*;


class Main {

    private static void solve() {
        int n = sc.nextInt();
        int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt(), d = sc.nextInt(), e = sc.nextInt(), f = sc.nextInt();

        if (n == 1) {
            System.out.println(a + b + c + d + e + f - max(a, max(b, max(c, max(d, max(e, f))))));
            return;
        }

        int min3 = Integer.MAX_VALUE;
        min3 = min(min3, a + b + c);
        min3 = min(min3, a + b + d);
        min3 = min(min3, f + b + d);
        min3 = min(min3, f + b + c);
        min3 = min(min3, a + e + c);
        min3 = min(min3, a + e + d);
        min3 = min(min3, f + e + d);
        min3 = min(min3, f + e + c);

        int min2 = Integer.MAX_VALUE;
        min2 = min(min2, a + b);
        min2 = min(min2, f + b);
        min2 = min(min2, f + e);
        min2 = min(min2, a + e);

        min2 = min(min2, a + c);
        min2 = min(min2, b + c);
        min2 = min(min2, e + c);
        min2 = min(min2, f + c);

        min2 = min(min2, a + d);
        min2 = min(min2, b + d);
        min2 = min(min2, e + d);
        min2 = min(min2, f + d);

        int min1 = Integer.MAX_VALUE;
        min1 = min(min1, a);
        min1 = min(min1, b);
        min1 = min(min1, c);
        min1 = min(min1, d);
        min1 = min(min1, e);
        min1 = min(min1, f);

        long sum3 = min3 * 4L;
        long sum2 = min2 * ((n - 2) * 8L + 4);
        long sum1 = min1 * (((long) (n - 2) * (n - 2) * 5) + (n - 2) * 4L);

        System.out.println(sum3 + sum2 + sum1);

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