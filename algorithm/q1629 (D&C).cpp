// q1629 - 곱셈
#include <iostream>
#include <string>

using namespace std;

int a, b, c;

long cutoff(int ia, int ib) {
    if (ib == 0) return 1;

    // a^b = (a^(b/2))^2
    long tmp = cutoff(ia, ib / 2) % c;
    tmp *= tmp; // int 사용 시 여기서 overflow 발생 가능.
    tmp %= c;
    return ib % 2 == 0 ? tmp : (tmp * a) % c;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // input
    cin >> a >> b >> c;

    // logic
    cout << cutoff(a, b);

    // output

}


