package com.ahsj.hiscore.controller.inpatient_medical_record;

import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.services.*;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description 住院大病历控制器
 * @Params
 * @return
 * @Author zhushixiang
 * @Date 2019-09-16
 * @Time 7:51
 **/
@Controller
@RequestMapping("/api/inpatientMedicalRecord")
public class HisInpatientMedicalRecordController extends BaseController {
    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;

    @Autowired
    HisBloodTransfusionRecordService hisBloodTransfusionRecordService;

    @Autowired
    HisRescueService hisRescueService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisPatientInfoService hisPatientInfoService;

    @Autowired
    HisLeaveHospitalService hisLeaveHospitalService;

    @Autowired
    HisDeathService hisDeathService;

    @Autowired
    HandoverService handoverService;

    @Autowired
    HisTransferRecordService hisTransferRecordService;

    @Autowired
    HisFirstCourseRecordService hisFirstCourseRecordService;

    @Autowired
    HisSuccessionService hisSuccessionService;

    @Autowired
    PreoperativeSummaryRecordService preoperativeSummaryRecordService;


    @Autowired
    HisStageSummaryService hisStageSummaryService;

    @Autowired
    MedicalCrisisNoticeService medicalCrisisNoticeService;

    @Autowired
    IOrganizationService iOrganizationService;

    @Autowired
    HisBedService hisBedService;

    @Autowired
    SurgicalRecordService surgicalRecordService;

    @Autowired
    PostoperativeRecordService postoperativeRecordService;

