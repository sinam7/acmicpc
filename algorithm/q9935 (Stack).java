// https://www.acmicpc.net/problem/9935
/*
    q9935 - 문자열 폭발
    Stack

    contains, replace/delete -> 내장함수 알고리즘 상 잦은 GC 대상 변수 생성으로 인해 메모리초과 발생
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;

    String bomb;
    char[] input;
    Stack<Character> s;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine().toCharArray();
        bomb = br.readLine();

        s = new Stack<>();

        for (int i = 0; i < input.length; i++) {
            s.add(input[i]);
            if (s.size() >= bomb.length() && isBomb())
                for (int j = 0; j < bomb.length(); j++)
                    s.pop();
        }

        if (s.isEmpty()) System.out.println("FRULA");
        else {
            StringBuilder sb = new StringBuilder();
            for (Character ch : s) sb.append(ch);
            System.out.println(sb);
        }

    }

    private boolean isBomb() {
        for (int i = 0; i < bomb.length(); i++)
            if (s.get(s.size() - 1 - i) != bomb.charAt(bomb.length() - 1 - i))
                return false;
        return true;
    }
}