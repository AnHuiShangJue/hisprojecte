package com.ahsj.hiscore.controller.nursingRecord;

import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisNursingRecord;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisNursingRecordService;
import com.ahsj.hiscore.services.HisPatientService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api/nursingRecord/")
public class nursingRecordController extends BaseController {

    @Autowired
    HisNursingRecordService hisNursingRecordService;

    @Autowired
    HisPatientService hisPatientService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    /**
     * @return
     * @Description list
     * @Params
     * @Author jin
     * @Date 2019/7/29
     * @Time 17:28
     */
    @RequestMapping(value = "list.ahsj")
    @ResponseBody
    public PageBean<HisNursingRecord> listByNumber(Map<String, Object> model, HttpServletRequest request, HisNursingRecord hisNursingRecord
            , @RequestParam(value = "manageNumber", required = true) String manageNumber
    ) throws Exception {
        PageBean<HisNursingRecord> pageBean = new PageBean<HisNursingRecord>();
        pageBean.setParameter(hisNursingRecord);
        pageBean = hisNursingRecordService.listByManageNumber(pageBean, manageNumber);
        return pageBean;
    }

    /**
     * @return
     * @Description saveOrUpdate
     * @Params
     * @Author jin
     * @Date 2019/7/30
     * @Time 9:55
     */
    @RequestMapping(value = "saveOrUpdate/index.ahsj")
    ModelAndView ankleRecord(String token, Long id, String manageNumber) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/ankleRecord/saveOrUpdate.html");
        modelAndView.addObject("token", token);
        modelAndView.addObject("title", "当次护理记录查看");
        modelAndView.addObject("manageNumber", manageNumber);
        if (EmptyUtil.Companion.isNullOrEmpty(id)) {
            modelAndView.addObject("nursingRecord", new HisNursingRecord());
        } else {
            modelAndView.addObject("nursingRecord", hisNursingRecordService.selectByPrimaryId(id));
        }
        return modelAndView;
    }


    /**
     * @return
     * @Description saveOrUpdate
     * @Params
     * @Author jin
     * @Date 2019/7/30
     * @Time 13:12
     */
    @RequestMapping(value = "saveOrUpdate.ahsj")
    @ResponseBody
    public Message saveOrUpdate(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "id", required = false) Long id
            , @RequestParam(value = "manageNumber", required = true) String manageNumber
            , @RequestParam(value = "bodyTemperature", required = false) Double bodyTemperature
            , @RequestParam(value = "pulse", required = false) String pulse
            , @RequestParam(value = "breathe", required = false) String breathe
            , @RequestParam(value = "heartRate", required = false) String heartRate
            , @RequestParam(value = "bloodPressure", required = false) String bloodPressure
            , @RequestParam(value = "painScore", required = false) String painScore
            , @RequestParam(value = "oxygenSaturation", required = false) String oxygenSaturation
            , @RequestParam(value = "flow", required = false) String flow
            , @RequestParam(value = "oxygenAbsorption", required = false) String oxygenAbsorption
            , @RequestParam(value = "sucking", required = false) String sucking
            , @RequestParam(value = "venousCatheterName", required = false) String venousCatheterName
            , @RequestParam(value = "venousCatheterCare", required = false) String venousCatheterCare
            , @RequestParam(value = "involvementProject", required = false) String involvementProject
            , @RequestParam(value = "involvement", required = false) String involvement
            , @RequestParam(value = "outputProject", required = false) String outputProject
            , @RequestParam(value = "outputTraits", required = false) String outputTraits
            , @RequestParam(value = "output", required = false) String output
            , @RequestParam(value = "other", required = false) String other
            , @RequestParam(value = "remark", required = false) String remark
            , @RequestParam(value = "signature", required = false) String signature
            , @RequestParam(value = "dates", required = false) Date dates
    ) throws Exception {
        HisNursingRecord hisNursingRecord = new HisNursingRecord();
        if (null != request.getParameter("id")) {
            hisNursingRecord.setId(id);
        }
        if (null != request.getParameter("manageNumber")) {
            hisNursingRecord.setManageNumber(manageNumber);
        }
        if (null != request.getParameter("bodyTemperature")) {
            hisNursingRecord.setBodyTemperature(bodyTemperature);
        }
        if (null != request.getParameter("manageNumber")) {
            hisNursingRecord.setManageNumber(manageNumber);
        }
        if (null != request.getParameter("pulse")) {
            hisNursingRecord.setPulse(pulse);
        }
        if (null != request.getParameter("breathe")) {
            hisNursingRecord.setBreathe(breathe);
        }
        if (null != request.getParameter("heartRate")) {
            hisNursingRecord.setHeartRate(heartRate);
        }
        if (null != request.getParameter("bloodPressure")) {
            hisNursingRecord.setBloodPressure(bloodPressure);
        }
        if (null != request.getParameter("painScore")) {
            hisNursingRecord.setPainScore(painScore);
        }
        if (null != request.getParameter("oxygenSaturation")) {
            hisNursingRecord.setOxygenSaturation(oxygenSaturation);
        }
        if (null != request.getParameter("flow")) {
            hisNursingRecord.setFlow(flow);
        }
        if (null != request.getParameter("oxygenAbsorption")) {
            hisNursingRecord.setOxygenSaturation(oxygenAbsorption);
        }
        if (null != request.getParameter("sucking")) {
            hisNursingRecord.setSucking(sucking);
        }
        if (null != request.getParameter("venousCatheterName")) {
            hisNursingRecord.setVenousCatheterName(venousCatheterName);
        }
        if (null != request.getParameter("venousCatheterCare")) {
            hisNursingRecord.setVenousCatheterCare(venousCatheterCare);
        }
        if (null != request.getParameter("involvementProject")) {
            hisNursingRecord.setInvolvementProject(involvementProject);
        }
        if (null != request.getParameter("involvement")) {
            hisNursingRecord.setInvolvement(involvement);
        }
        if (null != request.getParameter("outputProject")) {
            hisNursingRecord.setOutputProject(outputProject);
        }
        if (null != request.getParameter("outputTraits")) {
            hisNursingRecord.setOutputTraits(outputTraits);
        }
        if (null != request.getParameter("output")) {
            hisNursingRecord.setOutput(output);
        }
        if (null != request.getParameter("other")) {
            hisNursingRecord.setOther(other);
        }
        if (null != request.getParameter("remark")) {
            hisNursingRecord.setRemark(remark);
        }
        if (null != request.getParameter("signature")) {
            hisNursingRecord.setSignature(signature);
        }
        if (null != request.getParameter("dates")) {
            hisNursingRecord.setDates(dates);
        }

        return hisNursingRecordService.saveOrUpdate(hisNursingRecord);
    }

    /**
     * @return
     * @Description delete
     * @Params
     * @Author jin
     * @Date 2019/7/30
     * @Time 14:58
     */
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message delete(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "ids", required = true) Long[] ids
    ) throws Exception {
        if (null != request.getParameter("ids")) {
            return hisNursingRecordService.delete(ids);
        } else return MessageUtil.createMessage(false, "参数异常");
    }

    /**
     * @Description 跳转温度折线图
     * @Param token:
     * @Param manageNumber:
     * @Author: dingli
     * @return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/7/31
     * @Time 9:42
     **/
    @RequestMapping(value = "show.ahsj")
    @ResponseBody
    ModelAndView show(String token, String manageNumber, String medicalRecordId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/ankleRecord/show.html");
        modelAndView.addObject("token", token);
        modelAndView.addObject("manageNumber", manageNumber);
        modelAndView.addObject("medicalRecordId", medicalRecordId);
        modelAndView.addObject("hisPatientInfo", hisPatientService.selectByMedicalRecordId(medicalRecordId));
        return modelAndView;
    }

    /**
     * @Description 获取温度时间信息
     * @Param manageNumber:
     * @Param token:
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisNursingRecord>
     * @Date 2019/7/31
     * @Time 9:42
     **/
    @RequestMapping(value = "getshow.ahsj")
    @ResponseBody
    public List<HisNursingRecord> getShow(HisNursingRecord hisNursingRecord) throws Exception {
        return hisNursingRecordService.list(hisNursingRecord);
    }


    /**
     * @return
     * @Description 跳转打印体温单页面
     * @Params
     * @Author jin
     * @Date 2019/8/6
     * @Time 16:26
     */
    @RequestMapping(value = "printTemp/index.ahsj")
    public ModelAndView printTemp(String token, String hosNumber, String name, String age, String sex) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/ankleRecord/thermogram");
        modelAndView.addObject("title", "打印催款单");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hosNumber", hosNumber);//住院编号
        modelAndView.addObject("name", name);//名字
        modelAndView.addObject("age", age);//年龄
        modelAndView.addObject("sex", sex);//性别
        HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByHospNumber(hosNumber);
        modelAndView.addObject("departmentName", hisHospitalManage.getDepartmentName());//科室名
        modelAndView.addObject("wardsNumber", hisHospitalManage.getBedsNumber());//病房号
        modelAndView.addObject("bedsNumber", hisHospitalManage.getWardsNumber());//病床号
        return modelAndView;
    }

}
