a, b, c, d = [*map(int,input().split())]
if a > b:  # a <= b
    a, b = b, a
if c > d:  # c <= d
    c, d = d, c
if a > c:  # a <= c
    a, b, c, d = c, d, a, b

ans=0
if d <= b: # a<=c<=d<=b
    ans = b - a + 1
else: 
    if c <= b: # a<=c<=b < d
        ans = d - a + 1
    else:  # a<=b < c<=d
        ans = b - a + 1 + d - c + 1   

print(ans)
