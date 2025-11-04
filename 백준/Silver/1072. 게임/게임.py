def solution(X, Y):
    Z = (Y * 100) // X
    if Z >= 99:
        return -1

    left, right = 1, 1000000000
    answer = -1

    while left <= right:
        mid = (left + right) // 2
        newZ = ((Y + mid) * 100) // (X + mid)

        if newZ > Z:
            answer = mid
            right = mid - 1
        else:
            left = mid + 1

    return answer

X, Y = map(int, input().split())
print(solution(X, Y))
