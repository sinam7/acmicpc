// https://www.acmicpc.net/problem/1766

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;


class Main {

    static int N, M;
    static HashMap<Integer, ArrayList<Integer>> edge = new HashMap<>();
    static int[] questionsLeft;

    private static void solve() {
        N = sc.nextInt();
        M = sc.nextInt();
        questionsLeft = new int[N];
        for (int i = 0; i < M; i++) {
            int before = sc.nextInt() - 1, after = sc.nextInt() - 1;
            if (!edge.containsKey(before)) edge.put(before, new ArrayList<>());
            edge.get(before).add(after);
            questionsLeft[after]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        IntStream.range(0, N).filter(i -> questionsLeft[i] == 0).forEach(pq::add);

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int now = pq.poll();
            sb.append(now + 1).append(" ");
            if (edge.containsKey(now)) {
                edge.get(now).forEach(after -> {
                    questionsLeft[after]--;
                    if (questionsLeft[after] == 0) pq.add(after);
                });
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        sc.init();
        int T = 0;
        boolean nowDebugging = false;
//        T = sc.nextInt();
        do {
            Main.solve();
        } while (nowDebugging || --T > 0);
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