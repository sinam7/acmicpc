// https://www.acmicpc.net/problem/9663
/*
    N-Queen - 백트래킹
    2차원 배열로 접근하지 말아야.
    수학적으로 퀸에게 공격받지 않는 조건을 판정해야 함.
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

    int N;
    int answer = 0;
    int[] board; // board[i] = j; i열의 j행에 퀸이 있다.


    public void run() throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N];
        dfs(0);
        System.out.println(answer);
    }

    private void dfs(int queen_placed) {
        if (queen_placed == N) {
            answer++;
            return;
        }
        final int qp = queen_placed; // abbreviate
        for (int x = 0; x < N; x++)
        {
            boolean flag = true;
            for (int y = 0; y < qp && flag; y++) {
                // 세로로 같은 열에 존재 || 대각선에 존재(세로 간격 == 가로 간격)
                if (board[y] == x || Math.abs(qp - y) == Math.abs(x - board[y])) flag = false;
            }

            if (flag) {
                board[qp] = x;
                dfs(qp + 1);
            }
        }
    }

}
