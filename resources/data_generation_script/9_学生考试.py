import random

def get_grade():
    probability = random.randint(0, 100)
    if probability < 15:
        return random.randint(0, 59)
    elif probability < 40:
        return random.randint(60, 69)
    elif probability < 70:
        return random.randint(70, 79)
    elif probability < 85:
        return random.randint(80, 89)
    elif probability < 96:
        return random.randint(90, 95)
    else:
        return random.randint(96, 100)

from 学生编号 import sk_stu_num_3

print('--数科3班 英语考试')
for i in range(len(sk_stu_num_3)):
    esid = 13
    sql = f'insert into score(esid, stuNum, score) values ({esid}, {sk_stu_num_3[i]},{get_grade()})'
    print(sql)