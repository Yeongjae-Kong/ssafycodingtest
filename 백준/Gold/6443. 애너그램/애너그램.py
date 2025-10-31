import sys
from collections import defaultdict

def back_tracking(cnt):
    if cnt == len(lst):
        print("".join(answer))
        return
    for k in visited:
        if visited[k]:
            visited[k] -= 1
            answer.append(k)
            back_tracking(cnt+1)
            visited[k] += 1
            answer.pop()

n = int(input())

for _ in range(n):
    lst = list(input())
    lst.sort()
    visited = defaultdict(int)
    answer = []

    for i in lst:
        visited[i] += 1
        
    back_tracking(0)