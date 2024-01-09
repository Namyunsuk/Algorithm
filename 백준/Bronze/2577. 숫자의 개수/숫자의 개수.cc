#include <bits/stdc++.h>

using namespace std;

int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    int a, b, c, n,tmp;
    int freq[10];
    fill(freq, freq + 10, 0);
    cin >> a;
    cin >> b;
    cin >> c;

    n = a * b * c;
    while (n > 0) {
        tmp = n % 10;
        freq[tmp]++;
        n /= 10;
    }

    for (int i = 0; i < 10; i++) {
        cout << freq[i] << "\n";
    }
   
   
    return 0;
}