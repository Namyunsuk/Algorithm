#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

long dp[2][93];

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;

  dp[0][1] = 0;
  dp[1][1] = 1;

  for(int i=2;i<=n;i++){
    dp[0][i] = dp[0][i-1] + dp[1][i-1];
    dp[1][i] = dp[0][i-1];
  }

  cout << dp[0][n]+dp[1][n]; 
}


