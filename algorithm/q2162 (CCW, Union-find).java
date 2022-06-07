// https://www.acmicpc.net/problem/2162
/*
    q2162 - 선분 그룹
    CCW, Disjoint set
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    Line[] l;
    int[] parents;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        l = new Line[N + 1];
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) parents[i] = i;

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            l[i] = new Line(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())),
                    new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for (int i = 1; i <= N - 1; i++) {
            for (int j = i + 1; j <= N; j++) {
                int chk1 = ccw(l[i].a, l[i].b, l[j].a) * ccw(l[i].a, l[i].b, l[j].b);
                int chk2 = ccw(l[j].a, l[j].b, l[i].a) * ccw(l[j].a, l[j].b, l[i].b);
                if (chk1 < 0 && chk2 < 0) {
                    union(i, j);
                } else if ((chk1 == 0 && chk2 < 0) || (chk1 < 0 && chk2 == 0)) {
                    union(i, j);
                } else if (chk1 == 0 && chk2 == 0) {
                    Point p1 = (l[i].a.compareTo(l[i].b) < 0) ? l[i].a : l[i].b;
                    Point p2 = (l[i].a.compareTo(l[i].b) < 0) ? l[i].b : l[i].a;
                    Point p3 = (l[j].a.compareTo(l[j].b) < 0) ? l[j].a : l[j].b;
                    Point p4 = (l[j].a.compareTo(l[j].b) < 0) ? l[j].b : l[j].a;

                    if (p3.compareTo(p2) <= 0 && p1.compareTo(p4) <= 0)
                        union(i, j);
                }
            }
        }

        int max = 0, cnt = 0;
        int[] occur = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = find(parents[i]);
        }

        for (int i = 1; i <= N; i++) {
            occur[parents[i]]++;
            if (occur[parents[i]] == 1) cnt++;
            max = Math.max(max, occur[parents[i]]);
        }

        System.out.println(cnt + "\n" + max);


    }

    private void union(int i, int j) {
        int pi = find(i);
        int pj = find(j);

        int a = Math.min(pi, pj);
        int b = Math.max(pi, pj);

        if (a != b) parents[b] = a;
    }

    private int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    private int ccw(Point a, Point b, Point c) {
        //vec a->b X vec a->c ( xy - xy )
        int ans = (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y);

        // 반시계: 양수 , 시계: 음수, 세 점이 동일선상: 0
        return Integer.compare(ans, 0);
    }

    private static class Point implements Comparable<Point> {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Solve.Point o) {
            return this.x != o.x ? this.x - o.x : this.y - o.y;
        }
    }

    private static class Line {

        Point a;
        Point b;

        public Line(Point a, Point b) {
            this.a = a;
            this.b = b;
        }
    }
}