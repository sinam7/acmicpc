// https://www.acmicpc.net/problem/2630
/*
    분할정복 알고리즘으로 해결하는 문제.
    그러나 종이를 자를 필요가 없는 경우도 생각해야 한다.
 */
import java.util.Scanner;

class Main {

    static int[] ans = new int[2]; // White, Blue
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                arr[i][j] = sc.nextInt();

        sc.close();

        int chk = check(0, 0, N, arr); // 모두 같은, 즉 색종이 한장인 경우 검증
        if (chk != -1) { // 색종이 한장이면 바로 처리
            System.out.println(chk == 0 ? "1\n0" : "0\n1");
        }
        else { // 아니면 재귀+분할정복으로 자르기
            run(0,0, N, arr);
            for (int i : ans)
                System.out.println(i);
        }

    }

    private static void run(int xcrit, int ycrit, int len, int[][] arr) {
        for (int v = ycrit; v < ycrit + len; v += len/2) {
            for (int h = xcrit; h < xcrit + len; h += len/2)  {
                int status = check(v, h, len/2, arr);
                if (status == -1)
                    run(h, v, len/2, arr);
                else
                    ans[status] += 1;
            }
        }
    }

    private static int check(int xcrit, int ycrit, int half, int[][] arr) {  // 왼쪽 위에서 len만큼 그 칸을 체크
        int val = arr[ycrit][xcrit];

        for (int i = ycrit; i < ycrit + half; i++) {
            for (int j = xcrit; j < xcrit + half; j++)
                if (arr[i][j] != val)
                    return -1;
        }
        return val;
    }

}