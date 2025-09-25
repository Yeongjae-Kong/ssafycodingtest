T = int(input())

for _ in range(T):
    n, k, t, m = map(int, input().split())

    scores = [[0] * (k + 1) for _ in range(n + 1)]
    submit_counts = [0] * (n + 1)
    last_submit_time = [0] * (n + 1)

    for time_stamp in range(m):
        team_id, problem_id, score = map(int, input().split())
        
        if score > scores[team_id][problem_id]:
            scores[team_id][problem_id] = score
        
        submit_counts[team_id] += 1
        last_submit_time[team_id] = time_stamp

    final_scores = [0] * (n + 1)
    for i in range(1, n + 1):
        final_scores[i] = sum(scores[i])

    team_data = []
    for i in range(1, n + 1):
        team_data.append((final_scores[i], submit_counts[i], last_submit_time[i], i))

    team_data.sort(key=lambda x: (-x[0], x[1], x[2]))

    for rank, data in enumerate(team_data):
        if data[3] == t:
            print(rank + 1)
            break