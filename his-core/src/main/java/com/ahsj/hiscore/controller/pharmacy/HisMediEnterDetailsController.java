package com.ahsj.hiscore.controller.pharmacy;

import com.ahsj.hiscore.entity.HisMediEnterDetails;
import com.ahsj.hiscore.entity.MedicinePurchasingPlan;
import com.ahsj.hiscore.services.HisMediEnterDetailsService;
import com.ahsj.hiscore.services.HisPharmacyDetailService;
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
@RequestMapping("/api/medienter/")
public class HisMediEnterDetailsController extends BaseController {
    @Autowired
    HisMediEnterDetailsService hisMediEnterDetailsService;

    @Autowired
    HisPharmacyDetailService hisPharmacyDetailService;

    @Autowired
    MedicinePurchasingPlanService medicinePurchasingPlanService;

    /**
     *@Description 药品入库(前)
     *@Params [model, hisMediEnterDetails]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-06-26
     *@Time 11:55
    **/
    @RequestMapping(value = "saveOrUpdate.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public void unitMdeiEnter (Map<String, Object> model, HisMediEnterDetails hisMediEnterDetails) throws Exception {
        hisMediEnterDetailsService.unitMdeiEnter(hisMediEnterDetails);
    }


    /**
     *@Description 药品入库确认（前端）
     *@Params [token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-06-26
     *@Time 11:57
    **/
    @RequestMapping("/mediEnterSure/index.ahsj")
    ModelAndView mediEnterSure(String token,String planNumber){
        List<MedicinePurchasingPlan> medicinePurchasingPlanList=medicinePurchasingPlanService.selectByPlanNumberforList(planNumber);
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/mediEnterSure");
        modelAndView.addObject("title","药品入库确认");
        modelAndView.addObject("token",token);
        modelAndView.addObject("planNumber",planNumber);
        modelAndView.addObject("medicinePurchasingPlanList",medicinePurchasingPlanList);
        return modelAndView;
    }

    /**
     *@Description 药品入库（后版本）
     *@Params [model, request, ids, numbers, prices, planNumber]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-05
     *@Time 14:17
    **/
    @RequestMapping(value = "mediEnter.ahsj" ,method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Message mediEnter(Map<String, Object>model, HttpRequest request,
                               @RequestParam(value = "ids",required = true) Long[] ids,
                               @RequestParam(value = "numbers",required = true )Long[] numbers,
                               @RequestParam(value = "prices",required = true)Double[] prices,
                               @RequestParam(value = "planNumber",required = true)String planNumber,
                             @RequestParam(value = "validityPeriods",required = true)String[] validityPeriods) throws Exception{

        if(EmptyUtil.Companion.isNullOrEmpty(numbers) || EmptyUtil.Companion.isNullOrEmpty(prices) || EmptyUtil.Companion.isNullOrEmpty(validityPeriods))
            return MessageUtil.createMessage(false,"请将药品入库信息填写完整");
        //标记变量 若有信息未填写 置为false
        boolean flag = true;
        for (int i = 0; i <ids.length ; i++) {
            if(EmptyUtil.Companion.isNullOrEmpty(numbers[i]) || EmptyUtil.Companion.isNullOrEmpty(prices[i]) || EmptyUtil.Companion.isNullOrEmpty(validityPeriods[i])) {
                flag = false;
                break;
            }
        }
        if(!flag) return MessageUtil.createMessage(false,"请将药品入库信息填写完整");
        return hisMediEnterDetailsService.mediEnter(ids,numbers,prices,planNumber,validityPeriods);
    }


    /**
     *@Description list查询（出库时的左表格）
     *@Params [model, request, hisPharmacyDetail]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisPharmacyDetail>
     *@Author zhushixiang
     *@Date 2019-07-06
     *@Time 16:16
    **/
    @ResponseBody
    @RequestMapping(value = "exitList.ahsj", method = {RequestMethod.GET,RequestMethod.POST})
    public PageBean<HisMediEnterDetails> list (Map<String, Object> model, HttpServletRequest request, HisMediEnterDetails hisMediEnterDetails) throws Exception{
        PageBean<HisMediEnterDetails> pageBean = new PageBean<HisMediEnterDetails>();
        pageBean.setParameter(hisMediEnterDetails);
        pageBean=hisMediEnterDetailsService.list(pageBean);
        return pageBean;
    }

    /**
     *@Description 通过药品id查询入库表中所有数据以pageBean分页对象传回查到的数据
     *@Params [model, request, hisMediEnterDetails]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMediEnterDetails>
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 10:23
    **/
    @ResponseBody
    @RequestMapping(value = "listByPharmacyId.ahsj", method = {RequestMethod.GET,RequestMethod.POST})
    public PageBean<HisMediEnterDetails> listByPharmacyId (Map<String, Object> model, HttpServletRequest request, HisMediEnterDetails hisMediEnterDetails) throws Exception{
        HisMediEnterDetails h=hisMediEnterDetails;
        PageBean<HisMediEnterDetails> pageBean = new PageBean<HisMediEnterDetails>();
        pageBean.setParameter(hisMediEnterDetails);
        pageBean=hisMediEnterDetailsService.listByPharmacyId(pageBean);
        return pageBean;
    }


}
