import sys
input = sys.stdin.readline

n = int(input())
arr = []
s = set()

for _ in range(n):
    t = tuple(map(int, input().split()))
    arr.append(t)
    s.add(t)

ans = 0
for i in range(n):
    x1, y1 = arr[i]
    for j in range(i + 1, n):
        x2, y2 = arr[j]
        if x1 != x2 and y1 != y2:
            if (x1, y2) in s and (x2, y1) in s:
                ans += 1


print(ans // 2)
