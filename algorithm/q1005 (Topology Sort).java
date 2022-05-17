// https://www.acmicpc.net/problem/1005
/*
    q1005 - ACM Craft
    Topology sort
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
            int[] in_degree = new int[N + 1];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                techtree[X].add(new Node(Y, cost[Y]));
                in_degree[Y]++;
            }

            int W = Integer.parseInt(br.readLine());

            // topology sort
            int[] dist = new int[N + 1];

            Queue<Node> q = new LinkedList<>();
            for (int i = 1; i <= N; i++) {
                if (in_degree[i] == 0) {
                    q.offer(new Node(i, cost[i]));
                    dist[i] = cost[i];
                }
            }

            while (!q.isEmpty()) {
                Node curr = q.poll();
                for (Node next : techtree[curr.dest]) {
                    dist[next.dest] = Math.max(dist[next.dest], dist[curr.dest] + next.cost);
                    if (--in_degree[next.dest] == 0) {
                        q.offer(new Node(next.dest, dist[next.dest]));
                    }
                }
            }
            sb.append(dist[W]).append("\n");

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