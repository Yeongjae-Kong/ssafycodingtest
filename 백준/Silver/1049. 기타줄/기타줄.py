n, m = map(int, input().split())

set_lst = []
lst = []

for _ in range(m):
    a, b = map(int,input().split())
    set_lst.append(a)
    lst.append(b)

set_lst.sort()
lst.sort()

ans = 0

tmp = n//6
mod = n%6

if tmp*set_lst[0]+mod*lst[0] > (tmp+1)*set_lst[0]:
    print((tmp+1)*set_lst[0])
else:
    if n >= 6:
        if set_lst[0] / 6 > lst[0]:
            ans += lst[0]*n
        else:
            ans += set_lst[0]*tmp
            ans += lst[0]*mod
    else:
        ans = lst[0] * n
    print(ans)