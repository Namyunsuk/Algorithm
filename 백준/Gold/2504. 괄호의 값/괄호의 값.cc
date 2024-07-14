#include <bits/stdc++.h>
using namespace std;

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  stack<int> S;
  string str;
  int result = 0, mul = 1;

  cin >> str;

  for(int i=0; i<str.size(); i++){
    if(str[i]=='('){
      mul*=2;
      S.push(str[i]);
    }
    else if(str[i]=='['){
      mul*=3;
      S.push(str[i]);
    }
    else if(str[i]==')'){
      if(S.empty()|| S.top()!='('){
        cout << 0;
        return 0;
      }
      if(i!=0&&str[i-1]=='('){
        result+=mul;
        mul/=2;
        S.pop();
      }
      else{
        mul/=2;
        S.pop();
      }
    }
    else if(str[i]==']'){
      if(S.empty()|| S.top()!='['){
        cout << 0;
        return 0;
      }
      if(i!=0&&str[i-1]=='['){
        result+=mul;
        mul/=3;
        S.pop();
      }
      else{
        mul/=3;
        S.pop();
      }
    }
  }

  if(S.empty()){
    cout << result;
  }
  else{
    cout << 0;
  }
}


