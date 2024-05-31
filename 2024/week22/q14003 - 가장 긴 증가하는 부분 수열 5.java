// https://www.acmicpc.net/problem/14003

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

class Main {

    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] origin = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Stack<int[]> stack = new Stack<>();

        int[] lis = new int[N];
        Arrays.fill(lis, Integer.MAX_VALUE);
        int top = 0;

        lis[0] = origin[0];
        stack.push(new int[]{top, origin[0]}); // {LIS 인덱스, 값}
        for (int i = 1; i < N; i++) {
            if (lis[top] < origin[i]) { // 마지막 요소보다 크면 LIS에 추가
                lis[++top] = origin[i];
                stack.push(new int[]{top, origin[i]}); // {LIS 인덱스, 값}
            } else {
                bsearch(0, top, origin[i], lis, stack);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(top + 1).append("\n");
        int[] ans = new int[top + 1];
        while (top >= 0) {
            int[] curr = stack.pop();
            if (top == curr[0]) {
                ans[top--] = curr[1];
            }
        }
        for (int e : ans) {
            sb.append(e).append(" ");
        }
        System.out.println(sb);


    }

    private static void bsearch(int start, int end, int value, int[] lis, Stack<int[]> stack) {
        if (start == end) {
            lis[start] = value;
            stack.push(new int[]{start, value});
            return;
        }
        int mid = (start + end) / 2;
        if (lis[mid] >= value) {
            bsearch(start, mid, value, lis, stack);
        } else {
            bsearch(mid + 1, end, value, lis, stack);
        }
    }
}