n = int(input())

# 0 가로, 1 대각선, 2 세로
# dp[r][c][direction] = r,c에 도달하는 파이프의 direction
dp = [[[0]*3 for _ in range(n)] for _ in range(n)]
lst = []

for _ in range(n):
    lst.append(list(map(int, input().split())))

# 시작 위치 (0, 1)에 가로 방향 파이프 1개
dp[0][1][0] = 1

for r in range(n):
    for c in range(2, n):
        if lst[r][c] == 1:
            continue
        
        # 가로 파이프
        # 이전 위치(r, c-1)가 가로 or 대각선 파이프였을 때
        dp[r][c][0] = dp[r][c-1][0] + dp[r][c-1][1]

        # 세로 파이프
        # 이전 위치(r-1, c)가 세로 or 대각선 파이프였을 때
        dp[r][c][2] = dp[r-1][c][2] + dp[r-1][c][1]
        
        # 대각선 파이프
        # 이전 위치(r-1, c-1)가 모든 방향 가능,
        # 그리고 현재 위치와 그 위, 왼쪽이 모두 벽이 아닐 때
        if lst[r-1][c] == 0 and lst[r][c-1] == 0:
            dp[r][c][1] = dp[r-1][c-1][0] + dp[r-1][c-1][1] + dp[r-1][c-1][2]

print(sum(dp[n-1][n-1]))