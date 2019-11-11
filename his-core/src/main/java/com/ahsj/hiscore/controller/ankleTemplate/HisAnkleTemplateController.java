package com.ahsj.hiscore.controller.ankleTemplate;


import com.ahsj.hiscore.entity.HisAnkleTemplate;
import com.ahsj.hiscore.services.HisAnkleTemplateService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/api/hisAnkleTemplate/")
public class HisAnkleTemplateController extends BaseController {

    @Autowired
    HisAnkleTemplateService hisAnkleTemplateService;


    /**
     *@Description 获取模板列表名
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/22
     *@Time 15:39
    */
    @RequestMapping(value = "listWithName.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public PageBean<HisAnkleTemplate> listWithName(Map<String, Object> model, HttpServletRequest request, HisAnkleTemplate hisAnkleTemplate) throws Exception{
        PageBean<HisAnkleTemplate> pageBean = new PageBean<HisAnkleTemplate>();
        pageBean.setParameter(hisAnkleTemplate);
        return hisAnkleTemplateService.listWithName(pageBean);


    }
    /**
     *@Description 获取列表对应的护嘱详情
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/22
     *@Time 17:10
    */
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public PageBean<HisAnkleTemplate> list(Map<String, Object> model, HttpServletRequest request, HisAnkleTemplate hisAnkleTemplate,String templateNumber) throws Exception{
        PageBean<HisAnkleTemplate> pageBean = new PageBean<HisAnkleTemplate>();
        pageBean.setParameter(hisAnkleTemplate);
        return hisAnkleTemplateService.list(templateNumber);
    }

    /**
     *@Description 跳转到模板详情页面
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/22
     *@Time 17:02
    */

    @RequestMapping("list/index.ahsj")
    ModelAndView listIndex(String token,String templateNumber,String templateName){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/ankleTemplate/list");
        modelAndView.addObject("title","护嘱模板详情");
        modelAndView.addObject("token",token);
        modelAndView.addObject("templateNumber",templateNumber);
        modelAndView.addObject("templateName",templateName);
        return modelAndView;
    }

    /**
     *@Description 跳转新增或更新页面
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/22
     *@Time 18:01
    */

