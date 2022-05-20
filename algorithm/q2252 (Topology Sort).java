// https://www.acmicpc.net/problem/2252
/*
    q2252 - 줄 세우기
    위상 정렬
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
    int N, M;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] in_degree = new int[N + 1];
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            in_degree[B]++;
        }

        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (in_degree[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            Integer curr = q.poll();
            sb.append(curr).append(" ");
            for (Integer next : graph[curr]) {
                if (--in_degree[next] == 0) {
                    q.add(next);
                }
            }
        }

        System.out.println(sb);

    }

}