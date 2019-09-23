package com.ahsj.hiscore.controller.hisLeaveHospital;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.entity.HisLeaveHospital;
import com.ahsj.hiscore.services.HisLeaveHospitalService;
import core.controller.BaseController;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.EmptyUtil;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/14
 * @Time 16:43
 **/
@Controller
@RequestMapping("/api/HisLeaveHospital/")
public class HisLeaveHospitalController extends BaseController {

    @Autowired
    HisLeaveHospitalService hisLeaveHospitalService;


    /**
     * @Description 增加出院信息
     * @Params: [hisLeaveHospital]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/14
     * @Time 17:17
     **/
    @RequestMapping(value = "getHospital/index.ahsj")
    @ResponseBody
    public Message addHisLeaveHospital(HisLeaveHospital hisLeaveHospital) throws Exception {
        return hisLeaveHospitalService.insert(hisLeaveHospital);
    }

    /**
     * @Description 根据住院id获取出院信息
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisLeaveHospital
     * @Date 2019/9/14
     * @Time 17:18
     **/
    @RequestMapping(value = "getHisLeaveHospital/index.ahsj")
    @ResponseBody
    public HisLeaveHospital getHisLeaveHospital(String number) throws Exception {
        HisLeaveHospital hisLeaveHospital = hisLeaveHospitalService.getHisLeaveHospital(number);
        if (EmptyUtil.Companion.isNullOrEmpty(hisLeaveHospital)) {
            return new HisLeaveHospital();
        } else {
            return hisLeaveHospital;
        }
    }

    /**
     * @Description 医师确定
     * @Params: [id, doctor]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisLeaveHospital
     * @Date 2019/9/14
     * @Time 18:27
     **/
    @RequestMapping(value = "updateHospital/index.ahsj")
    @ResponseBody
    public Message updateHospital(String number, Integer doctor) throws Exception {
        HisLeaveHospital hisLeaveHospital = hisLeaveHospitalService.getHisLeaveHospital(number);
        if (doctor == Constants.HIS_DOCTOR_ONE &&EmptyUtil.Companion.isNullOrEmpty(hisLeaveHospital.getMajorId())) {//主治医师
            hisLeaveHospital.setMajorId(Long.valueOf(getId()));
        }
        if (doctor == Constants.HIS_DOCTOR_TWO && EmptyUtil.Companion.isNullOrEmpty(hisLeaveHospital.getDoctorId())) {//住院医生
            hisLeaveHospital.setDoctorId(Long.valueOf(getId()));
        }
        if (doctor == Constants.HIS_DOCTOR_THERE  && EmptyUtil.Companion.isNullOrEmpty(hisLeaveHospital.getInternshipId()) ) {//实习医生
            hisLeaveHospital.setInternshipId(Long.valueOf(getId()));
        }
        return hisLeaveHospitalService.updateByPrimaryKeySelective(hisLeaveHospital);
    }

}
