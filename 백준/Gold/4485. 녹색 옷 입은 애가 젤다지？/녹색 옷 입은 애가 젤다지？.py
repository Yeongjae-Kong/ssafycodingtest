import heapq
import math

n = int(input())
i = 1
d = [(0,1),(1,0),(0,-1),(-1,0)]


def dijkstra(n):
    ans = 0
    lst = []
    for _ in range(n):
        lst.append(list(map(int, input().split())))
    dist = [[math.inf]*n for _ in range(n)]
    dist[0][0] = lst[0][0]
    q = [(lst[0][0], 0, 0)] # w, 시작좌표(0,0)
    while q:
        w, y, x = heapq.heappop(q)
        if w > dist[y][x]:
            continue
        for dx, dy in d:
            nx, ny = x+dx, y+dy
            if 0<=ny<n and 0<=nx<n:
                nw = w+lst[ny][nx]
                if nw < dist[ny][nx]:
                    dist[ny][nx] = nw
                    heapq.heappush(q, (nw, ny, nx))
    return dist[n-1][n-1]
    
while n!=0:
    ans = dijkstra(n)
    print("Problem {}: {}".format(i, ans))
    i+=1
    n=int(input())