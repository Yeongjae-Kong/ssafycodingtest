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

# 테스트 케이스 실행
T = int(input())
for tc in range(1, T + 1):
    n, m, k = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(n)]
    # (x, y) 좌표에 존재하는 세포의 정보를 딕셔너리로 관리
    cells = {}
    # 초기 세포 정보 입력
    for r in range(n):
        for c in range(m):
            hp = arr[r][c]
            if hp > 0:
                # 초기 위치를 중심으로 확장되는 것을 고려하여 좌표를 이동
                # K시간 동안 최대 K칸 확장될 수 있으므로 (k, k)만큼 여유를 줌
                x, y = c + k, r + k
                cells[(x, y)] = {'hp': hp, 'status': 0, 'life': hp}

    # K 시간 동안 시뮬레이션
    for _ in range(k):
        # 현재 시간에 번식할 세포들을 저장할 임시 딕셔너리, key: (x, y), value: hp
        dic = defaultdict(int)
        
        # 번식 후 상태가 변할 세포들을 저장
        next_states = {}

        # 모든 세포의 상태를 확인하고 갱신
        for (x, y), cell in cells.items():
            if cell['status'] == 0:
                cell['life'] -= 1
                if cell['life'] == 0:
                    # 비활성 -> 활성
                    next_states[(x, y)] = {
                        'hp': cell['hp'],
                        'status': 1,
                        'life': cell['hp']
                    }
            elif cell['status'] == 1:
                # 활성화된 첫 1시간 동안 번식
                if cell['life'] == cell['hp']:
                    dx = [0, 0, 1, -1]
                    dy = [1, -1, 0, 0]
                    for i in range(4):
                        nx, ny = x + dx[i], y + dy[i]
                        
                        # 번식하려는 위치가 비어있다면
                        if (nx, ny) not in cells:
                            # 번식 후보에 추가. 같은 위치에 더 높은 hp가 있다면 갱신
                            if dic[(nx, ny)] < cell['hp']:
                                dic[(nx, ny)] = cell['hp']
                cell['life'] -= 1
                if cell['life'] == 0:
                    # 활성 -> 죽음 (-1)
                    next_states[(x, y)] = {'hp': cell['hp'], 'status': -1, 'life': 0}
        # 모든 cell 확인 후 cells 상태 갱신
        for pos, next in next_states.items():
            cells[pos] = next

        # 번식한 세포 추가
        for (x, y), hp in dic.items():
            # 활성화된 세포와 번식 후보가 겹칠 수 있으므로, 이미 다른 세포가 차지하고 있는지 확인
            if (x, y) not in cells:
                cells[(x, y)] = {
                    'hp': hp,
                    'status': 0,
                    'life': hp
                }
    ans = 0
    for cell in cells.values():
        if cell['status'] != -1:
            ans += 1
    print(f"#{tc} {ans}")