n = int(input())

arr = list(map(int, input().split()))

dp = [0]*(n)
dp[-1] = 1

for i in range(n-1, -1, -1):
    if arr[i] == 0:
        dp[i] = 1
    else:
        dp[i] = 1
        break
for i in range(n-1, -1, -1):
    if i+arr[i]+1 <= n-1:
        dp[i] = dp[i+arr[i]+1]+1
    else:
        dp[i] = 1
print(*dp)