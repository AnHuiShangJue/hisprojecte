package com.ahsj.hiscore.controller.medicalOrder;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisMedicalOrderDetailService;
import com.ahsj.hiscore.services.HisMedicalOrderService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/hisMedicalOrder/")
public class HisMedicalOrderController extends BaseController {

    @Autowired
    HisMedicalOrderService hisMedicalOrderService;

    @Autowired
    HisMedicalOrderDetailService hisMedicalOrderDetailService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;
    /**
     *@Description 根据就诊记录编号查看医嘱
     *@Params [model, request, hisAnkle]
     *@return core.entity.PageBean<HisAnkle>
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 13:38
    **/
    @RequestMapping("/listByRecordId.ahsj")
    @ResponseBody
    public PageBean<HisMedicalOrder> listByRecordId (Map<String, Object> model, HttpServletRequest request, HisMedicalOrder hisMedicalOrder) throws Exception{
        PageBean<HisMedicalOrder> pageBean = new PageBean<HisMedicalOrder>();
        pageBean.setParameter(hisMedicalOrder);
        return hisMedicalOrderService.listByRecordId(pageBean);
    }

    /**
     *@Description 删除
     *@Params [model, request, ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 17:29
     **/
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message delete (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisMedicalOrderService.delete(ids);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }

    /**
     *@Description 新增或修改
     *@Params [hisDoctorInfo, token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 17:32
     **/
    @RequestMapping("/update/index.ahsj")
    ModelAndView UpdateIndex(HisMedicalOrder hisMedicalOrder, String token)throws Exception{
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalorder/update");
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrder.getId())){
            modelAndView.addObject("hisMedicalOrder",hisMedicalOrderService.selectById(hisMedicalOrder.getId()));
            modelAndView.addObject("title","修改医嘱基本信息");
        }else{
            HisHospitalManage hisHospitalManage = hisHospitalManageService.selectById(hisMedicalOrder.getHosptalregistId());
            if(!EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage))
                hisMedicalOrder.setHosptalregistNumber(hisHospitalManage.getNumber());
            modelAndView.addObject("hisMedicalOrder",hisMedicalOrder);
            modelAndView.addObject("title","添加医嘱基本信息");
        }
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     *@Description 新增或更新
     *@Params [model, request, hisDoctorInfo]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 20:51
     **/
    @RequestMapping(value = "saveOrUpdate.ahsj", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Message saveOrUpdate (Map<String, Object> model, HttpServletRequest request, HisMedicalOrder hisMedicalOrder) throws Exception {
        return  hisMedicalOrderService.saveOrUpdate(hisMedicalOrder);
    }

    /**
     *@Description 打印医嘱
     *@Params [number, token, hisTollDetails]
     *@return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2019-09-21
     *@Time 17:50
    **/
    @PostMapping("printDoctorAdvice/index.ahsj")
    @ResponseBody
    List<HisMedicalOrderDetail> printDoctorAdvice(String number) throws Exception {//没有明细
        //根据医嘱编号查看明细
        List<HisMedicalOrderDetail> hisMedicalOrderDetailList = CodeHelper.getInstance().setCodeValue(hisMedicalOrderDetailService.selectByNumberAscAndNotStop(number));
        if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetailList)||hisMedicalOrderDetailList.size()==0)
            return new ArrayList<>();
        for (HisMedicalOrderDetail hisMedicalOrderDetail : hisMedicalOrderDetailList) {
            if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getAnName())){
                hisMedicalOrderDetail.setAnName("");
            } if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getPnName())){
                hisMedicalOrderDetail.setPnName("");
            } if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getSanName())){
                hisMedicalOrderDetail.setSanName("");
            } if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getSpnName())){
                hisMedicalOrderDetail.setSpnName("");
            } if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getStopUserName())){
                hisMedicalOrderDetail.setStopUserName("");
            }
        }
        return hisMedicalOrderDetailList;
    }
}
