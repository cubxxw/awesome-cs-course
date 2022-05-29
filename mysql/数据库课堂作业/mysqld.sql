/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : mysqld

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2021-11-08 12:00:31
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cno` char(5) NOT NULL,
  `cname` varchar(20) NOT NULL,
  PRIMARY KEY (`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('C01', '数据库');
INSERT INTO `course` VALUES ('C02', '数学');
INSERT INTO `course` VALUES ('C03', '操作系统');
INSERT INTO `course` VALUES ('C04', '信息工程网路');

-- ----------------------------
-- Table structure for `sc`
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc` (
  `sno` char(10) NOT NULL,
  `cno` char(5) NOT NULL,
  `degree` decimal(4,1) DEFAULT NULL,
  PRIMARY KEY (`sno`,`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES ('2005010101', 'C01', '92.0');
INSERT INTO `sc` VALUES ('2005010101', 'C02', '85.0');
INSERT INTO `sc` VALUES ('2005020101', 'C03', '88.0');
INSERT INTO `sc` VALUES ('2005020201', 'C02', '90.0');
INSERT INTO `sc` VALUES ('2005020201', 'C03', '80.0');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sno` char(10) NOT NULL,
  `sname` varchar(8) DEFAULT NULL,
  `ssex` char(2) DEFAULT NULL,
  `sbirthday` date DEFAULT NULL,
  `saddress` varchar(50) DEFAULT NULL,
  `sdept` char(16) DEFAULT NULL,
  `speciality` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('2005010101', '王一博', '男', '2001-03-25', '武汉市', '汉', '计算机科学');
INSERT INTO `student` VALUES ('2005010102', '赵小花', '女', '2003-11-11', '天津', '汉', '软件工程');
INSERT INTO `student` VALUES ('2005010201', '李多强', '男', '1997-12-15', '上海市', '汉族', '医学专业');
INSERT INTO `student` VALUES ('2005010202', '郝多钱', '男', '2001-10-22', '北京市', '汉', '金融');
INSERT INTO `student` VALUES ('2005020201', '张一山', '男', '1998-08-19', '武汉', '汉', '电影学');
INSERT INTO `student` VALUES ('2005020202', '倪多多', '女', '1998-04-23', '天津市', '苗族', '视觉传达');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `tno` char(3) NOT NULL,
  `tname` varchar(8) DEFAULT NULL,
  `tsex` char(2) DEFAULT NULL,
  `tbirthday` date DEFAULT NULL,
  `tdept` char(16) DEFAULT NULL,
  PRIMARY KEY (`tno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('101', '李新', '男', '1977-01-12', '计算机工程系');
INSERT INTO `teacher` VALUES ('102', '钱军', '女', '1968-06-04', '计算机工程系');
INSERT INTO `teacher` VALUES ('201', '王小花', '女', '1979-12-23', '信息工程系');
INSERT INTO `teacher` VALUES ('202', '张小青', '男', '1968-08-25', '信息工程系');

-- ----------------------------
-- Table structure for `teaching`
-- ----------------------------
DROP TABLE IF EXISTS `teaching`;
CREATE TABLE `teaching` (
  `cno` char(5) NOT NULL,
  `tno` char(3) NOT NULL,
  `cterm` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`cno`,`tno`),
  KEY `tno_fk` (`tno`),
  CONSTRAINT `cno_fk` FOREIGN KEY (`cno`) REFERENCES `course` (`cno`),
  CONSTRAINT `tno_fk` FOREIGN KEY (`tno`) REFERENCES `teacher` (`tno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of teaching
-- ----------------------------
INSERT INTO `teaching` VALUES ('C01', '101', '2');
INSERT INTO `teaching` VALUES ('C02', '102', '1');
INSERT INTO `teaching` VALUES ('C03', '201', '3');
INSERT INTO `teaching` VALUES ('C04', '202', '4');
