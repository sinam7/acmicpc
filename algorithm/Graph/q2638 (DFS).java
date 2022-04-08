// https://www.acmicpc.net/problem/2638
/*
    q2638 - 치즈
    BFS
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
    int N, M;

    final int[] diy = new int[]{0, 1, 0, -1};
    final int[] dix = new int[]{1, 0, -1, 0};

    int[][] arr;
    ArrayList<Point> removeCheese;
    int rCRemoved;
    int cheeseAmount;
    int age;


    public void run() throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rCRemoved = 0;
        cheeseAmount = 0;
        age = 0;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(s,
                    0, arr[i], 0, M);
            cheeseAmount += Arrays.stream(s).sum();
        }

        // 1. 외부 공기 체크
        arr[0][0] = 9;
        arr[0][M - 1] = 9;
        arr[N - 1][0] = 9;
        arr[N - 1][M - 1] = 9;

        bfs(0, 0);
        bfs(M - 1, 0);
        bfs(0, N - 1);
        bfs(M - 1, N - 1);

        removeCheese = new ArrayList<>();

        while (removeCheese.size() < cheeseAmount) {

            // 2. 조건에 맞는 치즈 녹이기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 1)
                        cheeseRemove(j, i);
                }
            }

            age++;
            if (removeCheese.size() >= cheeseAmount) {
                System.out.println(age);
                return;
            }

            for (int i = rCRemoved; i < removeCheese.size(); i++) {
                arr[removeCheese.get(i).y][removeCheese.get(i).x] = 9;
            }



            // 3. 치즈가 녹은 이후 새로 외부와 연결된 내부 공기 체크 -> 치즈가 모두 녹을때까지 2번으로 돌아가 반복
            for (int i = rCRemoved; i < removeCheese.size(); i++) {
                bfs(removeCheese.get(i).x, removeCheese.get(i).y);
            }

            rCRemoved = removeCheese.size();
        }

        System.out.println(age);
    }

    private void bfs(int x, int y) {
        if (!isValid(x, y)) return;

        Queue<Point> q = new LinkedList<>();
        boolean[][] v = new boolean[N][M];

        q.offer(new Point(x, y));
        v[y][x] = true;

        while (!q.isEmpty()) {
            Point curr = q.poll();

            for (int i = 0; i < 4; i++) {
                int dx = curr.x + dix[i], dy = curr.y + diy[i];

                if (isValid(dx, dy) && !v[dy][dx] && arr[dy][dx] == 0) {
                    arr[dy][dx] = 9;
                    v[dy][dx] = true;
                    q.offer(new Point(dx, dy));
                }
            }
        }
    }

    private void cheeseRemove(int x, int y) {
        if (!isValid(x, y)) return;

        int adjacentOutside = 0;
        for (int i = 0; i < 4; i++) {
            int dy = y + diy[i], dx = x + dix[i];

            if (isValid(dx, dy) &&  arr[dy][dx] == 9) {
                adjacentOutside++;
                if (adjacentOutside >= 2) {
                    removeCheese.add(new Point(x, y));
                    break;
                }
            }
        }

    }

    private boolean isValid(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
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