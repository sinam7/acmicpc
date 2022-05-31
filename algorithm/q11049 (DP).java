// https://www.acmicpc.net/problem/11049
/*
    q11049 - 행렬 곱셈 순서
    DP
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;
    int N;
    long[][] arr;
    long[][] dp;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new long[N + 1][2];
        dp = new long[N + 1][N + 1];

        arr[1] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        for (int i = 2; i <= N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            dp[i - 1][i] = arr[i - 1][0] * arr[i - 1][1] * arr[i][1];
        }

        for (int i = 3; i <= N; i++) { // 묶을 행렬 수
            for (int j = 1; j <= N - (i - 1); j++) { // 연산 시작 지점
                int end = j + i - 1;
                dp[j][end] = Integer.MAX_VALUE + 1L;
                for (int mid = j; mid < end; mid++) {
                    dp[j][end] = Math.min(dp[j][end],
                            dp[j][mid] + dp[mid + 1][end] + arr[j][0] * arr[mid][1] * arr[end][1]);
                }
            }
        }

        System.out.println(dp[1][N]);
    }

}