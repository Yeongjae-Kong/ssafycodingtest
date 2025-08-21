from collections import deque
import sys

def topological_sort(V, E, arr):
    in_degree = [0]*(V+1)
    lst = [[] for _ in range(V+1)]

    for i in range(0, E*2, 2):
        u, v = arr[i], arr[i+1]
        lst[u].append(v)
        in_degree[v] += 1
        
    queue = deque()
    for i in range(1, V + 1): 
        if in_degree[i] == 0:
            queue.append(i)

    result = []
    while queue:
        current = queue.popleft()
        result.append(current)

        for neighbor in lst[current]:
            in_degree[neighbor] -= 1
            if in_degree[neighbor] == 0:
                queue.append(neighbor)
    
    return result
    
V, E = map(int, sys.stdin.readline().split())

arr = []
for _ in range(E):
    a, b = map(int, sys.stdin.readline().split())
    arr.append(a)
    arr.append(b)

sorted_order = topological_sort(V, E, arr)

print(*sorted_order)