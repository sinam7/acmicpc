n = int(input())

arr = []
for _ in range(n):
    arr.append(tuple(map(int, input().split())))
arr.sort()

ans = 0
last = -1_000_000_001  # 선을 그은 이후 마지막으로 그은 선의 끝

for x, y in arr:
    ans += max(0, y - max(x, last))
    if last < y:
        last = y

print(ans)
