import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))

dic = {}
for i in arr:
    if i not in dic:
        dic[i] = 1
    else:
        dic[i] = dic[i] + 1


mx = dic[max(dic, key=dic.get)]
print('YES' if n - mx >= mx - 1 else 'NO')
