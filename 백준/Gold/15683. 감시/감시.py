import copy

N, M = map(int, input().split())
cctv, maps = [], []
modes = [[], [[0], [1], [2], [3]], [[0, 2], [1, 3]],
         [[0, 1], [1, 2], [2, 3], [0, 3]],
         [[0, 1, 2], [0, 1, 3], [1, 2, 3], [0, 2, 3]], [[0, 1, 2, 3]]]

# 북 동 남 서
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

# maps와 cctv 설정
# cctv = [[값, x좌표, y좌표]] 형태

# def fill(maps, mode, x, y): cctv가 보는 방향의 map을 -1로 채우는 함수.
# for m in mode, while loop 돌면서 maps[nx][ny]가 맵 크기보다 크거나 6을 만나면 break

# def dfs(depth, maps): # depth가 cctv 배열 크기와 같아지면 map을 돌며 count를 센 뒤 기존 값과 비교해 min값 return.
# 주의할 것은 maps를 copy.deepcopy(maps)로 받아와야 함.

# dfs(0, maps)

for i in range(N):
  data = list(map(int, input().split()))
  maps.append(data)
  for j in range(M):
    if data[j] != 0 and data[j] != 6:
      cctv.append([data[j], i, j])

def fill(maps, mode, x, y):
  for m in mode:  # Ex) m : 0, 2
    nx, ny = x, y # nx, ny는 x, y를 기준으로 변화해야 되기 때문에 while문 안에 X
    while True:
      nx += dx[m]
      ny += dy[m]
      if not (0 <= nx < N and 0 <= ny < M):
        break
      if maps[nx][ny] == 6:
        break
      if maps[nx][ny] == 0:
        maps[nx][ny] = -1

def dfs(depth, maps):
  global ans
  if depth == len(cctv):
    cnt = 0
    for i in range(N):
      cnt += maps[i].count(0)
    ans = min(ans, cnt)
    return

  temp = copy.deepcopy(maps)
  cctv_num, x, y = cctv[depth]
  for mode in modes[cctv_num]:  # Ex) mode : [0, 2], [1, 3]
    fill(temp, mode, x, y)
    dfs(depth + 1, temp)
    temp = copy.deepcopy(maps)

ans = int(1e9)
dfs(0, maps)
print(ans)