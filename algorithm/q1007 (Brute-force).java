// https://www.acmicpc.net/problem/1007
/*
    q1007 - 벡터 매칭
    Brute-force
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
    int T, N;

    int[][] p;
    boolean[] v;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            p = new int[N][2];
            for (int i = 0; i < N; i++) {
                p[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            v = new boolean[N];

            sb.append(dfs(0, 0)).append("\n");

        }
        System.out.println(sb);
    }

    private double dfs(int k, int depth) {
        if (depth == N / 2) return calc();
        double ans = Double.MAX_VALUE;
        for (int i = k; i < N; i++) {
            v[i] = true;
            ans = Math.min(ans, dfs(i + 1, depth + 1));
            v[i] = false;
        }
        return ans;
    }

    private double calc() {
        double vx = 0, vy = 0;
        for (int i = 0; i < N; i++) {
            if (v[i]) {
                vx += p[i][0];
                vy += p[i][1];
            } else {
                vx -= p[i][0];
                vy -= p[i][1];
            }
        }
        return Math.sqrt(vx * vx + vy * vy);
    }

}