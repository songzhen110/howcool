/*
 Navicat Premium Data Transfer

 Source Server         : centos
 Source Server Type    : MySQL
 Source Server Version : 50626
 Source Host           : 192.168.201.120:3306
 Source Schema         : how-cool

 Target Server Type    : MySQL
 Target Server Version : 50626
 File Encoding         : 65001

 Date: 23/03/2019 11:22:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) NOT NULL COMMENT '父菜单 ID，0=根菜单',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '路由名称',
  `sort` int(11) NOT NULL COMMENT '排序，从1开始',
  `level` int(11) NOT NULL COMMENT '层级，从1开始',
  `icon` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图标 URL',
  `path` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单路径',
  `redirect` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '重定向地址',
  `dictionaries` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典',
  `hidden` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否显示，0=显示，1=不显示',
  `no_cache` tinyint(1) NULL DEFAULT NULL COMMENT '是否缓存，0=否，1=是',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_id`(`id`) USING BTREE COMMENT '菜单ID索引',
  INDEX `index_parent_id`(`parent_id`) USING BTREE COMMENT '父节点索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统菜单' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user_0`;
CREATE TABLE `t_user_0`
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `u_id`        BIGINT      NOT NULL COMMENT '用户ID,格式：yyyyMMddHHmmss',
    `user_name`   VARCHAR(20)          DEFAULT NULL COMMENT '用户名',
    `password`    VARCHAR(64) NOT NULL COMMENT '密码',
    `mobile`      VARCHAR(20) NOT NULL COMMENT '联系电话',
    `email`       VARCHAR(64) NOT NULL COMMENT '邮箱',
    `status`      TINYINT ( 1 ) NOT NULL DEFAULT '0' COMMENT '账号状态：0.不启用；1.启用',
    `is_delete`   TINYINT     NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_by`   VARCHAR(64) NOT NULL COMMENT '创建者',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   VARCHAR(64) NOT NULL COMMENT '更新者',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `index_u_id` ( `u_id` ) USING BTREE COMMENT '用户ID唯一索引',
    UNIQUE KEY `index_user_name` ( `user_name` ) USING BTREE COMMENT '用户名唯一索引'
) COMMENT = '用户信息';

SET FOREIGN_KEY_CHECKS = 1;
