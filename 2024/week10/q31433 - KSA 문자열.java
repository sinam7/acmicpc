import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int originLength;
    static final char[] ksa = {'K', 'S', 'A'};

    public static void solve() {
        String str = sc.next();
        originLength = str.length();

        int case1 = calc(str, 0);
        int case2 = calc("K" + str, 1); // 맨 앞 K 추가
        int case3 = calc("KS" + str, 2);

        System.out.println(Math.min(case1, Math.min(case2, case3)));
    }

    private static int calc(String str, int count) {
        final int n = str.length();

        int ksaIndex = 0;  // KSA 문자열 길이
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == ksa[ksaIndex % 3]) {
                ksaIndex++;
            } else {
                count++;
            }
        }
        count += Math.abs(originLength - ksaIndex); // 만든 KSA 문자열이 원래 문자열보다 길면 지워야 하고, 짧으면 늘려야 함.
        return count;
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