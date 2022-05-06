// https://www.acmicpc.net/problem/11054
/*
    q11054 - 가장 긴 바이토닉 부분 수열
    최장 증가 수열(LIS), DP
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

    BufferedReader br;
    int N;
    int[] input;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        // 최장 증가 수열과 최장 감소 수열의 길이를 구한다.
        int[] ascending = LIS();
        int[] descending = LDS();

        // 두 수열을 합치면 각 위치에서의 바이토닉 수열의 길이를 구할 수 있다.
        int[] answer = ascending.clone();
        for (int i = 0; i < N; i++) {
            // 두 수열은 각각 맨 끝과 맨 처음에 공통 원소를 중복해 가지므로 길이를 1 줄여서 반영한다.
            answer[i] = ascending[i] + descending[i] - 1;
        }

        // 가장 긴 바이토닉 수열의 길이를 찾는다.
        System.out.println(Arrays.stream(answer).max().getAsInt());

    }

    private int[] LIS() { // Bottom-up
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            // 각 원소를 포함하는 부분수열의 길이는 최소 1이다.
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                // 자신(input[i]) 앞에 자신보다 작은 값(input[j])이 있는 경우
                // 현재 최장길이(dp[i])와 작은 값(j)에서의 최장길이(dp[j]) + 1(input[i]를 포함시키는 경우)를 비교한다.
                // Math.max(dp[i], dp[j] + 1)과 동일
                if (input[j] < input[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        return dp;
    }

    private int[] LDS() { // LIS 역순으로 돌리기
        int[] dp = new int[N];
        for (int i = N - 1; i >= 0; i--) { // LIS 탐색 순서를 반대로 돌림
            dp[i] = 1;

            for (int j = N - 1; j > i; j--) { // LIS 탐색 순서를 반대로 돌림
                if (input[j] < input[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        return dp;
    }

}