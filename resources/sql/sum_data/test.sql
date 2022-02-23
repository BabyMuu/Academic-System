select * from student where clsid = 48
select * from student where clsid in (40,43)
select * from student where stunum like '2020070101__'
select count(*) stu from student

select count(*) tea from teacher

select * from classinfo where clsname like '????%'
select * from classinfo where clsid = 43
select count(*) cls from classinfo

select * from examschedule

select clsid from student
	group by clsid

select * from score where stuNum = 202007030316
select * from score
select count(*) score from score


insert into examSchedule(caid, examdate, static, context)
	values(5,'2020-7-2',0,'????1,2????????')

delete score where esid = 6
delete score where stunum like '2020070204__'


select esid, avg(score) from score
	group by esid
	order by avg(score) desc


select stu.clsid, cls.clsname, avg(score.score) ???????? from student as stu
	inner join score on (stu.stunum = score.stunum)
	left join classinfo as cls on(stu.clsid = cls.clsid)
	group by stu.clsid, cls.clsname
	order by
		avg(score.score) desc
select * from classinfo 

select * from score
	where score < 60



select * stu from student
select * cls from classinfo
select * tea from teacher
select * score from score
select * lesson from lesson
select * course from coursearrangement
select * exam from examschedule

select count(*) count_stu from student
select count(*) count_cls from classinfo
select count(*) count_tea from teacher
select count(*) count_score from score
select count(*) count_lesson from lesson
select count(*) count_course from coursearrangement
select count(*) count_exam from examschedule