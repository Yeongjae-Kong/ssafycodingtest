from collections import deque

n = int(input())
lst = [list(input().strip()) for _ in range(n)]

# ----------------------------------------------------------
# 1️⃣ 출발점(#)과 도착점(#) 찾기
# ----------------------------------------------------------
points = []
for i in range(n):
    for j in range(n):
        if lst[i][j] == "#":
            points.append((j, i))  # (x, y)
sx, sy = points[0]
ex, ey = points[1]

# ----------------------------------------------------------
# 2️⃣ 초기 세팅
# ----------------------------------------------------------
d = [(0,1),(1,0),(0,-1),(-1,0)]  # 오른, 아래, 왼, 위
INF = 1e9
visited = [[[INF]*4 for _ in range(n)] for _ in range(n)]
q = deque()

# 출발점에서 4방향 모두 시작 가능
for i in range(4):
    visited[sy][sx][i] = 0
    q.append((sx, sy, 0, d[i]))  # x, y, ans, cur_d

# ----------------------------------------------------------
# 3️⃣ BFS 탐색
# ----------------------------------------------------------
while q:
    x, y, ans, cur_d = q.popleft()
    dx, dy = cur_d
    dir_idx = d.index(cur_d)

    nx, ny = x + dx, y + dy
    if not (0 <= nx < n and 0 <= ny < n): 
        continue
    if lst[ny][nx] == "*":
        continue

    # 다음 위치에서 이미 더 적은 거울 수로 방문했다면 스킵
    if visited[ny][nx][dir_idx] <= ans:
        continue

    visited[ny][nx][dir_idx] = ans

    # 도착점이면 정답 출력
    if (nx, ny) == (ex, ey):
        print(ans)
        break

    # 거울 설치 가능 지점이면 회전 고려
    if lst[ny][nx] == "!":
        for ndx, ndy in [(-dy, dx), (dy, -dx)]:  # 좌/우 회전
            ndir_idx = d.index((ndx, ndy))
            if visited[ny][nx][ndir_idx] > ans + 1:
                visited[ny][nx][ndir_idx] = ans + 1
                q.append((nx, ny, ans + 1, (ndx, ndy)))

    # 직진
    q.appendleft((nx, ny, ans, (dx, dy)))

# ----------------------------------------------------------
# 4️⃣ 종료 시점에서 정답 찾기 (혹시 break 안 걸렸을 경우)
# ----------------------------------------------------------
else:
    print(min(visited[ey][ex]))
