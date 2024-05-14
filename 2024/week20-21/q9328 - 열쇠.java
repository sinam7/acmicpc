// https://www.acmicpc.net/problem/9328

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static final int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sb.append(solve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
        }

        System.out.println(sb);
    }

    private static int solve(int h, int w) throws IOException {
        char[][] arr = new char[h][];
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) arr[i] = br.readLine().toCharArray();

        boolean[] hasKeys = new boolean[26];
        char[] keys = br.readLine().toCharArray();
        if (keys[0] != '0') for (char key : keys) hasKeys[key - 'a'] = true;

        Queue<int[]> queue = new LinkedList<>();
        HashMap<Integer, ArrayList<int[]>> waitingDoors = new HashMap<>();
        for (int i = 0; i < h; i++) {
            if (isPassable(i, 0, arr, hasKeys, waitingDoors)) queue.add(new int[]{i, 0});
            if (isPassable(i, w - 1, arr, hasKeys, waitingDoors)) queue.add(new int[]{i, w - 1});
        }
        for (int i = 1; i < w - 1; i++) {
            if (isPassable(0, i, arr, hasKeys, waitingDoors)) queue.add(new int[]{0, i});
            if (isPassable(h - 1, i, arr, hasKeys, waitingDoors)) queue.add(new int[]{h - 1, i});
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int y = curr[0], x = curr[1];

            if (visited[y][x] || !isPassable(y, x, arr, hasKeys, waitingDoors)) continue;
            visited[y][x] = true;

            char here = arr[y][x];
            if (here == '$') ans++;
            else if ('a' <= here && here <= 'z' && waitingDoors.containsKey(here - 'a')) {
                queue.addAll(waitingDoors.get(here - 'a'));
                waitingDoors.remove(here - 'a'); // may not need
            }

            for (int i = 0; i < 4; i++) {
                int ny = y + direction[i][0], nx = x + direction[i][1];
                if (!isValid(ny, nx, h, w)) continue;
                if (!visited[ny][nx] && isPassable(ny, nx, arr, hasKeys, waitingDoors)) {
                    queue.add(new int[]{ny, nx});
                }
            }
        }

        return ans;
    }

    private static boolean isPassable(int y, int x,
                                      char[][] arr, boolean[] hasKeys,
                                      HashMap<Integer, ArrayList<int[]>> waitingDoors) {
        char here = arr[y][x];
        if (here == '*') return false;
        if (here == '.') return true;
        if (here == '$') return true;
        if ('a' <= here && here <= 'z') {
            return hasKeys[here - 'a'] = true;
        } else if (!hasKeys[here - 'A']) {
            if (!waitingDoors.containsKey(here - 'A')) waitingDoors.put(here - 'A', new ArrayList<>());
            waitingDoors.get(here - 'A').add(new int[]{y, x});
            return false;
        } else return true;
    }

    private static boolean isValid(int y, int x, int h, int w) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }

}