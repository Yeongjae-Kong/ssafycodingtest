from collections import defaultdict
import sys

n, d, k, c = map(int, sys.stdin.readline().split())
lst = []
for _ in range(n):
    lst.append(int(sys.stdin.readline()))
lst.extend(lst[:k])
dic = defaultdict(int)
for i in range(k):
    dic[lst[i]]+=1
ans = len(dic)
if c not in dic:
    ans+=1
    
for i in range(k, n+k):
    dic[lst[i]]+=1
    dic[lst[i-k]]-=1
    if dic[lst[i-k]]==0:
        del dic[lst[i-k]]
    if c not in dic:
        ans = max(ans, len(dic)+1)
    else:
        ans = max(ans, len(dic))
print(ans)