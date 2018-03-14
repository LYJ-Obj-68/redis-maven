/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50527
Source Host           : 127.0.0.1:3306
Source Database       : redis-web

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-03-14 09:15:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `uphone` varchar(11) DEFAULT NULL,
  `uname` varchar(30) DEFAULT NULL,
  `ugender` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '17368688686', '木耳', 'W');
INSERT INTO `t_user` VALUES ('2', '17386866868', '香菇', 'W');
INSERT INTO `t_user` VALUES ('5', '15168688686', '红枣', 'M');
INSERT INTO `t_user` VALUES ('6', '15186866868', '桂圆', 'M');
