// https://www.acmicpc.net/problem/7662
/*
    q7662 - 이중 우선순위 큐 (Deque)

    TreeMap의 특징을 이용해 풀이.
     - 자동 정렬 (natural order)
     - 맨 앞의 키와 맨 뒤의 키에 모두 접근 가능
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();

            int k = Integer.parseInt(br.readLine());
            for (int j = 0; j < k; j++) {
                String[] s = br.readLine().split(" ");

                String cmd = s[0];
                int n = Integer.parseInt(s[1]);

                if (cmd.equals("I")) {
                    treeMap.put(n, treeMap.getOrDefault(n, 0) + 1);
                } else {
                    if (treeMap.size() == 0) continue;

                    int num = n == 1 ? treeMap.lastKey() : treeMap.firstKey();
                    treeMap.put(num, treeMap.get(num) - 1);
                    if (treeMap.get(num) == 0) treeMap.remove(num);
                }
            }

            if (treeMap.isEmpty()) sb.append("EMPTY\n");
            else sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
        }
        System.out.print(sb);

    }

}