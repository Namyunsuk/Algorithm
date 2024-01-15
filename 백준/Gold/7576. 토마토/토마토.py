from collections import deque
import sys


def bfs():
    while queue:
        x, y = queue.popleft()

        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]

            if nx < 0 or nx >= N or ny < 0 or ny >= M:
                continue

            if graph[nx][ny] == -1:
                continue

            if graph[nx][ny] == 0:
                graph[nx][ny] = graph[x][y]+1
                queue.append((nx, ny))


M, N = map(int, sys.stdin.readline().split())
result = 0

graph = []
queue = deque()


dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

for _ in range(N):
    graph.append(list(map(int, sys.stdin.readline().split())))


for i in range(N):
    for j in range(M):
        if graph[i][j] == 1:
            queue.append((i, j))


bfs()

for row in graph:
    for num in row:
        if num == 0:
            print(-1)
            exit()
        else:
            result = max(result, max(row))

print(result-1)
