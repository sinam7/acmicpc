// https://www.acmicpc.net/problem/1620
/*
    q1620 - 나는야 포켓몬 마스터 이다솜
    HashMap
 */
import java.util.HashMap;
import java.util.Scanner;

class Main {

    private void solve() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), m = sc.nextInt();
        HashMap<Integer, String> nameOfIndexIn = new HashMap<>();
        HashMap<String, Integer> indexOfNameIn = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            String name = sc.next();
            nameOfIndexIn.put(i, name);
            indexOfNameIn.put(name, i);
        }

        for (int i = 0; i < m; i++) {
            if (sc.hasNextInt()) {
                Integer integer = sc.nextInt();
                System.out.println(nameOfIndexIn.get(integer));
            } else {
                String input = sc.next();
                System.out.println(indexOfNameIn.get(input));
            }
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}