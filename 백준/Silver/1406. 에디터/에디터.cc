#include <bits/stdc++.h>

using namespace std;


int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    list<char> L;
    string s;
    int m;
    char op, val;

    cin >> s;
    for (char c : s) {
        L.push_back(c);
    }
    auto t = L.end();
    cin >> m;

    for (int i = 0; i < m; i++) {
        cin >> op;
        
        if (op == 'L') {
            if (t != L.begin()) {
                t--;
            }
        }
        else if (op == 'D') {
            if (t != L.end()) {
                t++;
            }
        }
        else if (op == 'B') {
            if (t != L.begin()) {
                t--;
                t = L.erase(t);
            }
        }
        else if(op == 'P') {
            cin >> val;
            L.insert(t, val);
        }
    }
    

    for (auto c : L) {
        cout << c;
    }

    

   
    return 0;
}