package com.ahsj.hiscore.controller.hisHospitalizedRecord;

import com.ahsj.hiscore.entity.HisHospitalizedRecord;
import com.ahsj.hiscore.services.HisHospitalizedRecordService;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/18
 * @Time 10:25
 **/
@Controller
@RequestMapping("/api/hisHospitalizedRecord/")
public class HisHospitalizedRecordController {
    @Autowired
    HisHospitalizedRecordService hisHospitalizedRecordService;

    /**
     * className HisHospitalizedRecordController
     *
     * @author dingli
     * @date 2019/9/18 10:25
     */

    /**
     * @Description 入院记录的增加和修改
     * @Params: [hisHospitalizedRecord]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/18
     * @Time 10:27
     **/
    @RequestMapping(value = "saveOrUpdate/index.ahsj")
    @ResponseBody
    public Message saveOrUpdate(HisHospitalizedRecord hisHospitalizedRecord) throws Exception {
        return hisHospitalizedRecordService.saveOrUpdate(hisHospitalizedRecord);
    }
}
