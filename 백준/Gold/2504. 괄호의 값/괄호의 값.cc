#include <bits/stdc++.h>

using namespace std;


int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    stack<char> S;
    string seq;
    int val=1,result = 0;


    cin >> seq;
    for (int i = 0; i < seq.size(); i++) {
        if (seq[i] == '(') {
            val *= 2;
            S.push(seq[i]);
        }
        else if (seq[i] == '[') {
            val *= 3;
            S.push(seq[i]);
        }
        else if (seq[i] == ')') {
            if (S.empty() || S.top() != '(') {
                cout << 0;
                return 0;
            }
            if (seq[i - 1] == '(') result += val;
            val /= 2;
            S.pop();
        }
        else if (seq[i] == ']') {
            if (S.empty() || S.top() != '[') {
                cout << 0;
                return 0;
            }
            if ( seq[i - 1] == '[') result += val;
            val /= 3;
            S.pop();
        }
    }
    if (!S.empty())cout << 0;
    else cout << result;
    
   
    return 0;
}