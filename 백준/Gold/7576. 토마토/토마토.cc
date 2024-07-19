#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int n,m;
int board[1000][1000];

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int ans = -1;
  queue<pair<int, int>> Q;

  cin >> m>>n;
  for(int i=0;i<n;i++){
    for(int j=0;j<m;j++){
      cin >> board[i][j];
      if(board[i][j] == 1) Q.push({i,j});
    }
  }

  while(!Q.empty()){
    pair<int, int> cur = Q.front(); Q.pop();
    for(int i=0;i<4;i++){
      int nx = cur.X + dx[i];
      int ny = cur.Y + dy[i];

      if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
      if(board[nx][ny]!=0) continue;
      board[nx][ny] = board[cur.X][cur.Y] + 1;
      Q.push({nx, ny});
    }
  }

  for(int i=0;i<n;i++){
    for(int j=0;j<m;j++){
      if(board[i][j]==0){
        cout << -1;
        return 0;
      }
      ans = max(ans, board[i][j]);
    }
  }
  if(ans == 1) cout << 0;
  else cout << ans-1;
}