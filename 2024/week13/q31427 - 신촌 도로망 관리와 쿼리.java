// https://www.acmicpc.net/problem/31427

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;


class Main {

    static int n, m, q;
    static ArrayList<Edge> edges;
    static int[] parent;
    static int[] rank;

    static Map<Integer, int[]> roadInfo;

    private static void solve() {
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt();

        edges = new ArrayList<>();

        // 도로 정보 입력
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt() - 1, v = sc.nextInt() - 1, z = sc.next().charAt(0) - 'A';
            edges.add(new Edge(new Pair<>(u, v), z));
        }

        // 전처리: 입력받은 도로 정보를 바탕으로, 모든 가중치 순서쌍에 대한 도로 할당 계산
        // subtask 1) 모든 가중치 순서쌍 만들기 -> 1~5까지의 숫자로 모든 순열 만들기
        // subtask 2) 각 순서쌍별로 MST 생성 -> 대학별 도로 할당 수 계산
        // subtask 3) 계산한 도로 할당 수 저장
        roadInfo = new HashMap<>();
        createPermuations(0, new int[5]);

        StringBuilder sb = new StringBuilder();

        // 쿼리 정보 입력
        for (int i = 0; i < q; i++) {
            int[] costs = new int[5];
            ArrayList<Pair<Integer>> tmp = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                costs[j] = sc.nextInt();
                tmp.add(new Pair<>(j, costs[j]));
            }

            // 순위 순서쌍 변환
            tmp.sort(Comparator.comparingInt(o -> o.second));
            int[] ranks = new int[5];
            for (int j = 0; j < 5; j++) {
                ranks[tmp.get(j).first] = j + 1;
            }
            int[] roads = roadInfo.get(makeCode(ranks));
            long sum = 0;
            for (int j = 0; j < 5; j++) {
                sum += (long) roads[j] * costs[j];
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }

    private static void createPermuations(int depth, int[] permutation) {
        if (depth == 5) {
            // do subtask 2
            // be careful with permutation; array is mutable; array is not sutable to use key of map.
            createMST(permutation);
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (permutation[i] == 0) {
                permutation[i] = depth + 1;
                createPermuations(depth + 1, permutation);
                permutation[i] = 0;
            }
        }
    }

    private static void createMST(int[] costs) {
        // costs have a rank of each univ's road maintenance cost. the lower, the cheaper.
        edges.sort(Comparator.comparingInt(o -> costs[o.univ]));

        // using Kruskal's Algorithm.
        // we need a union-find algorithm first.
        parent = IntStream.range(0, n).toArray();
        rank = new int[n];

        int[] roads = new int[5]; // counts of each univ's road to maintenance.
        for (var edge : edges) {
            Pair<Integer> e = edge.e;
            if (unite(e.first, e.second)) {
                roads[edge.univ]++;
            }
        }

        int code = makeCode(costs);
        roadInfo.put(code, roads);
    }

    private static int makeCode(int[] costs) {
        int code = 0;
        for (int i = 0, idx = 10000; i < 5; i++, idx /= 10) {
            code += costs[i] * idx;
        }
        return code;
    }

    private static boolean unite(int ca, int cb) {
        int a = find(ca);
        int b = find(cb);

        if (a == b) return false;

        if (rank[a] < rank[b]) {
            parent[a] = b;
        } else if (rank[a] > rank[b]) {
            parent[b] = a;
        } else {
            parent[b] = a;
            rank[a]++;
        }

        return true;
    }

    private static int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]]; // Path compression
            x = parent[x];
        }
        return x;
    }


    public static void main(String[] args) {
        sc.init();
        Main.solve();
    }

    static class Edge {
        Pair<Integer> e;
        int univ;

        public Edge(Pair<Integer> e, int univ) {
            this.e = e;
            this.univ = univ;
        }
    }

    static class Pair<T> {
        T first;
        T second;

        Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }
    }

    // fast input
    static class sc {
        private static BufferedReader br;
        private static StringTokenizer st;

        static void init() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        static String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException ignored) {
                }
            }
            return st.nextToken();
        }

        static int nextInt() {
            return Integer.parseInt(next());
        }

    }

}