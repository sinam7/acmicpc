// https://www.acmicpc.net/problem/11444
/*
    q11444 - 피보나치 수 6
    분할정복

    도가뉴 항등식 + 메모이제이션
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();

        solve.run();    // comment while running test.java

    }

}

class Solve {

    long N;
    HashMap<Long, Long> memo = new HashMap<>();

    public void run() throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();

        N = Long.parseUnsignedLong(br.readLine());

        memo.put(0L, 0L);
        memo.put(1L, 1L);
        memo.put(2L, 1L);
        memo.put(3L, 2L);

        System.out.println(dOcagne(N));

    }

    private long dOcagne(long i) {

        if (memo.containsKey(i)) return memo.get(i);

        long m = i / 2, n;

        if (i % 2 == 0) n = m;
        else n = m + 1;

        long i1 = (dOcagne(m - 1) % 1000000007
                * dOcagne(n) % 1000000007
                + dOcagne(m) % 1000000007
                * dOcagne(n + 1) % 1000000007
        ) % 1000000007;

        memo.put(i, i1);
        return memo.get(i);

    }
}
