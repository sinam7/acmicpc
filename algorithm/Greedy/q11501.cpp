// https://www.acmicpc.net/problem/11501
/*
 그리디로 풀 수 있으나, O(n)에 해결하는 방식을 찾아야한다.
 추가로, 데이터의 크기에 유의해 자료형을 선택하자.
 */

#include <iostream>

using namespace std;

int main() {
    int T;
    cin >> T;

    for (int tc = 0; tc < T; tc++) {
        int n, max = 0;
        long long profit = 0;
        cin >> n;
        int *t = new int[n];

        for (int i = 0; i < n; ++i) {
            cin >> t[i];
        }

        // 맨 뒤부터 최댓값을 잡고, 다음 최댓값이 나오기 전까지 매일 주식을 구매해 최댓값에 판 것으로 간주.
        for (int i = n-1; i >= 0; i--) {
            if (max < t[i]) {
                max = t[i];
            } else {
                profit += max - t[i];
            }
        }
        cout << profit << endl;
    }

}