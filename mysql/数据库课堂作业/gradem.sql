/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : gradem

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2021-11-07 19:29:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
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

-- ----------------------------
-- Table structure for sc
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

-- ----------------------------
-- Table structure for student
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

-- ----------------------------
-- Table structure for teacher
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

-- ----------------------------
-- Table structure for teaching
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
