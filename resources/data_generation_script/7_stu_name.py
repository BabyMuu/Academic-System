import random

def random_name():
    # 删减部分，比较大众化姓氏
    firstName = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻水云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳鲍史唐费岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅卞齐康伍余元卜顾孟平" \
                "黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计成戴宋茅庞熊纪舒屈项祝董粱杜阮席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田胡凌霍万柯卢莫房缪干解应宗丁宣邓郁单杭洪包诸左石崔吉" \
                "龚程邢滑裴陆荣翁荀羊甄家封芮储靳邴松井富乌焦巴弓牧隗山谷车侯伊宁仇祖武符刘景詹束龙叶幸司韶黎乔苍双闻莘劳逄姬冉宰桂牛寿通边燕冀尚农温庄晏瞿茹习鱼容向古戈终居衡步都耿满弘国文东殴沃曾关红游盖益桓公晋楚闫"
    # 百家姓中双姓氏
    firstName2 = "万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于单于太叔申屠公孙仲孙轩辕令狐钟离宇文长孙慕容鲜于闾丘司徒司空亓官司寇仉督子颛孙端木巫马公西漆雕乐正壤驷公良拓跋夹谷宰父谷梁段干百里东郭南门呼延羊舌微生梁丘左丘东门西门南宫南宫"
    # 女孩名字
    girl = '秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽'
    # 男孩名字
    boy = '伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘'
    # 名
    name = '中笑贝凯歌易仁器义礼智信友上都卡被好无九加电金马钰玉忠孝'
    # 10%的机遇生成双数姓氏
    if random.choice(range(100)) > 10:
        firstName_name = firstName[random.choice(range(len(firstName)))]
    else:
        i = random.choice(range(len(firstName2)))
        firstName_name = firstName2[i:i + 2]

    sex = random.choice(range(2))
    name_1 = ""
    # 生成并返回一个名字
    if sex > 0:
        girl_name = girl[random.choice(range(len(girl)))]
        if random.choice(range(2)) > 0:
            name_1 = name[random.choice(range(len(name)))]
        return firstName_name + name_1 + girl_name, 0
    else:
        boy_name = boy[random.choice(range(len(boy)))]
        if random.choice(range(2)) > 0:
            name_1 = name[random.choice(range(len(name)))]
        return firstName_name + name_1 + boy_name, 1

def get_btd_year():
    gailv = random.randint(1, 100)

    if gailv > 99:
        return "1999"
    elif gailv > 90:
        return "2000"
    elif gailv > 80:
        return "2001"
    elif gailv > 3:
        return "2002"
    else:
        return "2003"

def get_mouth_day():
    mouth = random.randint(1, 12)
    if mouth == 2:
        day = random.randint(1, 28)
    else:
        day = random.randint(1, 30)
    return f'-{mouth}-{day}'

class_list = {40: ['070301',0], 38: ['070101',0], 47: ['070102',0], 43: ['070302',0],
              48: ['070303',0], 39: ['070201',0], 41: ['070202',0], 42: ['070203',0],
              44: ['070204',0], 45: ['070205',0], 46: ['070206',0]}


def get_stuid(clsid):
    four = "2020"
    five_to_ten = class_list[clsid][0]
    class_list[clsid][1] += 1
    if class_list[clsid][1] < 10:
        eleven_to_twelve = "0" + str(class_list[clsid][1])
    else:
        eleven_to_twelve = str(class_list[clsid][1])
    return four + five_to_ten + eleven_to_twelve
def get_sql(stuid, clsid, stuname, stubtd, stusex):
    sql = f"insert into student (stunum,clsid, stuname, stubtd, pwd, static, sex)\
    	    values({stuid},{clsid},'{stuname}','{stubtd}',1,1,{stusex})"
    return sql
# index = 0
# for i in range(384):
#     stu_name, stu_sex = random_name()
#     clsid = 38 + index
#     index += 1
#     if index == 11:
#         index = 0
#     stu_btd = get_btd_year() + get_mouth_day()
#     stu_id = get_stuid(clsid)
#     sq = get_sql(stu_id,clsid, stu_name, stu_btd, stu_sex)
#     print(sq)


for i in range(35):
    stu_name, stu_sex = random_name()
    clsid = 47
    stu_btd = get_btd_year() + get_mouth_day()
    stu_id = get_stuid(clsid)
    sq = get_sql(stu_id, clsid, stu_name, stu_btd, stu_sex)
    print(sq)
