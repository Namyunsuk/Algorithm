#include <bits/stdc++.h>

#define X first
#define Y second

using namespace std;

string arr[100];
bool isvisit[100][100];
int dx[4] = { 1,0,-1,0 };
int dy[4] = { 0,1,0,-1 };
int n;
queue < pair<int, int>> Q;

void bfs1(int i, int j, char color) {
    isvisit[i][j] = true;
    Q.push({ i,j });
    while (!Q.empty()) {
        auto cur = Q.front();
        Q.pop();
        for (int dir = 0; dir < 4; dir++) {
            int nx = cur.X + dx[dir];
            int ny = cur.Y + dy[dir];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n)continue;
            if (isvisit[nx][ny] || arr[nx][ny] != color)continue;
            isvisit[nx][ny] = true;
            Q.push({ nx,ny });
        }
    }
}

void bfs2(int i, int j) {
    isvisit[i][j] = true;
    Q.push({ i,j });
    while (!Q.empty()) {
        auto cur = Q.front();
        Q.pop();
        for (int dir = 0; dir < 4; dir++) {
            int nx = cur.X + dx[dir];
            int ny = cur.Y + dy[dir];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n)continue;
            if (isvisit[nx][ny] || arr[nx][ny] == 'B')continue;
            isvisit[nx][ny] = true;
            Q.push({ nx,ny });
        }
    }
}

int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int result1 = 0, result2 = 0;

    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    for (int color : {'R', 'G', 'B'}) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isvisit[i][j] || arr[i][j] != color)continue;
                bfs1(i, j, color);
                result1 += 1;
            }
        }
    }

    for (int i = 0; i < n; i++) {
        fill(isvisit[i], isvisit[i] + n, 0);
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (isvisit[i][j] || arr[i][j] =='B')continue;
            bfs2(i, j);
            result2 += 1;
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (isvisit[i][j] || arr[i][j] != 'B')continue;
            bfs1(i, j, 'B');
            result2 += 1;
        }
    }

    cout << result1<<' '<<result2;

    

    return 0;
}