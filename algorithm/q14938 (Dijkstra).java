// https://www.acmicpc.net/problem/14938
/*
    q14938 - 서강그라운드
    다익스트라 (모든 정점 대상으로 반복)
 */

import org.jetbrains.annotations.NotNull;

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
    int N, M, R;
    int[] item;
    ArrayList<Edge>[] way;
    int[] ans;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        item = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        way = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            way[i] = new ArrayList<>();
        }
        for (int i = 0; i < R; i++) {
            int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            way[in[0]].add(new Edge(in[1], in[2]));
            way[in[1]].add(new Edge(in[0], in[2]));
        }

        ans = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            ans[i] = dijkstra(i);
        }

        System.out.println(Arrays.stream(ans).max().getAsInt());

    }

    private int dijkstra(int i) {
        boolean[] v = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[i] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(i, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            v[curr.dest] = true;

            for (Edge edge : way[curr.dest]) {
                if (!v[edge.dest] && dist[edge.dest] > dist[curr.dest] + edge.cost) {
                    dist[edge.dest] = dist[curr.dest] + edge.cost;
                    pq.offer(new Edge(edge.dest, dist[edge.dest]));
                }
            }

        }

        int res = 0;
        for (int j = 1; j <= N; j++) {
            if (dist[j] <= M) res += item[j];
        }

        return res;

    }

    private static class Edge implements Comparable<Edge> {

        int dest;
        int cost;

        public Edge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(@NotNull Solve.Edge o) {
            return this.cost - o.cost;
        }
    }

}