N = int(input())
ans = [0]*(N+1)
l, r = 1, N
val = 1
while l <= r:
    ans[l] = val; val += 1
    if l == r: break
    ans[r] = val; val += 1
    l += 1; r -= 1

print(*ans[1:])