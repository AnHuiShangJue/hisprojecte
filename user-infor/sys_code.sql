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

 Date: 20/09/2019 16:54:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_code`;
CREATE TABLE `sys_code`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `company_id` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `del_flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10229 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_code
-- ----------------------------
INSERT INTO `sys_code` VALUES (10084, 'is_acceptance', '知识产权是否授权', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10102, 'sex', '性别', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10117, 'pharmacy_type', '药剂类型', '主要用于药品基本数据的选择使用，是药品的必填项', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10118, 'is_enable', '是否启用', '是否启用的字典，1-启用 2-不启用', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10119, 'open_type', '打开方式', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10120, 'is_married', '婚否', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10121, 'is_pay', '是否付费', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10122, 'is_print', '是否打印', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10123, 'is_obsolete', '是否作废', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10124, 'is_settlement', '是否结算', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10125, 'registered_category', '挂号类别', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10126, 'is_obtained', '是否下架', '判断药品是否下架', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10127, 'prescription', '是否为处方药', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10128, 'mental_medicine', '是否为精神类药品', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10129, 'disable', '是否停用', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10130, 'level', '药品级别', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10131, 'medical_insurance_sign', '药品类型', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10132, 'base_medicine', '是否基药', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10133, 'narcotic_drugs', '是否麻醉药品', '是否麻醉药品', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10134, 'placeOrigin', '原产地', '原产地', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10135, 'pay_type', '支付方式', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10136, 'scheduling_type', '排班', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10137, 'is_rel', '接诊', '就诊状态', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10138, 'department_type', '诊疗类别', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10139, 'room_type', '病房类型', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10140, 'completion', '完成情况', '采购计划是否完成', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10141, 'depart_kbn', '部门区分', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10142, 'project_type', '项目类型', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10143, 'hospital_bed', '病床类型', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10144, 'loss_type', '药品报损类型', '药品报损类型', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10145, 'fee_type', '费用类型', '1：自费 2：医保', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10146, 'care_level', '护理级别', '1：一级护理  2：二级  3：三级  4：特技监护 5：重症监护', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10147, 'is_review', '是否审核', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10148, 'is_re', '住院来源', '来源，1：其他医院，2：门诊', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10179, 'assit_project_type', '诊疗项目类型', '', '1', '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10180, 'measures', '药品退回处理措施', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10181, 'is_discharged', '是否出院', '1：未出院 2：已出院', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10182, 'is_hos', '住院状态', '1：未审核 2：审核未通过 3：审核通过', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10183, 'record_type', '收费类别', '1：药品 2：非药品', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10184, 'is_skin_test', '是否皮试', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10185, 'intervals', '间隔', '医嘱护嘱（间隔）', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10186, 'is_stop', '是否停嘱', '用于护嘱医嘱', '1', '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10187, 'medical_order_type', '医嘱类型', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10188, 'is_proofreading', '是否校对', '医嘱护嘱是否校对', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10189, 'is_approved', '是否核准', '医嘱护嘱是否核准', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10190, 'attenchType', '收费来源', '1  门诊 2 住院收费', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10191, 'type', '收费类型', '1 药品收费 2 项目收费', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10192, 'is_out', '是否已出药', '药品是否已从药库中出售', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10194, 'ankle_type', '护嘱类型', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10195, 'is_payed', '是否退款', '1已退款2未退款', '1', '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10196, 'consumable_type', '耗材类型', '1', '1', '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10197, 'is_outbound', '是否出库', '1 ：已出库  2：未出库', '1', '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10198, 'is_back', '是否退回', '', '1', '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10199, 'is_del', '是否删除', '', '1', '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code` VALUES (10200, 'is_checked', '是否检查', '诊疗项目是否检查', '1', '0', '45', '2019-08-17 17:25:39', '45', '2019-08-17 17:25:39');
INSERT INTO `sys_code` VALUES (10201, 'project_sources_type', '诊疗项目来源', '1：门诊，2：住院', '1', '0', '45', '2019-08-19 18:03:38', '45', '2019-08-19 18:03:38');
INSERT INTO `sys_code` VALUES (10202, 'registered_type', '挂号类型', '1.有卡挂号 2.无卡挂号', '1', '0', '1', '2019-09-10 16:01:08', '1', '2019-09-10 16:01:08');
INSERT INTO `sys_code` VALUES (10203, 'is_logout', '是否注销', '', '1', '0', '1', '2019-09-11 11:32:53', '1', '2019-09-11 11:32:53');
INSERT INTO `sys_code` VALUES (10204, 'is_agree', '是否同意', '输血记录：家属是否签署治疗知情同意书', '1', '0', '1', '2019-09-15 10:51:16', '1', '2019-09-15 10:51:16');
INSERT INTO `sys_code` VALUES (10205, 'is_approve', '是否批准', '输血记录：医疗机构负责人或授权负责人是否批准', '1', '0', '1', '2019-09-15 10:51:43', '1', '2019-09-15 10:51:43');
INSERT INTO `sys_code` VALUES (10206, 'is_bad', '是否有不良反应', '输血过程有无不良反应', '1', '0', '1', '2019-09-15 10:52:11', '1', '2019-09-15 10:52:11');
INSERT INTO `sys_code` VALUES (10207, 'is_sign', '是否签名', '主要用于各种签名', '1', '0', '1', '2019-09-15 10:52:35', '1', '2019-09-15 10:52:35');
INSERT INTO `sys_code` VALUES (10208, 'blood_type', '血液类型', '', '1', '0', '1', '2019-09-15 10:56:11', '1', '2019-09-15 10:56:11');
INSERT INTO `sys_code` VALUES (10209, 'is_mind', '神志', '', '1', '0', '1', '2019-09-15 15:07:13', '1', '2019-09-15 15:07:13');
INSERT INTO `sys_code` VALUES (10210, 'is_face', '表情', '', '1', '0', '1', '2019-09-15 15:07:42', '1', '2019-09-15 15:07:42');
INSERT INTO `sys_code` VALUES (10211, 'is_feeling', '情绪', '', '1', '0', '1', '2019-09-15 15:07:58', '1', '2019-09-15 15:07:58');
INSERT INTO `sys_code` VALUES (10212, 'competence', '沟通能力', '', '1', '0', '1', '2019-09-15 15:08:19', '1', '2019-09-15 15:08:19');
INSERT INTO `sys_code` VALUES (10213, 'communication', '沟通方式', '', '1', '0', '1', '2019-09-15 15:08:44', '1', '2019-09-15 15:08:44');
INSERT INTO `sys_code` VALUES (10214, 'savvy', '理解能力', '', '1', '0', '1', '2019-09-15 15:09:06', '1', '2019-09-15 15:09:06');
INSERT INTO `sys_code` VALUES (10215, 'oral_mucosa', '口腔黏膜', '', '1', '0', '1', '2019-09-15 15:09:26', '1', '2019-09-15 15:09:26');
INSERT INTO `sys_code` VALUES (10216, 'false_tooth', '义齿', '', '1', '0', '1', '2019-09-15 15:10:03', '1', '2019-09-15 15:10:03');
INSERT INTO `sys_code` VALUES (10217, 'contour', '体型', '', '1', '0', '1', '2019-09-15 15:10:29', '1', '2019-09-15 15:10:29');
INSERT INTO `sys_code` VALUES (10218, 'attitude', '家属态度', '', '1', '0', '1', '2019-09-15 15:10:53', '1', '2019-09-15 15:10:53');
INSERT INTO `sys_code` VALUES (10219, 'yes_or_no', '是或否', '', '1', '0', '1', '2019-09-15 16:56:55', '1', '2019-09-15 16:56:55');
INSERT INTO `sys_code` VALUES (10220, 'blood_variety', '血液品种', '', '1', '0', '1', '2019-09-15 17:29:05', '1', '2019-09-15 17:29:05');
INSERT INTO `sys_code` VALUES (10221, 'medical_record_type', '住院病历类型（大病历类型）', '', '1', '0', '1', '2019-09-16 07:35:25', '1', '2019-09-16 07:35:25');
INSERT INTO `sys_code` VALUES (10222, 'is_supplement', '是否补记', '', '1', '0', '1', '2019-09-17 13:51:24', '1', '2019-09-17 13:51:24');
INSERT INTO `sys_code` VALUES (10223, 'meeting_format', '会诊类型', '', '1', '0', '1', '2019-09-17 15:08:04', '1', '2019-09-17 15:08:04');
INSERT INTO `sys_code` VALUES (10224, 'medical_order_detail_type', '医嘱详细类型', '', '1', '0', '1', '2019-09-18 10:45:33', '1', '2019-09-18 10:45:33');
INSERT INTO `sys_code` VALUES (10225, 'permission_type', '权限类型', '1.个人使用 2.科室内使用 3.院内使用', '1', '0', '1', '2019-09-18 15:39:31', '1', '2019-09-18 15:39:31');
INSERT INTO `sys_code` VALUES (10226, 'drug_allergy', '是否过敏', '1:是 2：否', '1', '0', '1', '2019-09-19 15:38:45', '1', '2019-09-19 15:38:51');
INSERT INTO `sys_code` VALUES (10227, 'consciousness_level', '意识水平', '0：清醒，1：对声音有反应；2：对疼痛有反应；3：无反应\'', '1', '0', '1', '2019-09-19 15:44:21', '1', '2019-09-19 15:44:25');
INSERT INTO `sys_code` VALUES (10228, 'degree_pain', '疼痛程度', '分未  2 4 6 8 10', '1', '0', '1', '2019-09-19 17:42:37', '1', '2019-09-19 17:42:42');

SET FOREIGN_KEY_CHECKS = 1;
