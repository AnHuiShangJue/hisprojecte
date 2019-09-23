package com.ahsj.hiscore.controller.pharmacy;

import com.ahsj.hiscore.entity.HisApplicationForDrugReturn;
import com.ahsj.hiscore.entity.HisPatientInfo;
import com.ahsj.hiscore.services.HisApplicationForDrugReturnDetailsService;
import com.ahsj.hiscore.services.HisApplicationForDrugReturnService;
import com.ahsj.hiscore.services.HisPatientService;
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
import java.util.Map;

@Controller
@RequestMapping("/api/returnDrug/")
public class HisApplicationForDrugReturnController {
    @Autowired
    HisApplicationForDrugReturnService hisApplicationForDrugReturnService;

    @Autowired
    HisApplicationForDrugReturnDetailsService hisApplicationForDrugReturnDetailsService;

    @Autowired
    HisPatientService hisPatientService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 药品报损申请查看（当前可查看所有报损申请）
     * @Params [token, isSameDepart]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 12:05
     **/
    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/returnDrugApplyList");
        modelAndView.addObject("title", "退药申请");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 为药品审核的list查询 只查询出未审核的申请
     * @Params [token]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 17:30
     **/
    @ResponseBody
    @RequestMapping(value = "listForReview.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<HisApplicationForDrugReturn> listForReview(Map<String, Object> model, HttpServletRequest request, HisApplicationForDrugReturn hisApplicationForDrugReturn) throws Exception {
        PageBean<HisApplicationForDrugReturn> pharmacyDetailPageBean = new PageBean<HisApplicationForDrugReturn>();
        pharmacyDetailPageBean.setParameter(hisApplicationForDrugReturn);
        return hisApplicationForDrugReturnService.listForReview(pharmacyDetailPageBean);
    }

