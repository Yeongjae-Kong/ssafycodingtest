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

# nxn 보드, 각 칸의 값은 디저트 종류
# 대각선 방향으로 움직이고 사각형 모양을 그리며 출발한 카페로 돌아와야 한다.
# 최대한 많이 먹고 오려고 한다.
# 왔던 길 다시 못감
# 같은 숫자의 디저트를 팔고 있는 카페가 있으면 안된다.
# 임의의 한 카페에서 출발해 대각선 방향으로 움직이고 서로 다른 디저트를 먹으면서 사각형 모양을 그리며 출발점으로 돌아오는 경우
# 디저트를 가장 많이 먹을 수 있는 경로를 찾고, 그 때의 디저트 수를 출력
# 그 경우 없으면 -1

dx = [1,1,-1,-1]
dy = [-1,1,1,-1]

def DFS(L, dir,x,y,visited): # 현재 방향 dir도 함께 넣음. 현재 방향 기준 직진(방향 유지), 좌회전을 해야하기 때문.
    global res
    if dir > 3:
        return
    if dir == 3 and (i,j) == (x,y):
        res = max(res, L)
    else:
        for d in range(2):
            direction = (dir + d) % 4
            nx = x + dx[direction]
            ny = y + dy[direction]
            if 0<=nx<n and 0<=ny<n and lst[nx][ny] not in visited:
                visited.append(lst[nx][ny])
                DFS(L+1, dir + d, nx,ny,visited)
                visited.pop()

T = int(input())
for t in range(1,T+1):
    n = int(input())
    lst = [list(map(int, input().split())) for _ in range(n)]

    res = -1
    for i in range(n):
        for j in range(n):
            DFS(0,0,i,j,[]) # 이 문제의 경우는 방문을 값을 통해 하기 때문에 리스트를 파라미터로 넘겨줌

    print(f"#{t} {res}")