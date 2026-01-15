sentence = list(input())
target = list(input())
i = 0
ans = 0
while i < len(sentence):
    check = 0
    for j in range(len(target)):
        if i+j < len(sentence) and sentence[i+j] == target[j]:
            check+=1
    if check == len(target):
        ans += 1
        i+= len(target)-1
    i+=1
print(ans)