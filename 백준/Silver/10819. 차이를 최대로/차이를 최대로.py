from itertools import permutations
n = int(input())
lst = list(map(int, input().split()))
res = 0
for i in permutations(lst, n):   
    ans = 0
    # print(i)
    for j in range(1, n):
        ans += abs(i[j-1]-i[j])
    if res < ans:
        res = ans
print(res)