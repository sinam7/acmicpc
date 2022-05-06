// https://www.acmicpc.net/problem/2467
/*
    q2467 - 용액
    투 포인터
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
        if (N == 2) {
            System.out.println(br.readLine());
            return;
        }
        in = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        int l = 0, r = in.length - 1;
        int a = l, b = r; // index, dummy initialized
        long ph = Long.MAX_VALUE; // INF

        while (l < r) {
            if (in[l] + in[r] == 0) {
                a = l; b = r;
                break;
            }

            long sum = in[l] + in[r];
            if (ph > Math.abs(sum)) {
                ph = Math.abs(sum);
                a = l; b = r;
            }

            if (sum >= 0) r--;
            else l++;
        }

        System.out.println(in[a] + " " + in[b]);
    }

}