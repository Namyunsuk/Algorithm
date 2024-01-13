#include <bits/stdc++.h>

using namespace std;

void parse(deque<int>& DQ, string& numbers) {
    int num = 0;
    for (int i = 1; i < numbers.size()-1; i++) {
        if (numbers[i] == ',') {
            DQ.push_back(num);
            num = 0;
        }
        else {
            num = num * 10 + (numbers[i] - '0');
        }
    }
    if (num != 0) {
        DQ.push_back(num);
    }
}

void printResult(deque<int>& DQ, int rev) {
    cout << "[";
    if (rev) {
        for (int i = DQ.size()-1; i >=0; i--) {
            if (i == 0) {
                cout << DQ[i];
            }
            else cout << DQ[i] << ",";
        }
    }
    else {
        for (int i = 0; i < DQ.size(); i++) {
            if (i == DQ.size() - 1) {
                cout << DQ[i];
            }
            else cout << DQ[i] << ",";
        }
    }
    cout << "]\n";
}

int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    deque<int> DQ;
    int t, n, rev, err;
    string p, numbers;

    cin >> t;
    while (t--) {
        rev = 0;
        err = 0;
        cin >> p;
        cin >> n;
        cin >> numbers;
        parse(DQ, numbers);
        
        for (int i = 0; i < p.size(); i++) {
            if (p[i] == 'R') {
                if (rev == 0) rev = 1;
                else rev = 0;
            }
            else {
                if (DQ.empty()) {
                    cout << "error\n";
                    err = 1;
                    break;
                }
                if (rev) DQ.pop_back();
                else DQ.pop_front();
            }
        }
        if(!err)
            printResult(DQ, rev);
        DQ.clear();
    }
   
    return 0;
}