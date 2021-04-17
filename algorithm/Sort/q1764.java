// https://www.acmicpc.net/problem/1764
/*
    TLE를 피하는 것이 요점.
    빠른 검색을 위해 Collections.binarySearch()를 활용.
    이를 위해선 사전 정렬이 필요해 Collections.sort()를 사용.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, m;
        n = sc.nextInt(); m = sc.nextInt();

        // 듣도 못한 사람들을 저장, 정렬
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < n; i++) names.add(sc.next());

        Collections.sort(names);

        int ans = 0;

        // 보도 못한 사람들을 입력받아 이진탐색, names에 있으면 aname에 추가.
        ArrayList<String> aname = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String name = sc.next();
            try {
                String target = names.get(Collections.binarySearch(names, name));
                if (target.equals(name)) {
                    ans++;
                    aname.add(name);
                }
            } catch (IndexOutOfBoundsException ignored) {
            }

        }

        // 듣보잡 목록을 정렬, StringBuilder 로 빠른 out 작성
        Collections.sort(aname);
        StringBuilder sb = new StringBuilder();
        sb.append(ans).append("\n");
        for (String e : aname) sb.append(e).append("\n");

        System.out.println(sb);

    }

}