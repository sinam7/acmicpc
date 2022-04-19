// https://www.acmicpc.net/problem/17144
/*
    q17144 - 미세먼지 안녕!
    구현, 시뮬레이션
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
    int R, C, T;
    int[][] arr;
    Point deviceUp, deviceDown;

    int[] dy = new int[]{0, 1, 0, -1};
    int[] dx = new int[]{1, 0, -1, 0};

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[R][C];

        for (int i = 0; i < R; i++) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < C; j++) {
                arr[i][j] = s[j];
                if (s[j] == -1) {
                    arr[i][j] = 0;
                    if (deviceUp == null) deviceUp = new Point(j, i);
                    else deviceDown = new Point(j, i);
                }
            }
        }

        while (T-- > 0) {
            spread();
        }

        int ans = 0;
        for (int i = 0; i < R; i++) {
            ans += Arrays.stream(arr[i]).sum();
        }

        System.out.println(ans);


    }

    private void spread() {
        int[][] next = new int[R][C];
        for (int i = 0; i < R; i++) {
            next[i] = arr[i].clone();
        }

        // 확산
        for (int i = 0; i < R; i++) { // y
            for (int j = 0; j < C; j++) { // x
                if (arr[i][j] >= 5) { // 5 이상이어야 확산할 미세먼지가 있다.
                    // spread in other board; next
                    int spreadOut = arr[i][j] / 5;
                    for (int k = 0; k < 4; k++) {
                        int ddx = j + dx[k];
                        int ddy = i + dy[k];

                        if (isValid(ddx, ddy)) {
                            next[ddy][ddx] += spreadOut;
                            next[i][j] -= spreadOut;
                        }
                    }
                }
            }
        }

        // 정화
        next[deviceUp.y - 1][deviceUp.x] = 0;
        next[deviceDown.y + 1][deviceDown.x] = 0;

        for (int i = deviceUp.y; i > 0; i--) next[i][deviceUp.x] = next[i - 1][deviceUp.x];
        for (int i = deviceDown.y; i < R - 1; i++) next[i][deviceUp.x] = next[i + 1][deviceUp.x];

        for (int i = 0; i < C - 1; i++) {
            next[0][i] = next[0][i + 1];
            next[R - 1][i] = next[R - 1][i + 1];
        }

        for (int i = 0; i < deviceUp.y; i++) next[i][C - 1] = next[i + 1][C - 1];
        for (int i = R - 1; i > deviceDown.y; i--) next[i][C - 1] = next[i - 1][C - 1];

        for (int i = C - 1; i > 0; i--) {
            next[deviceUp.y][i] = next[deviceUp.y][i - 1];
            next[deviceDown.y][i] = next[deviceDown.y][i - 1];
        }

        next[deviceUp.y][deviceUp.x + 1] = 0;
        next[deviceDown.y][deviceDown.x + 1] = 0;

        // 적용
        for (int i = 0; i < R; i++) {
            arr[i] = next[i].clone();
        }

    }

    private boolean isValid(int x, int y) {
        return 0 <= x && x < C && 0 <= y && y < R &&
                !(deviceDown.x == x && deviceDown.y == y) &&
                !(deviceUp.x == x && deviceUp.y == y);
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}