#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int arr[301][301];
int vis[301][301];
int meltState[301][301];

int dx[4] = {-1,0,1,0};
int dy[4] = {0,-1,0,1};

int n,m;
int year=0;
bool noIce;

bool solve(){
  for(int i=0;i<n;i++){
    fill(vis[i], vis[i]+m, 0);
  }
  int cnt=0;
  noIce = true;
  queue<pair<int, int>> Q;

  for(int i=0; i<n;i++){
    for(int j=0; j<m; j++){
      if(arr[i][j]==0 || vis[i][j]==1) continue;
      noIce = false;
      cnt++;
      vis[i][j] = 1;
      Q.push({i,j});
      while(!Q.empty()){
        auto cur = Q.front(); Q.pop();
        for(int i=0;i<4;i++){
          int nx = dx[i] + cur.X;
          int ny = dy[i] + cur.Y;

          if(nx<0 || nx>=n || ny<0 || ny>=m) continue;

          if(arr[nx][ny]==0||vis[nx][ny]==1) continue;
          vis[nx][ny] = 1;
          Q.push({nx, ny});
        }
      }
    }
  }


  return cnt>=2;
}

int nearIceCount(int x, int y){
  int cnt=0;
  for(int i=0;i<4;i++){
    int nx = x + dx[i];
    int ny = y + dy[i];

    if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
    if(arr[nx][ny]==0) cnt++;
  }
  return cnt;
}

void melt(){
      for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
          int iceCount = meltState[i][j];
          if(arr[i][j]-iceCount>=0){
            arr[i][j]-=iceCount;
          }else if(arr[i][j]-iceCount<0){
            arr[i][j] = 0;
          }
        }
    }
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> n>>m;
  for(int i=0;i<n;i++){
    for(int j=0;j<m;j++){
      cin >> arr[i][j];
    }
  }

  while(true){
    year++;
    for(int i=0;i<n;i++){
      for(int j=0;j<m;j++){
        if(arr[i][j]==0) continue;
        meltState[i][j] = nearIceCount(i,j);
      }
    }
    melt();
    if(solve()){
      cout<<year;
      return 0;
    } else{
      if(noIce){
        cout<<0;
        return 0;
      }
    }
  }
}