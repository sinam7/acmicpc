// https://www.acmicpc.net/problem/2206
/*
    q2206 - 벽 부수고 이동하기
    BFS

    벽을 부순 경우일때의 방문 시점과 그렇지 않은 경우의 방문 시점을 따로 기록해야 한다.
    그렇지 않으면 오답 경로의 일부가 정답 경로에 포함되는 경우에 기 방문 처리로 인해 정답 경로가 중간에 가로막혀 WA가 발생한다.
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

    int N, M;
    int[][] board;
    boolean[][][] visited;  // [y][x][0: 안부순상태의 방문, 1:벽한번부순상태의 방문]
    final int[][] vector = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public void run() throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N + 1][M + 1];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i + 1][j + 1] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }

        visited = new boolean[N + 1][M + 1][2];
        visited[1][1][0] = true;
        int bfs1 = bfs(true);

        visited = new boolean[N + 1][M + 1][2];
        visited[1][1][0] = true;
        int bfs2 = bfs(false);

        int answer;
        if (bfs1 == -1 || bfs2 == -1) answer = Math.max(bfs1, bfs2);
        else answer = Math.min(bfs1, bfs2);

        System.out.println(answer);

    }

    private int bfs(boolean canBreakWall) {

        int cost_min = Integer.MAX_VALUE;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(1, 1, 1, !canBreakWall));
        while (!queue.isEmpty()) {

            Node curr = queue.poll();
            if (curr.x == M && curr.y == N) {
                cost_min = Math.min(cost_min, curr.cost);
            }

            for (int[] direction : vector) {
                int x = curr.x + direction[1], y = curr.y + direction[0];
                if (!(1 <= x && x <= M && 1 <= y && y <= N)) continue;

                // 이미 벽을 부순 경우
                if (curr.broke) {
                    if (!visited[y][x][1]) {
                        if (board[y][x] == 1) continue;
                        visited[y][x][1] = true;
                        Node next = new Node(x, y, curr.cost + 1, true);
                        queue.offer(next);
                    }
                }

                // 아직 벽을 부수지 않은 경우
                else {
                    if (!visited[y][x][0]) {
                        visited[y][x][0] = true;
                        Node next = new Node(x, y, curr.cost + 1, false);
                        if (board[y][x] == 1) {
                            visited[y][x][1] = true;    // 벽 부순 후의 방문표기도 처리
                            next.broke = true;
                        }
                        queue.offer(next);
                    }
                }
            }

        }
        return cost_min == Integer.MAX_VALUE ? -1 : cost_min;

    }

    private static class Node {
        int x;
        int y;
        int cost;
        boolean broke;

        public Node(int x, int y, int cost, boolean broke) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.broke = broke;
        }

    }

}
