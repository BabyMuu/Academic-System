from random import choice,randint
"""获取名字"""
from faker import Faker
f_name = Faker('zh_cn')
"""获取职位"""
title_list = ['助教','讲师','副教授','教授']
def get_title():
    return choice(title_list)
"""获取生日"""
def get_btd():
    year = randint(1980,1995)
    mouth = randint(1,12)
    if mouth == 2 :
        day = randint(1,28)
    else:
        day = randint(1,30)
    return str(year)+"-"+str(mouth)+"-"+str(day), year
"""获取省份"""
province_dic = {
 '北京': '华北',
 '上海': '华东',
 '天津': '华北',
 '重庆': '西南',
 '河北': '华北',
 '山西': '华北',
 '河南': '华中',
 '辽宁': '东北',
 '吉林': '东北',
 '黑龙江': '东北',
 '内蒙古': '华北',
 '江苏': '华东',
 '山东': '华东',
 '安徽': '华东',
 '浙江': '华东',
 '福建': '华东',
 '钓鱼岛': '华南',
 '湖北': '华中',
 '湖南': '华中',
 '广东': '华南',
 '广西': '华南',
 '江西': '华东',
 '四川': '西南',
 '海南': '华南',
 '贵州': '西南',
 '云南': '西南',
 '陕西': '西北',
 '甘肃': '西北',
 '青海': '西北',
 '宁夏': '西北',
 '新疆': '西北',
 '香港': '华南',
 '台湾': '华东',
 '澳门': '华南',
 '西藏': '西南',
 '港澳': '华南'
}
province_list =[key for key in province_dic]
def get_province():
    return choice(province_list)

daoyuan_list = []
year_dic = {}
for i in range(1,100):
    title = get_title()
    # 生成职称
    if title == '助教':
        if randint(1,100) > 50 and len(daoyuan_list) < 5:
            type_s = "导员"
        elif randint(1,100) > 99:
            type_s = '领导'
        else:
            type_s = '教师'
    else:
        if randint(1, 100) > 95:
            type_s = '领导'
        else:
            type_s = '教师'
    """获取生日"""
    btd, year = get_btd()
    """获取专业类别"""
    department = "0" + str(randint(0,5))
    if year not in year_dic:
        year_dic[year] = 1
    else:
        year_dic[year] +=1
    if year_dic[year] < 10:
        n_id = '0' + str(year_dic[year])
    else:
        n_id = str(year_dic[year])
    #       入职年份    专业类别    序号
    num = str(year) + department + n_id
    sql = f"insert into teacher (teanum, teaName, teatitle, teatypeid, teabtd, pwd, static) \
        values('{num}','{f_name.name()}','{title}','{type_s}','{btd}',1,1)"
    print(sql)