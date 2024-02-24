import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private static void solve() {
        String str = sc.next();
        int n = str.length();

        if (n == 1) {
            System.out.println((str.equals("K") ? 0 : 2));
            return;
        }

        int top = 0;
        char[] arr = {'K', 'S', 'A'};
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == arr[top % 3]) {
                top++;
            }
        }
        int answer1 = (n - top) * 2;
        if (top == 0 && str.contains("S")) {
            top = 1;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == arr[top % 3]) {
                    top++;
                }
            }
            int answer2 = (n - top) * 2;
            System.out.println(answer2 + 2);
        } else {
            System.out.println(answer1);
        }
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