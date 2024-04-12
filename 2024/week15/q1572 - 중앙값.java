// https://www.acmicpc.net/problem/1572

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static final int MAX_INDEX = 65536;
    static int[] segtree = new int[(MAX_INDEX + 1) * 4], arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, K;
        long ans = 0;
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for (int i = 1; i <= K; i++) {
            arr[i] = Integer.parseInt(br.readLine()) + 1;
            update(1, MAX_INDEX + 1, 1, arr[i], 1);
        }

        ans += find(1, MAX_INDEX + 1, 1, (K + 1) / 2) - 1;

        for (int i = K + 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine()) + 1;
            update(1, MAX_INDEX + 1, 1, arr[i], 1);
            update(1, MAX_INDEX + 1, 1, arr[i - K], -1);
            ans += find(1, MAX_INDEX + 1, 1, (K + 1) / 2) - 1;
        }

        System.out.println(ans);

    }

    private static long find(int start, int end, int idx, int target) {
        if (segtree[idx] < target) return -1;
        if (start == end) return start;

        int mid = (start + end) / 2;

        long left = find(start, mid, idx * 2, target);
        if (left == -1) {
            return find(mid + 1, end, idx * 2 + 1, target - segtree[idx * 2]);
        } else {
            return left;
        }
    }

    private static void update(int start, int end, int idx, int target, int diff) {
        if (target < start || end < target) return;
        segtree[idx] += diff;

        if (target == start && target == end) return;

        int mid = (start + end) / 2;
        update(start, mid, idx * 2, target, diff);
        update(mid + 1, end, idx * 2 + 1, target, diff);
    }

}