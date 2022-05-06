// https://www.acmicpc.net/problem/4949
/*
    q4949 - 균형잡힌 세상
    Stack을 이용해 괄호 짝 맞추기
 */

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringBuilder answer = new StringBuilder();

        String s;
        while (true) {
            s = sc.nextLine();
            if (s.equals(".")) break;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(' || c == ')' || c == '[' || c == ']') sb.append(c);
            }
            if (check(sb.toString())) answer.append("yes\n");
            else answer.append("no\n");

        }
        System.out.println(answer);

    }

    private static boolean check(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                case '[':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
            }
        }
        return stack.isEmpty();
    }

}