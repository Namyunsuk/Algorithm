#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int dp[501][501];
int arr[501][501];

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >>n;

  for(int i=0;i<n;i++){
    for(int j=0;j<=i;j++){
      cin >>arr[i][j];
    }
  }
  
  dp[0][0] = arr[0][0];

  for(int i=1;i<n;i++){
    for(int j=0;j<n;j++){
      if(j==0){
        dp[i][j] = dp[i-1][j] + arr[i][j];
      }else{
        dp[i][j] = max(dp[i-1][j-1], dp[i-1][j]) + arr[i][j];
      }
    }
  }


  int ans=0;

  for(int i=0;i<n;i++){
    ans = max(ans, dp[n-1][i]);
  }

  cout << ans;
}