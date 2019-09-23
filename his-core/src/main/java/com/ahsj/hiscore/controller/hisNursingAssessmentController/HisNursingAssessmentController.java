package com.ahsj.hiscore.controller.hisNursingAssessmentController;

import com.ahsj.hiscore.entity.HisNursingAssessment;
import com.ahsj.hiscore.services.HisNursingAssessmentService;
import core.controller.BaseController;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/15
 * @Time 17:30
 **/
@Controller
@RequestMapping("/api/hisNursingAssessment/")
public class HisNursingAssessmentController extends BaseController {
    @Autowired
    HisNursingAssessmentService hisNursingAssessmentService;

    /**
     * className HisNursingAssessmentController
     *
     * @author dingli
     * @date 2019/9/15 17:30
     */

    /**
     * @Description 入院护理评估
     * @Params: [HisNursingAssessment]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/15
     * @Time 17:33
     **/
    @RequestMapping(value = "saveOrUpdate.ahsj")
    @ResponseBody
    public Message saveOrUpdate(HisNursingAssessment HisNursingAssessment) throws Exception {
        return hisNursingAssessmentService.saveOrUpdate(HisNursingAssessment);
    }

    /**
     * @Description 护士确认
     * @Params: [number, doctor]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/15
     * @Time 17:49
     **/
    @RequestMapping(value = "update.ahsj")
    @ResponseBody
    public Message updateHospital(Long hisHospitalManageId, HisNursingAssessment hisNursingAssessment) throws Exception {
        hisNursingAssessment.setHisHospitalManageId(hisHospitalManageId);
        hisNursingAssessment.setNurseId(getId());
        return hisNursingAssessmentService.affirm(hisNursingAssessment);
    }

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisNursingAssessment
     * @Date 2019/9/15
     * @Time 18:49
     **/
    @RequestMapping(value = "getHisNursingAssessment/index.ahsj")
    @ResponseBody
    public HisNursingAssessment getHisNursingAssessment(String number) throws Exception {
        return hisNursingAssessmentService.selectByNumber(number);
    }


}
