// https://www.acmicpc.net/problem/11725
/*
    기본 예제 통과 -> WA
    dfs 적용해 기존에 이미 연결된 노드 중 하나가 상위 노드에 연결되면 전부 갱신해주기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1]; // arr[노드번호]: 부모
        for (int i = 1; i <= N; i++) arr[i] = i;

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int ta = Integer.parseInt(st.nextToken()), tb = Integer.parseInt(st.nextToken());
            int a = Math.min(ta, tb), b = Math.max(ta, tb);

            if (a == 1) {
                arr[arr[b]] = b; // 이거 대신 dfs 적용해서 처음부터 다시 갈아줘야함
                arr[b] = 1;
            }
            else {
                if (arr[b] == 1) arr[a] = b;
                else arr[b] = a;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++)
            sb.append(arr[i]).append('\n');

        System.out.println(sb);

    }

}