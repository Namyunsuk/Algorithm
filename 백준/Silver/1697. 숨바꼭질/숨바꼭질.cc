#include <bits/stdc++.h>
using namespace std;

int board[100002];

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  queue<int> Q;
  int n,k;
  cin >>n >> k;

  fill(board, board+100001, -1);

  board[n] = 0;
  Q.push(n);

  while(board[k]==-1){
    auto cur = Q.front(); Q.pop();
    for(auto nx: {cur-1, cur+1, cur*2}){

      if(nx<0 || nx>100000) continue;
      if(board[nx] != -1) continue;
      board[nx] = board[cur] + 1;
      Q.push(nx);
    }
  }

  cout << board[k];

}