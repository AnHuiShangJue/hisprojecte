package com.ahsj.hiscore.controller.hisCharge;

import com.ahsj.hiscore.entity.HisCharge;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.entity.HisTollDetails;
import com.ahsj.hiscore.entity.HisTollRecord;
import com.ahsj.hiscore.services.HisChargeService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import com.ahsj.hiscore.services.HisTollDetailsService;
import com.ahsj.hiscore.services.HisTollRecordService;
import com.alibaba.fastjson.JSON;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

/**
 * @Description
 * @Author dingli
 * @Date 2019/7/13
 * @Time 17:02
 **/
@Controller
@RequestMapping("/api/hisCharge/")
public class hisChargeController extends BaseController {
    @Autowired
    HisChargeService hisChargeService;
    @Autowired
    HisTollRecordService hisTollRecordService;
    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisTollDetailsService hisTollDetailsService;

    /**
     * className hisChargeController
     *
     * @author dingli
     * @date 2019/7/13 17:02
     */
    @RequestMapping("list/index.ahsj")
    ModelAndView list(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisCharge/list");
        modelAndView.addObject("title", "收费明细");
        modelAndView.addObject("token", token);
        modelAndView.addObject("loginName", getUserName());
        return modelAndView;
    }

    /**
     * @param token
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/19
     * @Time 19:17
     **/
    @RequestMapping("print/index.ahsj")
    ModelAndView print(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisCharge/print");
        modelAndView.addObject("title", "打印凭证预览");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @param hisCharge Map HisCharge
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/15
     * @Time 10:55
     **/
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.POST})
    public PageBean<HisCharge> list(HisCharge hisCharge) throws Exception {
        PageBean<HisCharge> pageBean = new PageBean<HisCharge>();
        pageBean.setParameter(hisCharge);
        if (hisCharge.getMedicalRecordId() != null && hisCharge.getMedicalRecordId() != "") {
            pageBean = hisChargeService.getAllHisCharge(pageBean);
        } else {
            pageBean = null;
        }
        return pageBean;
    }

    /**
     * @param hisCharge
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/17
     * @Time 9:57
     */
    @RequestMapping(value = "sumbit.ahsj")
    public Message sumbit(String token, HisCharge hisCharge, HisTollRecord hisTollRecord, String medicalRecordId) throws Exception {
        hisCharge.setMedicalRecordId(medicalRecordId);
        return hisTollRecordService.addHisTollRecord(list(hisCharge).getData(), hisTollRecord);//添加交易信息
    }

    /**
     * @param hisCharge
     * @return
     * @Description
     * @Author: dingli
     * @Date 2019/7/17
     * @Time 9:57
     **/
    @RequestMapping(value = "getCharge.ahsj", method = {RequestMethod.GET})
    public String getCharge(HisCharge hisCharge, String medicalRecordId, String token) throws Exception {
        hisCharge.setMedicalRecordId(medicalRecordId);
        PageBean<HisCharge> list = list(hisCharge);
        if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalRecordService.selectByMedicalRecordId(medicalRecordId))) {//就诊号存在
            if (list.getData().size() != 0) {//如果就诊号存在
                return JSON.toJSONString(list.getData().get(0));
            }
            return JSON.toJSONString("-1");
            // list(hisCharge).getData().get(0) ,调用本类中的list的方法拿到list<hisCharge>集合，获取第一个Charge对象（service层将总价赋好）
        } else {//不存在
            return JSON.toJSONString("-2");
        }
    }


}







