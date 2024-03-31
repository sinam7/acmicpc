n = int(input())
ans = 0
for i in range(n, 0, -1):
    ans += (n / i)
print(ans)
