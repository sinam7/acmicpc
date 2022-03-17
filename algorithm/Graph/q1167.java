// https://www.acmicpc.net/problem/1167
/*
    q1167 - 트리의 지름
    DFS

    q1967과 매우 흡사한 문제.
    <q1967: 무방향 그래프 / q1167: 방향 그래프>로 입력형식 차이만 존재함.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }

}

class Solve {

    int N;
    Pair candidate;
    ArrayList<Pair>[] input;
    boolean[] v;


    public void run() throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        input = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) input[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int cost = Integer.parseInt(st.nextToken());

                input[from].add(new Pair(to, cost));
            };
        }

        v = new boolean[N + 1];
        v[1] = true;
        candidate = new Pair(1, 0);
        dfs(1, 0);

        v = new boolean[N + 1];
        v[candidate.dest] = true;
        candidate = new Pair(candidate.dest, 0);
        dfs(candidate.dest, 0);

        System.out.println(candidate.cost);
    }

    private void dfs(int i, int totalCost) {
        if (totalCost > candidate.cost) {
            candidate = new Pair(i, totalCost);
        }

        for (Pair next : input[i]) {
            if (v[next.dest]) continue;
            v[next.dest] = true;
            dfs(next.dest, totalCost + next.cost);
        }
    }


    private static class Pair {
        int dest;
        int cost;

        public Pair(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}
