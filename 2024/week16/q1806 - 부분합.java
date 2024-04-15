// https://www.acmicpc.net/problem/1806

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int ans = getAns(n, s, arr);
        ans = (ans == Integer.MAX_VALUE ? 0 : ans);
        System.out.println(ans);
    }

    private static int getAns(int n, int s, int[] arr) {
        int ans = Integer.MAX_VALUE;
        int sum = 0, left = 0;
        for (int right = left; right < n; right++) {
            sum += arr[right];
            while (sum >= s) {
                ans = Math.min(ans, right - left + 1);
                sum -= arr[left++];

                if (ans == 1) return ans;
            }
        }
        return ans;
    }
}