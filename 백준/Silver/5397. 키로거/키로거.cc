#include <bits/stdc++.h>

using namespace std;


int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    int n;
    string s;
    list<char> L;
    auto t = L.begin();

    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> s;
        for (char c : s) {
            if (c == '<') {
                if (t != L.begin())t--;
            }
            else if (c == '>') {
                if (t != L.end())t++;
            }
            else if (c == '-') {
                if (t != L.begin()) {
                    t--;
                    t = L.erase(t);
                }
            }
            else {
                L.insert(t, c);
            }
        }
        for (auto val : L) cout << val;
        cout << "\n";
        L.clear();
        t = L.begin();
    }

    

   
    return 0;
}