// https://www.acmicpc.net/problem/9251
/*
    LCS (최장 공통 부분수열) - DP

    LCS(x, y)   = 0 if x.length == 0 or y.length == 0
                = LCS(x-1, y-1) if x[-1] == y[-1]
                = MAX(LCS(x-1, y), LCS(x, y-1)) else.

    재귀로만 실행 시 TLE -> 메모이제이션 필요
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class q9251 {

    char[] x, y;
    int[][] dp;
    boolean[][] visited;

    public void run() throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1, str2;
        str1 = br.readLine(); str2 = br.readLine();
        x = str1.toCharArray(); y = str2.toCharArray();

        dp = new int[str1.length() + 1][str2.length() + 1];
        visited = new boolean[str1.length() + 1][str2.length() + 1];

        System.out.println(LCS(str1.length() - 1, str2.length() - 1));
    }

    // logic
    private int LCS(int i, int j) {
        if (i == -1 || j == -1) return 0; // 인덱스 체크
        if (visited[i][j]) return dp[i][j]; // 중복 연산 방지
        visited[i][j] = true;

        if (x[i] == y[j]) dp[i][j] = LCS(i - 1, j - 1) + 1;
        else dp[i][j] = Math.max(LCS(i, j - 1), LCS(i - 1, j));

        return dp[i][j];
    }

}
