#include <bits/stdc++.h>
using namespace std;

void parseNumbers(string &numbers, deque<int> &DQ){
    int num=0;
    for(auto value : numbers){
      if(value=='[') continue;
      if(value==','||value==']'){
        if(num==0) continue;
        DQ.push_back(num);
        num=0;
        continue;
      }
      num = num*10 + (value-'0');
    }
}

void printResult(deque<int> &DQ){
  cout <<"[";
  while(true){
    if(!DQ.size()) break;
    cout<<DQ.front();
    DQ.pop_front();
    if(!DQ.size()) break;
    cout<<",";
  }
  cout<<"]\n";
}


int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
  
  int T, n;
  bool flag=true, error=false;
  string query, numbers;

  cin >>T;

  while(T--){
    deque<int> DQ;
    cin >> query;
    cin >> n;
    cin >> numbers;
    parseNumbers(numbers, DQ);

    for(auto op : query){
      if(op=='R') flag = !flag;
      else if(op=='D'){
        if(!DQ.size()) { // 빈 경우
          cout<<"error\n";
          error = true;
          break;
        }
        else if(flag){ // 안뒤집힘
          DQ.pop_front();
        }
        else{
          DQ.pop_back();
        }
      }
    }
    if(!error){
      if(!flag){
        reverse(DQ.begin(), DQ.end());
      }
      printResult(DQ);
    }
    error = false; // error여부 초기화
    flag=true; //flag초기화
  }
}