    @Autowired
    HisDiagnosisTreatmentService hisDiagnosisTreatmentService;


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 住院病历list界面
     * @Params [token]
     * @Author zhushixiang
     * @Date 2019-09-16
     * @Time 7:51
     **/
    @RequestMapping("listByHospitalManageId/index.ahsj")
    ModelAndView listIndex(String token, String hospitalManageId, Integer isNurseOpreate) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/inpatient_medical_record/listByHospitalManageId");
        modelAndView.addObject("title", "住院病历（Inpatient medical record）");
        modelAndView.addObject("token", token);
        Long loginUserId = getId();
        modelAndView.addObject("loginUserId", loginUserId);
        modelAndView.addObject("hospitalManageId", hospitalManageId);
        boolean isOpreate;
        if (!EmptyUtil.Companion.isNullOrEmpty(isNurseOpreate)) {
            if (isNurseOpreate == 1) {
                isOpreate = false;
                modelAndView.addObject("isOpreate", isOpreate);
            }
        }
        return modelAndView;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicineInfo>
     * @Description list查询
     * @Params [model, request, hisMedicineInfo]
     * @Author zhushixiang
     * @Date 2019-09-16
     * @Time 7:53
     **/
    @RequestMapping(value = "listByHospitalManageId.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public PageBean<HisInpatientMedicalRecord> list(Map<String, Object> model, HttpServletRequest request, HisInpatientMedicalRecord hisInpatientMedicalRecord) throws Exception {
        PageBean<HisInpatientMedicalRecord> pageBean = new PageBean<HisInpatientMedicalRecord>();
        pageBean.setParameter(hisInpatientMedicalRecord);
        return hisInpatientMedicalRecordService.listByHospitalManageId(pageBean);
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 添加新的病历记录跳转界面
     * @Params [token, hospitalManageId]
     * @Author zhushixiang
     * @Date 2019-09-16
     * @Time 9:05
     **/
    @RequestMapping("save/index.ahsj")
    ModelAndView save(String token, Integer medicalRecordType, Long hospitalManageId) throws Exception {
        HisHospitalManage hisHospitalManage = hisHospitalManageService.selectById(hospitalManageId);
        ModelAndView modelAndView = new ModelAndView();
        if (medicalRecordType == 1) {

        }
        //首次病程记录
        else if (medicalRecordType == 2) {
            modelAndView.setViewName("backend/hiscore/first_course_record/saveOrUpdate");
            HisFirstCourseRecord hisFirstCourseRecord = new HisFirstCourseRecord();
            hisFirstCourseRecord.setHospitalManageId(hospitalManageId);
            modelAndView.addObject("hisFirstCourseRecord", hisFirstCourseRecord);
            modelAndView.addObject("title", "新增首次病程记录（New first disease record）");
        } else if (medicalRecordType == 3) {
            modelAndView.setViewName("backend/hiscore/hospitalmanage/daily_course");
            modelAndView.addObject("number", hisHospitalManage.getNumber());
            modelAndView.addObject("loginName", getUserName());
            modelAndView.addObject("title", "新增日常病程记录（New Daily course record）");
        } else if (medicalRecordType == 4) {
            modelAndView.setViewName("backend/hiscore/doctor/addOrUpdateHisRescue");
            HisRescue hisRescue = new HisRescue();
            hisRescue.setRecordNumber(hisHospitalManage.getMedicalNumber());
            hisRescue.setPatientId(hisHospitalManage.getPatientId());
            HisPatientInfo hisPatientInfo = hisPatientInfoService.selectByPrimaryKey(hisHospitalManage.getPatientId());
            hisRescue.setPatientName(hisPatientInfo.getName());
            modelAndView.addObject("hisRescue", hisRescue);
            modelAndView.addObject("title", "新增抢救记录（Add rescue record）");
        }
        //阶段小结
        else if (medicalRecordType == 5) {
            modelAndView.setViewName("backend/hiscore/hisstagesummary/update");
            HisStageSummary hisStageSummary = new HisStageSummary();
            hisStageSummary.setMedicalNumber(hisHospitalManage.getNumber());
            modelAndView.addObject("hisStageSummary", hisStageSummary);
            modelAndView.addObject("title", "阶段小结添加");
            modelAndView.addObject("token", token);
            return modelAndView;
        }
        //交班记录
        else if (medicalRecordType == 6) {
            modelAndView.setViewName("backend/hiscore/handover/update.html");
            HisHandover hisHandover = new HisHandover();
            hisHandover.setHospitalNumber(hisHospitalManage.getNumber());
            modelAndView.addObject("hisHandover", hisHandover);
            modelAndView.addObject("title", "新增交班记录（New shift record）");

        } else if (medicalRecordType == 7) {
            modelAndView.setViewName("backend/hiscore/succession/update");
            HisSuccession hisSuccession = new HisSuccession();
            hisSuccession.setHospitalNumber(hisHospitalManage.getNumber());
            modelAndView.addObject("hisSuccession", hisSuccession);
            modelAndView.addObject("title", "接班班信息添加");
            modelAndView.addObject("token", token);
        } else if (medicalRecordType == 8) {
            modelAndView.setViewName("backend/hiscore/blood_transfusion_record/saveOrUpdate");
            HisBloodTransfusionRecord hisBloodTransfusionRecord = new HisBloodTransfusionRecord();
            hisBloodTransfusionRecord.setHospitalManageId(hospitalManageId);
            modelAndView.addObject("hisBloodTransfusionRecord", hisBloodTransfusionRecord);
            modelAndView.addObject("title", "新增输血记录（New blood transfusion record）");
        } else if (medicalRecordType == 9) {
            HisDiagnosisTreatment hisDiagnosisTreatment = new HisDiagnosisTreatment();
            hisDiagnosisTreatment.setHospitalManageNumber(hisHospitalManage.getNumber());
            modelAndView = new ModelAndView("backend/hiscore/hisdiagnosistreatment/addOrUpdate");//templates/backend/hiscore/hisdiagnosistreatment/addOrUpdate.html
            modelAndView.addObject("hisDiagnosisTreatment", hisDiagnosisTreatment);


        } else if (medicalRecordType == 10) {
            modelAndView.setViewName("backend/hiscore/hospitalmanage/consultation_note");
            HisHospitalManage hisHospitalManage1 = hisHospitalManageService.selectById(hospitalManageId);
            modelAndView.addObject("loginName", getUserName());
            modelAndView.addObject("number", hisHospitalManage1.getNumber());
            modelAndView.addObject("title", "院内会诊记录单(Discharge information audit)");

        } else if (medicalRecordType == 11) {

        } else if (medicalRecordType == 12) {
            PreoperativeSummaryRecord preoperativeSummaryRecord = new PreoperativeSummaryRecord();
            preoperativeSummaryRecord.setMedicalNumber(hisHospitalManage.getNumber());
            modelAndView = new ModelAndView("backend/hiscore/preoperativesummaryrecord/addOrUpdate");//templates/backend/hiscore/hisdiagnosistreatment/addOrUpdate.html
            preoperativeSummaryRecord.setJoinDate(hisHospitalManage.getCreateDate());
            modelAndView.addObject("preoperativeSummaryRecord", preoperativeSummaryRecord);
            modelAndView.addObject("token", token);
        } else if (medicalRecordType == 13) {

        } else if (medicalRecordType == 14) {
            modelAndView = new ModelAndView("backend/hiscore/surgicalRecord/addOrUpdate");
            SurgicalRecord surgicalRecord = new SurgicalRecord();
            Organization organization = iOrganizationService.getOrganizationById(hisHospitalManage.getDepartmentId());
            surgicalRecord.setMedicalNumber(hisHospitalManage.getNumber());
            surgicalRecord.setAge(hisHospitalManage.getAge());
            surgicalRecord.setPatientName(hisHospitalManage.getPatientName());
            surgicalRecord.setJoinDate(hisHospitalManage.getCreateDate());
            surgicalRecord.setSexName(hisHospitalManage.getSexName());
            surgicalRecord.setSex(hisHospitalManage.getSex());
            surgicalRecord.setDepartmentName(organization.getName());
            surgicalRecord.setDepartment(hisHospitalManage.getDepartmentId().intValue());
            surgicalRecord.setBedNo(hisBedService.selectByPrimaryKey(hisHospitalManage.getBedId()).getNumber().toString());
            modelAndView.addObject("surgicalRecord", surgicalRecord);
        } else if (medicalRecordType == 15) {
            modelAndView = new ModelAndView("backend/hiscore/postoperativeRecord/addOrUpdate");
            PostoperativeRecord postoperativeRecord = new PostoperativeRecord();
            Organization organization = iOrganizationService.getOrganizationById(hisHospitalManage.getDepartmentId());
            postoperativeRecord.setMedicalNumber(hisHospitalManage.getNumber());
            postoperativeRecord.setAge(hisHospitalManage.getAge());
            postoperativeRecord.setPatientName(hisHospitalManage.getPatientName());
            postoperativeRecord.setJoinDate(hisHospitalManage.getCreateDate());
            postoperativeRecord.setSexName(hisHospitalManage.getSexName());
            postoperativeRecord.setSex(hisHospitalManage.getSex());
            postoperativeRecord.setDepartmentName(organization.getName());
            postoperativeRecord.setDepartment(hisHospitalManage.getDepartmentId().intValue());
            postoperativeRecord.setBedNo(hisBedService.selectByPrimaryKey(hisHospitalManage.getBedId()).getNumber().toString());
            modelAndView.addObject("postoperativeRecord", postoperativeRecord);
        } else if (medicalRecordType == 16) {

        }
        //病危通知书
        else if (medicalRecordType == 17) {
            modelAndView.setViewName("backend/hiscore/medicalcrisisnotice/update");
            MedicalCrisisNotice medicalCrisisNotice = new MedicalCrisisNotice();
            medicalCrisisNotice.setMedicalNumber(hisHospitalManage.getNumber());
            modelAndView.addObject("medicalCrisisNotice", medicalCrisisNotice);
            modelAndView.addObject("title", "病危通知书添加");
            modelAndView.addObject("token", token);
            return modelAndView;
        }
        //24小时内出入院记录
        else if (medicalRecordType == 18) {
            modelAndView.setViewName("backend/hiscore/nursestation/inOut24hour");
            HisInoutTwentyfourHours hisInoutTwentyfourHours = new HisInoutTwentyfourHours();
            modelAndView.addObject("title", "新增24小时内出入院记录(Added 24-hour admission record)");
            modelAndView.addObject("token", token);
            modelAndView.addObject("doctorId", hisHospitalManage.getDoctorId());
            modelAndView.addObject("userId", getId());
            modelAndView.addObject("hospitalnumber", hisHospitalManage.getNumber());
            if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage.getPatientId())) {
                HisPatientInfo hisPatientInfo = hisPatientInfoService.selectByPrimaryKey(hisHospitalManage.getPatientId());
                modelAndView.addObject("hisPatientInfo", hisPatientInfo);
            }
        } else if (medicalRecordType == 19) {
            modelAndView.setViewName("backend/hiscore/hospitalmanage/audit");
            modelAndView.addObject("loginName", getUserName());
            HisHospitalManage hisHospitalManage1 = hisHospitalManageService.selectById(hospitalManageId);
            modelAndView.addObject("number", hisHospitalManage1.getNumber());
            modelAndView.addObject("title", "新增出院记录（New discharge record）");
        } else if (medicalRecordType == 20) {
            HisHospitalManage hisHospitalManage1 = hisHospitalManageService.selectNumber(hisHospitalManage.getNumber());
            HisPatientInfo hisPatientInfo = hisPatientInfoService.selectByPrimaryKey(hisHospitalManage1.getPatientId());
            modelAndView = new ModelAndView("backend/hiscore/doctor/dieIn24hours");
            modelAndView.addObject("hisPatientInfo", hisPatientInfo);
            modelAndView.addObject("doctorId", hisHospitalManage1.getDoctorId());
            modelAndView.addObject("departmentName", hisHospitalManage1.getDepartmentIdName());
            modelAndView.addObject("departmentId", hisHospitalManage1.getDepartmentId());
            modelAndView.addObject("bedId", hisHospitalManage1.getBedId());
            modelAndView.addObject("title", "新增24小时内死亡记录（Add a death record within 24 hours）");
            modelAndView.addObject("token", token);
            modelAndView.addObject("number", hisHospitalManage1.getNumber());
            modelAndView.addObject("loginName", getUserName());
        } else if (medicalRecordType == 21) {
            modelAndView.setViewName("backend/hiscore/hospitalmanage/death");
            HisHospitalManage hisHospitalManage1 = hisHospitalManageService.selectById(hospitalManageId);
            modelAndView.addObject("number", hisHospitalManage1.getNumber());
            modelAndView.addObject("title", "新增死亡记录（New death record）");
        } else if (medicalRecordType == 22) {

        }
        //转科转入记录
        else if (medicalRecordType == 23) {
            modelAndView.setViewName("backend/hiscore/transferRecord/update");
            modelAndView.addObject("title", "转科转入记录添加(Transfer to the record to add)");
            modelAndView.addObject("token", token);
            modelAndView.addObject("hosptalManageId", hospitalManageId);
            HisTransferRecord hisTransferRecord = new HisTransferRecord();
            modelAndView.addObject("hisTransferRecord", hisTransferRecord);
            modelAndView.addObject("hospitalManage", hisHospitalManageService.selectById(hospitalManageId));
            modelAndView.addObject("doctorId", getId());
            modelAndView.addObject("departmentId", getDeptId());

        } else if (medicalRecordType == 24) {
            modelAndView.setViewName("backend/hiscore/transferRecord/update_out");
            modelAndView.addObject("title", "转科转出记录添加(Transfer account transfer record added)");
            modelAndView.addObject("token", token);
            modelAndView.addObject("hosptalManageId", hospitalManageId);
            HisTransferRecord hisTransferRecord = new HisTransferRecord();
            modelAndView.addObject("hisTransferRecord", hisTransferRecord);
            modelAndView.addObject("hospitalManage", hisHospitalManageService.selectById(hospitalManageId));
            modelAndView.addObject("doctorId", getId());
            modelAndView.addObject("departmentId", getDeptId());
        }

