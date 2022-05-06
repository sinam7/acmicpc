// https://www.acmicpc.net/problem/1916
/*
    최소비용 구하기 - 다익스트라
    (!) ConcurrentModificationException이 발생해 동시성 문제를 해결하기 위해
    Iterator를 사용했으나 실패, PriorityQueue 대신 PriorityBlockingQueue를 사용해 해결했다.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }

}

class Solve {

    static final int INT_MAX = Integer.MAX_VALUE;
    int N, M;
    int src, dest;

    public void run() throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
        }

        PriorityBlockingQueue<Node>[] pq = new PriorityBlockingQueue[N + 1]; // pq[src]: src -> (dest, weight)의 우선순위 큐
        for (int i = 1; i < N + 1; i++) pq[i] = new PriorityBlockingQueue<>();

        int[] dist = new int[N + 1]; // idx 1부터
        Arrays.fill(dist, INT_MAX);
        dist[src] = 0;

        boolean[] visit = new boolean[N + 1]; // idx 1부터

        for (int i = 0; i < M; i++) {
            String[] s = br.readLine().split(" ");
            int[] t = new int[3]; // Src, Dest, Cost
            for (int j = 0; j < 3; j++) t[j] = Integer.parseInt(s[j]);

            pq[t[0]].offer(new Node(t[1], t[2]));
        }

        {
            String[] s = br.readLine().split(" ");
            src = Integer.parseInt(s[0]);
            dest = Integer.parseInt(s[1]);
        }

        // 시작 노드에서 방문 가능한 모든 곳 방문
        while (!pq[src].isEmpty()) {
            Node poll = pq[src].poll();

            // 이미 방문한 곳은 다시 가지 않음
            if (visit[poll.dest]) continue;

            // 기존 최단 거리보다 짧거나 같은 경우 연산 시행
            if (poll.cost <= dist[poll.dest]) {
                dist[poll.dest] = poll.cost;  // 해당 노드까지의 최단거리 갱신
                visit[poll.dest] = true; // 방문 처리

                // 지금 방문한 노드와 연결된 노드들의 정보에 현재 노드의 가중치를 더해(분할정복) 우선순위 큐에 추가
                pq[poll.dest].forEach(node -> pq[src].offer(new Node(node.dest, node.cost + poll.cost)));
            }
        }

        System.out.println(dist[dest]);
    }

    class Node implements Comparable<Node> {

        int dest;
        int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
