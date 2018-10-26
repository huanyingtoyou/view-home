/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50637
Source Host           : localhost:3306
Source Database       : view

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-10-26 10:19:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_bill_rule
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_bill_rule`;
CREATE TABLE `t_sys_bill_rule` (
  `ID` varchar(32) NOT NULL COMMENT '编号规则ID',
  `RULES_CODE` varchar(50) NOT NULL COMMENT '规则代码',
  `ITEM_NAME` varchar(30) DEFAULT NULL COMMENT '单据名称',
  `PREFIX` varchar(30) NOT NULL COMMENT '编码前缀',
  `INIT_VALUE` int(11) DEFAULT NULL COMMENT '初始值',
  `CURRENT_VALUE` int(11) DEFAULT NULL COMMENT '当前值',
  `DATE_STRING` varchar(30) DEFAULT NULL COMMENT '年月字符',
  `DATE_TYPE` varchar(30) DEFAULT NULL COMMENT '日期类型',
  `ITEM_LENGTH` decimal(8,0) DEFAULT NULL COMMENT '单号长度',
  `VALID_FLAG` varchar(2) DEFAULT NULL COMMENT '是否有效',
  `REC_SRC` varchar(2) DEFAULT NULL COMMENT '记录来源',
  `NOTES` varchar(100) DEFAULT NULL COMMENT '备注',
  `CREATE_USER_ID` varchar(30) NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_USER_ID` varchar(20) DEFAULT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
  `DELETE_FLAG` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单据编号表';

-- ----------------------------
-- Records of t_sys_bill_rule
-- ----------------------------
INSERT INTO `t_sys_bill_rule` VALUES ('004988ef03ec4618bbb01d38ee41a15e', 'USER_ID', '用户id', 'U', null, '0', null, '', '8', null, null, '', 'admin', '2018-01-22 09:47:27', '1539942334292', '2018-10-19 17:45:34', '0');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `USER_ID` varchar(32) NOT NULL,
  `USERNAME` varchar(32) DEFAULT NULL,
  `PASSWORD` varchar(64) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `BIRTH_DATE` datetime DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATE_PERSON` varchar(32) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_PERSON` varchar(32) DEFAULT NULL,
  `SALT` varchar(50) DEFAULT NULL COMMENT '加密盐',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
