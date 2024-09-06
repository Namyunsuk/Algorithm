#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int arr[100001];
int vis[100001];

void solve(int idx){
  int cur = idx;
  while(true){
    vis[cur] = idx;
    cur = arr[cur];
    if(vis[cur]==idx){
      while(vis[cur]!=-1){
        vis[cur] = -1;
        cur = arr[cur];
      }
      return;
    }
    else if(vis[cur]!=0) return;
  }
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int T,n;
  cin >> T;

  while(T--){
    cin >> n;
    fill(vis+1, vis+n+1, 0);
    int ans=0;
    for(int i=1; i<= n; i++){
      cin >> arr[i];
    }

    for(int i=1; i<= n; i++){
      if(vis[i]==0) solve(i);
    }

    for(int i=1; i<=n;i++){
      if(vis[i]!=-1) ans++;
    }
    cout << ans << "\n";
  }

}