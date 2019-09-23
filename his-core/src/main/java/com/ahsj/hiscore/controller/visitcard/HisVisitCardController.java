package com.ahsj.hiscore.controller.visitcard;

import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.*;
import core.controller.BaseController;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/api/visitCard/")
public class HisVisitCardController extends BaseController {
    @Autowired
    HisVisitCardService hisVisitCardService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisRecordProjectService hisRecordProjectService;

    @Autowired
    HisMedicationDetailsService hisMedicationDetailsService;

    @Autowired
    HisTollRecordService hisTollRecordService;

    /**
     * @return core.message.Message
     * @Description 新增就诊卡/办卡
     * @Params [model, request, id, drugsNumb, drugsName, drugsAlias, drugsSpec, prescription, mentalMedicine, largeUnit, smallUnit, conversionRate, disable, level, medicalInsuranceSign, placeorigin, baseMedicine, narcoticDrugs, remarks, enterPrice]
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 11:36
     **/
    @RequestMapping(value = "save.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Message save(Map<String, Object> model, HttpServletRequest request, HisVisitCard hisVisitCard) throws Exception {
        return hisVisitCardService.save(hisVisitCard);
    }

    /**
     * @return
     * @Description 根据就诊卡编号拉取病人信息
     * @Params
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 15:37
     **/
    @RequestMapping(value = "getPatientInfoByNumber.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public HisPatientInfo getPatientInfoByNumber(Map<String, Object> model, HttpServletRequest request, String number) throws Exception {
        return hisVisitCardService.getPatientInfoByNumber(number);
    }

    /**
     * @return
     * @Description 刷卡（目前仅针对门诊,住院）  （未对接实际机器，仅有对数据库的操作）
     * @Params
     * @Author zhushixiang
     * @Date 2019-09-10
     * @Time 16:16
     **/
    //visitCardNumber:就诊卡编号
    @RequestMapping("/swipe.ahsj")
    @ResponseBody
    public Message swipe(Map<String, Object> model, HttpServletRequest request,@RequestParam(value = "visitCardNumber",required = true)String visitCardNumber ,
                         @RequestParam(value = "commonNumber",required = false)String commonNumber)throws Exception{
        if(EmptyUtil.Companion.isNullOrEmpty(visitCardNumber))
            return MessageUtil.createMessage(false,"就诊卡编号不能为空");
        return hisVisitCardService.swipe(visitCardNumber,commonNumber);
    }

    /**
     * @Description 退就诊卡
     * @Params: [hisVisitCard]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/10
     * @Time 17:38
     **/
    @RequestMapping("/returnIdCard.ahsj")
    @ResponseBody
    public Message returnIdCard(HisVisitCard hisVisitCard) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisVisitCard.getBalance())) {
            return hisVisitCardService.returnIdCard(hisVisitCard);
        } else {
            return MessageUtil.createMessage(false, "数据异常!");
        }
    }


    /**
     * @Description 充值就诊卡
     * @Params: [balance, id]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/10
     * @Time 15:38
     **/
    @RequestMapping("/voucherCenter.ahsj")
    @ResponseBody
    public Message voucherCenter(HisVisitCard hisVisitCard) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisVisitCard.getBalance())) {
            return hisVisitCardService.voucherCenter(hisVisitCard);
        } else {
            return MessageUtil.createMessage(false, "数据异常!");
        }
    }

    /**
     * @Description 根据就诊卡号查出病人信息
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisVisitCard
     * @Date 2019/9/10
     * @Time 15:17
     **/
    @PostMapping("/getHisVisitCard.ahsj")
    @ResponseBody
    public HisVisitCard getHisVisitCard(String number) throws Exception {
        return hisVisitCardService.selectByNumbers(number) == null ? new HisVisitCard() : hisVisitCardService.selectByNumbers(number);
    }

    /**
     * @Description 打印充值
     * @Params: [number, patientName, token]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/9/11
     * @Time 13:31
     **/
    @RequestMapping("printDrug/index.ahsj")
    ModelAndView printDrug(String number, String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/histoll/list_voucherCenter_print");
        modelAndView.addObject("title", "打印凭证预览");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number", number);
        return modelAndView;
    }

    /**
     * @Description 打印就诊卡的充值，退卡
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisTollRecord
     * @Date 2019/9/11
     * @Time 13:55
     **/
    @PostMapping("printShow/index.ahsj")
    @ResponseBody
    public HisTollRecord print(String number) throws Exception {
        return hisTollRecordService.ListByNumber(number);
    }

    @RequestMapping("printReturn/index.ahsj")
    ModelAndView printReturn(String number, String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/histoll/list_returnIdCard_print");
        modelAndView.addObject("title", "打印凭证预览");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number", number);
        return modelAndView;
    }

    /**
     *@Description 通用刷卡模块
     *@Params [number, token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-09-13
     *@Time 13:19
    **/
    @RequestMapping("commonSwipe/index.ahsj")
    ModelAndView commonSwipe(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/visitcard/commonSwipe");
        modelAndView.addObject("title", "通用刷卡模块");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

}
