// https://www.acmicpc.net/problem/11964

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

import static java.lang.Math.*;


class Main {

    static int T, A, B;
    static int[][] dp; // dp[current fullness][drank water]

    private static void solve() {
        T = sc.nextInt();
        A = sc.nextInt();
        B = sc.nextInt();

        // dp[i][j] = max fullness when current fullness is i and status j.
        // j indicates whether she drank water(1) or not yet(0).

        // the answer is max dp value (which is smaller or same as T).
        // so, we should track max dp value, and also stop the loop when dp value is reached to T.
        // when the value is larger than T, we should continue the loop (without saving the value).
        dp = new int[T + 1][2];
        System.out.println(dp(0, 0));

    }

    private static int dp(int currentFullness, int drankWater) {
        if (currentFullness > T) return 0;
        if (currentFullness == T) {
            System.out.println(T);  // max answer reached; print and exit.
            System.exit(0);
        }
        if (dp[currentFullness][drankWater] != 0) return dp[currentFullness][drankWater];

        // task 1: eat A and increase current fullness by A
        // task 2: eat B and increase current fullness by B
        // task 3: drink water and divide current fullness by 2 (round down) if she didn't drink water yet.
        int task1 = max(currentFullness, dp(currentFullness + A, drankWater));
        int task2 = max(currentFullness, dp(currentFullness + B, drankWater));
        int task3 = drankWater == 1 ? currentFullness : dp(currentFullness / 2, 1);

        dp[currentFullness][drankWater] = max(task1, max(task2, task3));
        return dp[currentFullness][drankWater];
    }


    public static void main(String[] args) {
        sc.init();
        int T;
//        T = sc.nextInt();
        do {
            Main.solve();
        } while (true ? false : --T > 0);
    }

    @SuppressWarnings("unused")
    static class Pair<T> {
        T first;
        T second;

        Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?> pair = (Pair<?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
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