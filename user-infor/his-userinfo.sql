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

 Date: 18/09/2019 14:28:57
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
) ENGINE = InnoDB AUTO_INCREMENT = 445 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

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
INSERT INTO `menu` VALUES (344, 'yfgl', '药方模块', '', '1001003', '1001', '0', 'tab', '0', 3, 'admin', '1', '0', '1', '2019-06-20 21:47:29', '1', '2019-07-26 11:34:18', 'hiscore');
INSERT INTO `menu` VALUES (345, 'yfypgl', '药方管理', '/api/prescription/list/index.ahsj', '1001003001', '1001003', '1', 'assignment', '0', 1, 'sys.prescription', '1', '0', '1', '2019-06-20 22:29:54', '1', '2019-07-26 13:41:13', 'hiscore');
INSERT INTO `menu` VALUES (346, 'hcgl', '耗材模块', '', '1001004', '1001', '0', 'signal_cellular_connected_no_internet_4_bar', '0', 4, 'sys.prescription', '1', '0', '1', '2019-06-21 14:15:33', '45', '2019-08-15 16:28:25', 'hiscore');
INSERT INTO `menu` VALUES (347, 'ghmk', '挂号模块', '', '1001005', '1001', '0', 'visibility', '0', 5, 'pharmacy', '1', '0', '1', '2019-06-21 14:18:25', '1', '2019-07-26 13:26:50', 'hiscore');
INSERT INTO `menu` VALUES (348, 'hcnrgl', '耗材内容管理', '/api/hisconsumables/list/index.ahsj', '1001004001', '1001004', '1', 'content_paste', '0', 1, 'sys.prescription', '1', '0', '1', '2019-06-21 14:28:58', '1', '2019-07-26 14:15:47', 'hiscore');
INSERT INTO `menu` VALUES (349, 'hzgh', '患者挂号', '/api/registered/list/index.ahsj', '1001005001', '1001005', '1', 'style', '0', 11, 'patient.registered', '1', '0', '1', '2019-06-21 14:37:48', '1', '2019-07-26 14:17:43', 'hiscore');
INSERT INTO `menu` VALUES (350, 'hsmk', '护士模块', '', '1001006', '1001', '0', 'people_outline', '0', 6, 'nurse', '1', '0', '1', '2019-06-21 15:51:59', '1', '2019-07-26 14:35:08', 'hiscore');
INSERT INTO `menu` VALUES (351, 'hsxxgl', '护士信息管理', '/api/nurse/list/index.ahsj', '1001006001', '1001006', '1', 'person', '0', 1, 'nursemanager', '1', '0', '1', '2019-06-21 15:54:20', '1', '2019-07-26 13:58:56', 'hiscore');
INSERT INTO `menu` VALUES (352, 'brmk', '病人模块', '', '1001007', '1001', '0', 'assignment_ind', '0', 7, 'pharmacy', '1', '0', '1', '2019-06-21 17:18:52', '1', '2019-07-26 14:34:34', 'hiscore');
INSERT INTO `menu` VALUES (353, 'brxxgl', '病人信息管理', '/api/patient/list/index.ahsj', '1001007001', '1001007', '1', 'person', '0', 1, 'hiscore.patient', '1', '0', '1', '2019-06-21 17:21:29', '1', '2019-07-01 14:41:44', 'hiscore');
INSERT INTO `menu` VALUES (354, 'ysmk', '医生模块', '', '1001008', '1001', '0', 'perm_contact_calendar', '0', 8, 'doctor', '1', '0', '1', '2019-06-21 20:08:13', '1', '2019-09-14 09:10:44', 'hiscore');
INSERT INTO `menu` VALUES (356, 'ysxxgl', '医生信息管理', '/api/doctor/list/index.ahsj', '1001008001', '1001008', '1', 'record_voice_over', '0', 1, 'doctor', '1', '0', '1', '2019-06-21 20:17:48', '1', '2019-07-26 14:20:48', 'hiscore');
INSERT INTO `menu` VALUES (357, 'ykmk', '药库模块', '', '1001009', '1001', '0', 'dns', '0', 9, 'pharmacy', '1', '0', '1', '2019-06-22 14:53:49', '1', '2019-07-26 13:23:23', 'hiscore');
INSERT INTO `menu` VALUES (360, 'ysjz', '医生接诊', '/api/hismedicalrecord/list/index.ahsj', '1001008002', '1001008', '1', 'speaker_notes', '0', 2, 'hismedicalrecord', '1', '0', '1', '2019-06-23 15:00:31', '1', '2019-07-26 14:19:52', 'hiscore');
INSERT INTO `menu` VALUES (361, 'ypgl', '药品管理', '/api/prescription/medicine/list.ahsj', '1001003002', '1001003', '1', 'zoom_in', '0', 2, 'sys.prescription_medicine', '1', '0', '1', '2019-06-23 15:08:17', '1', '2019-07-26 14:15:13', 'hiscore');
INSERT INTO `menu` VALUES (363, 'yprkgl', '药品入库管理', '/api/medienter/mediEnter/index.ahsj', '1001009002', '1001009', '1', 'input', '0', 2, 'medienter', '1', '0', '1', '2019-06-25 22:04:39', '1', '2019-07-26 14:22:32', 'hiscore');
INSERT INTO `menu` VALUES (364, 'ykxxll', '药库信息浏览', '/api/pharmacy/list/index.ahsj', '1001009003', '1001009', '1', 'remove_red_eye', '0', 1, 'pharmacyview', '1', '0', '1', '2019-06-27 21:02:07', '1', '2019-07-26 14:21:26', 'hiscore');
INSERT INTO `menu` VALUES (365, 'ypxxgl', '药品信息管理', '/api/hismedicineinfo/list/index.ahsj', '1001009004', '1001009', '1', 'reorder', '0', 3, 'medicalmanager', '1', '0', '1', '2019-06-27 21:03:44', '1', '2019-07-26 14:22:54', 'hiscore');
INSERT INTO `menu` VALUES (366, 'hckcgl', '耗材库存管理', '/api/hisconsumablesdetails/consumablesdetails/index.ahsj', '1001004003', '1001004', '1', 'input', '1', 3, 'sys.prescription', '1', '0', '1', '2019-06-28 21:22:06', '1', '2019-07-26 13:43:34', 'hiscore');
INSERT INTO `menu` VALUES (367, 'bfmk', '病房模块', '', '1001010', '1001', '0', 'airline_seat_individual_suite', '0', 10, 'sys.prescription', '1', '0', '1', '2019-07-02 13:21:08', '45', '2019-07-31 15:41:18', 'hiscore');
INSERT INTO `menu` VALUES (368, 'pbmk', '排班模块', '', '1001011', '1001', '0', 'event', '0', 11, '0', '1', '0', '45', '2019-07-02 13:58:22', '1', '2019-07-26 13:24:16', 'hiscore');
INSERT INTO `menu` VALUES (369, 'pbgl', '排班管理', '/api/typography/dept.ahsj', '1001011001', '1001011', '1', 'insert_invitation', '0', 1, '0', '1', '0', '1', '2019-07-02 14:11:44', '1', '2019-07-26 14:24:01', 'hiscore');
INSERT INTO `menu` VALUES (370, 'bfgl', '病房管理', '/api/hisWards/list/index.ahsj', '1001010001', '1001010', '1', 'hotel', '0', 1, 'sys.ward', '1', '0', '1', '2019-07-02 14:26:06', '1', '2019-07-26 14:23:43', 'hiscore');
INSERT INTO `menu` VALUES (373, 'zzcdgl', '组织菜单管理', '/api/organization/menu/index.ahsj', '1001002007', '1001002', '1', 'apps', '0', 7, 'sys.orgmenu', '1', '0', '1', '2019-07-01 12:47:03', '1', '2019-07-26 14:12:15', 'accounts');
INSERT INTO `menu` VALUES (374, 'ypcgjhjl', '药品采购计划记录', '/api/planRecord/list/index.ahsj', '1001009005', '1001009', '1', 'shopping_cart', '0', 4, 'medicinepurchaserecord', '1', '0', '1', '2019-07-03 14:42:38', '1', '2019-07-26 14:22:20', 'hiscore');
INSERT INTO `menu` VALUES (375, 'hccgmk', '耗材采购模块', '/api/hisconsumablesbuyplan/list/index.ahsj', '1001004004', '1001004', '1', 'shopping_cart', '1', 4, 'sys.prescription', '1', '0', '1', '2019-07-03 20:01:09', '1', '2019-07-26 14:16:52', 'hiscore');
INSERT INTO `menu` VALUES (376, 'sfxm', '收费项目模块', '', '1001013', '1001', '0', 'payment', '1', 13, 'earn_money_project', '1', '0', '45', '2019-07-04 16:46:02', '45', '2019-08-16 18:20:08', 'hiscore');
INSERT INTO `menu` VALUES (377, 'jzjlcx', '接诊记录查询', '/api/hismedicalrecord/medicalrecordlist/index.ahsj', '1001008003', '1001008', '1', 'search', '0', 3, 'hismedicalrecordinquire', '1', '0', '1', '2019-07-08 14:00:40', '1', '2019-07-26 14:19:03', 'hiscore');
INSERT INTO `menu` VALUES (380, 'ypbsqd', '药品报损清单', '/api/drugLoss/list/index.ahsj', '1001009006', '1001009', '1', 'list', '0', 5, 'ListofDrugDamageReporting', '1', '0', '45', '2019-07-09 14:41:43', '1', '2019-07-26 14:21:32', 'hiscore');
INSERT INTO `menu` VALUES (381, 'ghlxlb', '挂号类型列表', '/api/registeredtype/list/index.ahsj', '1001005002', '1001005', '1', 'storage', '0', 2, 'sys.prescription', '1', '0', '45', '2019-07-09 16:44:41', '1', '2019-07-26 14:17:55', 'hiscore');
INSERT INTO `menu` VALUES (385, 'hcshmk', '报损审核模块', '/api/hisconsumablesdestory/list/index.ahsj', '1001004005', '1001004', '1', 'autorenew', '1', 5, 'sys.prescription', '1', '0', '45', '2019-07-10 10:20:20', '45', '2019-07-31 10:51:25', 'hiscore');
INSERT INTO `menu` VALUES (387, 'sfxmgl', '收费项目管理', '/api/hisProject/index.ahsj', '1001013001', '1001013', '1', 'monetization_on', '0', 1, '0', '1', '0', '45', '2019-07-10 12:41:35', '1', '2019-07-26 14:24:22', 'hiscore');
INSERT INTO `menu` VALUES (388, 'sfxmzhgl', '收费项目组合管理', '/api/hisPproject/combination/index.ahsj', '1001013002', '1001013', '1', 'playlist_add_check', '0', 2, 'combination', '1', '0', '45', '2019-07-11 16:43:05', '1', '2019-07-26 14:25:04', 'hiscore');
INSERT INTO `menu` VALUES (389, 'tymk', '退药模块', '0', '1001016', '1001', '0', 'redo', '0', 16, 'drugReturnModule', '1', '0', '1', '2019-07-11 17:17:30', '45', '2019-08-01 13:59:33', 'hiscore');
INSERT INTO `menu` VALUES (390, 'tysq', '退药申请', '/api/returnDrug/list/index.ahsj', '1001016001', '1001016', '1', 'system_update', '0', 1, 'applicationForDrugReturn', '1', '0', '1', '2019-07-11 17:19:47', '1', '2019-07-26 14:27:42', 'hiscore');
INSERT INTO `menu` VALUES (391, 'tysh', '退药审核', '/api/returnDrug/listForReview/index.ahsj', '1001016002', '1001016', '1', 'update', '0', 2, 'DrugReturnReview', '1', '0', '1', '2019-07-11 17:22:18', '1', '2019-07-26 14:27:02', 'hiscore');
INSERT INTO `menu` VALUES (396, 'sfmk', '收费台', '', '1001017', '1001', '0', 'shopping_cart', '0', 17, 'sys.Charge', '1', '0', '1', '2019-07-13 16:56:24', '1', '2019-09-10 14:27:29', 'hiscore');
INSERT INTO `menu` VALUES (397, 'sfmx', '门诊收费', '/api/hisTollDetails/outpatient/list/index.ahsj', '1001017001', '1001017', '1', 'attach_money', '1', 1, 'sys.Charge', '1', '0', '1', '2019-07-13 16:57:30', '1', '2019-07-26 14:30:04', 'hiscore');
INSERT INTO `menu` VALUES (398, 'hszmk', '护士站模块', '', '1001018', '1001', '0', 'people', '1', 18, 'sys.prescription', '1', '0', '45', '2019-07-15 10:53:14', '1', '2019-09-17 14:16:02', 'hiscore');
INSERT INTO `menu` VALUES (399, 'brlbgl', '病人列表管理', '/api/nursestation/list/index.ahsj', '1001018001', '1001018', '1', 'add_to_queue', '1', 1, 'sys.prescription', '1', '0', '45', '2019-07-15 10:55:18', '1', '2019-07-26 14:32:50', 'hiscore');
INSERT INTO `menu` VALUES (400, 'zybrgl', '住院病人管理', '/api/doctor/listForInpatient/index.ahsj', '1001008004', '1001008', '1', 'person', '0', 4, 'inpatientManagement', '1', '0', '45', '2019-07-15 11:23:20', '45', '2019-07-15 11:30:35', 'hiscore');
INSERT INTO `menu` VALUES (401, 'brblgl', '住院病人管理', '/api/nursestation/manage/index.ahsj', '1001018002', '1001018', '1', 'airline_seat_recline_extra', '1', 2, 'sys.prescription', '1', '0', '45', '2019-07-15 11:26:48', '1', '2019-07-26 14:32:27', 'hiscore');
INSERT INTO `menu` VALUES (403, 'zymk', '住院模块', '', '1001019', '1001', '0', 'local_hotel', '0', 14, 'sys.prescription', '1', '0', '45', '2019-07-16 17:24:21', '1', '2019-09-14 11:29:07', 'hiscore');
INSERT INTO `menu` VALUES (404, 'rydj', '入院登记', '/api/hosptalregist/add/index.ahsj', '1001019001', '1001019', '1', 'receipt', '0', 1, 'sys.prescription', '1', '0', '45', '2019-07-16 17:28:17', '1', '2019-07-26 14:25:31', 'hiscore');
INSERT INTO `menu` VALUES (405, 'zygl', '住院管理', '/api/hishospitalmanage/list/index.ahsj', '1001019002', '1001019', '1', 'streetview', '0', 2, 'hismedicalrecord', '1', '0', '45', '2019-07-16 17:37:18', '1', '2019-07-26 14:26:03', 'hiscore');
INSERT INTO `menu` VALUES (406, 'cwtj', '财务统计', '', '1001020', '1001', '0', 'add_shopping_cart', '0', 19, 'sys.finance', '1', '0', '45', '2019-07-19 16:37:48', '1', '2019-09-12 16:43:45', 'hiscore');
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
INSERT INTO `menu` VALUES (422, 'xmtfmk', '项目退费模块', '0', '1001021', '1001', '0', 'redo', '0', 12, 'xmtfmk', '1', '0', '45', '2019-08-16 15:27:12', '45', '2019-08-17 13:16:47', 'hiscore');
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
INSERT INTO `menu` VALUES (440, 'jzkmk', '就诊卡模块', '', '1001025', '1001', '0', 'menu', '0', 21, 'sys.visitcard', '1', '0', '1', '2019-09-13 13:21:19', '1', '2019-09-13 13:22:29', 'hiscore');
INSERT INTO `menu` VALUES (441, 'tyskmk', '通用刷卡模块', '/api/visitCard/commonSwipe/index.ahsj', '1001025001', '1001025', '1', 'menu', '0', 1, 'sys.commonSwipe', '1', '0', '1', '2019-09-13 13:22:29', '1', '2019-09-13 13:22:29', 'hiscore');
INSERT INTO `menu` VALUES (442, 'blmbgl', '病历模板管理', '/api/medicalTemplate/list/index.ahsj', '1001008006', '1001008', '1', 'person', '0', 6, 'sys.medicalTemplate', '1', '0', '1', '2019-09-14 09:10:44', '1', '2019-09-14 09:10:44', 'hiscore');
INSERT INTO `menu` VALUES (444, 'mzbrgl', '门诊病人管理', '/api/hismedicalrecord/outpatientRecord/index.ahsj', '1001018006', '1001018', '1', 'menu', '1', 6, 'sys.prescription', '1', '0', '1', '2019-09-17 14:16:02', '1', '2019-09-17 14:37:40', 'hiscore');

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

