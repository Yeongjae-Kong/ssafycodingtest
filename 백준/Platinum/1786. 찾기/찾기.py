T = list(input())
P = list(input())

#  LPS(가장 긴 접두사이자 접미사) 계산
def get_lps(pattern):
    m = len(pattern)
    lps = [0] * m
    length = 0
    i = 1

    while i < m:
        if pattern[i] == pattern[length]:
            length += 1
            lps[i] = length
            i += 1
        else:
            if length != 0:
                length = lps[length - 1]
            else:
                lps[i] = 0
                i += 1
    return lps

def kmp_search(text, pattern):
    n = len(text)
    m = len(pattern)
    lps = get_lps(pattern)
    
    positions = []
    i = 0  # 텍스트 인덱스
    j = 0  # 패턴 인덱스

    while i < n:
        if pattern[j] == text[i]:
            i += 1
            j += 1
        
        if j == m:
            # 패턴 전체가 매칭된 경우
            positions.append(i - j + 1)
            j = lps[j - 1]
        elif i < n and pattern[j] != text[i]:
            # 불일치 발생 시
            if j != 0:
                j = lps[j - 1]
            else:
                i += 1
    return positions

ans = kmp_search(T, P)
cnt = len(ans)

print(cnt)
if ans:
    print(*ans)