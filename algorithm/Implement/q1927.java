// https://www.acmicpc.net/problem/1927
/*
    Min Heap
    빠른 입출력 방식 사용 필요
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        Queue<Integer> queue = new PriorityQueue<>();           // Min heap을 직접 구현했다면 자료구조 디렉토리에 저장했을 것.
                                                                // 표준 라이브러리를 사용해 끝냈으므로 구현에 가깝지 않은가..?

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   // 빠른 입력
        StringBuilder sb = new StringBuilder();                                     // 빠른 출력

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int cmd = Integer.parseInt(br.readLine());;
            if (cmd == 0) {
                Integer out = queue.poll();
                sb.append(out == null ? 0 : out).append('\n');
            }
            else
                queue.offer(cmd);
        }

        System.out.println(sb);
    }

}