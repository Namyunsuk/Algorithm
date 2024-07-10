#include <bits/stdc++.h>

using namespace std;

int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    stack<char> S;
    string seq;
    int result = 0;

    cin >> seq;

    for (int i = 0; i < seq.size();i++) {
        if (seq[i] == '(')S.push(seq[i]);
        else {
            if (seq[i - 1] == '(') {
                S.pop();
                result += S.size();
            }
            else {
                S.pop();
                result++;
            }
        }
    }

    cout << result;

   
    return 0;
}