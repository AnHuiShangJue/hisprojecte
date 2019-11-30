package com.ahsj.hiscore.controller.HospitalManage;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.dao.HisHospitalManageMapper;
import com.ahsj.hiscore.dao.HisHosptalregistMapper;
import com.ahsj.hiscore.dao.HisUserNurseMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.*;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/api/hishospitalmanage/")
public class HisHospitalManageController extends BaseController {
    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisRegisteredService hisRegisteredService;

    @Autowired
    HisDoctorInfoService hisDoctorInfoService;

    @Autowired
    HisPatientService hisPatientService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisNurseService hisNurseService;

    @Autowired
    HisBedService hisBedService;

    @Autowired
    HisWradService hisWradService;

    @Autowired
    HisHosptalregistService hisHosptalregistService;

    @Autowired
    HisUserDoctorService hisUserDoctorService;

    @Autowired
    HisUserNurseMapper hisUserNurseMapper;

    @Autowired
    HisHosptalregistMapper hisHosptalregistMapper;

    @Autowired
    HisHospitalManageMapper hisHospitalManageMapper;


    /**
     * @Description 就诊list
     * @Author muxu
     * @Date 2019/6/23
     * @Time 17:47
     * @Return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Params [model, request, HisMedicalRecord]
     **/
    @ResponseBody
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.POST})
    public PageBean<HisHospitalManage> list(Map<String, Object> model, HttpServletRequest request, HisHospitalManage HisHospitalManage) throws Exception {
        PageBean<HisHospitalManage> pageBean = new PageBean<HisHospitalManage>();
        pageBean.setParameter(HisHospitalManage);
        return hisHospitalManageService.list(pageBean);
    }


    /**
     * @Description 新增或更新
     * @Author muxu
     * @Date 2019/7/1
     * @Time 23:02
     * @Return core.message.Message
     * @Params [model, hisMedicalRecord]
     **/
    @RequestMapping(value = "hosEnter.ahsj")
    @ResponseBody
    public Message hosEnter(Map<String, Object> model, HttpServletRequest request,
                            @RequestParam(value = "id", required = true) Long id)
            throws Exception {
        if (null != request.getParameter("id")) {
            return hisHospitalManageService.hosEnter(id);
        } else return MessageUtil.createMessage(false, "参数异常");
    }

    /**
     * @Description 新增或更新
     * @Author muxu
     * @Date 2019/7/1
     * @Time 23:02
     * @Return core.message.Message
     * @Params [model, hisMedicalRecord]
     **/
    @RequestMapping(value = "fail.ahsj")
    @ResponseBody
    public Message fail(Map<String, Object> model, HttpServletRequest request,
                        @RequestParam(value = "id", required = true) Long id)
            throws Exception {
        if (null != request.getParameter("id")) {
            return hisHospitalManageService.fail(id);
        } else return MessageUtil.createMessage(false, "参数异常");
    }


    /**
     * @Description
     * @Author muxu
     * @Date 2019/7/1
     * @Time 23:02
     * @Return core.message.Message
     * @Params [model, request, hisMedicalRecord]
     **/
    @RequestMapping(value = "saveOrUpdate.ahsj")
    @ResponseBody
    public Message saveOrUpdate(HisHospitalManage hisHospitalManage) throws Exception {
        return hisHospitalManageService.saveOrUpdate(hisHospitalManage);
    }

    /**
     * @Description list
     * @Author muxu
     * @Date 2019/7/1
     * @Time 23:02
     * @Return org.springframework.web.servlet.ModelAndView
     * @Params [token, hisMedicalRecord]
     **/
    @RequestMapping("list/index.ahsj")
    ModelAndView listIndex(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hospitalmanage/list");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
//        modelAndView.addObject("userDepartId", getDeptId());
        return modelAndView;
    }

    /**
     * @Description 病人信息详细查看
     * @Author muxu
     * @Date 2019/7/1
     * @Time 23:09
     * @Return org.springframework.web.servlet.ModelAndView
     * @Params [token, hisRegistered]
     **/
    @RequestMapping("update/index.ahsj")
    ModelAndView updateIndex(String token, HisHospitalManage hisHospitalManage, Long hosptalRegistId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hospitalmanage/update");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        if (EmptyUtil.Companion.isNullOrEmpty(hosptalRegistId)) {
            modelAndView.addObject("hisPatientInfo", hisPatientService.selectById(hisHospitalManage.getPatientId()));
            modelAndView.addObject("hisDoctorInfo", hisDoctorInfoService.selectById(hisHospitalManage.getDoctorId()));
            modelAndView.addObject("hisHospitalManage", hisHospitalManage);
            modelAndView.addObject("hisHosptalregist", hisHosptalregistService.selectById(hisHospitalManage.getIds()));
        } else {
            HisHosptalregist hisHosptalregist = hisHosptalregistService.selectById(hosptalRegistId);
            modelAndView.addObject("hisPatientInfo", hisPatientService.selectById(hisHosptalregist.getPatientId()));
            modelAndView.addObject("hisDoctorInfo", hisDoctorInfoService.selectById(hisHosptalregist.getDoctorId()));
            modelAndView.addObject("hisHosptalregist", hisHosptalregist);

            hisHospitalManage.setPatientId(hisHosptalregist.getPatientId());
            hisHospitalManage.setDoctorId(hisHosptalregist.getDoctorId());
            hisHospitalManage.setIds(hosptalRegistId);
            modelAndView.addObject("hisHospitalManage", hisHospitalManage);
            modelAndView.addObject("flag", 1);

        }


        return modelAndView;
    }

    /**
     * @Description view
     * @Author muxu
     * @Date 2019/7/17
     * @Time 15:40
     * @Return
     * @Params
     **/
    @RequestMapping("view/index.ahsj")
    ModelAndView viewIndex(String token, HisHosptalregist hisHosptalregist, HisHospitalManage hisHospitalManage, Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hospitalmanage/view");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hisPatientInfo", hisPatientService.selectById(hisHosptalregist.getPatientId()));
        modelAndView.addObject("hisDoctorInfo", hisDoctorInfoService.selectById(hisUserDoctorService.selectByUserId(hisHosptalregist.getDoctorId()).getDoctorId()));
      /*  if (hisHospitalManage.getWardId() != 0) {
            modelAndView.addObject("hisWard", hisWradService.selectByPrimaryKey(hisHospitalManage.getWardId()));
        }
        if (hisHospitalManage.getBedId() != 0) {
            modelAndView.addObject("hisBed", hisBedService.selectByPrimaryKey(hisHospitalManage.getBedId()));
        }*/
        if (hisHosptalregist.getNurseId() != 0) {
            modelAndView.addObject("hisNurse", hisNurseService.selectById(hisUserNurseMapper.selectByUserId(hisHosptalregist.getNurseId()).getNurseId()));
        }
        modelAndView.addObject("hisHospitalManage", hisHospitalManageMapper.selectByPrimaryKey(id));
        modelAndView.addObject("hisHosptalregist", hisHosptalregistMapper.selectByDepartmentId(hisHospitalManage.getIds()));
        return modelAndView;
    }


    /**
     * @Description 删除
     * @Author muxu
     * @Time 18:40
     * @Return core.message.Message
     * @Params [model, request, ids]
     * @Date 2019/7/8
     **/
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message delete(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "ids", required = true) Long[] ids
    ) throws Exception {
        if (null != request.getParameter("ids")) {
            return hisHospitalManageService.delete(ids);
        } else return MessageUtil.createMessage(false, "参数异常");
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 住院信息表的基本信息查询，（只查询未出院的，且连表查出病房与病床的编号以及医生与护士的姓名）
     * @Params [token]
     * @Author zhushixiang
     * @Date 2019-07-15
     * @Time 11:43
     **/
    @ResponseBody
    @RequestMapping(value = "listForDetails.ahsj", method = {RequestMethod.POST})
    public PageBean<HisHospitalManage> listForDetails(Map<String, Object> model, HttpServletRequest request, HisHospitalManage hisHospitalManage) throws Exception {
        PageBean<HisHospitalManage> pageBean = new PageBean<HisHospitalManage>();
        pageBean.setParameter(hisHospitalManage);
        return hisHospitalManageService.listForDetails(pageBean);
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 查看病人病历信息
     * @Params [token]
     * @Author zhushixiang
     * @Date 2019-07-15
     * @Time 14:11
     **/
    @RequestMapping("listForMedicalRecord/index.ahsj")
    ModelAndView listForMedicalRecord(String token, Long patientId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/doctor/listForMedicalRecord");
        modelAndView.addObject("title", "病人病历信息查看");
        modelAndView.addObject("token", token);
        modelAndView.addObject("patientId", patientId);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 根据就诊编号查看住院信息
     * @Params [token, patientId]
     * @Author zhushixiang
     * @Date 2019-07-16
     * @Time 9:48
     **/
    @RequestMapping("DetailsForHospitalManageByMedicalNumber/index.ahsj")
    ModelAndView listForHospitalManageByMedicalNumber(String token, String medicalRecordId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/doctor/DetailsForHospitalManageByMedicalNumber");
        HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByNumber(medicalRecordId);
        modelAndView.addObject("title", "当次住院信息查看");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hisHospitalManage", hisHospitalManage);
        return modelAndView;
    }


    /**
     * @return
     * @Description 跳转编辑病人住院信息页面
     * @Params
     * @Author jin
     * @Date 2019/9/10
     * @Time 17:26
     */
    @RequestMapping(value = "inpatientUpdate/index.ahsj")
    public ModelAndView inpatientUpdate(String token, Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hospitalmanage/inpatient_update");
        modelAndView.addObject("title", "病人住院信息编辑");
        modelAndView.addObject("token", token);
        if (!EmptyUtil.Companion.isNullOrEmpty(id)) {
            HisHospitalManage hospitalManage = hisHospitalManageService.selectById(id);
            modelAndView.addObject("hisHospitalManage", hospitalManage);
        }
        return modelAndView;
    }

    /**
     * @Description 出院审核
     * @Params: [token]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/9/14
     * @Time 14:30
     **/
    @RequestMapping("discharged/index.ahsj")
    ModelAndView discharged(String token, String number) throws Exception {
        HisHospitalManage hisHospitalManage = hisHospitalManageService.selectNumber(number);
        ModelAndView modelAndView = null;
        if (Integer.parseInt(hisHospitalManage.getHospitalizationDay()) > Constants.HIS_DOCTOR_ONE) {
            modelAndView = new ModelAndView("backend/hiscore/hospitalmanage/audit");
            modelAndView.addObject("number", hisHospitalManage.getNumber());
        } else {//24h内
            modelAndView = new ModelAndView("backend/hiscore/nursestation/inOut24hour");
            modelAndView.addObject("patientId", hisHospitalManage.getPatientId());
            modelAndView.addObject("doctorId", hisHospitalManage.getDoctorId());
            modelAndView.addObject("userId", getId());
            modelAndView.addObject("hospitalnumber", hisHospitalManage.getNumber());
            if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage.getPatientId())) {
                HisPatientInfo hisPatientInfo = hisPatientService.selectById(hisHospitalManage.getPatientId());
                modelAndView.addObject("hisPatientInfo", hisPatientInfo);
            }
        }
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("loginName", getUserName());
        return modelAndView;
    }

    /**
     * @Description 根据住院编号获取病人信息
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisHospitalManage
     * @Date 2019/9/14
     * @Time 15:28
     **/
    @RequestMapping(value = "getHospital/index.ahsj")
    @ResponseBody
    public HisHospitalManage getHospital(String number) throws Exception {
        return hisHospitalManageService.selectNumber(number);
    }

    /**
     * @Description 死亡记录
     * @Params: [token, number]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/9/14
     * @Time 20:02
     **/
    @RequestMapping("death/index.ahsj")
    ModelAndView death(String token, String number) throws Exception {
        HisHospitalManage hisHospitalManage = hisHospitalManageService.selectNumber(number);
        ModelAndView modelAndView = null;
        if (Integer.parseInt(hisHospitalManage.getHospitalizationDay()) > Constants.HIS_DOCTOR_ONE) {
            modelAndView = new ModelAndView("backend/hiscore/hospitalmanage/death");
        } else {//24h内
            HisPatientInfo hisPatientInfo = hisPatientService.selectById(hisHospitalManage.getPatientId());
            modelAndView = new ModelAndView("backend/hiscore/doctor/dieIn24hours");
            modelAndView.addObject("hisPatientInfo", hisPatientInfo);
            modelAndView.addObject("doctorId", hisHospitalManage.getDoctorId());
            modelAndView.addObject("departmentName", hisHospitalManage.getDepartmentIdName());
            modelAndView.addObject("departmentId", hisHospitalManage.getDepartmentId());
            modelAndView.addObject("bedId", hisHospitalManage.getBedId());
//            modelAndView.addObject("bedName",hisHospitalManage.getBedId());


        }
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number", hisHospitalManage.getNumber());
        modelAndView.addObject("loginName", getUserName());
        return modelAndView;
    }

    /**
     * @Description 住院管理设置病床
     * @Params: [token, id]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/11/16
     * @Time 13:24
     **/
    @RequestMapping(value = "setBed/index.ahsj")
    public ModelAndView setBed(String number, String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hospitalmanage/set_bed");
        modelAndView.addObject("title", "选择病床");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number", number);
        return modelAndView;
    }

    /**
     * @Description 设置病床
     * @Params: [bedIds, hisHospitalManage]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/11/16
     * @Time 16:19
     **/
    @RequestMapping(value = "setBed.ahsj")
    @ResponseBody
    public Message setBed(HisHospitalManage hisHospitalManage) throws Exception { //id 接收的病床id，xml不能接收2个参数
        return hisHospitalManageService.sedBed(hisHospitalManage);
    }

    //强制出院
    @RequestMapping(value = "forcedDischarge.ahsj")
    @ResponseBody
    public Message forcedDischarge(HisHospitalManage hisHospitalManage) throws Exception {
        return hisHospitalManageService.forcedDischarge(hisHospitalManage);
    }

}
