import heapq
import math
import sys

def solve_dijkstra(start_nodes, V, graph):
    dist = [math.inf] * (V + 1)
    q = []

    for node in start_nodes:
        dist[node] = 0
        heapq.heappush(q, (0, node))

    while q:
        w, k = heapq.heappop(q)
        if dist[k] < w:
            continue
        for next_w, next_k in graph[k]:
            if w + next_w < dist[next_k]:
                dist[next_k] = w + next_w
                heapq.heappush(q, (w + next_w, next_k))
    return dist


V, E = map(int, sys.stdin.readline().split())
graph = [[] for _ in range(V + 1)]
for _ in range(E):
    u, v, w = map(int, sys.stdin.readline().split())
    graph[u].append((w, v))
    graph[v].append((w, u))
M, x = map(int, sys.stdin.readline().split())
lst_m = list(map(int, sys.stdin.readline().split()))
S, y = map(int, sys.stdin.readline().split())
lst_s = list(map(int, sys.stdin.readline().split()))

# 맥도날드에서 각 집까지의 최단 거리 계산
dist_m = solve_dijkstra(lst_m, V, graph)

# 스타벅스에서 각 집까지의 최단 거리 계산
dist_s = solve_dijkstra(lst_s, V, graph)

min_total_dist = math.inf
found = False

for i in range(1, V + 1):
    # 현재 정점이 맥도날드나 스타벅스가 있는 곳인지 확인
    if i in lst_m or i in lst_s:
        continue
        
    # 맥세권 및 스세권 조건 충족 여부 확인
    if dist_m[i] <= x and dist_s[i] <= y:
        total_dist = dist_m[i] + dist_s[i]
        min_total_dist = min(min_total_dist, total_dist)
        found = True

if found:
    print(min_total_dist)
else:
    print(-1)