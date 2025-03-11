/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : carmanage

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 11/03/2025 15:44:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cm_car
-- ----------------------------
DROP TABLE IF EXISTS `cm_car`;
CREATE TABLE `cm_car`  (
  `carid` int(255) NOT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `kind` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `year` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`carid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `cm_car_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `cm_usr` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cm_car
-- ----------------------------
INSERT INTO `cm_car` VALUES (1, 4, '本田思铂睿', '2009-2017', '红色');

-- ----------------------------
-- Table structure for cm_order
-- ----------------------------
DROP TABLE IF EXISTS `cm_order`;
CREATE TABLE `cm_order`  (
  `orderid` int(255) NOT NULL,
  `uid` int(11) NOT NULL,
  `money` int(255) NULL DEFAULT NULL,
  `starttime` datetime NULL DEFAULT NULL,
  `finishedtime` datetime(6) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`orderid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `cm_order_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `cm_usr` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cm_order
-- ----------------------------
INSERT INTO `cm_order` VALUES (1, 4, 9900, '2025-03-11 15:34:37', '2025-03-11 15:43:13.655000', '已完成');

-- ----------------------------
-- Table structure for cm_pic
-- ----------------------------
DROP TABLE IF EXISTS `cm_pic`;
CREATE TABLE `cm_pic`  (
  `picid` int(11) NOT NULL,
  `subid` int(11) NULL DEFAULT NULL,
  `filepath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`picid`) USING BTREE,
  INDEX `subid`(`subid`) USING BTREE,
  CONSTRAINT `cm_pic_ibfk_1` FOREIGN KEY (`subid`) REFERENCES `cm_sub` (`subid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cm_pic
-- ----------------------------
INSERT INTO `cm_pic` VALUES (1, 1, 'C:/Users/Yuer/Documents/workspace-spring-tool-suite-4-4.22.1.RELEASE/carmanage/src/main/resources/static/image/1_broken.png');

-- ----------------------------
-- Table structure for cm_sub
-- ----------------------------
DROP TABLE IF EXISTS `cm_sub`;
CREATE TABLE `cm_sub`  (
  `subid` int(11) NOT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`subid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `cm_sub_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `cm_usr` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cm_sub
-- ----------------------------
INSERT INTO `cm_sub` VALUES (1, 4, '我的车子坏了请维修！', '通过');

-- ----------------------------
-- Table structure for cm_usr
-- ----------------------------
DROP TABLE IF EXISTS `cm_usr`;
CREATE TABLE `cm_usr`  (
  `uid` int(11) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `authority` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cm_usr
-- ----------------------------
INSERT INTO `cm_usr` VALUES (1, 'admin', 'admin', 4);
INSERT INTO `cm_usr` VALUES (2, 'shen', '12345678a', 2);
INSERT INTO `cm_usr` VALUES (4, 'user', '12345678a', 1);
INSERT INTO `cm_usr` VALUES (3, 'wei', '12345678a', 3);

-- ----------------------------
-- Table structure for cm_workorder
-- ----------------------------
DROP TABLE IF EXISTS `cm_workorder`;
CREATE TABLE `cm_workorder`  (
  `workorderid` int(255) NOT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `cmuid` int(11) NULL DEFAULT NULL,
  `starttime` datetime NULL DEFAULT NULL,
  `finishedtime` datetime NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`workorderid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `cmuid`(`cmuid`) USING BTREE,
  CONSTRAINT `cm_workorder_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `cm_usr` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cm_workorder_ibfk_2` FOREIGN KEY (`cmuid`) REFERENCES `cm_usr` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cm_workorder
-- ----------------------------
INSERT INTO `cm_workorder` VALUES (1, 4, 3, '2025-03-11 15:34:37', '2025-03-11 15:42:57', '已完成');

SET FOREIGN_KEY_CHECKS = 1;
