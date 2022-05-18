// https://www.acmicpc.net/problem/1644
/*
    q1644 - 소수의 연속합
    투 포인터
 */

/*
         최초 소수부터 부분합을 미리 더해서 계산하면 향후 연산 비용이 줄어든다.
         이후 구간합에서 이전 구간합을 빼는 방식을 통해 특정 구간의 부분합을 구할 수 있으며,
         이를 통해 문제에서 구하려는 값을 얻을 수 있다.

           N = 31
        i) 0 1 2  3  4  5  6  7  8   9  10
    prime) 0 2 3  5  7 11 13 17 19  23  29
     psum) 0 2 5 10 17 28 41 58 77 100 129

         구간합[6] - 구간합[3] = 부분합(4~6)
         ("2+3+5" +7+11+13) - "+2+3+5") = 31
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;
    int N;
    int ans = 0;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ArrayList<Long> psum = new ArrayList<>();
        boolean[] v = new boolean[N + 1];
        psum.add(0L);
        // 에라토스테네스의 체
        for (int i = 2; i < Math.sqrt(N) + 1; i++) {
            for (int j = 2 * i; j <= N; j += i)
                v[j] = true; // 소수가 아님을 표시
        }

        long sum = 0;
        for (int i = 2; i <= N; i++) {
            // 소수 체크해 부분합 추가
            if (!v[i]) {
                sum += i;
                psum.add(sum);
            }
        }




        int left = 0, right = 0;
        while (left <= right && right < psum.size()) {
            long chk = psum.get(right) - psum.get(left);
            if (chk < N) {
                right++;
            } else if (chk > N) {
                left++;
            } else {
                ans++;
                right++; // 찾았으면 이후 값을 더해 마저 진행
            }
        }

        System.out.println(ans);
    }

}