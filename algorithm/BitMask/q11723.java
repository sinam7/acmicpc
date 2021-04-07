/* https://www.acmicpc.net/problem/11723
   비트마스크를 이용한 빠른 처리가 필요한 문제.
   TLE를 피하기 위해 StringBuilder를 통해 출력값을 한번에 출력해야 함. (반복 출력은 매우 느림)
 */
import java.io.*;
import java.util.StringTokenizer;

class Main {

    private void solve() {
        StringBuilder sb = new StringBuilder();

        // input
        int m = sc.nextInt();

        /* algorithm */
        int s = 0;
        for (int i = 0; i < m; i++) {
            String cmd = sc.next();
            // (1 << (sc.nextInt() - 1)) : 목표 비트
            switch (cmd) {
                case "add":
                    s |= (1 << (sc.nextInt() - 1));  // 해당 비트를 OR 연산으로 합함
                    break;
                case "remove":
                    s &= ~(1 << (sc.nextInt() - 1));  // 값을 반전해 해당 비트만 0으로 만들어 AND 연산
                    break;
                case "check":
                    // output
                    sb.append((s & (1 << (sc.nextInt() - 1))) == 0 ? "0\n" : "1\n");  // 비트가 겹치면 0은 아님
                    break;
                case "toggle":
                    s ^= (1 << (sc.nextInt() - 1)); // 해당 비트가 아니면 ? ^ 0 = ?이므로 다른 비트에 영향 없음
                    break;
                case "all":
                    s = 0b11111111111111111111;
                    break;
                case "empty":
                    s = 0b00000000000000000000;
                    break;
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        sc.init();

        new Main().solve();
    }

    @SuppressWarnings("unused")
    static class sc {
        private static BufferedReader br;
        private static StringTokenizer st;

        static void init() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        static String readLine() {
            try {
                return br.readLine();
            } catch (IOException ignored) {
            }
            return null;
        }

        static String readLineReplace() {
            try {
                return br.readLine().replaceAll("\\s+", "");
            } catch (IOException ignored) {
            }
            return null;
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

        static double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}