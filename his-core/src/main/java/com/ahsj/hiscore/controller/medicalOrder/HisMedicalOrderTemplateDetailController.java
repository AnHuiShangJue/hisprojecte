package com.ahsj.hiscore.controller.medicalOrder;

import com.ahsj.hiscore.entity.HisMedicalOrderTemplate;
import com.ahsj.hiscore.entity.HisMedicalOrderTemplateDetail;
import com.ahsj.hiscore.services.HisMedicalOrderTemplateDetailService;
import com.ahsj.hiscore.services.HisMedicalOrderTemplateService;
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
@RequestMapping("/api/medicalOrderTemplateDetail/")
public class HisMedicalOrderTemplateDetailController extends BaseController {
    @Autowired
    HisMedicalOrderTemplateDetailService hisMedicalOrderTemplateDetailService;

    @Autowired
    HisMedicalOrderTemplateService hisMedicalOrderTemplateService;
    /**
     *@Description 医嘱模板明细分页查询
     *@Params [token, isSameDepart]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-28
     *@Time 16:57
     **/
    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex(String token,@RequestParam(value = "templateNumber",required = true) String templateNumber,
                           @RequestParam(value = "templateName",required = false)String templateName)throws Exception{
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalorder/templateDetail");
        modelAndView.addObject("title","医嘱模板基本信息");
        modelAndView.addObject("templateNumber",templateNumber);
        if(EmptyUtil.Companion.isNullOrEmpty(templateName)) {
            HisMedicalOrderTemplate hisMedicalOrderTemplate = hisMedicalOrderTemplateService.selectByTemplateNumber(templateNumber);
            modelAndView.addObject("templateName",hisMedicalOrderTemplate.getTemplateName());
        }
        else
            modelAndView.addObject("templateName",templateName);
        modelAndView.addObject("token",token);
        return modelAndView;
    }

    /**
     *@Description 医嘱模板明细分页查询
     *@Params [model, request, hisMedicalOrderTemplateDetail]
     *@return core.entity.PageBean<HisMedicalOrderTemplateDetail>
     *@Author zhushixiang
     *@Date 2019-07-28
     *@Time 16:58
     **/
    @ResponseBody
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.GET,RequestMethod.POST})
    public PageBean<HisMedicalOrderTemplateDetail> list (Map<String, Object> model, HttpServletRequest request, HisMedicalOrderTemplateDetail hisMedicalOrderTemplateDetail) throws Exception{
        PageBean<HisMedicalOrderTemplateDetail> pageBean = new PageBean<HisMedicalOrderTemplateDetail>();
        pageBean.setParameter(hisMedicalOrderTemplateDetail);
        return hisMedicalOrderTemplateDetailService.list(pageBean);
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
    public Message saveOrUpdate (Map<String, Object> model, HisMedicalOrderTemplateDetail hisMedicalOrderTemplateDetail) throws Exception {
        return  hisMedicalOrderTemplateDetailService.saveOrUpdate(hisMedicalOrderTemplateDetail);
    }

    /**
     *@Description 新增或更新
     *@Params [hisMedicalOrderTemplate, token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-06-25
     *@Time 11:29
     **/
    @RequestMapping(value = "saveOrUpdate/index.ahsj")
    ModelAndView saveOrUpdateIndex(HisMedicalOrderTemplateDetail hisMedicalOrderTemplateDetail,String token,String remarks)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        if(remarks.equals("1")) {
            modelAndView.setViewName("backend/hiscore/medicalorder/saveOrUpdateForTemplateDetail");
            if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderTemplateDetail.getId())) {
                modelAndView.addObject("hisMedicalOrderTemplateDetail", hisMedicalOrderTemplateDetailService.selectById(hisMedicalOrderTemplateDetail.getId()));
            } else {
                modelAndView.addObject("hisMedicalOrderTemplateDetail", hisMedicalOrderTemplateDetail);
            }
            modelAndView.addObject("title","医嘱模板管理");
        }//用药医嘱
        else if(remarks.equals("2")){
            modelAndView.setViewName("backend/hiscore/medicalorder/creatMedicineOrder");
            modelAndView.addObject("isTemplate",2);
            modelAndView.addObject("templateNumber",hisMedicalOrderTemplateDetail.getTemplateNumber());
        }//项目医嘱
        else if(remarks.equals("3")){
            modelAndView.setViewName("backend/hiscore/medicalorder/creatProjectOrder");
            modelAndView.addObject("isTemplate",3);
            modelAndView.addObject("templateNumber",hisMedicalOrderTemplateDetail.getTemplateNumber());
        }
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
            return  hisMedicalOrderTemplateDetailService.delete(ids);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }

    //新增用药医嘱模板
    @RequestMapping(value = "saveMedicineOrder.ahsj")
    @ResponseBody

    public Message saveMedicineOrder (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="drugsNumbs", required=false) String[] drugsNumbs
            , @RequestParam(value="nums", required=false) Integer[] nums
            , @RequestParam(value="usages", required=false) String[] usages
            , @RequestParam(value="intervals", required=false) String[] intervals
            , @RequestParam(value="templateNumber", required=false) String templateNumber
    ) throws Exception {
        return hisMedicalOrderTemplateDetailService.saveMedicineOrder(drugsNumbs,nums,usages,intervals,templateNumber);
    }
    //新增项目医嘱模板
    @RequestMapping(value = "saveProjectOrder.ahsj")
    @ResponseBody
    public Message saveProjectOrder (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="numbers", required=false) String[] numbers
            , @RequestParam(value="nums", required=false) Integer[] nums
            , @RequestParam(value="templateNumber", required=false) String templateNumber
    ) throws Exception {
        return hisMedicalOrderTemplateDetailService.saveProjectOrder(numbers,nums,templateNumber);
    }
}
