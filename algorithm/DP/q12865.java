// https://www.acmicpc.net/problem/12865
/*
    q12865 - 평범한 배낭

    일반적인 0/1 Knapsack Problem 문제.
    idea from ST_ (https://st-lab.tistory.com/141)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }

}

class Solve {

    int N, K;

    int[] W, V; // W[i] = i번 물건의 무게, V[i] = ";
    Integer[][] dp; // dp[i][k] = 최대 무게 i 제한에서 0~k번 물건까지 탐색 후 최적해 저장

    public void run() throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        W = new int[N];
        V = new int[N];

        dp = new Integer[N][K+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(knapsack(N - 1, K));

    }

    private int knapsack(int i, int k) {
        if (i < 0) return 0;

        if (dp[i][k] == null) {
            if (W[i] > k)
                dp[i][k] = knapsack(i - 1, k);

            else
                dp[i][k] = Math.max(V[i] + knapsack(i - 1, k - W[i]), knapsack(i - 1, k));
        }

        return dp[i][k];
    }

}
