// https://www.acmicpc.net/problem/11659

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] segtree = new int[N * 4];

        init(1, N, 1, segtree, arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            sb.append(find(1, N ,1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), segtree)).append("\n");
        }
        System.out.println(sb);
    }

    private static int init(int start, int end, int idx, int[] segtree, int[] arr) {
        if (start == end) {
            segtree[idx] = arr[start - 1];
            return segtree[idx];
        }

        int mid = (start + end) / 2;
        return segtree[idx] = init(start, mid, idx * 2, segtree, arr) + init(mid + 1, end, idx * 2 + 1, segtree, arr);
    }

    private static int find(int start, int end, int idx, int rstart, int rend, int[] segtree) {
        if (rend < start || end < rstart) return 0;
        if (rstart <= start && end <= rend) return segtree[idx];

        int mid = (start + end) / 2;
        return find(start, mid, idx * 2, rstart, rend, segtree) +
                find(mid + 1, end, idx * 2 + 1, rstart, rend, segtree);
    }


}