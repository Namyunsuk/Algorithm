#include <bits/stdc++.h>

using namespace std;


int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    int n, k;
    list<int> L;

    cin >> n >> k;
    for (int i = 1; i <= n; i++) {
        L.push_back(i);
    }
    auto p = L.begin();

    cout << "<";

    while (L.size() != 0) {
        for (int i = 0; i < k - 1; i++) {
            if (p == L.end()) {
                p = L.begin();
                p++;
            }
            else p++;
        }
        if (p == L.end()) p = L.begin();
        if (L.size() == 1) {
            cout << *p;
            p = L.erase(p);
        }
        else {
            cout << *p << ", ";
            p = L.erase(p);
        }
    }

    cout << ">";
   
    return 0;
}