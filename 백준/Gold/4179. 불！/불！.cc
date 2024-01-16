#include <bits/stdc++.h>

#define x first
#define y second

using namespace std;

int dist1[1000][1000];
int dist2[1000][1000];
string arr[1000];
int dx[4] = { 1,0,-1,0 };
int dy[4] = { 0,1,0,-1 };


int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);


    queue<pair<int, int>> Q1;
    queue<pair<int, int>> Q2;
    int r, c;
    
    
    cin >>r >> c;

    for (int i = 0; i < r; i++) {
        fill(dist1[i], dist1[i] + c, -1);
        fill(dist2[i], dist2[i] + c, -1);
    }
    

    for (int i = 0; i < r; i++) {
        cin >> arr[i];
        for (int j = 0; j < c; j++) {
            if (arr[i][j] == 'J') {
                dist2[i][j] = 0;
                Q2.push({ i,j });
            }
            else if (arr[i][j] == 'F') {
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
            if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
            if (dist1[nx][ny]>=0 ||arr[nx][ny] == '#') continue;
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
            
            if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                cout << dist2[cur.x][cur.y] + 1;
                return 0;
            }
            if (dist2[nx][ny]>=0 || arr[nx][ny] == '#') continue;
            if (dist1[nx][ny] != -1 && dist1[nx][ny] <= dist2[cur.x][cur.y] + 1) continue;
            
            dist2[nx][ny] = dist2[cur.x][cur.y] + 1;
            Q2.push({ nx,ny });
        }
    }

    cout << "IMPOSSIBLE";
   
   
    return 0;
}