// https://www.acmicpc.net/problem/1202
/*
    q1202 - 보석 도둑
    Greedy, Sort, PriorityQueue
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;
    int N, K;
    Jewel[] jewels;
    int[] C;
    long ans = 0;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        C = new int[K];
        for (int i = 0; i < K; i++) {
            C[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewels);
        Arrays.sort(C);

        int jewelIdx = 0; // 이미 확인한 보석은 넘어가기 위함
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < K; i++) {
            // 오름차순 정렬되어 있으므로 무게상한 넘어가면 자동으로 break
            while (jewelIdx < N && jewels[jewelIdx].mass <= C[i]) {
                pq.offer(jewels[jewelIdx++].value);
            }

            // 가방 당 1개이므로 while이 아닌 if로 1회만 처리; 남은 보석은 더 큰 가방에서 처리 가능
            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }

        System.out.println(ans);
    }


    private static class Jewel implements Comparable<Jewel> {

        int mass;
        int value;

        public Jewel(int mass, int value) {
            this.mass = mass;
            this.value = value;
        }

        @Override // Ascending
        public int compareTo(Jewel o) {
            return this.mass - o.mass;
        }
    }
}