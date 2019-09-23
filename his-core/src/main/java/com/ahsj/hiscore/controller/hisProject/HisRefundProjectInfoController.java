package com.ahsj.hiscore.controller.hisProject;

import com.ahsj.hiscore.entity.HisRefundProjectInfo;
import com.ahsj.hiscore.services.HisRecordProjectService;
import com.ahsj.hiscore.services.HisRefundProjectInfoService;
import com.ahsj.hiscore.services.HisRefundProjectService;
import core.controller.BaseController;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisRefundProjectInfoController
 * <p>
 * Date:     2019/8/17 13:17
 *
 * @author XJP
 * @create 2019/8/17
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/hisProject/refund/info")
public class HisRefundProjectInfoController extends BaseController {

    @Autowired
    HisRecordProjectService hisRecordProjectService;

    @Autowired
    HisRefundProjectService hisRefundProjectService;

    @Autowired
    HisRefundProjectInfoService hisRefundProjectInfoService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 进入退项目退费明细页面
     * @Params [token]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 13:10
     **/
    @GetMapping("/index.ahsj")
    public ModelAndView getIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/refundInfoProjectList");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProjectInfo>
     * @功能说明 查看退项目退费明细
     * @Params [hisRefundProjectInfo]
     * @Author XJP
     * @Date 2019/8/20
     * @Time 13:13
     **/
    @PostMapping("/hisRefundProjectInfo/lists.ahsj")
    public PageBean<HisRefundProjectInfo> list(HisRefundProjectInfo hisRefundProjectInfo) throws Exception {
        PageBean<HisRefundProjectInfo> pageBean = new PageBean<>();
        pageBean.setParameter(hisRefundProjectInfo);
        pageBean = hisRefundProjectInfoService.list(pageBean);
        return pageBean;
    }

}
