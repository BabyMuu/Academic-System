exam = """2	3	2020/6/30	0	计类C语言考试
4	6	2020/7/1	0	计类英语考试
6	3	2020/6/30	0	通信1,2班 C语言考试
7	3	2020/6/30	0	通信3,4班 C语言考试
8	3	2020/6/30	0	通信5,6班 C语言考试
9	6	2020/7/1	0	通信1,2英语考试
10	6	2020/7/1	0	通信3,4英语考试
11	6	2020/7/1	0	通信5,6英语考试
12	6	2020/7/1	0	数科1,2英语考试
13	6	2020/7/1	0	数科3英语考试
14	5	2020/7/2	0	数科3高数考试
15	5	2020/7/2	0	数科1,2高数考试"""

exam_true = []
exam = exam.split('\n')
for e in exam:
    exam_true.append(e.split('\t'))
print(exam_true)
print(len(exam_true))
for i in range(len(exam_true)):
    sql = f"insert into examschedule(esid,caid, examdate, static, context) values ({exam_true[i][0]},{exam_true[i][1]},'{exam_true[i][2]}',{exam_true[i][3]},'{exam_true[i][4]}')"
    print(sql)
