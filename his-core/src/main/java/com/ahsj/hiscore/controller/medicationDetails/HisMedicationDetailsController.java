package com.ahsj.hiscore.controller.medicationDetails;

import com.ahsj.hiscore.entity.HisMedicationDetails;
import com.ahsj.hiscore.services.HisMedicationDetailsService;
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
        return hisMedicationDetailsService.selectPrint(number);
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


}