    /**
     * @return core.entity.PageBean<HisPharmacyDetail>
     * @Description 药品报损表的分页查询
     * @Params [model, request, hisPharmacyDetail]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 12:05
     **/
    @ResponseBody
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<HisApplicationForDrugReturn> list(Map<String, Object> model, HttpServletRequest request, HisApplicationForDrugReturn hisApplicationForDrugReturn) throws Exception {
        PageBean<HisApplicationForDrugReturn> pharmacyDetailPageBean = new PageBean<HisApplicationForDrugReturn>();
        pharmacyDetailPageBean.setParameter(hisApplicationForDrugReturn);
        return hisApplicationForDrugReturnService.list(pharmacyDetailPageBean);
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 增加新的退回申请
     * @Params [hisMedicineInfo, token]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 13:58
     **/
    @RequestMapping("/addApply/index.ahsj")
    ModelAndView addApply(HisApplicationForDrugReturn hisApplicationForDrugReturn, String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/returnDrugApplyAdd");
        modelAndView.addObject("hisApplicationForDrugReturn", hisApplicationForDrugReturn);
        modelAndView.addObject("title", "药品退回申请");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return core.message.Message
     * @Description 药品退回申请及其详细信息保存
     * @Params [model, request, ids, numbers, personInCharge, expectedTime, prices]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 15:08
     **/
    @RequestMapping(value = "saveForApply.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Message saveForApply(Map<String, Object> model, HttpRequest request,
                                @RequestParam(value = "ids", required = true) Long[] ids,
                                @RequestParam(value = "numbers", required = true) Long[] numbers,
                                @RequestParam(value = "patientName", required = true) String patientName,
                                @RequestParam(value = "phonenumber", required = true) Long phonenumber,
                                @RequestParam(value = "idcard", required = true) String idcard,
                                @RequestParam(value = "reason", required = true) String reason) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(patientName))
            return MessageUtil.createMessage(false, "请输入退药人姓名");
        if (EmptyUtil.Companion.isNullOrEmpty(idcard))
            return MessageUtil.createMessage(false, "请输入退药人身份证号码");
        if (EmptyUtil.Companion.isNullOrEmpty(numbers))
            return MessageUtil.createMessage(false, "请将信息填写完整");
        boolean flag = true;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] <= 0) {
                flag = false;
                break;
            }
        }
        if (!flag) {
            return MessageUtil.createMessage(false, "退药的数量必须大于0");
        }
        return hisApplicationForDrugReturnService.saveForApply(ids, numbers, patientName, phonenumber, idcard, reason);
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 药品退回申请基本信息修改
     * @Params [hisMedicinePurchasingPlanRecord, token]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 15:51
     **/
    @RequestMapping("/update/index.ahsj")
    ModelAndView UpdateIndex(HisApplicationForDrugReturn hisApplicationForDrugReturn, String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/returnDrugApplyUpdate");
        if (!EmptyUtil.Companion.isNullOrEmpty(hisApplicationForDrugReturn.getId())) {
            modelAndView.addObject("hisApplicationForDrugReturn", hisApplicationForDrugReturnService.selectById(hisApplicationForDrugReturn.getId()));
        } else {
            modelAndView.addObject("hisApplicationForDrugReturn", hisApplicationForDrugReturn);
        }
        modelAndView.addObject("title", "修改退药申请信息");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return core.message.Message
     * @Description 药品退回申请基本信息修改
     * @Params [model, hisMedicinePurchasingPlanRecord]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 16:14
     **/
    @RequestMapping(value = "update.ahsj")
    @ResponseBody
    public Message Update(Map<String, Object> model, HisApplicationForDrugReturn hisApplicationForDrugReturn) throws Exception {
        return hisApplicationForDrugReturnService.Update(hisApplicationForDrugReturn);
    }

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [model, request, ids]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 16:27
     **/
    @ResponseBody
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    public Message delete(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "ids", required = true) Long[] ids
    ) throws Exception {
        if (null != request.getParameter("ids")) {
            return hisApplicationForDrugReturnService.delete(ids);
        } else return MessageUtil.createMessage(false, "参数异常");
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 退药审核界面
     * @Params [hisApplicationForDrugReturn, token]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 15:54
     **/
    @RequestMapping("/listForReview/index.ahsj")
    ModelAndView review(String voucher, String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/returnDrugApplyListForReview");
        modelAndView.addObject("title", "退药审核");
        modelAndView.addObject("token", token);
        modelAndView.addObject("voucher", voucher);
        return modelAndView;
    }

    /**
     * @Description 根据交易流水号查询退药信息
     * @Param number:
     * @Param token:
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     * @Date 2019/7/25
     * @Time 10:29
     **/
    @ResponseBody
    @RequestMapping("getDrugDetails.ahsj")
    public HisApplicationForDrugReturn getDrugDetails(String number) throws Exception {
        if (number.substring(0, 3).equals("HTR") && !EmptyUtil.Companion.isNullOrEmpty
                (hisApplicationForDrugReturnService.getNumber(number))) {//交易流水号
            return hisApplicationForDrugReturnService.getNumber(number);
        }
        if (number.substring(0, 2).equals("HM") && !EmptyUtil.Companion.isNullOrEmpty
                (hisApplicationForDrugReturnService.selectByRecordNumber(number))) {//就诊编号
            return hisApplicationForDrugReturnService.selectByRecordNumber(number);
        }
        return new HisApplicationForDrugReturn();
    }

    /**
     * @Description 根据就诊编号查询退药信息
     * @Param RecordNumber:
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     * @Date 2019/8/1
     * @Time 11:00
     **/
    @ResponseBody
    @RequestMapping("showDrugDetails.ahsj")
    public HisApplicationForDrugReturn DrugDetails(String recordNumber) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisApplicationForDrugReturnService.showDrugByRecordNumber(recordNumber))) {
            return new HisApplicationForDrugReturn();
        }
        return hisApplicationForDrugReturnService.showDrugByRecordNumber(recordNumber);
    }

    /**
     * @Description 根据就诊编号查病人信息
     * @Param medicalNumber:
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisPatientInfo
     * @Date 2019/8/1
     * @Time 16:41
     **/
    @ResponseBody
    @RequestMapping("userDrugDetails.ahsj")
    public HisPatientInfo useDrugDetails(String medicalNumber) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisPatientService.selectByMedicalRecordId(medicalNumber))) {
            return new HisPatientInfo();
        }
        return hisPatientService.selectByMedicalRecordId(medicalNumber);
    }


}
