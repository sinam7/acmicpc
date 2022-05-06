// https://www.acmicpc.net/problem/15686
/*
    q15686 - 치킨 배달
    Brute-force
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;

    int N, M;
    ArrayList<Pair> store, home;
    boolean[] isStoreOpen;
    int MIN;

    public void run() throws IOException {

        // input
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        store = new ArrayList<>();
        home = new ArrayList<>();

        // 가게와 집 사이 장애물(최단거리 계산에 방해되는 벽 등)이 없으므로 굳이 BFS를 사용할 필요가 없다.
        for (int i = 0; i < N; i++) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                if (s[j] == 2) store.add(new Pair(j, i));
                if (s[j] == 1) home.add(new Pair(j, i));
            }
        }

        MIN = Integer.MAX_VALUE;
        isStoreOpen = new boolean[store.size()];
        chooseStore(0, 0);

        System.out.println(MIN);
    }

    private void chooseStore(int i, int start) {
        if (i == M) {
            calculate();
            return;
        }

        // backtrack
        for (int j = start; j < store.size(); j++) {
            if (!isStoreOpen[j]) {
                isStoreOpen[j] = true;
                chooseStore(i + 1, j + 1); // 중복 방지 -> 시간 최적화
                isStoreOpen[j] = false;
            }
        }

    }

    private void calculate() {
        int sum = 0;
        for (int ih = 0; ih < home.size(); ih++) {
            int chickenDistance = Integer.MAX_VALUE; // 한 집에서 열린 모든 점포를 대상으로 계산한 최단 치킨거리
            Pair h = home.get(ih);
            for (int is = 0; is < store.size(); is++) {
                if (isStoreOpen[is]) {
                    Pair s = store.get(is);
                    chickenDistance = Math.min(chickenDistance,
                            Math.abs(h.x - s.x) + Math.abs(h.y - s.y));
                }
            }
            sum += chickenDistance; // 를 합산해
        }
        MIN = Math.min(MIN, sum); // 다른 모든 점포 선택 경우의 수와 비교해 최솟값을 취한다.

    }


    private static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
