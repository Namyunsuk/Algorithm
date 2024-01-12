#include <bits/stdc++.h>

using namespace std;



int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    queue<int> Q;
    int n, num;
    string op;

    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> op;

        if (op == "push") {
            cin >> num;
            Q.push(num);
        }
        else if (op == "pop") {
            if (!Q.empty()) {
                num = Q.front();
                cout << num<<"\n";
                Q.pop();
            }
            else cout << -1 << "\n";
        }
        else if (op == "size") {
            cout << Q.size() << "\n";
            
        }
        else if (op == "empty") {
            if (Q.empty()) {
                cout << 1<<"\n";
            }
            else cout << 0<<"\n";
        }
        else if (op == "front") {
            if (!Q.empty()) {
                num = Q.front();
                cout << num << "\n";
            }
            else cout << -1 << "\n";
        }
        else if (op == "back") {
            if (!Q.empty()) {
                num = Q.back();
                cout << num << "\n";
            }
            else cout << -1 << "\n";
        }
    }

    
       
   
    return 0;
}