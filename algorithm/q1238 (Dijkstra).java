// https://www.acmicpc.net/problem/1238
/*
    q1238 - 파티
    다익스트라

    역방향 도로를 저장하지 않고 for(i:1~N) 다익스트라를 통해 왕복 거리를 구하면 TLE 위험이 있다.
    현재는 TLE가 발생하지 않으나 TC가 강화되거나 시간 제한이 줄어들면 발생 가능하다.
    각 도시에서 X 도시까지의 최단 거리를 구하는 과정을 반복하는 것이 비효율적이며,
    위 상황에서의 단방향 도로를 모두 뒤집어 X 도시에서 모든 도시까지의 최단 거리 배열을 구하면
    이것이 곧 각 도시에서의 X 도시까지의 최단 거리 배열이 된다.
    이를 통해 N+1번의 다익스트라 연산(900ms)을 2번의 연산(268ms)으로 최적화가 가능하다.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    int N, M, X;
    int[] dest, reverseDest;
    ArrayList<Edge>[] way, reverseWay;
    static final int INF = 1000000000; // 최대 거리: 도시간 연결 999 * 최대 소요 시간 100 * 왕복 2 = 199,800 (일자 양방향 연결)

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // 도로 양면으로 저장하는 이유: 중복 다익스트라 호출(i->X)을 1회로 최적화(X->all 최단거리)
        way = new ArrayList[N + 1];         // 정방향 도로: 파티장에서 각 도시로 이동하는 경우
        reverseWay = new ArrayList[N + 1];  // 역방향 도로: 각 도시에서 파티장으로 이동하는 경우
        for (int i = 1; i <= N; i++) {
            way[i] = new ArrayList<>();
            reverseWay[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            way[in[0]].add(new Edge(in[1], in[2]));
            reverseWay[in[1]].add(new Edge(in[0], in[2]));
        }

        dest = dijkstra(way, new int[N + 1], X);
        reverseDest = dijkstra(reverseWay, new int[N + 1], X);

        int max = -1;
        for (int i = 1; i <= N; i++) {
            max = Math.max(dest[i] + reverseDest[i], max);
        }

        System.out.println(max);

    }

    private int[] dijkstra(ArrayList<Edge>[] list, int[] dist, int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        boolean[] v = new boolean[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            if (v[curr.dest]) continue;
            v[curr.dest] = true;

            for (Edge edge : list[curr.dest]) {
                if (dist[edge.dest] > dist[curr.dest] + edge.cost) {
                    dist[edge.dest] = dist[curr.dest] + edge.cost;
                    pq.offer(new Edge(edge.dest, dist[edge.dest]));
                }
            }
        }

        return dist;
    }

    private static class Edge implements Comparable<Edge> {
        int dest;
        int cost;

        public Edge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }


    }


}