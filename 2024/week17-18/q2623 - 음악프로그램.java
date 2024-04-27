// https://www.acmicpc.net/problem/2623

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 문제에 빠진 제약사항: 모든 가수들은 적어도 한 명 이상의 보조 PD가 담당하고 있다. (모든 보조 PD의 담당 가수의 합집합은 전체 가수와 같다.)
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        int[] prev = new int[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            if (k == 0) continue;

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to;
            for (int j = 1; j < k; j++) {
                to = Integer.parseInt(st.nextToken()) - 1;
                if (!map.containsKey(from)) map.put(from, new ArrayList<>());
                map.get(from).add(to);
                prev[to]++;

                from = to;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (prev[i] == 0) queue.offer(i);
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            Integer curr = queue.poll();

            sb.append(curr + 1).append("\n");
            cnt++;

            if (map.containsKey(curr)) {
                for (int next : map.get(curr)) {
                    prev[next]--;
                    if (prev[next] == 0) queue.offer(next);
                }
            }

        }

        if (cnt < N) System.out.println(0);
        else System.out.println(sb);
    }
}
