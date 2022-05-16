// https://www.acmicpc.net/problem/20040
/*
    q20040 - 사이클 게임
    Union-Find (Disjoint set)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;

    int N, M;
    int[] parent;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        for (int i = 1; i <= M; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (union(a, b)) {
                System.out.println(i);
                return;
            }

        }
        System.out.println(0);

    }

    private int find(int a) {
        int i = a;
        while (i != parent[i]) {
            i = parent[parent[i]];
        }
        parent[a] = i; // path compression
        return i;
    }

    private boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return true;
        parent[Math.max(pa, pb)] = parent[Math.min(pa, pb)];
        return false;
    }

}