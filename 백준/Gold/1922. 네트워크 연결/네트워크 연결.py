# - 크루스칼 정리
# 모든 노드를 연결하는 최솟값 구할 때 사용. 문제 예시) 다리 연결, 인터넷 연결 등
# weight - 출발 - 도착점 순으로 edge 설정
# weight를 오름차순으로 정렬하고 각 노드가 자기 자신을 가리키게 parents 초기화.
# find()와 union() 구현
# 모든 edge에 대해 loop 돌면서
# 만약 대표자가 같으면, 사이클이 생기므로 continue
# 대표자가 다르면, 집합을 합치고 간선 선택 후 weight 업데이트.
# 간선이 N-1개가 되면, 모두 연결한것이므로 break.
import sys

input = sys.stdin.readline

def find(parent, x):
    if parent[x] != x:
        parent[x] = find(parent, parent[x]) # 경로 압축
    return parent[x]

def union(parent, a, b):
    a = find(parent, a)
    b = find(parent, b)
    # 두 원소의 대표자가 다를 경우에만 합침
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

N = int(input())  # 컴퓨터의 수 (정점의 수)
M = int(input())  # 연결할 수 있는 선의 수 (간선의 수)

parent = list(range(N + 1))

edges = []
for _ in range(M):
    a, b, cost = map(int, input().split())
    # 비용을 기준으로 정렬하기 위해 튜플의 첫 번째 원소로 비용을 저장
    edges.append((cost, a, b))

# --- 크루스칼 수행 ---
edges.sort()

total_cost = 0  # 최소 총비용 저장
edge_count = 0  # 선택된 간선의 수 저장

for cost, a, b in edges:
    # 대표자 같은지 확인해서 사이클 발생 여부 체크
    if find(parent, a) != find(parent, b):
        # 대표자가 다르다면 사이클이 발생하지 않으므로 집합 합치고 간선 채택
        union(parent, a, b)
        total_cost += cost
        edge_count += 1
        if edge_count == N - 1:
            break

print(total_cost)