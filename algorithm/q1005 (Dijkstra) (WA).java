// https://www.acmicpc.net/problem/1005
/*
    q1005 - ACM Craft
    Dijkstra (WA - TLE)
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

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        final int INF = -1; // MAX 거리를 구하는 것이므로 INF 값을 음수로 설정

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] cost = new int[N + 1];
            System.arraycopy(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(),
                    0, cost, 1, N);

            ArrayList<Node>[] techtree = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) techtree[i] = new ArrayList<>();

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                techtree[Y].add(new Node(X, cost[X]));
            }

            int W = Integer.parseInt(br.readLine());

            // dijkstra
            int[] dist = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[W] = cost[W]; // 시작 건물 기본 cost 설정

            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.reverseOrder());
            pq.offer(new Node(W, 0));
            while (!pq.isEmpty()) {
                Node curr = pq.poll();
                for (Node next : techtree[curr.dest]) {
                    if (dist[next.dest] < dist[curr.dest] + next.cost) {
                        dist[next.dest] = dist[curr.dest] + next.cost;
                        pq.offer(new Node(next.dest, dist[next.dest]));
                    }
                }
            }

            sb.append(Arrays.stream(dist).filter(i -> i != INF).max().orElse(-1)).append("\n");

        }

        System.out.println(sb);
    }

    private static class Node implements Comparable<Node> {

        int dest;
        int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override // Ascending
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

}