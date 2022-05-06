// https://www.acmicpc.net/problem/11779
/*
    q11779 - 최소비용 구하기 2
    다익스트라

    기존 최단거리 구하기 문제에 최단 경로 정보도 저장해야 하는 문제다.
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
    int N, M, start, end;
    ArrayList<Edge>[] input;

    int[] backtrace;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        input = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            input[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            input[s[0]].add(new Edge(s[1], s[2]));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        backtrace = new int[N + 1];
        ArrayList<Integer> route = new ArrayList<>();

        int cost = dijkstra();

        // 다익스트라 알고리즘 중 저장한 경로를 가공한다.
        // 해당 인덱스는 그 지점에 도달하기 전에 방문한 위치를 저장하므로,
        // 이를 역추적하는 방식으로 최단경로를 찾는다.
        int tmp = end;
        while (tmp != 0) {
            route.add(tmp);
            tmp = backtrace[tmp];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cost).append("\n").append(route.size()).append("\n");

        // backtrace 배열을 통해 route에 경로를 저장했으나, 역방향(도착->시작)으로 저장되어 있으므로
        // 배열을 반대로 뒤집어 출력한다.
        for (int i = route.size() - 1; i >= 0; i--) {
            sb.append(route.get(i)).append(" ");
        }
        System.out.println(sb);

    }

    private int dijkstra() {
        boolean[] v = new boolean[N + 1];
        int[] dist = new int[N + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (v[curr.dest]) continue;
            v[curr.dest] = true;

            for (Edge edge : input[curr.dest]) {
                if (!v[edge.dest] && dist[edge.dest] > dist[curr.dest] + edge.cost) {
                    dist[edge.dest] = dist[curr.dest] + edge.cost;
                    backtrace[edge.dest] = curr.dest;   // 최단거리가 갱신될 때마다 그 지점에 오기 전에 들린 장소를 기억
                    pq.offer(new Edge(edge.dest, dist[edge.dest]));
                }
            }
        }

        return dist[end];

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