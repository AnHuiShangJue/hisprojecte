package com.ahsj.hiscore.controller.pharmacy;

import com.ahsj.hiscore.entity.HisMedicinePurchasingPlanRecord;
import com.ahsj.hiscore.entity.HisPharmacyDetail;
import com.ahsj.hiscore.services.HisMedicinePurchasingPlanRecordService;
import com.ahsj.hiscore.services.HisPharmacyDetailService;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/planRecord/")
public class HisMedicinePurchasingPlanRecordController extends BaseController {
    @Autowired
    HisMedicinePurchasingPlanRecordService hisMedicinePurchasingPlanRecordService;

    @Autowired
    HisPharmacyDetailService hisPharmacyDetailService;

    
    /**
     *@Description list
     *@Params [model, request, hisMedicinePurchasingPlanRecord]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicinePurchasingPlanRecord>
     *@Author zhushixiang
     *@Date 2019-07-03
     *@Time 11:03
    **/
    @ResponseBody
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.GET,RequestMethod.POST})
    public PageBean<HisMedicinePurchasingPlanRecord> list (Map<String, Object> model, HttpServletRequest request, HisMedicinePurchasingPlanRecord hisMedicinePurchasingPlanRecord) throws Exception{
        PageBean<HisMedicinePurchasingPlanRecord> pageBean = new PageBean<HisMedicinePurchasingPlanRecord>();
        pageBean.setParameter(hisMedicinePurchasingPlanRecord);
        return hisMedicinePurchasingPlanRecordService.list(pageBean);
    }

    /**
     *@Description list前端
     *@Params [token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-03
     *@Time 11:14
    **/
    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/purchasePlanRecordList");
//        modelAndView.addObject("name",getUserName());
        modelAndView.addObject("title","药品采购计划记录信息表");
        modelAndView.addObject("token",token);
//        modelAndView.addObject("roleName",getRoleName());
        return modelAndView;
    }

    /**
     *@Description 药品采购计划记录信息修改(前端)
     *@Params [hisMedicinePurchasingPlanRecord, token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-03
     *@Time 20:13
    **/
    @RequestMapping("/update/index.ahsj")
    ModelAndView UpdateIndex(HisMedicinePurchasingPlanRecord hisMedicinePurchasingPlanRecord,String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/purchasePlanRecordUpdate");
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicinePurchasingPlanRecord.getId())){
            modelAndView.addObject("hisMedicinePurchasingPlanRecord",hisMedicinePurchasingPlanRecordService.selectById(hisMedicinePurchasingPlanRecord.getId()));
        }else{
            modelAndView.addObject("hisMedicinePurchasingPlanRecord",hisMedicinePurchasingPlanRecord);
        }
        modelAndView.addObject("title","药品采购计划记录信息修改");
        modelAndView.addObject("token", token);
        return modelAndView;
    }
    
    /**
     *@Description 药品采购计划信息添加
     *@Params [token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-03
     *@Time 20:15
    **/
    @RequestMapping("/add/index.ahsj")
    ModelAndView addIndex(String token)throws Exception{
        List<HisPharmacyDetail> hisPharmacyDetailList=hisPharmacyDetailService.selectForStock();
        ModelAndView modelAndView=new ModelAndView("backend/hiscore/pharmacy/purchasePlanRecordAdd");
        modelAndView.addObject("title","药品采购计划信息添加");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hisPharmacyDetailList",hisPharmacyDetailList);
        return modelAndView;
    }

    /**
     *@Description 修改
     *@Params [model, hisMedicinePurchasingPlanRecord]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-03
     *@Time 20:13
    **/
    @RequestMapping(value = "saveOrUpdate.ahsj")
    @ResponseBody
    public Message saveOrUpdate (Map<String, Object> model, HisMedicinePurchasingPlanRecord hisMedicinePurchasingPlanRecord) throws Exception {
        return  hisMedicinePurchasingPlanRecordService.saveOrUpdate(hisMedicinePurchasingPlanRecord);
    }

    /**
     *@Description 删除
     *@Params [model, request, ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-03
     *@Time 20:13
    **/
    @ResponseBody
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    public Message delete (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisMedicinePurchasingPlanRecordService.delete(ids);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }
    
    
}
