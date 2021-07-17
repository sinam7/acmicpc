// https://www.acmicpc.net/problem/1697
/*
    bfs를 이용한 최단거리 찾기
    새 위치 offer 시, condition check가 필요함 (MLE/TLE 방지)

    현재 위치에서 n-1, n+1, 2n 위치를 큐에 offer, 해당 위치에 도달할 때까지 반복, 각 사이클마다 카운트
 */

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), K = sc.nextInt();

        sc.close();

        Queue<int[]> queue = new LinkedList<>();    // bfs
        Set<Integer> set = new HashSet<>();         // 중복 방지

        queue.offer(new int[]{N, 0});               // {값, 이동 시간}

        while (!queue.isEmpty()) {
            int[] t = queue.poll();
            int num = t[0], time = t[1];
            if (num == K) {
                System.out.println(time);
                break;
            }

            if (num - 1 >= 0 && !set.contains(num - 1)) {       // 범위 밖 값이 들어가는 것 막기 (MLE)
                queue.offer(new int[]{num - 1, time + 1});
                set.add(num - 1);
            }
            if (num + 1 <= K && !set.contains(num + 1)) {       // 필요 없이 큰 값이 들어가는 것 막기 (MLE)
                queue.offer(new int[]{num + 1, time + 1});
                set.add(num + 1);
            }
            if (num * 2 < K * 2 && !set.contains(num * 2)) {    // 필요 없이 큰 값이 들어가는 것 막기 (MLE)
                queue.offer(new int[]{num * 2, time + 1});
            }

        }

    }

}