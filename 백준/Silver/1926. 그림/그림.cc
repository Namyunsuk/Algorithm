#include <bits/stdc++.h>

#define X first
#define Y second

using namespace std;

int arr[500][500];
int isvisit[500][500];
int dx[4] = { 0,1,0,-1 };
int dy[4] = { 1,0,-1,0 };


int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int n, m, val, cnt=0, mx=0;
    queue<pair<int, int>> Q;

    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> val;
            arr[i][j] = val;
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (arr[i][j] != 1 || isvisit[i][j] == 1) continue;
            int  area = 0;
            isvisit[i][j] = 1;
            Q.push({ i,j });
            cnt += 1;
            while (!Q.empty()) {
                area++;
                pair<int, int> cur = Q.front();
                Q.pop();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.X + dx[dir];
                    int ny= cur.Y + dy[dir];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    if (arr[nx][ny] != 1 || isvisit[nx][ny] == 1) continue;
                    isvisit[nx][ny] = 1;
                    Q.push({ nx,ny });
                    
                }
            }
            mx = max(mx, area);
        }
    }
    
    cout << cnt << "\n"<<mx;

   
    return 0;
}