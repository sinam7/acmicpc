// https://www.acmicpc.net/problem/13172
/*
    q13172 - Σ
    정수론, 모듈러 곱셈 역원, 고속 거듭제곱 (분할정복)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;
    int M;
    final int X = 1000000007;

    long ans = 0;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long N = Long.parseLong(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            // S/N
            int gcd;
            if (N > S) gcd = GCD((int) N, S);
            else gcd = GCD(S, (int) N);

            N /= gcd;
            S /= gcd;

            long inverse = square(N, X - 2) % X;
            ans += S * inverse % X;
            ans %= X;
        }
        System.out.println(ans);

    }

    private int GCD(int a, int b) {
        if (b == 0) return a;
        else return GCD(b, a % b);
    }

    private long square(long a, int m) {
        if (m == 1) return a % X;
        if (m % 2 == 1) return a * square(a, m - 1) % X;
        long result = square(a, m / 2) % X;
        return result * result % X;
    }


}