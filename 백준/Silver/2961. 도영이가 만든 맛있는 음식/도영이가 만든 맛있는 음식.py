from itertools import combinations
import math

n = int(input())
s = []
b = []
for _ in range(n):
    s1, b1 = map(int, input().split())
    s.append(s1)
    b.append(b1)

mul_s = []
sum_b = []
for i in range(1, n+1):
    for a in combinations(s, i):
        mul = 1
        for j in a:
            mul *= j
            mul_s.append(mul)
for i in range(1, n+1):
    for a in combinations(b, i):
        sum = 0
        for j in a:
            sum += j
            sum_b.append(sum)
            
tmp = math.inf
for i in range(len(mul_s)):
    abs(mul_s[i] - sum_b[i])
    if (tmp > abs(mul_s[i] - sum_b[i])):
        tmp = abs(mul_s[i] - sum_b[i])
print(tmp)