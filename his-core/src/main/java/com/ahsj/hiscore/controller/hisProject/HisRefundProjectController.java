package com.ahsj.hiscore.controller.hisProject;

import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.HisRecordProjectService;
import com.ahsj.hiscore.services.HisRefundProjectDetailService;
import com.ahsj.hiscore.services.HisRefundProjectService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisRefundProjectController
 * <p>
 * Date:     2019/8/16 15:40
 * 退项目申请
 *
 * @author XJP
 * @create 2019/8/16
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/hisProject/refund")
public class HisRefundProjectController extends BaseController {

    @Autowired
    HisRecordProjectService hisRecordProjectService;

    @Autowired
    HisRefundProjectService hisRefundProjectService;

    @Autowired
    HisRefundProjectDetailService hisRefundProjectDetailService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 首页
     * @Params [token]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 16:51
     **/
    @GetMapping("/index.ahsj")
    public ModelAndView getIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/refundProjectList");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 进入退项目添加页面
     * @Params [token]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 16:51
     **/
    @GetMapping("/addIndex.ahsj")
    public ModelAndView addIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/refundProjectEdit");
        modelAndView.addObject("token", token);
        modelAndView.addObject("userLoginName", getUserName());
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 进入查看退项目明细页面
     * @Params [token, hisRefundProject]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 10:10
     **/
    @GetMapping("/query/detil.ahsj")
    public ModelAndView detil(String token, HisRefundProject hisRefundProject, HisRefundProjectDetail hisRefundProjectDetail) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/refundProjectInfo");
        modelAndView.addObject("token", token);
        modelAndView.addObject("voucher", hisRefundProject.getVoucher());
        modelAndView.addObject("vouchers", hisRefundProjectDetail.getVouchers());
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明
     * @Params [token, hisRefundProjectDetail]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 18:45
     **/
    @GetMapping("/query/detilInfo.ahsj")
    public ModelAndView detilInfo(String token, HisRefundProjectDetail hisRefundProjectDetail) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/refundProjectDetailInfo");
        modelAndView.addObject("token", token);
        modelAndView.addObject("vouchers", hisRefundProjectDetail.getVouchers());
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 根据就诊编号 交易流水号 查看所就诊项目
     * @Params [token]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 17:17
     **/
    @PostMapping("/query/list.ahsj")
    public PageBean<HisRecordProject> queryList(HisRecordProject hisRecordProject) {
        PageBean<HisRecordProject> pageBean = new PageBean<>();
        if (EmptyUtil.Companion.isNullOrEmpty(hisRecordProject.getTollRecordNumber()) && EmptyUtil.Companion.isNullOrEmpty(hisRecordProject.getMedicalRecordId())) {
            return pageBean;
        }
        pageBean.setParameter(hisRecordProject);
        PageBean<HisRecordProject> hisProjectPageBean = hisRecordProjectService.queryAddList(pageBean);
        return hisProjectPageBean;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明 保存退项目申请数据
     * @Params [hisRecordProject]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 18:35
     **/
    @PostMapping("/saveForAppEdit.ahsj")
    public Message saveForAppEdit(HisRefundProject hisRefundProject, @RequestParam("nums") Integer[] nums, @RequestParam("ids") Long[] ids) throws Exception {

        return hisRefundProjectService.saveForAppEdit(hisRefundProject, nums, ids, getUserName());
    }

    /**
     * @return core.message.Message
     * @功能说明 查询申请退项目的列表记录
     * @Params [hisRefundProject, nums, ids]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 19:56
     **/
    @PostMapping("/list.ahsj")
    public PageBean<HisRefundProject> list(HisRefundProject hisRefundProject) throws Exception {
        PageBean<HisRefundProject> pageBean = new PageBean<HisRefundProject>();
        pageBean.setParameter(hisRefundProject);
        pageBean = hisRefundProjectService.list(pageBean);
        return pageBean;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明 查询申请退项目的列表记录明细
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 10:33
     **/
    @PostMapping("/query/projectInfo.ahsj")
    public PageBean<HisRecordProject> projectInfo(HisRecordProject hisRecordProject, String token) throws Exception {
        PageBean<HisRecordProject> pageBean = new PageBean<HisRecordProject>();
        pageBean.setParameter(hisRecordProject);
        pageBean = hisRecordProjectService.projectInfo(pageBean);
        return pageBean;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordProject>
     * @功能说明 批量删除申请退款项目
     * @Params [hisRecordProject, token]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 12:19
     **/
    @PostMapping("/delete.ahsj")
    public Message delete(@RequestParam("vouchers[]") String[] vouchers) throws Exception {
        return hisRefundProjectService.delete(vouchers);
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProjectDetail>
     * @功能说明
     * @Params [hisRefundProjectDetail, token]
     * @Author XJP
     * @Date 2019/8/26
     * @Time 17:19
     **/
    @PostMapping("/query/projectInfoDetail.ahsj")
    public PageBean<HisRefundProjectDetail> projectInfoDetail(HisRefundProjectDetail hisRefundProjectDetail, String token) throws Exception {
        PageBean<HisRefundProjectDetail> pageBean = new PageBean<HisRefundProjectDetail>();
        pageBean.setParameter(hisRefundProjectDetail);
        pageBean = hisRefundProjectDetailService.projectInfoDetail(pageBean);
        return pageBean;
    }

}
