#include <bits/stdc++.h>

using namespace std;

int arr[2000001];

int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    int n, x, tmp, count=0;
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> tmp;
        arr[tmp]++;
    }
    cin >> x;

    for (int i = 0; i < (x+1)/2; i++) {
        if (arr[i] == 1 && arr[x - i] == 1) count++;
    }
   
    cout<<count;
   
    return 0;
}