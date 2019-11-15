package com.ahsj.hiscore.controller.medicationDetails;

import com.ahsj.hiscore.entity.HisInfusion;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.entity.HisMedicationDetails;
import com.ahsj.hiscore.entity.HisRegistered;
import com.ahsj.hiscore.services.HisInfusionService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import com.ahsj.hiscore.services.HisMedicationDetailsService;
import com.ahsj.hiscore.services.HisRegisteredService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
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
@RequestMapping("/api/medicationDetails/")
public class HisMedicationDetailsController extends BaseController {
    @Autowired
    HisMedicationDetailsService hisMedicationDetailsService;

    @Autowired
    HisInfusionService hisInfusionService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisRegisteredService hisRegisteredService;

    /**
     * @return
     * @Description 分页查询
     * @Params
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 13:31
     **/
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public PageBean<HisMedicationDetails> list(Map<String, Object> model, HttpServletRequest request, HisMedicationDetails hisMedicationDetails) throws Exception {
        PageBean<HisMedicationDetails> pageBean = new PageBean<HisMedicationDetails>();
        pageBean.setParameter(hisMedicationDetails);
        return hisMedicationDetailsService.list(pageBean);
    }

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [model, request, ids]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 13:34
     **/
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Message delete(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "ids", required = true) Long[] ids
    ) throws Exception {
        if (null != request.getParameter("ids")) {
            return hisMedicationDetailsService.delete(ids);
        } else return MessageUtil.createMessage(false, "参数异常");
    }


    /**
     * @Description 根据就诊编号查看用药明细
     * @Param hd:
     * @Author: dingli
     * @return: core.entity.PageBean<HisApplicationForDrugReturnDetails>
     * @Date 2019/8/1
     * @Time 15:02
     **/
    @ResponseBody
    @RequestMapping("useDrugDetails.ahsj")
    public PageBean<HisMedicationDetails> useDrugDetails(HisMedicationDetails hd)
            throws Exception {
        PageBean<HisMedicationDetails> pageBean = new PageBean<HisMedicationDetails>();
        if (!EmptyUtil.Companion.isNullOrEmpty(hd.getMedicalNumber())) {
            pageBean.setParameter(hd);
            pageBean = hisMedicationDetailsService.useDrug(pageBean);
            return pageBean;
        } else {
            pageBean.setData(new ArrayList<HisMedicationDetails>());
            return pageBean;
        }

    }

    /**
     * @param medicalNumber
     * @Description 打印用药明细
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @Date 2019/8/19
     * @Time 14:06
     **/
    @ResponseBody
    @RequestMapping("PrintUseDrug.ahsj")
    public List<HisMedicationDetails> PrintUseDrug(String medicalNumber) throws Exception {
        return hisMedicationDetailsService.selectByMedicalNumber(medicalNumber);
    }

    /**
     * @Description 查询输液单可选药品信息
     * @Author muxu
     * @Date 2019/8/29
     * @Time 15:17
     * @Return
     * @Params
     **/
    @RequestMapping(value = "OutpatientInfusionList.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public PageBean<HisMedicationDetails> selectOutpatientInfusionByMedicalRecordId(Map<String, Object> model, HttpServletRequest request, HisMedicationDetails hisMedicationDetails) throws Exception {
        PageBean<HisMedicationDetails> pageBean = new PageBean<HisMedicationDetails>();
        pageBean.setParameter(hisMedicationDetails);
        return hisMedicationDetailsService.selectOutpatientInfusionByMedicalRecordId(pageBean);
    }

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     * @Date 2019/9/7
     * @Time 11:27
     **/
    @ResponseBody
    @RequestMapping("selectPrint.ahsj")
    public List<HisMedicationDetails> selectPrint(String number) throws Exception {
        HisRegistered hisRegistered =hisRegisteredService.selectByNumber(number);
        HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByRegisterId(hisRegistered.getId());
        List<HisInfusion> hisInfusionList = hisInfusionService.selectByRecordNumberAndNotPay(hisMedicalRecord.getMedicalRecordId());
        List<HisMedicationDetails> hisMedicationDetailsList = hisMedicationDetailsService.selectPrint(number);
        if(!EmptyUtil.Companion.isNullOrEmpty(hisInfusionList) && hisInfusionList.size() != 0) {
            for (HisInfusion hisInfusion : hisInfusionList) {
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetailsList) && hisMedicationDetailsList.size() != 0) {
                    for (HisMedicationDetails hisMedicationDetails : hisMedicationDetailsList) {
                        if (hisMedicationDetails.getDrugsNumb().equals(hisInfusion.getDrugsNumb())) {
                            hisMedicationDetails.setCount(hisMedicationDetails.getCount() - Integer.valueOf(hisInfusion.getDosage()));
                            if(hisMedicationDetails.getCount() <= 0)
                                hisMedicationDetailsList.remove(hisMedicationDetails);
                            break;
                        }
                    }
                }
            }
        }
        return hisMedicationDetailsList;
    }

    /**
     *@Description
     *@Params
     *@return
     *@Author jin
     *@Date 2019/11/8
     *@Time 18:43
    */
    @ResponseBody
    @RequestMapping("infusionPrint.ahsj")
    public List<HisMedicationDetails> infusionPrint(String number) throws Exception {
        return hisMedicationDetailsService.infusionPrint(number);
    }

    /**
     * @Description 查看历史用药
     * @Params: [token, numbers]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/9/24
     * @Time 11:21
     **/
    @RequestMapping("showDrugs/index.ahsj")
    public ModelAndView showDrugs(String token, String medicalRecordId) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/useDrug");
        modelAndView.addObject("medicalRecordId", medicalRecordId);
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     *@Description 根据就诊记录ID查询未付的药品
     *@Params [token, medicalRecordId]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-10-06
     *@Time 17:48
    **/
    @RequestMapping("listByRecordIdAndNotPay/index.ahsj")
    public ModelAndView listByRecordIdAndNotPay(String token, String recordId) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/listByRecordIdAndNotPay");
        modelAndView.addObject("recordId", recordId);
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     *@Description 根据就诊记录ID查询未付的药品
     *@Params [model, request, hisMedicationDetails]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicationDetails>
     *@Author zhushixiang
     *@Date 2019-10-06
     *@Time 17:55
    **/
    @RequestMapping(value = "listByRecordIdAndNotPay.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public PageBean<HisMedicationDetails> listByRecordIdAndNotPay(Map<String, Object> model, HttpServletRequest request, HisMedicationDetails hisMedicationDetails) throws Exception {
        PageBean<HisMedicationDetails> pageBean = new PageBean<HisMedicationDetails>();
        pageBean.setParameter(hisMedicationDetails);
        return hisMedicationDetailsService.listByRecordIdAndNotPay(pageBean);
    }

    /**
     *@Description 根据ids 批量查找
     *@Params [ids]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     *@Author zhushixiang
     *@Date 2019-10-06
     *@Time 19:23
    **/
    @ResponseBody
    @RequestMapping("selectByIds.ahsj")
    public List<HisMedicationDetails> selectByIds(Long[] ids) throws Exception {
        return hisMedicationDetailsService.selectByIds(ids);
    }

    /**
     *@Description 根据药品编号和就诊记录ID 核查该药品是否存在输液单
     *@Params [ids]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMedicationDetails>
     *@Author zhushixiang
     *@Date 2019-10-07
     *@Time 16:57
    **/
    @ResponseBody
    @RequestMapping("selectByDrugsNumbAndRecordIdAndNotPay.ahsj")
    public List<HisInfusion> selectByDrugsNumbAndRecordIdAndNotPay(Long recordId,String drugsNumb) throws Exception {
        return hisInfusionService.selectByDrugsNumbAndRecordIdAndNotPay(recordId,drugsNumb);
    }




}
