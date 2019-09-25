package com.ahsj.hiscore.controller.hisProject;

import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.*;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
     * @功能说明  退项目打印 页面
     * @Params [token]
     * @Author XJP
     * @Date 2019/8/19
     * @Time 14:42
     **/
    @GetMapping("/print.ahsj")
    public ModelAndView print(String token ,HisRefundProjectInfo hisRefundProjectInfo) throws Exception {

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
    public PageBean<HisRecordProject>pageBeanList(HisRecordProject hisRecordProject) throws Exception {
        PageBean<HisRecordProject> pageBean = new PageBean<HisRecordProject>();
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
        List<HisRefundProjectDetail> hisRefundProjectDetails =hisRefundProjectDetailService.HisRecordProjectLists(hisRefundProjectDetail);
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
        hisRefundProjectInfo.toString();
        return hisTollRecordService.hisProjectSave(hisRefundProjectInfo);  //dingli
    }

}
