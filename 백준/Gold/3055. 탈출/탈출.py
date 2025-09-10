from collections import deque
d = [(0,1),(1,0),(0,-1),(-1,0)]
r, c = map(int, input().split())

lst = []

for i in range(r):
    lst.append(list(input()))
    
waterlst = deque()
for i in range(r):
    for j in range(c):
        if lst[i][j] == "S":
            s_idx = (i, j)
        if lst[i][j] == "*":
            waterlst.append((i,j))
            
visited = [[0]*c for _ in range(r)]

q = deque()
y,x = s_idx
q.append((x,y,0))

ans = 0

while q:
    # 물 먼저
    for _ in range(len(waterlst)):
        wy, wx = waterlst.popleft()
        for dx, dy in d:
            nwx, nwy = wx+dx, wy+dy
            if 0<=nwx<c and 0<=nwy<r and lst[nwy][nwx] == ".":
                lst[nwy][nwx] = "*"
                waterlst.append((nwy,nwx))
    # 도치 이동
    check = 0
    for _ in range(len(q)):
        x,y,cnt = q.popleft()
        lst[y][x] = "." # 원복
        visited[y][x] = 1
        for dx, dy in d:
            nx, ny = x+dx, y+dy
            if 0<=nx<c and 0<=ny<r and lst[ny][nx] == "." and not visited[ny][nx]:
                lst[ny][nx] = "S"
                q.append((nx,ny,cnt+1))
            if 0<=nx<c and 0<=ny<r and lst[ny][nx] == "D":
                check = 1
    if check:
        ans = cnt+1
        break
if ans == 0:
    print("KAKTUS")
else:
    print(ans)