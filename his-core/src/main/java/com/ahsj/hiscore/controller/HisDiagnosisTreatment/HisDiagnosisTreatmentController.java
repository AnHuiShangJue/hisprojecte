package com.ahsj.hiscore.controller.HisDiagnosisTreatment;

import com.ahsj.hiscore.entity.HisDiagnosisTreatment;
import com.ahsj.hiscore.entity.HisRescue;
import com.ahsj.hiscore.services.HisDiagnosisTreatmentService;
import core.controller.BaseController;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisDiagnosisTreatmentController
 * <p>
 * Date:     2019/9/16 16:17
 *
 * @author XJP
 * @create 2019/9/16
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/hisdiagnosistreatment")
public class HisDiagnosisTreatmentController extends BaseController {

    @Autowired
    HisDiagnosisTreatmentService hisDiagnosisTreatmentService;

    @GetMapping("/add/index.ahsj")
    public ModelAndView index(String token, HisDiagnosisTreatment hisDiagnosisTreatment) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisdiagnosistreatment/addOrUpdate");//templates/backend/hiscore/hisdiagnosistreatment/addOrUpdate.html
        if (EmptyUtil.Companion.isNullOrEmpty(hisDiagnosisTreatment.getId())) {
            modelAndView.addObject("hisDiagnosisTreatment", hisDiagnosisTreatment);
            modelAndView.addObject("token", token);
            return modelAndView;
        } else {
            hisDiagnosisTreatment = hisDiagnosisTreatmentService.selectByPrimaryKey(hisDiagnosisTreatment.getId());
            modelAndView.addObject("hisDiagnosisTreatment", hisDiagnosisTreatment);
            modelAndView.addObject("token", token);
            return modelAndView;
        }
    }

    /**
     * @return core.message.Message
     * @功能说明 增添和修改有创治疗信息
     * @Params [hisDiagnosisTreatment]
     * @Author XJP
     * @Date 2019/9/17
     * @Time 10:20
     **/
    @PostMapping("/addOrUpdateHisDiagnosisTreatment.ahsj")
    public Message addOrUpdateHisDiagnosisTreatment(HisDiagnosisTreatment hisDiagnosisTreatment) throws Exception {
        return hisDiagnosisTreatmentService.addOrUpdateHisDiagnosisTreatment(hisDiagnosisTreatment);
    }

    /**
     *@功能说明 医生有创治疗签字
     *@Params [hisRescue]
     *@return core.message.Message
     *@Author XJP
     *@Date 2019/9/16
     *@Time 9:26
     **/
    @PostMapping("/signature/HisDiagnosisTreatment.ahsj")
    public Message signature(HisDiagnosisTreatment hisDiagnosisTreatment) throws Exception {
        return hisDiagnosisTreatmentService.signature(hisDiagnosisTreatment,getId());
    }

}
