// https://www.acmicpc.net/problem/1043
/*
    q1043 - 거짓말
    Union-Find (Disjoint set)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;

    int[] parent;
    ArrayList<Integer> knowTruth;
    ArrayList<ArrayList<Integer>> party;
    int N, M, ans = 0;

    public void run() throws IOException {

        // input
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        knowTruth = new ArrayList<>();
        party = new ArrayList<>();

        int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (s[0] == 0) {
            System.out.println(M);
            return;
        }

        for (int i = 1; i < s.length; i++)
            knowTruth.add(s[i]);

        for (int i = 0; i < M; i++) {
            s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ArrayList<Integer> p = new ArrayList<>();
            for (int j = 1; j < s.length; j++)
                p.add(s[j]);
            party.add(p);
        }

        parent = new int[N + 1];
        for (int i = 0; i < parent.length; i++) parent[i] = i;

        for (ArrayList<Integer> p : party) {
            Integer head = p.get(0);
            for (int i = 1; i < p.size(); i++)
                union(head, p.get(i));
        }

        {
            Integer head = knowTruth.get(0);
            for (int i = 1; i < knowTruth.size(); i++)
                union(head, knowTruth.get(i));
        }

        for (ArrayList<Integer> p : party) {
            boolean isAnswer = true;
            for (Integer ele : p) {
                if (find(ele) == find(knowTruth.get(0))) {
                    isAnswer = false;
                    break;
                }
            }
            if (isAnswer) ans++;
        }

        System.out.println(ans);

    }

    private void union(Integer a, Integer b) {
        int fa = find(a);
        int fb = find(b);

        parent[fb] = fa;
    }

    private int find(Integer a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]); // path compression
    }

}
