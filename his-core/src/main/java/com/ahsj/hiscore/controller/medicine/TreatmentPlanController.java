package com.ahsj.hiscore.controller.medicine;

import com.ahsj.hiscore.controller.BaseMedicineController;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.model.HisMedicalModel;
import com.ahsj.hiscore.feign.IUserService;
import com.ahsj.hiscore.services.*;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: his
 * @description:治疗方案控制器
 * @author: chenzhicai
 * @create: 2019-07-05-16-38
 **/
@Controller
@RequestMapping("/api/treatmentplan/")
public class TreatmentPlanController extends BaseMedicineController {

    @Autowired
    TreatmentPlanService treatmentPlanService;

    @Autowired
    HisMedicalService hisMedicalService;

    @Autowired
    HisMedicationDetailsService hisMedicationDetailsService;

    @Autowired
    HisPrescriptionService hisPrescriptionService;

    @Autowired
    HisPrescriptionMedicineService hisPrescriptionMedicineService;

    @Autowired
    HisProjectService hisProjectService;

    @Autowired
    HisRecordProjectService hisRecordProjectService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisInfusionService hisInfusionService;

    @Autowired
    IUserService iUserService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 就诊计划页面
     * @Params [token, id]
     * @Author chenzhicai
     * @Date 2019-07-12
     * @Time 11:13
     **/
    @RequestMapping("plan/index.ahsj")
    ModelAndView planIndex(String token, Long id, Long doctorId, Integer isOpreate,String number) throws Exception {
        HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectById(id);
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/treatment_plan");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("recordId", id);
        modelAndView.addObject("number", number);
        modelAndView.addObject("recordNumber", hisMedicalRecord.getMedicalRecordId());
        HisMedical hisMedical = hisMedicalService.selectByRecordId(id);
        if (!EmptyUtil.Companion.isNullOrEmpty(hisMedical)) {
            modelAndView.addObject("hisMedical", hisMedical);
        } else {
            modelAndView.addObject("hisMedical", new HisMedical());

        }
        List<HisMedicationDetails> hisMedicationDetails = hisMedicationDetailsService.selectByRecordIdNotIsOutOrIsPay(id);

        if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetails)) {
            //处理id
            for (HisMedicationDetails h :
                    hisMedicationDetails) {
                h.setId(h.getMedicationId());
            }
            modelAndView.addObject("hisMedicalDetailList", hisMedicationDetails);
        } else {
            modelAndView.addObject("hisMedicalDetailList", new ArrayList<HisMedicationDetails>());
        }
        List<HisRecordProject> hisRecordProjectList = hisRecordProjectService.selectByMedicalRecordIdNotIsCheckedOrIspayed(id);
        if (!EmptyUtil.Companion.isNullOrEmpty(hisRecordProjectList)) {
            //处理id
            for (HisRecordProject h :
                    hisRecordProjectList) {
                h.setId(h.getProjectId());
                h.setHisProjectNum(h.getNum());
            }
            modelAndView.addObject("hisMedicalProjectList", hisRecordProjectList);
        } else {
            modelAndView.addObject("hisMedicalProjectList", new ArrayList<HisRecordProject>());
        }
        modelAndView.addObject("isSameUser", isSameUser(doctorId));
        //当isOpreate为-1是
        if (null != isOpreate && isOpreate == 1) {
            modelAndView.addObject("isSameUser", false);
        }

        //查询对应未付款的输液单
        List<HisInfusion> hisInfusionList = hisInfusionService.selectByRecordNumberAndNotPay(hisMedicalRecord.getMedicalRecordId());
        if(!EmptyUtil.Companion.isNullOrEmpty(hisInfusionList) && hisInfusionList.size() > 0) {
            Long setId = 1L;//保存设置ID为1  当编号发生变化时+1
            String infusionNumber = hisInfusionList.get(0).getNumber();
            for (HisInfusion hisInfusion : hisInfusionList) {
                if(infusionNumber.equals(hisInfusion.getNumber())){

                }else {
                    infusionNumber = hisInfusion.getNumber();
                    setId++;
                }
                hisInfusion.setId(setId);
            }
        }
        modelAndView.addObject("hisInfusionList",hisInfusionList);

        return modelAndView;
    }


    /**
     * @return core.message.Message
     * @Description 新增或保存
     * @Params [hisMedicalModel]
     * @Author chenzhicai
     * @Date 2019-07-12
     * @Time 11:12
     **/
    @PostMapping("saveOrUpdate.ahsj")
    @ResponseBody
    Message saveOrUpdate(@RequestBody HisMedicalModel hisMedicalModel) throws Exception {
        HisMedical hisMedical = hisMedicalModel.getMediCard();
        Long recordId = hisMedicalModel.getRecordId();
        List<HisMedicationDetails> detailsList = hisMedicalModel.getMediDetail();
        List<HisRecordProject> projects = hisMedicalModel.getProjects();
        List<HisInfusion> hisInfusionList = hisMedicalModel.getInfusionDetail();

        return treatmentPlanService.saveOrUpdate(hisMedical, detailsList, projects, recordId,hisInfusionList);
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 返回药方页面
     * @Params []
     * @Author chenzhicai
     * @Date 2019-07-17
     * @Time 02:43
     **/
    @RequestMapping("prescription/index.ahsj")
    ModelAndView prescriptionIndex(HisPrescription hisPrescription, String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/list_prescription");
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 查询药方列表
     * @Params []
     * @Author chenzhicai
     * @Date 2019-07-17
     * @Time 02:43
     **/
    @PostMapping("prescription/list.ahsj")
    @ResponseBody
    PageBean<HisPrescription> listForMedicalRecord(HisPrescription hisPrescription) {
        PageBean<HisPrescription> prescriptionPageBean = new PageBean<>();
        prescriptionPageBean.setParameter(hisPrescription);
        return hisPrescriptionService.listForMedicalRecord(prescriptionPageBean);
    }

    /**
     * @return
     * @Description 查询可用药方（药方中的药品医院有且数量充足，药方处于启用状态）
     * @Params
     * @Author zhushixiang
     * @Date 2019-08-27
     * @Time 9:46
     **/
    @RequestMapping("prescription/listByTreatmentPlan.ahsj")
    @ResponseBody
    PageBean<HisPrescription> listByTreatmentPlan(HisPrescription hisPrescription) {
        PageBean<HisPrescription> prescriptionPageBean = new PageBean<>();
        prescriptionPageBean.setParameter(hisPrescription);
        return hisPrescriptionService.listByTreatmentPlan(prescriptionPageBean);
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 查询药方列表
     * @Params []
     * @Author chenzhicai
     * @Date 2019-07-17
     * @Time 02:43
     **/
    @PostMapping("prescription/details.ahsj")
    @ResponseBody
    List<HisPrescriptionMedicine> listByPreseId(Long id)throws Exception {
        List<HisPrescriptionMedicine> hisPrescriptionMedicineList = hisPrescriptionMedicineService.selectByPrescriptionIdForTable(id.toString());
        return hisPrescriptionMedicineService.selectByPrescriptionIdForTable(id.toString());
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 返回药方页面
     * @Params []
     * @Author chenzhicai
     * @Date 2019-07-17
     * @Time 02:43
     **/
    @RequestMapping("project/index.ahsj")
    ModelAndView projectIndex(HisPrescription hisPrescription, String token,@RequestParam(value = "medicalOrderNumber",required = false) String medicalOrderNumber) throws Exception {
        UserInfo userLoginId = iUserService.getUserLoginId(getUserId());

        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/list_project");
        modelAndView.addObject("token", token);
        if(!EmptyUtil.Companion.isNullOrEmpty(medicalOrderNumber))
            modelAndView.addObject("medicalOrderNumber", medicalOrderNumber);
            modelAndView.addObject("departmentId", userLoginId.getDeptId());
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 返回药方页面
     * @Params []
     * @Author chenzhicai
     * @Date 2019-07-17
     * @Time 02:43
     **/
    @RequestMapping("combine/index.ahsj")
    ModelAndView combineIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/list_combine");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 查询药方列表
     * @Params []
     * @Author chenzhicai
     * @Date 2019-07-17
     * @Time 02:43
     **/
    @PostMapping("project/details.ahsj")
    @ResponseBody
    List<HisProject> listByCombineId(HisProject hisProject) throws Exception {
        PageBean<HisProject> pageBean = new PageBean<>();
        pageBean.setParameter(hisProject);
        List<HisProject> hisProjectList = hisProjectService.queryCombinationIds(pageBean).getData();
        return hisProjectList;
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 此控制器有三重功能
     * 若病人未住院：显示当次门诊病历信息 isOpreate = false
     * 若病人住过院但是已经出院了：显示当次门诊病历信息 isOpreate = false
     * 若病人在住院还未出院：可操作为病人开药以及项目等 isOpreate = true
     * @Params [token, id, doctorId, isOpreate]
     * @Author zhushixiang
     * @Date 2019-08-16
     * @Time 11:27
     **/
    @RequestMapping("record/index.ahsj")
    ModelAndView recordIndex(String token, Long id, boolean isOpreate) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/treatment_record");
        //isOpreate = true说明病人在住院还未出院：可操作为病人开药以及项目等
        HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectById(id);
        //根据就诊编号查询出住院信息
        HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByNumber(hisMedicalRecord.getMedicalRecordId());
        List<HisMedicationDetails> hisMedicationDetails = new ArrayList<>();
        List<HisRecordProject> hisRecordProjectList = new ArrayList<>();
        if (isOpreate) {
            modelAndView.addObject("title", "住院病人治疗方案");
            hisMedicationDetails = hisMedicationDetailsService.selectByRecordIdNotIsOutOrIsPay(id);
            hisRecordProjectList = hisRecordProjectService.selectByMedicalRecordIdNotIsCheckedOrIspayed(id);
        } else {
            modelAndView.addObject("title", "治疗方案查看");
            hisMedicationDetails = hisMedicationDetailsService.selectByRecordId(id);
            hisRecordProjectList = hisRecordProjectService.selectByMedicalRecordId(id);
        }
        modelAndView.addObject("isOpreate", isOpreate);
        modelAndView.addObject("token", token);

        modelAndView.addObject("recordId", id);
        HisMedical hisMedical = hisMedicalService.selectByRecordId(id);
        if (!EmptyUtil.Companion.isNullOrEmpty(hisMedical)) {
            modelAndView.addObject("hisMedical", hisMedical);
        } else {
            modelAndView.addObject("hisMedical", new HisMedical());
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetails)) {
            //处理id
            for (HisMedicationDetails h :
                    hisMedicationDetails) {
                h.setId(h.getMedicationId());
            }
            modelAndView.addObject("hisMedicalDetailList", hisMedicationDetails);
        } else {
            modelAndView.addObject("hisMedicalDetailList", new ArrayList<HisMedicationDetails>());
        }

        if (!EmptyUtil.Companion.isNullOrEmpty(hisRecordProjectList)) {
            //处理id
            for (HisRecordProject h :
                    hisRecordProjectList) {
                h.setId(h.getProjectId());
            }
            modelAndView.addObject("hisMedicalProjectList", hisRecordProjectList);
        } else {
            modelAndView.addObject("hisMedicalProjectList", new ArrayList<HisRecordProject>());
        }

        //当isOpreate为-1是
        if (isOpreate == false) {
            modelAndView.addObject("isSameUser", false);
        } else {
            modelAndView.addObject("isSameUser", true);
        }
        return modelAndView;
    }

    /**
     * @param id
     * @Description
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     * @Date 2019/8/28
     * @Time 14:09
     **/
    @PostMapping("prescription/detailsFor.ahsj")
    @ResponseBody
    List<HisPrescriptionMedicine> listByPreseIds(Long id) throws Exception{
        return hisPrescriptionMedicineService.selectByPrescriptionIdForTables(id);
    }

    /**
     *@Description
     *@Params [id]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 15:18
    **/
    @RequestMapping("detailsForByPrescriptionId.ahsj")
    @ResponseBody
    List<HisPrescriptionMedicine> detailsForByPrescriptionId(Long prescriptionId)throws Exception{
        HisPrescription hisPrescription = hisPrescriptionService.selectByPrescriptionId(prescriptionId);
        if(EmptyUtil.Companion.isNullOrEmpty(hisPrescription))
            return new ArrayList<HisPrescriptionMedicine>();
        return hisPrescriptionMedicineService.selectByPrescriptionIdForTables(hisPrescription.getId());
    }



    /**
     * @param hisPrescription
     * @Description 查看药方信息
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescription>
     * @Date 2019/8/28
     * @Time 9:33
     **/
    @RequestMapping("prescription/listByTreatmentPlans.ahsj")
    @ResponseBody
    PageBean<HisPrescription> listByTreatmentPlans(HisPrescription hisPrescription) {
        PageBean<HisPrescription> prescriptionPageBean = new PageBean<>();
        prescriptionPageBean.setParameter(hisPrescription);
        return hisPrescriptionService.listByTreatmentPlans(prescriptionPageBean);
    }
}

    