// https://www.acmicpc.net/problem/13549
/*
    q13549 - 숨바꼭질 3
    dijkstra
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

    int N, K;
    int[] dist;

    public void run() throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[200001];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[N] = 0;

        dijkstra(); // dijkstra

        System.out.println(dist[K]);

    }

    private void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(N, 0));
        dist[N] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            Node[] tmp = new Node[]{new Node(2 * curr.val, curr.dist),
                    new Node(curr.val - 1, curr.dist + 1),
                    new Node(curr.val + 1, curr.dist + 1)};

            for (Node node : tmp) {
                if (node.val < 0 || node.val > 200000) continue;
                if (dist[node.val] > node.dist) {
                    dist[node.val] = node.dist;
                    pq.offer(node);
                }
            }

        }
    }

    private class Node implements Comparable<Node> {

        int val;
        int dist;

        public Node(int val, int dist) {
            this.val = val;
            this.dist = dist;
        }

        @Override
        public int compareTo(@NotNull Solve.Node o) {
            return this.dist - o.dist;
        }
    }

}
