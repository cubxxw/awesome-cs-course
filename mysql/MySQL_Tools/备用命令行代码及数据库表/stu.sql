/*
Navicat MySQL Data Transfer

Source Server         : 测试1
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2014-05-11 00:37:03
*/

SET FOREIGN_KEY_CHECKS=0;
set names utf8;

-- ----------------------------
-- Table structure for `stu`
-- ----------------------------
DROP TABLE IF EXISTS `stu`;
CREATE TABLE `stu` (
  `Sno` char(12) NOT NULL DEFAULT '',
  `Sname` char(20) DEFAULT NULL,
  `Ssex` char(2) DEFAULT NULL,
  `Sage` smallint(6) DEFAULT NULL,
  `Sdept` char(20) DEFAULT NULL,
  PRIMARY KEY (`Sno`),
  UNIQUE KEY `Sno` (`Sno`),
  UNIQUE KEY `Sno_2` (`Sno`),
  UNIQUE KEY `Sname` (`Sname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu
-- ----------------------------
INSERT INTO `stu` VALUES ('121031325', '卫', '男', '22', '计算机学院');
INSERT INTO `stu` VALUES ('121031329', '闻', '男', '20', '计算机学院');
INSERT INTO `stu` VALUES ('121031323', '文', '男', '21', '计算机学院');
INSERT INTO `stu` VALUES ('121031324', '罗', '男', '21', '计算机学院');
INSERT INTO `stu` VALUES ('121031326', '郭王', '男', '22', '计算机学院');
