from collections import deque
import sys
import copy


def find_safe_size(map):
    count = 0

    for i in range(N):
        for j in range(M):
            if map[i][j] == 0:
                count += 1
    return count


def wall(count):
    if count == 3:
        bfs()
        return
    for i in range(N):
        for j in range(M):
            if graph[i][j] == 0:
                graph[i][j] = 1
                wall(count+1)
                graph[i][j] = 0


def bfs():
    global result
    queue = deque()

    for i in virus:
        queue.append(i)

    map = copy.deepcopy(graph)

    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]

            if nx < 0 or nx >= N or ny < 0 or ny >= M:
                continue

            if map[nx][ny] == 0:
                map[nx][ny] = 2
                queue.append((nx, ny))
    result = max(result, find_safe_size(map))


N, M = map(int, sys.stdin.readline().split())

result = 0

graph = []

virus = []

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

for _ in range(N):
    graph.append(list(map(int, sys.stdin.readline().split())))

cnt = 0
for i in range(N):
    for j in range(M):
        if cnt >= 10:
            break
        if graph[i][j] == 2:
            virus.append((i, j))
            cnt += 1

wall(0)
print(result)
