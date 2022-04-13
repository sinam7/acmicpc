// https://www.acmicpc.net/problem/10830
/*
    q10830 - 행렬 제곱
    분할 정복, 선형대수학
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
    int[][] matrix;
    HashMap<Long, int[][]> memo;
    int N;
    long B;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        matrix = new int[N][N];
        memo = new HashMap<>();

        for (int i = 0; i < N; i++) {
            System.arraycopy(
                    Arrays.stream(br.readLine().split(" ")).mapToInt(o -> Integer.parseInt(o) % 1000).toArray(),
                    0, matrix[i], 0, N);
        }

        memo.put(1L, matrix);
        int[][] answer;
        if (B % 2 == 0) answer = matrixMultiply(B / 2, B / 2);
        else answer = matrixMultiply((B / 2) + 1, B / 2);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) sb.append(answer[i][j]).append(" ");
            sb.append("\n");
        }

        System.out.println(sb);

    }


    private int[][] matrixMultiply(long a, long b) {
        if (memo.get(a + b) != null) return memo.get(a + b);

        int[][] matA, matB;

        if (memo.get(a) == null) {
            if (a % 2 == 0) memo.put(a, matrixMultiply(a / 2, a / 2));
            else memo.put(a, matrixMultiply((a / 2) + 1, a / 2));
        }
        matA = memo.get(a);

        if (memo.get(b) == null) {
            if (b % 2 == 0) memo.put(b, matrixMultiply(b / 2, b / 2));
            else memo.put(b, matrixMultiply((b / 2) + 1, b / 2));
        }
        matB = memo.get(b);

        int[][] ans = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans[i][j] = 0;
                for (int k = 0; k < N; k++) {
                    ans[i][j] += matA[i][k] * matB[k][j];
                    ans[i][j] %= 1000;
                }
            }
        }

        memo.put(a + b, ans);
        return memo.get(a + b);
    }
}