import sys

input = sys.stdin.readline

N, Q = list(map(int, input().split()))

parent = [-1 for _ in range(N + 1)]
childs = [[] for _ in range(N + 1)]
status = [-1 for _ in range(N + 1)]  # -1: closed, 1: opened

for i in range(1, N + 1):
    data = list(map(int, input().split()))
    n, arr = data[0], data[1:]
    childs[i] = arr
    for child in arr:
        parent[child] = i


def draw(parent, stack):
    for child in childs[parent]:
        stack.append(child)
        if status[child] == 1:
            draw(child, stack)


cursor = 1
status[1] = 1
for _ in range(Q):
    command = input().split()
    now = [1]
    draw(1, now)
    if command[0] == 'toggle':
        status[now[cursor]] *= -1
    else:  # if command[0] == 'move':
        count = int(command[1])

        cursor += count
        if cursor < 1:
            cursor = 1

        if cursor >= len(now):
            cursor = len(now) - 1

        print(now[cursor])
