package com.ahsj.hiscore.controller.medicine;

import com.ahsj.hiscore.entity.HisMedical;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.entity.HisRegistered;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/api/hismedicalrecord/")
public class HisMedicalRecordController extends BaseController {
    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisRegisteredService hisRegisteredService;

    @Autowired
    HisDoctorInfoService hisDoctorInfoService;

    @Autowired
    HisPatientService hisPatientService;

    @Autowired
    HisMedicalService hisMedicalService;


    /**
     * @Description 就诊list
     * @Author muxu
     * @Date 2019/6/23
     * @Time 17:47
     * @Return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Params [model, request, HisMedicalRecord]
     **/
    //@ApiOperation(value = "分页查询")
    @ResponseBody
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.POST})
    public PageBean<HisMedicalRecord> list(Map<String, Object> model, HttpServletRequest request, HisMedicalRecord HisMedicalRecord) throws Exception {
        PageBean<HisMedicalRecord> pageBean = new PageBean<HisMedicalRecord>();
        pageBean.setParameter(HisMedicalRecord);
        return hisMedicalRecordService.list(pageBean);
    }

    /**
     * @Description 就诊记录list
     * @Author muxu
     * @Date 2019/7/7
     * @Time 14:45
     * @Return
     * @Params
     **/
    @ResponseBody
    @RequestMapping(value = "medicalrecordlist.ahsj", method = {RequestMethod.POST})
    public PageBean<HisMedicalRecord> medicalrecordlist(Map<String, Object> model, HttpServletRequest request, HisMedicalRecord HisMedicalRecord) throws Exception {
        PageBean<HisMedicalRecord> pageBean = new PageBean<HisMedicalRecord>();
        pageBean.setParameter(HisMedicalRecord);
        return hisMedicalRecordService.medicalrecordlist(pageBean);
    }


    /**
     * @Description 新增或更新
     * @Author muxu
     * @Date 2019/7/1
     * @Time 23:02
     * @Return core.message.Message
     * @Params [model, hisMedicalRecord]
     **/
    @RequestMapping(value = "recEnter.ahsj")
    @ResponseBody
    public Message recEnter(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "id", required = true) Long id, @RequestParam(value = "type", required = false) Integer type, @RequestParam(value = "patientId", required = false) Long patientId) throws Exception {
        if (null != request.getParameter("id")) {
            return hisMedicalRecordService.recEnter(id, getId().toString(), type, patientId);
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
    public Message saveOrUpdate(Map<String, Object> model, HttpServletRequest request, HisMedicalRecord hisMedicalRecord) throws Exception {
        String Authorization = request.getHeader("Authorization");
        String ContentType = request.getHeader("Content-Type");
        return hisMedicalRecordService.saveOrUpdate(hisMedicalRecord, request);
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
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/list");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("userDepartId", getDeptId());
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
    ModelAndView updateIndex(String token, HisRegistered hisRegistered, HisMedicalRecord hisMedicalRecord, String numbers) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/update");
        //modelAndView.addObject("userName", getUserName());
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("numbers", numbers);
        modelAndView.addObject("userId", getId());
        modelAndView.addObject("hisPatientInfo", hisPatientService.selectById(hisRegistered.getPatientId()));
        modelAndView.addObject("hisMedicalRecord", hisMedicalRecordService.selectByRegisterId(hisRegistered.getId()));
        return modelAndView;
    }


    /**
     * @Description
     * @Author muxu
     * @Date 2019/7/8
     * @Time 11:47
     * @Return
     * @Params
     **/
    @RequestMapping("medicalrecordlist/index.ahsj")
    ModelAndView medicalrecordlistIndex(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/medicalrecordlist");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("userId", getId());
        return modelAndView;
    }

    /**
     *@Description cpoy 上面的，跳不同页面
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/17
     *@Time 14:36
    */
    @RequestMapping("outpatientRecord/index.ahsj")
    ModelAndView outpatientRecord(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/outpatientRecord");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("userId", getId());
        return modelAndView;
    }


    /**
     * @Description
     * @Author muxu
     * @Date 2019/7/8
     * @Time 11:48
     * @Return
     * @Params
     **/

    @RequestMapping("medicalrecordupdate/index.ahsj")
    ModelAndView medicalrecordIndex(String token, HisMedicalRecord hisMedicalRecord, Integer isOpreate) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/medicalrecordupdate");
        //modelAndView.addObject("userName", getUserName());
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hisMedical", hisMedicalService.selectByRecordId(Long.valueOf(hisMedicalRecord.getId())));
        modelAndView.addObject("hisMedicalRecord", hisMedicalRecordService.selectById(hisMedicalRecord.getId()));
        modelAndView.addObject("hisRegistered", hisRegisteredService.selectById(hisMedicalRecord.getRegisteredId()));
        modelAndView.addObject("hisPatientInfo", hisPatientService.selectById(hisMedicalRecord.getPatientId()));
        modelAndView.addObject("isOpreate", isOpreate);
        /*modelAndView.addObject("UserName",hisMedicalRecordService.selectByUserId(hisMedicalRecord.getDoctorId()));*/
        return modelAndView;
    }

    /**
     * @Description
     * @Author muxu
     * @Date 2019/7/19
     * @Time 21:07
     * @Return
     * @Params
     **/

    @RequestMapping("medicalrecordotherupdate/index.ahsj")
    ModelAndView othermedicalrecordIndex(String token, HisMedicalRecord hisMedicalRecord, Integer isOpreate) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/medicalrecord_otherupdate");
        //modelAndView.addObject("userName", getUserName());
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hisMedicalRecord", hisMedicalRecordService.selectById(hisMedicalRecord.getId()));
        modelAndView.addObject("hisRegistered", hisRegisteredService.selectById(hisMedicalRecord.getRegisteredId()));
        modelAndView.addObject("hisPatientInfo", hisPatientService.selectById(hisMedicalRecord.getPatientId()));
        /*modelAndView.addObject("UserName",hisMedicalRecordService.selectByUserId(hisMedicalRecord.getDoctorId()));*/
        modelAndView.addObject("isOpreate", isOpreate);
        return modelAndView;
    }

    /**
     * @Description 删除
     * @Author muxu
     * @Date 2019/7/8
     * @Time 18:40
     * @Return core.message.Message
     * @Params [model, request, ids]
     **/
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message delete(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "ids", required = true) Long[] ids
    ) throws Exception {
        if (null != request.getParameter("ids")) {
            return hisMedicalRecordService.delete(ids);
        } else return MessageUtil.createMessage(false, "参数异常");
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Description 查看过往病历（由就诊记录表medical_record,住院信息表hospital_manage,护嘱表ankle以及医嘱表medical_order中的信息组合而成）根据PatientId来查找此病人病历
     * @Params [model, request, HisMedicalRecord]
     * @Author zhushixiang
     * @Date 2019-07-15
     * @Time 16:05
     **/
    @ResponseBody
    @RequestMapping(value = "listForMedicalHistoryByPatientId.ahsj")
    public PageBean<HisMedicalRecord> listForMedicalHistoryByPatientId(Map<String, Object> model, HttpServletRequest request, HisMedicalRecord hisMedicalRecord) throws Exception {
        PageBean<HisMedicalRecord> pageBean = new PageBean<HisMedicalRecord>();
        pageBean.setParameter(hisMedicalRecord);
        return hisMedicalRecordService.listForMedicalHistoryByPatientId(pageBean);
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 历史病历修改
     * @Params [token, hisMedicalRecord, isOpreate]
     * @Author zhushixiang
     * @Date 2019-08-16
     * @Time 17:50
     **/
    @RequestMapping("updateHistory/index.ahsj")
    ModelAndView updateHistory(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/history_medicalrecord");
        modelAndView.addObject("title", "医院历史病历");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return
     * @Description 查看所有病人的信息
     * @Params
     * @Author zhushixiang
     * @Date 2019-08-17
     * @Time 14:51
     **/
    @RequestMapping("historyMedicalrecordPatientinfo/index.ahsj")
    ModelAndView historyMedicalrecordPatientinfo(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/history_medicalrecord_patientinfo");
        modelAndView.addObject("title", "医院所有病人信息");
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     * @return
     * @Description 病人病历查看（注意不能查看正在住院的人的本次就诊病历）
     * @Params
     * @Author zhushixiang
     * @Date 2019-08-17
     * @Time 14:58
     **/
    @RequestMapping("listForHistory/index.ahsj")
    ModelAndView listForHistory(String token, Long patientId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/history_medicalrecord_detail");
        modelAndView.addObject("title", "病人病历信息");
        modelAndView.addObject("token", token);
        modelAndView.addObject("patientId", patientId);
        return modelAndView;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Description 查看病人所有历史病历
     * @Params [model, request, hisMedicalRecord]
     * @Author zhushixiang
     * @Date 2019-08-17
     * @Time 15:05
     **/
    @ResponseBody
    @RequestMapping(value = "listForAllMedicalHistoryByPatientId.ahsj")
    public PageBean<HisMedicalRecord> listForAllMedicalHistoryByPatientId(Map<String, Object> model, HttpServletRequest request, HisMedicalRecord hisMedicalRecord) throws Exception {
        PageBean<HisMedicalRecord> pageBean = new PageBean<HisMedicalRecord>();
        pageBean.setParameter(hisMedicalRecord);
        return hisMedicalRecordService.listForAllMedicalHistoryByPatientId(pageBean);
    }

    /**
     * @return
     * @Description 住院病历修改
     * @Params
     * @Author zhushixiang
     * @Date 2019-08-17
     * @Time 16:16
     **/
    @RequestMapping("updateHistoryForInHospital/index.ahsj")
    ModelAndView updateHistoryForInHospital(String token, Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/updateHistoryForInHospital");
        modelAndView.addObject("title", "住院病历修改");
        HisMedical hisMedical = hisMedicalService.selectByRecordId(id);
        modelAndView.addObject("hisMedical", hisMedical);
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @Description 查询输液单可选药品信息
     * @Author muxu
     * @Date 2019/8/29
     * @Time 15:27
     * @Return
     * @Params
     **/
    @RequestMapping("OutpatientInfusionList/index.ahsj")
    ModelAndView selectOutpatientInfusionByMedicalRecordId(String token, Long id, Long patientId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/outpatient_infusion");
        modelAndView.addObject("title", "输液单");
        modelAndView.addObject("token", token);
        modelAndView.addObject("medicalRecordId", id);
        modelAndView.addObject("patientId", patientId);
        return modelAndView;
    }

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisMedical
     * @Date 2019/9/7
     * @Time 12:57
     **/
    @RequestMapping("/print.ahsj")
    @ResponseBody
    HisMedical selectPrint(String number) throws Exception {
        return hisMedicalService.selectPrint(number);
    }

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisMedicalRecord
     * @Date 2019/9/7
     * @Time 14:06
     **/
    @ResponseBody
    @RequestMapping("selectPrint1.ahsj")
    public HisMedicalRecord selectPrintDetail(String number) throws Exception {
        return hisMedicalRecordService.selectPrint(number);
    }
}
