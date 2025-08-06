from itertools import permutations

n, m = map(int, input().split())
    
lst = [i for i in range(1, n+1)]

for p in permutations(lst, m):
    print(*p)
