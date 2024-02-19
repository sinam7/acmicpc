// https://www.acmicpc.net/problem/31423

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private static int[] descendent;
    private static int[] child;

    private static void union(int head, int tail) {
        child[descendent[head]] = tail;  // head 그룹의 마지막 노드 뒤에 tail을 붙인다.
        descendent[head] = descendent[tail]; // 해당 그룹의 마지막 노드 갱신
    }


    private static void solve() {
        int n = sc.nextInt();

        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }

        descendent = new int[n];
        child = new int[n];
        for (int i = 0; i < n; i++) {
            descendent[i] = i;
        }


        int head = 0;
        for (int i = 0; i < n - 1; i++) {
            head = sc.nextInt() - 1;
            int tail = sc.nextInt() - 1;

            union(head, tail);
        }

        int idx = head;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[idx]);
            idx = child[idx];
        }
        System.out.print(sb);
    }

    public static void main(String[] args) {
        sc.init();
        Main.solve();
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