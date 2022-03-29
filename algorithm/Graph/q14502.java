// https://www.acmicpc.net/problem/14502
/*
    q14502 - 연구소
    BFS, Brute-force
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
    int[][] input;
    int MAX = 0;

    public void run() throws IOException {

        // input
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input = new int[N][M];
        for (int i = 0; i < N; i++) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < M; j++)
                input[i][j] = s[j];
        }

        placeWall(0);

        System.out.println(MAX);
    }

    private void placeWall(int wallPlaced) {
        if (wallPlaced == 3) {
            spreadVirus();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (input[i][j] == 0) {
                    input[i][j] = 1;
                    placeWall(wallPlaced + 1);
                    input[i][j] = 0; // 이전 형상 기억
                }
            }
        }
    }

    private void spreadVirus() {

        // 단일 clone으로는 내부 1차원 배열까지 deepcopy가 일어나지 않으니 한 단계 더 들어가서 실행해야.
        int[][] clone = new int[N][M];
        for (int i = 0; i < N; i++) clone[i] = input[i].clone();

        boolean[][] v = new boolean[N][M];

        Queue<Pair> q = new LinkedList<>();

        // 초기 바이러스 위치 확인해서 큐에 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (clone[i][j] == 2) {
                    q.offer(new Pair(j, i));
                }
            }
        }

        Pair[] dir = new Pair[]{new Pair(1, 0),
                new Pair(0, 1),
                new Pair(-1, 0),
                new Pair(0, -1)};

        Pair init = Objects.requireNonNull(q.peek());
        v[init.y][init.x] = true;

        // 바이러스 확산
        while (!q.isEmpty()) {
            Pair curr = q.poll();

            for (Pair d : dir) {
                int dy = curr.y + d.y, dx = curr.x + d.x;
                if (dx < 0 || M <= dx || dy < 0 || N <= dy) continue;
                if (!v[dy][dx] && clone[dy][dx] == 0) {
                    clone[dy][dx] = 2;
                    v[dy][dx] = true;
                    q.offer(new Pair(dx, dy));
                }
            }
        }

        // 안전지대 계산
        int n = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (clone[i][j] == 0) n++;
            }
        }
        MAX = Math.max(MAX, n);
    }


    private static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
