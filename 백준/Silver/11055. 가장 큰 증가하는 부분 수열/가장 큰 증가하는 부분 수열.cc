#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int dp[1002];
int arr[1002];

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;
  for(int i=1; i<=n; i++){
    cin >> arr[i];
    dp[i] = arr[i];
  }

  dp[1] = arr[1];

  for(int i=2;i<=n;i++){
    for(int j=1;j<i;j++){
      if(arr[j] < arr[i]){
        dp[i] = max(dp[i], dp[j]+ arr[i]);
      }
    }
  }

  cout << *max_element(dp+1, dp+n+1);
}