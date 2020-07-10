/*
 Navicat Premium Data Transfer

 Source Server         : 220
 Source Server Type    : MySQL
 Source Server Version : 50096
 Source Host           : 192.168.0.220:3306
 Source Schema         : chuxi

 Target Server Type    : MySQL
 Target Server Version : 50096
 File Encoding         : 65001

 Date: 10/07/2020 17:00:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for log_user
-- ----------------------------
DROP TABLE IF EXISTS `log_user`;
CREATE TABLE `log_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `userid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `create_date` datetime NULL DEFAULT NULL COMMENT '日志时间',
  `content` varchar(4000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'ip',
  PRIMARY KEY USING BTREE (`id`),
  INDEX `fk_log_user_userid` USING BTREE(`userid`),
  INDEX `fk_log_user_create_date` USING BTREE(`create_date`)
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of log_user
-- ----------------------------
INSERT INTO `log_user` VALUES (7, 10, '2020-07-02 09:54:27', '注册用户', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (8, 10, '2020-07-02 10:42:06', '用户登陆', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (9, 10, '2020-07-02 10:46:56', '用户登陆', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (10, 10, '2020-07-02 10:47:01', '用户退出', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (11, 10, '2020-07-02 10:47:57', '用户登陆', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (12, 10, '2020-07-02 10:48:13', '用户退出', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (13, 10, '2020-07-02 10:48:32', '用户登陆', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (14, 10, '2020-07-02 10:51:27', '用户退出', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (15, 10, '2020-07-02 10:52:04', '用户登陆', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (16, 10, '2020-07-02 10:52:10', '用户退出', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (17, 10, '2020-07-02 10:54:11', '用户登陆', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (18, 10, '2020-07-02 10:54:42', '用户退出', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (19, 10, '2020-07-02 10:54:51', '用户登陆', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (20, 10, '2020-07-07 08:45:00', '用户退出', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (21, 10, '2020-07-07 08:45:03', '用户登陆', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (22, 10, '2020-07-07 08:45:30', '用户退出', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (23, 10, '2020-07-07 08:45:42', '用户登陆', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (24, 10, '2020-07-07 10:50:06', '用户登陆', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (25, 10, '2020-07-08 09:50:51', '用户退出', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (26, 10, '2020-07-08 09:51:01', '用户登陆', '0:0:0:0:0:0:0:1');
INSERT INTO `log_user` VALUES (27, 10, '2020-07-08 09:51:21', '用户登陆', '192.168.0.193');
INSERT INTO `log_user` VALUES (28, 10, '2020-07-10 11:00:52', '用户登陆', '192.168.9.1');
INSERT INTO `log_user` VALUES (31, 10, '2020-07-10 16:45:30', '<a href=\"/file/getFile?realFile=E:/file/chuxi/10/head/ea14473c3fc044728a98ba52091b4110.jpg&fileName=电脑壁纸-锁屏-01.jpg\" target=\"_blank\">新增头像 电脑壁纸-锁屏-01.jpg</a>', '192.168.0.193');
INSERT INTO `log_user` VALUES (32, 10, '2020-07-10 16:46:28', '<a href=\"/file/getFile?realFile=E:/file/chuxi/10/head/583749c53801479b98e0e0929964be17.jpg&fileName=电脑壁纸-锁屏-04.jpg\" target=\"_blank\">新增头像 电脑壁纸-锁屏-04.jpg</a>', '192.168.0.193');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名字',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `state` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `login_date` datetime NULL DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY USING BTREE (`id`),
  INDEX `fk_sys_user_mobile` USING BTREE(`mobile`),
  INDEX `fk_sys_user_account` USING BTREE(`account`),
  INDEX `fk_sys_user_email` USING BTREE(`email`)
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (10, '张三', '13683816984', '2449709309@qq.com', NULL, 'e10adc3949ba59abbe56e057f20f883e', '1', '2020-07-02 09:54:30', '2020-07-10 03:01:05');

-- ----------------------------
-- Table structure for sys_user_head
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_head`;
CREATE TABLE `sys_user_head`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL COMMENT '用户id',
  `ceeate_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `user_file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户文件名字',
  `file_ext` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件后缀',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '上传后文件名字',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文件路径',
  `is_show` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否展示',
  PRIMARY KEY USING BTREE (`id`),
  INDEX `fk_sys_user_head_userid` USING BTREE(`userid`)
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户头像表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_head
-- ----------------------------
INSERT INTO `sys_user_head` VALUES (3, 10, '2020-07-10 16:17:45', '电脑壁纸-锁屏-05', 'jpg', 'ca91ff9a88484aa8b859b9b856cf2fce', 'E:/file/chuxi/10/head/', '0');
INSERT INTO `sys_user_head` VALUES (4, 10, '2020-07-10 16:45:30', '电脑壁纸-锁屏-01', 'jpg', 'ea14473c3fc044728a98ba52091b4110', 'E:/file/chuxi/10/head/', '0');
INSERT INTO `sys_user_head` VALUES (5, 10, '2020-07-10 16:46:28', '电脑壁纸-锁屏-04', 'jpg', '583749c53801479b98e0e0929964be17', 'E:/file/chuxi/10/head/', '1');

SET FOREIGN_KEY_CHECKS = 1;
