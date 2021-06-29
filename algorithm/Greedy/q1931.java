// https://www.acmicpc.net/problem/1931
/*
    Greedy로 풀이.
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int answer = 0;

    public static void main(String[] args) {

        int N = sc.nextInt();
        Pair[] schedules = new Pair[N];
        for (int i = 0; i < N; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            schedules[i] = new Pair(a, b);
        }
        sc.close();

        Arrays.sort(schedules);

//        for (Pair p : schedules) {
//            System.out.println(p.start + " " + p.end);
//        }


        // Greedy; 가장 일찍 끝나는 회의 선택 반복
        answer = 0;
        int lastEnd = 0;
        for (int i = 0; i < N; i++) {
            if (lastEnd <= schedules[i].start) {
                answer++;
                lastEnd = schedules[i].end;
//                System.out.println("(!) meeting " + schedules[i].start +  " to " +  schedules[i].end);
            }
        }

        System.out.println(answer);

    }
}

class Pair implements Comparable<Pair> {
    public int start, end;

    public Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Pair o) {  // 종료 기준 오름차순 -> 시작 기준 오름차순
        if (end != o.end)
            return Integer.compare(end, o.end);
        else return Integer.compare(start, o.start);
    }
}