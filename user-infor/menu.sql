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

 Date: 20/09/2019 16:57:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `menu_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `menu_url` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `tree_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `has_sub` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `icon_cls` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `open_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `order_num` int(11) NULL DEFAULT NULL,
  `permission` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `company_id` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `del_flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `service_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '服务名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 448 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (334, 'sy', '首页', '', '1001001', '1001', '0', 'home', '0', 1, '', '1', '0', NULL, NULL, '45', '2019-07-29 15:58:45', 'accounts');
INSERT INTO `menu` VALUES (336, 'xtgl', '系统管理', '', '1001002', '1001', '0', 'settings', '0', 2, 'admin', '1', '0', NULL, NULL, '1', '2019-07-26 11:28:54', 'accounts');
INSERT INTO `menu` VALUES (337, 'zzgl', '组织管理', '/api/organization/list/index.ahsj', '1001002001', '1001002', '1', 'input', '0', 1, 'sys.orgnazation', '1', '0', NULL, NULL, '1', '2019-07-26 14:12:28', 'accounts');
INSERT INTO `menu` VALUES (338, 'jsgl', '角色管理', '/api/role/list/index.ahsj', '1001002002', '1001002', '1', 'account_circle', '0', 2, 'sys.role', '1', '0', NULL, NULL, '1', '2019-07-26 14:11:18', 'accounts');
INSERT INTO `menu` VALUES (339, 'cdgl', '菜单管理', '/api/menu/list/index.ahsj', '1001002003', '1001002', '1', 'menu', '0', 3, 'sys.menu', '1', '0', NULL, NULL, '1', '2019-07-26 14:09:43', 'accounts');
INSERT INTO `menu` VALUES (340, 'yhgl', '用户管理', '/api/user/index/index.ahsj', '1001002004', '1001002', '1', 'account_box', '0', 4, 'sys.user', '1', '0', NULL, NULL, '1', '2019-07-26 14:11:28', 'accounts');
INSERT INTO `menu` VALUES (341, 'zdgl', '字典管理', '/api/syscode/index/index.ahsj', '1001002005', '1001002', '1', 'account_balance', '0', 5, 'sys.code', '1', '0', NULL, NULL, '1', '2019-07-26 14:11:45', 'accounts');
INSERT INTO `menu` VALUES (342, 'czgl', '操作管理', '/api/operate/index/index.ahsj', '1001002006', '1001002', '1', 'build', '0', 6, 'sys.operate', '1', '0', NULL, NULL, '1', '2019-07-26 14:10:52', 'accounts');
INSERT INTO `menu` VALUES (344, 'yfgl', '药方系统管理平台', '', '1001003', '1001', '0', 'tab', '0', 3, 'admin', '1', '0', '1', '2019-06-20 21:47:29', '1', '2019-09-20 16:56:54', 'hiscore');
INSERT INTO `menu` VALUES (345, 'yfypgl', '药方管理', '/api/prescription/list/index.ahsj', '1001003001', '1001003', '1', 'assignment', '0', 1, 'sys.prescription', '1', '0', '1', '2019-06-20 22:29:54', '1', '2019-07-26 13:41:13', 'hiscore');
INSERT INTO `menu` VALUES (346, 'hcgl', '耗材系统管理平台', '', '1001004', '1001', '0', 'signal_cellular_connected_no_internet_4_bar', '0', 4, 'sys.prescription', '1', '0', '1', '2019-06-21 14:15:33', '1', '2019-09-20 16:56:04', 'hiscore');
INSERT INTO `menu` VALUES (347, 'ghmk', '挂号系统管理平台', '', '1001005', '1001', '0', 'visibility', '0', 5, 'pharmacy', '1', '0', '1', '2019-06-21 14:18:25', '1', '2019-09-20 16:55:59', 'hiscore');
INSERT INTO `menu` VALUES (348, 'hcnrgl', '耗材内容管理', '/api/hisconsumables/list/index.ahsj', '1001004001', '1001004', '1', 'content_paste', '0', 1, 'sys.prescription', '1', '0', '1', '2019-06-21 14:28:58', '1', '2019-07-26 14:15:47', 'hiscore');
INSERT INTO `menu` VALUES (349, 'hzgh', '患者挂号', '/api/registered/list/index.ahsj', '1001005001', '1001005', '1', 'style', '0', 11, 'patient.registered', '1', '0', '1', '2019-06-21 14:37:48', '1', '2019-07-26 14:17:43', 'hiscore');
INSERT INTO `menu` VALUES (350, 'hsmk', '护士系统管理平台', '', '1001006', '1001', '0', 'people_outline', '0', 6, 'nurse', '1', '0', '1', '2019-06-21 15:51:59', '1', '2019-09-20 16:56:11', 'hiscore');
INSERT INTO `menu` VALUES (351, 'hsxxgl', '护士信息管理', '/api/nurse/list/index.ahsj', '1001006001', '1001006', '1', 'person', '0', 1, 'nursemanager', '1', '0', '1', '2019-06-21 15:54:20', '1', '2019-07-26 13:58:56', 'hiscore');
INSERT INTO `menu` VALUES (352, 'brmk', '病人系统管理平台', '', '1001007', '1001', '0', 'assignment_ind', '0', 7, 'pharmacy', '1', '0', '1', '2019-06-21 17:18:52', '1', '2019-09-20 16:55:53', 'hiscore');
INSERT INTO `menu` VALUES (353, 'brxxgl', '病人信息管理', '/api/patient/list/index.ahsj', '1001007001', '1001007', '1', 'person', '0', 1, 'hiscore.patient', '1', '0', '1', '2019-06-21 17:21:29', '1', '2019-07-01 14:41:44', 'hiscore');
INSERT INTO `menu` VALUES (354, 'ysmk', '医生系统管理平台', '', '1001008', '1001', '0', 'perm_contact_calendar', '0', 8, 'doctor', '1', '0', '1', '2019-06-21 20:08:13', '1', '2019-09-20 16:57:05', 'hiscore');
INSERT INTO `menu` VALUES (356, 'ysxxgl', '医生信息管理', '/api/doctor/list/index.ahsj', '1001008001', '1001008', '1', 'record_voice_over', '0', 1, 'doctor', '1', '0', '1', '2019-06-21 20:17:48', '1', '2019-07-26 14:20:48', 'hiscore');
INSERT INTO `menu` VALUES (357, 'ykmk', '药库系统管理平台', '', '1001009', '1001', '0', 'dns', '0', 9, 'pharmacy', '1', '0', '1', '2019-06-22 14:53:49', '1', '2019-09-20 16:57:00', 'hiscore');
INSERT INTO `menu` VALUES (360, 'ysjz', '医生接诊', '/api/hismedicalrecord/list/index.ahsj', '1001008002', '1001008', '1', 'speaker_notes', '0', 2, 'hismedicalrecord', '1', '0', '1', '2019-06-23 15:00:31', '1', '2019-07-26 14:19:52', 'hiscore');
INSERT INTO `menu` VALUES (361, 'ypgl', '药品管理', '/api/prescription/medicine/list.ahsj', '1001003002', '1001003', '1', 'zoom_in', '0', 2, 'sys.prescription_medicine', '1', '0', '1', '2019-06-23 15:08:17', '1', '2019-07-26 14:15:13', 'hiscore');
INSERT INTO `menu` VALUES (363, 'yprkgl', '药品入库管理', '/api/medienter/mediEnter/index.ahsj', '1001009002', '1001009', '1', 'input', '0', 2, 'medienter', '1', '0', '1', '2019-06-25 22:04:39', '1', '2019-07-26 14:22:32', 'hiscore');
INSERT INTO `menu` VALUES (364, 'ykxxll', '药库信息浏览', '/api/pharmacy/list/index.ahsj', '1001009003', '1001009', '1', 'remove_red_eye', '0', 1, 'pharmacyview', '1', '0', '1', '2019-06-27 21:02:07', '1', '2019-07-26 14:21:26', 'hiscore');
INSERT INTO `menu` VALUES (365, 'ypxxgl', '药品信息管理', '/api/hismedicineinfo/list/index.ahsj', '1001009004', '1001009', '1', 'reorder', '0', 3, 'medicalmanager', '1', '0', '1', '2019-06-27 21:03:44', '1', '2019-07-26 14:22:54', 'hiscore');
INSERT INTO `menu` VALUES (366, 'hckcgl', '耗材库存管理', '/api/hisconsumablesdetails/consumablesdetails/index.ahsj', '1001004003', '1001004', '1', 'input', '1', 3, 'sys.prescription', '1', '0', '1', '2019-06-28 21:22:06', '1', '2019-07-26 13:43:34', 'hiscore');
INSERT INTO `menu` VALUES (367, 'bfmk', '病房系统管理平台', '', '1001010', '1001', '0', 'airline_seat_individual_suite', '0', 10, 'sys.prescription', '1', '0', '1', '2019-07-02 13:21:08', '1', '2019-09-20 16:55:46', 'hiscore');
INSERT INTO `menu` VALUES (368, 'pbmk', '排班系统管理平台', '', '1001011', '1001', '0', 'event', '0', 11, '0', '1', '0', '45', '2019-07-02 13:58:22', '1', '2019-09-20 16:56:26', 'hiscore');
INSERT INTO `menu` VALUES (369, 'pbgl', '排班管理', '/api/typography/dept.ahsj', '1001011001', '1001011', '1', 'insert_invitation', '0', 1, '0', '1', '0', '1', '2019-07-02 14:11:44', '1', '2019-07-26 14:24:01', 'hiscore');
INSERT INTO `menu` VALUES (370, 'bfgl', '病房管理', '/api/hisWards/list/index.ahsj', '1001010001', '1001010', '1', 'hotel', '0', 1, 'sys.ward', '1', '0', '1', '2019-07-02 14:26:06', '1', '2019-07-26 14:23:43', 'hiscore');
INSERT INTO `menu` VALUES (373, 'zzcdgl', '组织菜单管理', '/api/organization/menu/index.ahsj', '1001002007', '1001002', '1', 'apps', '0', 7, 'sys.orgmenu', '1', '0', '1', '2019-07-01 12:47:03', '1', '2019-07-26 14:12:15', 'accounts');
INSERT INTO `menu` VALUES (374, 'ypcgjhjl', '药品采购计划记录', '/api/planRecord/list/index.ahsj', '1001009005', '1001009', '1', 'shopping_cart', '0', 4, 'medicinepurchaserecord', '1', '0', '1', '2019-07-03 14:42:38', '1', '2019-07-26 14:22:20', 'hiscore');
INSERT INTO `menu` VALUES (375, 'hccgmk', '耗材采购模块', '/api/hisconsumablesbuyplan/list/index.ahsj', '1001004004', '1001004', '1', 'shopping_cart', '1', 4, 'sys.prescription', '1', '0', '1', '2019-07-03 20:01:09', '1', '2019-07-26 14:16:52', 'hiscore');
INSERT INTO `menu` VALUES (376, 'sfxm', '收费项目系统管理平台', '', '1001013', '1001', '0', 'payment', '1', 13, 'earn_money_project', '1', '0', '45', '2019-07-04 16:46:02', '1', '2019-09-20 16:56:32', 'hiscore');
INSERT INTO `menu` VALUES (377, 'jzjlcx', '接诊记录查询', '/api/hismedicalrecord/medicalrecordlist/index.ahsj', '1001008003', '1001008', '1', 'search', '0', 3, 'hismedicalrecordinquire', '1', '0', '1', '2019-07-08 14:00:40', '1', '2019-07-26 14:19:03', 'hiscore');
INSERT INTO `menu` VALUES (380, 'ypbsqd', '药品报损清单', '/api/drugLoss/list/index.ahsj', '1001009006', '1001009', '1', 'list', '0', 5, 'ListofDrugDamageReporting', '1', '0', '45', '2019-07-09 14:41:43', '1', '2019-07-26 14:21:32', 'hiscore');
INSERT INTO `menu` VALUES (381, 'ghlxlb', '挂号类型列表', '/api/registeredtype/list/index.ahsj', '1001005002', '1001005', '1', 'storage', '0', 2, 'sys.prescription', '1', '0', '45', '2019-07-09 16:44:41', '1', '2019-07-26 14:17:55', 'hiscore');
INSERT INTO `menu` VALUES (385, 'hcshmk', '报损审核模块', '/api/hisconsumablesdestory/list/index.ahsj', '1001004005', '1001004', '1', 'autorenew', '1', 5, 'sys.prescription', '1', '0', '45', '2019-07-10 10:20:20', '45', '2019-07-31 10:51:25', 'hiscore');
INSERT INTO `menu` VALUES (387, 'sfxmgl', '收费项目管理', '/api/hisProject/index.ahsj', '1001013001', '1001013', '1', 'monetization_on', '0', 1, '0', '1', '0', '45', '2019-07-10 12:41:35', '1', '2019-07-26 14:24:22', 'hiscore');
INSERT INTO `menu` VALUES (388, 'sfxmzhgl', '收费项目组合管理', '/api/hisPproject/combination/index.ahsj', '1001013002', '1001013', '1', 'playlist_add_check', '0', 2, 'combination', '1', '0', '45', '2019-07-11 16:43:05', '1', '2019-07-26 14:25:04', 'hiscore');
INSERT INTO `menu` VALUES (389, 'tymk', '退药系统管理平台', '0', '1001016', '1001', '0', 'redo', '0', 16, 'drugReturnModule', '1', '0', '1', '2019-07-11 17:17:30', '1', '2019-09-20 16:56:40', 'hiscore');
INSERT INTO `menu` VALUES (390, 'tysq', '退药申请', '/api/returnDrug/list/index.ahsj', '1001016001', '1001016', '1', 'system_update', '0', 1, 'applicationForDrugReturn', '1', '0', '1', '2019-07-11 17:19:47', '1', '2019-07-26 14:27:42', 'hiscore');
INSERT INTO `menu` VALUES (391, 'tysh', '退药审核', '/api/returnDrug/listForReview/index.ahsj', '1001016002', '1001016', '1', 'update', '0', 2, 'DrugReturnReview', '1', '0', '1', '2019-07-11 17:22:18', '1', '2019-07-26 14:27:02', 'hiscore');
INSERT INTO `menu` VALUES (396, 'sfmk', '收费台', '', '1001017', '1001', '0', 'shopping_cart', '0', 17, 'sys.Charge', '1', '0', '1', '2019-07-13 16:56:24', '1', '2019-09-10 14:27:29', 'hiscore');
INSERT INTO `menu` VALUES (397, 'sfmx', '门诊收费', '/api/hisTollDetails/outpatient/list/index.ahsj', '1001017001', '1001017', '1', 'attach_money', '1', 1, 'sys.Charge', '1', '0', '1', '2019-07-13 16:57:30', '1', '2019-07-26 14:30:04', 'hiscore');
INSERT INTO `menu` VALUES (398, 'hszmk', '护士站系统管理平台', '', '1001018', '1001', '0', 'people', '1', 18, 'sys.prescription', '1', '0', '45', '2019-07-15 10:53:14', '1', '2019-09-20 16:56:15', 'hiscore');
INSERT INTO `menu` VALUES (399, 'brlbgl', '病人列表管理', '/api/nursestation/list/index.ahsj', '1001018001', '1001018', '1', 'add_to_queue', '1', 1, 'sys.prescription', '1', '0', '45', '2019-07-15 10:55:18', '1', '2019-07-26 14:32:50', 'hiscore');
INSERT INTO `menu` VALUES (400, 'zybrgl', '住院病人管理', '/api/doctor/listForInpatient/index.ahsj', '1001008004', '1001008', '1', 'person', '0', 4, 'inpatientManagement', '1', '0', '45', '2019-07-15 11:23:20', '45', '2019-07-15 11:30:35', 'hiscore');
INSERT INTO `menu` VALUES (401, 'brblgl', '住院病人管理', '/api/nursestation/manage/index.ahsj', '1001018002', '1001018', '1', 'airline_seat_recline_extra', '1', 2, 'sys.prescription', '1', '0', '45', '2019-07-15 11:26:48', '1', '2019-07-26 14:32:27', 'hiscore');
INSERT INTO `menu` VALUES (403, 'zymk', '住院系统管理平台', '', '1001019', '1001', '0', 'local_hotel', '0', 14, 'sys.prescription', '1', '0', '45', '2019-07-16 17:24:21', '1', '2019-09-20 16:57:12', 'hiscore');
INSERT INTO `menu` VALUES (404, 'rydj', '入院登记', '/api/hosptalregist/add/index.ahsj', '1001019001', '1001019', '1', 'receipt', '0', 1, 'sys.prescription', '1', '0', '45', '2019-07-16 17:28:17', '1', '2019-07-26 14:25:31', 'hiscore');
INSERT INTO `menu` VALUES (405, 'zygl', '住院管理', '/api/hishospitalmanage/list/index.ahsj', '1001019002', '1001019', '1', 'streetview', '0', 2, 'hismedicalrecord', '1', '0', '45', '2019-07-16 17:37:18', '1', '2019-07-26 14:26:03', 'hiscore');
INSERT INTO `menu` VALUES (406, 'cwtj', '财务统计', '', '1001020', '1001', '0', 'add_shopping_cart', '0', 19, 'sys.finance', '1', '0', '45', '2019-07-19 16:37:48', '1', '2019-09-19 17:01:24', 'hiscore');
INSERT INTO `menu` VALUES (407, 'ghsftj', '挂号收费统计', '/api/registeredCharge/list/index.ahsj', '1001020001', '1001020', '1', 'attach_money', '1', 1, 'sys.prescription', '1', '0', '45', '2019-07-19 16:39:53', '1', '2019-07-26 14:33:52', 'hiscore');
INSERT INTO `menu` VALUES (408, 'mzzysftj', '门诊住院收费统计', '/api/hisTollRecord/list/index.ahsj', '1001020002', '1001020', '1', 'attach_money', '1', 2, 'sys.prescription', '1', '0', '45', '2019-07-19 16:41:54', '1', '2019-07-26 14:34:02', 'hiscore');
INSERT INTO `menu` VALUES (409, 'zysf', '住院收费', '/api/hisTollDetails/hospital/list/index.ahsj', '1001017002', '1001017', '1', 'assignment_returned', '1', 2, 'histolldetails.hospital', '1', '0', '45', '2019-07-21 19:40:30', '1', '2019-07-26 14:30:36', 'hiscore');
INSERT INTO `menu` VALUES (410, 'hzmbgl', '护嘱模板管理', '/api/nursestation/template/index.ahsj', '1001018003', '1001018', '1', 'airplay', '1', 3, 'sys.prescription', '1', '0', '45', '2019-07-22 14:00:43', '1', '2019-07-26 14:33:08', 'hiscore');
INSERT INTO `menu` VALUES (411, 'cysf', '出院收费', '/api/hisTollDetails/hospital/exit/list/index.ahsj', '1001017003', '1001017', '1', 'assignment_return', '1', 3, 'histolldetails.hospital.exit', '1', '0', '1', '2019-07-22 16:16:21', '1', '2019-07-26 14:30:22', 'hiscore');
INSERT INTO `menu` VALUES (412, 'tysf', '退药收费', '/api/hisTollDetails/drugReturn/list/index.ahsj', '1001017004', '1001017', '1', 'assignment_turned_in', '0', 4, '0', '1', '0', '45', '2019-07-24 19:49:28', '1', '2019-07-26 14:30:51', 'hiscore');
INSERT INTO `menu` VALUES (413, 'yzmbgl', '医嘱模板管理', '/api/medicalOrderTemplate/list/index.ahsj', '1001008005', '1001008', '1', 'person', '0', 5, 'medicalOrderTemplateManage', '1', '0', '1', '2019-07-28 16:52:24', '1', '2019-07-28 16:52:24', 'hiscore');
INSERT INTO `menu` VALUES (414, 'sjtj', '数据统计', '/api/hisdatastatistics/list/index.ahsj', '1001001001', '1001001', '1', 'show_chart', '0', 1, 'sys.datacount', '1', '0', '45', '2019-07-29 15:58:45', '1', '2019-07-30 13:24:31', 'hiscore');
INSERT INTO `menu` VALUES (415, 'ckjlmk', '出库记录', '/api/hisexitconsumables/exitconsumables/index.ahsj', '1001004006', '1001004', '1', 'menu', '1', 6, 'sys.prescription', '1', '0', '45', '2019-07-31 14:03:03', '45', '2019-07-31 14:40:18', 'hiscore');
INSERT INTO `menu` VALUES (418, 'tymx', '退药明细', '/api/returnDrugDetails/drugReturn/show/index.ahsj', '1001016003', '1001016', '1', 'person', '0', 3, 'drugReturn', '1', '0', '45', '2019-08-01 10:04:14', '45', '2019-08-01 14:00:28', 'hiscore');
INSERT INTO `menu` VALUES (419, 'yymx', '用药明细', '/api/returnDrugDetails/drugReturn/useDrug/index.ahsj', '1001016004', '1001016', '1', 'person', '0', 4, 'useDrug', '1', '0', '45', '2019-08-01 13:59:33', '45', '2019-08-01 14:08:49', 'hiscore');
INSERT INTO `menu` VALUES (420, 'zybcsyqk', '住院病床使用情况', '/api/nursestation/useInpatientBeds/index.ahsj', '1001018004', '1001018', '1', 'person', '1', 4, 'sys.prescription', '1', '0', '45', '2019-08-13 16:58:58', '45', '2019-08-13 16:59:23', 'hiscore');
INSERT INTO `menu` VALUES (421, 'hcsqsh', '耗材申请审核', '/api/hisexitconsumables/exitForHos/index.ahsj', '1001004007', '1001004', '1', 'menu', '1', 7, 'sys.prescription', '1', '0', '45', '2019-08-15 16:28:25', '45', '2019-08-15 16:28:33', 'hiscore');
INSERT INTO `menu` VALUES (422, 'xmtfmk', '项目退费系统管理平台', '0', '1001021', '1001', '0', 'redo', '0', 12, 'xmtfmk', '1', '0', '45', '2019-08-16 15:27:12', '1', '2019-09-20 16:56:47', 'hiscore');
INSERT INTO `menu` VALUES (423, 'xmtfmksq', '项目退费申请', '/api/hisProject/refund/index.ahsj', '1001021001', '1001021', '1', 'person', '0', 1, 'xmtusqmk', '1', '0', '45', '2019-08-16 15:33:22', '45', '2019-08-16 15:53:10', 'hiscore');
INSERT INTO `menu` VALUES (428, 'lssjwh', '历史数据维护', '', '1001024', '1001', '0', 'person', '0', 20, 'sys.history', '1', '0', '1', '2019-08-16 17:48:14', '1', '2019-08-16 17:49:52', 'hiscore');
INSERT INTO `menu` VALUES (429, 'lsblxg', '历史病历修改', '/api/hismedicalrecord/historyMedicalrecordPatientinfo/index.ahsj', '1001024001', '1001024', '1', 'person', '0', 1, 'sys.historyForMedicalRecord', '1', '0', '1', '2019-08-16 17:49:52', '45', '2019-08-17 14:53:12', 'hiscore');
INSERT INTO `menu` VALUES (430, 'xmsh', '收费项目审核', '/api/hisprojectreview/list/index.ahsj', '1001013003', '1001013', '1', 'person', '0', 3, '0', '1', '0', '45', '2019-08-16 18:20:08', '45', '2019-08-19 16:15:23', 'hiscore');
INSERT INTO `menu` VALUES (431, 'tfxmsqshmk', '项目退费申请审核', '/api/hisProject/refund/audit/index.ahsj', '1001021002', '1001021', '1', 'person', '0', 2, '1', '1', '0', '45', '2019-08-17 13:03:16', '45', '2019-08-17 13:03:16', 'hiscore');
INSERT INTO `menu` VALUES (432, 'txmmx', '项目退费明细', '/api/hisProject/refund/info/index.ahsj', '1001021003', '1001021', '1', 'person', '0', 3, 'txmmx', '1', '0', '45', '2019-08-17 13:16:47', '45', '2019-08-17 13:16:47', 'hiscore');
INSERT INTO `menu` VALUES (433, 'xmtf', '项目退费', '/api/hisProject/refund/price/index.ahsj', '1001017005', '1001017', '1', 'person', '0', 5, 'xmtf', '1', '0', '45', '2019-08-17 17:31:14', '45', '2019-08-17 17:31:14', 'hiscore');
INSERT INTO `menu` VALUES (434, 'xse', '新生儿', '/api/nursestation/newBorn/index.ahsj', '1001018005', '1001018', '1', 'menu', '1', 5, 'sys.prescription', '1', '0', '45', '2019-09-10 11:36:20', '45', '2019-09-10 11:37:57', 'hiscore');
INSERT INTO `menu` VALUES (435, 'tktf', '退卡退费', '/api/hisTollDetails/returnTheCard/index.ahsj', '1001017006', '1001017', '1', 'assignment_turned_in', '1', 7, '0', '1', '0', '1', '2019-09-10 13:15:12', '1', '2019-09-10 14:30:02', 'hiscore');
INSERT INTO `menu` VALUES (436, 'jzkcz', '就诊卡充值', '/api/hisTollDetails/voucherCenter/index.ahsj', '1001017007', '1001017', '1', 'person', '0', 6, '0', '1', '0', '1', '2019-09-10 14:27:29', '1', '2019-09-10 14:30:10', 'hiscore');
INSERT INTO `menu` VALUES (437, 'jzksftj', '就诊卡收费统计', '/api/hisTollRecord/listVisitCard/index.ahsj', '1001020003', '1001020', '1', 'attach_money', '1', 3, 'sys.prescription', '1', '0', '1', '2019-09-12 14:32:08', '1', '2019-09-12 14:32:08', 'hiscore');
INSERT INTO `menu` VALUES (438, 'ypsftj', '药品收费统计', '/api/hisTollRecord/listDrug/index.ahsj', '1001020004', '1001020', '1', 'attach_money', '1', 4, 'sys.prescription', '1', '0', '1', '2019-09-12 16:42:38', '1', '2019-09-12 16:42:38', 'hiscore');
INSERT INTO `menu` VALUES (439, 'xmsftj', '项目收费统计', '/api/hisTollRecord/listProject/index.ahsj', '1001020005', '1001020', '1', 'attach_money', '1', 5, 'sys.prescription', '1', '0', '1', '2019-09-12 16:43:45', '1', '2019-09-12 16:43:45', 'hiscore');
INSERT INTO `menu` VALUES (440, 'jzkmk', '就诊卡系统管理平台', '', '1001025', '1001', '0', 'menu', '0', 21, 'sys.visitcard', '1', '0', '1', '2019-09-13 13:21:19', '1', '2019-09-20 16:56:20', 'hiscore');
INSERT INTO `menu` VALUES (441, 'tyskmk', '通用刷卡模块', '/api/visitCard/commonSwipe/index.ahsj', '1001025001', '1001025', '1', 'menu', '0', 1, 'sys.commonSwipe', '1', '0', '1', '2019-09-13 13:22:29', '1', '2019-09-13 13:22:29', 'hiscore');
INSERT INTO `menu` VALUES (442, 'blmbgl', '病历模板管理', '/api/medicalTemplate/list/index.ahsj', '1001008006', '1001008', '1', 'person', '0', 6, 'sys.medicalTemplate', '1', '0', '1', '2019-09-14 09:10:44', '1', '2019-09-14 09:10:44', 'hiscore');
INSERT INTO `menu` VALUES (444, 'mzbrgl', '门诊病人管理', '/api/hismedicalrecord/outpatientRecord/index.ahsj', '1001018006', '1001018', '1', 'menu', '1', 6, 'sys.prescription', '1', '0', '1', '2019-09-17 14:16:02', '1', '2019-09-17 14:37:40', 'hiscore');
INSERT INTO `menu` VALUES (445, 'ykpd', '药库盘点', '/api/hisTollRecord/pharmacylist/index.ahsj', '1001020006', '1001020', '1', 'attach_money', '1', 6, 'sys.prescription', '1', '0', '1', '2019-09-18 17:39:27', '1', '2019-09-18 17:39:37', 'hiscore');
INSERT INTO `menu` VALUES (446, 'hljl', '护理记录', '/api/nursestation/nursingRecord/index.ahsj', '1001018007', '1001018', '1', 'menu', '1', 7, 'sys.prescription', '1', '0', '1', '2019-09-19 10:31:24', '1', '2019-09-19 10:31:24', 'hiscore');
INSERT INTO `menu` VALUES (447, 'hcpd', '耗材盘点', '/api/hisTollRecord/consumableInventory/index.ahsj', '1001020007', '1001020', '1', 'attach_money', '1', 7, 'sys.prescription', '1', '0', '1', '2019-09-19 17:01:24', '1', '2019-09-19 17:01:24', 'hiscore');
