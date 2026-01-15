from collections import deque
import sys # [추가됨] 시간 초과 방지를 위해 추가

# [추가됨] 입력 속도 향상
input = sys.stdin.readline 

n = int(input())

lst = []
for _ in range(n):
    lst.append(list(map(int, input().split())))

visited = [[0]*n for _ in range(n)]
# q = deque() # [삭제됨] q는 함수 안에서 매번 새로 만드는 것이 안전합니다.

d = [(1,0),(0,1),(-1,0),(0,-1)]

def bfs(i,j,num):
    q = deque() # [수정됨] 로컬 변수로 이동
    q.append((i, j))
    visited[j][i] = num
    outline_lst = []
    
    while q:
        x, y = q.popleft()
        for dx, dy in d:
            nx, ny = x+dx, y+dy
            if 0<=nx<n and 0<=ny<n:
                # 육지이고 아직 방문 안 했으면 섬 번호 매기기
                if visited[ny][nx] == 0 and lst[ny][nx] == 1:
                    visited[ny][nx] = num
                    q.append((nx, ny))
                
                # [수정됨] 바다인 경우: 섬 번호를 매기면 안 됨! (visited 체크 X)
                # 바다는 그냥 가장자리 리스트에만 넣어야 함
                elif lst[ny][nx] == 0:
                    # 중복해서 큐에 넣는 것을 방지하기 위해 outline_lst에 이미 있는지 확인하거나
                    # 여기서 visited를 건드리면 안됩니다. 
                    # 다만, BFS 탐색 중 같은 바다를 여러 번 outline에 넣을 수 있으므로
                    # 여기서는 일단 넣고 나중에 BFS2 돌릴 때 처리합니다.
                    outline_lst.append((nx,ny,num)) 
    return outline_lst

def bfs2(start_x, start_y, current_island_num): # [수정됨] 인자 이름 명확화
    q = deque()
    q.append((start_x, start_y, 0))
    
    # [수정됨] BFS2를 돌 때마다 방문 기록을 초기화해야 함
    # 전역 visited를 쓰면 이전 BFS 기록 때문에 길이 막힘
    visited_bridge = [[False]*n for _ in range(n)]
    visited_bridge[start_y][start_x] = True
    
    while q:
        x, y, cnt = q.popleft()
        
        # [추가됨] 가지치기: 현재 답보다 길어지면 탐색 중단 (시간 최적화)
        if cnt >= ans: 
            continue

        for dx, dy in d:
            nx, ny = x+dx, y+dy
            if 0<=nx<n and 0<=ny<n:
                # 1. 다른 육지에 도착한 경우 (내 섬이 아닌 경우)
                if lst[ny][nx] == 1 and visited[ny][nx] != current_island_num:
                    return cnt + 1 # 다리 길이 반환
                
                # 2. 바다이고, 이번 탐색에서 아직 방문 안 한 경우
                elif lst[ny][nx] == 0 and not visited_bridge[ny][nx]:
                    visited_bridge[ny][nx] = True
                    q.append((nx, ny, cnt+1))
                    
    return 100000 # 도달 못한 경우 큰 값 반환

ans = 10000 # 전역 변수 초기화
num = 1
island_lst = []

# 섬 구분하기 (Labeling)
for i in range(n):
    for j in range(n):
        # i는 가로(x), j는 세로(y)로 쓰시는 스타일이므로 좌표 순서 유의
        if lst[j][i] == 1 and visited[j][i] == 0:
            # bfs 내부는 (x, y) 순서로 처리됨 -> (i, j) 전달
            outlinelst = bfs(i, j, num)
            num += 1
            island_lst.append(outlinelst)

# 다리 길이 구하기
# [수정됨] island_lst에는 각 섬의 '테두리 바다 좌표'들이 들어있음
for e in island_lst:
    for x, y, island_num in e:
        # 각 좌표에서 다리놓기 시도
        cnt = bfs2(x, y, island_num)
        if cnt < ans:
            ans = cnt

print(ans)