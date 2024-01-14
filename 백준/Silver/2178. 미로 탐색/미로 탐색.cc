#include <bits/stdc++.h>

#define X first
#define Y second

using namespace std;

string arr[100];
int dist[100][100];
int dx[4] = {1,0,-1,0};
int dy[4] = {0,-1,0,1};


int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int n, m, num;
    queue<pair<int, int>> Q;
    
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    dist[0][0] = 1;
    Q.push({0,0});

    while (!Q.empty()) {
        pair<int, int> cur = Q.front();
        Q.pop();
        for (int i = 0; i < 4; i++) {
            int nx = cur.X + dx[i];
            int ny = cur.Y + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if (arr[nx][ny] != '1' || dist[nx][ny] >0) continue;
            dist[nx][ny] = dist[cur.X][cur.Y] + 1;
            Q.push({ nx,ny });
        }
    }

    cout << dist[n - 1][m - 1];
   
    return 0;
}