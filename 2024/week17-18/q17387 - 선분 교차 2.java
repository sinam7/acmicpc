// https://www.acmicpc.net/problem/17387

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Point p1, p2, p3, p4;

        StringTokenizer st = new StringTokenizer(br.readLine());
        p1 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        p2 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        p3 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        p4 = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        // 끝 점이 겹치는 경우
        if (p1.equals(p3) || p1.equals(p4) || p2.equals(p3) || p2.equals(p4)) {
            System.out.println(1);
            return;
        }

        // 축 상으로 완전히 떨어져 있는 경우 (x, y 중 하나라도 겹치지 않는 경우)
        if (max(p3.x, p4.x) < min(p1.x, p2.x) || max(p1.x, p2.x) < min(p3.x, p4.x) ||
                max(p3.y, p4.y) < min(p1.y, p2.y) || max(p1.y, p2.y) < min(p3.y, p4.y)) {
            System.out.println(0);
            return;
        }

        // ccw를 이용한 선분 교차 판단
        System.out.println(
                (ccw(p1, p2, p3) * ccw(p1, p2, p4) <= 0 && ccw(p3, p4, p1) * ccw(p3, p4, p2) <= 0) ?
                1 : 0);

    }

    // 값이 커질 수 있어 int res 사용 불가.
    private static double ccw(Point p1, Point p2, Point p3) {
        double res = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y -
                p2.x * p1.y - p3.x * p2.y - p1.x * p3.y;
        return signum(res); // sign of result
    }

    static class Point {
        long x;
        long y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}
