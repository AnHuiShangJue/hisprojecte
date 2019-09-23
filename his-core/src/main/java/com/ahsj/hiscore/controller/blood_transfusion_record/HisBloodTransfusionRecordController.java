package com.ahsj.hiscore.controller.blood_transfusion_record;

import com.ahsj.hiscore.entity.HisBloodTransfusionRecord;
import com.ahsj.hiscore.entity.HisMedical;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.entity.HisPharmacyDetail;
import com.ahsj.hiscore.services.HisBloodTransfusionRecordService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
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
import java.util.Map;

/**
 *@Description 输血记录控制器
 *@Params
 *@return
 *@Author zhushixiang
 *@Date 2019-09-15
 *@Time 9:59
**/
@Controller
@RequestMapping("/api/bloodTransfusionRecord/")
public class HisBloodTransfusionRecordController extends BaseController {
    @Autowired
    HisBloodTransfusionRecordService hisBloodTransfusionRecordService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

   /**
    *@Description 根据住院表ID 获取对应输血记录
    *@Params [token, medicalRecordId(就诊记录编号), hospitalManageId（住院ID）, pName（病人姓名）]
    *@return org.springframework.web.servlet.ModelAndView
    *@Author zhushixiang
    *@Date 2019-09-15
    *@Time 10:04
   **/
    @RequestMapping("/listByHospitalManageId/index.ahsj")
    ModelAndView listByHospitalManageId(String token, Long hospitalManageId) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/blood_transfusion_record/listByHospitalManageId");
        modelAndView.addObject("title", "当次住院输血记录查看");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hospitalManageId", hospitalManageId);
        Long loginUserId  = getId();
        modelAndView.addObject("loginUserId", loginUserId);
        return modelAndView;
    }

    /**
     *@Description 根据住院ID分页查询
     *@Params [model, request, hisPharmacyDetail]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisPharmacyDetail>
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 10:24
    **/
    @ResponseBody
    @RequestMapping(value = "listByHospitalManageId.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<HisBloodTransfusionRecord> list(Map<String, Object> model, HttpServletRequest request, HisBloodTransfusionRecord hisBloodTransfusionRecord) throws Exception {
        PageBean<HisBloodTransfusionRecord> pageBean = new PageBean<HisBloodTransfusionRecord>();
        pageBean.setParameter(hisBloodTransfusionRecord);
        return hisBloodTransfusionRecordService.listByHospitalManageId(pageBean);
    }

    /**
     *@Description 新增或更新界面跳转
     *@Params [hisPharmacyDetail, token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 11:10
    **/
    @RequestMapping("saveOrUpdate/index.ahsj")
    ModelAndView saveOrUpdate(HisBloodTransfusionRecord hisBloodTransfusionRecord, String token,Long hospitalManageId)throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/blood_transfusion_record/saveOrUpdate");
        if (!EmptyUtil.Companion.isNullOrEmpty(hisBloodTransfusionRecord.getId())) {
            modelAndView.addObject("hisBloodTransfusionRecord", hisBloodTransfusionRecordService.selectById(hisBloodTransfusionRecord.getId()));
        } else {
            hisBloodTransfusionRecord.setHospitalManageId(hospitalManageId);
            modelAndView.addObject("hisBloodTransfusionRecord", hisBloodTransfusionRecord);
        }
        modelAndView.addObject("title", "输血记录（Blood transfusion record）");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     *@Description 新增或更新
     *@Params [model, hisPharmacyDetail]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 11:43
    **/
    @RequestMapping(value = "saveOrUpdate.ahsj")
    @ResponseBody
    public Message saveOrUpdate(Map<String, Object> model, HisBloodTransfusionRecord hisBloodTransfusionRecord) throws Exception {
        return hisBloodTransfusionRecordService.saveOrUpdate(hisBloodTransfusionRecord);
    }

    /**
     *@Description 签字
     *@Params [model, hisBloodTransfusionRecord]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 15:04
    **/
    @RequestMapping(value = "sign.ahsj")
    @ResponseBody
    public Message sign(Map<String, Object> model, @RequestParam(value = "id",required = true)Long id,@RequestParam(value = "loginUserId",required = true)Long loginUserId) throws Exception {
        String signName = getUserId();
        return hisBloodTransfusionRecordService.sign(id,loginUserId,signName);
    }

    /**
     *@Description 删除
     *@Params [model, request, ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 15:52
    **/
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Message delete (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisBloodTransfusionRecordService.delete(ids);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }
}
