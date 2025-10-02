from collections import defaultdict

n = int(input())

temp = list(input())
ans = 0

for _ in range(n-1):
    dic = defaultdict(int)
    dic2 = defaultdict(int)
    for l in temp:
        dic[l] += 1
    lst = list(input())
    for l in lst:
        dic2[l] += 1
    check = 0
    for l in temp:
        if dic2[l] == 0:
            check += 1
            dic2.pop(l)
        else: # 존재
            dic[l] -= 1
            dic2[l] -= 1
            if dic2[l] == 0:
                dic2.pop(l)
    if check <= 1:
        if len(dic2) == 1:
            for d in dic2.values():
                if d == 1:
                    ans+=1
        elif len(dic2) == 0:
            ans+=1

print(ans)


