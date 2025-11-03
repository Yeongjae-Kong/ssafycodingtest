def solution(n, results):
    answer = 0
    graph = [[0] * n for _ in range(n)]
    
    for a, b in results: 
        graph[a-1][b-1] = 1
    print(graph)
    for k in range(n):
        for i in range(n):
            for j in range(n):
                if graph[i][j] == 0 and (graph[i][k] and graph[k][j]):
                    graph[i][j] = 1 # 경로 갱신 
    for i in range(n):
        tmp = 0
        for j in range(n):
            tmp += graph[i][j]
            tmp += graph[j][i]
        if tmp == n-1:
            answer+=1

    return answer