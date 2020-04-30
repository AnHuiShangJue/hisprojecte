package com.ahsj.hiscore.controller.consumables;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisRecordConsumablesService;
import com.ahsj.hiscore.services.HisRefundConsumablesService;
import com.ahsj.hiscore.services.HisTollRecordService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import java.util.List;

/**
 * @ClassName : HisRefundConsumablesController
 * @Description :
 * @Author : XJP
 * @Date: 2020-04-28 09:28
 */

@RestController
@RequestMapping("/api/hisRefundConsumables")
public class HisRefundConsumablesController extends BaseController {


    @Autowired
    HisRefundConsumablesService hisRefundConsumablesService;

    @Autowired
    HisRecordConsumablesService hisRecordConsumablesService;

    @Autowired
    HisTollRecordService hisTollRecordService;


    @Autowired
    HisHospitalManageService hisHospitalManageService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description
     * @MethodName exitConsumablesIndex
     * @Params [token]
     * @Author XJP
     * @Date 2020/4/28
     * @Time 10:00
     **/
    @RequestMapping(value = "/index.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    ModelAndView exitConsumablesIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/refundApply");
        modelAndView.addObject("title", "");
        modelAndView.addObject("token", token);
        return modelAndView;
    }
    /**
     *@Description
     *@MethodName exitConsumablesIndex
     *@Params [token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author XJP
     *@Date 2020/4/29
     *@Time 15:48
    **/
    @RequestMapping(value = "/audit/index.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    ModelAndView auditonsumablesIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/refundAuditComsumables");
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    @GetMapping("/return/index.ahsj")
    public ModelAndView getIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/refundComsumablesListOne");
        modelAndView.addObject("token", token);
        modelAndView.addObject("userName", getUserName());
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 进入退项目添加页面
     * @MethodName addIndex
     * @Params [token]
     * @Author XJP
     * @Date 2020/4/28
     * @Time 10:43
     **/
    @GetMapping("/addIndex.ahsj")
    public ModelAndView addIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/refundConsumablesUpdate");
        modelAndView.addObject("token", token);
        modelAndView.addObject("userLoginName", getUserName());
        return modelAndView;
    }




    /**
     *@Description 进入查看退耗材明细页面
     *@MethodName detil
     *@Params [token, hisRefundProject, hisRefundProjectDetail]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author XJP
     *@Date 2020/4/29
     *@Time 14:38
    **/
    @GetMapping("/query/detil.ahsj")
    public ModelAndView detil(String token, HisRefundConsumables hisRefundConsumables, HisRefundProjectDetail hisRefundProjectDetail) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/refundConsumablesInfo");
        modelAndView.addObject("token", token);
        modelAndView.addObject("voucher", hisRefundConsumables.getVoucher());
        modelAndView.addObject("vouchers", hisRefundProjectDetail.getVouchers());
        return modelAndView;
    }



    /**
     *@Description
     *@MethodName audit
     *@Params [token, hisRefundConsumables]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author XJP
     *@Date 2020/4/29
     *@Time 16:11
    **/
    @GetMapping("/audit/query/detil.ahsj")
    public ModelAndView audit(String token, HisRefundConsumables hisRefundConsumables) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/refundAuditConsumablesInfo");
      List<HisRefundConsumables> hisRefundConsumablesList =   hisRefundConsumablesService.queryHisHisRefundConsumablesVoucher(hisRefundConsumables);
