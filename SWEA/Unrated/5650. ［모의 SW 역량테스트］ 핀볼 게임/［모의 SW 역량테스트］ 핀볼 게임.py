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
#sys.stdin = open("input.txt", "r")

# 인덱스: 0(상), 1(하), 2(좌), 3(우)
directions = ((-1, 0), (1, 0), (0, -1), (0, 1))

def play(x, y, d_idx):
    score = 0
    startx, starty = x, y
    
    while True:
        y += directions[d_idx][0]
        x += directions[d_idx][1]

        if not (0<=y<n and 0<=x<n): # 맵 벗어나면 방향 바꾸기
            score+=1
            y -= directions[d_idx][0] # 좌표 원 위치
            x -= directions[d_idx][1]
            if d_idx == 0: d_idx = 1
            elif d_idx == 1: d_idx = 0
            elif d_idx == 2: d_idx = 3
            elif d_idx == 3: d_idx = 2
                
        if lst[y][x] == -1 or (x == startx and y == starty):
            return score
            
        # 블록(1~5) 만난 경우
        if 1 <= lst[y][x] <= 5:
            score += 1
            block_type = lst[y][x]

            if block_type == 1:
                if d_idx == 0: d_idx = 1
                elif d_idx == 1: d_idx = 3
                elif d_idx == 2: d_idx = 0
                elif d_idx == 3: d_idx = 2
            elif block_type == 2:
                if d_idx == 0: d_idx = 3
                elif d_idx == 1: d_idx = 0
                elif d_idx == 2: d_idx = 1
                elif d_idx == 3: d_idx = 2
            elif block_type == 3:
                if d_idx == 0: d_idx = 2
                elif d_idx == 1: d_idx = 0
                elif d_idx == 2: d_idx = 3
                elif d_idx == 3: d_idx = 1
            elif block_type == 4:
                if d_idx == 0: d_idx = 1
                elif d_idx == 1: d_idx = 2
                elif d_idx == 2: d_idx = 3
                elif d_idx == 3: d_idx = 0
            elif block_type == 5:
                if d_idx == 0: d_idx = 1
                elif d_idx == 1: d_idx = 0
                elif d_idx == 2: d_idx = 3
                elif d_idx == 3: d_idx = 2
            
        # 웜홀(6~10)을 만난 경우
        elif 6 <= lst[y][x] <= 10:
            for i in range(n):
                for j in range(n):
                    if lst[i][j] == lst[y][x] and (i, j) != (y, x):
                        y, x = i, j
                        break
                else:
                    continue
                break

T = int(input())
for tc in range(T):
    n = int(input())
    lst = [list(map(int, input().split())) for _ in range(n)]
    ans = 0
    for y in range(n):
        for x in range(n):
            if lst[y][x] == 0:
                for d_idx in range(4):
                    ans = max(ans, play(x, y, d_idx))
    
    print("#{} {}".format(tc + 1, ans))