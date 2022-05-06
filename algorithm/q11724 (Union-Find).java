// https://www.acmicpc.net/problem/11724
/*
    Connected Component
    Union-Find, Path compression
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    static ArrayList<Integer> arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(), M = sc.nextInt();

        arr = new ArrayList<>(N + 1);
        arr.add(0, 0);                                 // 1 ~ N 번까지 활용
        for (int i = 1; i <= N; i++) arr.add(i, null); // 값이 null -> 자신이 root

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            union(u, v);
        }

        int ans = 0;
        for (int i = 1; i <= N; i++)
            if (arr.get(i) == null) ans++;

        System.out.println(ans);

    }
    
    static void union(int i, int j) {
        Integer root1 = find(i);
        Integer root2 = find(j);
        if (!root1.equals(root2)) arr.set(root2, root1);
    }

    static Integer find(int curr) {
        if (arr.get(curr) == null) return curr;
        arr.set(curr, find(arr.get(curr))); // Path compression
        return arr.get(curr);
    }

}