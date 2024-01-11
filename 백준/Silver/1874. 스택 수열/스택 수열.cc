#include <bits/stdc++.h>

using namespace std;


int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    stack<int> S;
    vector<char> Ans;
    int n, num;
    vector<int> V;

    cin >> n;

    for (int i = 0; i < n; i++) {
        cin >> num;
        V.push_back(num);
    }
    auto p = V.begin();


    for (int i = 1; i <= n; i++) {        
        S.push(i);
        Ans.push_back('+');

        
        while (*p == S.top()) {
            Ans.push_back('-');
            S.pop();
            p++;
            if (S.empty()) break;
        }
        if (!S.empty()&&S.top() > *p) {
            cout << "NO";
            return 0;
        }
    }

    for (auto c : Ans) {
        cout << c << "\n";
    }
   
    return 0;
}