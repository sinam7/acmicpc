// https://www.acmicpc.net/problem/31418

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private static void solve() {
        int[] arr = new int[4]; // width, height, numVirus, time
        for (int i = 0; i < 4; i++) {
            arr[i] = sc.nextInt();
        }

        int width = arr[0];
        int height = arr[1];
        int numVirus = arr[2];
        int time = arr[3];

        long answer = 1;
        for (int i = 0; i < numVirus; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            int minX = Math.max(1, x - time);
            int maxX = Math.min(width, x + time);
            int minY = Math.max(1, y - time);
            int maxY = Math.min(height, y + time);

            answer *= ((long) (maxX - minX + 1) * (maxY - minY + 1)) % 998244353;
            answer %= 998244353;
        }

        System.out.println(answer);


    }

    public static void main(String[] args) {
        sc.init();
        Main.solve();
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