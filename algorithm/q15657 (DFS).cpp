#include <iostream>
#include <stdlib.h>
#include <string>

using namespace std;

void dfs(int, int, int, string);
int arr[10000] = {};

int compare(const void *a, const void *b) {
    return *(int *) a - *(int *) b;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m;
    cin >> n >> m;

    for (int i = 0; i < n; ++i) {
        cin >> arr[i];
    }
    qsort(arr, n, sizeof(int), compare);
    dfs(1, n, m, "");
}

void dfs(int start, int end, int length, string str) {
    if (start > end) return;
    if (length == 0) { cout << str << "\n"; return; }
    while (start <= end) {
        string cpy(str);
        cpy.append(to_string(arr[start - 1])).append(" ");
        dfs(start, end, length - 1, cpy);
        start += 1;
    }
}

