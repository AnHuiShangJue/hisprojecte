package com.ahsj.hiscore.controller.hisObstetrics;

import com.ahsj.hiscore.entity.HisObstetrics;
import com.ahsj.hiscore.services.HisObstetricsService;
import core.controller.BaseController;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/16
 * @Time 15:12
 **/

@Controller
@RequestMapping("/api/HisObstetrics/")
public class HisObstetricsController extends BaseController {

    @Autowired
    HisObstetricsService hisObstetricsService;

    /**
     * @Description 孕科护理的添加和修改
     * @Params: [hisObstetrics]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/16
     * @Time 17:06
     **/
    @RequestMapping(value = "saveOrUpdate.ahsj")
    @ResponseBody
    public Message saveOrUpdate(HisObstetrics hisObstetrics) throws Exception {
        return hisObstetricsService.saveOrUpdate(hisObstetrics);
    }

    /**
     * @Description 护士签字
     * @Params: [hisObstetricsId, hisObstetrics]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/16
     * @Time 17:07
     **/
    @RequestMapping(value = "update.ahsj")
    @ResponseBody
    public Message updateHospital(Long hisHospitalManageId, HisObstetrics hisObstetrics) throws Exception {
        hisObstetrics.setHisHospitalManageId(hisHospitalManageId);
        hisObstetrics.setDoctorId(getId());
        return hisObstetricsService.affirm(hisObstetrics);
    }

    /**
     * @Description 查看孕科护理
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisNursingAssessment
     * @Date 2019/9/16
     * @Time 17:09
     **/
    @RequestMapping(value = "getHisObstetrics/index.ahsj")
    @ResponseBody
    public HisObstetrics getHisObstetrics(String number) throws Exception {
        return hisObstetricsService.selectByNumber(number);
    }

}
