// https://www.acmicpc.net/problem/1660
/*
    q1660 - 캡틴 이다솜
    dp
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> tetra = new ArrayList<>();
        tetra.add(0);
        for (int i = 1, a = 1; a <= N; i++, a = (i * (i + 1) * (i + 2)) / 6) tetra.add(a);
        tetra.add(300001);

        int[] dp = new int[N + 1];
        Arrays.fill(dp, 300001);
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; tetra.get(j) <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - tetra.get(j)] + 1);
            }
        }

        System.out.println(dp[N]);
    }

}