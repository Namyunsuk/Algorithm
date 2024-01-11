#include <bits/stdc++.h>

using namespace std;


int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    stack<int> S;
    int k,num, result=0;

    cin >> k;
    for (int i = 0; i < k; i++) {
        cin >> num;
        if (num == 0) {
            S.pop();
        }
        else {
            S.push(num);
        }
    }

    while (!S.empty()) {
        result += S.top();
        S.pop();
    }

    cout << result;
   
    return 0;
}