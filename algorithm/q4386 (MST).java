// https://www.acmicpc.net/problem/4386
/*
    q4386 - 별자리 만들기
    MST (Kruskal; Greedy)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    int N;
    PriorityQueue<Node> pq;
    ArrayList<Point> points;
    int[] parent;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // input
        N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>();
        points = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points.add(new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())));
            for (int j = 0; j < points.size() - 1; j++) {
                pq.offer(new Node(j, i, getDistance(j, i)));
            }
        }

        // Kruskal 알고리즘을 위한 union-find
        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        double ans = 0;
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (union(curr.src, curr.dest)) ans += curr.cost;
        }

        System.out.printf("%.2f", ans);

    }

    private double getDistance(int a, int b) {
        Point pa = points.get(a);
        Point pb = points.get(b);

        double ans = Math.pow(pa.x - pb.x, 2) + Math.pow(pa.y - pb.y, 2);
        return Math.sqrt(ans);
    }

    private boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        parent[Math.max(a, b)] = Math.min(a, b);
        return true;
    }

    private int find(int a) {
        int curr = a;
        while (curr != parent[curr]) curr = parent[curr];
        return curr;
    }

    private static class Point {

        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

    }

    private static class Node implements Comparable<Node> {

        int src;
        int dest;
        double cost;

        public Node(int src, int dest, double cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }

        @Override // Ascending
        public int compareTo(Node o) {
            return Double.compare(this.cost, o.cost);
        }
    }

}