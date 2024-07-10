#include <bits/stdc++.h>
using namespace std;


int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
  
  deque<int> DQ;
  int n, m, num, ans=0;
  cin >> n >> m;

  for(int i=1; i<=n;i++){
    DQ.push_back(i);
  }

  while(m--){
    cin >> num;

    if(DQ.front()==num){
        DQ.pop_front();
        continue;
    }

    int idx = find(DQ.begin(), DQ.end(), num) - DQ.begin();

    while(DQ.front() != num){
      if(idx <= DQ.size() - idx){ // left
          int tmp = DQ.front();
           DQ.pop_front();
           DQ.push_back(tmp);
           ans++;
      }
      else{
        int tmp = DQ.back();
        DQ.pop_back();
        DQ.push_front(tmp);
        ans++;
      }
    }

    DQ.pop_front();
  }

  cout << ans;
}
