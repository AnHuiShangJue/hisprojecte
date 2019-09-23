package com.ahsj.hiscore.controller.hisProject;

import com.ahsj.hiscore.entity.HisRefundProject;
import com.ahsj.hiscore.services.HisRecordProjectService;
import com.ahsj.hiscore.services.HisRefundProjectService;
import core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import core.message.Message;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisRefundProjectAuditController
 * <p>
 * Date:     2019/8/17 13:06
 * 退项目审核
 *
 * @author XJP
 * @create 2019/8/17
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/hisProject/refund/audit")
public class HisRefundProjectAuditController extends BaseController {

    @Autowired
    HisRecordProjectService hisRecordProjectService;

    @Autowired
    HisRefundProjectService hisRefundProjectService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 进入退项目审核页面
     * @Params [token]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 13:10
     **/
    @GetMapping("/index.ahsj")
    public ModelAndView getIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/refundAuditProjectList");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明
     * @Params [token]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 14:40
     **/
    @GetMapping("/query/detil.ahsj")
    public ModelAndView audit(String token, HisRefundProject hisRefundProject) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/refundAuditProjectInfo");
        List<HisRefundProject> refundProjectList = hisRefundProjectService.queryHisRefundProjectVoucher(hisRefundProject);
        HisRefundProject hisRefundProject1 = refundProjectList.get(0);
        modelAndView.addObject("token", token);
        modelAndView.addObject("isReview", hisRefundProject1.getIsReview());
        modelAndView.addObject("voucher", hisRefundProject.getVoucher());

        return modelAndView;
    }
    /**
     *@功能说明 退项目申请审核通过
     *@Params [hisRefundProject]
     *@return sun.plugin2.message.Message
     *@Author XJP
     *@Date 2019/8/17
     *@Time 15:35
    **/
    @PostMapping("/reviewSuccess.ahsj")
    public Message reviewSuccess(HisRefundProject hisRefundProject) throws Exception {
        return hisRefundProjectService.reviewSuccess(hisRefundProject);
    }
    /**
     *@功能说明 退项目申请审核失败
     *@Params [hisRefundProject]
     *@return sun.plugin2.message.Message
     *@Author XJP
     *@Date 2019/8/17
     *@Time 15:35
    **/
    @PostMapping("/reviewFail.ahsj")
    public Message reviewFail(HisRefundProject hisRefundProject) throws Exception {
        return hisRefundProjectService.reviewFail(hisRefundProject);
    }
}
