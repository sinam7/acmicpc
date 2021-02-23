// https://www.acmicpc.net/problem/1074

/*
1차: 실제 배열을 만들어 재귀 호출로 풀이 -> 메모리 초과
2차: 배열을 지우고 인덱스 값으로만 재귀 호출 -> 시간 초과
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    int i = 0, r, c;

    /* 3차 알고리즘 계획
    1. r,c가 어느 구역에 존재하는지를 찾아야 한다.
    2. 그 구역을 찾으면, 그 이전의 크고 작은 구역에서의 연산 과정을 스킵, i 값을 강제 할당한다.
    3. 재귀 호출을 통해 r, c가 존재하는 4칸 크기 구역까지 들어간다.
    4. 4칸 크기 구역에서 r, c인 칸을 찾아 출력한다.
     */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

private void recursive(int rs, int re, int cs, int ce) {
        if (re - rs > 1 || ce - cs > 1) {
            recursive(rs, (rs + re) / 2, cs, (cs + ce) / 2);
            recursive(rs, (rs + re) / 2, ((cs + ce) / 2) + 1, ce);
            recursive(((rs + re) / 2) + 1, re, cs, (cs + ce) / 2);
            recursive(((rs + re) / 2) + 1, re, ((cs + ce) / 2) + 1, ce);
        } else {
            if (!(rs == r && cs == c)) i++; else System.out.println(i);
            if (!(rs == r && ce == c)) i++; else System.out.println(i);
            if (!(re == r && cs == c)) i++; else System.out.println(i);
            if (!(re == r && ce == c)) i++; else System.out.println(i);
        }
    }


    private void solve() {
        int n = sc.nextInt();
        int n1 = 1 << n;

        r = sc.nextInt();
        c = sc.nextInt();
        if (r == 0 && c == 0) {
            System.out.println(0);
            return;
        }

        recursive(0, n1 - 1, 0, n1 - 1);
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