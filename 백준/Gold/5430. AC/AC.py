from collections import deque
import sys

T = int(sys.stdin.readline())


for _ in range(T):
    p = sys.stdin.readline().rstrip()
    n = int(sys.stdin.readline())
    arr = []
    queue = deque()

    R_NUM = 0

    flag = True

    arr = sys.stdin.readline().rstrip()
    if n != 0:
        arr = list(map(int, arr[1:-1].split(",")))
        queue = deque(arr)

    for op in p:
        if op == "R":
            R_NUM += 1
        else:
            if len(queue) < 1:
                print("error")
                flag = False
                break

            if R_NUM % 2 == 0:
                queue.popleft()
            else:
                queue.pop()

    if flag:
        if R_NUM % 2 == 1:
            queue.reverse()

        print('[' + ','.join(map(str, list(queue))) + ']')
