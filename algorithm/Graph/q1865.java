// https://www.acmicpc.net/problem/1865
/*
    q1865 - 웜홀
    Bellman-Ford

    최단 거리를 찾는 문제가 아닌 Negative-Cycle을 찾는 문제 (Negative Cycle이 있어야 되돌아왔을 때 시간이 무한히 감소)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();

        solve.run();    // comment while running test.java

    }

}

class Solve {

    private static final int INF = 987654321;
    int N, M, W, TC;
    ArrayList<ArrayList<Edge>> arr;

    public void run() throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            arr = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                arr.add(new ArrayList<>());
            }

            for (int i = 1; i <= M; i++) {
                int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                arr.get(s[0]).add(new Edge(s[1], s[2]));
                arr.get(s[1]).add(new Edge(s[0], s[2]));
            }

            for (int i = 1; i <= W; i++) {
                int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                arr.get(s[0]).add(new Edge(s[1], s[2] * -1));
            }

            sb.append(bellman_ford(1) ? "YES\n" : "NO\n");
        }

        System.out.println(sb);

    }

    private boolean bellman_ford(int start) {

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        boolean updated;
        for (int cycle = 1; cycle <= N; cycle++) {
            updated = false;
            for (int curr = 1; curr <= N; curr++) {
                for (Edge edge : arr.get(curr)) {
                    // if (dist[curr] == INF) continue; // 고정된 정점에서 출발 + 출발 정점과 연결되지 않은 다른 정점 사이 Cycle이 생기는 경우를 찾을 수 없음 -> INF 비교 X
                    if (dist[edge.dest] > dist[curr] + edge.cost) {
                        updated = true; // 갱신 확인 처리
                        dist[edge.dest] = dist[curr] + edge.cost;
                        if (cycle == N) return true;
                    }
                }
            }
            if (!updated) break; // cycle에서 갱신이 일어나지 않으면 향후 갱신 시도는 불필요 -> 시간 최적화
        }

        return false;

    }

    private static class Edge {
        int dest;
        int cost;

        public Edge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}
