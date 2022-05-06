// https://www.acmicpc.net/problem/11053
/*
    DP - 가장 긴 증가하는 부분 수열

    자신보다 작은 수들 중 가장 큰 dp값 + 1을 취한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        ArrayList<Integer> dp = new ArrayList<>();
        dp.add(1);
        for (int i = 1; i < N; i++) {
            dp.add(1);
            for (int j = 0; j < i; j++)
                if (arr[j] < arr[i]) // Strictly increasing
                    dp.set(i, Math.max(dp.get(j) + 1, dp.get(i)));
        }

        System.out.println(Collections.max(dp));

    }

}