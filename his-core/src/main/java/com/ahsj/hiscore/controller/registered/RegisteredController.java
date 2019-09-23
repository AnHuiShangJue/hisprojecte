package com.ahsj.hiscore.controller.registered;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.controller.BaseOAController;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisDailyRecordService;
import com.ahsj.hiscore.services.HisRegisteredService;
import com.ahsj.hiscore.services.HisRegisteredTypeService;
import com.ahsj.hiscore.services.impl.HisDailyRecordServiceImpl;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/registered")
public class RegisteredController extends BaseOAController {
    Logger logger = LoggerFactory.getLogger(RegisteredController.class.getSimpleName());
    @Autowired
    HisRegisteredService hisRegisteredService;

    @Autowired
    HisRegisteredTypeService hisRegisteredTypeService;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    ITranslateService iTranslateService;

    /**
     * @return
     * @Description 患者挂号
     * @Params
     * @Author shenbuqing
     * @Date 2019/6/13
     * @Time 20:23
     **/
    @RequestMapping(value = "/registered.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Message registered(HttpServletRequest request,
                              @RequestParam(value = "patientName", required = true) String patientName,
                              @RequestParam(value = "idcard", required = true) String idcard,
                              @RequestParam(value = "orgId", required = true) String orgId,
                              @RequestParam(value = "registeredCategory", required = true) int registeredCategory,
                              @RequestParam(value = "remark", required = true) String remark,
                              @RequestParam(value = "money", required = true) BigDecimal money,
                              @RequestParam(value = "paidAmount", required = false) BigDecimal paidAmount,
                              @RequestParam(value = "retrieveAmount", required = false) BigDecimal retrieveAmount,
                              @RequestParam(value = "payType", required = true) Integer payType
    ) throws Exception {
        HisRegistered hisRegistered = new HisRegistered();
        HisPatientInfo hisPatientInfo = new HisPatientInfo();
        HisRegisteredpay hisRegisteredpay = new HisRegisteredpay();
        if (null != request.getParameter("patientName")) {
            hisRegistered.setPatientName(patientName);
            hisPatientInfo.setName(patientName);
        }
        if (null != request.getParameter("idcard")) {
            hisPatientInfo.setIdcard(idcard);
        }
        if (null != request.getParameter("registeredCategory")) {
            hisRegistered.setRegisteredCategory(registeredCategory);
        }
        if (null != request.getParameter("remark")) {
            hisRegistered.setRemark(remark);
        }
        if (null != request.getParameter("orgId")) {
            hisRegistered.setDepartmentId(new Long(orgId));
        }
        if (null != request.getParameter("money")) {
            hisRegistered.setMoney(money);
            hisRegisteredpay.setAmountReceivable(money);
        }
        if (null != request.getParameter("paidAmount")) {
            hisRegisteredpay.setPaidAmount(paidAmount);
        }
        if (null != request.getParameter("retrieveAmount")) {
            hisRegisteredpay.setRetrieveAmount(retrieveAmount);
        }
        if (null != request.getParameter("payType")) {
            hisRegisteredpay.setPayType(payType);
        }
        return hisRegisteredService.registered(hisRegistered, hisPatientInfo, hisRegisteredpay);
    }

    /**
     * @return
     * @Description 是否付费
     * @Params
     * @Author shenbuqing
     * @Date 2019/6/14
     * @Time 14:40
     **/
    @RequestMapping(value = "/ispay.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Message isPay(HisRegistered hisRegistered) throws Exception {
        return hisRegisteredService.isPay(hisRegistered);
    }

    /**
     * @return
     * @Description 付费操作
     * @Params
     * @Author shenbuqing
     * @Date 2019/6/14
     * @Time 14:41
     **/
    @RequestMapping(value = "/payOperation.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Message payOperation(HisRegisteredpay hisRegisteredpay) throws Exception {
        return hisRegisteredService.payOperation(hisRegisteredpay);
    }

    /**
     * @return
     * @Description 作废操作
     * @Params
     * @Author shenbuqing
     * @Date 2019/6/14
     * @Time 15:40
     **/
    @RequestMapping(value = "/isObsolete.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Message isObsolete(Long[] id) throws Exception {
        return hisRegisteredService.isObsolete(id);
    }

    /**
     * @return
     * @Description 打印挂号单操作
     * @Params
     * @Author shenbuqing
     * @Date 2019/6/15
     * @Time 15:02
     **/
    @RequestMapping(value = "/print.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Message print(Long id) throws Exception {
        return hisRegisteredService.print(id);
    }


    /**
     * @return
     * @Description 挂号患者列表查询
     * @Params
     * @Author shenbuqing
     * @Date 2019/6/21
     * @Time 15:33
     **/
    @RequestMapping(value = "/list.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public PageBean<HisRegistered> list(Map<String, Object> model, HttpServletRequest request, HisRegistered hisRegistered) throws Exception {
        PageBean<HisRegistered> pageBean = new PageBean<HisRegistered>();
        pageBean.setParameter(hisRegistered);
        return hisRegisteredService.list(pageBean);
    }


