package com.ahsj.hiscore.controller.first_course_record;

import com.ahsj.hiscore.entity.HisFirstCourseRecord;
import com.ahsj.hiscore.services.HisFirstCourseRecordService;
import core.controller.BaseController;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/api/firstCourseRecord/")
public class HisFirstCourseRecordController extends BaseController {
    @Autowired
    HisFirstCourseRecordService hisFirstCourseRecordService;

    /**
     *@Description 新增或更新
     *@Params [model, hisPharmacyDetail]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 11:43
     **/
    @RequestMapping(value = "saveOrUpdate.ahsj")
    @ResponseBody
    public Message saveOrUpdate(Map<String, Object> model, HisFirstCourseRecord hisFirstCourseRecord) throws Exception {
        return hisFirstCourseRecordService.saveOrUpdate(hisFirstCourseRecord);
    }

    /**
     *@Description 签字
     *@Params [model, hisFirstCourseRecordService]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 15:04
     **/
    @RequestMapping(value = "sign.ahsj")
    @ResponseBody
    public Message sign(Map<String, Object> model, @RequestParam(value = "id",required = true)Long id,@RequestParam(value = "loginUserId",required = true)Long loginUserId) throws Exception {
        String signName = getUserId();
        return hisFirstCourseRecordService.sign(id,loginUserId,signName);
    }
}
