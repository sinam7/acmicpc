// https://www.acmicpc.net/problem/2166
/*
    q2166 - 다각형의 면적
    신발끈 공식
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;
    int N;
    double ans1 = 0, ans2 = 0;
    ArrayList<Point> points;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        points = new ArrayList<>();

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points.add(new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())));
        }
        points.add(points.get(0));

        // 신발끈 공식
        for (int i = 0; i < points.size() - 1; i++) {
            Point a = points.get(i), b = points.get(i + 1);
            ans1 += (a.x * b.y);
            ans2 += (a.y * b.x);
        }

        double ans = Math.abs(ans1 - ans2) / 2.0;
        System.out.printf("%.1f", (double) Math.round(ans * 10) / 10);
    }

    private static class Point {

        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

    }

}