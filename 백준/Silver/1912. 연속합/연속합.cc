#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int dp[100002];
int arr[100002];

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;

  for(int i=1;i<=n;i++){
    cin >> arr[i];
  }

  dp[1] = arr[1];
  for(int i=2;i<=n;i++){
    dp[i] = max(0, dp[i-1]) + arr[i];
  }

  cout << *max_element(dp+1, dp+n+1);
}