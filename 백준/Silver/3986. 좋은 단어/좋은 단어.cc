#include <bits/stdc++.h>
using namespace std;

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
  
  int N, ans = 0;
  string words;

  cin >> N;

  while(N--){
    stack<int> S;
    cin >> words;
    for(auto word : words){
      if(S.empty() || S.top()!=word){
          S.push(word);
      }
      else{
        S.pop();
      }
    }
    if(S.empty()) ans++;
  }

  cout << ans;
}

