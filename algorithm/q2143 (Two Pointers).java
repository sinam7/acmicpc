// https://www.acmicpc.net/problem/2143
/*
    q2143 - 두 배열의 합
    투 포인터, 부분 합

    Thanks to: https://lotuslee.tistory.com/30?category=963570 (배열 2개에서의 투 포인터)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;
    int T;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int[] rawArr1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine());
        int[] rawArr2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        ArrayList<Long> arrA = new ArrayList<>();
        ArrayList<Long> arrB = new ArrayList<>();

        { // 주어진 수열의 연속 합의 부분수열을 구해야 한다.
            long sum;
            for (int i = 0; i < N; i++) {
                sum = 0L;
                for (int j = i; j < N; j++) {
                    sum += rawArr1[j];
                    arrA.add(sum);
                }
            }

            for (int i = 0; i < M; i++) {
                sum = 0L;
                for (int j = i; j < M; j++) {
                    sum += rawArr2[j];
                    arrB.add(sum);
                }
            }
        }

        // 투 포인터 사용을 위한 정렬
        Collections.sort(arrA);
        Collections.sort(arrB);

        // 투 포인터
        int pa = 0, pb = arrB.size() - 1;
        long ans = 0;

        while (pa < arrA.size() && pb >= 0) {
            long sum = arrA.get(pa) + arrB.get(pb);

            if (sum == T) {
                long duplicateA = 0, duplicateB = 0;
                long sumA = arrA.get(pa), sumB = arrB.get(pb);
                while (pa < arrA.size() && arrA.get(pa) == sumA) {
                    pa++;
                    duplicateA++;
                }

                while (pb >= 0 && arrB.get(pb) == sumB) {
                    pb--;
                    duplicateB++;
                }

                ans += duplicateA * duplicateB;
            } else if (sum < T) {
                pa++;
            } else if (sum > T) {
                pb--;
            }

        }

        System.out.println(ans);
    }

}