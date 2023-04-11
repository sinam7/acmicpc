// https://www.acmicpc.net/problem/1966

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    private void solve() {
        int TC = sc.nextInt();
        while (TC-- > 0) {
            run();
        }
    }

    private static void run() {
        int N = sc.nextInt(), M = sc.nextInt();
        Document target = null;

        ArrayList<Document> arr = new ArrayList<>(N); // 모든 문서는 여기서 관리한다.
        PriorityQueue<Integer> priority = new PriorityQueue<>(Comparator.reverseOrder()); // 중요도 순으로 정렬
        for (int i = 0; i < N; i++) { // 입력
            Integer e = sc.nextInt();
            Document doc = new Document(e);
            if (i == M) target = doc; // 궁금한 문서 지정
            arr.add(doc);
            priority.add(e);
        }

        Integer next = priority.poll(); // 다음으로 뽑아야 할 중요도 문서

        // circular iteration 구현
        int pointer = 0;
        int ans = 0;
        while (true) {
            for (; pointer < arr.size(); pointer++) {
                if (arr.get(pointer).priority == next) { // 탐색 중인 문서의 중요도가 다음 뽑아야 할 문서면 출력. NPE Warning; next는 없을 수 없기 때문에 무시
                    ans++;
                    Document print = arr.remove(pointer);
                    if (print == target) { // 출력한 문서가 궁금한 문서일 경우 완료
                        System.out.println(ans);
                        return;
                    }
                    pointer--; // 출력한 문서가 궁금한 문서가 아니면, pointer를 1 감소해야 함 (element가 하나 줄었으니)
                    next = priority.poll(); // 다음 출력할 문서 선택
                }
            }
            pointer = 0;
        }
    }

    public static void main(String[] args) {
        sc.init();

        new Main().solve();
    }

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

// Line 38의 객체 직접 비교를 위해 만든 클래스. Integer(Wrapper) 클래스로는 객체 직접 비교가 안 될 줄이야.
class Document {
    int priority;

    public Document(Integer e) {
        priority = e;
    }
}