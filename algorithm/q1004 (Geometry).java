// https://www.acmicpc.net/problem/1004

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private void solve() {
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int answer = 0;

            Point source = new Point(sc.nextInt(), sc.nextInt());
            Point destination = new Point(sc.nextInt(), sc.nextInt());
            int i1 = sc.nextInt();
            for (int j = 0; j < i1; j++) {
                Circle circle = new Circle(new Point(sc.nextInt(), sc.nextInt()), sc.nextInt());
                if (circle.checkInside(source) ^ circle.checkInside(destination)) answer++;
            }

            System.out.println(answer);
        }
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

class Circle {
    Point center;
    int radius;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public boolean checkInside(Point target) {
        double dist = Math.sqrt(Math.pow(center.x - target.x, 2) + Math.pow(center.y - target.y, 2));
        return dist < radius;
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

