package com.ahsj.hiscore.common;

import com.ahsj.hiscore.controller.BaseOAController;

/**
 * @Author XJP
 * @Date 2019/7/26
 * @Time 14:35
 **/
public class Constants {

    private static BaseOAController resourseUrl = new BaseOAController();

    public static final String TRANSLATE_SYS_CODE = "10001"; // SysCode
    public static final String TRANSLATE_SYS_CODE_DETAIL = "10002";// SysCodeDetail
    public static final String TRANSLATE_SYS_ORGANIZATION = "10003"; //Organization
    public static final String TRANSLATE_HIS_PROJECT = "10004";  //HisProject
    public static final String TRANSLATE_HIS_CONSUMABLESBUYPLAN = "10005"; //HisConsumablesBuyplan
    public static final String TRANSLATE_HIS_CONSUMABLESDETAILS = "10006";// HisConsumablesDetails
    public static final String TRANSLATE_HIS_CONSUMABLESBUYPLANDETAILS = "10007"; //HisConsumablesBuyplanDetails
    public static final String TRANSLATE_HIS_MEDICINEINFO = "10008"; //HisMedicineInfo
    public static final String TRANSLATE_HIS_MEDICATIONDETAILS = "10009"; //HisMedicationDetails
    public static final String TRANSLATE_HIS_HISANKLETEMPLATE = "10010";// HisAnkleTemplate
    public static final String TRANSLATE_HIS_HISCONSUMABLES = "10011";// HisConsumables
    public static final String TRANSLATE_HIS_HISPRESCRIPTION = "10013";// HisPrescription
    public static final String TRANSLATE_HIS_HISPRESCRIPTIONMEDICINE = "10014";// HisPrescriptionMedicine
    public static final String TRANSLATE_HIS_HHISMEDICAL= "10015";// hisMedical
    public static final String TRANSLATE_HIS_MEDICALORDERDETAIL= "10016";// HisMedicalOrderDetail
    //测试
    public static final String TRANSLATE_HIS_HISCONSUMABLES_LIST = "10012";


    public static final String TRANSLATE_HIS_DELETE = "com.ahsj.delete";


    public static final String TYPE_ENUM = "enum";
    public static final String TYPE_CODE = "code";
    public static final String GLOBAL_DATA_ENUM = "GLOBAL_DATA_ENUM";
    public static final String GLOBAL_DATA_TABLE = "GLOBAL_DATA_TABLE";
    public static final String GLOBAL_DATA_ORANGIATION = "GLOBAL_DATA_ORANGIATION";
    public static final String GLOBAL_DATA_PRIVILEGE = "PRIVILEGE";
    public static final String SESSION_KEY = "session_key_user_auth";
    public static final String TREE_HAS_SUB_YES = "0";
    public static final String TREE_HAS_SUB_NO = "1";

    public static final String HIS_KM = "km";
    public static final String HIS_CH = "ch";
    public static final String HIS_EN = "en";


    public static final String HIS_HHM = "HHM"; //就诊记录编号 头部  空降
    public static final String HIS_HM = "HM"; //就诊记录编号 头部
    public static final String HIS_HTR = "HTR"; //交易流水号 头部
    public static final String HIS_HR = "HR";  //住院编号 头部

    public static final String HIS_NAME = "HISNAME";
    public static final String HIS_IDCARD = "HISIDCARD";

    public static final Integer HIS_NUNMER = -1;

    public static final Integer HIS_ONE = 1;
    public static final Integer HIS_TWO = 2;

    public static final Integer HIS_DOCTOR_ONE = 1;
    public static final Integer HIS_DOCTOR_TWO = 2;
    public static final Integer HIS_DOCTOR_THERE = 3;



    //新生儿
    public static final String HIS_HEALTH_ONE = "重度窒息";
    public static final String HIS_HEALTH_TWO = "轻度窒息";
    public static final String HIS_HEALTH_THREE = "正常";
    public static final String HIS_NEW_BORN_ADD= "新增";
    public static final String HIS_NEW_BORN_UPDARTE = "修改";



    //字典映射
    public static final String HIS_SYS_CODE_TYPE = "assit_project_type";
    public static final String HIS_SYS_CODE_PRESCRIPTION = "prescription";
    public static final String HIS_SYS_CODE_MENTAL_MEDICINE = "mental_medicine";
    public static final String HIS_SYS_CODE_LEVEL = "level";
    public static final String HIS_SYS_CODE_MEDICAL_INSURANCE_SIGN = "medical_insurance_sign";
    public static final String HIS_SYS_CODE_BASE_MEDICINE = "base_medicine";
    public static final String HIS_SYS_CODE_NARCOTIC_DRUGS = "narcotic_drugs";
    public static final String HIS_SYS_CODE_PLACEORIGIN = "placeOrigin";
    public static final String HIS_SYS_CODE_DISABLE = "disable";
    public static final String HIS_SYS_CODE_IS_OBTAINED = "is_obtained";
    public static final String HIS_SYS_CODE_ROOM_TYPE = "room_type";
    public static final String HIS_SYS_CODE_IS_MARRIED = "is_married";
    public static final String HIS_SYS_CODE_SEX = "sex";
    public static final String HIS_SYS_IS_ENABLE = "is_enable";
    public static final String HIS_SYS_ASSIT_PROJECT_TYPE = "assit_project_type";


