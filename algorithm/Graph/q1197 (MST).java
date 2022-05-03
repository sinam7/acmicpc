// https://www.acmicpc.net/problem/1197
/*
    q1197 - 최소 스패닝 트리
    MST (Kruskal; Greedy)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;
    int V, E;
    PriorityQueue<Node> pq;
    int[] parent;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // input
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();

        for (int i = 0; i < E; i++) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pq.offer(new Node(s[0], s[1], s[2]));
        }

        // Kruskal 알고리즘을 위한 union-find
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) parent[i] = i;

        int ans = 0;
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (union(curr.src, curr.dest)) ans += curr.cost;
        }

        System.out.println(ans);

    }

    private boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        parent[Math.max(a, b)] = Math.min(a, b);
        return true;
    }

    private int find(int a) {
        int curr = a;
        while (curr != parent[curr]) curr = parent[curr];
        return curr;
    }

    private static class Node implements Comparable<Node> {

        int src;
        int dest;
        int cost;

        public Node(int src, int dest, int cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }

        @Override // Ascending
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

}