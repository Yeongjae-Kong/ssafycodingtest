from collections import deque

def solution(maps):
    q = deque()
    q.append((0, 0, 1))
    visited = [[0]*len(maps[0]) for _ in range(len(maps))]
    visited[0][0] = 1
    d = [(0,1),(1,0),(0,-1),(-1,0)]
    while q:
        i, j, cnt = q.popleft()
        if (i,j) == (len(maps[0])-1, len(maps)-1):
            return cnt
        for dx, dy in d:
            nx, ny = i+dx, j+dy
            if 0<=nx<len(maps[0]) and 0<=ny<len(maps) and not visited[ny][nx] and maps[ny][nx] == 1:
                q.append((nx, ny, cnt+1))
                visited[ny][nx] = 1
    return -1
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
#     n, m = len(maps), len(maps[0])  # 맵의 크기
#     directions = [(1, 0), (-1, 0), (0, 1), (0, -1)]  # 이동 방향 (남, 북, 동, 서)

#     queue = deque([(0, 0, 1)])  
#     while queue:
#         x, y, dist = queue.popleft()
        
#         if x == n-1 and y == m-1:
#             return dist

#         for dx, dy in directions:
#             nx, ny = x + dx, y + dy
#             if 0 <= nx < n and 0 <= ny < m and maps[nx][ny] == 1:
#                 queue.append((nx, ny, dist + 1))
#                 maps[nx][ny] = 0  # 방문 처리(다시 방문하지 않도록)

#     return -1