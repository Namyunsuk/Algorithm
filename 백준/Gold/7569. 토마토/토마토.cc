#include <bits/stdc++.h>

#define x first
#define y second
#define y third

using namespace std;

int arr[100][100][100];
int dist[100][100][100];

int dx[6] = { 1,-1,0,0,0,0 };
int dy[6] = { 0,0,1,-1,0,0 };
int dz[6] = { 0,0,0,0,1,-1 };

int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int m, n, h;
    queue<tuple<int, int, int>> Q;
    cin >> m >> n >> h;

    for (int i = 0; i < h; i++) {
        for (int j = 0; j < n; j++) {
            fill(dist[i][j], dist[i][j] + m, -1);
        }
    }

    for (int i = 0; i < h; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < m; k++) {
                cin >> arr[i][j][k];
                if (arr[i][j][k] == 1) {
                    dist[i][j][k] = 0;
                    Q.push({ i,j,k });
                }
            }
        }
    }

    while (!Q.empty()) {
        int curx, cury, curz;
        auto cur = Q.front();
        Q.pop();
        tie(curz, curx, cury) = cur;
        for (int i = 0; i < 6; i++) {
            int nx = curx + dx[i];
            int ny = cury + dy[i];
            int nz = curz + dz[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || nz < 0 || nz >= h)continue;
            if (dist[nz][nx][ny] >= 0 || arr[nz][nx][ny] == -1)continue;
            dist[nz][nx][ny] = dist[curz][curx][cury] + 1;
            Q.push({ nz,nx,ny });
        }
    }

    int mx = 0;
    for (int i = 0; i < h; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < m; k++) {
                if (arr[i][j][k]!=-1 && dist[i][j][k] == -1) {
                    cout << -1;
                    return 0;
                }
                mx = max(mx, dist[i][j][k]);
            }
        }
    }
    
    cout << mx;

    return 0;
}