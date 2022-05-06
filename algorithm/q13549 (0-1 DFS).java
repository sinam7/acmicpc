// https://www.acmicpc.net/problem/13549
/*
    q13549 - 숨바꼭질 3
    0-1 DFS 풀이 가능 (가중치가 0/1만 존재함)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }

}

class Solve {

    int N, K;
    int[] dist;

    public void run() throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[200001];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[N] = 0;

        zero_one_bfs(); // 0-1 DFS

        System.out.println(dist[K]);

    }

    private void zero_one_bfs() {
        Deque<Integer> deque = new ArrayDeque<>();

        deque.offerFirst(N);

        while (!deque.isEmpty()) {
            int curr = deque.removeFirst();

            int warp = 2 * curr;
            if (warp < 200001 && dist[warp] > dist[curr]) {
                dist[warp] = dist[curr];
                deque.offerFirst(warp);
            }

            for (int i : new int[]{curr - 1, curr + 1}) {
                if (i < 0 || 200000 < i) continue;
                if (dist[i] > dist[curr]) {
                    dist[i] = dist[curr] + 1;
                    deque.offerLast(i);
                }
            }
        }

    }

}
