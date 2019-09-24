package com.ahsj.hiscore.controller.medicalOrder;

import com.ahsj.hiscore.entity.HisMedicalOrderTemplate;
import com.ahsj.hiscore.services.HisMedicalOrderTemplateService;
import com.alibaba.fastjson.JSON;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/api/medicalOrderTemplate")
public class HisMedicalOrderTemplateController extends BaseController {
    @Autowired
    HisMedicalOrderTemplateService hisMedicalOrderTemplateService;

    /**
     *@Description 医嘱模板分页查询
     *@Params [token, isSameDepart]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-28
     *@Time 16:57
    **/
    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalorder/template");
        modelAndView.addObject("title","医嘱模板基本信息");
        modelAndView.addObject("token",token);
        modelAndView.addObject("userId",getId());
        return modelAndView;
    }

    /**
     *@Description 医嘱模板分页查询
     *@Params [model, request, hisMedicalOrderTemplate]
     *@return core.entity.PageBean<HisMedicalOrderTemplate>
     *@Author zhushixiang
     *@Date 2019-07-28
     *@Time 16:58
    **/
    @ResponseBody
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.GET,RequestMethod.POST})
    public PageBean<HisMedicalOrderTemplate> list (Map<String, Object> model, HttpServletRequest request, HisMedicalOrderTemplate hisMedicalOrderTemplate) throws Exception{
        PageBean<HisMedicalOrderTemplate> pageBean = new PageBean<HisMedicalOrderTemplate>();
        pageBean.setParameter(hisMedicalOrderTemplate);
        return hisMedicalOrderTemplateService.list(pageBean);
    }

    /**
     *@Description 新增或更新
     *@Params [model, hisMedicalOrderTemplate]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-06-25
     *@Time 10:20
     **/
    @RequestMapping(value = "saveOrUpdate.ahsj")
    @ResponseBody
    public Message saveOrUpdate (Map<String, Object> model, HisMedicalOrderTemplate hisMedicalOrderTemplate) throws Exception {

        if (hisMedicalOrderTemplate.getRemarks() == "1"){
            hisMedicalOrderTemplate.setRemarks(String.valueOf(getId()));
        }
        return  hisMedicalOrderTemplateService.saveOrUpdate(hisMedicalOrderTemplate);
    }

    /**
     *@Description 新增或更新
     *@Params [hisMedicalOrderTemplate, token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-06-25
     *@Time 11:29
     **/
    @RequestMapping("saveOrUpdate/index.ahsj")
    ModelAndView saveOrUpdateIndex(HisMedicalOrderTemplate hisMedicalOrderTemplate,String token)throws Exception{
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalorder/saveOrUpdateForTemplate");
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderTemplate.getId())){
            modelAndView.addObject("hisMedicalOrderTemplate",hisMedicalOrderTemplateService.selectById(hisMedicalOrderTemplate.getId()));
        }else{
            modelAndView.addObject("hisMedicalOrderTemplate",hisMedicalOrderTemplate);
        }
        modelAndView.addObject("title","医嘱模板管理");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     *@Description 删除
     *@Params [model, request, ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-06-25
     *@Time 11:30
     **/
    @ResponseBody
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    public Message delete (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisMedicalOrderTemplateService.delete(ids);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }

    /**
     *@Description 获取医嘱模板名到select中
     *@Params
     *@return
     *@Author zsx
     *@Date 2019/7/23
     *@Time 13:17
     */

    @RequestMapping(value = "getTemplate.ahsj", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String  getTemplate() throws Exception{
        String medicalOrderTemplate = JSON.toJSONString(hisMedicalOrderTemplateService.selectTemplate());
        return  medicalOrderTemplate;
    }
}
