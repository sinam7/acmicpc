// https://www.acmicpc.net/problem/11660
/*
    구간 합 구하기 5 - DP
    Scanner는 생각보다 많이 느리다. 시간 초과 방지를 위해 BufferedReader를 사용해야.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }

}

class Solve {
    final int MAX_SIZE = 1024 + 1;
    int n, m;
    int[][] arr = new int[MAX_SIZE][MAX_SIZE];
    boolean[][] visit = new boolean[MAX_SIZE][MAX_SIZE];
    int[][] memo = new int[MAX_SIZE][MAX_SIZE]; // memo[i][j] = arr[0][0]~arr[i][j]의 구간합


    public void run() throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        {
            String[] s = br.readLine().split(" ");
            n = Integer.parseInt(s[0]);
            m = Integer.parseInt(s[1]);
        }

        for (int i = 1; i <= n; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(s[j - 1]);
            }
        }

        // logic - dp()의 구현 방식과 동일
        for (int i = 0; i < m; i++) {
            int x1, y1, x2, y2;
            String[] s = br.readLine().split(" ");

            x1 = Integer.parseInt(s[0]) - 1;
            y1 = Integer.parseInt(s[1]) - 1;
            x2 = Integer.parseInt(s[2]);
            y2 = Integer.parseInt(s[3]);

            // output
            System.out.println(dp(x2, y2) - dp(x1, y2) - dp(x2, y1) + dp(x1, y1));
        }

    }

    // (1,1)부터 해당 지점까지의 구역합을 이용
    private int dp(int i, int j) {
        if (!isValid(i, j)) return 0;
        if (!visit[i][j]) {
            visit[i][j] = true;
            memo[i][j] = dp(i - 1, j) + dp(i, j - 1) - dp(i - 1, j - 1) + arr[i][j];
        }
        return memo[i][j];
    }

    private boolean isValid(int i, int j) {
        return 0 < i && i <= n && 0 < j && j <= n;
    }

}
