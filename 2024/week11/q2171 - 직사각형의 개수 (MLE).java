// https://www.acmicpc.net/problem/2171

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Main {

    private static void solve() {
        int n = sc.nextInt();
        List<Pair<Integer>> arr = new ArrayList<>();    // arr.contains() -> O(n)
        HashSet<Pair<Integer>> set = new HashSet<>();   // set.contains() -> O(1)
        for (int i = 0; i < n; i++) {
            Pair<Integer> e = new Pair<>(sc.nextInt(), sc.nextInt());
            arr.add(e);
            set.add(e);
        }


        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x1 = arr.get(i).first;
            int y1 = arr.get(i).second;
            for (int j = i + 1; j < n; j++) {
                int x2 = arr.get(j).first;
                int y2 = arr.get(j).second;
                if (x1 != x2 && y1 != y2) {
                    if (set.contains(new Pair<>(x1, y2)) && set.contains(new Pair<>(x2, y1))) ans++;
                }
            }
        }

        System.out.println(ans / 2);  // 1, 4번째 점으로 찾은 직사각형이 2, 3번째 점으로 찾은 것과 중복됨.
        // 1 2
        // 3 4
    }


    public static void main(String[] args) {
        sc.init();
        do {
            Main.solve();
        } while (false);
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