// https://www.acmicpc.net/problem/1655

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Main {

    static int N;

    private static void solve() {
        N = sc.nextInt();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 큰 값 중 최소를 확인
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); // 작은 값 중 최대를 확인

        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            if (minHeap.size() == maxHeap.size()) {
                maxHeap.add(sc.nextInt());
            } else {
                minHeap.add(sc.nextInt());
            }

            // maxHeap [1, 2, 3(top, median)] -><- [4(top), 5, 6] minHeap
            if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
                if (maxHeap.peek() > minHeap.peek()) {
                    int temp = minHeap.poll();
                    minHeap.add(maxHeap.poll());
                    maxHeap.add(temp);
                }
            }

            sb.append(maxHeap.peek()).append("\n");
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