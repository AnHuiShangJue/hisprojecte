package com.ahsj.hiscore.controller.pharmacy;

import com.ahsj.hiscore.entity.HisApplicationForDrugReturn;
import com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails;
import com.ahsj.hiscore.services.HisApplicationForDrugReturnDetailsService;
import com.ahsj.hiscore.services.HisApplicationForDrugReturnService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/returnDrugDetails/")
public class HisApplicationForDrugReturnDetailsController extends BaseController {
    @Autowired
    HisApplicationForDrugReturnDetailsService hisApplicationForDrugReturnDetailsService;

    @Autowired
    HisApplicationForDrugReturnService hisApplicationForDrugReturnService;

    /**
     * @return core.entity.PageBean<MedicinePurchasingPlan>
     * @Description 药品退回申请详细信息(根据凭证编号)
     * @Params [model, request, medicinePurchasingPlan]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 17:02
     **/
    @RequestMapping(value = "listForVoucher.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public PageBean<HisApplicationForDrugReturnDetails> listForVoucher(Map<String, Object> model, HttpServletRequest request, HisApplicationForDrugReturnDetails medicinePurchasingPlan) throws Exception {
        PageBean<HisApplicationForDrugReturnDetails> pageBean = new PageBean<HisApplicationForDrugReturnDetails>();
        pageBean.setParameter(medicinePurchasingPlan);
        pageBean = hisApplicationForDrugReturnDetailsService.listForVoucher(pageBean);
        return pageBean;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 药品退回申请详细信息（根据凭证编号）前端
     * @Params [token, planNumber]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 17:09
     **/
    @RequestMapping("listForVoucher/index.ahsj")
    ModelAndView listForVoucherIndex(String token, String voucher) {
        HisApplicationForDrugReturn hisApplicationForDrugReturn = hisApplicationForDrugReturnService.selectByVoucher(voucher);
        if (hisApplicationForDrugReturn.getIsReview() == 3) {
            ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/returnDrugNotReview");
            modelAndView.addObject("title", "药品退回申请详细信息");
            modelAndView.addObject("token", token);
            modelAndView.addObject("voucher", voucher);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/returnDrugIsReview");
            modelAndView.addObject("title", "药品退回申请详细信息");
            modelAndView.addObject("token", token);
            modelAndView.addObject("voucher", voucher);
            return modelAndView;
        }
    }

    /**
     * @return core.message.Message
     * @Description 药品退回申请详细信息明细 编辑
     * @Params [model, request, ids, numbers, personInCharge, expectedTime, prices]
     * @Author zhushixiang
     * @Date 2019-07-04
     * @Time 19:02
     **/
    @RequestMapping("/edit/index.ahsj")
    ModelAndView editIndex(String token, String voucher) {
        List<HisApplicationForDrugReturnDetails> hisApplicationForDrugReturnDetailsList = hisApplicationForDrugReturnDetailsService.selectByVoucherForReturnDrug(voucher);
        HisApplicationForDrugReturn hisApplicationForDrugReturn = hisApplicationForDrugReturnService.selectByVoucher(voucher);
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/returnDrugDetailsEdit");
        modelAndView.addObject("title", "药品退回申请详细信息编辑");
        modelAndView.addObject("token", token);
        modelAndView.addObject("voucher", voucher);
        modelAndView.addObject("recordNumber", hisApplicationForDrugReturn.getRecordNumber());
        modelAndView.addObject("hisApplicationForDrugReturnDetailsList", hisApplicationForDrugReturnDetailsList);
        return modelAndView;
    }


    /**
     * @return
     * @Description 药品退回申请详细编辑后提交（更改多条药品退回记录）
     * @Params
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 10:26
     **/
    @RequestMapping(value = "saveForMultiple.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Message saveForMultiple(Map<String, Object> model, HttpRequest request,
                                   @RequestParam(value = "ids", required = true) Long[] ids,
                                   @RequestParam(value = "numbers", required = true) Long[] numbers,
                                   @RequestParam(value = "voucher", required = true) String voucher) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(numbers))
            return MessageUtil.createMessage(false, "请将信息填写完整");
        return hisApplicationForDrugReturnDetailsService.saveForMultiple(ids, numbers, voucher);
    }

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [model, request, ids]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 14:38
     **/
    @ResponseBody
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    public Message delete(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "ids", required = true) Long[] ids
    ) throws Exception {
        if (null != request.getParameter("ids")) {
            return hisApplicationForDrugReturnDetailsService.delete(ids);
        } else return MessageUtil.createMessage(false, "参数异常");
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 退药审核明细
     * @Params [voucher, token]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 17:41
     **/
    @RequestMapping("/review/index.ahsj")
    ModelAndView review(String voucher, String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/returnDrugApplyReview");
        modelAndView.addObject("title", "退药审核");
        modelAndView.addObject("token", token);
        modelAndView.addObject("voucher", voucher);
        return modelAndView;
    }

    /**
     * @return core.message.Message
     * @Description 审核通过
     * @Params [model, request, ids]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 17:56
     **/
    @ResponseBody
    @RequestMapping(value = "reviewSuccess.ahsj", method = {RequestMethod.POST})
    public Message reviewSuccess(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "voucher", required = true) String voucher
    ) throws Exception {
        if (null != request.getParameter("voucher")) {
            return hisApplicationForDrugReturnDetailsService.reviewSuccess(voucher);
        } else return MessageUtil.createMessage(false, "参数异常");
    }

    /**
     * @return core.message.Message
     * @Description 审核驳回
     * @Params [model, request, voucher]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 20:56
     **/
    @ResponseBody
    @RequestMapping(value = "reviewFail.ahsj", method = {RequestMethod.POST})
    public Message reviewFail(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "voucher", required = true) String voucher
    ) throws Exception {
        if (null != request.getParameter("voucher")) {
            return hisApplicationForDrugReturnDetailsService.reviewFail(voucher);
        } else return MessageUtil.createMessage(false, "参数异常");
    }

    /**
     * @Description 根据交易流水号查看所有详情
     * @Param voucher:
     * @Param token:
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     * @Date 2019/7/25
     * @Time 13:15
     **/
    @ResponseBody
    @RequestMapping("getDrugDetails.ahsj")
    public PageBean<HisApplicationForDrugReturnDetails> getByNumber(PageBean<HisApplicationForDrugReturnDetails> pageBean, HisApplicationForDrugReturnDetails hd)
            throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hd.getNumber())) {
            pageBean.setParameter(hd);
            if (hd.getNumber().substring(0, 3).equals("HTR")) {//输入交易流水号
                pageBean = hisApplicationForDrugReturnDetailsService.byNumber(pageBean);
            }
            if (hd.getNumber().substring(0, 2).equals("HM")) {//输入的就诊编号
                hd.setRecordNumber(hd.getNumber());//赋就诊编号
                pageBean = hisApplicationForDrugReturnDetailsService.byByRecordNumber(pageBean);
            }
        } else {
            pageBean.setData(new ArrayList<HisApplicationForDrugReturnDetails>());
        }
        return pageBean;
    }

    /**
     * @Description 跳转退药明细查询
     * @Param token:
     * @Author: dingli
     * @return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/8/1
     * @Time 10:01
     **/
    @RequestMapping("drugReturn/show/index.ahsj")
    public ModelAndView showdrugReturn(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/show_drugReturn");
        modelAndView.addObject("title", "退药明细");
        modelAndView.addObject("token", token);
        modelAndView.addObject("loginName", getUserName());
        return modelAndView;
    }

    /**
     * @Description 根据就诊编号查出退药明细
     * @Param hd:
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     * @Date 2019/8/1
     * @Time 13:29
     **/
    @ResponseBody
    @RequestMapping("showDrugDetails.ahsj")
    public PageBean<HisApplicationForDrugReturnDetails> getRecordNumber(HisApplicationForDrugReturnDetails hd)
            throws Exception {
        PageBean<HisApplicationForDrugReturnDetails> pageBean = new PageBean<HisApplicationForDrugReturnDetails>();
        if (!EmptyUtil.Companion.isNullOrEmpty(hd.getRecordNumber())) {
            pageBean.setParameter(hd);
            pageBean = hisApplicationForDrugReturnDetailsService.ByRecordNumber(pageBean);
            return pageBean;
        } else {
            pageBean.setData(new ArrayList<HisApplicationForDrugReturnDetails>());
            return pageBean;
        }
    }

    /**
     * @Description 打印退药明细
     * @Param recordNumber:
     * @Param token:
     * @Author: dingli
     * @return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/8/9
     * @Time 10:49
     **/
    @RequestMapping("DrugDetailsPrint.ahsj")
    ModelAndView DrugDetailsPrint(String recordNumber, String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/printDrugReturn");
        modelAndView.addObject("title", "打印凭证预览");
        modelAndView.addObject("token", token);
        modelAndView.addObject("recordNumber", recordNumber);
        return modelAndView;
    }

    /**
     * @Description 打印用药明细
     * @Param medicalNumber:
     * @Param token:
     * @Author: dingli
     * @return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/8/9
     * @Time 13:17
     **/
    @RequestMapping("useDetailsPrint.ahsj")
    ModelAndView useDetailsPrint(String medicalNumber, String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/printUseDrug");
        modelAndView.addObject("title", "打印凭证预览");
        modelAndView.addObject("token", token);
        modelAndView.addObject("medicalNumber", medicalNumber);
        return modelAndView;
    }

    /**
     * @Description 跳转用药明细
     * @Param token:
     * @Author: dingli
     * @return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/8/1
     * @Time 14:05
     **/
    @RequestMapping("drugReturn/useDrug/index.ahsj")
    public ModelAndView usedrug(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/useDrug");
        modelAndView.addObject("title", "用药明细");
        modelAndView.addObject("token", token);
        modelAndView.addObject("loginName", getUserName());
        return modelAndView;
    }

    /**
     * @param recordNumber
     * @Description 打印用药明细
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     * @Date 2019/8/19
     * @Time 10:29
     **/
    @ResponseBody
    @RequestMapping("printDrugDetails.ahsj")
    public List<HisApplicationForDrugReturnDetails> selectByRecordNumber(String recordNumber) throws Exception {
        return hisApplicationForDrugReturnDetailsService.selectByRecordNumber(recordNumber);
    }

}
