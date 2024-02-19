// https://www.acmicpc.net/problem/31416

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private static void solve() {
        int[] arr = new int[4]; // TimeA, TimeB, QuantityA, QuantityB
        for (int i = 0; i < 4; i++) {
            arr[i] = sc.nextInt();
        }

        int timeA = arr[0];
        int timeB = arr[1];
        int quantityA = arr[2];
        int quantityB = arr[3];

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < quantityA; i++) {
            int seniorLasts = timeA * i + timeB * quantityB;
            int juniorLasts = timeA * (quantityA - i);

            answer = Math.min(answer, Math.max(seniorLasts, juniorLasts));
        }

        System.out.println(answer);

    }

    public static void main(String[] args) {
        sc.init();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            Main.solve();
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