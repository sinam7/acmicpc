// https://www.acmicpc.net/problem/12852
/*
    q12852 - 1로 만들기 2
    DP
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;
    int X;
    HashMap<Integer, Integer> dp;
    HashMap<Integer, Integer> backtrace;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        dp = new HashMap<>();
        backtrace = new HashMap<>();

        X = Integer.parseInt(br.readLine());

        if (X == 1) {
            System.out.println("0\n1");
            return;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(X, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.n == 1 && curr.cost < dp.get(1)) {
                dp.put(1, curr.cost);
            }

            if (curr.n % 3 == 0) {
                if (!dp.containsKey(curr.n / 3) || dp.get(curr.n / 3) > curr.cost + 1) {
                    dp.put(curr.n / 3, curr.cost + 1);
                    backtrace.put(curr.n / 3, curr.n);
                    pq.offer(new Node(curr.n / 3, curr.cost + 1));
                }
            }

            if (curr.n % 2 == 0) {
                if (!dp.containsKey(curr.n / 2) || dp.get(curr.n / 2) > curr.cost + 1) {
                    dp.put(curr.n / 2, curr.cost + 1);
                    backtrace.put(curr.n / 2, curr.n);
                    pq.offer(new Node(curr.n / 2, curr.cost + 1));
                }
            }

            if (curr.n - 1 >= 1) {
                if (!dp.containsKey(curr.n - 1) || dp.get(curr.n - 1) > curr.cost + 1) {
                    dp.put(curr.n - 1, curr.cost + 1);
                    backtrace.put(curr.n - 1, curr.n);
                    pq.offer(new Node(curr.n - 1, curr.cost + 1));
                }
            }
        }

        ArrayList<Integer> route = new ArrayList<>();
        int curr = 1;
        while (backtrace.containsKey(curr)) {
            route.add(curr);
            curr = backtrace.get(curr);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp.get(1)).append("\n").append(X).append(" ");
        for (int i = route.size() - 1; i >= 0; i--) {
            sb.append(route.get(i)).append(" ");
        }
        System.out.println(sb);


    }

    private static class Node implements Comparable<Node> {

        int n;
        int cost;

        public Node(int n, int cost) {
            this.n = n;
            this.cost = cost;
        }

        @Override
        public int compareTo(Solve.Node o) {
            return o.cost - this.cost;
        }
    }

}