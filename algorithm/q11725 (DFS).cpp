// https://www.acmicpc.net/problem/11725
#include <iostream>
#include <stdlib.h>
#include <vector>
#include <string>

#define MAX 100001

using namespace std;

bool visited[MAX];      // 중복 탐색 방지 (parent 꼬임 방지)
int parent[MAX];        // parent 정보 저장
vector<int> tree[MAX];  // 연결된 노드 정보

void dfs(int current) {

    visited[current] = true;

    // 연결된 노드 전체에 대해 탐색
    for (int i = 0; i < tree[current].size(); ++i) {
        int next = tree[current][i];
        // 미방문 노드에 대해
        if (!visited[next]) {
            // 현재 노드를 다음(tree 벡터 내의)노드의 부모로 설정.
            // 1번 노드(Root)부터 탐색하므로 가능한 방식.
            parent[next] = current;

            // 재귀 시행
            dfs(next);
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n;
    cin >> n;

    for (int i = 0; i < n - 1; ++i) {
        int a, b;
        cin >> a >> b;

        // 입력 당시에는 누가 부모 노드인지 알 수 없으므로 양방향으로 모두 등록, visited를 통해 dfs에서 중복 오류 방지함.
        tree[a].push_back(b);
        tree[b].push_back(a);
    }

    // dfs 시행
    dfs(1);

    for (int i = 2; i <= n; ++i) cout << parent[i] << '\n';
    
}


