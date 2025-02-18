import sys


def dfs(x, y, sum_v, cnt):
    global result

    if cnt == 4:
        result = max(result, sum_v)
        return
    else:
        for i in range(4):
            dx = x+nx[i]
            dy = y+ny[i]

            if 0 <= dx < N and 0 <= dy < M and not visited[dx][dy]:

                # ㅏ, ㅓ, ㅗ, ㅜ 모양 예외처리
                if cnt == 2:
                    visited[dx][dy] = True
                    # sum_V에는 옆 블럭 값을 더해주고 좌표는 다시 원래 좌표 값을 넣어 줌
                    dfs(x, y, sum_v+graph[dx][dy], cnt+1)
                    visited[dx][dy] = False

                visited[dx][dy] = True
                dfs(dx, dy, sum_v+graph[dx][dy], cnt+1)
                visited[dx][dy] = False


N, M = map(int, sys.stdin.readline().split())

graph = []

visited = [[False]*M for i in range(N)]

nx = [-1, 1, 0, 0]
ny = [0, 0, -1, 1]

result = 0

for _ in range(N):
    graph.append(list(map(int, sys.stdin.readline().split())))

for i in range(N):
    for j in range(M):
        visited[i][j] = True
        dfs(i, j, graph[i][j], 1)
        visited[i][j] = False

print(result)
