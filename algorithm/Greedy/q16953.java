// https://www.acmicpc.net/problem/16953
/*
    A → B - Greedy Algorithm
    A → B보다 B → A가 훨씬 쉽고 빠르다.
    (사실 A → B 가 가능한지는 생각해보지 않았다.)
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }

}

class Solve {
    int a, b;

    public void run() throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        {
            String[] s = br.readLine().split(" ");
            a = Integer.parseInt(s[0]);
            b = Integer.parseInt(s[1]);
        }

        // logic
        System.out.println(bfs(b, 1));

    }

    private int bfs(int b, int cnt) {
        if (b < a) return -1;
        if (b == a) return cnt;
        if (b % 10 == 1) return bfs(b / 10, cnt + 1);
        if (b % 2 == 1) return -1;
        return bfs(b / 2, cnt + 1);
    }
}
