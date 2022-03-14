// https://www.acmicpc.net/problem/1967
/*
    q1967 - 트리의 지름
    DFS
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

    int N;
    Node max;
    ArrayList<Node>[] dist;
    boolean[] visited;

    public void run() throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        dist = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) dist[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            dist[s[0]].add(new Node(s[1], s[2]));
            dist[s[1]].add(new Node(s[0], s[2]));
        }


        // 어느 정점에서 순회를 시작하든 트리의 지름을 구성하는 두 정점 중 하나가 가장 먼 정점으로 계산된다.
        visited = new boolean[N + 1];
        visited[1] = true;
        max = new Node(1, 0);
        dfs(1, 0);

        // 위에서 찾은 가장 먼 정점에서 순회를 다시 시작하면 트리의 지름을 구성하는 정점과 그 거리를 찾을 수 있다.
        visited = new boolean[N + 1];
        visited[max.dest] = true;
        max = new Node(max.dest, 0);
        dfs(max.dest, 0);

        System.out.println(max.cost);

    }

    private void dfs(int curr, int totalCost) {

        if (totalCost > max.cost) {
            max = new Node(curr, totalCost);
        }

        for (Node node : dist[curr]) {
            if (!visited[node.dest]) {
                visited[node.dest] = true;
                dfs(node.dest, totalCost + node.cost);
            }
        }

    }

    private static class Node {
        int dest;
        int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

}
