// https://www.acmicpc.net/problem/2407
/*
    BigInteger
 */

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), m = sc.nextInt();
        if (m > n / 2) m = n - m;

        BigInteger num = BigInteger.ONE;
        for (int i = n; i > n - m; i--)
            num = num.multiply(BigInteger.valueOf(i));
        for (int i = 1; i <= m; i++)
            num = num.divide(BigInteger.valueOf(i));

        System.out.println(num);

    }

}