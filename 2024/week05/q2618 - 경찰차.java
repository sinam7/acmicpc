// https://www.acmicpc.net/problem/2618

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, W;
    static int[][] pos = new int[1000 + 1][2];
    static int[][] dp;

    private static void solve() {
        N = sc.nextInt();
        W = sc.nextInt();

        // dp[a][b] = 1번 차량이 마지막으로 a번 사건을 해결했고, 2번 차량이 마지막으로 b번 사건을 해결했을 때,
        // 남은 모든 사건을 해결하기 위한 최소 거리
        dp = new int[W + 1][W + 1];

        for (int i = 1; i <= W; ++i) {
            pos[i][0] = sc.nextInt();
            pos[i][1] = sc.nextInt();
        }

        for (int i = 0; i <= W; ++i) {
            for (int j = 0; j <= W; ++j) {
                dp[i][j] = -1; // 초기화. 재귀 단계에서 음수 값이 나오지 않으므로 가능.
            }
        }

        System.out.println(getTotalDistance(0, 0));
        getTrace(0, 0);
        // print trace


    }

    // dp[a][b] = 1번 차량이 마지막으로 a번 사건을 해결했고, 2번 차량이 마지막으로 b번 사건을 해결했을 때,
    // 남은 모든 사건을 해결하기 위한 최소 거리
    private static int getTotalDistance(int car1, int car2) {
        if (car1 == W || car2 == W) {
            return 0;
        }

        if (dp[car1][car2] != -1) {
            return dp[car1][car2];
        }

        int next = Math.max(car1, car2) + 1;
        int car1Move = getDistance(car1, next, 1);
        int car2Move = getDistance(car2, next, 2);

        // recursive; car1, car2 중 더 작은 값을 선택
        int car1dp = getTotalDistance(next, car2) + car1Move;
        int car2dp = getTotalDistance(car1, next) + car2Move;

        dp[car1][car2] = Math.min(car1dp, car2dp);
        return dp[car1][car2];
    }

    private static void getTrace(int car1, int car2) {
        if (car1 == W || car2 == W) return;

        int next = Math.max(car1, car2) + 1;
        int car1Move = getDistance(car1, next, 1);
        int car2Move = getDistance(car2, next, 2);


        // 결정된 최단거리를 근거로 다음 사건을 누가 맡는지 결정
        if (car1Move + dp[next][car2] < car2Move + dp[car1][next]) {
            System.out.println(1);
            getTrace(next, car2);
        } else {
            System.out.println(2);
            getTrace(car1, next);
        }
    }

    // a 사건 위치에서 b 사건 위치까지의 거리
    private static int getDistance(int a, int b, int car) {
        if (car == 1 && a == 0) {
            return Math.abs(1 - pos[b][0]) + Math.abs(1 - pos[b][1]);
        } else if (car == 2 && a == 0) {
            return Math.abs(N - pos[b][0]) + Math.abs(N - pos[b][1]);
        }
        int i = Math.abs(pos[a][0] - pos[b][0]) + Math.abs(pos[a][1] - pos[b][1]);
        return i;
    }


    public static void main(String[] args) {
        sc.init();
        Main.solve();
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

/** Test cases
 * 6
 * 4
 * 1 5
 * 1 1
 * 1 5
 * 1 1
 * <p>
 * 6
 * 2
 * 1
 * 2
 * 1
 * <p>
 * ----------------------
 * <p>
 * 10
 * 5
 * 9 2
 * 5 10
 * 9 5
 * 5 2
 * 5 6
 * <p>
 * 28
 * 1
 * 2
 * 1
 * 1
 * 1
 * <p> or
 * 28
 * 1
 * 2
 * 1
 * 2
 * 2
 * <p> or
 * 28
 * 1
 * 2
 * 1
 * 1
 * 2
 */