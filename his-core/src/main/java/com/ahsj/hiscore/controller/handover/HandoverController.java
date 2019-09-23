package com.ahsj.hiscore.controller.handover;

import com.ahsj.hiscore.controller.BaseOAController;
import com.ahsj.hiscore.entity.HisHandover;
import com.ahsj.hiscore.services.HandoverService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

/**
 * @program: hisprojecte
 * @description:交班管理
 * @author: chenzhicai
 * @create: 2019-09-15-00-54
 **/
@Controller
@RequestMapping("/api/handover")
public class HandoverController extends BaseController {

    @Autowired
    HandoverService handoverService;

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
    public Message saveOrUpdate(HisHandover hisHandover) throws Exception {
        return handoverService.saveOrUpdate(hisHandover);
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description list
     * @Params []
     * @Author chenzhicai
     * @Date 2019-09-15
     * @Time 13:38
     **/
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageBean<HisHandover> list(HisHandover hisHandover) throws Exception {
        PageBean<HisHandover> pageBean = new PageBean<HisHandover>();
        pageBean.setParameter(hisHandover);
        return handoverService.list(pageBean);
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
    public HisHandover selectById(Long id) {
        return handoverService.selectById(id);
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
    public ModelAndView index(String token,String number) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/handover/list");
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
    public ModelAndView updateIndex(HisHandover hisHandover,String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/handover/update");
        if (!EmptyUtil.Companion.isNullOrEmpty(hisHandover.getId())) {
            modelAndView.addObject("hisHandover", handoverService.selectById(hisHandover.getId()));
        } else {
            modelAndView.addObject("hisHandover", hisHandover);
        }
        modelAndView.addObject("title", "交班信息添加");
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
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/doctor/handOver");
        modelAndView.addObject("title", "交班记录打印");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hisHandover", handoverService.selectById(new Long(id)));
        return modelAndView;
    }
}

    