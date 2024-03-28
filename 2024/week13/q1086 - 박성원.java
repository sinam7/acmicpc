// https://www.acmicpc.net/problem/1086

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Main {

    static int N, K;
    static String[] input;
    static int[] powerOfTens;
    static int[] remainders;

    static long[][] dp; // dp[state][value<k] = sum of divide ups when current state and it's value mod by k is value.

    private static void solve() {
        N = sc.nextInt();
        input = new String[N];
        for (int i = 0; i < N; i++) input[i] = sc.next();
        K = sc.nextInt();

        // 전처리
        // 10^i(:0~50(i))%K의 나머지 계산.
        powerOfTens = new int[51];
        powerOfTens[0] = 1 % K; // (10^0 % K)
        for (int i = 1; i <= 50; i++) powerOfTens[i] = (powerOfTens[i - 1] * 10) % K;

        // 각 문자열의 나머지 계산.
        remainders = new int[N];
        for (int i = 0; i < N; i++) remainders[i] = calculateRemainderOfString(i);

        dp = new long[1 << N][K];
        dp[0][0] = 1;
        calc();

        // 완성된 순열이 나누어 떨어지는 경우는 dp[(1 << N) - 1][0]에 누적된다.
        long numerator = dp[(1 << N) - 1][0];
        long denominator = 1;
        for (int i = 1; i <= N; i++) denominator *= i;

        long gcd = gcd(denominator, numerator); // always denominator >= numerator
        numerator /= gcd;
        denominator /= gcd;

        System.out.println(numerator + "/" + denominator);
    }

    private static long gcd(long i, long j) {
        return (j == 0) ? i : gcd(j, i%j);
    }

    private static int calculateRemainderOfString(int i) {
        String s = input[i];
        int ans = 0;
        int length = s.length();
        for (int j = 0; j < length; j++) {
            ans += (long) (s.charAt(length - j - 1) - '0') * powerOfTens[j] % K;
            ans %= K;
        }
        return ans;
    }

    private static void calc() {

        for (int state = 0; state < (1 << N); state++) {
            for (int i = 0; i < N; i++) {
                if ((state & 1 << i) == 0) { // bit not used
                    int nextState = state | 1 << i;
                    for (int j = 0; j < K; j++) {
                        // concat(current K value, input[i]) -> K will multiplied by input[i].length
                        int nextValue = ((j * powerOfTens[input[i].length()]) % K + remainders[i]) % K;
                        dp[nextState][nextValue] += dp[state][j]; // 없는 나머지 상태는 여기서 걸러짐.
                    }
                }
            }
        }

    }


    private static boolean isValidIndex(int y, int x, int maxY, int maxX) {
        return 0 <= y && y < maxY && 0 <= x && x < maxX;
    }


    public static void main(String[] args) {
        sc.init();
        Main.solve();
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