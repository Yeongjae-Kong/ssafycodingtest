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

def check_runway(l, x):
    n = len(l)
    visited = [False] * n

    for i in range(1, n):
        # 높이 차이가 1을 초과하면 불가
        if abs(l[i] - l[i-1]) > 1:
            return False

        if l[i] - l[i-1] == 1:
            # x개 평지 확인
            temp = 0
            for j in range(x):
                # 경사로 놓을 위치의 인덱스 확인
                temp_index = i - 1 - j
                # 인덱스 범위를 벗어나거나, 높이가 다르거나, 이미 경사로가 있다면 불가
                if temp_index < 0 or l[temp_index] != l[i-1] or visited[temp_index]:
                    return False
                visited[temp_index] = True

        elif l[i] - l[i-1] == -1:
            temp = 0
            for j in range(x):
                temp_index = i + j
                if temp_index >= n or l[temp_index] != l[i] or visited[temp_index]:
                    return False
                visited[temp_index] = True
    
    return True

t = int(input())
for testcase in range(t):
    n, x = map(int, input().split())
    lst = [list(map(int, input().split())) for _ in range(n)]

    cnt = 0
    for l in lst:
        if check_runway(l, x):
            cnt += 1
    
    lst_rotate = list(zip(*lst))
    for l in lst_rotate:
        if check_runway(list(l), x):
            cnt += 1
            
    print("#{} {}".format(testcase+1, cnt))