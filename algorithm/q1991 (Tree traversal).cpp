// q1991 - 트리 순회
#include <iostream>
#include <string>

using namespace std;

int n;
int tree[26][2]; // index: name, [][0]: lchild, [][1]: rchild

void preorder(int i);
void inorder(int i);
void postorder(int i);
bool isleaf(int i);

int lchild(int);
int rchild(int);
void visit(int);

int main() {
//    ios::sync_with_stdio(false);
//    cin.tie(NULL);
//    cout.tie(NULL);

    // input
    scanf("%d", &n);
    for (int i = 0; i < n; ++i) {
        char ta, tb, tc;
        cin >> ta >> tb >> tc;
        int idx = ta - 'A';
        tree[idx][0] = (tb != '.' ? tb - 'A' : -1);
        tree[idx][1] = (tc != '.' ? tc - 'A' : -1);
    }
    // logic
    preorder(0);
    printf("\n");
    inorder(0);
    printf("\n");
    postorder(0);

    // output

}

void preorder(int i) { // 전위 순회
    if (!isleaf(i)) {
        visit(i);
        preorder(lchild(i));
        preorder(rchild(i));
    }
}

void inorder(int i) {
    if (!isleaf(i)) {
        inorder(lchild(i));
        visit(i);
        inorder(rchild(i));
    }
}

void postorder(int i) {
    if (!isleaf(i)) {
        postorder(lchild(i));
        postorder(rchild(i));
        visit(i);
    }
}

bool isleaf(int i) {
    return i == -1;
}

int lchild(int i){
    return tree[i][0];
}
int rchild(int i){
    return tree[i][1];
}

void visit(int i) {
    printf("%c", i + 'A');
}
