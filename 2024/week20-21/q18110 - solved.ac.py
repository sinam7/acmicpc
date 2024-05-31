n = int(input())
def round(n):
    return int(n + 0.5)

if n == 0:
    print(0)
else:
    opinion = []
    for _ in range(n):
        opinion.append(int(input()))
    cutoff = round(n * 0.15)
    opinion = sorted(opinion)[cutoff: n-cutoff]
    avg = round(sum(opinion)/len(opinion))
    print(avg)
    
