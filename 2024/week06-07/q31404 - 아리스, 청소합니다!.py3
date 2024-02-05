import sys

input = sys.stdin.readline

h, w = list(map(int, input().split()))
r, c, d = list(map(int, input().split()))

ruleA = [list(map(int, list(input().strip()))) for _ in range(h)]
ruleB = [list(map(int, list(input().strip()))) for _ in range(h)]

direction = [(-1, 0), (0, 1), (1, 0), (0, -1)]
lastCleaned = [-1, -1, -1]
lastBefore = [-2, -2]

floor = [[0 for _ in range(w)] for _ in range(h)]  # 0: dust, 1: cleaned
ans = 0
count = 0
notCleanedYet = False
while True:
    # OOB Check
    if not ((0 <= r < h) and (0 <= c < w)):
        break

    # Cycle Check
    if (not notCleanedYet) and lastCleaned == [r, c, d] and lastBefore == [r - direction[d][0], c - direction[d][1]]:
        break

    notCleanedYet = True if floor[r][c] == 0 else False

    if notCleanedYet:
        lastBefore = [r, c]  # remember the position before last cleaned

    # clean
    floor[r][c] = 1

    # turn
    d = (d + (ruleA if notCleanedYet else ruleB)[r][c]) % 4

    # move
    r = (r + direction[d][0])
    c = (c + direction[d][1])
    count += 1

    if notCleanedYet:
        lastCleaned = [r, c, d]  # remember last cleaned position
        ans += count
        count = 0

print(ans)
