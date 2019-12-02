package com.ahsj.hiscore.controller.hisProject;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisRefundProjectPriceController
 * <p>
 * Date:     2019/8/17 18:18
 *
 * @author XJP
 * @create 2019/8/17
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/hisProject/refund/price")
public class HisRefundProjectPriceController extends BaseController {

    @Autowired
    HisRecordProjectService hisRecordProjectService;

    @Autowired
    HisRefundProjectService hisRefundProjectService;

    @Autowired
    HisRefundProjectInfoService hisRefundProjectInfoService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisPatientInfoService hisPatientInfoService;

    @Autowired
    HisRefundProjectDetailService hisRefundProjectDetailService;

    @Autowired
    HisTollRecordService hisTollRecordService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 进入退项目退费页面
     * @Params [token]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 18:59
     **/
    @GetMapping("/index.ahsj")
    public ModelAndView getIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/refundPriceListOne");
        modelAndView.addObject("token", token);
        modelAndView.addObject("userName", getUserName());
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 退项目打印 页面
     * @Params [token]
     * @Author XJP
     * @Date 2019/8/19
     * @Time 14:42
     **/
    @GetMapping("/print.ahsj")
    public ModelAndView print(String token, HisRefundProjectInfo hisRefundProjectInfo) throws Exception {

        List<HisRefundProjectInfo> printlylist = hisRefundProjectInfoService.printlylist(hisRefundProjectInfo);
        HisRefundProjectInfo info = printlylist.get(printlylist.size() - 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/printRefundProjectInfo");
        modelAndView.addObject("token", token);
        modelAndView.addObject("tollRecordNumber", hisRefundProjectInfo.getTollRecordNumber());
        modelAndView.addObject("vouchers", info.getVoucher());
        modelAndView.addObject("sum", info.getRefundSumProce());
        modelAndView.addObject("date", format.format(info.getCreateDate()));
        modelAndView.addObject("pname", info.getPatientName());
        return modelAndView;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明 查询退款明细
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 18:59
     **/
    @PostMapping("/refundProjectList.ahsj")
    public PageBean<HisRecordProject> pageBeanList(HisRecordProject hisRecordProject) throws Exception {
        String tollRecordNumber = hisRecordProject.getTollRecordNumber();
        PageBean<HisRecordProject> pageBean = new PageBean<HisRecordProject>();
        if (!EmptyUtil.Companion.isNullOrEmpty(tollRecordNumber)) {
            //        HIS_HM = "HM"; //就诊记录编号 头部   medicalRecordId
            if (tollRecordNumber.contains(Constants.HIS_HHM) || tollRecordNumber.contains(Constants.HIS_HM)) {
                hisRecordProject.setRecordNumber(hisRecordProject.getTollRecordNumber());
                hisRecordProject.setTollRecordNumber(null);
                pageBean.setParameter(hisRecordProject);
                //    PageBean<HisRecordProject> hisProjectPageBean = hisRecordProjectService.queryAddList(pageBean);
                PageBean<HisRecordProject> hisProjectPageBean = hisRecordProjectService.queryPriceList(pageBean);
                HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByMedicalNumber(tollRecordNumber);
                if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage) && !EmptyUtil.Companion.isNullOrEmpty(pageBean.getData())) {
                    hisProjectPageBean.getData().get(0).setRestDeposit(hisHospitalManage.getRestDeposit());
                    hisProjectPageBean.getData().get(0).setDeposit(hisHospitalManage.getRestDeposit().add(hisProjectPageBean.getData().get(0).getPrices()));
                }
                return hisProjectPageBean;
            }
//         HIS_HTR = "HTR"; //交易流水号 头部
            if (tollRecordNumber.contains(Constants.HIS_HTR)) {
                HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByTollNumber(tollRecordNumber);
                pageBean.setParameter(hisRecordProject);
                //  PageBean<HisRecordProject> hisProjectPageBean = hisRecordProjectService.queryAddList(pageBean);
                PageBean<HisRecordProject> hisProjectPageBean = hisRecordProjectService.queryPriceList(pageBean);
                if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage)) {
                    hisProjectPageBean.getData().get(0).setRestDeposit(hisHospitalManage.getRestDeposit());
                    hisProjectPageBean.getData().get(0).setDeposit(hisHospitalManage.getRestDeposit().add(hisProjectPageBean.getData().get(0).getPrices()));
                }
                return hisProjectPageBean;
            }
//        HIS_HR = "HR";  //住院编号 头部
            if (tollRecordNumber.contains(Constants.HIS_HR)) {
                HisHospitalManage hisHospitalManage = hisHospitalManageService.selectNumber(tollRecordNumber);
                if (!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage)) {
                    hisRecordProject.setRecordNumber(hisHospitalManage.getMedicalNumber());
                    hisRecordProject.setTollRecordNumber(null);
                    pageBean.setParameter(hisRecordProject);
                    //    PageBean<HisRecordProject> hisProjectPageBean = hisRecordProjectService.queryAddList(pageBean);
                    PageBean<HisRecordProject> hisProjectPageBean = hisRecordProjectService.queryPriceList(pageBean);
                    if (!EmptyUtil.Companion.isNullOrEmpty(hisProjectPageBean.getData())) {
                    hisProjectPageBean.getData().get(0).setRestDeposit(hisHospitalManage.getRestDeposit());
                    hisProjectPageBean.getData().get(0).setDeposit(hisHospitalManage.getRestDeposit().add(hisProjectPageBean.getData().get(0).getPrices()));
                    return hisProjectPageBean;
                }}
            }
        }
        pageBean.setParameter(hisRecordProject);
        pageBean = hisRecordProjectService.pageBeanList(pageBean);
        return pageBean;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 18:59
     **/
    @PostMapping("/printlyList.ahsj")
    public List<HisRefundProjectDetail> HisRecordProjectLists(HisRefundProjectDetail hisRefundProjectDetail) throws Exception {
        List<HisRefundProjectDetail> hisRefundProjectDetails = hisRefundProjectDetailService.HisRecordProjectLists(hisRefundProjectDetail);
        return hisRefundProjectDetails;
    }

    /**
     * @return core.message.Message
     * @功能说明 退项目成功明细
     * @Params [hisRefundProjectInfo]
     * @Author XJP
     * @Date 2019/8/19
     * @Time 14:02
     **/
    @PostMapping("/save.ahsj")
    public Message saveHisRefundProjectInfo(HisRefundProjectInfo hisRefundProjectInfo) throws Exception {
        //  return hisRefundProjectService.saveHisRefundProjectInfo(hisRefundProjectInfo);
        return hisTollRecordService.hisProjectSave(hisRefundProjectInfo);  //dingli
    }

}
