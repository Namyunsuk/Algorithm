#include <bits/stdc++.h>

using namespace std;

int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    int arr[26];
    fill(arr, arr + 26, 0);

    string s;
    cin >> s;
    
    for (auto c : s) {
        arr[c - 'a'] += 1;
    }

    for (int i = 0; i < 26;i++) {
        cout << arr[i] << ' ';
    }

   
   
    return 0;
}