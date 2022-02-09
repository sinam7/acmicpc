// https://www.acmicpc.net/problem/1753
/*
    최단경로 - 다익스트라
    노드가 많아 일반적인 DP의 2차원 배열 사용 시 메모리 초과가 발생한다.
    우선순위 큐를 활용해 간선 정보를 저장하고 갱신해야 한다.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;

class q1753 {

    static final int INT_MAX = Integer.MAX_VALUE;
    int V, E, src;

    public void run() throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        {
            String[] s = br.readLine().split(" ");
            V = Integer.parseInt(s[0]);
            E = Integer.parseInt(s[1]);

            src = Integer.parseInt(br.readLine().strip());
        }

        PriorityQueue<Node>[] pq = new PriorityQueue[V + 1]; // pq[src]: src -> (dest, weight)의 우선순위 큐
        for (int i = 1; i < V + 1; i++) pq[i] = new PriorityQueue<>();

        int[] dist = new int[V + 1]; // idx 1부터
        Arrays.fill(dist, INT_MAX);
        dist[src] = 0;

        boolean[] visit = new boolean[V + 1]; // idx 1부터

        for (int i = 0; i < E; i++) {
            String[] s = br.readLine().split(" ");
            int[] t = new int[3]; // Src, Dest, Weight
            for (int j = 0; j < 3; j++) t[j] = Integer.parseInt(s[j]);

            pq[t[0]].offer(new Node(t[1], t[2]));
        }

        // 시작 노드에서 방문 가능한 모든 곳 방문
        while (!pq[src].isEmpty()) {
            Node poll = Objects.requireNonNull(pq[src].poll());

            // 이미 방문한 곳은 다시 가지 않음
            if (visit[poll.dest]) continue;

            // 기존 최단 거리보다 짧거나 같은 경우 연산 시행
            if (poll.weight <= dist[poll.dest]) {
                dist[poll.dest] = poll.weight;  // 갱신
                visit[poll.dest] = true; // 방문 처리

                // 지금 방문한 노드와 연결된 노드들의 정보에 현재 노드의 가중치를 더해(분할정복) 우선순위 큐에 추가
                for (Node node : pq[poll.dest]) {
                    pq[src].offer(new Node(node.dest, node.weight + poll.weight));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(dist[i] != INT_MAX ? dist[i] : "INF").append("\n");
        }

        System.out.println(sb);
    }

    class Node implements Comparable<Node> {

        int dest;
        int weight;

        public Node(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(q1753.Node o) {
            return this.weight - o.weight;
        }
    }
}
