// https://www.acmicpc.net/problem/2108
/*
    q2108 - 통계학
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Solve solve = new Solve();
        solve.run();

    }
}

class Solve {

    BufferedReader br;

    int N;
    int sum = 0;

    public void run() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int e = Integer.parseInt(br.readLine());
            pq.offer(e);
            sum += e;
            if (!freq.containsKey(e)) freq.put(e, 1);
            else freq.put(e, freq.get(e) + 1);
        }

        System.out.println(Math.round(sum / (double) pq.size()));
        int[] sortedArray = new int[pq.size()];

        int size = pq.size();
        for (int i = 0; i < size; i++) {
            sortedArray[i] = pq.poll();
        }

        System.out.println(sortedArray[size / 2]);

        PriorityQueue<Integer> maxOccurrences = new PriorityQueue<>();
        int max = -1;
        for (Integer key : freq.keySet()) {
            Integer occurrence = freq.get(key);
            if (occurrence > max) {
                max = occurrence;
                maxOccurrences.clear();
                maxOccurrences.add(key);
            } else if (occurrence == max) {
                maxOccurrences.add(key);
            }
        }

        int mode = maxOccurrences.poll();
        if (!maxOccurrences.isEmpty()) mode = maxOccurrences.poll();
        System.out.println(mode);

        System.out.println(sortedArray[0] - sortedArray[size - 1]);

    }


}