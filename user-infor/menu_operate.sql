/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : his-userinfo

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 19/09/2019 22:47:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu_operate
-- ----------------------------
DROP TABLE IF EXISTS `menu_operate`;
CREATE TABLE `menu_operate`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) NULL DEFAULT NULL,
  `operate_id` bigint(20) NULL DEFAULT NULL,
  `permission` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu_operate
-- ----------------------------
INSERT INTO `menu_operate` VALUES (4, 339, 19, 'add');
INSERT INTO `menu_operate` VALUES (5, 339, 20, 'update');
INSERT INTO `menu_operate` VALUES (6, 339, 21, 'delete');
INSERT INTO `menu_operate` VALUES (7, 340, 19, 'sys.user.add');
INSERT INTO `menu_operate` VALUES (8, 340, 20, 'sys.user.update');
INSERT INTO `menu_operate` VALUES (9, 340, 21, 'sys.user.delete');
INSERT INTO `menu_operate` VALUES (10, 351, 19, 'nursemanager.add');
INSERT INTO `menu_operate` VALUES (11, 351, 20, 'nursemanager.update');
INSERT INTO `menu_operate` VALUES (12, 351, 21, 'nursemanager.delete');
INSERT INTO `menu_operate` VALUES (37, 353, 19, 'hiscore.patient.add');
INSERT INTO `menu_operate` VALUES (38, 353, 20, 'hiscore.patient.update');
INSERT INTO `menu_operate` VALUES (39, 353, 21, 'hiscore.patient.delete');
INSERT INTO `menu_operate` VALUES (40, 353, 22, 'hiscore.patient.select');
INSERT INTO `menu_operate` VALUES (41, 369, 20, '0.update');
INSERT INTO `menu_operate` VALUES (42, 370, 19, 'sys.ward.add');
INSERT INTO `menu_operate` VALUES (43, 370, 20, 'sys.ward.update');
INSERT INTO `menu_operate` VALUES (44, 370, 21, 'sys.ward.delete');
INSERT INTO `menu_operate` VALUES (45, 370, 22, 'sys.ward.select');
INSERT INTO `menu_operate` VALUES (50, 379, 19, 'sys.prescription.add');
INSERT INTO `menu_operate` VALUES (51, 379, 20, 'sys.prescription.update');
INSERT INTO `menu_operate` VALUES (52, 379, 21, 'sys.prescription.delete');
INSERT INTO `menu_operate` VALUES (53, 379, 22, 'sys.prescription.select');
INSERT INTO `menu_operate` VALUES (58, 393, 19, '0.add');
INSERT INTO `menu_operate` VALUES (59, 393, 20, '0.update');
INSERT INTO `menu_operate` VALUES (60, 393, 21, '0.delete');
INSERT INTO `menu_operate` VALUES (61, 393, 22, '0.select');
INSERT INTO `menu_operate` VALUES (62, 394, 19, 'sys.prescription.add');
INSERT INTO `menu_operate` VALUES (63, 394, 20, 'sys.prescription.update');
INSERT INTO `menu_operate` VALUES (64, 394, 21, 'sys.prescription.delete');
INSERT INTO `menu_operate` VALUES (65, 394, 22, 'sys.prescription.select');

SET FOREIGN_KEY_CHECKS = 1;
