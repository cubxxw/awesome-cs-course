drop table if exists workers;
CREATE TABLE `workers` (
  编号 char(12) NOT NULL,
  姓名 char(10) DEFAULT NULL,
  性别 char(2) DEFAULT '男' check(Wsex in('男','女')),
  出生年月 char(8) DEFAULT NULL,
  工作月数 int(11) DEFAULT '0' check(WworkTime>=0),
  学历 char(10) DEFAULT NULL,
  职务 char(20) DEFAULT NULL,
  住址 char(30) DEFAULT NULL,
  电话 char(12) DEFAULT NULL,
  PRIMARY KEY (编号)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

INSERT INTO `workers` VALUES ('001', '甲', '男', '1993.2', '1', '本科', '普通员工', '北京', '010-1111111');
INSERT INTO `workers` VALUES ('002', '乙', '女', '1990.3', '16', '本科', '主管', '北京', '010-1111222');
INSERT INTO `workers` VALUES ('003', '丙', '男', '1992.2', '1', '本科', '普通员工', '武汉', '027-1234123');
INSERT INTO `workers` VALUES ('004', '丁', '女', '1993.1', '3', '本科', '普通员工', '武汉', '027-1234567');
