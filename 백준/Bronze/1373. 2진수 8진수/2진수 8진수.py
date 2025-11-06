s = input()
n = len(s)
if n % 3 == 1:
    s = "00"+s
if n % 3 == 2:
    s = "0"+s
n = len(str(s))
answer = []
for i in range(0, n, 3):
    tmp = s[i:i+3]
    ans = int(tmp[0])*4+int(tmp[1])*2+int(tmp[2])*1
    answer.append(ans)
for i in answer:
    print(i, end="")