        modelAndView.addObject("token", token);
        modelAndView.addObject("flag", true);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 修改住院大病历
     * @Params [token, medicalRecordType, hospitalManageId]
     * @Author zhushixiang
     * @Date 2019-09-16
     * @Time 9:36id
     **/
    @RequestMapping("update/index.ahsj")
    ModelAndView update(String token, @RequestParam(value = "id", required = true) Long id, @RequestParam(value = "isOperate", required = false) Integer isOperate) throws Exception {

        //根据大病历表ID 查询大病历
        HisInpatientMedicalRecord hisInpatientMedicalRecord = hisInpatientMedicalRecordService.selectById(id);
        ModelAndView modelAndView = new ModelAndView();
        Integer medicalRecordType = hisInpatientMedicalRecord.getMedicalRecordType();
        if (medicalRecordType == 1) {

        } else if (medicalRecordType == 2) {
            modelAndView.setViewName("backend/hiscore/first_course_record/saveOrUpdate");
            HisFirstCourseRecord hisFirstCourseRecord = hisFirstCourseRecordService.selectById(hisInpatientMedicalRecord.getTargetId());
            modelAndView.addObject("hisFirstCourseRecord", hisFirstCourseRecord);
            modelAndView.addObject("title", "修改日常病程记录记录（Modify daily disease record）");

        } else if (medicalRecordType == 3) {
            HisHospitalManage hisHospitalManage = hisHospitalManageService.selectById(hisInpatientMedicalRecord.getHospitalManageId());
            modelAndView.setViewName("backend/hiscore/hospitalmanage/daily_course_update");
            modelAndView.addObject("id", hisInpatientMedicalRecord.getTargetId());
            modelAndView.addObject("number", hisHospitalManage.getNumber());
            modelAndView.addObject("loginName", getUserName());
            modelAndView.addObject("title", "修改日常病程记录（New Daily course record）");
        } else if (medicalRecordType == 4) {
            modelAndView.setViewName("backend/hiscore/doctor/addOrUpdateHisRescue");
            HisRescue hisRescue = hisRescueService.selectById(hisInpatientMedicalRecord.getTargetId());
            modelAndView.addObject("hisRescue", hisRescue);
            modelAndView.addObject("title", "修改抢救记录（Modify rescue record）");
        } else if (medicalRecordType == 5) {
            modelAndView.setViewName("backend/hiscore/hisstagesummary/update");
            modelAndView.addObject("hisStageSummary", hisStageSummaryService.selectById(hisInpatientMedicalRecord.getId()));
            modelAndView.addObject("title", "阶段小结添加");
            modelAndView.addObject("token", token);
            return modelAndView;
        } else if (medicalRecordType == 6) {
            modelAndView.setViewName("backend/hiscore/handover/update.html");
            HisHandover hisHandover = handoverService.selectById(hisInpatientMedicalRecord.getTargetId());
            modelAndView.addObject("hisHandover", hisHandover);
            modelAndView.addObject("title", "修改交班记录（Modify the shift record）");

        } else if (medicalRecordType == 7) {
            modelAndView.setViewName("backend/hiscore/succession/update");
            modelAndView.addObject("hisSuccession", hisSuccessionService.selectById(hisInpatientMedicalRecord.getTargetId()));
            modelAndView.addObject("title", "交班信息添加");
            modelAndView.addObject("token", token);
            return modelAndView;
        } else if (medicalRecordType == 8) {
            modelAndView.setViewName("backend/hiscore/blood_transfusion_record/saveOrUpdate");
            HisBloodTransfusionRecord hisBloodTransfusionRecord = hisBloodTransfusionRecordService.selectById(hisInpatientMedicalRecord.getTargetId());
            modelAndView.addObject("hisBloodTransfusionRecord", hisBloodTransfusionRecord);
            modelAndView.addObject("title", "修改输血记录（Modify blood transfusion record）");
        } else if (medicalRecordType == 9) {
            HisDiagnosisTreatment hisDiagnosisTreatment = hisDiagnosisTreatmentService.selectByPrimaryKey(hisInpatientMedicalRecord.getTargetId());
            modelAndView.addObject("hisDiagnosisTreatment", hisDiagnosisTreatment);

        } else if (medicalRecordType == 10) {
            modelAndView.setViewName("backend/hiscore/hospitalmanage/consultation_note_update");
            HisHospitalManage hisHospitalManage = hisHospitalManageService.selectById(hisInpatientMedicalRecord.getHospitalManageId());
            modelAndView.addObject("id", hisInpatientMedicalRecord.getTargetId());
            modelAndView.addObject("number", hisHospitalManage.getNumber());
            modelAndView.addObject("loginName", getUserName());
            modelAndView.addObject("title", "院内会诊记录单修改(Discharge information audit update)");

        } else if (medicalRecordType == 11) {

        } else if (medicalRecordType == 12) {
            modelAndView = new ModelAndView("backend/hiscore/preoperativesummaryrecord/addOrUpdate");
            PreoperativeSummaryRecord preoperativeSummaryRecord = preoperativeSummaryRecordService.selectByPrimaryKey(hisInpatientMedicalRecord.getTargetId());
            modelAndView.addObject("preoperativeSummaryRecord", preoperativeSummaryRecord);
        } else if (medicalRecordType == 13) {

        } else if (medicalRecordType == 14) {
            modelAndView = new ModelAndView("backend/hiscore/surgicalRecord/addOrUpdate");
            SurgicalRecord surgicalRecord = surgicalRecordService.selectByPrimaryKey(hisInpatientMedicalRecord.getTargetId());
            Organization organization = iOrganizationService.getOrganizationById(surgicalRecord.getDepartment().longValue());
            surgicalRecord.setSexName(surgicalRecord.getSex() == 1 ? "男" : "女");
            surgicalRecord.setDepartmentName(organization.getName());
            modelAndView.addObject("surgicalRecord", surgicalRecord);
        } else if (medicalRecordType == 15) {
            modelAndView = new ModelAndView("backend/hiscore/postoperativeRecord/addOrUpdate");
            PostoperativeRecord postoperativeRecord = postoperativeRecordService.selectByPrimaryKey(hisInpatientMedicalRecord.getTargetId());
            Organization organization = iOrganizationService.getOrganizationById(postoperativeRecord.getDepartment().longValue());
            postoperativeRecord.setSexName(postoperativeRecord.getSex() == 1 ? "男" : "女");
            postoperativeRecord.setDepartmentName(organization.getName());
            modelAndView.addObject("postoperativeRecord", postoperativeRecord);

        } else if (medicalRecordType == 16) {

        } else if (medicalRecordType == 17) {

        } else if (medicalRecordType == 18) {
        }
        //病危通知书
        else if (medicalRecordType == 17) {
            modelAndView.setViewName("backend/hiscore/medicalcrisisnotice/update");
            MedicalCrisisNotice medicalCrisisNotice = new MedicalCrisisNotice();
            modelAndView.addObject("medicalCrisisNotice", medicalCrisisNoticeService.selectById(hisInpatientMedicalRecord.getId()));
            modelAndView.addObject("title", "病危通知书添加");
            modelAndView.addObject("token", token);
            return modelAndView;
        } else if (medicalRecordType == 18) {
            //预留  24小时内出入院记录 暂无编辑和修改
        } else if (medicalRecordType == 19) {
            modelAndView.setViewName("backend/hiscore/hospitalmanage/audit");
            HisLeaveHospital hisLeaveHospital = hisLeaveHospitalService.selectById(hisInpatientMedicalRecord.getTargetId());
            HisHospitalManage hisHospitalManage = hisHospitalManageService.selectById(hisLeaveHospital.getHisHospitalManageId());
            modelAndView.addObject("loginName", getUserName());
            modelAndView.addObject("number", hisHospitalManage.getNumber());
            modelAndView.addObject("title", "修改出院记录（Modify discharge records）");
        } else if (medicalRecordType == 20) {
            //预留  24小时内死亡记录 暂无编辑和修改
        } else if (medicalRecordType == 21) {
            modelAndView.setViewName("backend/hiscore/hospitalmanage/death");
            HisDeath hisDeath = hisDeathService.selectById(hisInpatientMedicalRecord.getTargetId());
            HisHospitalManage hisHospitalManage = hisHospitalManageService.selectById(hisDeath.getHisHospitalManageId());
            modelAndView.addObject("number", hisHospitalManage.getNumber());
            modelAndView.addObject("title", "修改死亡记录（Modify the death record）");
        } else if (medicalRecordType == 22) {

        } else if (medicalRecordType == 23) {
            modelAndView.addObject("title", "转科转入记录修改（Transfer to record change）");
            modelAndView.addObject("token", token);
            HisTransferRecord hisTransferRecord = hisTransferRecordService.selectById(hisInpatientMedicalRecord.getTargetId());
            modelAndView.addObject("hosptalManageId", hisTransferRecord.getHosptalManageId());
            modelAndView.addObject("hisTransferRecord", hisTransferRecord);
            modelAndView.addObject("hospitalManage", hisHospitalManageService.selectById(hisTransferRecord.getHosptalManageId()));
            modelAndView.addObject("doctorId", getId());
            modelAndView.addObject("departmentId", getDeptId());
            modelAndView.setViewName("backend/hiscore/transferRecord/update");
        } else if (medicalRecordType == 24) {
            modelAndView.addObject("title", "转科转出记录修改（Transfer to the record to modify the record）");
            modelAndView.addObject("token", token);
            HisTransferRecord hisTransferRecord = hisTransferRecordService.selectById(hisInpatientMedicalRecord.getTargetId());
            modelAndView.addObject("hosptalManageId", hisTransferRecord.getHosptalManageId());
            modelAndView.addObject("hisTransferRecord", hisTransferRecord);
            modelAndView.addObject("hospitalManage", hisHospitalManageService.selectById(hisTransferRecord.getHosptalManageId()));
            modelAndView.addObject("doctorId", getId());
            modelAndView.addObject("departmentId", getDeptId());
            modelAndView.setViewName("backend/hiscore/transferRecord/update_out");
        }
        //定义变量flag记录是否可操作
        boolean flag = true;
        if (!EmptyUtil.Companion.isNullOrEmpty(isOperate)) {
            if (isOperate == 1)
                flag = true;
            else
                flag = false;
        }
        modelAndView.addObject("flag", flag);
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return core.message.Message
     * @Description 签字
     * @Params [model, request, id, type, patientId]
     * @Author zhushixiang
     * @Date 2019-09-16
     * @Time 9:49
     **/
    @RequestMapping(value = "sign.ahsj")
    @ResponseBody
    public Message sign(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "id", required = true) Long id, @RequestParam(value = "loginUserId", required = false) Long loginUserId) throws Exception {
        //根据大病历表ID 查询大病历
        HisInpatientMedicalRecord hisInpatientMedicalRecord = hisInpatientMedicalRecordService.selectById(id);
        if (EmptyUtil.Companion.isNullOrEmpty(hisInpatientMedicalRecord))
            return MessageUtil.createMessage(false, "签字失败,数据库中不存在此病历！");
        if (hisInpatientMedicalRecord.getMedicalRecordType() == 1) {

        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 2) {
            return hisFirstCourseRecordService.sign(hisInpatientMedicalRecord.getTargetId(), loginUserId, getUserId());
        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 3) {

        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 4) {
            HisRescue hisRescue = new HisRescue();
            hisRescue.setId(hisInpatientMedicalRecord.getTargetId());
            return hisRescueService.signature(hisRescue, loginUserId);
        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 5) {

        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 6) {
            //交班无需签字
        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 7) {

        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 8) {
            return hisBloodTransfusionRecordService.sign(hisInpatientMedicalRecord.getTargetId(), loginUserId, getUserId());
        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 9) {

        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 10) {

        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 11) {

        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 12) {

        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 13) {

        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 14) {

        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 15) {

        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 16) {

        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 17) {

        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 18) {
            //预留  24小时内死亡记录 暂无签字
        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 19) {
            //多人签字
        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 20) {

        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 21) {
            //多人签字
        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 22) {

        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 23) {
            if (!EmptyUtil.Companion.isNullOrEmpty(id) || !EmptyUtil.Companion.isNullOrEmpty(getUserId()) || !EmptyUtil.Companion.isNullOrEmpty(getId())) {
                HisTransferRecord hisTransferRecord = hisTransferRecordService.selectById(hisInpatientMedicalRecord.getTargetId());
                hisTransferRecord.setDoctorId(getId());
                hisTransferRecord.setDoctorSignature(getUserId());
                return hisTransferRecordService.sign(hisTransferRecord);
            } else {
                return MessageUtil.createMessage(false, "数据异常，请联系管理员！");
            }
        } else if (hisInpatientMedicalRecord.getMedicalRecordType() == 24) {
            if (!EmptyUtil.Companion.isNullOrEmpty(id) || !EmptyUtil.Companion.isNullOrEmpty(getUserId()) || !EmptyUtil.Companion.isNullOrEmpty(getId())) {
                HisTransferRecord hisTransferRecord = hisTransferRecordService.selectById(hisInpatientMedicalRecord.getTargetId());
                hisTransferRecord.setDoctorId(getId());
                hisTransferRecord.setDoctorSignature(getUserId());
                return hisTransferRecordService.sign(hisTransferRecord);
            } else {
                return MessageUtil.createMessage(false, "数据异常，请联系管理员！");
            }
        }
        return MessageUtil.createMessage(false, "签字失败");
    }

}
