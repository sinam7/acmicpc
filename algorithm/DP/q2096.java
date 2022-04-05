// https://www.acmicpc.net/problem/2096
/*
    q2096 - 내려가기
    DP

    메모리 제한이 적으므로 최소한의 dp 배열을 할당해 계속 갱신해야 한다.
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

    int[] maxDp, minDp;
    int N;

    public void run() throws IOException {

        // input
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 윈도우 크기가 최대 3 (직하단, 좌하단, 우하단)
        maxDp = new int[3];
        minDp = new int[3];

        int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.arraycopy(in, 0, maxDp, 0, 3);
        System.arraycopy(in, 0, minDp, 0, 3);

        for (int i = 1; i < N; i++) {
            in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            calculate(maxDp, in, Math::max);
            calculate(minDp, in, Math::min);
        }

        System.out.println( Arrays.stream(maxDp).max().orElse(-1) +
                            " " +
                            Arrays.stream(minDp).min().orElse(-1));

    }

    private void calculate(int[] dp, int[] in, Doable Do) {
        int tmp0 = dp[0], tmp2 = dp[2];

        dp[0] = Do.doSmth(dp[0], dp[1]) + in[0];
        dp[2] = Do.doSmth(dp[1], dp[2]) + in[2];
        dp[1] = Do.doSmth(tmp0, Do.doSmth(tmp2, dp[1])) + in[1];
    }

    interface Doable {
        int doSmth(int o1, int o2);
    }

}