//        HisRefundProject hisRefundProject1 = refundProjectList.get(0);
        modelAndView.addObject("token", token);
        modelAndView.addObject("isReview", hisRefundConsumablesList.get(0).getIsReview());
        modelAndView.addObject("voucher", hisRefundConsumables.getVoucher());
        return modelAndView;
    }





    /**
     *@Description 退耗材申请审核通过
     *@MethodName reviewSuccess
     *@Params [hisRefundProject]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/29
     *@Time 16:27
    **/
    @PostMapping("/audit/reviewSuccess.ahsj")
    public Message reviewSuccess(HisRefundConsumables hisRefundConsumables) throws Exception {
        return hisRefundConsumablesService.reviewSuccess(hisRefundConsumables);
    }


    /**
     *@Description 退耗材申请审核失败
     *@MethodName reviewFail
     *@Params [hisRefundProject]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/29
     *@Time 16:27
    **/
    @PostMapping("/audit/reviewFail.ahsj")
    public Message reviewFail(HisRefundConsumables hisRefundConsumables) throws Exception {
        return hisRefundConsumablesService.reviewFail(hisRefundConsumables);
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProject>
     * @Description 查询申请退耗材的列表记录
     * @MethodName list
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2020/4/28
     * @Time 10:33
     **/
    @PostMapping("/list.ahsj")
    public PageBean<HisRefundConsumables> list(HisRefundConsumables hisRefundConsumables) throws Exception {
        PageBean<HisRefundConsumables> pageBean = new PageBean<HisRefundConsumables>();
        pageBean.setParameter(hisRefundConsumables);
        pageBean = hisRefundConsumablesService.list(pageBean);
        return pageBean;
    }


    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordProject>
     * @Description 根据就诊编号 交易流水号 住院编号 查看所就诊项目
     * @MethodName queryList
     * @Params [hisRecordProject]
     * @Author XJP
     * @Date 2020/4/28
     * @Time 15:16
     **/
    @PostMapping("/query/list.ahsj")
    public PageBean<HisRecordConsumables> queryList(HisRecordConsumables hisRecordConsumables) throws Exception {
        String tollRecordNumber = hisRecordConsumables.getTollRecordNumber();
        PageBean<HisRecordConsumables> pageBean = new PageBean<>();
        if (EmptyUtil.Companion.isNullOrEmpty(hisRecordConsumables.getTollRecordNumber()) && EmptyUtil.Companion.isNullOrEmpty(hisRecordConsumables.getMedicalRecordNumber())) {
            return pageBean;
        }
        //        HIS_HM = "HM"; //就诊记录编号 头部   medicalRecordId
        if (tollRecordNumber.contains(Constants.HIS_HHM) || tollRecordNumber.contains(Constants.HIS_HM)) {
            hisRecordConsumables.setMedicalRecordNumber(hisRecordConsumables.getTollRecordNumber());
            hisRecordConsumables.setTollRecordNumber(null);
            pageBean.setParameter(hisRecordConsumables);
            PageBean<HisRecordConsumables> hisProjectPageBean = hisRecordConsumablesService.queryAddList(pageBean);
            return hisProjectPageBean;
        }
        //         HIS_HTR = "HTR"; //交易流水号 头部
        if (tollRecordNumber.contains(Constants.HIS_HTR)) {
            HisTollRecord hisTollRecord = hisTollRecordService.getHisTollRecord(hisRecordConsumables.getTollRecordNumber());
            hisRecordConsumables.setMedicalRecordNumber(hisTollRecord.getMedicalRecordId());
            pageBean.setParameter(hisRecordConsumables);
            PageBean<HisRecordConsumables> hisProjectPageBean = hisRecordConsumablesService.queryAddList(pageBean);
            return hisProjectPageBean;
        }
        //        HIS_HR = "HR";  //住院编号 头部
        if (tollRecordNumber.contains(Constants.HIS_HR)) {
            HisHospitalManage hisHospitalManageByNumber = hisHospitalManageService.getHisHospitalManageByNumber(hisRecordConsumables.getTollRecordNumber());
            hisRecordConsumables.setMedicalRecordNumber(hisHospitalManageByNumber.getMedicalNumber());
            pageBean.setParameter(hisRecordConsumables);
            PageBean<HisRecordConsumables> hisProjectPageBean = hisRecordConsumablesService.queryAddList(pageBean);
            return hisProjectPageBean;
        }
        pageBean.setParameter(hisRecordConsumables);
        PageBean<HisRecordConsumables> hisProjectPageBean = hisRecordConsumablesService.queryAddList(pageBean);
        return hisProjectPageBean;
    }


    /**
     * @return core.message.Message
     * @Description
     * @MethodName saveForAppEdit
     * @Params [hisRefundProject, nums, ids]
     * @Author XJP
     * @Date 2020/4/29
     * @Time 11:01
     **/
    @PostMapping("/saveForAppEdit.ahsj")
    public Message saveForAppEdit(HisRefundConsumables hisRefundConsumables, @RequestParam("nums") Integer[] nums, @RequestParam("ids") Long[] ids) throws Exception {
        String tollRecordNumber = hisRefundConsumables.getTollRecordNumber();
        //        HIS_HM = "HM"; //就诊记录编号 头部   medicalRecordId
        if (tollRecordNumber.contains(Constants.HIS_HM) || tollRecordNumber.contains(Constants.HIS_HHM)) {
            hisRefundConsumables.setRecordNumber(hisRefundConsumables.getTollRecordNumber());
            return hisRefundConsumablesService.saveForAppEdit(hisRefundConsumables, nums, ids, getUserName());
        }
        //         HIS_HTR = "HTR"; //交易流水号 头部
        if (tollRecordNumber.contains(Constants.HIS_HTR)) {
            HisTollRecord hisTollRecord = hisTollRecordService.getHisTollRecord(hisRefundConsumables.getTollRecordNumber());
            hisRefundConsumables.setRecordNumber(hisTollRecord.getMedicalRecordId());
            return hisRefundConsumablesService.saveForAppEdit(hisRefundConsumables, nums, ids, getUserName());
        }
        //        HIS_HR = "HR";  //住院编号 头部
        if (tollRecordNumber.contains(Constants.HIS_HR)) {
            HisHospitalManage hisHospitalManageByNumber = hisHospitalManageService.selectByHisHospitalManageByNumber(hisRefundConsumables.getTollRecordNumber());
            hisRefundConsumables.setRecordNumber(hisHospitalManageByNumber.getMedicalNumber());
            return hisRefundConsumablesService.saveForAppEdit(hisRefundConsumables, nums, ids, getUserName());
        }
        return hisRefundConsumablesService.saveForAppEdit(hisRefundConsumables, nums, ids, getUserName());
    }




    /**
     *@Description 查询耗材退项目的列表记录明细
     *@MethodName projectInfo
     *@Params [hisRecordProject, token]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordProject>
     *@Author XJP
     *@Date 2020/4/29
     *@Time 14:43
    **/
    @PostMapping("/query/projectInfo.ahsj")
    public PageBean<HisRecordConsumables> consumablesInfo(HisRecordConsumables hisRecordConsumables, String token) throws Exception {
        PageBean<HisRecordConsumables> pageBean = new PageBean<HisRecordConsumables>();
        pageBean.setParameter(hisRecordConsumables);
        pageBean = hisRecordConsumablesService.consumablesInfo(pageBean);
        return pageBean;
    }




    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundProject>
     * @功能说明 查询退款明细
     * @Params [hisRefundProject]
     * @Author XJP
     * @Date 2019/8/17
     * @Time 18:59
     **/
    @PostMapping("/return/refundProjectList.ahsj")
    public PageBean<HisRecordConsumables> pageBeanList(HisRecordConsumables hisRecordConsumables) throws Exception {
        String tollRecordNumber = hisRecordConsumables.getTollRecordNumber();
        PageBean<HisRecordConsumables> pageBean = new PageBean<HisRecordConsumables>();

        if (!EmptyUtil.Companion.isNullOrEmpty(tollRecordNumber)) {
            //        HIS_HM = "HM"; //就诊记录编号 头部   medicalRecordId
            if (tollRecordNumber.contains(Constants.HIS_HHM) || tollRecordNumber.contains(Constants.HIS_HM)) {
                hisRecordConsumables.setMedicalRecordNumber(hisRecordConsumables.getTollRecordNumber());
                hisRecordConsumables.setTollRecordNumber(null);
                pageBean.setParameter(hisRecordConsumables);
                PageBean<HisRecordConsumables> hisProjectPageBean = hisRecordConsumablesService.pageBeanList(pageBean);
                return hisProjectPageBean;
            }
            //         HIS_HTR = "HTR"; //交易流水号 头部
            if (tollRecordNumber.contains(Constants.HIS_HTR)) {
                HisTollRecord hisTollRecord = hisTollRecordService.getHisTollRecord(hisRecordConsumables.getTollRecordNumber());
                hisRecordConsumables.setMedicalRecordNumber(hisTollRecord.getMedicalRecordId());
                pageBean.setParameter(hisRecordConsumables);
                PageBean<HisRecordConsumables> hisProjectPageBean = hisRecordConsumablesService.pageBeanList(pageBean);
                return hisProjectPageBean;
            }
            //        HIS_HR = "HR";  //住院编号 头部
            if (tollRecordNumber.contains(Constants.HIS_HR)) {
                HisHospitalManage hisHospitalManageByNumber = hisHospitalManageService.getHisHospitalManageByNumber(hisRecordConsumables.getTollRecordNumber());
                hisRecordConsumables.setMedicalRecordNumber(hisHospitalManageByNumber.getMedicalNumber());
                pageBean.setParameter(hisRecordConsumables);
                PageBean<HisRecordConsumables> hisProjectPageBean = hisRecordConsumablesService.pageBeanList(pageBean);
                return hisProjectPageBean;
            }
        }
 /*       pageBean.setParameter(hisRecordConsumables);
        pageBean = hisRecordConsumablesService.pageBeanList(pageBean);*/
        return null;
    }



    /**
     *@Description 退耗材成功明细
     *@MethodName saveHisRefundProjectInfo
     *@Params [hisRefundProjectInfo]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/30
     *@Time 10:23
    **/
    @PostMapping("/return/save.ahsj")
    public Message saveHisRefundConsumablesInfo(HisRefundConsumables hisRefundConsumables) throws Exception {
        return hisTollRecordService.saveHisRefundConsumablesInfo(hisRefundConsumables);
    }

}
