// https://www.acmicpc.net/problem/11404
/*
    q11404 - 플로이드
    Floyd-Warshall
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }

}

class Solve {

    int N, M;
    Integer[][] input;
    Integer[][] dp;

    public void run() throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        input = new Integer[N + 1][N + 1];
        dp = new Integer[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            input[s[0]][s[1]] = (input[s[0]][s[1]] == null) ? s[2] : Math.min(input[s[0]][s[1]], s[2]);
        }

        // algorithm
        floydWarshall();

        // output
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(dp[i][j] == null ? 0 : dp[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    private void floydWarshall() {
        dp = input.clone();

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;   // 출발지와 도착지가 같은 경우는 없으므로 고려하지 않음

                    if (dp[i][j] == null) { // 직통연결이 없을 때
                        if (dp[i][k] != null && dp[k][j] != null) // 우회로가 있으면
                            dp[i][j] = dp[i][k] + dp[k][j]; // 우회로 비용을 직통 연결 비용으로 적용
                    } else if (dp[i][k] != null && dp[k][j] != null) { // 직통연결과 우회로 모두 있으면
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]); // 최솟값으로 갱신
                    }
                }
            }
        }
    }
}
