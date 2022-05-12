 // https://www.acmicpc.net/problem/9252
/*
    q9252 - LCS 2
    최장 공통 부분 수열 (LCSubsequence)
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

    BufferedReader br;
    String s1, s2;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // input
        s1 = br.readLine();
        s2 = br.readLine();

        // LCS dp 연산
        int[][] LCS = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                } else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                }
            }
        }

        // dp 테이블에서 LCS 문자열 추출
        int idx = LCS[s1.length()][s2.length()];
        char[] ans = new char[idx--];
        for (int i = s1.length(), j = s2.length(); idx >= 0;) {
            if (LCS[i - 1][j] == LCS[i][j]) {
                --i;
            } else if (LCS[i][j - 1] == LCS[i][j]) {
                --j;
            } else {
                ans[idx--] = s1.charAt(--i);
                --j;
            }
        }

        System.out.println(ans.length + "\n" + String.valueOf(ans));
    }

}