    //项目
    public static final String HIS_SYS_EXCEL_PROJECT_CH_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/excel/export/hisproject_CH.xlsx";
    public static final String HIS_SYS_EXCEL_PROJECT_KM_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/excel/export/hisproject_EN.xlsx";
    //项目
    public static final String HIS_SYS_EXCEL_PROJECT_IMPORT_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/excel/import/hisproject.xlsx";
    public static final String HIS_SYS_EXCEL_PROJECT_ERROR_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/errorImport/errorInfo.txt";

    //医生
    public static final String HIS_SYS_EXCEL_DOCTOR_CH_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/excel/export/hisdoctor.xlsx";
    public static final String HIS_SYS_EXCEL_DOCTOR_IMPORT_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/excel/import/hisdoctor.xlsx";
    public static final String HIS_SYS_EXCEL_DOCTOR_ERROR_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/errorImport/errorInfo.txt";
    public static final String HIS_SYS_EXCEL_DOCTOR_KM_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/excel/export/hisdoctor_EN.xlsx";

    //药品
    public static final String HIS_SYS_EXCEL_MEDICINE_CH_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/excel/export/hismedicine_CH.xlsx";
    public static final String HIS_SYS_EXCEL_MEDICINE_KM_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/excel/export/hismedicine_EN.xlsx";
    public static final String HIS_SYS_EXCEL_MEDICINE_IMPORT_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/excel/import/hismedicine.xlsx";
    public static final String HIS_SYS_EXCEL_MEDICINE_ERROR_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/errorImport/HisMedicineInfoErrorInfo.txt";

    //药库
    public static final String HIS_SYS_EXCEL_PHARMACY_CH_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/excel/export/hisPharmacy_CH.xlsx";
    public static final String HIS_SYS_EXCEL_PHARMACY_KM_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/excel/export/hisPharmacy_EN.xlsx";
    public static final String HIS_SYS_EXCEL_PHARMACY_IMPORT_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/excel/import/hisPharmacy.xlsx";
    public static final String HIS_SYS_EXCEL_PHARMACY_ERROR_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/errorImport/HisPharmacyDetailErrorInfo.txt";

    //药方
    public static final String HIS_SYS_EXCEL_PRESCRIPTION_CH_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/excel/export/PrescriptionMedicine_CH.xlsx";
    public static final String HIS_SYS_EXCEL_PRESCRIPTION_KM_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/excel/export/PrescriptionMedicine_EN.xlsx";
    public static final String HIS_SYS_EXCEL_PRESCRIPTION_IMPORT_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/excel/import/hisPrescriptionMedicine.xlsx";
    public static final String HIS_SYS_EXCEL_PRESCRIPTION_ERROR_FILE_URL = resourseUrl.getJarResourcesPaths() + "/templates/errorImport/HisPrescriptionMedicineErrorInfo.txt";


    //病房
    public static final String HIS_SYS_EXCEL_HISWARD_CH_FILE_URL = resourseUrl.getJarResourcesPaths() +"/templates/excel/export/HisWard_CH.xlsx";
    public static final String HIS_SYS_EXCEL_HISWARD_KM_FILE_URL = resourseUrl.getJarResourcesPaths() +"/templates/excel/export/HisWard_EN.xlsx";
    public static final String HIS_SYS_EXCEL_HISWARD_IMPORT_FILE_URL = resourseUrl.getJarResourcesPaths() +"/templates/excel/import/HisWard.xlsx";
    //病人
    public static final String HIS_SYS_EXCEL_HIS_PATIENT_INFO_IMPORT_FILE_URL = resourseUrl.getJarResourcesPaths() +"/templates/excel/import/HisPatientInfo.xlsx";
    public static final String HIS_SYS_EXCEL_HIS_PATIENT_INFO_KM_FILE_URL = resourseUrl.getJarResourcesPaths() +"/templates/excel/export/hisPatientInfo_KM.xlsx";
    public static final String HIS_SYS_EXCEL_HIS_PATIENT_INFO_CH_FILE_URL = resourseUrl.getJarResourcesPaths() +"/templates/excel/export/hisPatientInfo_CH.xlsx";
    //耗材
    public static final String HIS_SYS_EXCEL_HIS_CONSUMABLES_IMPORT_FILE_URL = resourseUrl.getJarResourcesPaths() +"/templates/excel/import/hisConsumables.xlsx";
    public static final String HIS_SYS_EXCEL_CONSUMABLES_KM_FILE_URL = resourseUrl.getJarResourcesPaths() +"/templates/excel/export/hisconsumables_EN.xlsx";
    public static final String HIS_SYS_EXCEL_CONSUMABLES_CH_FILE_URL = resourseUrl.getJarResourcesPaths() +"/templates/excel/export/hisconsumables_CH.xlsx";

   //-------------------------------------------------------------------------------------------------------------------------

    //word 导出  C:\JAVA\IDEA\work\his\his-core\src\main\resources\templates\word\export\consultation.doc

    public static final String HIS_SYS_WORD_CONSUMABLES_CH_FILE_URL = resourseUrl.getJarResourcesPaths() +"/templates/word/export/consultation.docx";


    public Constants() {
    }
}