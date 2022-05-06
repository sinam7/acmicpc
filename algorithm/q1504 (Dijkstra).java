// https://www.acmicpc.net/problem/1504
/*
    q1504 - 특정한 최단 경로
    Dijkstra
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
    int N, E;
    int v1, v2;

    ArrayList<Edge>[] input; // [start]{end, cost}

    public void run() throws IOException {

        // input
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 그래프 init
        input = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            input[i] = new ArrayList<>();
        }

        // 양방향 그래프 작성
        for (int i = 0; i < E; i++) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            input[s[0]].add(new Edge(s[1], s[2]));
            input[s[1]].add(new Edge(s[0], s[2]));
        }

        // 무조건 방문해야 하는 두 정점
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());


        // logic - 1~N으로 가는 루트가 2종이므로 둘 모두 비교
        int[][] ans = new int[][]{
                {dijkstra(1, v1), dijkstra(v1, v2), dijkstra(v2, N)}, // 1-v1-v2-N
                {dijkstra(1, v2), dijkstra(v2, v1), dijkstra(v1, N)}  // 1-v2-v1-N
        };

        // INF(Integer.MAX_VALUE)가 존재하면 오버플로가 발생해 음수가 나올 수 있어 Math.min() 연산에 문제가 생길 수 있다.
        // 그래서 자료형을 long으로 확장해 연산의 정확성을 확보했다.
        long result = Math.min(
                Arrays.stream(ans[0]).mapToLong(o -> (long) o).sum(),
                Arrays.stream(ans[1]).mapToLong(o -> (long) o).sum());

        // 두 루트 모두 길이 없다면(>=Integer.MAX_VALUE + 0 + 0) -1 출력
        System.out.println(result >= Integer.MAX_VALUE ? -1 : result);
    }

    private int dijkstra(int start, int end) {

        boolean[] v = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE); // 최단거리 배열 INF 초기화

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // 아래 2라인을 통해 첫 curr.end에 start를 넣어 시작할 수 있다;시작지점과 연결된 간선부터 연산
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (!v[curr.end]) { // 중복 방문 방지(다익스트라)
                v[curr.end] = true;

                for (Edge edge : input[curr.end]) {
                    if (!v[edge.end] && dist[edge.end] > dist[curr.end] + edge.cost) {
                        dist[edge.end] = dist[curr.end] + edge.cost;
                        pq.offer(new Edge(edge.end, dist[edge.end]));
                    }
                }
            }
        }

        return dist[end]; // 길 없으면 INF(Integer.MAX_VALUE)

    }

    private static class Edge implements Comparable<Edge> {

        int end;
        int cost;

        public Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }


        @Override
        public int compareTo(Solve.Edge o) {
            return this.cost - o.cost;
        }
    }

}