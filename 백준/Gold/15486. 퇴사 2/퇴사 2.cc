#include <bits/stdc++.h>
using namespace std;

int t[1500003];
int p[1500003];
int dp[1500003];

int max_value = 0;

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;

  for(int i=1;i<=n;i++){
    cin >> t[i] >> p[i];
  }

  for(int i=1;i<=n;i++){
      max_value = max(dp[i], max_value);
      dp[i] = max_value;
    if(i+t[i] <= n+1){
      dp[i+t[i]] = max(dp[i]+p[i], dp[i+t[i]]);
    }
  }

  cout << *max_element(dp+1, dp+n+2);
}
