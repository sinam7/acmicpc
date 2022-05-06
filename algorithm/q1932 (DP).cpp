// q1932 - 정수 삼각형
#include <iostream>
#include <string>

#define MAX_SIZE 500
#define MAX(a, b) (a > b ? a : b)

using namespace std;


int n;
int table[MAX_SIZE][MAX_SIZE] = {};
bool visited[MAX_SIZE][MAX_SIZE] = {};
int memo[MAX_SIZE][MAX_SIZE] = {};

bool isValid(int i, int j) {
    return 0 <= i && i < n && 0 <= j && j < n;
}

int dp(int i, int j) {
    if (!isValid(i, j)) return 0;
    if (visited[i][j]) return memo[i][j];

    visited[i][j] = true;
    memo[i][j] = table[i][j] + MAX(dp(i + 1, j), dp(i + 1, j + 1));

    return memo[i][j];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // input
    cin >> n;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j <= i; ++j) {
            cin >> table[i][j];
        }
    }

    // logic - dp
    cout << dp(0, 0);

    // output

}


