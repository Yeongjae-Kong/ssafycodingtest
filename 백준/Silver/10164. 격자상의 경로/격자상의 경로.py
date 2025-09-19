def solve(sx, sy, ex, ey):
    rows = ex - sx
    cols = ey - sy
    dp = [[0] * (cols + 1) for _ in range(rows + 1)]
    dp[0][0] = 1

    for i in range(rows + 1):
        for j in range(cols + 1):
            if i > 0:
                dp[i][j] += dp[i-1][j]
            if j > 0:
                dp[i][j] += dp[i][j-1]
    return dp[rows][cols]

n, m, k = map(int, input().split())

if k == 0:
    print(solve(0, 0, n - 1, m - 1))
else:
    k_row = (k - 1) // m
    k_col = (k - 1) % m

    path1 = solve(0, 0, k_row, k_col)
    path2 = solve(k_row, k_col, n - 1, m - 1)

    print(path1 * path2)