// https://www.acmicpc.net/problem/15650
/*
    백트래킹 문제이나, 굳이 백트래킹을 위한 조건을 도입할 필요가 없는 문제.
    간단하게 재귀로 풀이 가능
 */

import java.util.Scanner;

public class Main {

    static int N, M;
    static StringBuilder answer;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        answer = new StringBuilder();

        solve(1, M, new StringBuilder());
        System.out.print(answer);

    }

    /*
        1부터 자신을 StringBuilder에 넣은 뒤 재귀호출로 자신보다 큰 수에 대해 이를 iter번 (M번) 반복.
        수열에 요소가 모두(M개) 들어가면(iter == 0) answer에 append.
     */
    private static void solve(int from, int iter, StringBuilder sb) {
        if (iter == 0) {
            answer.append(sb).append('\n');
            return;
        }

        for (int i = from; i <= N; i++) {
            StringBuilder next = new StringBuilder(sb);
            solve(i + 1, iter - 1, next.append(i).append(' '));
        }

    }

}