package com.ahsj.hiscore.controller.nurse;


import com.ahsj.hiscore.entity.HisNurse;
import com.ahsj.hiscore.services.HisNurseService;
import com.alibaba.fastjson.JSON;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/api/nurse/")
public class HisNurseController extends BaseController {

    @Autowired
    HisNurseService hisNurseService;


    /**
     *@Description 新增或更新
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-12
     *@Time 22:12
     **/
    //@ApiOperation(value = "新增或更新")
    @RequestMapping(value = "saveOrUpdate.ahsj")
    @ResponseBody
    public Message saveOrUpdate (Map<String, Object> model, HttpServletRequest request, HisNurse hisNurse) throws Exception {
        String Authorization =request.getHeader("Authorization");
        String ContentType =request.getHeader("Content-Type");
        return  hisNurseService.saveOrUpdate(hisNurse,request);
    }

    /**
     *@Description 删除
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-12
     *@Time 22:12
     **/
    //@ApiOperation(value = "删除")
    @RequestMapping(value = "delete.ahsj")
    @ResponseBody
    public Message delete (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisNurseService.delete(ids);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }


    /**
     *@Description list
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-13
     *@Time 0:40
     **/
    //@ApiOperation(value = "分页查询")
    @RequestMapping(value = "list.ahsj")
    @ResponseBody
    public PageBean<HisNurse> list (Map<String, Object> model, HttpServletRequest request, HisNurse hisNurse) throws Exception{
        PageBean<HisNurse> pageBean = new PageBean<HisNurse>();
        pageBean.setParameter(hisNurse);
        return hisNurseService.list(pageBean);
    }

    @RequestMapping("update/index.ahsj")
    ModelAndView UpdateIndex(HisNurse hisNurse,String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nurse/update");
        if(!EmptyUtil.Companion.isNullOrEmpty(hisNurse.getId())){
            modelAndView.addObject("hisNurse",hisNurseService.selectById(hisNurse.getId()));
        }else{
            modelAndView.addObject("hisNurse",hisNurse);
        }
        modelAndView.addObject("title","人员信息添加");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @RequestMapping("list/index.ahsj")
    ModelAndView listIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nurse/list");
        modelAndView.addObject("userName",getUserName());
        modelAndView.addObject("title","医疗信息系统");
        modelAndView.addObject("token",token);
//        modelAndView.addObject("roleName",getRoleName());
        return modelAndView;
    }
    /**
     *@Description list
     *@Params
     *@return
     *@Author 沐旭
     *@Date 2019-07-10
     *@Time 17:10
     **/
    @RequestMapping(value = "getNurse.ahsj", method = {RequestMethod.GET})
    @ResponseBody
    public String  getNuser(String token) throws Exception{
        String ab = JSON.toJSONString(hisNurseService.selectNurse());
        return  ab;
    }

}
