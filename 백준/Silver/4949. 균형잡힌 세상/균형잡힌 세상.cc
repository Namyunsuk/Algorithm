#include <bits/stdc++.h>

using namespace std;

int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    string sen;

    while (1) {
        stack<char> S;
        bool valid = true;

        getline(cin, sen);
        if (sen == ".") {
            break;
        }

        for (int i = 0; i < sen.size(); i++) {
            if (sen[i] == '(' || sen[i] == '[') {
                S.push(sen[i]);
            }
            else if (sen[i] == ')') {
                if (S.empty() || S.top() != '(') {
                    valid = false;
                    break;
                }
                S.pop();
            }
            else if (sen[i] == ']') {
                if (S.empty() || S.top() != '['){
                    valid = false;
                    break;
                }
                S.pop();
            }
        }

        
        if (!S.empty()) valid = false;

        if (valid) cout << "yes\n";
        else cout << "no\n";
    }
   
    return 0;
}