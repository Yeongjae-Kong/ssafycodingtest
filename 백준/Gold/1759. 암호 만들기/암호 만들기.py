from itertools import combinations

L, C = map(int, input().split())
lst = list(input().split())
lst.sort()
vowel = ['a', 'e', 'i', 'o', 'u']
comb = list(combinations(lst, L))

for pw in comb:
    v_cnt = 0
    for i in pw:
        if i in vowel:
            v_cnt += 1
    if 1 <= v_cnt <= L - 2:
        print(''.join(pw))