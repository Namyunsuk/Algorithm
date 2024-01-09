#include <bits/stdc++.h>

using namespace std;

int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    int n,tmp;
    int num[10];
    fill(num, num + 10, 0);
    cin >> n;

    while (n > 0) {
        tmp = n % 10;
        if (tmp == 6 || tmp == 9) {
            if (num[6] <= num[9])num[6]++;
            else num[9]++;
        }
        else num[tmp]++;
        n /= 10;
    }

    cout << *max_element(num, num + 10);
   
   
    return 0;
}