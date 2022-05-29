create table if not exists student(
	sno varchar(20) primary key,
	sname varchar(10) not null,
	ssex enum('男','女') not null default'男' comment'性别'，
	sage int(4) not null
	sclass varchar(10) not null,
	intime datetime not null
); "创建一个表结构，设置为学生表"

create table if not exists course (
	cno varchar(10) primary key,
	cname varchar(10) not null,
	tno varchar(10) not null, 
	c_period varchar(10) not null,
	clocation varchar(10) not null,
	credit int(2) not null,
	ctime int(2) 
);"创建一个表结构，设置为课程信息表"

create table if not exists teacher (
	tno varchar(20) primary key,
	tname varchar(10) not null,
	tsex varchar(2) not null,
	tphone varchar(20) not null,
	dmpno varchar(10) not null,
	profess varchar(10) not null
); "创建一个表结构，设置为老师表"

create table if not exists department (
	dmpno varchar(10) primary key,
	dname varchar(10) not null,
	dmphead varchar(10) not null
); "创建一个表结构，设置为院系表，"

create table if not exists major (
	mno varchar(5) not null,
	mname varchar(10) not null,
	primary key (mno, mname)
); "创建一个表结构，设置为专业表"

create table if not exists stu_course (
	sno varchar(20),
	cno varchar(10),
	grade int(3) default null,
	primary key (sno, cno)  "成绩"
);"创建一个表结构，设置为学生成绩表"

create table if not exists teacher_course (
	tno varchar(10),
	cno varchar(10),
	primary key (tno, cno)
);"创建一个表结构，设置为老师授课表"

create table if not exists class_stu_num (
	class varchar(10) primary key,
	stu_num int default 0
);"创建一个表结构，设置为学生上课对应教室的表"

create table if not exists stu_reward_punishment (
	sno varchar(20) not null,
	reward varchar(100) default null,
	punishment varchar(100) default null
);"创建一个表结构，设置学生奖惩表"

create table if not exists account (
	username varchar(20) primary key,
	passward varchar(20) not null
);"设置一个账户信息表，对应的是登陆用户的信息表"
