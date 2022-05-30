// https://www.acmicpc.net/problem/10942
/*
    q10942 - 팰린드롬?
    DP
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
    int N, M;
    int[] arr;
    Boolean[][] dp;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(("0 " +br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new Boolean[N+1][N+1];

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
            sb.append(checkPalindrome(S, E) ? 1 : 0).append("\n");
        }
        System.out.println(sb);

    }

    private boolean checkPalindrome(int s, int e) {
        if (dp[s][e] != null) return dp[s][e]; // memoization
        else if (s >= e) dp[s][e] = true;   // palindrome check
        else if (arr[s] == arr[e]) dp[s][e] = checkPalindrome(s + 1, e - 1); // recursion
        else dp[s][e] = false;

        return dp[s][e];

    }

}