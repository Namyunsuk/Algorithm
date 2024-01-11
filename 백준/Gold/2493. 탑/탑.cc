#include <bits/stdc++.h>

using namespace std;

#define top_height first 
#define index second 


int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    stack<pair<int, int>> S;
    
    int n, height;

    cin >> n;
    S.push({ 100000001,0 });
    for (int i = 1; i <= n; i++) {
        cin >> height;
        while (S.top().top_height < height) S.pop();
        cout << S.top().index << ' ';
        S.push({ height,i });
        
    }
       
   
    return 0;
}