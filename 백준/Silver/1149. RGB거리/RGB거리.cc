#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int dp[3][1000002];
int R[1001];
int G[1001];
int B[1001];

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;

  for(int i=0; i<n;i++){
    cin>>R[i]>>G[i]>>B[i];
  }

  dp[0][0] = R[0];
  dp[1][0] = G[0];
  dp[2][0] = B[0];

  for(int i=1;i<n;i++){
    dp[0][i] = min(dp[1][i-1], dp[2][i-1])+R[i]; // R
    dp[1][i] = min(dp[0][i-1], dp[2][i-1])+G[i]; // G
    dp[2][i] = min(dp[0][i-1], dp[1][i-1])+B[i]; // B
  }

  cout << min({dp[0][n-1],dp[1][n-1],dp[2][n-1]});
}