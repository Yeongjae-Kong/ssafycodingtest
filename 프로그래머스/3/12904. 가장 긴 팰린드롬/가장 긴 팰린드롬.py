def solution(s):
    ans = 0
    for i in range(len(s)):
        l, r = i, i+1
        tmp = 0
        while l >= 0 and r < len(s):
            if s[l]==s[r]:
                ans = max(ans, r-l+1)
                l -= 1
                r += 1
            else: break
        l, r = i, i
        tmp = 0
        while l >= 0 and r < len(s):
            if s[l]==s[r]:
                ans = max(ans, r-l+1)
                l -= 1
                r += 1
            else: break

    return ans

