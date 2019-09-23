package com.ahsj.hiscore.controller.ankle;

import com.ahsj.hiscore.entity.HisAnkle;
import com.ahsj.hiscore.entity.HisAnkleDetail;
import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.services.HisAnkleDetailService;
import com.ahsj.hiscore.services.HisAnkleService;
import com.ahsj.hiscore.services.HisHospitalManageService;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/hisAnkle/")
public class HisAnkleController extends BaseController {
    @Autowired
    HisAnkleService hisAnkleService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisAnkleDetailService hisAnkleDetailService;
    
    /**
     *@Description 根据就诊记录编号（record_id = medical_record_id）查看护嘱
     *@Params [hospitalManageId, token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-16
     *@Time 11:00
    **/
    @RequestMapping("listDetails/index.ahsj")
    ModelAndView listDetails(String number, String token,String isSameNurse,Long nurseId,String recordId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/ankle/listDetails");
        modelAndView.addObject("title", "当次护嘱信息");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number",number);
        modelAndView.addObject("isSameNurse",isSameNurse);
        modelAndView.addObject("recordId",recordId);

        //获取到当前登录的用户ID
        modelAndView.addObject("nowUserId",getId());
        //主管护士id
        modelAndView.addObject("nurseId",nurseId);
//        List<HisAnkleDetail> hisAnkleDetailList = hisAnkleDetailService.selectByNumber(number);
//        if (hisAnkleDetailList.size() != 0){
//            modelAndView.addObject("Myname",hisAnkleDetailList.get(0).getName());
//        }
        return modelAndView;
    }


    /**
     *@Description 新增护嘱界面
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/17
     *@Time 17:45
    */
    @RequestMapping("save/index.ahsj")
    ModelAndView saveIndex(String medicalRecordId, String token,Long id, Integer length ) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/ankle/save");
        modelAndView.addObject("title", "当次护嘱信息");
        modelAndView.addObject("token", token);
        if (EmptyUtil.Companion.isNullOrEmpty(id)){
            HisAnkle hisAnkle = new HisAnkle();
            HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByNumber(medicalRecordId);

            hisAnkle.setHosptalregistId(hisHospitalManage.getId());
            hisAnkle.setHosptalregistNumber(hisHospitalManage.getHosptalRegistId());
            hisAnkle.setPatientId(hisHospitalManage.getPatientId());
//            就诊记录编号
            hisAnkle.setRecordId(hisHospitalManage.getNumber());

            modelAndView.addObject("hisAnkle",hisAnkle);
            modelAndView.addObject("flag",false);
        }else {
                //编辑
                HisAnkle hisAnkle = hisAnkleService.selectByPrimaryId(id);
                modelAndView.addObject("hisAnkle",hisAnkle);
            modelAndView.addObject("flag",true);

        }
        return modelAndView;
    }


    /**
     *@Description 根据就诊记录编号（record_id = medical_record_id）查看护嘱
     *@Params [model, request, hisAnkle]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisAnkle>
     *@Author zhushixiang
     *@Date 2019-07-16
     *@Time 11:17
    **/
    @RequestMapping("listByRecordId.ahsj")
    @ResponseBody
    public PageBean<HisAnkle> listByRecordId (Map<String, Object> model, HttpServletRequest request, HisAnkle hisAnkle) throws Exception{
        PageBean<HisAnkle> pageBean = new PageBean<HisAnkle>();
        pageBean.setParameter(hisAnkle);
        return hisAnkleService.listByRecordId(pageBean);
    }

    @RequestMapping("/listByMedicalRecordId.ahsj")
    @ResponseBody
    public PageBean<HisAnkle> listByMedicalRecordId (Map<String, Object> model, HttpServletRequest request, HisAnkle hisAnkle) throws Exception{
        PageBean<HisAnkle> pageBean = new PageBean<HisAnkle>();
        pageBean.setParameter(hisAnkle);
        return hisAnkleService.listByMedicalRecordId(pageBean);
    }
    
    /**
     *@Description 新增护嘱记录
     *@Params 
     *@return 
     *@Author jin
     *@Date 2019/7/17
     *@Time 15:36
    */
    @RequestMapping(value = "save.ahsj",method = {RequestMethod.POST})
    @ResponseBody
    public Message save (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="id", required=true) Long id
            , @RequestParam(value="hosptalregistId", required=false) Long hosptalregistId
            , @RequestParam(value="patientId", required=false) Long patientId
            , @RequestParam(value="recordId", required=false) String recordId
            , @RequestParam(value="hosptalregistNumber", required=false) Long hosptalregistNumber
            , @RequestParam(value="number", required=false) String number
            , @RequestParam(value="type", required=false) Integer type
            , @RequestParam(value="isApproved", required=false) Integer isApproved
            , @RequestParam(value="proofreading", required=false) Integer proofreading
            , @RequestParam(value="orderNum", required=false) Integer orderNum
    ) throws Exception {
        HisAnkle hisAnkle = new HisAnkle();
        if(null != request.getParameter("id")){
            hisAnkle.setId(id);
        }
        if(null != request.getParameter("hosptalregistId")){
            hisAnkle.setHosptalregistId(hosptalregistId);
        }
        if(null != request.getParameter("patientId")){
            hisAnkle.setPatientId(patientId);
        }
        if(null != request.getParameter("recordId")){
            hisAnkle.setRecordId(recordId);
        }
        if(null != request.getParameter("hosptalregistNumber")){
            hisAnkle.setHosptalregistNumber(hosptalregistNumber);
        }
        if(null != request.getParameter("number")){
            DateTimeFormatter fmt=DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");//设置日期格式
            String planNumber= LocalDateTime.now().format(fmt);//mpp：medicine purchase plan药品采购计划
            hisAnkle.setNumber("A"+planNumber);
        }
        if(null != request.getParameter("type")){
            hisAnkle.setType(type);
        }
        if(null != request.getParameter("isApproved")){
            hisAnkle.setIsApproved(isApproved);
        }
        if(null != request.getParameter("proofreading")){
            hisAnkle.setIsProofreading(proofreading);
        }
        if(null != request.getParameter("orderNum")){
            hisAnkle.setOrderNum(orderNum);
        }
        return  hisAnkleService.saveOrUpdate(hisAnkle);
    }


    /**
     *@Description delete
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/19
     *@Time 9:47
    */

    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message delete (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
            , @RequestParam(value="recordId", required=true) String recordId
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisAnkleService.delete(ids,recordId);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }
    
    



    
}