    /**
     * @return
     * @Description 返回前端列表
     * @Params
     * @Author shenbuqing
     * @Date 2019/6/21
     * @Time 17:44
     **/
    @RequestMapping("/list/index.ahsj")
    ModelAndView UserIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/registered/list");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("userName", getUserName());
        modelAndView.addObject("registerdCount", hisRegisteredService.countForToday(getId()));
        return modelAndView;
    }


    /**
     * @return
     * @Description 前端的新增页面
     * @Params
     * @Author shenbuqing
     * @Date 2019/6/21
     * @Time 17:45
     **/
    @RequestMapping("/update/index.ahsj")
    ModelAndView UpdateIndex(HisRegistered hisRegistered) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/registered/update");
        if (!EmptyUtil.Companion.isNullOrEmpty(hisRegistered.getId())) {
            modelAndView.addObject("hisRegistered", hisRegisteredService.selectById(hisRegistered.getId()));
        } else {
            modelAndView.addObject("hisRegistered", hisRegistered);
        }
        modelAndView.addObject("registTypeList", hisRegisteredTypeService.listByAll());
        modelAndView.addObject("title", "挂号信息添加");
        modelAndView.addObject("token", hisRegistered.getToken());
        return modelAndView;
    }

    @RequestMapping("/detail/index.ahsj")
    ModelAndView detailIndex(HisRegistered hisRegistered) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/registered/detail");
        if (!EmptyUtil.Companion.isNullOrEmpty(hisRegistered.getId())) {
            hisRegistered = hisRegisteredService.detailInit(hisRegistered.getId());
            modelAndView.addObject("hisRegistered", hisRegistered);
        } else {
            modelAndView.addObject("hisRegistered", hisRegistered);
        }
        modelAndView.addObject("title", "挂号单号: " + hisRegistered.getNumber() + " 详细信息");
        modelAndView.addObject("token", hisRegistered.getToken());
        return modelAndView;
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 打印初始化页面
     * @Params [hisRegistered]
     * @Author chenzhicai
     * @Date 2019/7/3
     * @Time 5:12 PM
     **/
    @RequestMapping("/print/index.ahsj")
    ModelAndView printIndex(HisRegistered hisRegistered) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/registered/print");
        if (!EmptyUtil.Companion.isNullOrEmpty(hisRegistered.getId())) {
            hisRegistered = hisRegisteredService.detailInit(hisRegistered.getId());
            SysCodeDetail detail = new SysCodeDetail();
            Translate translate = new Translate();
            translate.setTranslateId(hisRegistered.getDepartmentId());
            translate.setTranslateType(Constants.TRANSLATE_SYS_ORGANIZATION);
            translate.setTranslateColumn("name");
            Translate clo1 = iTranslateService.queryTranslateClo(translate);

            hisRegistered.setDepartmentName(clo1.getTranslateKhmer());
            hisRegistered.setDepartmentNameEnlish(clo1.getTranslateChina());


            detail.setCode(hisRegistered.getRegisteredCategory().toString());
            SysCodeDetail sysCodeDetail = iCodeService.SysCodeDetail(detail);
            Translate translate1 = new Translate();
            translate1.setTranslateId(sysCodeDetail.getId().longValue());

            translate1.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
            translate1.setTranslateColumn("value");
            Translate clo = iTranslateService.queryTranslateClo(translate1);
            hisRegistered.setCategoryName(clo.getTranslateKhmer());
            hisRegistered.setCategoryNameEnlish(clo.getTranslateChina());


            modelAndView.addObject("hisregistered", hisRegistered);

        } else {
            hisRegistered = hisRegisteredService.detailInit(hisRegistered.getId());
            SysCodeDetail detail = new SysCodeDetail();
            Translate translate = new Translate();
            translate.setTranslateId(hisRegistered.getDepartmentId());
            translate.setTranslateType(Constants.TRANSLATE_SYS_ORGANIZATION);
            translate.setTranslateColumn("name");
            Translate clo1 = iTranslateService.queryTranslateClo(translate);
            hisRegistered.setDepartmentName(clo1.getTranslateKhmer());


            detail.setCode(hisRegistered.getRegisteredCategory().toString());
            SysCodeDetail sysCodeDetail = iCodeService.SysCodeDetail(detail);
            Translate translate1 = new Translate();
            translate1.setTranslateId(sysCodeDetail.getId().longValue());
            translate1.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
            translate1.setTranslateColumn("value");
            Translate clo = iTranslateService.queryTranslateClo(translate1);
            hisRegistered.setCategoryName(clo.getTranslateKhmer());
            modelAndView.addObject("hisregistered", hisRegistered);
        }
        modelAndView.addObject("token", hisRegistered.getToken());
        return modelAndView;
    }


}
