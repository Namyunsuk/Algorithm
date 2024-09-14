#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int dp[2][42];


int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int t, n;
  cin >>t;

  dp[0][0] = 1;
  dp[1][0] = 0;

  dp[0][1] = 0;
  dp[1][1] = 1;

  while(t--){
    cin>>n;
    for(int i=2;i<=n;i++){
      dp[0][i] = dp[0][i-1]+dp[0][i-2];
      dp[1][i] = dp[1][i-1]+dp[1][i-2];
    }

    cout<<dp[0][n]<<" "<<dp[1][n]<<"\n";
  }
}