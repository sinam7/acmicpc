// https://www.acmicpc.net/problem/18870
/*
    n개의 좌표를 입력받아 Array 에 저장 및 정렬,
    정렬된 순서를 바탕으로 HashMap 에 매핑해 출력
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) arr.add(sc.nextInt());
        sc.close();

        ArrayList<Integer> sorted = (ArrayList<Integer>) arr.clone();
        Collections.sort(sorted);

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; i < N; i++) {
            if (map.containsKey(sorted.get(i))) continue;
            map.put(sorted.get(i), j);
            j += 1;
        }

        StringBuilder sb = new StringBuilder();
        for (Integer integer : arr) {
            sb.append(map.get(integer)).append(" ");
        }
        System.out.println(sb);

    }

}