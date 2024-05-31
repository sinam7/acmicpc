// https://www.acmicpc.net/problem/2098

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Math.min;

class Main {

    static int N;
    static final int INF = -1; // 16 * 1,000,000 >= LIMIT 15 * 1,000,000 + 1
    static final int NO_PATH = 1_000_000 << 5; // 16 * 1,000,000 >= LIMIT 15 * 1,000,000 + 1
    static long[][] dp, arr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N][];
        for (int i = 0; i < N; i++)
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        dp = new long[N][1 << N];
        Arrays.stream(dp).forEach(dpi -> Arrays.fill(dpi, INF));

        for (int i = 1; i < N; i++) { // 마지막 경로 전처리
            dp[i][(1 << N) - 1] = (arr[i][0] == 0 ? NO_PATH : arr[i][0]);
        }

        long dfs = dfs(0, 1);
        System.out.println(dfs);
    }

    private static long dfs(int curr, int bitmask) {
//        if (bitmask == (1 << N) - 1)
//            return dp[curr][bitmask] = (arr[curr][0] == 0 ? NO_PATH : arr[curr][0]); // 순회 완료; 마지막 최적해는 현재 도시 -> 출발 도시 경로 뿐.
        if (dp[curr][bitmask] != INF)
            return dp[curr][bitmask]; // 이미 방문한 경로

        for (int i = 1; i < N; i++) {
            if ((bitmask & (1 << i)) == 1 << i)
                continue; // 이미 방문한 도시
            if (arr[curr][i] == 0) {
//                dp[curr][bitmask] = NO_PATH;
                continue; // 도로 없음
            }
            if (dp[curr][bitmask] == INF) dp[curr][bitmask] = dfs(i, bitmask | (1 << i)) + arr[curr][i];
            else dp[curr][bitmask] = min(dp[curr][bitmask], dfs(i, bitmask | (1 << i)) + arr[curr][i]);
        }

        if (dp[curr][bitmask] == INF) return dp[curr][bitmask] = NO_PATH;
        return dp[curr][bitmask];
    }
}