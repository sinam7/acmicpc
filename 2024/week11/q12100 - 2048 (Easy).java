// https://www.acmicpc.net/problem/12100

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Main {

    static int N;

    private static void solve() {
        N = sc.nextInt();
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int answer = 0;
        for (int i = 0; i < 4; i++) {
            answer = Math.max(dfs(0, arr, i), answer);
        }

        System.out.println(answer);
    }

    /*
        1. 연속된 빈 공간의 끝까지 이동
        2. 같은 숫자의 블럭과 마주치면 흡수됨.

        이동은 4방향으로 이루어진다. (상, 하, 좌, 우)
        따라서, 4방향으로 이동을 시도하고, 각각의 경우에 대해 dfs를 호출한다.

        이동은 다음과 같이 이루어진다.
        1. 이동할 행/열을 선택해 이동 방향과 가장 가까운 블럭부터 큐에 넣는다.
        2. 큐에서 블럭을 하나씩 꺼내 이동 방향에 가까운 벽에 놓는다.
        3. 해당 블럭의 정보를 기억한다.
        4. 큐가 빌 때까지 2~3을 반복한다. 만약 꺼낸 블럭이 이전 블럭과 같다면, 두 블럭을 합친다.
        5. 모든 행/열에 대해 1~4를 반복한다.
     */
    private static int dfs(int stage, int[][] origin, int direction) {

        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = origin[i].clone();
        }

        // 상
        if (direction == 0) {
            arr = moveUp(arr);
        } else if (direction == 1) {
            arr = moveDown(arr);
        } else if (direction == 2) {
            arr = moveLeft(arr);
        } else if (direction == 3) {
            arr = moveRight(arr);
        }

        if (stage + 1 == 5) {
            return Arrays.stream(arr).mapToInt(x -> Arrays.stream(x).max().getAsInt()).max().getAsInt();
        }

        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = Math.max(result, dfs(stage + 1, arr, i));
        }

        return result;


    }

    private static int[][] moveUp(int[][] arr) {
        Queue<Integer> queue = new LinkedList<>();
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (arr[y][x] != 0) {
                    queue.add(arr[y][x]);
                    arr[y][x] = 0;
                }
            }
            int idx = 0;
            while (!queue.isEmpty()) {
                int current = queue.poll();
                if (arr[idx][x] == 0) {
                    arr[idx][x] = current;
                } else if (arr[idx][x] == current) {
                    arr[idx][x] *= 2;
                    idx++;
                } else {
                    arr[++idx][x] = current;
                }
            }
        }
        return arr;
    }

    private static int[][] moveDown(int[][] arr) {
        Queue<Integer> queue = new LinkedList<>();
        for (int x = 0; x < N; x++) {
            for (int y = N - 1; y >= 0; y--) {
                if (arr[y][x] != 0) {
                    queue.add(arr[y][x]);
                    arr[y][x] = 0;
                }
            }
            int idx = N - 1;
            while (!queue.isEmpty()) {
                int current = queue.poll();
                if (arr[idx][x] == 0) {
                    arr[idx][x] = current;
                } else if (arr[idx][x] == current) {
                    arr[idx][x] *= 2;
                    idx--;
                } else {
                    arr[--idx][x] = current;
                }
            }
        }
        return arr;
    }

    private static int[][] moveLeft(int[][] arr) {
        Queue<Integer> queue = new LinkedList<>();
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (arr[y][x] != 0) {
                    queue.add(arr[y][x]);
                    arr[y][x] = 0;
                }
            }
            int idx = 0;
            while (!queue.isEmpty()) {
                int current = queue.poll();
                if (arr[y][idx] == 0) {
                    arr[y][idx] = current;
                } else if (arr[y][idx] == current) {
                    arr[y][idx] *= 2;
                    idx++;
                } else {
                    arr[y][++idx] = current;
                }
            }
        }
        return arr;
    }

    private static int[][] moveRight(int[][] arr) {
        Queue<Integer> queue = new LinkedList<>();
        for (int y = 0; y < N; y++) {
            for (int x = N - 1; x >= 0; x--) {
                if (arr[y][x] != 0) {
                    queue.add(arr[y][x]);
                    arr[y][x] = 0;
                }
            }
            int idx = N - 1;
            while (!queue.isEmpty()) {
                int current = queue.poll();
                if (arr[y][idx] == 0) {
                    arr[y][idx] = current;
                } else if (arr[y][idx] == current) {
                    arr[y][idx] *= 2;
                    idx--;
                } else {
                    arr[y][--idx] = current;
                }
            }
        }
        return arr;
    }


    public static void main(String[] args) {
        sc.init();
        int T = 0;
        boolean nowDebugging = false;
//        T = sc.nextInt();
        do {
            Main.solve();
        } while (nowDebugging || --T > 0);
    }

    @SuppressWarnings("unused")
    static class Pair<T> {
        T first;
        T second;

        Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?> pair = (Pair<?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    // fast input
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