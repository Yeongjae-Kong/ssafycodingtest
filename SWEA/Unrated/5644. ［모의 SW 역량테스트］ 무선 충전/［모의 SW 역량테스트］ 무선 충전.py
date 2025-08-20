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
t = int(input())

d = [(0, 0), (0, -1), (1, 0), (0, 1), (-1, 0)]

for T in range(t):
    m, a = map(int, input().split())
    graph = [[[] for _ in range(11)] for _ in range(11)]
    move_a = list(map(int, input().split()))
    move_b = list(map(int, input().split()))
    for _ in range(a):
        x, y, c, p = map(int, input().split())
        for dx in range(-c, c+1):
            temp = c-abs(dx)
            for dy in range(-temp, temp+1):
                nx, ny = x+dx, y+dy
                if 1<=nx<=10 and 1<=ny<=10:
                    graph[ny][nx].append((c, p, y, x))
                    graph[ny][nx].sort(key=lambda x:x[1], reverse=True) # 정렬로 삽입
                    
    a = [1,1]
    b = [10,10]
    ans_a = 0
    ans_b = 0
    # 초기 위치(0초)부터 충전
    if graph[a[1]][a[0]]:
        ans_a = graph[a[1]][a[0]][0][1]
    if graph[b[1]][b[0]]:
        ans_b = graph[b[1]][b[0]][0][1]
        
    for idx, i in enumerate(zip(move_a, move_b)):
        a_i, b_i = i
        a_dx, a_dy = d[a_i]
        b_dx, b_dy = d[b_i]
        a[0]+=a_dx
        a[1]+=a_dy
        b[0]+=b_dx
        b[1]+=b_dy
        val_a = graph[a[1]][a[0]]
        val_b = graph[b[1]][b[0]]
        
        # ex) t=11처럼 , bc 겹쳤을 때 대처
        # a, b 둘 다 보고, a, b의 val이 같은 값이면, a와 b의 두번째값이 있는지,
        # 둘다있다면 어떤게 더 큰지 비교해서 정한 뒤 ans에 더하기
        if val_a:
            if val_b:
                if val_a[0][1] == val_b[0][1] and val_a[0][0] == val_b[0][0] and val_a[0][2] == val_b[0][2] and val_a[0][3] == val_b[0][3]: #두 max bc가 같으면
                    if len(val_a) >= 2 and len(val_b) >= 2: #둘다 두번째 값 있으면
                        if val_a[1][1] >= val_b[1][1]: # 두번째 값이 a가 더 크면, a에 두번째 넣어야 max됨.
                            ans_a+=val_a[1][1]
                            ans_b+=val_b[0][1] #b는 첫번째 값 삽입
                        else: # b가 더크면
                            ans_a+=val_a[0][1]
                            ans_b+=val_b[1][1]
                    elif len(val_a) >= 2: # a만 두개면
                        ans_a += val_a[1][1]
                        ans_b += val_b[0][1]
                    elif len(val_b) >= 2: # b만 두개면
                        ans_a += val_a[0][1]
                        ans_b += val_b[1][1]
                    else: #둘다 같은 bc 하나가 겹치면 그냥 a에 더해버림(반 나눠야하는데 어차피 a,b sum 구하는거니 계산 편하게)
                        ans_a+=val_a[0][1]
                else: #두 개의 max bc가 다르면 그냥 각각 더함
                    ans_a+=val_a[0][1]
                    ans_b+=val_b[0][1]
            else: #b가 아예 없으면 그냥 a 더함
                ans_a+=val_a[0][1]
        if val_b:
            if len(val_a) == 0: # a가 아예 없으면
                ans_b +=val_b[0][1] # 그냥 b 더함
            # a가 있는 경우는 위에서 했으므로 패스
            
        # print(idx+1)
        # print("ans a ", ans_a)
        # print("a의 좌표 x y ", a[0],a[1])
        # print("ans b ", ans_b)
        # print("b의 좌표 x y ", b[0],b[1], "----")
    ans = ans_a+ans_b
    print("#{} {}".format(T+1,ans))

# for i in graph:
#    print(i)