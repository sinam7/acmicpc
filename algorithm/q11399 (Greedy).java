// https://www.acmicpc.net/problem/11399
/*
    그리디, 정렬
    답이 어떻게 만들어지는지 잠깐만 고민하면 풀 수 있다.
 */

import java.util.Arrays;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
        }

        Arrays.sort(p);

        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                ans += p[j];
            }
        }
        System.out.println(ans);
    }
}