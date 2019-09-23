package com.ahsj.hiscore.controller.HisStageSummary;

import com.ahsj.hiscore.entity.HisStageSummary;
import com.ahsj.hiscore.services.HisStageSummaryService;
import com.ahsj.hiscore.services.HisSuccessionService;
import core.controller.BaseController;
import core.entity.PageBean;
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
 * @description: 阶段小结
 * @author: chenzhicai
 * @create: 2019-09-17-09-14
 **/
@RequestMapping("/api/hisstagesummary/")
@Controller
public class HisStageSummaryController extends BaseController {
    @Autowired
    HisStageSummaryService hisStageSummaryService;

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
    public Message saveOrUpdate(HisStageSummary hisSuccession) throws Exception {
        return hisStageSummaryService.saveOrUpdate(hisSuccession);
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
    public HisStageSummary selectById(Long id) {
        return hisStageSummaryService.selectById(id);
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
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/succession/list");
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
    public ModelAndView updateIndex(HisStageSummary hisSuccession,String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisstagesummary/update");
        if (!EmptyUtil.Companion.isNullOrEmpty(hisSuccession.getId())) {
            modelAndView.addObject("hisSuccession", hisStageSummaryService.selectById(hisSuccession.getId()));
        } else {
            modelAndView.addObject("hisSuccession", hisSuccession);
        }
        modelAndView.addObject("title", "阶段小结添加");
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
        modelAndView.addObject("title", "阶段小结打印");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hisHandover", hisStageSummaryService.selectById(new Long(id)));
        return modelAndView;
    }
}

    