#include <bits/stdc++.h>

#define x first
#define y second

using namespace std;

int arr[300][300];

int dx[8] = { -2,-2,2,2,-1,1,-1,1 };
int dy[8] = { -1,1,-1,1,-2,-2,2,2 };

int main(int argc, char** argv) {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t, l, X, Y;
    cin >> t;
    while (t--) {
        queue<pair<int, int>> Q;
        cin >> l;
        for (int i = 0; i < l; i++) {
            fill(arr[i], arr[i] + l, -1);
        }
        cin >> X >> Y;
        arr[X][Y] = 0;
        Q.push({ X,Y });
        cin >> X >> Y;
        while (arr[X][Y] == -1) {
            auto cur = Q.front();
            Q.pop();
            for (int i = 0; i < 8; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= l || ny < 0 || ny >= l)continue;
                if (arr[nx][ny] >= 0)continue;
                arr[nx][ny] = arr[cur.x][cur.y] + 1;
                Q.push({ nx,ny });
            }
        }
        cout << arr[X][Y] << "\n";
    }

    return 0;
}