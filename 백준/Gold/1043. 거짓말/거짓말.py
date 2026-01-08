# 두번째 줄에 있는 사람과 연결된 모든 사람이 있는 파티를 피해야.

n, m = map(int, input().split())

true_lst = list(map(int, input().split()))
true_lst.pop(0)

party = [] # 두번째 loop용

for _ in range(m):
    party_lst = list(map(int, input().split()))
    party_lst.pop(0)
    check = 0
    for i in range(len(party_lst)):
        if party_lst[i] in true_lst:
            check = 1 # 진실을 아는 사람이 있다
            break
    if check == 1: # 이 파티에 진실을 아는 사람이 있으면 true lst에 추가
        for i in range(len(party_lst)):
            if party_lst[i] not in true_lst: # true lst에 없는 사람만 추가
                true_lst.append(party_lst[i])
    party.append(party_lst)

for _ in range(len(party)):
    for i in range(len(party)):
        check = 0
        for j in party[i]:
            if j in true_lst:
                check = 1
                break
        if check == 1:
            for j in party[i]:
                if j not in true_lst:
                    true_lst.append(j)
    tmp = party.pop(0)
    party.append(tmp)
    
# loop 한번 돌리면 연결된 모든 사람이 true lst에 들어있음
ans = 0
for i in range(len(party)):
    check = 0
    for people in party[i]:
        if people in true_lst:
            check = 1
    if check == 0: # 아는 사람이 아무도 없으면 ans+1
        ans += 1

print(ans)