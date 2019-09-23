package com.ahsj.hiscore.controller.hismedical;

import com.ahsj.hiscore.entity.HisMedical;
import com.ahsj.hiscore.services.HisMedicalService;
import core.controller.BaseController;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/api/hismedical")
public class HisMedicalController extends BaseController {
    @Autowired
    HisMedicalService hisMedicalService;
    /**
     *@Description 新增更新
     *@Params [model, hisPatientInfo]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-06-21
     *@Time 19:13
     **/
//    @ApiOperation(value = "新增更新")
    @RequestMapping(value = "saveOrUpdate.ahsj", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Message saveOrUpdate(Map<String, Object> model, HisMedical hisMedical) throws Exception{
        return hisMedicalService.saveOrUpdate(hisMedical);
    }



}
