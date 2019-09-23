package com.ahsj.baseuserinfo.controller;

import com.ahsj.baseuserinfo.entity.Operate;
import com.ahsj.baseuserinfo.services.OperateService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

@Controller
@RequestMapping("/api/operate/")
public class OperateController extends BaseController {


    @Autowired
    private OperateService operateService;


    @RequestMapping("list.ahsj")
    @ResponseBody
    public PageBean<Operate> list(Operate sysOperate) throws Exception {
        PageBean<Operate> pageEntity = new PageBean<Operate>();
        pageEntity.setParameter(sysOperate);
        return operateService.list(pageEntity);
    }


    @RequestMapping("update/index.ahsj")
    public ModelAndView updateInit(Long id,String token) throws Exception {
        ModelAndView modelAndView  = new ModelAndView();
        if(!EmptyUtil.Companion.isNullOrEmpty(id)){
            modelAndView.addObject("sysOperate",operateService.updateInit(id));
        }else{
            modelAndView.addObject("sysOperate",new Operate());
        }
        modelAndView.setViewName("backend/sys/operate/update");
        modelAndView.addObject("token",token);
        return modelAndView;
    }


    @RequestMapping("saveOrUpdate.ahsj")
    @ResponseBody
    public Message saveOrUpdate(Operate sysOperate) throws Exception {
        return operateService.saveOrUpdate(sysOperate);
    }


    @RequestMapping("delete.ahsj")
    @ResponseBody
    public Message delete(Long[] id) throws Exception {
        return operateService.delete(id);
    }

    @RequestMapping("/index/index.ahsj")
    ModelAndView codeIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/sys/operate/list");
        modelAndView.addObject("userName",getUserName());
        modelAndView.addObject("title","智慧园区系统");
        modelAndView.addObject("token",token);
        return modelAndView;
    }
}
