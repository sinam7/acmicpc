// https://www.acmicpc.net/problem/13460

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    private static final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static Marble hole;
    private static boolean[][][][] v;

    private static char[][] board;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        Marble red = null, blue = null;
        board = new char[height][width];
        v = new boolean[width][height][width][height];
        for (int i = 0; i < height; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < width; j++) {
                board[i][j] = chars[j];
                if (chars[j] == 'R') red = new Marble(j, i);
                else if (chars[j] == 'B') blue = new Marble(j, i);
                else if (chars[j] == 'O') hole = new Marble(j, i);
            }
        }

        System.out.println(bfs(red, blue));


    }

    private static int bfs(Marble red, Marble blue) {
        PriorityQueue<Entry> queue = new PriorityQueue<>();
        queue.add(new Entry(red, blue, 0));

        while (!queue.isEmpty()) {
            Entry curr = queue.poll();
            if (v[curr.red.x][curr.red.y][curr.blue.x][curr.blue.y]) continue;
            if (curr.trial == 10) return -1;
            v[curr.red.x][curr.red.y][curr.blue.x][curr.blue.y] = true;
            for (int i = 0; i < 4; i++) { // direction
                Marble new_red = moveMarble(curr.red, i);
                Marble new_blue = moveMarble(curr.blue, i);

                if (new_blue.equals(hole)) continue;
                if (new_red.equals(hole))
                    return curr.trial + 1;

                if (new_blue.equals(new_red)) {
                    switch (i) {
                        case 0:
                            if (curr.blue.x < curr.red.x) new_blue.x--;
                            else new_red.x--;
                            break;
                        case 1:
                            if (curr.blue.y < curr.red.y) new_blue.y--;
                            else new_red.y--;
                            break;
                        case 2:
                            if (curr.blue.x > curr.red.x) new_blue.x++;
                            else new_red.x++;
                            break;
                        case 3:
                            if (curr.blue.y > curr.red.y) new_blue.y++;
                            else new_red.y++;
                            break;
                    }
                }
                if (v[new_red.x][new_red.y][new_blue.x][new_blue.y]) continue;

                queue.add(new Entry(new_red, new_blue, curr.trial + 1));
            }
        }
        return -1;
    }

    private static Marble moveMarble(Marble marble, int d) {
        int x = marble.x, y = marble.y;
        while (board[y][x] != '#') {
            y += directions[d][0];
            x += directions[d][1];
            if (board[y][x] == 'O') return new Marble(x, y);
        }
        y -= directions[d][0];
        x -= directions[d][1];
        return new Marble(x, y);
    }

    static class Entry implements Comparable<Entry> {
        Marble red, blue;
        int trial;

        public Entry(Marble red, Marble blue, int trial) {
            this.red = red;
            this.blue = blue;
            this.trial = trial;
        }

        @Override
        public int compareTo(Entry o) {
            return this.trial - o.trial;
        }
    }

    static class Marble {
        int x, y;

        public Marble(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Marble marble = (Marble) o;

            if (x != marble.x) return false;
            return y == marble.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }


}