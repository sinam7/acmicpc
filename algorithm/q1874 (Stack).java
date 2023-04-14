// https://www.acmicpc.net/problem/1874

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

    int next = 0;
    private void solve() {
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N;) {
            if (stack.isEmpty() || stack.peek() < arr[i]) {
                stack.push(++next);
                sb.append("+\n");
            }

            if (stack.peek() == arr[i]) {
                stack.pop();
                sb.append("-\n");
                i++;
            } else if (stack.peek() > arr[i]) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        sc.init();

        new Main().solve();
    }

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