#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int n, m;
bool visited[8];
int arr[8] = {};
int raw[8] = {};


void dfs(int size) {

    // output
    if (size == m) {
        for (int i = 0; i < m; ++i) cout << raw[i] << " ";
        cout << "\n";
        return;
    }

    // 마지막에 들어온 수를 별도로 관리하지 않으면 이전 수열 정보가 남아 문제가 됨.
    int last = 0;
    // 문제가 단조 증가 수열이 아니므로 재귀에서도 처음부터 시작함.
    for (int i = 0; i < n; ++i) {
        // 입력 데이터 배열 정렬 후, 같은 숫자 입력이 연속으로 들어오면 중복된 수열임.
        if (!visited[i] && arr[i] != last) {
            raw[size] = arr[i];
            last = raw[size];

            visited[i] = true;  // 방문 처리는 값을 실제로 넣은 시점에 시행
            dfs(size + 1);
            visited[i] = false; // 수열을 완성한 이후 다른 수로 시작하는 수열도 만들기 위해 방문 취소 처리
        }
    }

}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // input
    cin >> n >> m;

    for (int i = 0; i < n; ++i) cin >> arr[i];
    sort(arr, arr + n);

    // logic
    dfs(0);

    // output : logic에서 처리

}


