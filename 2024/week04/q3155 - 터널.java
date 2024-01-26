// https://www.acmicpc.net/problem/3155

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private void solve() {
        int N = sc.nextInt();
        int[] ceil = new int[N];
        int[] floor = new int[N];
        int[] ans = new int[N];

        for (int i = 0; i < N; ++i) {
            ceil[i] = sc.nextInt();
        }

        for (int i = 0; i < N; ++i) {
            floor[i] = sc.nextInt();
        }

        ans[0] = 0;
        for (int i = 1; i < N - 1; ++i) {
            if (ans[i] >= ceil[i + 1]) { // 현재 예상 경로가 천장에 막히는 경우
                ans[i] = ceil[i + 1] - 1;
            } else if (ans[i] <= floor[i + 1]) { // 현재 예상 경로가 바닥에 막히는 경우
                ans[i] = floor[i + 1] + 1;
            } else { // 진행에 방해받지 않는 경우
                ans[i] = ans[i - 1];
            }
            ans[i + 1] = ans[i]; // 다음 경로는 현재 경로와 같다고 예상
        }

        // print ans array with space
        for (int i = 0; i < N; ++i) {
            System.out.print(ans[i] + " ");
        }

    }


    public static void main(String[] args) {
        sc.init();

        new Main().solve();
    }

    // fast io
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
