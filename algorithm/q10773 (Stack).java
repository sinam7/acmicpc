// https://www.acmicpc.net/problem/10773
/*
    q10773 - 제로
    Stack
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {

            int cmd = Integer.parseInt(br.readLine());

            if (cmd == 0) stack.pop();
            else stack.push(cmd);

        }

        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }

        System.out.println(ans);

    }

}