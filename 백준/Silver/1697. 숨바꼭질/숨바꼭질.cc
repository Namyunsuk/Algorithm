#include <bits/stdc++.h>

#define x first
#define y second

using namespace std;

int arr[100001];


int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);


    queue<int> Q;
    int n, k;
    fill(arr, arr + 100001, -1);

    cin >> n >> k;
    arr[n] = 0;
    Q.push(n);

    while (arr[k]==-1) {
        int cur = Q.front();
        Q.pop();
        for (int nx:{cur-1,cur+1,cur*2}) {
            if (nx < 0 || nx > 100000) continue;
            if (arr[nx] != -1) continue;
            arr[nx] = arr[cur] + 1;
            Q.push(nx);
        }
    }
   
    cout << arr[k];
   
   
    return 0;
}