#include <bits/stdc++.h>

using namespace std;

int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    deque<int> DQ;
    int n, m, num, result=0;

    cin >> n >> m;
    for (int i = 1; i <= n; i++) {
        DQ.push_back(i);
    }

    
    while (m--) {
        cin >> num;
        if (num != DQ.front()) {
            int idx = find(DQ.begin(), DQ.end(), num)-DQ.begin();
            if (idx+1 <= (DQ.size() + 1) / 2) {
                while (DQ.front() != num) {
                    DQ.push_back(DQ.front());
                    DQ.pop_front();
                    result++;
                }
            }
            else {
                while (DQ.front() != num) {
                    DQ.push_front(DQ.back());
                    DQ.pop_back();
                    result++;
                }
            }
        }
        
        DQ.pop_front();
    }
    
    cout << result;
   
    return 0;
}