-- ----------------------------
-- Table structure for operate
-- ----------------------------
DROP TABLE IF EXISTS `operate`;
CREATE TABLE `operate`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `order_num` bigint(20) NULL DEFAULT NULL,
  `company_id` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `del_flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of operate
-- ----------------------------
INSERT INTO `operate` VALUES (19, 'add', '新增', '新增权限', 1, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO `operate` VALUES (20, 'update', '修改', '修改权限', 1, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO `operate` VALUES (21, 'delete', '删除', '删除权限', 1, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO `operate` VALUES (22, 'select', '查询', '查询权限', 1, '1', '0', '1', '2019-06-18 21:53:24', '1', '2019-06-18 21:53:24');
INSERT INTO `operate` VALUES (25, 'visiting', '接诊', '接诊权限', NULL, '1', '0', '1', '2019-06-19 22:48:19', '1', '2019-06-19 22:48:30');
INSERT INTO `operate` VALUES (26, '454545', '4545', '', NULL, '1', '0', '45', '2019-07-29 15:18:47', '45', '2019-07-29 15:18:47');

-- ----------------------------
-- Table structure for org_menu
-- ----------------------------
DROP TABLE IF EXISTS `org_menu`;
CREATE TABLE `org_menu`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT,
  `org_id` bigint(255) NULL DEFAULT NULL COMMENT '组织id',
  `menu_id` bigint(255) NULL DEFAULT NULL COMMENT '菜单节点id',
  `permssion_perms_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限字段',
  `create_id` bigint(255) NULL DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(255) NULL DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of org_menu
