n, m = map(int, input().split())
lst = [list(map(int, input().split())) for _ in range(n)]
lst_col = []
for i in range(m):
    tmp=[]
    for j in range(n):
        tmp.append(lst[j][i])
    lst_col.append(tmp)
ans = 0
check_min = []
for idx, l in enumerate(lst):
    check_min.append([sum(l), idx, 0])
for idx, l in enumerate(lst_col):
    check_min.append([sum(l), idx, 1])
for idx in range(len(check_min)):
    temp_min = min(check_min) #(16, 1, 0)
    check_min.remove(temp_min)
    if ans < temp_min[0]:
        ans = temp_min[0]
    if temp_min[2] == 0: # row
        for i in range(len(check_min)):
            if check_min[i][2] == 1:
                check_min[i][0] -=  lst[temp_min[1]][check_min[i][1]] 
    else: # col
        for i in range(len(check_min)):
            if check_min[i][2] == 0:
                check_min[i][0] -=  lst[check_min[i][1]][temp_min[1]]
print(ans)