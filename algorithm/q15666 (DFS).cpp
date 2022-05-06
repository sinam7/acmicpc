// q15666 - N과 M (12)
#include <iostream>
#include <string>
#include <set>

using namespace std;

int n, m;
int last = 0;
int arr[8] = {};
int raw[8] = {};

void removeDuplicates(int* array) {
    set<int> s;
    for (int i = 0; i < n; ++i) {
        s.insert(arr[i]);
    }

    for (int i = 0; i < n; ++i) array[i] = 0;
    int j = 0;
    for (int i : s) {
        array[j] = i;
        ++j;
    }
    n = s.size();
}

void dfs(int size) {

    // output
    if (size == m) {
        for (int i = 0; i < m; ++i) cout << raw[i] << " ";
        cout << "\n";
        return;
    }

    for (int i = 0; i < n; ++i) {
        if (last <= arr[i]) { // 단조 증가 구현
            raw[size] = arr[i];

            last = raw[size];
            dfs(size + 1);
            last = (size > 0 ? raw[size - 1] : 0);
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
    removeDuplicates(arr);

    // logic
    dfs(0);

    // output : logic에서 처리

}


