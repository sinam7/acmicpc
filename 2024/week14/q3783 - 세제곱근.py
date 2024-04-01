import decimal
T = int(input())

decimal.getcontext().prec = 1000
for _ in range(T):
    n = input().rstrip()
    res = decimal.Decimal(decimal.Decimal(n + ".0000000000") ** (decimal.Decimal(1) / decimal.Decimal(3)))
    res = decimal.Decimal(round(res, 500)).quantize(decimal.Decimal('.0000000001'), rounding=decimal.ROUND_DOWN)
    
    print(res)
    
