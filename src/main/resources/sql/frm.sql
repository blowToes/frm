/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : frm

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-12-21 10:46:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ts_permissions
-- ----------------------------
DROP TABLE IF EXISTS `ts_permissions`;
CREATE TABLE `ts_permissions` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `permission_name` varchar(32) DEFAULT NULL COMMENT '权限名',
  `permission_url` varchar(64) DEFAULT NULL COMMENT '资源定义',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of ts_permissions
-- ----------------------------
INSERT INTO `ts_permissions` VALUES ('1', 'user:add', '/api/security/ts-user/query/page/users', '12', '2018-12-20', '2018-12-18');
INSERT INTO `ts_permissions` VALUES ('2', 'user:query', '/query/user', '12', '2018-12-20', '2018-12-18');

-- ----------------------------
-- Table structure for ts_role
-- ----------------------------
DROP TABLE IF EXISTS `ts_role`;
CREATE TABLE `ts_role` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名',
  `rule_description` varchar(64) DEFAULT NULL COMMENT '角色描述',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of ts_role
-- ----------------------------
INSERT INTO `ts_role` VALUES ('1', 'admin', '管理员', '1', '2018-12-18', '2018-12-18');
INSERT INTO `ts_role` VALUES ('2', 'user', '普通用户', '1', '2018-12-18', '2018-12-18');

-- ----------------------------
-- Table structure for ts_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `ts_role_permission`;
CREATE TABLE `ts_role_permission` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `perssion_id` varchar(32) DEFAULT NULL COMMENT '权限ID',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of ts_role_permission
-- ----------------------------
INSERT INTO `ts_role_permission` VALUES ('1', '1', '1', '12', '2018-12-18', '2018-12-18');
INSERT INTO `ts_role_permission` VALUES ('2', '2', '2', '12', '2018-12-18', '2018-12-18');

-- ----------------------------
-- Table structure for ts_user
-- ----------------------------
DROP TABLE IF EXISTS `ts_user`;
CREATE TABLE `ts_user` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `account` varchar(32) DEFAULT NULL COMMENT '账号',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `pwdword` varchar(64) DEFAULT NULL COMMENT '密码',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of ts_user
-- ----------------------------
INSERT INTO `ts_user` VALUES ('1', 'zhangsan', 'zhangsan', 'ad7a8f43d37c65f82e581d63c7591b1d', '男', 'qw', '2018-12-18', '2018-12-18');
INSERT INTO `ts_user` VALUES ('2', 'admin', 'admin', 'df655ad8d3229f3269fad2a8bab59b6c', '女', 'qw', '2018-12-04', '2018-12-11');
INSERT INTO `ts_user` VALUES ('a5107bcf98b343b59104921df6e798ad', 'lisi', 'lisi', '8c702ae443795331c91cfab48f3f3833', '女', null, '2018-12-19', '2018-12-19');

-- ----------------------------
-- Table structure for ts_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ts_user_role`;
CREATE TABLE `ts_user_role` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(32) DEFAULT NULL COMMENT 'jueseID',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of ts_user_role
-- ----------------------------
INSERT INTO `ts_user_role` VALUES ('1', '1', '2', '12', '2018-12-18', '2018-12-18');
INSERT INTO `ts_user_role` VALUES ('2', '2', '1', '12', '2018-12-18', '2018-12-18');
