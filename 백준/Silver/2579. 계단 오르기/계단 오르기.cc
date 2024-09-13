#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int dp[3][1000002];
int arr[301];

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >>n;
  for(int i=0;i<n;i++){
    cin >> arr[i];
  }

  dp[1][0] = arr[0];
  dp[2][0] = 0;
  dp[1][1] = arr[1];
  dp[2][1] = arr[0] + arr[1];

  for(int i=2; i<n;i++){
    dp[1][i] = max(dp[1][i-2], dp[2][i-2])+arr[i];
    dp[2][i] = dp[1][i-1] + arr[i];
  }

  cout << max(dp[1][n-1], dp[2][n-1]);
}
