#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int dx[4] = {-1,0,1,0};
int dy[4] = {0,-1,0,1};

int arr[103][103];
int dis[103][103];
int vis[103][103];
int islandMap[103][103];

int n;
int ans = 2000000000;

void markIsland(){
  int num = 0;
  queue<pair<int, int>> Q;
  for(int i=0;i<n;i++){
    for(int j=0; j<n;j++){
      if(arr[i][j]==0 || vis[i][j]==1) continue;
      num++;
      vis[i][j] = 1;
      islandMap[i][j] = num;
      Q.push({i, j});
      while(!Q.empty()){
        auto cur = Q.front(); Q.pop();
        for(int dir = 0;dir<4;dir++){
          int nx = cur.X + dx[dir];
          int ny = cur.Y + dy[dir];

          if(nx<0||nx>=n||ny<0||ny>=n) continue;
          if(arr[nx][ny]==0 || vis[nx][ny]==1) continue;
          vis[nx][ny] = 1;
          islandMap[nx][ny] = num;
          Q.push({nx, ny});
        }
      }
    }
  }
}

bool isSameLand(int startX, int startY, int desX, int desY){
    return islandMap[startX][startY] == islandMap[desX][desY];
}

void solve(){
  for(int i=0;i<n;i++){
    for(int j=0; j<n;j++){
      queue<pair<int, int>> Q;
      bool escape = false;
      if(arr[i][j]==0) continue;
      Q.push({i,j});
      dis[i][j] = 0;
      while(!Q.empty() && !escape){
        auto cur = Q.front(); Q.pop();
        for(int dir=0;dir<4;dir++){
          int nx = cur.X + dx[dir];
          int ny = cur.Y + dy[dir];

          if(nx<0||nx>=n||ny<0||ny>=n) continue;
          if(dis[nx][ny]>=0) continue;
          if(arr[nx][ny]==1&&isSameLand(i, j, nx, ny)) continue;
          if(arr[nx][ny]==1&&!isSameLand(i, j, nx, ny)){
            ans = min(ans, dis[cur.X][cur.Y]);
            escape = true;
            break;
          }
          dis[nx][ny] = dis[cur.X][cur.Y]+1;
          Q.push({nx, ny});
        }
      }
      for(int i=0;i<n;i++){
        fill(dis[i], dis[i]+n,-1);
      }
    }
  }
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> n;

  for(int i=0;i<n;i++){
    fill(dis[i], dis[i]+n, -1);
  }

  for(int i=0;i<n;i++){
    for(int j=0;j<n;j++){
      cin >> arr[i][j];
    }
  }

  markIsland();
  solve();



  cout<<ans;
}