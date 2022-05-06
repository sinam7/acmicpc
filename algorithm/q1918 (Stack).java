// https://www.acmicpc.net/problem/1918
/*
    q1918 - 후위 표기식
    Stack
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();

        solve.run();    // comment while running test.java

    }

}

class Solve {

    Stack<Character> opStack = new Stack<>();

    public void run() throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] arr = br.readLine().toCharArray();

        for (char c : arr) {
            switch (c) {
                case '*':
                case '/':
                case '+':
                case '-':
                    while (!opStack.isEmpty() && getPriority(opStack.peek()) >= getPriority(c))
                        sb.append(opStack.pop());
                    opStack.push(c);
                    break;
                case '(':
                    opStack.push('(');
                    break;
                case ')':
                    while (!opStack.isEmpty()) {
                        if (opStack.peek() == '(') {
                            opStack.pop();
                            break;
                        }
                        sb.append(opStack.pop());
                    }
                    break;
                default:
                    sb.append(c);
            }
        }

        while (!opStack.isEmpty()) sb.append(opStack.pop());
        System.out.println(sb);
    }

    private static int getPriority(char c) {
        switch (c) {
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }

}
