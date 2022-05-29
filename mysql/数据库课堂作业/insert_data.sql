"创建表格插入数据"
insert into student values ('202032120777', '张晓梅', '女', 20, '经管1701', '2017-09-01');
insert into student values ('201932120778', '李晓龙', '男', 21, '数学1601', '2020-09-01');
insert into student values ('202132120779', '黄飞红', '女', 20, '英语1801', '2018-09-01');
insert into student values ('202032120768', '李云隆', '男', 21, '数学1701', '2017-09-01');
insert into student values ('201932120720', '张阿红', '女', 19, '英语1901', '2019-09-01');
insert into student values ('201932120711', '马晕', '男', 22, '数学1601', '2020-09-01');
insert into student values ('202032120762', '鲁智深', '男', 21, '经管1701', '2017-09-01');
insert into student values ('202132120719', '孙文空', '女', 19, '英语1801', '2018-09-01');
insert into student values ('201932120710', '马花藤', '男', 22, '数学1601', '2020-09-01');

insert into course values ('c001', '微观经济学', '122118', '周四 第10-12节', '3-311', 3, 80);
insert into course values ('c002', '宏观经济学', '122124', '周五 第10-12节', '22-402', 6, 60);
insert into course values ('c003', '英语名著欣赏', '122125', '周三 第1-3节', '4-301', 2, 60);
insert into course values ('c004', '数学分析', '122110', '周二 第1-4节', '20-201', 6, 80);
insert into course values ('c005', '高等数学', '122110', '周四 第1-4节', '3-310', 6, 80);
insert into course values ('c006', '商务英语写作', '122122', '周四 第8-9节', '5-301', 4, 80);

insert into teacher values ('122118', '陈读', '男', '17398447611' , 'd001', '副教授');
insert into teacher values ('122124', '韩宏', '女', '17398440222' , 'd001', '教授');
insert into teacher values ('122125', '马冬梅', '女', '17398449118' , 'd002', '副教授');
insert into teacher values ('122110', '唐伯虎', '男', '17398443912' , 'd003', '副教授');
insert into teacher values ('122122', '谭英文', '男', '17398442121' , 'd002', '副教授');

insert into department values ('d001', '经济学院', '王元'); 	
insert into department values ('d002', '英语学院', '张小龙'); 
insert into department values ('d003', '数学学院', '黄飞'); 

insert into major values ('m001', '经济学专业'); 
insert into major values ('m002', '英文专业');
insert into major values ('m003', '数学专业');  

insert into stu_course values ('202032120777', 'c001', null);
insert into stu_course values ('202032120777', 'c002', null);
insert into stu_course values ('201932120778', 'c004', null);
insert into stu_course values ('201932120778', 'c005', null);
insert into stu_course values ('202132120779', 'c003', null);
insert into stu_course values ('202032120768', 'c004', 70);
insert into stu_course values ('202032120762', 'c002', 80);
insert into stu_course values ('202132120719', 'c003', 90);
insert into stu_course values ('202132120719', 'c006', 92);

insert into teacher_course values ('122118', 'c001');
insert into teacher_course values ('122124', 'c002');
insert into teacher_course values ('122125', 'c003');
insert into teacher_course values ('122110', 'c004');
insert into teacher_course values ('122110', 'c005');
insert into teacher_course values ('122122', 'c006');

insert into class_stu_num values ('经管1701', 2);
insert into class_stu_num values ('数学1601', 3);
insert into class_stu_num values ('数学1701', 1);
insert into class_stu_num values ('英语1801', 2);
insert into class_stu_num values ('英语1901', 1);

insert into stu_reward_punishment(sno, reward) values ('202032120777', '校一等奖学金');
insert into stu_reward_punishment(sno, punishment) values ('202032120777', '全校通告批评');
insert into stu_reward_punishment(sno, punishment) values ('201932120711', '校一等奖学金');

insert into account values ('stu', '1224');
insert into account values ('stu1', '1224');
insert into account values ('teach', '1224');
insert into account values ('admin', '1224');  "管理员"
insert into account values ('student', '1224');
"用户的登陆信息表格"
