// https://www.acmicpc.net/problem/2473
/*
    q2473 - 세 용액
    투 포인터

    q2467 - 용액 문제의 활용.
    기존 방식에서 i번째 용액을 포함시킨 상태로 투 포인터 연산을 n번 반복하면 3개 용액에서 같은 결과를 얻을 수 있다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;
    int N;
    long[] in;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        if (N == 3) {
            System.out.println(br.readLine());
            return;
        }
        in = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        Arrays.sort(in);

        int l = 0, r = in.length - 1;
        int a = l, b = r, c = 0; // index, dummy initialized
        long ph = Long.MAX_VALUE; // INF

        for (int i = 0; i < in.length; i++) {
            l = 0 == i ? 1 : 0; r = in.length - 1 == i ? in.length - 2 : in.length - 1;
            while (l < r) {

                if (in[l] + in[r] + in[i] == 0) {
                    a = l; b = r; c = i;
                    break;
                }

                long sum = in[l] + in[r] + in[i];
                if (ph > Math.abs(sum)) {
                    ph = Math.abs(sum);
                    a = l; b = r; c = i;
                }

                if (sum >= 0) {
                    if (--r == i) --r;
                }
                else if (++l == i) ++l;
            }
        }

        long[] ans = new long[] {in[a], in[b], in[c]};
        Arrays.sort(ans);
        StringBuilder sb = new StringBuilder();
        for (long lo : ans) {
            sb.append(lo).append(" ");
        }
        System.out.println(sb);
    }

}