// https://www.acmicpc.net/problem/2108

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    private void solve() {
        List<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> freq = new HashMap<>();

        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int tmp = sc.nextInt();
            list.add(tmp);

            // 최빈값 계산을 위한 HashMap<값, 빈도수>
            if (freq.containsKey(tmp)) {
                freq.put(tmp, freq.get(tmp) + 1);
            } else {
                freq.put(tmp, 1);
            }
        }
        
        // 정렬
        Collections.sort(list);

        // 산술평균
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        System.out.printf("%.0f\n", sum / (double) list.size());

        // 중간값 (assert N은 홀수)
        System.out.println(list.get(list.size() / 2));

        // 최빈값
        // 1. 빈도수 최다 구하기
        int max = -1;
        for (int key : freq.keySet()) {
            if (freq.get(key) > max) {
                max = freq.get(key);
            }
        }
        
        // 2. 최다 빈도수를 가진 key들을 새 ArrayList에 저장
        List<Integer> frequency = new ArrayList<>();
        for (int key : freq.keySet()) {
            if (freq.get(key) == max) {
                frequency.add(key);
            }
        }
        // 3. 최빈값들을 정렬
        Collections.sort(frequency);
        // 4. 최빈값 출력
        System.out.println(frequency.size() == 1 ? frequency.get(0) : frequency.get(1));

        // 범위
        System.out.println(list.get(list.size() - 1) - list.get(0));
    }

    public static void main(String[] args) {
        sc.init();

        new Main().solve();
    }

    @SuppressWarnings("unused")
    static class sc {
        private static BufferedReader br;
        private static StringTokenizer st;

        static void init() {
            br = new BufferedReader(new InputStreamReader(System.in));
            st = new StringTokenizer("");
        }

        static String readLine() {
            try {
                return br.readLine();
            } catch (IOException ignored) {
            }
            return null;
        }

        static String readLineReplace() {
            try {
                return br.readLine().replaceAll("\\s+", "");
            } catch (IOException ignored) {
            }
            return null;
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

        static long nextLong() {
            return Long.parseLong(next());
        }

        static int nextInt() {
            return Integer.parseInt(next());
        }

        static double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}