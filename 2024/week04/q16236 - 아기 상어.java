// https://www.acmicpc.net/problem/16236

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    private void solve() {
        int N = sc.nextInt();
        int[][] map = new int[N][N];
        int[] shark = new int[2];

        // priority queue for what fish to eat by distance.
        // if distance is same, eat fish with smaller x position. if x position is same, eat fish with smaller y position.
        // data type = {x, y, distance}
        PriorityQueue<int[]> foodQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] == o2[2]) {
                if (o1[0] == o2[0]) {
                    return Integer.compare(o1[1], o2[1]);
                }
                return Integer.compare(o1[0], o2[0]);
            }
            return Integer.compare(o1[2], o2[2]);
        });

        // input map data
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int nInt = sc.nextInt();
                if (nInt == 9) {
                    // shark position
                    shark = new int[]{i, j};
                    nInt = 0;
                }
                map[i][j] = nInt;
            }
        }

        int answer = 0;
        int sharkSize = 2;
        int sharkEat = 0;

        while (true) {
            // scan all same distance's fish
            bfs(map, shark, foodQueue, sharkSize);

            // if food found, eat the fish according to priority
            if (!foodQueue.isEmpty()) {
                // get fish position
                int[] fish = foodQueue.poll();
                int nx = fish[0];
                int ny = fish[1];
                int distance = fish[2];
                // increase shark eat count
                sharkEat++;
                // remove fish
                map[nx][ny] = 0;
                // if shark eat count is same as shark size, increase shark size
                if (sharkSize < 7 && sharkEat == sharkSize) {
                    sharkSize++;
                    sharkEat = 0;
                }
                // increase shark move count
                answer += distance;

                // restart bfs
                foodQueue.clear();
                shark = new int[]{nx, ny};
            } else {
                // if foodQueue is empty, call mother and break
                break;
            }
        }

        System.out.println(answer);
    }

    // bfs
    private void bfs(int[][] map, int[] shark, PriorityQueue<int[]> foodQueue, int sharkSize) {
        int N = map.length;
        int[][] visited = new int[N][N];
        // dx, dy priority: up, left, right, down
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        // init queue with shark position
        Queue<int[]> queue = new LinkedList<>();

        queue.add(shark);
        visited[shark[0]][shark[1]] = 1;

        // bfs starts
        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            int x = position[0];
            int y = position[1];

            // check 4 positions around shark
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // position validation - out of map
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                // if visited or bigger than shark size - cannot go through (wall)
                if (visited[nx][ny] != 0 || map[nx][ny] > sharkSize) {
                    continue;
                }

                // if smaller than shark size - eat fish
                if (map[nx][ny] != 0 && map[nx][ny] < sharkSize) {
                    // add fish to foodQueue
                    foodQueue.add(new int[]{nx, ny, visited[x][y]});
                } else {
                    // cannot eat (just passing) - enqueue
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = visited[x][y] + 1;
                }

            }
        }
    }


    public static void main(String[] args) {
        sc.init();

        new Main().solve();
    }

    // fast io
    static class sc {
        private static BufferedReader br;
        private static StringTokenizer st;

        static void init() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        static String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException ignored) {
                }
            }
            return st.nextToken();
        }

        static int nextInt() {
            return Integer.parseInt(next());
        }

    }
}