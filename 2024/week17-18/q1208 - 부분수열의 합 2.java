// https://www.acmicpc.net/problem/1208

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), S = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int half = N / 2;
        int lsize = 1 << half;
        int rsize = 1 << (N - half);
        Map<Integer, Integer> lmap = new HashMap<>();

        // 비트필드를 이용한 조합 생성
        for (int i = 1; i < lsize; i++) {
            int tmp = 0;
            for (int j = 0; j < half; j++) {
                if ((i & 1 << j) == 1 << j) {
                    tmp += arr[j];
                }
            }
            if (!lmap.containsKey(tmp)) lmap.put(tmp, 0);
            lmap.put(tmp, lmap.get(tmp) + 1);
        }

        long ans = lmap.getOrDefault(S, 0); // 배열 좌측 반에서 얻은 조합 중 S 개수.

        for (int i = 1; i < rsize; i++) {
            int rval = 0;
            for (int j = 0; j < (N - half); j++) {
                if ((i & 1 << j) == 1 << j) {
                    rval += arr[j + half];
                }
            }
            if (rval == S) ans++; // 배열 우측 반에서 얻은 조합 중 S 개수.
            ans += lmap.getOrDefault(S - rval, 0); // 양측 조합을 최소 1개 이상 사용할 때의 S 개수.
        }

        System.out.println(ans);

    }
}
