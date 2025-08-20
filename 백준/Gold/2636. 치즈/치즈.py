# 역으로 생각해서 가장자리부터 시작해서 빈 곳에 대해 bfs를 하면 된다.
# 가장자리에는 치즈가 없으므로, 가장자리부터 bfs를 해서 치즈가 없는 부분을 도려낸 뒤,
# 치즈와 접한 부분을 flag 세우고 시간마다 삭제하는 방식.
from collections import deque

h, w = map(int, input().split())
arr = []
total_cheeze = 0
d = [(0,1),(1,0),(0,-1),(-1,0)]

time = 0

for i in range(h):
    lst = list(map(int, input().split()))
    total_cheeze += sum(lst)
    arr.append(lst)
    
while total_cheeze > 0:
    visited = [[0]*w for _ in range(h)]
    q = deque()
    q.append((0,0)) # 가장자리는 항상 치즈가 없기 때문에
    visited[0][0] = 1
    delete_cheeze = []
    
    while q:
        x,y = q.popleft()
        for dx, dy in d:
            nx, ny = x+dx, y+dy
            if 0<=nx<w and 0<=ny<h and not visited[ny][nx] and arr[ny][nx] == 0:
                visited[ny][nx] = 1
                q.append((nx,ny))
            if 0<=nx<w and 0<=ny<h and not visited[ny][nx] and arr[ny][nx] == 1:
                visited[ny][nx] = 1
                delete_cheeze.append((nx,ny))
    # 한시간 뒤
    total_cheeze -= len(delete_cheeze)
    for x, y in delete_cheeze:
        arr[y][x] = 0
    time += 1
    
print(time)
print(len(delete_cheeze))