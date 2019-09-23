package com.ahsj.hiscore.controller.hisDeath;


import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.entity.HisDeath;
import com.ahsj.hiscore.services.HisDeathService;
import core.controller.BaseController;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/14
 * @Time 20:26
 **/
@Controller
@RequestMapping("/api/hisDeath/")
public class hisDeathController extends BaseController {

    @Autowired
    HisDeathService hisDeathService;


    /**
     * className hisDeathController
     *
     * @author dingli
     * @date 2019/9/14 20:26
     */

    /**
     * @Description 根据住院号获取死亡记录
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisDeath
     * @Date 2019/9/14
     * @Time 20:29
     **/
    @RequestMapping(value = "getHisDeath/index.ahsj")
    @ResponseBody
    public HisDeath getHisLeaveHospital(String number) throws Exception {
        HisDeath hisDeath = hisDeathService.selectByNumber(number);
        if (EmptyUtil.Companion.isNullOrEmpty(hisDeath)) {
            return new HisDeath();
        } else {
            return hisDeath;
        }
    }

    /**
     * @Description 增加死亡证明
     * @Params: [hisDeath]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/14
     * @Time 20:35
     **/
    @RequestMapping(value = "addHisDeath/index.ahsj")
    @ResponseBody
    public Message addHisLeaveHospital(HisDeath hisDeath) throws Exception {
        return hisDeathService.insert(hisDeath);
    }

    /**
     * @Description 医生确认
     * @Params: [number, doctor]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/15
     * @Time 9:59
     **/
    @RequestMapping(value = "updateHospital/index.ahsj")
    @ResponseBody
    public Message updateHospital(String number, Integer doctor) throws Exception {
        HisDeath hisDeath = hisDeathService.selectByNumber(number);
        if (doctor == Constants.HIS_DOCTOR_THERE && EmptyUtil.Companion.isNullOrEmpty(hisDeath.getMajorId())) {//主治医师
            hisDeath.setMajorId(Long.valueOf(getId()));
        }
        if (doctor == Constants.HIS_DOCTOR_TWO && EmptyUtil.Companion.isNullOrEmpty(hisDeath.getDoctorId())) {//住院医生
            hisDeath.setDoctorId(Long.valueOf(getId()));
        }
        if (doctor == Constants.HIS_DOCTOR_ONE && EmptyUtil.Companion.isNullOrEmpty(hisDeath.getArchiaterId())) {//主任医生
            hisDeath.setArchiaterId(Long.valueOf(getId()));
        }
        return hisDeathService.update(hisDeath);
    }

    /**
     * @Description 打印死亡记录
     * @Params: [token]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/9/15
     * @Time 10:00
     **/
    @RequestMapping("list/index.ahsj")
    ModelAndView list(String token, String number) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/doctor/deathRecord");
        modelAndView.addObject("title", "死亡记录打印");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number", number);
        return modelAndView;
    }
}
