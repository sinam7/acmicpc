// https://www.acmicpc.net/problem/7576
/*
    bfs를 이용한 풀이

    익은 토마토에서부터 bfs를 진행, 연속적으로 숙성시킨다.
    안 익은 토마토 개수를 세는 변수를 통해 모든 토마토가 익었는지 확인.
    bfs를 마치고도 토마토가 남아있으면 모든 토마토가 익을 수 없음.

    bfs가 끝난 뒤 토마토 상자 정보인 arr에는 각 위치의 토마토가 n+1일차에 숙성되었단 정보가 담겨 있다. (-1: 빈공간 제외)
    -> 초기 숙성된(0일차) 토마토의 정보가 1로 저장되어 있기 때문.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] arr = new int[1000][1000];
    static boolean[][] visit = new boolean[1000][1000];

    static int N = 0, M = 0, day = 0, tomatoes = 0;
    static Queue<int[]> queue = new LinkedList<>();


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        M = sc.nextInt(); N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int tmp = sc.nextInt();
                arr[i][j] = tmp;
                if (tmp == 0) tomatoes++;   // 숙성되어야 할 토마토의 개수
            }
        }

        if (tomatoes == 0) {                // 이미 모든 토마토가 숙성되었다면 종료
            System.out.println(0);
            return;
        }

        solve();

        if (tomatoes != 0) System.out.println(-1);  // 숙성 작업 이후에도 남은 토마토가 있는지 확인
        else System.out.println(day - 1);           // 실제 숙성 기간 반영 (-1)

    }

    static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1 && !visit[i][j]) {   // bfs 시작 지점 (초기에 숙성되어있던 토마토)을 큐에 추가
                    queue.add(new int[]{i, j});
                    visit[i][j] = true;
                }
            }
        }
        bfs();
    }

    private static void bfs() {

        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};         // 탐색 방향 (우,하,좌,상)
        while (!queue.isEmpty()) {
            int[] t = queue.poll();

            for (int[] ints : direction) {
                int dy = t[0] + ints[0], dx = t[1] + ints[1];
                if (dy < 0 || N <= dy || dx < 0 || M <= dx) continue;   // check Array bounds

                if ((arr[dy][dx] != -1) && !visit[dy][dx]) {
                    arr[dy][dx] = arr[t[0]][t[1]] + 1;                  // n+1일차에 숙성되었음을 알림

                    day = Math.max(day, arr[dy][dx]);                   // 숙성 기간 갱신

                    queue.add(new int[]{dy, dx});                       // 큐에 다음 토마토 추가
                    visit[dy][dx] = true;                               // 방문 처리
                    tomatoes--;                                         // 숙성된 토마토이므로 -1
                }
            }
        }
    }

}