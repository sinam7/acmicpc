// https://www.acmicpc.net/problem/1427

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    private void solve() {
        /* 문자열을 char[] 형으로 입력받아 Arrays.sort()로 정렬하고,
           그 결과를 StringBuffer를 통해 뒤집어 출력함.
         */
        char[] arr = sc.next().toCharArray();
        Arrays.sort(arr);
        String answer = new String(arr);

        System.out.println(new StringBuffer(answer).reverse().toString());
    }

    public static void main(String[] args) {
        sc.init();

        new Main().solve();
    }

    @SuppressWarnings("unused")
    static class sc {
        private static BufferedReader br;
        private static StringTokenizer st;

        static void init() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        static String readLine() {
            try {
                return br.readLine();
            } catch (IOException ignored) {
            }
            return null;
        }

        static String readLineReplace() {
            try {
                return br.readLine().replaceAll("\\s+", "");
            } catch (IOException ignored) {
            }
            return null;
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

        static double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}