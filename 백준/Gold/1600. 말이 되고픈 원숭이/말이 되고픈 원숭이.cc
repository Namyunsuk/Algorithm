#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int dx1[4] = {-1, 0, 1, 0};
int dy1[4] = {0, -1, 0, 1};

int dx2[8] = {-1, -2, -2,-1, 1, 2, 2, 1 };
int dy2[8] = {-2, -1, 1, 2, -2, -1, 1, 2};

int k,n,m;;
int arr[201][201];
int dis[32][201][201];
int ans = 20000000;
queue<tuple<int, int, int>> Q;

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >>k;
  cin >>m>>n;

  for(int i=0;i<n;i++){
    for(int j=0;j<m;j++){
      cin >> arr[i][j];
    }
  }

  dis[0][0][0] = 1;
  Q.push({0, 0, 0});

  while(!Q.empty()){
    int kCount, x, y;
    tie(kCount, x, y) = Q.front();
    Q.pop();

    if(kCount<k){
      for(int i=0;i<8;i++){
        int nx = x + dx2[i];
        int ny = y + dy2[i];
        if(nx<0||nx>=n||ny<0||ny>=m) continue;
        if(arr[nx][ny]==1) continue;
        if(dis[kCount+1][nx][ny]>0) continue;
        dis[kCount+1][nx][ny] = dis[kCount][x][y]+1;
        Q.push({kCount+1, nx, ny});
      }
    }
    for(int i=0;i<4;i++){
      int nx = x+dx1[i];
      int ny = y+dy1[i];
      if(nx<0||nx>=n||ny<0||ny>=m) continue;
      if(arr[nx][ny]==1) continue;
      if(dis[kCount][nx][ny]>0) continue;
      dis[kCount][nx][ny] = dis[kCount][x][y]+1;
      Q.push({kCount, nx, ny});
    }
  }

  for(int i=0;i<k+1;i++){
    if(dis[i][n-1][m-1]!=0)
      ans = min(dis[i][n-1][m-1], ans);
  }

  if(ans==20000000){
    cout<<-1;
    return 0;
  }
  cout<<ans-1;
  return 0;
}