// https://www.acmicpc.net/problem/12851
/*
    q12851 - 숨바꼭질 2
    BFS
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    int from, to;
    int cost, way = 1;
    static int MAX = 100000;
    boolean[] v = new boolean[MAX * 2 + 1];

    public void run() throws IOException {

        // input
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());


        bfs();

        System.out.println(cost);
        System.out.println(way);

    }

    private void bfs() {

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(from, 0));
        boolean hasArrived = false;

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            v[curr.pos] = true;

            if (hasArrived) {
                if (curr.cost >= cost) {
                    if (curr.pos == to && curr.cost == cost) way++;
                    continue;
                }
            }

            if (curr.pos == to && !hasArrived) {
                cost = curr.cost;
                hasArrived = true;
                continue;
            }


            if (curr.pos * 2 <= MAX * 2 && !v[curr.pos * 2]) pq.offer(new Pair(curr.pos * 2, curr.cost + 1));
            if (curr.pos + 1 <= MAX && !v[curr.pos + 1]) pq.offer(new Pair(curr.pos + 1, curr.cost + 1));
            if (curr.pos - 1 >= 0 && !v[curr.pos - 1]) pq.offer(new Pair(curr.pos - 1, curr.cost + 1));

        }

    }

    private class Pair implements Comparable<Pair> {

        int pos;
        int cost;

        public Pair(int pos, int cost) {
            this.pos = pos;
            this.cost = cost;
        }

        @Override
        public int compareTo(Solve.Pair o) {
            return this.cost - o.cost;
        }
    }
}
