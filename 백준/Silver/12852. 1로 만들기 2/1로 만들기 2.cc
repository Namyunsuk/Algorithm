#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int dp[1000002];
int arr[1000002];

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >>n;

  dp[1] = 0;


  for(int i=2;i<=n;i++){
    dp[i] = dp[i-1] + 1;
    arr[i] = i-1;
    if(i%2==0) {
      if(dp[i]>dp[i/2]+1){
        dp[i] = dp[i/2]+1;
        arr[i] = i/2;
      }
    }
    if(i%3==0) {
      if(dp[i]>dp[i/3]+1){
        dp[i] = dp[i/3]+1;
        arr[i] = i/3;
      }
    }
  }

  cout<<dp[n]<<"\n";
  int cur = n;
  while(true){
    cout<< cur <<" ";
    if(cur==1) break;
    cur = arr[cur];
  }
}


