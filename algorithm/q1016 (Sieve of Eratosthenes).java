// https://www.acmicpc.net/problem/1016
/*
    q1016 - 제곱 ㄴㄴ 수
    수학, 정수론, 에라토스테네스의 체
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;
    long min, max;
    boolean[] notAnswer;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());
        notAnswer = new boolean[(int) (max - min + 1)]; // 최대 인덱스 길이 1,000,001

        /*
            제곱ㄴㄴ수: 1보다 큰 제곱수로 나누어 떨어지지 않는 수
            -> 제곱ㄴㄴ수가 아닌 수는 어떤 1보다 큰 제곱수와 다른 자연수의 곱으로 표현 가능하다.
         */
        for (int i = 2; i <= Math.sqrt(max); i++) { // 에라토스테네스의 체(sqrt)
            long square = (long) i * i; // 가능한 가장 큰 제곱수 = 1,000,001,000,000 = 1,000,000^2 > 2,147,483,647 (int max)
            /*
                제곱ㄴㄴ수가 아닌 수를 표시해 제곱ㄴㄴ수를 얻는다.
                {제곱수 * j(>=0)}(<=max)에서 j++를 통해 모든 범위 내 제곱ㄴㄴ수가 아닌 수를 검사한다.
                OOB 회피;    min < square 인 경우 start = min / square == 0이 되어
                            인덱스 j = start * square == 0, 0 - (min >= 1) <= -1로 OOB 발생
             */
            long start = min % square == 0 ? min / square : min / square + 1;

            for (long j = start; j * square <= max; j++) {
                notAnswer[(int) (j * square - min)] = true; // 해당 제곱ㄴㄴ수가 아닌 수의 인덱스를 true 로 변경
            }
        }

        int cnt = 0;
        for (boolean b : notAnswer) if (!b) cnt++; // false;제곱ㄴㄴ수 카운트
        System.out.println(cnt);

    }

}