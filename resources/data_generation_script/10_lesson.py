lesson = """1	Java	Java	4	56
2	python	python	3	48
3	C	C	3	48
4	C++	C++	4	56
5	高数	高等数学	3	45
6	英语	英语	1	48"""

lesson_true = []
lesson = lesson.split('\n')
for e in lesson:
    lesson_true.append(e.split('\t'))
print(lesson_true)
print(len(lesson_true))
for i in range(len(lesson_true)):
    sql = f"insert into lesson(lesname,context, score, hours) values ('{lesson_true[i][1]}','{lesson_true[i][2]}',{lesson_true[i][3]},{lesson_true[i][4]})"
    print(sql)
