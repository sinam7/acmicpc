// https://www.acmicpc.net/problem/1647
/*
    q1647 - 도시 분할 계획
    MST (Kruskal; Greedy)

    1197번 문제와 동일한 방식으로 풀이가 가능하다.
    하지만 두 개의 분리된 마을을 만들어야 한다는 차이가 있는데,
    이는 가장 긴 간선(유지비가 가장 큰)을 끊어내는 것으로 풀이가 가능하다.

    최소 신장 트리를 연산하는 과정에서 N개의 집은 N-1개의 도로로 모두 이어지게 된다.
    이 중 가장 긴 간선을 끊어내는 것으로 두 개의 마을을 만들 수 있다(집 한 채도 마을로 취급하므로).
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

        int maxcost = 0;
        int ans = 0;
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (union(curr.src, curr.dest)) {
                ans += curr.cost;
                maxcost = Math.max(maxcost, curr.cost);
            }
        }

        System.out.println(ans - maxcost);

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