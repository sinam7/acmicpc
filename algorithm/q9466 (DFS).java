// https://www.acmicpc.net/problem/9466
/*
    q9466 - 텀 프로젝트
    DFS
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;
    int N, cnt;
    int[] arr;
    boolean[] visit, done;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            arr = Arrays.stream(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
            done = new boolean[N + 1];
            visit = new boolean[N + 1];
            cnt = 0; // 팀에 포함된 학생 수

            for (int i = 1; i <= N; i++) {
                if (!done[i]) dfs(i);
            }

            sb.append(N - cnt).append("\n");

        }
        System.out.println(sb);


    }

    private void dfs(int i) {
        visit[i] = true;
        int next = arr[i];

        // 선택한 학생을 확인하지 않았으면 확인한다.
        if (!visit[next]) {
            dfs(next);
        } else if (!done[next]) { // 이미 확인한 학생인데 또 확인한다 = 사이클이 생겼다.
            for (int j = next; j != i; j = arr[j]) {
                cnt++; // 연결된 학생 수만큼 더하고,
            }
            cnt++; // 본인도 더해준다.
        }

        done[i] = true; // 다시 체크하지 않기 위해 플래그를 체크한다.
    }

}