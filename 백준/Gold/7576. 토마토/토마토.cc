#include <bits/stdc++.h>

#define x first
#define y second

using namespace std;

int arr[1000][1000];
int dx[4] = { 1,0,-1,0 };
int dy[4] = { 0,1,0,-1 };


int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    queue<pair<int, int>> Q;
    int n, m;
    cin >> m >> n;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> arr[i][j];
            if (arr[i][j] == 1) Q.push({ i,j });
        }
    }

    while (!Q.empty()) {
        auto cur = Q.front();
        Q.pop();
        for (int i = 0; i < 4; i++) {
            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if (arr[nx][ny] != 0) continue;
            arr[nx][ny] = arr[cur.x][cur.y] + 1;
            Q.push({ nx,ny });
        }
    }

    int mx = -1;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (arr[i][j] == 0) {
                cout << -1;
                return 0;
            }
            mx = max(mx, arr[i][j]);
        }
    }
    
    if (mx == 1) cout << 0;
    else cout << mx-1;


   
   
    return 0;
}