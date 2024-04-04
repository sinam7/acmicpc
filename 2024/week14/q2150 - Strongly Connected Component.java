// https://www.acmicpc.net/problem/2150

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


@SuppressWarnings({"unchecked", "rawtypes"})
class Main {

    static int V, E;
    static ArrayList<ArrayList<Integer>> SCC;
    static boolean[] visited;
    static ArrayList[] graph;
    static ArrayList[] reverse_graph;
    static Stack<Integer> stack;

    static void solve() {
        V = sc.nextInt();

        SCC = new ArrayList<>();
        graph = new ArrayList[V + 1];
        reverse_graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<Integer>();
            reverse_graph[i] = new ArrayList<Integer>();
        }

        E = sc.nextInt();
        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph[a].add(b);
            reverse_graph[b].add(a);
        }

        // =====================

        stack = new Stack<>();
        visited = new boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
        visited = new boolean[V + 1];
        while (!stack.isEmpty()) {
            int top = stack.pop();
            if (!visited[top]) {
                SCC.add(new ArrayList<>());
                r_dfs(top);
                SCC.get(SCC.size() - 1).sort(Integer::compareTo);
            }
        }
        SCC.sort(Comparator.comparingInt(o -> o.get(0)));

        // =============================

        StringBuilder sb = new StringBuilder();
        int size = SCC.size();
        sb.append(size).append('\n');
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> elements = SCC.get(i);
            int e_size = elements.size();
            for (int j = 0; j < e_size; j++) {
                sb.append(elements.get(j)).append(' ');
            }
            sb.append("-1\n");
        }
        System.out.println(sb);

    }

    private static void dfs(int vertex) {
        visited[vertex] = true;
        int size = graph[vertex].size();
        for (int i = 0; i < size; i++) {
            int next = (int) graph[vertex].get(i);
            if (!visited[next]) {
                dfs(next);
            }
        }
        stack.push(vertex);
    }

    private static void r_dfs(int vertex) {
        visited[vertex] = true;
        int size = reverse_graph[vertex].size();
        for (int i = 0; i < size; i++) {
            int next = (int) reverse_graph[vertex].get(i);
            if (!visited[next]) {
                r_dfs(next);
            }
        }
        SCC.get(SCC.size() - 1).add(vertex);
    }


    public static void main(String[] args) {
        sc.init();
//        int t = sc.nextInt();
        int t = 1;
        while (t-- > 0) Main.solve();
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

        static void init(InputStreamReader in) {
            br = new BufferedReader(in);
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

        static long nextLong() {
            return Long.parseLong(next());
        }

        static int nextInt() {
            return Integer.parseInt(next());
        }


    }

}