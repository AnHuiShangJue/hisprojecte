package com.ahsj.hiscore.controller.medicalOrder;

import com.ahsj.hiscore.common.utils.ZipUtils;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.*;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/medicalOrderDetail/")
public class HisMedicalOrderDetailController extends BaseController {
    @Autowired
    HisMedicalOrderDetailService hisMedicalOrderDetailService;

    @Autowired
    HisMedicalOrderTemplateDetailService hisMedicalOrderTemplateDetailService;

    @Autowired
    HisMedicalOrderService hisMedicalOrderService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisBedService hisBedService;

    @Autowired
    HisInfusionService hisInfusionService;
    /**
     *@Description 根据医嘱编号查看医嘱详细信息
     *@Params [number, token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 15:13
     **/
    @RequestMapping("listDetailByNumber/index.ahsj")
    ModelAndView listDetailByNumber( String token,@RequestParam(value ="number",required = true)String number,
                                     @RequestParam(value = "isDischarged",required = false)Integer isDischarged,String hosptalregistNumber,Integer type) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if(type == 1){
            modelAndView.setViewName("backend/hiscore/medicalorder/listDetailByNumber");

        }
        //临时医嘱
        else {
            modelAndView.setViewName("backend/hiscore/medicalorder/listDetailByNumberTemporary");
        }
        modelAndView.addObject("title", "当次医嘱详细信息");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number",number);
        modelAndView.addObject("isDischarged",isDischarged);
        modelAndView.addObject("hosptalregistNumber",hosptalregistNumber);
        modelAndView.addObject("UserId",getId());
        return modelAndView;
    }

    /**
     *@Description 护士查看医嘱
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/21
     *@Time 22:38
     */
    @RequestMapping("listDetailByNumberNurse/index.ahsj")
    ModelAndView listDetailByNumberNurse( String token,@RequestParam(value ="number",required = true)String number,
                                          @RequestParam(value = "isDischarged",required = false)Integer isDischarged,String hosptalregistNumber,Integer type) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if(type == 1){
            modelAndView.setViewName("backend/hiscore/nursestation/listDetailByNumber");

        }
        //临时医嘱
        else {
            modelAndView.setViewName("backend/hiscore/nursestation/listDetailByNumberTemporary");
        }
        modelAndView.addObject("title", "当次医嘱详细信息");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number",number);
        modelAndView.addObject("isDischarged",isDischarged);
        modelAndView.addObject("hosptalregistNumber",hosptalregistNumber);
        return modelAndView;
    }

    /**
     *@Description 根据医嘱编号查看医嘱详细信息
     *@Params [model, request, hisMedicalOrderDetail]
     *@return core.entity.PageBean<hisMedicalOrderDetail>
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 15:20
     **/
    @RequestMapping("/listDetailByNumber.ahsj")
    @ResponseBody
    public PageBean<HisMedicalOrderDetail> listDetailByNumber (Map<String, Object> model, HttpServletRequest request, HisMedicalOrderDetail hisMedicalOrderDetail) throws Exception{
        PageBean<HisMedicalOrderDetail> pageBean = new PageBean<HisMedicalOrderDetail>();
        pageBean.setParameter(hisMedicalOrderDetail);
        pageBean = hisMedicalOrderDetailService.listDetailByNumber(pageBean);
        return pageBean;
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
            return  hisMedicalOrderDetailService.delete(ids);
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
    ModelAndView UpdateIndex(HisMedicalOrderDetail hisMedicalOrderDetail, String token)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getId())){
            modelAndView.setViewName("backend/hiscore/medicalorder/updateForDetail");
            modelAndView.addObject("hisMedicalOrderDetail",hisMedicalOrderDetailService.selectById(hisMedicalOrderDetail.getId()));
            modelAndView.addObject("title","修改医嘱详细信息(Modify medical details)");
        }else{
            //普通医嘱
            if(hisMedicalOrderDetail.getMedicalOrderType() == 1){
                modelAndView.setViewName("backend/hiscore/medicalorder/updateForDetail");
                hisMedicalOrderDetail.setIsFirstEdit(1);
                modelAndView.addObject("hisMedicalOrderDetail",hisMedicalOrderDetail);
                modelAndView.addObject("title","添加普通医嘱（Add general medical advice）");
            }
            //用药医嘱
            else if(hisMedicalOrderDetail.getMedicalOrderType() == 2){
                modelAndView.setViewName("backend/hiscore/pharmacy/listForMedication");
                modelAndView.addObject("title", "添加用药医嘱（Add medication order）");
                hisMedicalOrderDetail.setIsFirstEdit(1);
                modelAndView.addObject("hisMedicalOrderDetail",hisMedicalOrderDetail);
                modelAndView.addObject("type",1);
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getNumber()))
                    modelAndView.addObject("medicalOrderNumber", hisMedicalOrderDetail.getNumber());

            }
            //项目医嘱
            else if(hisMedicalOrderDetail.getMedicalOrderType() == 3){
                modelAndView.setViewName("backend/hiscore/medicalrecord/list_project");
                hisMedicalOrderDetail.setIsFirstEdit(1);
                modelAndView.addObject("hisMedicalOrderDetail",hisMedicalOrderDetail);
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getNumber()))
                    modelAndView.addObject("medicalOrderNumber", hisMedicalOrderDetail.getNumber());
            }
        }
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     *@Description 临时医嘱
     *@Params [hisMedicalOrderDetail, token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-09-21
     *@Time 21:48
     **/
    @RequestMapping("updateTemporary/index.ahsj")
    ModelAndView updateTemporary(HisMedicalOrderDetail hisMedicalOrderDetail, String token)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getId())){
            modelAndView.setViewName("backend/hiscore/medicalorder/updateForDetailTemporary");
            modelAndView.addObject("hisMedicalOrderDetail",hisMedicalOrderDetailService.selectById(hisMedicalOrderDetail.getId()));
            modelAndView.addObject("title","修改医嘱详细信息(Modify medical details)");
        }else{
            //普通医嘱
            if(hisMedicalOrderDetail.getMedicalOrderType() == 1){
                modelAndView.setViewName("backend/hiscore/medicalorder/updateForDetailTemporary");
                hisMedicalOrderDetail.setIsFirstEdit(1);
                modelAndView.addObject("hisMedicalOrderDetail",hisMedicalOrderDetail);
                modelAndView.addObject("title","添加普通医嘱（Add general medical advice）");
            }
            //用药医嘱
            else if(hisMedicalOrderDetail.getMedicalOrderType() == 2){
                modelAndView.setViewName("backend/hiscore/pharmacy/listForMedication");
                modelAndView.addObject("title", "添加用药医嘱（Add medication order）");
                hisMedicalOrderDetail.setIsFirstEdit(1);
                modelAndView.addObject("hisMedicalOrderDetail",hisMedicalOrderDetail);
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getNumber()))
                    modelAndView.addObject("medicalOrderNumber", hisMedicalOrderDetail.getNumber());
            }
            //项目医嘱
            else if(hisMedicalOrderDetail.getMedicalOrderType() == 3){
                modelAndView.setViewName("backend/hiscore/medicalrecord/list_project");
                hisMedicalOrderDetail.setIsFirstEdit(1);
                modelAndView.addObject("hisMedicalOrderDetail",hisMedicalOrderDetail);
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getNumber()))
                    modelAndView.addObject("medicalOrderNumber", hisMedicalOrderDetail.getNumber());
            }
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
    public Message saveOrUpdate (Map<String, Object> model, HttpServletRequest request, HisMedicalOrderDetail hisMedicalOrderDetail) throws Exception {
        return  hisMedicalOrderDetailService.saveOrUpdate(hisMedicalOrderDetail);
    }

    /**
     *@Description 校对和核准
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/22
     *@Time 11:27
     */
    @RequestMapping(value = "proofreading.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message proofreading (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
            , @RequestParam(value="recordId", required=true) String recordId
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisMedicalOrderDetailService.proofreading(ids,recordId);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }

    @RequestMapping(value = "isApproved.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message isApproved (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
            , @RequestParam(value="recordId", required=true) String recordId
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisMedicalOrderDetailService.isApproved(ids,recordId);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }

    /**
     *@Description 查询模板并添加到护嘱明细中
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/23
     *@Time 15:09
     */

    @RequestMapping(value = "addTemplate.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message addTemplate(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="templateNumber", required=false) String templateNumber
            , @RequestParam(value="number", required=false) String number
//            , @RequestParam(value="name", required=false) String name
    ) throws Exception {
        if(null != request.getParameter("templateNumber")){
            return hisMedicalOrderDetailService.addTemplate(templateNumber,number);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }

    /**
     *@Description 通过医嘱开药
     *@Params [model, request, ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 18:13
     **/
    @RequestMapping(value = "addMedicine.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Message addMedicine(Map<String, Object> model, HttpServletRequest request,
                               @RequestParam(value = "ids", required = true)Long[] ids,
                               @RequestParam(value = "medicalOrderNumber", required = true) String medicalOrderNumber
    ) throws Exception {
        return hisMedicalOrderDetailService.addMedicine(ids,medicalOrderNumber);
    }

    /**
     *@Description 通过医嘱开项目
     *@Params [model, request, ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 18:13
     **/
    @RequestMapping(value = "addProject.ahsj", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Message addProject(Map<String, Object> model, HttpServletRequest request,
                              @RequestParam(value = "ids", required = true)Long[] ids,
                              @RequestParam(value = "medicalOrderNumber", required = true) String medicalOrderNumber
    ) throws Exception {
        return hisMedicalOrderDetailService.addProject(ids,medicalOrderNumber);
    }

    /**
     *@Description 停嘱
     *@Params [model, request, ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-18
     *@Time 13:55
     **/
    @RequestMapping(value = "stopOrder.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message stopOrder (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="id", required=true) Long id,@RequestParam(value = "stopDate",required = true) String stopDate
    ) throws Exception {
        if(null != request.getParameter("id")){
            return  hisMedicalOrderDetailService.stopOrder(id,getId(),stopDate);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }

    /**
     *@Description 取消临时医嘱
     *@Params [model, request, id]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-25
     *@Time 11:09
    **/
    @RequestMapping(value = "cancleOrder.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message cancleOrder (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="id", required=true) Long id
            , @RequestParam(value="stopDate", required=false) String stopDate
    ) throws Exception {
        if(null != request.getParameter("id")){
            return  hisMedicalOrderDetailService.cancleOrder(id,getId(),stopDate);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }

    /**
     *@Description 跳转打印医嘱页面
     *@Params [number, token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-09-21
     *@Time 18:10
     **/
    @RequestMapping("printDoctorAdvice/index.ahsj")
    //type代表是续打还是非续打 1是非续打 2续打
    ModelAndView printDoctorAdvice(String number, String token,String hosptalregistNumber,@RequestParam(value = "type",required = false)Integer type,Integer continueCount,Integer isFirst) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "打印凭证预览");
        modelAndView.addObject("token", token);
        HisMedicalOrder hisMedicalOrder = hisMedicalOrderService.selectByNumber(number);
        if(hisMedicalOrder.getType() == 1) {
            if(type == 1)
                modelAndView.setViewName("backend/hiscore/medicalorder/newDoctorAdvice");
            else
                modelAndView.setViewName("backend/hiscore/medicalorder/newContinuePrintForDoctorAdvice");
        }
        else if(hisMedicalOrder.getType() == 2){
            if(type == 1)
                modelAndView.setViewName("backend/hiscore/medicalorder/temporaryOrders");
            else
                modelAndView.setViewName("backend/hiscore/medicalorder/newContinuePrintForDoctorAdvice");
        }

        HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByHospNumber(hosptalregistNumber);
        if(hisHospitalManage.getSex()==1)
            hisHospitalManage.setSexName("男");
        else
            hisHospitalManage.setSexName("女");
        modelAndView.addObject("hisMedicalOrder", hisMedicalOrder);
        HisBed hisBed = hisBedService.selectByPrimaryKey(hisHospitalManage.getBedId());
        if(!EmptyUtil.Companion.isNullOrEmpty(hisBed))
            hisHospitalManage.setBedsNumber(hisBed.getNumber());
        modelAndView.addObject("hisHospitalManage", hisHospitalManage);
        //根据医嘱编号查看明细
        List<HisMedicalOrderDetail> hisMedicalOrderDetailList = CodeHelper.getInstance().setCodeValue(hisMedicalOrderDetailService.selectByNumberAscAndNotStop(number));
        if(EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetailList)||hisMedicalOrderDetailList.size()!=0) {
            String infusionNumber = "";//记录医嘱单编号
            Integer flag = 0;//记录输液单的起始
            for (HisMedicalOrderDetail hisMedicalOrderDetail : hisMedicalOrderDetailList) {
                //记录拼接医嘱内容
                StringBuffer stringBuffer = new StringBuffer(hisMedicalOrderDetail.getName());
                if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getAnName())) {
                    hisMedicalOrderDetail.setAnName("");
                }
                if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getPnName())) {
                    hisMedicalOrderDetail.setPnName("");
                }
                if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getSanName())) {
                    hisMedicalOrderDetail.setSanName("");
                }
                if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getSpnName())) {
                    hisMedicalOrderDetail.setSpnName("");
                }
                if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getStopUserName())) {
                    hisMedicalOrderDetail.setStopUserName("");
                }
                if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getStopUserName())) {
                    hisMedicalOrderDetail.setStopUserName("");
                }
              /*  if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getUsages())) {
                    stringBuffer.append("  "+hisMedicalOrderDetail.getUsages());
                }*/
                if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getIntervalsName())) {
                    stringBuffer.append("  "+hisMedicalOrderDetail.getIntervalsName());
                }
                if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getTotalAmount())) {
                    stringBuffer.append("  "+hisMedicalOrderDetail.getTotalAmount());
                }



                //设置医嘱单符号
                    //从是医嘱单的开始   记录编号若编号不一致重新开始一次
                    //标记输液单起始符号 「代表开始  \代表其中包含药品 」代表结束
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicalOrderDetail.getIsInfusionList())) {
                    if (hisMedicalOrderDetail.getIsInfusionList() == 1) {
                        if (EmptyUtil.Companion.isNullOrEmpty(infusionNumber)|| flag == -1) {
                            infusionNumber = hisMedicalOrderDetail.getInfusionNumber();
                            flag = 0;
                        }
                        List<HisInfusion> hisInfusionList = hisInfusionService.selectByNumber(infusionNumber);
                            if (flag == 0) {
                                stringBuffer.append("  「");
                                flag++;
                            } else {
                                if (flag + 1 == hisInfusionList.size()) {
                                    stringBuffer.append("  」");
                                    flag = -1;
                                } else {
                                    stringBuffer.append("  |");
                                    flag++;
                                }
                            }

                    }
                }
                hisMedicalOrderDetail.setName(stringBuffer.toString());
            }


        }
        modelAndView.addObject("hisMedicalOrderDetailList",hisMedicalOrderDetailList);
        modelAndView.addObject("continueCount",continueCount);
        modelAndView.addObject("isFirst",isFirst);
        return modelAndView;
    }

    /**
     *@Description 医嘱添加药品组合
     *@Params [model, request, id]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-24
     *@Time 18:18
    **/
    @RequestMapping(value = "addCombinationMedicine.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    //传来的ID为医嘱明细ID
    public Message addCombinationMedicine (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisMedicalOrderDetailService.addCombinationMedicine(ids);
        }else  return MessageUtil.createMessage(false,"参数异常(Abnormal parameter)");
    }

}
