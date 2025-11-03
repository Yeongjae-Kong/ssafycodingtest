from collections import deque

def solution(n, computers):
    visited = [[0]*n for _ in range(n)]
    
    def bfs(i, j):
        q = deque()
        q.append((i, j))
        visited[i][j] = 1
        while q:
            x, y = q.popleft()
            for i in range(n):
                if computers[x][i] == 1 and not visited[x][i]:
                    q.append((x, i))
                    visited[x][i] = 1
            for i in range(n):
                if computers[y][i] == 1 and not visited[y][i]:
                    q.append((y, i))
                    visited[y][i] = 1
                    
    cnt = 0
    for i in range(n):
        for j in range(n):
            if not visited[i][j] and computers[i][j] == 1:
                bfs(i,j)
                cnt+=1
    return cnt
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
#     answer = 0
#     visited = [[0]*n for _ in range(n)]
#     q = deque()
#     def bfs(i,j):
#         q.append((i,j))
#         visited[i][j] = 1
#         while q:
#             i, j = q.popleft()
#             for k in range(n):
#                 if computers[i][k] == 1 and not visited[i][k]:
#                     q.append((i,k))
#                     visited[i][j] = 1
#                 if computers[j][k] == 1 and not visited[j][k]:
#                     q.append((j,k))
#                     visited[j][k] = 1
            
#     for i in range(n):
#         for j in range(n):
#             if not visited[i][j] and computers[i][j] == 1:
#                 bfs(i,j)
#                 answer += 1
#     return answer