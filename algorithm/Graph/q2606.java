// https://www.acmicpc.net/problem/2606
/*
    DFS를 통해 인접 노트 탐색
    언젠가 나중에 생각나면 BFS를 이용한 풀이도 해 볼 예정.
 */

import java.util.Scanner;

class Main {

    static int n, answer = 0;
    static int[] visit;
    static int[][] graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int pair = sc.nextInt();

        visit = new int[n+1];
        graph = new int[n+1][n+1];


        for (int i = 0; i < pair; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            graph[a][b] = graph[b][a] = 1;
        }

        dfs(1);
        System.out.println(answer);

    }

    public static void dfs(int curr) {
        visit[curr] = 1;

        for (int i = 1; i <= n; i++)
            if (visit[i] == 0 && graph[curr][i] == 1) {
                answer++;
                dfs(i);
            }
    }
}