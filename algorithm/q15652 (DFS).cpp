/*
 * https://www.acmicpc.net/problem/15652
 * dfs - 백트래킹 문제
 */
#include <iostream>
#include <string>

using namespace std;

void dfs(int, int, int, string);

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m;
    cin >> n >> m;
    dfs(1, n, m, "");
}

void dfs(int start, int end, int length, string str) {
    if (start > end) return;
    if (length == 0) { cout << str << "\n"; return; }
    while (start <= end) {
        string cpy(str);
        cpy.append(to_string(start)).append(" ");
        dfs(start, end, length - 1, cpy);
        start += 1;
    }
}

