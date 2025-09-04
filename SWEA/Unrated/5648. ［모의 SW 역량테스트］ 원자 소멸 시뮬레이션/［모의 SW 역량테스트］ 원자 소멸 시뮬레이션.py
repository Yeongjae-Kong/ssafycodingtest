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

from collections import defaultdict

t = int(input())

for test_case in range(t):
    n = int(input())
    atoms = []
    for _ in range(n):
        x, y, d, k = map(int, input().split())
        atoms.append([d, x*2, y*2, k])

    ans = 0

    for _ in range(4002):
        if not atoms:
            break

        # 1. 모든 원자들을 한 칸씩 이동시킨 후 딕셔너리에 저장
        dic = defaultdict(list)
        for i in range(len(atoms)):
            d, x, y, k = atoms[i]
            if d == 0:
                y += 1
            elif d == 1:
                y -= 1
            elif d == 2:
                x -= 1
            elif d == 3:
                x += 1
            
            # 맵을 벗어난 원자는 무시
            if -2000 <= x <= 2000 and -2000 <= y <= 2000:
                dic[(x, y)].append([d, x, y, k])

        # 2. 충돌 여부 확인 및 다음 단계 원자 리스트 생성
        next_atoms = []
        for pos, atom_list in dic.items():
            if len(atom_list) > 1:
                # 충돌 발생: 모든 원자의 에너지 합산
                for atom in atom_list:
                    ans += atom[3]
            else:
                # 충돌 없음: 다음 단계 리스트에 추가
                next_atoms.extend(atom_list)

        atoms = next_atoms
    
    print(f"#{test_case+1} {ans}")