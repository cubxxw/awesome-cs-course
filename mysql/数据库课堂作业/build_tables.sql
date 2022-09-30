create table if not exists student(
	sno varchar(20) primary key,
	sname varchar(10) not null,
	ssex varchar(2) not null,
	sage int(4) not null,
	sclass varchar(10) not null,
	intime datetime not null
);

create table if not exists course (
	cno varchar(10) primary key,
	cname varchar(10) not null,
	tno varchar(10) not null, 
	c_period varchar(10) not null,
	clocation varchar(10) not null,
	credit int(2) not null,
	ctime int(2) not null
);

create table if not exists teacher (
	tno varchar(20) primary key,
	tname varchar(10) not null,
	tsex varchar(2) not null,
	tphone varchar(20) not null,
	dmpno varchar(10) not null,
	profess varchar(10) not null
);

create table if not exists department (
	dmpno varchar(5) primary key,
	dname varchar(10) not null,
	dmphead varchar(10) not null
);

create table if not exists major (
	mno varchar(5) not null,
	mname varchar(10) not null,
	primary key (mno, mname)
);

create table if not exists stu_course (
	sno varchar(20),
	cno varchar(10),
	grade int(3) default null,
	primary key (sno, cno)
);

create table if not exists teacher_course (
	tno varchar(10),
	cno varchar(10),
	primary key (tno, cno)
);

create table if not exists class_stu_num (
	class varchar(10) primary key,
	stu_num int default 0
);

create table if not exists stu_reward_punishment (
	sno varchar(20) not null,
	reward varchar(100) default null,
	punishment varchar(100) default null
);

create table if not exists account (
	username varchar(20) primary key,
	passward varchar(20) not null
);