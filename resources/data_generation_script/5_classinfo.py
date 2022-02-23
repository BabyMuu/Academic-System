from random import choice,randint
dy_list = [19830509,
           19840105,
           19840201,
           19840209,
           19860006,
           19860209,
           19870002,
           19920103,
           19930202,
           19930405,
           19940102
           ]
class_name = [
    ['计类', 0],
    ["通信", 0],
    ["数科", 0]
]
for i in range(len(dy_list)):
    grade = randint(2019,2021)
    daoyuan = choice(dy_list)
    zhuanye_rand = randint(0,2)
    zhuanye = class_name[zhuanye_rand][0]
    class_name[zhuanye_rand][1] += 1
    class_num = class_name[zhuanye_rand][1]
    dy_list.remove(daoyuan)
    sql =f"insert into classinfo (clsName, grade, teaNum)\
        values('{zhuanye}{class_num}班', '2020', '{daoyuan}')"
    print(sql)