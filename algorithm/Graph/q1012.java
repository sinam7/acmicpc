// https://www.acmicpc.net/problem/1012
/*
    DFS로 풀이. 배추 군집 수를 어떻게 계산할지 생각해야 한다.
 */

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int Answer = 0;

    public static void main(String[] args) {

        // TC 반복
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            Answer = 0;
            run();
        }

        sc.close();
    }

    static void run() {
        int M = sc.nextInt(), N = sc.nextInt(), K = sc.nextInt();

        int[][] arr = new int[N][M];
        boolean[][] visit = new boolean[N][M];

        // 배추 위치 표기
        for (int i = 0; i < K; i++) {
            int X = sc.nextInt(), Y = sc.nextInt();
            arr[Y][X] = 1;
        }

        // 풀이
        dfs(arr, visit, M, N);
        System.out.println(Answer);
    }

    static void dfs(int[][] arr, boolean[][] visit, int maxX, int maxY) {
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                if (arr[i][j] != 0 && !visit[i][j]) { // 배추가 있는 미방문 위치면
                    Answer += 1; // 새 지렁이 추가,
                    dfs_helper(arr, visit, j, i, maxX, maxY); // 인접 지역 방문
                }
            }
        }
    }

    static void dfs_helper(int[][] arr, boolean[][] visit, int X, int Y, int maxX, int maxY) {
        if (arr[Y][X] == 0) return;
        // visit
        visit[Y][X] = true;
        arr[Y][X] = Answer;

        Pair[] direction = {new Pair(1, 0), new Pair(0, 1), new Pair(-1, 0), new Pair(0, -1)};
        for (Pair d: direction) {
            if (isVaild(X + d.x, 0, maxX) && isVaild(Y + d.y, 0, maxY)) {
                if (!visit[Y + d.y][X + d.x]) // if not visited
                    dfs_helper(arr, visit, X + d.x, Y + d.y, maxX, maxY); // visit adjacent node
            }
        }


    }

    // IndexOutOfBoundsException 방지 메소드
    static boolean isVaild(int i, int min, int max) {
        return min <= i && i < max;
    }

}
// direction을 위한 편의성 클래스
class Pair {
    public int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {
        this(0, 0);
    }
}