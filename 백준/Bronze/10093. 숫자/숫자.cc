#include <bits/stdc++.h>

using namespace std;

int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    int a, b;
    cin >> a >> b;
    if (a > b) swap(a, b);
    if (a == b || a == b + 1) cout << 0;
    else {
        cout << b - a - 1 << "\n";
        for (int i = a + 1; i < b; i++) {
            cout << i << ' ';
        }
    }

   
   
    return 0;
}