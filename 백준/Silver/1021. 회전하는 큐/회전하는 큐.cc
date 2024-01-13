#include <bits/stdc++.h>

using namespace std;



int findIndex(int num);
void func2();
void func3();

deque<int> DQ;

int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    vector<int> numbers;
    int n, m, num, result=0;

    cin >> n >> m;
    for (int i = 1; i <= n; i++) {
        DQ.push_back(i);
    }

    for (int i = 0; i < m; i++) {
        cin >> num;
        numbers.push_back(num);
    }
    auto p = numbers.begin();

    while (p != numbers.end()) {
        if (*p != DQ.front()) {
            int idx = findIndex(*p);
            if (idx <= (DQ.size() + 1) / 2) {
                while (DQ.front() != *p) {
                    func2();
                    result++;
                }
            }
            else {
                while (DQ.front() != *p) {
                    func3();
                    result++;
                }
            }
        }
        
        DQ.pop_front();
        p++;
    }
    
    cout << result;
   
    return 0;
}

void func2() {
    int num = DQ.front();
    DQ.pop_front();
    DQ.push_back(num);
}

void func3() {
    int num = DQ.back();
    DQ.pop_back();
    DQ.push_front(num);
}

int findIndex(int num) {
    for (int i = 0; i < DQ.size(); i++) {
        if (DQ[i] == num) return i + 1;
    }
}