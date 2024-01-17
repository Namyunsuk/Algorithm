#include <bits/stdc++.h>

#define x first
#define y second

using namespace std;

string arr[1000];
int dist1[1000][1000];
int dist2[1000][1000];

int dx[4] = { 1,0,-1,0 };
int dy[4] = { 0,1,0,-1 };

int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t, n, m;
    cin >> t;
    while (t--) {
        bool escape = false;
        queue<pair<int, int>> Q1;
        queue<pair<int, int>> Q2;
        cin >> m>> n;
        for (int i = 0; i < n; i++) {
            fill(dist1[i], dist1[i] + m, -1);
            fill(dist2[i], dist2[i] + m, -1);
        }
        for (int i = 0; i < n; i++) {
            cin >> arr[i];
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '@') {
                    dist2[i][j] = 0;
                    Q2.push({ i,j });
                }
                if (arr[i][j] == '*') {
                    dist1[i][j] = 0;
                    Q1.push({ i,j });
                }
            }
        }

        while (!Q1.empty()) {
            auto cur = Q1.front();
            Q1.pop();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny<0 || ny>=m)continue;
                if (dist1[nx][ny] >= 0 || arr[nx][ny] == '#')continue;
                dist1[nx][ny] = dist1[cur.x][cur.y] + 1;
                Q1.push({ nx,ny });
            }
        }

        while (!Q2.empty()) {
            auto cur = Q2.front();
            Q2.pop();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny<0 || ny>=m) {
                    cout << dist2[cur.x][cur.y] + 1<<"\n";
                    escape = true;
                    break;
                }
                if (dist2[nx][ny] >= 0 || arr[nx][ny] == '#')continue;
                if (dist1[nx][ny]!=-1 && dist1[nx][ny] <= dist2[cur.x][cur.y] + 1)continue;
                dist2[nx][ny] = dist2[cur.x][cur.y] + 1;
                Q2.push({ nx,ny });
            }
            if (escape)break;
        }

        if(!escape)cout << "IMPOSSIBLE\n";
    }

    return 0;
}