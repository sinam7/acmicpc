// https://www.acmicpc.net/problem/2239
/*
    q2239 - 스도쿠
    백트래킹

    조건 계산을 위한 정보를 입력 단계에서 미리 저장해 실행 시간을 줄일 수 있다.
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
    int[][] arr = new int[9][9];
    boolean done = false;
    boolean[][] rowValid = new boolean[9][10];
    boolean[][] colValid = new boolean[9][10];
    boolean[][] boxValid = new boolean[9][10];

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            char[] in = br.readLine().toCharArray();
            for (int j = 0; j < 9; j++) {
                arr[i][j] = in[j] - '0';
                toggle(i, j, arr[i][j]);
            }
        }

        calc(0, 0);

    }

    private void calc(int x, int y) {
        if (y == 9) {
            print();
            done = true;
        } else if (arr[y][x] != 0) {
            if (x + 1 == 9) calc(0, y + 1);
            else calc(x + 1, y);
        } else
            for (int i = 1; i <= 9; i++) {
                if (isValid(y, x, i)) {
                    arr[y][x] = i;
                    toggle(y, x, i);

                    if (x + 1 == 9) calc(0, y + 1);
                    else calc(x + 1, y);

                    if (done) return;
                    arr[y][x] = 0;
                    toggle(y, x, i);
                }
            }
    }

    private void print() {
        StringBuilder sb = new StringBuilder();
        for (int[] ints : arr) {
            for (int i : ints) sb.append(i);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    void toggle(int y, int x, int n) { // 해당 위치를 사용처리하거나 사용된 값을 revert함.
        rowValid[y][n] = !rowValid[y][n];
        colValid[x][n] = !colValid[x][n];
        boxValid[y / 3 * 3 + x / 3][n] = !boxValid[y / 3 * 3 + x / 3][n];
    }

    boolean isValid(int y, int x, int n) { // 조건 확인
        return !rowValid[y][n] && !colValid[x][n] && !boxValid[y / 3 * 3 + x / 3][n];
    }

}