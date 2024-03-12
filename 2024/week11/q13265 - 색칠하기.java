// https://www.acmicpc.net/problem/13265

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;


class Main {

    /*
    Main idea:
    1. 그래프 간선 정보를 모두 입력받는다.
    2. 한 정점을 선택, DFS를 통해 연결된 모든 정점을 방문한다.
    3. 단, 방문하는 정점의 색을 출발 노드의 색과 반대되는 색으로 칠한다.
    4. 이미 방문한 노드와 연결된 경우, 그 노드에 이미 칠해진 색이 다음 칠해야 할 색과 같은지 확인한다.
    5. 다르다면 두 색으로 칠할 수 없는 그래프이므로 impossible을 출력한다.
    6. 같다면 계속 DFS를 진행한다.
    7. 모든 정점을 방문하고도 5번 현상이 없었다면, 모든 정점이 색칠된 것이므로 possible을 출력한다.
    8. 여러 그래프로 만들어졌을 수 있으니, 모든 정점을 방문할 때까지 2~7을 반복한다.
     */
    private static void solve() {
        int n = sc.nextInt(), m = sc.nextInt();
        List<Integer>[] edges = new ArrayList[n];
        for (int i = 0; i < n; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt() - 1;
            int to = sc.nextInt() - 1;
            edges[from].add(to);
            edges[to].add(from);
        }

        Boolean[] visited = new Boolean[n];
        boolean isRed = true;
        for (int i = 0; i < n; i++) {
            if (visited[i] == null) {
                if (!dfs(i, edges, visited, isRed)) {
                    System.out.println("impossible");
                    return;
                }
            }
        }
        System.out.println("possible");

    }

    private static boolean dfs(int i, List<Integer>[] edges, Boolean[] visited, boolean isRed) {
        if (visited[i] == null) {
            visited[i] = isRed;
            for (int next : edges[i]) {
                if (!dfs(next, edges, visited, !isRed)) return false;
            }
            return true;
        } else {
            // return false if failed to paint same color at the node visited twice or more.
            return visited[i] == isRed;
        }

    }


    public static void main(String[] args) {
        sc.init();
        int T = sc.nextInt();
        do {
            Main.solve();
        } while (--T > 0);
    }

    @SuppressWarnings("unused")
    static class Pair<T> {
        T first;
        T second;

        Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?> pair = (Pair<?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
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