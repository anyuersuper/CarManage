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

 Date: 06/03/2025 15:30:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cm_car
-- ----------------------------
DROP TABLE IF EXISTS `cm_car`;
CREATE TABLE `cm_car`  (
  `carid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
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
INSERT INTO `cm_car` VALUES ('1', 1, '劳斯莱斯', '2025', '黑色');

-- ----------------------------
-- Table structure for cm_order
-- ----------------------------
DROP TABLE IF EXISTS `cm_order`;
CREATE TABLE `cm_order`  (
  `orderid` int(255) NOT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `cmuid` int(11) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`orderid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `cmuid`(`cmuid`) USING BTREE,
  CONSTRAINT `cm_order_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `cm_usr` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cm_order_ibfk_2` FOREIGN KEY (`cmuid`) REFERENCES `cm_usr` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cm_order
-- ----------------------------

-- ----------------------------
-- Table structure for cm_sub
-- ----------------------------
DROP TABLE IF EXISTS `cm_sub`;
CREATE TABLE `cm_sub`  (
  `subid` int(11) NOT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`subid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `cm_sub_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `cm_usr` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cm_sub
-- ----------------------------

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
INSERT INTO `cm_usr` VALUES (7, '11', '11', 1);
INSERT INTO `cm_usr` VALUES (8, '111', '11', 1);
INSERT INTO `cm_usr` VALUES (9, '1111', '11', 1);
INSERT INTO `cm_usr` VALUES (10, '11111', '11', 1);
INSERT INTO `cm_usr` VALUES (11, '111111', '11', 1);
INSERT INTO `cm_usr` VALUES (3, 'aaaa', 'aaa', 1);
INSERT INTO `cm_usr` VALUES (1, 'admin', 'admin', 4);
INSERT INTO `cm_usr` VALUES (5, 'asdasd', 'asdasd', 1);
INSERT INTO `cm_usr` VALUES (2, 'asdasda', 'adasdasd', 1);
INSERT INTO `cm_usr` VALUES (6, 'asdasds', 'asdasd', 1);
INSERT INTO `cm_usr` VALUES (4, 'sdasd', 'sadasd', 1);

SET FOREIGN_KEY_CHECKS = 1;
