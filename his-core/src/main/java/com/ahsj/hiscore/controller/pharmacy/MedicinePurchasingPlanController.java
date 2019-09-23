package com.ahsj.hiscore.controller.pharmacy;

import com.ahsj.hiscore.entity.HisMedicinePurchasingPlanRecord;
import com.ahsj.hiscore.entity.MedicinePurchasingPlan;
import com.ahsj.hiscore.services.HisMedicinePurchasingPlanRecordService;
import com.ahsj.hiscore.services.MedicinePurchasingPlanService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.http.HttpRequest;
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
@RequestMapping("/api/purchasingplan/")
public class MedicinePurchasingPlanController extends BaseController {
    @Autowired
    MedicinePurchasingPlanService medicinePurchasingPlanService;

    @Autowired
    HisMedicinePurchasingPlanRecordService hisMedicinePurchasingPlanRecordService;
    /**
     *@Description 药品采购计划
     *@Params [model, medicinePurchasingPlan]
     *@return sun.plugin2.message.Message
     *@Author zhushixiang
     *@Date 2019-07-02
     *@Time 16:22
    **/
    @RequestMapping(value = "save.ahsj" ,method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Message saveOrUpdate(Map<String, Object>model, HttpRequest request,
                                @RequestParam(value = "ids",required = true) Long[] ids,
                                @RequestParam(value = "numbers",required = true )Long[] numbers,
                                @RequestParam(value = "personInCharge", required = true)String personInCharge,
                                @RequestParam(value = "expectedTime",required = true)String expectedTime,
                                @RequestParam(value = "prices",required = true)Double[] prices) throws Exception{
        if(EmptyUtil.Companion.isNullOrEmpty(personInCharge))
            return MessageUtil.createMessage(false,"请输入负责人姓名");
        if(EmptyUtil.Companion.isNullOrEmpty(expectedTime))
            return MessageUtil.createMessage(false,"请设置预定采购日期");
        if(EmptyUtil.Companion.isNullOrEmpty(numbers) && EmptyUtil.Companion.isNullOrEmpty(prices))
            return medicinePurchasingPlanService.saveOrUpdate(ids,numbers,personInCharge,expectedTime,prices);
        if(EmptyUtil.Companion.isNullOrEmpty(numbers) || EmptyUtil.Companion.isNullOrEmpty(prices))
            return MessageUtil.createMessage(false,"请将药品的单价和数量信息填写完整");
        return medicinePurchasingPlanService.saveOrUpdate(ids,numbers,personInCharge,expectedTime,prices);
    }

    /**
     *@Description 药品采购计划详细信息信息（前端）
     *@Params [token, planNumber]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 13:07
    **/
    @RequestMapping("details/index.ahsj")
    ModelAndView listIndex(String token,String planNumber){
        HisMedicinePurchasingPlanRecord hisMedicinePurchasingPlanRecord=hisMedicinePurchasingPlanRecordService.selectByPlanNumber(planNumber);
        if(hisMedicinePurchasingPlanRecord.getCompletion()==2) {
            ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/purchasePlanDetails");
            modelAndView.addObject("title", "药品采购计划详细信息表");
            modelAndView.addObject("token", token);
            modelAndView.addObject("planNumber", planNumber);
            return modelAndView;
        }
        else {
            ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/purchasePlanDetailsCompletion");
            modelAndView.addObject("title", "药品采购计划详细信息表");
            modelAndView.addObject("token", token);
            modelAndView.addObject("planNumber", planNumber);
            return modelAndView;
        }
    }

    /**
     *@Description 药品采购计划详细信息
     *@Params [model, request, medicinePurchasingPlan]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.MedicinePurchasingPlan>
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 15:43
    **/
    @RequestMapping(value = "details.ahsj", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public PageBean<MedicinePurchasingPlan> details (Map<String, Object> model, HttpServletRequest request, MedicinePurchasingPlan medicinePurchasingPlan) throws Exception{
        PageBean<MedicinePurchasingPlan> pageBean = new PageBean<MedicinePurchasingPlan>();
        pageBean.setParameter(medicinePurchasingPlan);
        pageBean = medicinePurchasingPlanService.details(pageBean);
        return pageBean;
    }

    /**
     *@Description 删除
     *@Params [model, request, ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 19:01
    **/
    @ResponseBody
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    public Message delete (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  medicinePurchasingPlanService.delete(ids);
        }else
            return MessageUtil.createMessage(false,"参数异常");
    }


    /**
     *@Description 药品采购计划明细 编辑
     *@Params [model, request, ids, numbers, personInCharge, expectedTime, prices]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 19:02
    **/
    @RequestMapping("/edit/index.ahsj")
    ModelAndView editIndex(String token,String planNumber){
        List<MedicinePurchasingPlan> medicinePurchasingPlanList=medicinePurchasingPlanService.selectByPlanNumber(planNumber);
        ModelAndView modelAndView=new ModelAndView("backend/hiscore/pharmacy/purchasePlanDetailsEdit");
        modelAndView.addObject("title","药品采购计划详细信息编辑");
        modelAndView.addObject("token", token);
        modelAndView.addObject("planNumber",planNumber);
        modelAndView.addObject("medicinePurchasingPlanList",medicinePurchasingPlanList);
        return modelAndView;
    }


    /**
     *@Description 药品采购计划明细编辑后提交
     *@Params [model, request, ids, numbers, personInCharge, expectedTime, prices]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-05
     *@Time 9:22
    **/
    @RequestMapping(value = "saveDetails.ahsj" ,method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Message saveDetails(Map<String, Object>model, HttpRequest request,
                                @RequestParam(value = "ids",required = true) Long[] ids,
                                @RequestParam(value = "numbers",required = true )Long[] numbers,
                                @RequestParam(value = "prices",required = true)Double[] prices,
                               @RequestParam(value = "planNumber",required = true)String planNumber) throws Exception{
        if(EmptyUtil.Companion.isNullOrEmpty(numbers) || EmptyUtil.Companion.isNullOrEmpty(prices))
            return MessageUtil.createMessage(false,"请将药品的单价和数量信息填写完整");
        return medicinePurchasingPlanService.saveDetails(ids,numbers,prices,planNumber);
    }

}
