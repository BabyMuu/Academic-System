import json
with open("01.json", mode='rt', encoding="utf-8") as f:
    tea_list = json.load(f)
import random

def get_sql(lesid, cla_num):
    tea_num = random.choice(tea_list)
    tea_list.remove(tea_num)
    sql = f"insert into coursearrangement(lesid, teanum, clsid, year, semester)" \
          f" values({lesid},'{tea_num}', {cla_num}, 2020, 2)"
    print(sql)
for i in range(2):
    tea_num = random.choice(tea_list)
    tea_list.remove(tea_num)
    if i:
        lesid = 1
    else:
        lesid = 3
    get_sql(lesid, 40)
    get_sql(lesid, 43)
    get_sql(lesid, 48)
    # get_sql(lesid, 44)
    # get_sql(lesid, 45)
    # get_sql(lesid, 46)



with open("01.json", "wt", encoding="utf-8") as f:
    json.dump(tea_list, f)