# 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
# 아래 표준 입출력 예제 필요시 참고하세요.

# 표준 입력 예제
'''
a = int(input())                        정수형 변수 1개 입력 받는 예제
b, c = map(int, input().split())        정수형 변수 2개 입력 받는 예제 
d = float(input())                      실수형 변수 1개 입력 받는 예제
e, f, g = map(float, input().split())   실수형 변수 3개 입력 받는 예제
h = input()                             문자열 변수 1개 입력 받는 예제
'''

# 표준 출력 예제
'''
a, b = 6, 3
c, d, e = 1.0, 2.5, 3.4
f = "ABC"
print(a)                                정수형 변수 1개 출력하는 예제
print(b, end = " ")                     줄바꿈 하지 않고 정수형 변수와 공백을 출력하는 예제
print(c, d, e)                          실수형 변수 3개 출력하는 예제
print(f)                                문자열 1개 출력하는 예제
'''




'''
아래의 구문은 input.txt 를 read only 형식으로 연 후,
앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
아래 구문을 이용하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 구문을 사용하셔도 좋습니다.
아래 구문을 사용하기 위해서는 import sys가 필요합니다.
단, 채점을 위해 코드를 제출하실 때에는 반드시 아래 구문을 지우거나 주석 처리 하셔야 합니다.
'''
#import sys
#sys.stdin = open("input.txt", "r")

def check(D, W, K, film): # 모든 셀이 검사(k개 이상 연속)를 만족하는지?
    for j in range(W):
        count = 0
        prev = -1
        is_pass = False
        for i in range(D):
            if film[i][j] == prev:
                count += 1
            else:
                prev = film[i][j]
                count = 1
            if count >= K:
                is_pass = True
                break
        if not is_pass: # 하나라도 실패하면 false
            return False
    return True # 모두 통과하면 return True

def backtrack(depth, drug_count, D, W, K, film):
    global min_drug_count
    if drug_count >= min_drug_count: # 현재 투입횟수가 지금까지 계산된 최소 투입 횟수보다 크면 볼 필요없으므로 return (가지치기)
        return
    if depth == D:
        if check(D, W, K, film): # 전부 k개 이상 연속적인 셀이 있으면
            min_drug_count = min(min_drug_count, drug_count) # min_count와 비교해서 더 작으면 min값 갱신. 
        return

    backtrack(depth + 1, drug_count, D, W, K, film)

    original_row = film[depth][:]
    film[depth] = [0] * W
    backtrack(depth + 1, drug_count + 1, D, W, K, film)
    
    film[depth] = [1] * W
    backtrack(depth + 1, drug_count + 1, D, W, K, film)
    film[depth] = original_row

T = int(input())
for tc in range(1, T + 1):
    D, W, K = map(int, input().split())
    film = [list(map(int, input().split())) for _ in range(D)]

    min_drug_count = D
    if check(D, W, K, film):
        min_drug_count = 0
    else:
        backtrack(0, 0, D, W, K, film)
    
    print(f"#{tc} {min_drug_count}")