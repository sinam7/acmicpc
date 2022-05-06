// https://www.acmicpc.net/problem/2263
/*
    q2263 - 트리의 순회
    재귀

    각 Tree Traversal의 특징을 알아야 풀 수 있다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();

        solve.run();    // comment while running test.java

    }

}

class Solve {

    BufferedReader br;
    StringBuilder sb;

    int n;

    int[] inorder;
    int[] postorder;
    int[] inorderIdx;

    public void run() throws IOException {

        // input
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        inorderIdx = new int[n + 1];

        inorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        postorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < n; i++) inorderIdx[inorder[i]] = i;


        getPreorder(0, n, 0, n);

        System.out.println(sb);


    }

    private void getPreorder(int in_start, int in_end, int po_start, int po_end) {
        if (in_start >= in_end || po_start >= po_end) return;

        int root = postorder[po_end - 1]; // 한 부분트리의 postorder에서 가장 끝 노드가 루트다.
        int rootIdx = inorderIdx[root]; // 한 부분트리의 inorder에서 루트 노드를 기준으로 좌우 자손 노드가 갈린다.

        sb.append(root).append(" ");

        // left child가 없을 때 인덱스 오류를 방지하며, 그대로 실행해도 메서드 시작 조건문에서 걸러지게 해 StackOverflow 방지
        int leftElements = Math.max(rootIdx - in_start, 0); // 남은 좌측 자손 수를 바탕으로 재귀 실행

        getPreorder(in_start, rootIdx, po_start, po_start + leftElements); // 좌측 하위 트리
        getPreorder(rootIdx + 1, in_end, po_start + leftElements, po_end - 1); // 우측 하위 트리
    }

}
