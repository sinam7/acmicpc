// https://www.acmicpc.net/problem/15654
/*
    정렬 후 M개 순서대로 선택
    중복 제거 (백트래킹)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static int N, M;
    static StringBuilder answer;
    static ArrayList<Integer> arr;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        answer = new StringBuilder();
        arr = new ArrayList<>();

        for (int i = 0; i < N; i++) arr.add(sc.nextInt());
        Collections.sort(arr);

        solve(M, new ArrayList<>());
        System.out.print(answer);

    }

    private static void solve(int iter, ArrayList<Integer> arrayList) {
        if (iter == 0) {
            answer.append(build(arrayList)).append('\n');
            return;
        }

        for (int i = 0; i < arr.size(); i++) {
            if (arrayList.contains(arr.get(i))) continue;

            ArrayList<Integer> next = new ArrayList<>(arrayList);
            next.add(arr.get(i));
            solve(iter - 1, next);
        }

    }

    private static StringBuilder build(ArrayList<Integer> arrayList) {
        StringBuilder sb = new StringBuilder();
        for (Integer integer : arrayList) sb.append(integer).append(' ');
        return sb;
    }

}