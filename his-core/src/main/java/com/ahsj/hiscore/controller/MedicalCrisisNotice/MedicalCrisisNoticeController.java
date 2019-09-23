package com.ahsj.hiscore.controller.MedicalCrisisNotice;

import com.ahsj.hiscore.entity.MedicalCrisisNotice;
import com.ahsj.hiscore.services.MedicalCrisisNoticeService;
import core.controller.BaseController;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

/**
 * @program: hisprojecte
 * @description:
 * @author: chenzhicai
 * @create: 2019-09-17-10-15
 **/
@Controller
@RequestMapping("/api/medicalcrisisnotice")
public class MedicalCrisisNoticeController extends BaseController {
    @Autowired
    MedicalCrisisNoticeService medicalCrisisNoticeService;
    
    
    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 保存
     * @Params []
     * @Author chenzhicai
     * @Date 2019-09-15
     * @Time 13:38
     **/
    @RequestMapping(value = "saveOrUpdate.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Message saveOrUpdate(MedicalCrisisNotice medicalCrisisNotice) throws Exception {
        return medicalCrisisNoticeService.saveOrUpdate(medicalCrisisNotice);
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 查询单跳数据
     * @Params []
     * @Author chenzhicai
     * @Date 2019-09-15
     * @Time 13:38
     **/
    @RequestMapping(value = "selectById.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public MedicalCrisisNotice selectById(Long id) {
        return medicalCrisisNoticeService.selectById(id);
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 列表页面
     * @Params []
     * @Author chenzhicai
     * @Date 2019-09-15
     * @Time 13:38
     **/
    @RequestMapping(value = "list/index.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView index(String token, String number) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalcrisisnotice/list");
        modelAndView.addObject("title", "病人信息模块");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hospitalNumber", number);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 列表页面
     * @Params []
     * @Author chenzhicai
     * @Date 2019-09-15
     * @Time 13:38
     **/
    @RequestMapping(value = "update/index.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView updateIndex(MedicalCrisisNotice medicalCrisisNotice,String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalcrisisnotice/update");
        if (!EmptyUtil.Companion.isNullOrEmpty(medicalCrisisNotice.getId())) {
            modelAndView.addObject("medicalCrisisNotice", medicalCrisisNoticeService.selectById(medicalCrisisNotice.getId()));
        } else {
            modelAndView.addObject("medicalCrisisNotice", medicalCrisisNotice);
        }
        modelAndView.addObject("title", "病危通知书添加");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     *@Description 打印
     *@Params [token, number]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author chenzhicai
     *@Date 2019-09-15
     *@Time 20:34
     **/
    @RequestMapping(value = "print/index.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView print(String token,String number,Long id) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/doctor/succession");
        modelAndView.addObject("title", "病危通知书打印");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hisHandover", medicalCrisisNoticeService.selectById(new Long(id)));
        return modelAndView;
    }
}

    