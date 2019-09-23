package com.ahsj.hiscore.controller.pharmacy;

import com.ahsj.hiscore.entity.HisDrugLossReporting;
import com.ahsj.hiscore.entity.HisMediEnterDetails;
import com.ahsj.hiscore.entity.HisPharmacyDetail;
import com.ahsj.hiscore.services.HisDrugLossReportingService;
import com.ahsj.hiscore.services.HisMediEnterDetailsService;
import com.ahsj.hiscore.services.HisPharmacyDetailService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/api/drugLoss/")
public class HisDrugLossReportingController extends BaseController {

    @Autowired
    HisDrugLossReportingService hisDrugLossReportingService;

    @Autowired
    HisMediEnterDetailsService hisMediEnterDetailsService;

    @Autowired
    HisPharmacyDetailService hisPharmacyDetailService;
    /**
     *@Description 药品报损后台逻辑
     *@Params [model, request, ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 11:20
     **/
    @ResponseBody
    @RequestMapping(value = "lossDrug.ahsj", method = {RequestMethod.POST})
    public Message lossDrug (Map<String, Object> model, HttpServletRequest request,HisDrugLossReporting hisDrugLossReporting) throws Exception {
        return hisDrugLossReportingService.lossDrug(hisDrugLossReporting);
    }

    /**
     *@Description 输入报损详细信息界面
     *@Params [token, request, pharmacyId]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 11:33
     **/
    @RequestMapping("/lossDrug/index.ahsj")
    ModelAndView drugLossReporting(String token, HttpServletRequest request,Long id){
        HisMediEnterDetails hisMediEnterDetails=hisMediEnterDetailsService.selectByPrimaryKey(id);
        HisPharmacyDetail hisPharmacyDetail=hisPharmacyDetailService.selectByPharmacyId(hisMediEnterDetails.getPharmacyId());
        HisDrugLossReporting hisDrugLossReporting=new HisDrugLossReporting();
        hisDrugLossReporting.setMediEnterId(id);
        hisDrugLossReporting.setPharmacyId(hisPharmacyDetail.getMedicineId());
        hisDrugLossReporting.setDrugsNumb(hisPharmacyDetail.getDrugsNumb());
        hisDrugLossReporting.setDrugsName(hisPharmacyDetail.getDrugsName());
        hisDrugLossReporting.setDrugsSpec(hisPharmacyDetail.getDrugsSpec());
        if(!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getEnterPrice()))
            hisDrugLossReporting.setEnterPrice(hisPharmacyDetail.getEnterPrice().doubleValue());
        hisDrugLossReporting.setValidityPeriod(hisMediEnterDetails.getValidityPeriod());
        hisDrugLossReporting.setManufacturers(hisPharmacyDetail.getPlaceorigin());
        if(!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getLargeUnit()))
            hisDrugLossReporting.setLargeUnit(hisPharmacyDetail.getLargeUnit());
        if(!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getSmallUnit()))
            hisDrugLossReporting.setSmallUnit(hisPharmacyDetail.getSmallUnit());
        if(!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getSalePrice()))
            hisDrugLossReporting.setSalePrice(hisPharmacyDetail.getSalePrice().doubleValue());
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/drugLossReportingEdit");
        modelAndView.addObject("title","药品报损详细信息输入");
        modelAndView.addObject("hisDrugLossReporting",hisDrugLossReporting);
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     *@Description 药品报损分页查询
     *@Params [token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 14:43
    **/
    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/lossDrugList");
        modelAndView.addObject("title","药品报损清单");
        modelAndView.addObject("token",token);
        return modelAndView;
    }

    /**
     *@Description 药品报损分页查询
     *@Params [model, request, hisPatientInfo]
     *@return core.entity.PageBean<HisPatientInfo>
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 14:52
    **/
    @ResponseBody
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.GET,RequestMethod.POST})
    public PageBean<HisDrugLossReporting> list (Map<String, Object> model, HttpServletRequest request, HisDrugLossReporting hisDrugLossReporting) throws Exception{
        PageBean<HisDrugLossReporting> pageBean = new PageBean<HisDrugLossReporting>();
        pageBean.setParameter(hisDrugLossReporting);
        return hisDrugLossReportingService.list(pageBean);
    }

    /**
     *@Description 查看已经审核过的药品报损信息
     *@Params [token, id]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 17:33
    **/
    @RequestMapping("/visitDetail/index.ahsj")
    ModelAndView visitDetail(String token,Long id){
        HisDrugLossReporting hisDrugLossReporting=hisDrugLossReportingService.selectById(id);
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/visitIsReviewLossDrug");
        modelAndView.addObject("title","药品报损详细信息查看");
        modelAndView.addObject("hisDrugLossReporting",hisDrugLossReporting);
        modelAndView.addObject("token",token);
        return modelAndView;
    }

    /**
     *@Description 药品报损审核
     *@Params [token, id]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 21:03
    **/
    @RequestMapping("/review/index.ahsj")
    ModelAndView review(String token,Long id){
        HisDrugLossReporting hisDrugLossReporting=hisDrugLossReportingService.selectById(id);
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/visitNotReviewLossDrug");
        modelAndView.addObject("title","药品报损审核");
        modelAndView.addObject("hisDrugLossReporting",hisDrugLossReporting);
        modelAndView.addObject("token",token);
        return modelAndView;
    }

    /**
     *@Description 药品报损审核通过
     *@Params [model, request, hisDrugLossReporting]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 21:11
    **/

    @ResponseBody
    @RequestMapping(value = "reviewSuccess.ahsj")
    public Message reviewSuccess (Map<String, Object> model, HttpServletRequest request,HisDrugLossReporting hisDrugLossReporting) throws Exception {
        return hisDrugLossReportingService.reviewSuccess(hisDrugLossReporting);
    }

    @ResponseBody
    @RequestMapping(value = "reviewFail.ahsj")
    public Message reviewFail (Map<String, Object> model, HttpServletRequest request,HisDrugLossReporting hisDrugLossReporting) throws Exception {
        return hisDrugLossReportingService.reviewFail(hisDrugLossReporting);
    }
}
