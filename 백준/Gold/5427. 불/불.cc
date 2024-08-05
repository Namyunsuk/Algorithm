#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

string board[1001];


int vis1[1001][1001];
int vis2[1001][1001];

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int t, m, n;

  cin >> t;

  while(t--){
    bool fin = false;
    queue<pair<int, int>> Q1, Q2; // 1: 불, 2: 상근
    cin >> n >> m;

    fill(board, board+m, "");

    for(int i=0; i<m; i++){
      fill(vis1[i], vis1[i]+n, 0);
      fill(vis2[i], vis2[i]+n, 0);
    }

    for(int i=0;i<m;i++){ // 상근, 불 초기 위치 세팅
      cin >> board[i];
      for(int j=0; j<n;j++){
        if(board[i][j]=='*'){
          Q1.push({i, j});
        }
        if(board[i][j]=='@'){
          Q2.push({i, j});
        }
      }
    }

    // 불 탐색
    while(!Q1.empty()){
      auto cur = Q1.front(); Q1.pop();

      for(int dir=0; dir<4; dir++){
        int nx = cur.X + dx[dir];
        int ny = cur.Y + dy[dir];

        if(nx<0||nx>=m||ny<0||ny>=n) continue; // 벗어난 경우
        if(board[nx][ny]=='#' || board[nx][ny]=='@') continue; // 벽 또는 상근이인 경우
        if(vis1[nx][ny]>0) continue; // 이미 방문
        vis1[nx][ny] = vis1[cur.X][cur.Y] + 1;
        Q1.push({nx, ny});
      }
    }
    
    // 상근 탐색
    while(!Q2.empty()){
      auto cur = Q2.front(); Q2.pop();

      for(int dir=0; dir<4; dir++){
        int nx = cur.X + dx[dir];
        int ny = cur.Y + dy[dir];

        if(nx<0||nx>=m||ny<0||ny>=n){ // 탈출한 경우
          cout << vis2[cur.X][cur.Y] + 1<<"\n";
          fin = true;
          break;
        }
        if(vis1[nx][ny]!=0&&(vis1[nx][ny]<= vis2[cur.X][cur.Y] + 1 )) continue;
        if(board[nx][ny]=='#' || board[nx][ny]=='*') continue; // 벽 또는 상근이인 경우
        if(vis2[nx][ny]>0) continue; // 이미 방문
        vis2[nx][ny] = vis2[cur.X][cur.Y] + 1;
        Q2.push({nx, ny});
      }
      if(fin) break;
    }

    if(!fin){
      cout << "IMPOSSIBLE\n";
    }
  }
}