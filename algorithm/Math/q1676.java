// https://www.acmicpc.net/problem/1676
/*
    Math.min(_2s, _5s)
 */


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int two = 0, five = 0;


        for (int i = 2; i <= N; i++) {
            two += divideNumber(i, 2);
            five += divideNumber(i, 5);
        }

        System.out.println(Math.min(five, two));

    }

    private static int divideNumber(int i, int num) {
        int res = 0;
        while (i % num == 0) {
            res++;
            i /= num;
        }
        return res;
    }
}