from collections import deque
import sys


def bfs(x, y, w):  # x,y좌표, 벽 부순 여부
    queue = deque()
    queue.append((x, y, w))
    visited[x][y][w] = 1

    while queue:
        x, y, w = queue.popleft()

        if x == N-1 and y == M-1:
            return visited[x][y][w]

        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]

            if nx < 0 or nx >= N or ny < 0 or ny >= M:
                continue

            # 다음이 벽이고 아직 부수지 않은 경우
            if graph[nx][ny] == 1 and w == 0:
                visited[nx][ny][1] = visited[x][y][w]+1
                queue.append((nx, ny, 1))
            # 다음이 벽이 아니고 방문하지 않은 경우
            elif graph[nx][ny] == 0 and visited[nx][ny][w] == 0:
                visited[nx][ny][w] = visited[x][y][w]+1
                queue.append((nx, ny, w))

    return -1


N, M = map(int, sys.stdin.readline().split())

graph = []
visited = [[[0]*2 for i in range(M)] for i in range(N)]
# x,y위치에서 벽을 부수지 않을 떄의 이동거리는 visited[x][y][0]에 저장될 것이고,
# 벽을 부수었을 떄의 이동거리는 visited[x][y][1]에 저장될 것이다.

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

for _ in range(N):
    graph.append(list(map(int, sys.stdin.readline().rstrip())))

print(bfs(0, 0, 0))
