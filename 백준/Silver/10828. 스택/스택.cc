#include <bits/stdc++.h>

using namespace std;


int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    stack<int> S;
    int n, val;
    string op;
    cin >> n;
    
    for (int i = 0; i < n; i++) {
        cin >> op;

        if (op == "push") {
            cin >> val;
            S.push(val);
        }
        else if (op == "pop") {
            if (S.empty()) cout << "-1" << "\n";
            else {
                val = S.top();
                cout << val << "\n";
                S.pop();
            }
        }
        else if (op == "size") {
            cout << S.size() <<"\n";
        }
        else if (op == "empty") {
            if (S.empty()) cout << "1" << "\n";
            else cout << "0" << "\n";
        }
        else if (op == "top") {
            if (S.empty()) cout << "-1" << "\n";
            else {
                val = S.top();
                cout << val << "\n";
            }
        }
    }
   
    return 0;
}