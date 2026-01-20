s=input().strip()
cnt_zero = s.count('0')//2
cnt_one = s.count('1')//2

s = s.replace('1', '', cnt_one)

s = s[::-1].replace('0', '', cnt_zero)[::-1]

print(s)