    @RequestMapping("saveOrUpdate/index.ahsj")
    ModelAndView saveOrUpdate(String token,Long id,String templateName,String templateNumber) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/ankleTemplate/saveOrUpdate");
        modelAndView.addObject("title","护嘱模板新增或更新");
        modelAndView.addObject("token",token);
        HisAnkleTemplate hisAnkleTemplate = new HisAnkleTemplate();
        if (EmptyUtil.Companion.isNullOrEmpty(id)){
            //添加
            if (EmptyUtil.Companion.isNullOrEmpty(templateNumber)){
                //number为空 第一次新增
                hisAnkleTemplate.setOrderNum(1);
                String createdate = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());//当前时间年月日时分秒
                hisAnkleTemplate.setTemplateNumber("TEM" + createdate);//设置编号
                modelAndView.addObject("hisAnkleTemplate",hisAnkleTemplate);
                modelAndView.addObject("nameFlag",false);
//                modelAndView.addObject("hisAnkleTemplateFlag",false);
                modelAndView.addObject("orderNumberFlag",true);

            }else {
                //再次添加
                hisAnkleTemplate.setTemplateNumber(templateNumber);
                hisAnkleTemplate.setTemplateName(templateName);
                modelAndView.addObject("hisAnkleTemplate",hisAnkleTemplate);
                modelAndView.addObject("nameFlag",true);
//                modelAndView.addObject("hisAnkleTemplateFlag",true);
            }
        }else {
            //更新
            modelAndView.addObject("hisAnkleTemplate",hisAnkleTemplateService.selectByPrimaryId(id));
            modelAndView.addObject("orderNumberFlag",true);
            modelAndView.addObject("nameFlag",true);
//            modelAndView.addObject("hisAnkleTemplateFlag",true);

        }
        return modelAndView;
    }

    /**
     *@Description 新增或更新方法
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/22
     *@Time 18:06
    */

    @RequestMapping(value = "saveOrUpdate.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message saveOrUpdate (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="id", required=false) Long id
            , @RequestParam(value="templateNumber", required=false) String templateNumber
            , @RequestParam(value="templateName", required=false) String templateName
            , @RequestParam(value="name", required=false) String name
            , @RequestParam(value="isSkinTest", required=false) Integer isSkinTest
            , @RequestParam(value="specification", required=false) String specification
            , @RequestParam(value="unit", required=false) String unit
            , @RequestParam(value="usages", required=false) String usages
            , @RequestParam(value="dosage", required=false) String dosage
            , @RequestParam(value="remarks", required=false) String remarks
            , @RequestParam(value="intervals", required=false) String intervals
//            , @RequestParam(value="proofreadingNurseId", required=false) Long proofreadingNurseId
//            , @RequestParam(value="approvedNurseId", required=false) Long approvedNurseId
//            , @RequestParam(value="isStop", required=false) Integer isStop
//            , @RequestParam(value="stopDate", required=false) Date stopDate
//            , @RequestParam(value="stopUserId", required=false) Long stopUserId
//            , @RequestParam(value="stopPrfingNurseId", required=false) Long stopPrfingNurseId
//            , @RequestParam(value="stopApdNurseId", required=false) Long stopApdNurseId
            , @RequestParam(value="totalAmount", required=false) Double totalAmount
            , @RequestParam(value="orderNum", required=false) Integer orderNum

    ) throws Exception {
        HisAnkleTemplate hisAnkleTemplate = new HisAnkleTemplate();
        if(null != request.getParameter("id")){
            hisAnkleTemplate.setId(id);
        }
        if(null != request.getParameter("templateNumber")){
            hisAnkleTemplate.setTemplateNumber(templateNumber);
        }
        if(null != request.getParameter("templateName")){
            hisAnkleTemplate.setTemplateName(templateName);
        }
        if(null != request.getParameter("name")){
            hisAnkleTemplate.setName(name);
        }
        if(null != request.getParameter("isSkinTest")){
            hisAnkleTemplate.setIsSkinTest(isSkinTest);
        }
        if(null != request.getParameter("specification")){
            hisAnkleTemplate.setSpecification(specification);
        }
        if(null != request.getParameter("unit")){
            hisAnkleTemplate.setUnit(unit);
        }
        if(null != request.getParameter("usages")){
            hisAnkleTemplate.setUsages(usages);
        }
        if(null != request.getParameter("dosage")){
            hisAnkleTemplate.setDosage(dosage);
        }
        if(null != request.getParameter("remarks")){
            hisAnkleTemplate.setRemarks(remarks);
        }
        if(null != request.getParameter("intervals")){
            hisAnkleTemplate.setIntervals(intervals);
        }
        if(null != request.getParameter("orderNum")){
            hisAnkleTemplate.setOrderNum(orderNum);
        }
        if(null != request.getParameter("totalAmount")){
            hisAnkleTemplate.setTotalAmount(totalAmount);
        }

        return  hisAnkleTemplateService.saveOrUpdate(hisAnkleTemplate);
//        return null;
    }

    /**
     *@Description 获取护嘱模板名到select中
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/23
     *@Time 13:17
    */

    @RequestMapping(value = "getTemplate.ahsj", method = {RequestMethod.GET})
    @ResponseBody
    public String  getTemplate() throws Exception{
        String ab = JSON.toJSONString(hisAnkleTemplateService.selectTemplate());
        return  ab;
    }

    /**
     *@Description deletetemp 删除整个模板
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/13
     *@Time 14:42
    */
    @RequestMapping(value = "deletetemp.ahsj")
    @ResponseBody
    public Message  deletetemp(HttpServletRequest request
            , @RequestParam(value="tempNumber", required=true) String[] tempNumber
    ) throws Exception{
        return hisAnkleTemplateService.deletetemp(tempNumber);
    }

    /**
     *@Description 删除模板里的内容  deletetempdetails
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/13
     *@Time 15:02
    */
    @RequestMapping(value = "deletetempdetails.ahsj")
    @ResponseBody
    public Message  deletetempdetails(HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
    ) throws Exception{
        return hisAnkleTemplateService.deletetempdetails(ids);
    }

}
