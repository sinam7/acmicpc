// https://www.acmicpc.net/problem/7579
/*
    q7579 - 앱
    DP (0/1 배낭 문제)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;
    final int MAX = 10001;
    int N, M;
    int[] memory, cost;
    int leastCost = MAX;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memory = Arrays.stream(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        cost = Arrays.stream(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] dp = new int[N + 1][10000 + 1]; // 1~i번의 프로그램을 종료할 수 있을 때 감당 가능한 비용 j 내에서 확보하는 최대 메모리
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= 10000; j++) {
                if ((j - cost[i]) >= 0){
                    dp[i][j] = Math.max(dp[i - 1][j - cost[i]] + memory[i], dp[i - 1][j]);
                    if (dp[i][j] >= M) {
                        leastCost = Math.min(leastCost, j);
                        break;
                    }
                }
                else dp[i][j] = dp[i - 1][j];

            }
        }

        System.out.println(leastCost);


    }

}