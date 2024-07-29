#include <bits/stdc++.h>
using namespace std;

int board[101][101][101];

int dx[6] = {0, -1, 0 ,1 , 0, 0};
int dy[6] = {-1, 0, 1, 0, 0, 0};
int dz[6] = {0, 0, 0 , 0, -1, 1};

int ans = 0;

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n, m, h;
  queue<tuple<int, int, int>> Q;

  cin >> m>>n>>h;

  for(int i=0; i<h; i++){
    for(int j=0; j<n; j++){
      for(int k=0; k<m; k++){
        cin>> board[i][j][k];

        if(board[i][j][k]== 1){
          Q.push({i, j, k});
        }
      }
    }
  }

  while(!Q.empty()){
    auto cur = Q.front(); Q.pop();
    int curX, curY, curZ;    
    tie(curZ, curX, curY) = cur;

    for(int i=0; i<6; i++){
      int nx = curX + dx[i];
      int ny = curY + dy[i];
      int nz = curZ + dz[i];

      if(nx<0|| nx>=n|| ny<0|| ny>=m|| nz<0|| nz>=h) continue;
      if(board[nz][nx][ny]!=0) continue;
      board[nz][nx][ny] = board[curZ][curX][curY]+1;
      Q.push({nz, nx, ny});
    }
  }

  for(int i=0; i<h; i++){
    for(int j=0; j<n; j++){
      for(int k=0; k<m; k++){
        if(board[i][j][k]==-1) continue;
        if(board[i][j][k]==0){
          cout << -1;
          return 0;
        }
        ans = max(ans, board[i][j][k]);
      }
    }
  }

  cout <<ans-1;
  
  
}