-- ----------------------------
INSERT INTO `org_menu` VALUES (4, 4, 340, 'caiwuke.yhgl', NULL, NULL, NULL, NULL);
INSERT INTO `org_menu` VALUES (8, 4, 338, 'caiwuke.jsgl', NULL, NULL, NULL, NULL);
INSERT INTO `org_menu` VALUES (9, 4, 339, 'caiwuke.cdgl', NULL, NULL, NULL, NULL);
INSERT INTO `org_menu` VALUES (10, 4, 336, 'caiwuke.xtgl', NULL, NULL, NULL, NULL);
INSERT INTO `org_menu` VALUES (11, 4, 337, 'caiwuke.zzgl', NULL, NULL, NULL, NULL);
INSERT INTO `org_menu` VALUES (12, 4, 338, 'caiwuke.jsgl', NULL, NULL, NULL, NULL);
INSERT INTO `org_menu` VALUES (13, 4, 339, 'caiwuke.cdgl', NULL, NULL, NULL, NULL);
INSERT INTO `org_menu` VALUES (14, 4, 1, 'caiwuke.yhgl.add', NULL, NULL, NULL, NULL);
INSERT INTO `org_menu` VALUES (15, 4, 2, 'caiwuke.yhgl.update', NULL, NULL, NULL, NULL);
INSERT INTO `org_menu` VALUES (16, 4, 3, 'caiwuke.yhgl.delete', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tree_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '树ID',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '父ID',
  `type` varchar(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '种类1：部门2：科室',
  `depart_kbn` varchar(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '公司部门区分',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `credit_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '统一社会信用代码',
  `responsible` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系人',
  `address` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `tel_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系方式',
  `order_no` int(11) NULL DEFAULT NULL COMMENT '排序',
  `is_init_data` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `zip_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `company_id` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '公司',
  `del_flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '删除区分',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '创建者ID',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '更新者ID',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '权限标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '组织机构' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES (1, '1', '0', '0', '0', '医疗信息系统', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', 1, NULL, NULL, NULL, NULL, '0', NULL, '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31', NULL);
INSERT INTO `organization` VALUES (4, '1001001', '1001', '2', '2', '财务科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, NULL, NULL, '1', '0', 'admin', '2019-08-09 10:29:31', 'admin', '2019-08-10 10:29:31', 'caiwuke');
INSERT INTO `organization` VALUES (7, '1001', '1', '1', '2', '财务部', '', '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, '', '', '', '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31', 'caiwubu');
INSERT INTO `organization` VALUES (8, '1002', '1', '1', '1', '门诊部', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '238271', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31', NULL);
INSERT INTO `organization` VALUES (9, '1002001', '1002', '2', '1', '口腔科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31', NULL);
INSERT INTO `organization` VALUES (10, '1002002', '1002', '2', '1', '耳鼻喉科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31', NULL);
INSERT INTO `organization` VALUES (11, '1002003', '1002', '2', '1', '内科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31', NULL);
INSERT INTO `organization` VALUES (12, '1003', '1', '1', '2', '药房部', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31', NULL);
INSERT INTO `organization` VALUES (13, '1003001', '1003', '2', '2', '药房科 ', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31', NULL);
INSERT INTO `organization` VALUES (14, '1004', '1', '1', '1', '护士站', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '238271', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31', NULL);
INSERT INTO `organization` VALUES (15, '1004001', '1004', '2', '1', '护士科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31', NULL);
INSERT INTO `organization` VALUES (18, '1002004', '1002', '2', '1', '手外科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:25:03', '1', '2019-08-24 09:25:03', NULL);
INSERT INTO `organization` VALUES (19, '1002005', '1002', '2', '1', '妇科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:25:18', '1', '2019-08-24 09:25:18', NULL);
INSERT INTO `organization` VALUES (20, '1002006', '1002', '2', '1', '骨科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:27:31', '1', '2019-08-24 09:27:31', NULL);
INSERT INTO `organization` VALUES (21, '1002007', '1002', '2', '1', '产科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:27:41', '1', '2019-08-24 09:27:41', NULL);
INSERT INTO `organization` VALUES (22, '1002008', '1002', '2', '1', '神经外科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:27:48', '1', '2019-08-24 09:27:48', NULL);
INSERT INTO `organization` VALUES (23, '1002009', '1002', '2', '1', '胸心外科 肺结节门诊', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:30:06', '1', '2019-08-24 09:30:06', NULL);
INSERT INTO `organization` VALUES (24, '1002010', '1002', '2', '1', '肝胆外科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:30:02', '1', '2019-08-24 09:30:02', NULL);
INSERT INTO `organization` VALUES (25, '1002011', '1002', '2', '1', '胃肠外科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:29:58', '1', '2019-08-24 09:29:58', NULL);
INSERT INTO `organization` VALUES (26, '1002012', '1002', '2', '2', '小儿外科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:29:02', '1', '2019-08-24 09:29:02', NULL);
INSERT INTO `organization` VALUES (27, '1002013', '1002', '2', '1', '甲乳外科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:29:18', '1', '2019-08-24 09:29:18', NULL);
INSERT INTO `organization` VALUES (28, '1002014', '1002', '2', '1', '血管外科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:29:52', '1', '2019-08-24 09:29:52', NULL);
INSERT INTO `organization` VALUES (29, '1002015', '1002', '2', '1', '泌尿外科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:30:27', '1', '2019-08-24 09:30:27', NULL);
INSERT INTO `organization` VALUES (30, '1002016', '1002', '2', '1', '烧伤整形科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:30:38', '1', '2019-08-24 09:30:38', NULL);
INSERT INTO `organization` VALUES (31, '1002017', '1002', '2', '1', '美容整形科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:30:50', '1', '2019-08-24 09:30:50', NULL);
INSERT INTO `organization` VALUES (32, '1002018', '1002', '2', '1', '儿科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:31:16', '1', '2019-08-24 09:31:16', NULL);
INSERT INTO `organization` VALUES (33, '1002019', '1002', '2', '1', '心血管内科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:31:26', '1', '2019-08-24 09:31:26', NULL);
INSERT INTO `organization` VALUES (34, '1002020', '1002', '2', '1', '高血压门诊', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:31:36', '1', '2019-08-24 09:31:36', NULL);
INSERT INTO `organization` VALUES (35, '1002021', '1002', '2', '1', '普通内科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:31:46', '1', '2019-08-24 09:31:46', NULL);
INSERT INTO `organization` VALUES (36, '1002022', '1002', '2', '1', '全科医学科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:32:30', '1', '2019-08-24 09:32:30', NULL);
INSERT INTO `organization` VALUES (37, '1002023', '1002', '2', '1', '老年医学科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:32:44', '1', '2019-08-24 09:32:44', NULL);
INSERT INTO `organization` VALUES (38, '1002024', '1002', '2', '1', '消化内科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:33:40', '1', '2019-08-24 09:33:40', NULL);
INSERT INTO `organization` VALUES (39, '1002025', '1002', '2', '1', '血液内科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:33:47', '1', '2019-08-24 09:33:47', NULL);
INSERT INTO `organization` VALUES (40, '1002026', '1002', '2', '1', '内分泌科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:33:58', '1', '2019-08-24 09:33:58', NULL);
INSERT INTO `organization` VALUES (41, '1002027', '1002', '2', '1', '风湿免疫科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:35:15', '1', '2019-08-24 09:35:15', NULL);
INSERT INTO `organization` VALUES (42, '1002028', '1002', '2', '1', '肾脏内科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:35:34', '1', '2019-08-24 09:35:34', NULL);
INSERT INTO `organization` VALUES (43, '1002029', '1002', '2', '1', '神经内科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:35:42', '1', '2019-08-24 09:35:42', NULL);
INSERT INTO `organization` VALUES (44, '1002030', '1002', '2', '1', '呼吸内科 肺结节门诊', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:36:00', '1', '2019-08-24 09:36:00', NULL);
INSERT INTO `organization` VALUES (45, '1002031', '1002', '2', '1', '呼吸内科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:36:09', '1', '2019-08-24 09:36:09', NULL);
INSERT INTO `organization` VALUES (46, '1002032', '1002', '2', '1', '中医科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:36:29', '1', '2019-08-24 09:36:29', NULL);
INSERT INTO `organization` VALUES (47, '1002033', '1002', '2', '1', '皮肤性病科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:37:21', '1', '2019-08-24 09:37:21', NULL);
INSERT INTO `organization` VALUES (48, '1002034', '1002', '2', '1', '放疗科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:37:54', '1', '2019-08-24 09:37:54', NULL);
INSERT INTO `organization` VALUES (49, '1002035', '1002', '2', '1', '介入科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:38:07', '1', '2019-08-24 09:38:07', NULL);
INSERT INTO `organization` VALUES (50, '1002036', '1002', '2', '1', '性医学科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:38:17', '1', '2019-08-24 09:38:17', NULL);
INSERT INTO `organization` VALUES (51, '1002037', '1002', '2', '1', '生殖医学', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:38:29', '1', '2019-08-24 09:38:29', NULL);
INSERT INTO `organization` VALUES (52, '1002038', '1002', '2', '1', '医学心理科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:38:39', '1', '2019-08-24 09:38:39', NULL);
INSERT INTO `organization` VALUES (53, '1002039', '1002', '2', '1', '麻醉科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:38:47', '1', '2019-08-24 09:38:47', NULL);
INSERT INTO `organization` VALUES (54, '1002040', '1002', '2', '1', '肿瘤内科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:38:56', '1', '2019-08-24 09:38:56', NULL);
INSERT INTO `organization` VALUES (55, '1002041', '1002', '2', '1', '康复医学科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:39:11', '1', '2019-08-24 09:39:11', NULL);
INSERT INTO `organization` VALUES (56, '1002042', '1002', '2', '1', '临床营养科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:39:32', '1', '2019-08-24 09:39:32', NULL);
INSERT INTO `organization` VALUES (57, '1002043', '1002', '2', '1', '普外科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:40:06', '1', '2019-08-24 09:40:06', NULL);
INSERT INTO `organization` VALUES (58, '1002044', '1002', '2', '1', '核医学科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:40:50', '1', '2019-08-24 09:40:50', NULL);
INSERT INTO `organization` VALUES (59, '1002045', '1002', '2', '1', '眼科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:41:05', '1', '2019-08-24 09:41:05', NULL);
INSERT INTO `organization` VALUES (60, '1002046', '1002', '2', '1', '血液净化科', NULL, '陈治才', '芜湖市镜湖区万达广场二期三栋1306', '18555338533', NULL, NULL, '', NULL, '1', '0', '1', '2019-08-24 09:41:12', '1', '2019-08-24 09:41:12', NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `company_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `del_flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', '整个系统的管理员，具有最高权限', '1', NULL, '0', '1', '2019-04-08 16:01:52', NULL, NULL);
INSERT INTO `role` VALUES (5, '挂号员', '挂号模块管理人员', NULL, '1', '0', '1', '2019-06-21 15:09:51', '1', '2019-06-21 15:09:51');
INSERT INTO `role` VALUES (7, '护士', '', NULL, '1', '0', '1', '2019-07-25 09:30:57', '1', '2019-07-25 09:30:57');
INSERT INTO `role` VALUES (8, '医生', '', NULL, '1', '0', '1', '2019-07-25 09:33:02', '1', '2019-07-25 09:33:02');

-- ----------------------------
-- Table structure for role_org_rel
-- ----------------------------
DROP TABLE IF EXISTS `role_org_rel`;
CREATE TABLE `role_org_rel`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `tree_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '角色组织' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_org_rel
-- ----------------------------
INSERT INTO `role_org_rel` VALUES (1, 1, '1');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL,
  `menu_tree_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `operate_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7313 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (174, 5, '1001005', NULL);
INSERT INTO `role_permission` VALUES (175, 5, '1001005001', NULL);
INSERT INTO `role_permission` VALUES (3981, 7, '1001018', NULL);
INSERT INTO `role_permission` VALUES (3982, 7, '1001018001', NULL);
INSERT INTO `role_permission` VALUES (3983, 7, '1001018002', NULL);
INSERT INTO `role_permission` VALUES (3984, 7, '1001018003', NULL);
INSERT INTO `role_permission` VALUES (6284, 8, '1001003', NULL);
INSERT INTO `role_permission` VALUES (6285, 8, '1001003001', NULL);
INSERT INTO `role_permission` VALUES (6286, 8, '1001003002', NULL);
INSERT INTO `role_permission` VALUES (6287, 8, '1001008', NULL);
INSERT INTO `role_permission` VALUES (6288, 8, '1001008002', NULL);
INSERT INTO `role_permission` VALUES (6289, 8, '1001008003', NULL);
INSERT INTO `role_permission` VALUES (6290, 8, '1001008004', NULL);
INSERT INTO `role_permission` VALUES (6291, 8, '1001008005', NULL);
INSERT INTO `role_permission` VALUES (6292, 8, '1001019', NULL);
INSERT INTO `role_permission` VALUES (6293, 8, '1001019001', NULL);
INSERT INTO `role_permission` VALUES (6294, 8, '1001019002', NULL);
INSERT INTO `role_permission` VALUES (7217, 1, '1001001', NULL);
INSERT INTO `role_permission` VALUES (7218, 1, '1001001001', NULL);
INSERT INTO `role_permission` VALUES (7219, 1, '1001002', NULL);
INSERT INTO `role_permission` VALUES (7220, 1, '1001002001', NULL);
INSERT INTO `role_permission` VALUES (7221, 1, '1001002002', NULL);
INSERT INTO `role_permission` VALUES (7222, 1, '1001002003', NULL);
INSERT INTO `role_permission` VALUES (7223, 1, 'update', NULL);
INSERT INTO `role_permission` VALUES (7224, 1, 'delete', NULL);
INSERT INTO `role_permission` VALUES (7225, 1, 'add', NULL);
INSERT INTO `role_permission` VALUES (7226, 1, '1001002004', NULL);
INSERT INTO `role_permission` VALUES (7227, 1, 'sys.user.update', NULL);
INSERT INTO `role_permission` VALUES (7228, 1, 'sys.user.delete', NULL);
INSERT INTO `role_permission` VALUES (7229, 1, 'sys.user.add', NULL);
INSERT INTO `role_permission` VALUES (7230, 1, '1001002005', NULL);
INSERT INTO `role_permission` VALUES (7231, 1, '1001002006', NULL);
INSERT INTO `role_permission` VALUES (7232, 1, '1001002007', NULL);
INSERT INTO `role_permission` VALUES (7233, 1, '1001003', NULL);
INSERT INTO `role_permission` VALUES (7234, 1, '1001003001', NULL);
INSERT INTO `role_permission` VALUES (7235, 1, '1001004', NULL);
INSERT INTO `role_permission` VALUES (7236, 1, '1001004001', NULL);
INSERT INTO `role_permission` VALUES (7237, 1, '1001004003', NULL);
INSERT INTO `role_permission` VALUES (7238, 1, '1001004004', NULL);
INSERT INTO `role_permission` VALUES (7239, 1, '1001004005', NULL);
INSERT INTO `role_permission` VALUES (7240, 1, '1001004006', NULL);
INSERT INTO `role_permission` VALUES (7241, 1, '1001004007', NULL);
INSERT INTO `role_permission` VALUES (7242, 1, '1001005', NULL);
INSERT INTO `role_permission` VALUES (7243, 1, '1001005002', NULL);
INSERT INTO `role_permission` VALUES (7244, 1, '1001005001', NULL);
INSERT INTO `role_permission` VALUES (7245, 1, '1001006', NULL);
INSERT INTO `role_permission` VALUES (7246, 1, '1001006001', NULL);
INSERT INTO `role_permission` VALUES (7247, 1, '1001007', NULL);
INSERT INTO `role_permission` VALUES (7248, 1, '1001007001', NULL);
INSERT INTO `role_permission` VALUES (7249, 1, 'hiscore.patient.add', NULL);
INSERT INTO `role_permission` VALUES (7250, 1, 'hiscore.patient.update', NULL);
INSERT INTO `role_permission` VALUES (7251, 1, 'hiscore.patient.delete', NULL);
INSERT INTO `role_permission` VALUES (7252, 1, 'hiscore.patient.select', NULL);
INSERT INTO `role_permission` VALUES (7253, 1, '1001008', NULL);
INSERT INTO `role_permission` VALUES (7254, 1, '1001008001', NULL);
INSERT INTO `role_permission` VALUES (7255, 1, '1001008002', NULL);
INSERT INTO `role_permission` VALUES (7256, 1, '1001008003', NULL);
INSERT INTO `role_permission` VALUES (7257, 1, '1001008004', NULL);
INSERT INTO `role_permission` VALUES (7258, 1, '1001008005', NULL);
INSERT INTO `role_permission` VALUES (7259, 1, '1001008006', NULL);
INSERT INTO `role_permission` VALUES (7260, 1, '1001009', NULL);
INSERT INTO `role_permission` VALUES (7261, 1, '1001009003', NULL);
INSERT INTO `role_permission` VALUES (7262, 1, '1001009004', NULL);
INSERT INTO `role_permission` VALUES (7263, 1, '1001009005', NULL);
INSERT INTO `role_permission` VALUES (7264, 1, '1001009006', NULL);
INSERT INTO `role_permission` VALUES (7265, 1, '1001010', NULL);
INSERT INTO `role_permission` VALUES (7266, 1, '1001010001', NULL);
INSERT INTO `role_permission` VALUES (7267, 1, 'sys.ward.delete', NULL);
INSERT INTO `role_permission` VALUES (7268, 1, 'sys.ward.select', NULL);
INSERT INTO `role_permission` VALUES (7269, 1, 'sys.ward.add', NULL);
INSERT INTO `role_permission` VALUES (7270, 1, 'sys.ward.update', NULL);
INSERT INTO `role_permission` VALUES (7271, 1, '1001011', NULL);
INSERT INTO `role_permission` VALUES (7272, 1, '1001011001', NULL);
INSERT INTO `role_permission` VALUES (7273, 1, '1001021', NULL);
INSERT INTO `role_permission` VALUES (7274, 1, '1001021001', NULL);
INSERT INTO `role_permission` VALUES (7275, 1, '1001021002', NULL);
INSERT INTO `role_permission` VALUES (7276, 1, '1001021003', NULL);
INSERT INTO `role_permission` VALUES (7277, 1, '1001013', NULL);
INSERT INTO `role_permission` VALUES (7278, 1, '1001013001', NULL);
INSERT INTO `role_permission` VALUES (7279, 1, '1001013002', NULL);
INSERT INTO `role_permission` VALUES (7280, 1, '1001013003', NULL);
INSERT INTO `role_permission` VALUES (7281, 1, '1001019', NULL);
INSERT INTO `role_permission` VALUES (7282, 1, '1001019001', NULL);
INSERT INTO `role_permission` VALUES (7283, 1, '1001019002', NULL);
INSERT INTO `role_permission` VALUES (7284, 1, '1001016', NULL);
INSERT INTO `role_permission` VALUES (7285, 1, '1001016001', NULL);
INSERT INTO `role_permission` VALUES (7286, 1, '1001016002', NULL);
INSERT INTO `role_permission` VALUES (7287, 1, '1001016003', NULL);
INSERT INTO `role_permission` VALUES (7288, 1, '1001016004', NULL);
INSERT INTO `role_permission` VALUES (7289, 1, '1001017', NULL);
INSERT INTO `role_permission` VALUES (7290, 1, '1001017001', NULL);
INSERT INTO `role_permission` VALUES (7291, 1, '1001017002', NULL);
INSERT INTO `role_permission` VALUES (7292, 1, '1001017003', NULL);
INSERT INTO `role_permission` VALUES (7293, 1, '1001017004', NULL);
INSERT INTO `role_permission` VALUES (7294, 1, '1001017005', NULL);
INSERT INTO `role_permission` VALUES (7295, 1, '1001017007', NULL);
INSERT INTO `role_permission` VALUES (7296, 1, '1001017006', NULL);
INSERT INTO `role_permission` VALUES (7297, 1, '1001018', NULL);
INSERT INTO `role_permission` VALUES (7298, 1, '1001018001', NULL);
INSERT INTO `role_permission` VALUES (7299, 1, '1001018002', NULL);
INSERT INTO `role_permission` VALUES (7300, 1, '1001018003', NULL);
INSERT INTO `role_permission` VALUES (7301, 1, '1001018004', NULL);
INSERT INTO `role_permission` VALUES (7302, 1, '1001018005', NULL);
INSERT INTO `role_permission` VALUES (7303, 1, '1001018006', NULL);
INSERT INTO `role_permission` VALUES (7304, 1, '1001020', NULL);
INSERT INTO `role_permission` VALUES (7305, 1, '1001020001', NULL);
INSERT INTO `role_permission` VALUES (7306, 1, '1001020002', NULL);
INSERT INTO `role_permission` VALUES (7307, 1, '1001020003', NULL);
INSERT INTO `role_permission` VALUES (7308, 1, '1001020004', NULL);
INSERT INTO `role_permission` VALUES (7309, 1, '1001024', NULL);
INSERT INTO `role_permission` VALUES (7310, 1, '1001024001', NULL);
INSERT INTO `role_permission` VALUES (7311, 1, '1001025', NULL);
INSERT INTO `role_permission` VALUES (7312, 1, '1001025001', NULL);

-- ----------------------------
-- Table structure for sys_attachment_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_attachment_info`;
CREATE TABLE `sys_attachment_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `relate_id` bigint(20) NULL DEFAULT NULL COMMENT '关联主键',
  `relate_page` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `relate_key` varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '关联附件key',
  `file_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '附件名称',
  `file_org_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '附件原名',
  `location` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '附件路径',
  `file_size` varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '附件大小',
  `file_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '附件种类',
  `upload_user` bigint(20) NULL DEFAULT NULL COMMENT '上传人',
  `upload_date` datetime(0) NULL DEFAULT NULL COMMENT '上传日期',
  `file_type_key` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `file_type_value` varchar(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `order_no` int(11) NULL DEFAULT NULL,
  `upload_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `company_id` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '公司',
  `del_flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '删除区分',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '创建者ID',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '更新者ID',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version_no` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '附件' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_attachment_info
-- ----------------------------
INSERT INTO `sys_attachment_info` VALUES (16, 8, 'paghe', 'ww', '49502541298800.jpg', '0.jpg', 'D:/upload/20190904/49502541298800.jpg', '15.7 KB', '.jpg', 45, '2019-09-04 09:43:39', NULL, NULL, NULL, NULL, '1', '0', '45', '2019-09-04 09:43:39', '45', '2019-09-04 09:43:39', 0);
INSERT INTO `sys_attachment_info` VALUES (17, 8, 'paghe', 'ww', '72353731171300.jpg', '1.jpg', 'D:/upload/20190904/72353731171300.jpg', '119 KB', '.jpg', 45, '2019-09-04 16:04:30', NULL, NULL, NULL, NULL, '1', '0', '45', '2019-09-04 16:04:30', '45', '2019-09-04 16:04:30', 0);
INSERT INTO `sys_attachment_info` VALUES (18, 8, 'paghe', 'ww', '72353731171300.jpg', '2.jpg', 'D:/upload/20190904/72353731171300.jpg', '3.3 KB', '.jpg', 45, '2019-09-04 16:04:30', NULL, NULL, NULL, NULL, '1', '0', '45', '2019-09-04 16:04:30', '45', '2019-09-04 16:04:30', 0);
INSERT INTO `sys_attachment_info` VALUES (22, 8, '测试', '测试', '23178960157200.jpg', 'timg.jpg', 'D:/upload/20190906/23178960157200.jpg', '15.1 KB', '.jpg', 45, '2019-09-06 15:21:38', NULL, NULL, NULL, NULL, '1', '0', '45', '2019-09-06 15:21:38', '45', '2019-09-06 15:21:38', 0);
INSERT INTO `sys_attachment_info` VALUES (23, 8, '测试', '测试', '23178960157200.jpg', 'u=2457383618,2793215635&fm=26&gp=0.jpg', 'D:/upload/20190906/23178960157200.jpg', '17.9 KB', '.jpg', 45, '2019-09-06 15:21:38', NULL, NULL, NULL, NULL, '1', '0', '45', '2019-09-06 15:21:38', '45', '2019-09-06 15:21:38', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 10225 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

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

-- ----------------------------
-- Table structure for sys_code_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_code_detail`;
CREATE TABLE `sys_code_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `value` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `type_id` bigint(20) NULL DEFAULT NULL,
  `order_num` int(11) NULL DEFAULT NULL,
  `del_flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 713 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_code_detail
-- ----------------------------
INSERT INTO `sys_code_detail` VALUES (338, '1', '男', '', 10102, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (339, '2', '女', '', 10102, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (374, '1', '已授权', '', 10084, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (375, '2', '未授权', '', 10084, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (399, '1', '口服剂', '药品类型未口服使用的药品类型', 10117, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (400, '2', '片药', '片药', 10117, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (401, '1', '启用', '', 10118, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (402, '2', '不启用', '', 10118, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (403, '0', '打开', '', 10119, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (404, '1', '不打开', '', 10119, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (405, '1', '已婚', '', 10120, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (406, '2', '未婚', '', 10120, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (407, '1', '已付', '', 10121, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (408, '2', '未付', '', 10121, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (409, '1', '已打印', '', 10122, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (410, '2', '未打印', '', 10122, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (411, '1', '已作废', '', 10123, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (412, '2', '未作废', '', 10123, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (413, '1', '已结算', '', 10124, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (414, '2', '未结算', '', 10124, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (415, '2', '平诊', '', 10125, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (416, '1', '已下架', NULL, 10126, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (417, '2', '未下架', NULL, 10126, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (418, '1', '是', NULL, 10127, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (419, '2', '否', '', 10127, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (420, '1', '是', NULL, 10128, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (421, '2', '否', '', 10128, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (422, '1', '已停用', '1', 10129, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (423, '2', '未停用', '2', 10129, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (424, '1', '非限制', NULL, 10130, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (425, '2', '限制', NULL, 10130, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (426, '3', '特殊', NULL, 10130, 3, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (427, '1', '甲类', NULL, 10131, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (428, '2', '乙类', NULL, 10131, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (429, '3', '丙类', NULL, 10131, 3, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (430, '4', '非目录', NULL, 10131, 4, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (431, '1', '是', '', 10132, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (432, '2', '否', NULL, 10132, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (433, '1', '是', NULL, 10133, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (434, '2', '否', NULL, 10133, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (435, '1', '芜湖xx医药公司', NULL, 10134, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (436, '2', '合肥xx医药公司', NULL, 10134, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (437, '1', '现金', '', 10135, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (438, '1', '急诊', '', 10125, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (439, '1', '白班', NULL, 10136, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (440, '2', '夜班', NULL, 10136, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (441, '3', '节假日', NULL, 10136, 3, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (442, '4', '双休日', NULL, 10136, 4, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (443, '1', '候诊', NULL, 10137, 0, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (444, '2', '已就诊', NULL, 10137, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (445, '1', '急诊', NULL, 10138, 1, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (446, '2', '平诊', NULL, 10138, 2, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (447, '1', '一级病房', NULL, 10139, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (448, '2', '二级病房', NULL, 10139, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (449, '3', '三级病房', NULL, 10139, 3, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (450, '4', '四级病房', NULL, 10139, 4, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (451, '1', '已完成', NULL, 10140, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (452, '2', '未完成', NULL, 10140, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (453, '1', '医疗单位', '', 10141, 1, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (454, '2', '非医疗单位', '', 10141, 2, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (455, '1', '检查费', '', 10142, 1, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (456, '2', '化验费', '', 10142, 2, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (457, '1', '普通病床', NULL, 10143, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (458, '2', '特殊病床', NULL, 10143, 2, '0', '2', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (459, '1', '失效', NULL, 10144, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (460, '2', '过期', NULL, 10144, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (461, '3', '退回', NULL, 10144, 3, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (462, '1', '自费', NULL, 10145, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (463, '2', '医保', NULL, 10145, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (464, '1', '一级护理', NULL, 10146, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (465, '2', '二级护理', NULL, 10146, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (466, '3', '三级护理', NULL, 10146, 3, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (467, '4', '四级护理', NULL, 10146, 4, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (468, '5', '特技监护', NULL, 10146, 5, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (469, '6', '重症监护', NULL, 10146, 6, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (470, '1', '审核通过', NULL, 10147, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (471, '2', '审核未通过', NULL, 10147, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (472, '1', '其他医院', NULL, 10148, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (473, '2', '门诊', NULL, 10148, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (474, '1', '诊查', NULL, 10179, 1, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (475, '2', '化验', NULL, 10179, 2, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (476, '3', '治疗', NULL, 10179, 3, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (477, '4', '注射', NULL, 10179, 4, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (478, '5', '手术', NULL, 10179, 5, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (479, '6', 'X光', NULL, 10179, 6, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (480, '7', '放射', NULL, 10179, 7, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (481, '8', '输氧', NULL, 10179, 8, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (482, '9', '卫材', NULL, 10179, 9, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (483, '10', '专科', NULL, 10179, 10, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (484, '11', '材料', NULL, 10179, 11, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (485, '12', '监护', NULL, 10179, 12, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (486, '13', '观察', NULL, 10179, 13, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (487, '14', '检查', NULL, 10179, 14, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (488, '15', 'B超', NULL, 10179, 15, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (489, '16', '心电', NULL, 10179, 16, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (490, '17', '理疗', NULL, 10179, 17, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (491, '18', '处理', NULL, 10179, 18, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (492, '19', '住院', NULL, 10179, 19, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (493, '20', '其他', NULL, 10179, 20, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (494, '21', '输血', NULL, 10179, 21, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (495, '22', 'CT', NULL, 10179, 22, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (496, '23', '麻醉', NULL, 10179, 23, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (497, '24', '熬药', NULL, 10179, 24, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (498, '25', '体检', NULL, 10179, 25, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (499, '26', '挂号', NULL, 10179, 26, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (500, '27', '床费', NULL, 10179, 27, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (501, '28', '调温', NULL, 10179, 28, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (502, '29', '镶复', NULL, 10179, 29, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (503, '30', '正畸', NULL, 10179, 30, '0', '1', '2019-08-09 10:29:31', NULL, '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (504, '1', '入库', '', 10180, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (505, '2', '不入库', '', 10180, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (506, '3', '未审核', '', 10147, 3, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (507, '1', '未出院', NULL, 10181, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (508, '2', '已出院', NULL, 10181, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (509, '1', '未审核', NULL, 10182, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (510, '2', '审核未通过', NULL, 10182, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (511, '1', '药品', NULL, 10183, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (512, '2', '项目', NULL, 10183, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (513, '3', '已审核', NULL, 10182, 3, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (514, '1', '已皮试', '', 10184, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (515, '2', '未皮试', '', 10184, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (518, '1', '已停嘱', '', 10186, 1, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (519, '2', '未停嘱', '', 10186, 2, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (520, '1', '长期医嘱', '', 10187, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (521, '2', '短期医嘱', '', 10187, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (522, '1', '已校对', '', 10188, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (523, '2', '未校对', '', 10188, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (524, '1', '已核准', '', 10189, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (525, '2', '未核准', '', 10189, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (526, '3', '住院', '', 10183, 3, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (527, '1', '门诊收费', NULL, 10190, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (528, '2', '住院收费', NULL, 10190, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (529, '1', '药品收费', NULL, 10191, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (530, '2', '项目收费', NULL, 10191, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (531, '1', '已出药', '', 10192, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (532, '2', '未出药', '', 10192, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (535, '1', '长期护嘱', '', 10194, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (536, '2', '短期护嘱', '', 10194, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (544, '1', 'qh', '每小时一次', 10185, 1, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (545, '2', 'q2h', '每两小时一次', 10185, 2, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (546, '3', 'q4h', '每四小时一次', 10185, 3, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (547, '4', 'q6h', '每六小时一次', 10185, 4, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (548, '5', 'qd', '每日一次', 10185, 5, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (549, '6', 'bid', '每日两次', 10185, 6, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (550, '7', 'tid', '每日三次', 10185, 7, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (551, '8', 'qid', '每日四次', 10185, 8, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (552, '9', 'qn', '每晚一次', 10185, 9, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (553, '10', 'qod', '隔日一次', 10185, 10, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (554, '11', 'biw', '每周两次', 10185, 11, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (555, '12', 'hs', '临睡前', 10185, 12, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (556, '13', 'am', '上午', 10185, 13, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (557, '14', 'pm', '下午', 10185, 14, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (558, '15', 'St', '立即', 10185, 15, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (559, '16', 'DC', '停止、取消', 10185, 16, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (560, '17', 'prn', '需要时（长期）', 10185, 17, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (561, '18', 'sos', '需要时（限用一次，12小时内有效）', 10185, 18, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (562, '19', 'ac', '饭前', 10185, 19, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (563, '20', 'pc', '饭后', 10185, 20, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (564, '21', '12n', '中午12点', 10185, 21, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (565, '22', '12mn', '午夜12点', 10185, 22, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (570, '3', '住院退费', NULL, 10190, 3, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (571, '4', '药品退费', NULL, 10190, 4, '0', '1', '2019-08-09 10:29:31', '1', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (572, '1', '已退款', '', 10195, 1, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (573, '2', '未退款', '', 10195, 2, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (575, '1', '医用消毒剂类', '', 10196, 1, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (576, '2', '注射穿刺类及医用高分子材料类', '', 10196, 2, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (577, '3', '医用卫生材料及敷料类', '', 10196, 3, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (578, '4', '口腔耗材类', '', 10196, 4, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (579, '5', '手术室部分常用医用耗材类', '', 10196, 5, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (580, '6', '医技耗材类', '', 10196, 6, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (581, '7', '麻醉耗材类', '', 10196, 7, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (582, '8', '眼科类', '', 10196, 8, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (583, '9', '心胸外科类', '', 10196, 9, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (584, '10', '介入类', '', 10196, 10, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (588, '11', '骨科类', '', 10196, 11, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (589, '12', '手术室类', '', 10196, 12, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (590, '1', '已出库', '无', 10197, 1, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (591, '2', '未出库', '无', 10197, 2, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (592, '4', '药品退费', '', 10191, 4, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (593, '1', '已退回', '', 10198, 1, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (594, '2', '未退回', '', 10198, 2, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (595, '1', '已删除', '', 10199, 1, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (596, '2', '未删除', '', 10199, 2, '0', '45', '2019-08-09 10:29:31', '45', '2019-08-10 10:29:31');
INSERT INTO `sys_code_detail` VALUES (597, '3', '住院收费', '住院病床费用', 10191, 3, '0', '45', '2019-08-13 14:51:14', '45', '2019-08-13 14:51:14');
INSERT INTO `sys_code_detail` VALUES (598, '1', '已检查', '诊疗项目是否检查', 10200, 1, '0', '45', '2019-08-17 17:27:03', '45', '2019-08-17 17:27:03');
INSERT INTO `sys_code_detail` VALUES (599, '2', '未检查', '诊疗项目是否检查', 10200, 2, '0', '45', '2019-08-17 17:27:20', '45', '2019-08-17 17:27:20');
INSERT INTO `sys_code_detail` VALUES (600, '1', '门诊', '', 10201, 1, '0', '45', '2019-08-19 18:04:40', '45', '2019-08-19 18:04:40');
INSERT INTO `sys_code_detail` VALUES (601, '2', '住院', '', 10201, 2, '0', '45', '2019-08-19 18:04:50', '45', '2019-08-19 18:04:50');
INSERT INTO `sys_code_detail` VALUES (602, '0', '选择护理等级', '', 10146, 0, '0', '1', '2019-08-26 17:17:08', '1', '2019-08-26 17:17:08');
INSERT INTO `sys_code_detail` VALUES (603, '5', '退卡退费', '', 10190, 5, '0', '1', '2019-09-10 13:49:29', '1', '2019-09-10 13:49:29');
INSERT INTO `sys_code_detail` VALUES (604, '6', '就诊卡充值', '', 10190, 6, '0', '1', '2019-09-10 14:29:09', '1', '2019-09-10 14:29:09');
INSERT INTO `sys_code_detail` VALUES (605, '2', '就诊卡', '', 10135, 2, '0', '1', '2019-09-10 15:30:39', '1', '2019-09-10 15:30:39');
INSERT INTO `sys_code_detail` VALUES (606, '1', '就诊卡挂号', '', 10202, 1, '0', '1', '2019-09-10 16:01:51', '1', '2019-09-10 16:01:51');
INSERT INTO `sys_code_detail` VALUES (607, '2', '无卡挂号', '', 10202, 2, '0', '1', '2019-09-10 16:01:59', '1', '2019-09-10 16:01:59');
INSERT INTO `sys_code_detail` VALUES (608, '7', '门诊刷卡', '', 10190, 7, '0', '1', '2019-09-11 09:56:58', '1', '2019-09-11 09:56:58');
INSERT INTO `sys_code_detail` VALUES (609, '1', '已注销', '', 10203, 1, '0', '1', '2019-09-11 11:33:19', '1', '2019-09-11 11:33:19');
INSERT INTO `sys_code_detail` VALUES (610, '2', '未注销', '', 10203, 2, '0', '1', '2019-09-11 11:33:30', '1', '2019-09-11 11:33:30');
INSERT INTO `sys_code_detail` VALUES (611, '3', '24h后死亡', '', 10181, 3, '0', '1', '2019-09-15 10:28:15', '1', '2019-09-15 10:28:15');
INSERT INTO `sys_code_detail` VALUES (612, '1', '同意', '', 10204, 1, '0', '1', '2019-09-15 10:52:55', '1', '2019-09-15 10:52:55');
INSERT INTO `sys_code_detail` VALUES (613, '2', '未同意', '', 10204, 2, '0', '1', '2019-09-15 10:53:04', '1', '2019-09-15 10:53:04');
INSERT INTO `sys_code_detail` VALUES (614, '1', '已批准', '', 10205, 1, '0', '1', '2019-09-15 10:53:18', '1', '2019-09-15 10:53:18');
INSERT INTO `sys_code_detail` VALUES (615, '2', '未批准/无负责人', '', 10205, 2, '0', '1', '2019-09-15 10:53:29', '1', '2019-09-15 10:53:29');
INSERT INTO `sys_code_detail` VALUES (616, '1', '有', '', 10206, 1, '0', '1', '2019-09-15 10:53:43', '1', '2019-09-15 10:53:43');
INSERT INTO `sys_code_detail` VALUES (617, '2', '无', '', 10206, 2, '0', '1', '2019-09-15 10:53:51', '1', '2019-09-15 10:53:51');
INSERT INTO `sys_code_detail` VALUES (618, '1', '已签名', '', 10207, 1, '0', '1', '2019-09-15 10:54:13', '1', '2019-09-15 10:54:13');
INSERT INTO `sys_code_detail` VALUES (619, '2', '未签名', '', 10207, 2, '0', '1', '2019-09-15 10:54:19', '1', '2019-09-15 10:54:19');
INSERT INTO `sys_code_detail` VALUES (620, '1', 'A型血', '', 10208, 1, '0', '1', '2019-09-15 10:56:24', '1', '2019-09-15 10:56:24');
INSERT INTO `sys_code_detail` VALUES (621, '2', 'B型血', '', 10208, 2, '0', '1', '2019-09-15 10:56:37', '1', '2019-09-15 10:56:37');
INSERT INTO `sys_code_detail` VALUES (622, '3', 'AB型血', '', 10208, 3, '0', '1', '2019-09-15 10:56:47', '1', '2019-09-15 10:56:47');
INSERT INTO `sys_code_detail` VALUES (623, '4', 'O型血', '', 10208, 4, '0', '1', '2019-09-15 10:56:57', '1', '2019-09-15 10:56:57');
INSERT INTO `sys_code_detail` VALUES (624, '1', '清楚', '', 10209, 1, '0', '1', '2019-09-15 15:11:32', '1', '2019-09-15 15:11:32');
INSERT INTO `sys_code_detail` VALUES (625, '2', '嗜睡', '', 10209, 2, '0', '1', '2019-09-15 15:11:43', '1', '2019-09-15 15:11:43');
INSERT INTO `sys_code_detail` VALUES (626, '3', '意识模糊', '', 10209, 3, '0', '1', '2019-09-15 15:12:14', '1', '2019-09-15 15:12:14');
INSERT INTO `sys_code_detail` VALUES (627, '4', '昏睡', '', 10209, 4, '0', '1', '2019-09-15 15:12:32', '1', '2019-09-15 15:12:32');
INSERT INTO `sys_code_detail` VALUES (628, '5', '浅昏迷', '', 10209, 5, '0', '1', '2019-09-15 15:12:50', '1', '2019-09-15 15:12:50');
INSERT INTO `sys_code_detail` VALUES (629, '6', '深昏迷', '', 10209, 6, '0', '1', '2019-09-15 15:13:04', '1', '2019-09-15 15:13:04');
INSERT INTO `sys_code_detail` VALUES (630, '7', '痴呆', '', 10209, 7, '0', '1', '2019-09-15 15:13:25', '1', '2019-09-15 15:13:25');
INSERT INTO `sys_code_detail` VALUES (631, '1', '正常', '', 10210, 1, '0', '1', '2019-09-15 15:13:51', '1', '2019-09-15 15:13:51');
INSERT INTO `sys_code_detail` VALUES (632, '2', '淡漠', '', 10210, 2, '0', '1', '2019-09-15 15:14:07', '1', '2019-09-15 15:14:07');
INSERT INTO `sys_code_detail` VALUES (633, '3', '痛苦', '', 10210, 3, '0', '1', '2019-09-15 15:14:17', '1', '2019-09-15 15:14:17');
INSERT INTO `sys_code_detail` VALUES (634, '4', '紧张', '', 10210, 4, '0', '1', '2019-09-15 15:14:27', '1', '2019-09-15 15:14:27');
INSERT INTO `sys_code_detail` VALUES (635, '1', '稳定', '', 10211, 1, '0', '1', '2019-09-15 15:14:48', '1', '2019-09-15 15:14:48');
INSERT INTO `sys_code_detail` VALUES (636, '2', '烦躁', '', 10211, 2, '0', '1', '2019-09-15 15:14:58', '1', '2019-09-15 15:14:58');
INSERT INTO `sys_code_detail` VALUES (637, '3', '紧张', '', 10211, 3, '0', '1', '2019-09-15 15:15:12', '1', '2019-09-15 15:15:12');
INSERT INTO `sys_code_detail` VALUES (638, '4', '恐惧', '', 10211, 4, '0', '1', '2019-09-15 15:15:45', '1', '2019-09-15 15:15:45');
INSERT INTO `sys_code_detail` VALUES (639, '5', '焦虑', '', 10211, 5, '0', '1', '2019-09-15 15:15:59', '1', '2019-09-15 15:15:59');
INSERT INTO `sys_code_detail` VALUES (640, '6', '抑郁', '', 10211, 6, '0', '1', '2019-09-15 15:16:09', '1', '2019-09-15 15:16:09');
INSERT INTO `sys_code_detail` VALUES (641, '7', '绝望', '', 10211, 7, '0', '1', '2019-09-15 15:16:25', '1', '2019-09-15 15:16:25');
INSERT INTO `sys_code_detail` VALUES (642, '1', '正常', '', 10212, 1, '0', '1', '2019-09-15 15:16:49', '1', '2019-09-15 15:16:49');
INSERT INTO `sys_code_detail` VALUES (643, '2', '低下', '', 10212, 2, '0', '1', '2019-09-15 15:17:00', '1', '2019-09-15 15:17:00');
INSERT INTO `sys_code_detail` VALUES (644, '3', '无法沟通', '', 10212, 3, '0', '1', '2019-09-15 15:17:11', '1', '2019-09-15 15:17:11');
INSERT INTO `sys_code_detail` VALUES (645, '1', '语言', '', 10213, 1, '0', '1', '2019-09-15 15:17:37', '1', '2019-09-15 15:17:37');
INSERT INTO `sys_code_detail` VALUES (646, '2', '文字', '', 10213, 2, '0', '1', '2019-09-15 15:17:52', '1', '2019-09-15 15:17:52');
INSERT INTO `sys_code_detail` VALUES (647, '3', '手势', '', 10213, 3, '0', '1', '2019-09-15 15:18:03', '1', '2019-09-15 15:18:03');
INSERT INTO `sys_code_detail` VALUES (648, '4', '代诉', '', 10213, 4, '0', '1', '2019-09-15 15:18:19', '1', '2019-09-15 15:18:19');
INSERT INTO `sys_code_detail` VALUES (649, '1', '良好', '', 10214, 1, '0', '1', '2019-09-15 15:18:43', '1', '2019-09-15 15:18:43');
INSERT INTO `sys_code_detail` VALUES (650, '2', '一般', '', 10214, 2, '0', '1', '2019-09-15 15:18:52', '1', '2019-09-15 15:18:52');
INSERT INTO `sys_code_detail` VALUES (651, '3', '差', '', 10214, 3, '0', '1', '2019-09-15 15:19:06', '1', '2019-09-15 15:19:06');
INSERT INTO `sys_code_detail` VALUES (652, '1', '正常', '', 10215, 1, '0', '1', '2019-09-15 15:19:22', '1', '2019-09-15 15:19:22');
INSERT INTO `sys_code_detail` VALUES (653, '2', '充血', '', 10215, 2, '0', '1', '2019-09-15 15:19:37', '1', '2019-09-15 15:19:37');
INSERT INTO `sys_code_detail` VALUES (654, '3', '破损', '', 10215, 3, '0', '1', '2019-09-15 15:19:47', '1', '2019-09-15 15:19:47');
INSERT INTO `sys_code_detail` VALUES (655, '4', '霉菌感染', '', 10215, 4, '0', '1', '2019-09-15 15:20:08', '1', '2019-09-15 15:20:08');
INSERT INTO `sys_code_detail` VALUES (656, '1', '无义齿', '', 10216, 1, '0', '1', '2019-09-15 15:20:35', '1', '2019-09-15 15:20:35');
INSERT INTO `sys_code_detail` VALUES (657, '2', '有义齿', '', 10216, 2, '0', '1', '2019-09-15 15:20:43', '1', '2019-09-15 15:20:43');
INSERT INTO `sys_code_detail` VALUES (658, '5', '溃疡', '', 10215, 5, '0', '1', '2019-09-15 15:22:56', '1', '2019-09-15 15:22:56');
INSERT INTO `sys_code_detail` VALUES (659, '1', '正常', '', 10217, 1, '0', '1', '2019-09-15 15:23:16', '1', '2019-09-15 15:23:16');
INSERT INTO `sys_code_detail` VALUES (660, '2', '肥胖', '', 10217, 2, '0', '1', '2019-09-15 15:24:07', '1', '2019-09-15 15:24:07');
INSERT INTO `sys_code_detail` VALUES (661, '3', '消瘦', '', 10217, 3, '0', '1', '2019-09-15 15:24:21', '1', '2019-09-15 15:24:21');
INSERT INTO `sys_code_detail` VALUES (662, '4', '恶液质', '', 10217, 4, '0', '1', '2019-09-15 15:24:38', '1', '2019-09-15 15:24:38');
INSERT INTO `sys_code_detail` VALUES (663, '1', '关心', '', 10218, 1, '0', '1', '2019-09-15 15:25:13', '1', '2019-09-15 15:25:13');
INSERT INTO `sys_code_detail` VALUES (664, '2', '不关心', '', 10218, 2, '0', '1', '2019-09-15 15:25:28', '1', '2019-09-15 15:25:28');
INSERT INTO `sys_code_detail` VALUES (665, '3', '过于关心', '', 10218, 3, '0', '1', '2019-09-15 15:25:43', '1', '2019-09-15 15:25:43');
INSERT INTO `sys_code_detail` VALUES (666, '4', '无人照顾', '', 10218, 4, '0', '1', '2019-09-15 15:25:57', '1', '2019-09-15 15:25:57');
INSERT INTO `sys_code_detail` VALUES (667, '5', '不配合', '', 10218, 5, '0', '1', '2019-09-15 15:26:10', '1', '2019-09-15 15:26:10');
INSERT INTO `sys_code_detail` VALUES (668, '1', '是', '', 10219, 1, '0', '1', '2019-09-15 16:57:39', '1', '2019-09-15 16:57:39');
INSERT INTO `sys_code_detail` VALUES (669, '2', '否', '', 10219, 2, '0', '1', '2019-09-15 16:57:53', '1', '2019-09-15 16:57:53');
INSERT INTO `sys_code_detail` VALUES (670, '1', '新鲜血', '', 10220, 1, '0', '1', '2019-09-15 17:29:33', '1', '2019-09-15 17:29:33');
INSERT INTO `sys_code_detail` VALUES (671, '2', '库存血', '', 10220, 2, '0', '1', '2019-09-15 17:29:40', '1', '2019-09-15 17:29:40');
INSERT INTO `sys_code_detail` VALUES (672, '3', '血浆', '', 10220, 3, '0', '1', '2019-09-15 17:29:51', '1', '2019-09-15 17:29:51');
INSERT INTO `sys_code_detail` VALUES (673, '4', '红细胞', '', 10220, 4, '0', '1', '2019-09-15 17:30:02', '1', '2019-09-15 17:30:02');
INSERT INTO `sys_code_detail` VALUES (674, '5', '白细胞浓缩悬液', '', 10220, 5, '0', '1', '2019-09-15 17:30:17', '1', '2019-09-15 17:30:17');
INSERT INTO `sys_code_detail` VALUES (675, '6', '血小板浓缩悬液', '', 10220, 6, '0', '1', '2019-09-15 17:30:26', '1', '2019-09-15 17:30:26');
INSERT INTO `sys_code_detail` VALUES (676, '7', '凝血制剂', '', 10220, 7, '0', '1', '2019-09-15 17:30:38', '1', '2019-09-15 17:30:38');
INSERT INTO `sys_code_detail` VALUES (677, '8', '白蛋白液', '', 10220, 8, '0', '1', '2019-09-15 17:30:51', '1', '2019-09-15 17:30:51');
INSERT INTO `sys_code_detail` VALUES (678, '9', '纤维蛋白原', '', 10220, 9, '0', '1', '2019-09-15 17:31:06', '1', '2019-09-15 17:31:06');
INSERT INTO `sys_code_detail` VALUES (679, '10', '抗血友病球蛋白浓缩剂', '', 10220, 10, '0', '1', '2019-09-15 17:31:15', '1', '2019-09-15 17:31:15');
INSERT INTO `sys_code_detail` VALUES (680, '11', '其他', '', 10220, 11, '0', '1', '2019-09-15 17:31:21', '1', '2019-09-15 17:31:21');
INSERT INTO `sys_code_detail` VALUES (681, '1', '入院记录', '', 10221, 1, '0', '1', '2019-09-16 07:35:40', '1', '2019-09-16 07:35:40');
INSERT INTO `sys_code_detail` VALUES (682, '3', '日常病程记录', '', 10221, 2, '0', '1', '2019-09-16 07:36:05', '1', '2019-09-16 07:36:05');
INSERT INTO `sys_code_detail` VALUES (683, '25', '上级医师查房记录', '', 10221, 3, '0', '1', '2019-09-16 07:36:23', '1', '2019-09-16 07:36:23');
INSERT INTO `sys_code_detail` VALUES (684, '4', '抢救记录', '', 10221, 4, '0', '1', '2019-09-16 07:36:36', '1', '2019-09-16 07:36:36');
INSERT INTO `sys_code_detail` VALUES (685, '5', '阶段小结记录', '', 10221, 5, '0', '1', '2019-09-16 07:36:52', '1', '2019-09-16 07:36:52');
INSERT INTO `sys_code_detail` VALUES (686, '6', '交班记录', '', 10221, 6, '0', '1', '2019-09-16 07:37:04', '1', '2019-09-16 07:37:04');
INSERT INTO `sys_code_detail` VALUES (687, '23', '转科转入记录', '', 10221, 23, '0', '1', '2019-09-16 07:37:18', '1', '2019-09-16 07:37:18');
INSERT INTO `sys_code_detail` VALUES (688, '8', '输血记录', '', 10221, 8, '0', '1', '2019-09-16 07:37:26', '1', '2019-09-16 07:37:26');
INSERT INTO `sys_code_detail` VALUES (689, '9', '有创诊疗操作记录', '', 10221, 9, '0', '1', '2019-09-16 07:37:40', '1', '2019-09-16 07:37:40');
INSERT INTO `sys_code_detail` VALUES (690, '10', '会诊申请及会诊记录', '', 10221, 10, '0', '1', '2019-09-16 07:38:02', '1', '2019-09-16 07:38:02');
INSERT INTO `sys_code_detail` VALUES (691, '11', '病例讨论记录', '', 10221, 11, '0', '1', '2019-09-16 07:38:14', '1', '2019-09-16 07:38:14');
INSERT INTO `sys_code_detail` VALUES (692, '12', '术前小结记录', '', 10221, 12, '0', '1', '2019-09-16 07:38:27', '1', '2019-09-16 07:38:27');
INSERT INTO `sys_code_detail` VALUES (693, '13', '手术安全核查记录', '', 10221, 13, '0', '1', '2019-09-16 07:38:57', '1', '2019-09-16 07:38:57');
INSERT INTO `sys_code_detail` VALUES (694, '14', '手术记录', '', 10221, 14, '0', '1', '2019-09-16 07:39:18', '1', '2019-09-16 07:39:18');
INSERT INTO `sys_code_detail` VALUES (695, '15', '术后病程记录', '', 10221, 15, '0', '1', '2019-09-16 07:39:33', '1', '2019-09-16 07:39:33');
INSERT INTO `sys_code_detail` VALUES (696, '16', '医嘱记录', '', 10221, 16, '0', '1', '2019-09-16 07:39:45', '1', '2019-09-16 07:39:45');
INSERT INTO `sys_code_detail` VALUES (697, '17', '病危（重）通知书', '', 10221, 17, '0', '1', '2019-09-16 07:40:06', '1', '2019-09-16 07:40:06');
INSERT INTO `sys_code_detail` VALUES (698, '19', '出院记录(住院时间超过24h)', '', 10221, 19, '0', '1', '2019-09-16 07:40:17', '1', '2019-09-16 07:40:17');
INSERT INTO `sys_code_detail` VALUES (699, '21', '死亡记录(住院超过24h)', '', 10221, 21, '0', '1', '2019-09-16 07:40:28', '1', '2019-09-16 07:40:28');
INSERT INTO `sys_code_detail` VALUES (700, '22', '医院感染调查表', '', 10221, 22, '0', '1', '2019-09-16 07:40:44', '1', '2019-09-16 07:40:44');
INSERT INTO `sys_code_detail` VALUES (701, '18', '24小时内出入院记录', '', 10221, 18, '0', '1', '2019-09-16 14:25:31', '1', '2019-09-16 14:25:31');
INSERT INTO `sys_code_detail` VALUES (702, '20', '24小时内死亡记录', '', 10221, 20, '0', '1', '2019-09-16 14:25:47', '1', '2019-09-16 14:25:47');
INSERT INTO `sys_code_detail` VALUES (703, '7', '接班记录', '', 10221, 7, '0', '1', '2019-09-16 16:45:37', '1', '2019-09-16 16:45:37');
INSERT INTO `sys_code_detail` VALUES (704, '24', '转科转出记录', '', 10221, 24, '0', '1', '2019-09-16 17:30:57', '1', '2019-09-16 17:30:57');
INSERT INTO `sys_code_detail` VALUES (705, '2', '首次病程记录', '', 10221, 2, '0', '1', '2019-09-16 19:12:00', '1', '2019-09-16 19:12:00');
INSERT INTO `sys_code_detail` VALUES (706, '1', '正常记录', '', 10222, 1, '0', '1', '2019-09-17 13:51:34', '1', '2019-09-17 13:51:34');
INSERT INTO `sys_code_detail` VALUES (707, '2', '补记录', '', 10222, 2, '0', '1', '2019-09-17 13:51:42', '1', '2019-09-17 13:51:42');
INSERT INTO `sys_code_detail` VALUES (708, '1', '常规会诊', '', 10223, 1, '0', '1', '2019-09-17 15:08:28', '1', '2019-09-17 15:08:28');
INSERT INTO `sys_code_detail` VALUES (709, '2', '急会诊', '', 10223, 2, '0', '1', '2019-09-17 15:08:42', '1', '2019-09-17 15:08:42');
INSERT INTO `sys_code_detail` VALUES (710, '1', '普通医嘱', '', 10224, 1, '0', '1', '2019-09-18 10:45:47', '1', '2019-09-18 10:45:47');
INSERT INTO `sys_code_detail` VALUES (711, '2', '用药医嘱', '', 10224, 2, '0', '1', '2019-09-18 10:45:54', '1', '2019-09-18 10:45:54');
INSERT INTO `sys_code_detail` VALUES (712, '3', '项目医嘱', '', 10224, 3, '0', '1', '2019-09-18 10:46:04', '1', '2019-09-18 10:46:04');

-- ----------------------------
-- Table structure for typography
-- ----------------------------
DROP TABLE IF EXISTS `typography`;
CREATE TABLE `typography`  (
  `p_id` int(20) NOT NULL AUTO_INCREMENT,
  `p_dept_id` int(20) NOT NULL COMMENT '部门id',
  `p_user_id` int(20) NOT NULL COMMENT '用户id',
  `P_start_date` datetime(0) NOT NULL COMMENT '排班起始时间',
  `p_end_date` datetime(0) NOT NULL COMMENT '排班结束时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `update_user_id` int(20) NULL DEFAULT NULL COMMENT '修改人员id',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_user_id` int(20) NULL DEFAULT NULL COMMENT '创建人id',
  `p_years_month` datetime(0) NULL DEFAULT NULL COMMENT '排班的年月',
  `p_k_id` int(20) NULL DEFAULT NULL COMMENT '科室id',
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '排班表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户类型 ',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '性别 1-男 2-女',
  `tel_phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `specialty` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `password` char(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '所属部门',
  `qq` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'QQ',
  `card_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `agency_years` decimal(10, 2) NULL DEFAULT NULL COMMENT '代理年限',
  `is_certificate` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '是否有资格证',
  `mail` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `order_no` int(11) NULL DEFAULT NULL,
  `qrcode` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '二维码',
  `original_file_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `is_init_data` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `company_id` varchar(8) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `del_flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 439 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, '', 'admin', '系统管理员', '1', '18555338533', NULL, 'admin', '9', NULL, NULL, NULL, NULL, '1062613427@qq.com', NULL, NULL, NULL, NULL, '1', '', '1', '2019-04-08 15:47:45', '1', '2019-04-08 15:47:49');
INSERT INTO `user_info` VALUES (45, NULL, 'anhuishangjue', 'anhuishangjue', '1', '18555338533', '', 'anhuishangjue', '10', '', '123123132131231231', NULL, NULL, '1@1', NULL, NULL, NULL, NULL, '1', '0', '1', '2019-06-30 12:43:24', '1', '2019-06-30 12:43:24');
INSERT INTO `user_info` VALUES (428, NULL, '2546366', 'sfds', NULL, NULL, NULL, '546366', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '1', '1', '2019-09-15 20:53:58', '1', '2019-09-15 20:53:58');
INSERT INTO `user_info` VALUES (429, NULL, 'D00000001', '口腔科医生1', '1', NULL, NULL, '645121', '9', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '1', '1', '2019-09-15 20:56:21', '1', '2019-09-15 20:56:21');
INSERT INTO `user_info` VALUES (430, NULL, 'D00000002', '口腔科医生2', '1', NULL, NULL, '463645', '9', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '1', '1', '2019-09-15 20:56:44', '1', '2019-09-15 20:56:44');
INSERT INTO `user_info` VALUES (431, NULL, 'N00000001', '护士1', '2', '5576564', NULL, '152121', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '1', '1', '2019-09-15 20:57:27', '1', '2019-09-15 20:57:27');
INSERT INTO `user_info` VALUES (432, NULL, '54512121', '测试门诊打印', NULL, NULL, NULL, '512121', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '1', '1', '2019-09-16 07:19:29', '1', '2019-09-16 07:19:29');
INSERT INTO `user_info` VALUES (433, NULL, '6545656', '丁力', NULL, NULL, NULL, '545656', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '1', '1', '2019-09-16 08:51:56', '1', '2019-09-16 08:51:56');
INSERT INTO `user_info` VALUES (434, NULL, '56489156', '李四', NULL, NULL, NULL, '489156', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '1', '1', '2019-09-16 09:04:30', '1', '2019-09-16 09:04:30');
INSERT INTO `user_info` VALUES (435, NULL, '254636454', '测试出药', NULL, NULL, NULL, '636454', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '1', '1', '2019-09-16 14:15:14', '1', '2019-09-16 14:15:14');
INSERT INTO `user_info` VALUES (436, NULL, '25463654', '朱', NULL, NULL, NULL, '463654', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '1', '1', '2019-09-17 15:59:13', '1', '2019-09-17 15:59:13');
INSERT INTO `user_info` VALUES (437, NULL, '091999', '0918', NULL, NULL, NULL, '091999', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '1', '1', '2019-09-18 08:53:10', '1', '2019-09-18 08:53:10');
INSERT INTO `user_info` VALUES (438, NULL, '5454154152', '测试退药', NULL, NULL, NULL, '154152', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '1', '1', '2019-09-18 13:47:22', '1', '2019-09-18 13:47:22');

-- ----------------------------
-- Table structure for user_org_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_org_rel`;
CREATE TABLE `user_org_rel`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '部门ID',
  `is_leader` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '是否负责人',
  `is_init_data` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '用户部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_org_rel
-- ----------------------------
INSERT INTO `user_org_rel` VALUES (1, 1, '1001', '1', NULL);
INSERT INTO `user_org_rel` VALUES (23, 45, '1002002', NULL, NULL);
INSERT INTO `user_org_rel` VALUES (25, 377, '1004001', NULL, NULL);
INSERT INTO `user_org_rel` VALUES (26, 378, '1004001', NULL, NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(255) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(255) NULL DEFAULT NULL,
  `role_id` bigint(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (44, 1, 1);
INSERT INTO `user_role` VALUES (45, 45, 1);
INSERT INTO `user_role` VALUES (46, 379, 8);
INSERT INTO `user_role` VALUES (47, 380, 8);
INSERT INTO `user_role` VALUES (48, 377, 7);
INSERT INTO `user_role` VALUES (49, 378, 7);

SET FOREIGN_KEY_CHECKS = 1;
