#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int dx[4] = {-1,0,1,0};
int dy[4] = {0,-1,0,1};

int arr[100003];
int n, k;
queue<int> Q;

void teleport(int num){
  int tmp = num;
    while(true){
    if(tmp==0) break;
    tmp*=2;
    if(tmp>100000) break;
    if(arr[tmp]>=0) break;
    if(tmp == k){
      arr[k] = arr[num];
      break;
      }
    arr[tmp] = arr[num];
    Q.push(tmp);
  }
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);


  cin >> n>> k;

if(n==k){
  cout<<0;
  return 0;
}

  fill(arr, arr+100002, -1);

  arr[n] = 0;
  Q.push(n);

  teleport(n);

  while(arr[k]==-1){
    auto cur = Q.front(); Q.pop();
    for(int idx:{cur-1, cur+1}){
      if(idx<0 || idx>=100000 ||idx==n) continue;
      if(arr[idx]>=0) continue;
      arr[idx] = arr[cur] + 1;
      Q.push(idx);
      teleport(idx);
    }
  }

  cout << arr[k];
  return 0;
}