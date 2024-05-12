#include <bits/stdc++.h>
using namespace std;

int freq[26];
int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
  
  string S;
  
  cin>>S;

  for(auto e:S){
    freq[e-'a']+=1;
  }

  for(auto e:freq){
    cout<<e<<' ';
  }
  
} 