package com.ahsj.hiscore.controller.ankle;

import com.ahsj.hiscore.entity.HisAnkleDetail;
import com.ahsj.hiscore.entity.HisAnkleTemplate;
import com.ahsj.hiscore.entity.HisPatientInfo;
import com.ahsj.hiscore.services.HisAnkleDetailService;
import com.ahsj.hiscore.services.HisAnkleTemplateService;
import com.ahsj.hiscore.services.HisPatientService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/hisAnkleDetail/")
public class HisAnkleDetailController extends BaseController {

    @Autowired
    HisAnkleDetailService hisAnkleDetailService;

    @Autowired
    HisAnkleTemplateService hisAnkleTemplateService;
    @Autowired
    HisPatientService hisPatientService;

    /**
     *@Description 根据护嘱编号查看护嘱明细
     *@Params [medicalRecordId, token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-17
     *@Time 14:43
    **/
    @RequestMapping(value = "listByNumber.ahsj",method = {RequestMethod.POST})
    @ResponseBody
    public PageBean<HisAnkleDetail> listByNumber (Map<String, Object> model, HttpServletRequest request, HisAnkleDetail hisAnkleDetail) throws Exception{
        PageBean<HisAnkleDetail> pageBean = new PageBean<HisAnkleDetail>();
        pageBean.setParameter(hisAnkleDetail);
        return hisAnkleDetailService.listByNumber(pageBean);
    }

    /**
     *@Description 新增护嘱明细（跳转页面）
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/17
     *@Time 15:12
     */

    @RequestMapping("save/index.ahsj")
    ModelAndView save(String number, String token,Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/ankle/saveDetails");
        modelAndView.addObject("title", "新增护嘱");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number",number);
        if (EmptyUtil.Companion.isNullOrEmpty(id)){
            //新增
            modelAndView.addObject("hisAnkleDetail",new HisAnkleDetail());
            modelAndView.addObject("flag",false);
        }else {
            //更新
            modelAndView.addObject("flag",true);

            modelAndView.addObject("hisAnkleDetail",hisAnkleDetailService.selectByPrimaryId(id));
        }
        return modelAndView;
    }
    
    
    /**
     *@Description 跳转到打印输液单页面
     *@Params 
     *@return 
     *@Author jin
     *@Date 2019/7/29
     *@Time 14:31
    */
    @RequestMapping("printf/index.ahsj")
    ModelAndView printf(String number, String token,Long id,String recordId,String proofreadingNurseName,String approvedNurseName) throws Exception {
   /*     HisAnkleDetail hisAnkleDetail = new HisAnkleDetail();
        hisAnkleDetail.setNumber(number);
        hisAnkleDetail.setId(id);*/

        //hisPatientInfo.setSexName(hisAnkleDetail.getIsSkinTest()==1 ? "是":"否");


        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/print-infusion");
        modelAndView.addObject("title", "打印输液单");
        modelAndView.addObject("token", token);
        modelAndView.addObject("recordId", recordId);//护嘱编号
        modelAndView.addObject("number", number);//用查找病人信息
        modelAndView.addObject("id", id);//用于查找护嘱明细
        modelAndView.addObject("proofreadingNurseName", proofreadingNurseName);//校对护士
        modelAndView.addObject("approvedNurseName", approvedNurseName);//核准护士

        if (!EmptyUtil.Companion.isNullOrEmpty(id)){
            HisPatientInfo hisPatientInfo = hisPatientService.selectByAnkleDetailNumber(number);
            HisAnkleDetail hisAnkleDetail = hisAnkleDetailService.selectByPrimaryId(id);

            hisPatientInfo.setSexName(hisPatientInfo.getSex()==1 ? "男":"女");
            switch (hisAnkleDetail.getIntervals()){
                case 1:hisAnkleDetail.setIntervalsName("qh");break;
                case 2:hisAnkleDetail.setIntervalsName("q2h");break;
                case 3:hisAnkleDetail.setIntervalsName("q4h");break;
                case 4:hisAnkleDetail.setIntervalsName("q6h");break;
                case 5:hisAnkleDetail.setIntervalsName("qd");break;
                case 6:hisAnkleDetail.setIntervalsName("bid");break;
                case 7:hisAnkleDetail.setIntervalsName("tid");break;
                case 8:hisAnkleDetail.setIntervalsName("qid");break;
                case 9:hisAnkleDetail.setIntervalsName("qn");break;
                case 10:hisAnkleDetail.setIntervalsName("qod");break;
                case 11:hisAnkleDetail.setIntervalsName("biw");break;
                case 12:hisAnkleDetail.setIntervalsName("hs");break;
                case 13:hisAnkleDetail.setIntervalsName("am");break;
                case 14:hisAnkleDetail.setIntervalsName("pm");break;
                case 15:hisAnkleDetail.setIntervalsName("St");break;
                case 16:hisAnkleDetail.setIntervalsName("DC");break;
                case 17:hisAnkleDetail.setIntervalsName("prn");break;
                case 18:hisAnkleDetail.setIntervalsName("sos");break;
                case 19:hisAnkleDetail.setIntervalsName("ac");break;
                case 20:hisAnkleDetail.setIntervalsName("pc");break;
                case 21:hisAnkleDetail.setIntervalsName("12n");break;
                case 22:hisAnkleDetail.setIntervalsName("12mn");break;
                default:break;
            }
            hisAnkleDetail.setIsSkinTestName(hisAnkleDetail.getIsSkinTest() == 1 ? "已皮试":"未皮试");


            modelAndView.addObject("hisAnkleDetail",hisAnkleDetail);
            modelAndView.addObject("hisPatientInfo",hisPatientInfo);
        }
        return modelAndView;
    }
    /**
     *@Description 获取输液单信息
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/2
     *@Time 14:36
    */
    @RequestMapping(value = "getAnkleForPrintf.ahsj")
    @ResponseBody
    HisAnkleDetail getAnkleForPrintf(Long id )throws Exception{
        HisAnkleDetail hisAnkleDetail = hisAnkleDetailService.selectByPrimaryId(id);
        return hisAnkleDetail;
    }
    @RequestMapping(value = "getPatientForPrintf.ahsj")
    @ResponseBody
    HisPatientInfo getPatientForPrintf(String number )throws Exception{
        HisPatientInfo hisPatientInfo = hisPatientService.selectByAnkleDetailNumber(number);
        return hisPatientInfo;
    }

    /**
     *@Description 新增或更新
     *@Params 
     *@return 
     *@Author jin
     *@Date 2019/7/17
     *@Time 15:18
    */
    @RequestMapping(value = "saveOrUpdate.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message saveOrUpdate (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="id", required=true) Long id
            , @RequestParam(value="number", required=false) String number
            , @RequestParam(value="name", required=false) String name
            , @RequestParam(value="isSkinTest", required=false) Integer isSkinTest
            , @RequestParam(value="specification", required=false) String specification
            , @RequestParam(value="unit", required=false) String unit
            , @RequestParam(value="usages", required=false) String usages
            , @RequestParam(value="dosage", required=false) String dosage
            , @RequestParam(value="remarks", required=false) String remarks
            , @RequestParam(value="intervals", required=false) Integer intervals
            , @RequestParam(value="proofreadingNurseId", required=false) Long proofreadingNurseId
            , @RequestParam(value="approvedNurseId", required=false) Long approvedNurseId
            , @RequestParam(value="isStop", required=false) Integer isStop
            , @RequestParam(value="stopDate", required=false) Date stopDate
            , @RequestParam(value="stopUserId", required=false) Long stopUserId
            , @RequestParam(value="stopPrfingNurseId", required=false) Long stopPrfingNurseId
            , @RequestParam(value="stopApdNurseId", required=false) Long stopApdNurseId
            , @RequestParam(value="totalAmount", required=false) Double totalAmount
            , @RequestParam(value="orderNum", required=false) Integer orderNum
            , @RequestParam(value="isProofreading", required=false) Integer isProofreading
            , @RequestParam(value="isApproved", required=false) Integer isApproved

    ) throws Exception {
        HisAnkleDetail hisAnkleDetail = new HisAnkleDetail();
        if(null != request.getParameter("id")){
            hisAnkleDetail.setId(id);
        }
        if(null != request.getParameter("number")){
            hisAnkleDetail.setNumber(number);
        }
        if(null != request.getParameter("name")){
            hisAnkleDetail.setName(name);
        }
        if(null != request.getParameter("isSkinTest")){
            hisAnkleDetail.setIsSkinTest(isSkinTest);
        }
        if(null != request.getParameter("specification")){
            hisAnkleDetail.setSpecification(specification);
        }
        if(null != request.getParameter("unit")){
            hisAnkleDetail.setUnit(unit);
        }
        if(null != request.getParameter("usages")){
            hisAnkleDetail.setUsages(usages);
        }
        if(null != request.getParameter("dosage")){
            hisAnkleDetail.setDosage(dosage);
        }
        if(null != request.getParameter("remarks")){
            hisAnkleDetail.setRemarks(remarks);
        }
        if(null != request.getParameter("intervals")){
            hisAnkleDetail.setIntervals(intervals);
        }
        if(null != request.getParameter("proofreadingNurseId")){
            hisAnkleDetail.setProofreadingNurseId(proofreadingNurseId);
        }
        if(null != request.getParameter("approvedNurseId")){
            hisAnkleDetail.setApprovedNurseId(approvedNurseId);
        }
        if(null != request.getParameter("isStop")){
            hisAnkleDetail.setIsStop(isStop);
        }
        if(null != request.getParameter("stopDate")){
            hisAnkleDetail.setStopDate(stopDate);
        }
        if(null != request.getParameter("stopUserId")){
            hisAnkleDetail.setStopUserId(stopUserId);
        }
        if(null != request.getParameter("stopPrfingNurseId")){
            hisAnkleDetail.setStopPrfingNurseId(stopPrfingNurseId);
        }
        if(null != request.getParameter("stopApdNurseId")){
            hisAnkleDetail.setStopApdNurseId(stopApdNurseId);
        }
        if(null != request.getParameter("totalAmount")){
            hisAnkleDetail.setTotalAmount(totalAmount);
        }
        if(null != request.getParameter("orderNum")){
            hisAnkleDetail.setOrderNum(orderNum);
        }
        if(null != request.getParameter("isProofreading")){
            hisAnkleDetail.setIsProofreading(isProofreading);
        }
        if(null != request.getParameter("isApproved")){
            hisAnkleDetail.setIsApproved(isApproved);
        }
        return  hisAnkleDetailService.saveOrUpdate(hisAnkleDetail);
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
            HisAnkleDetail hisAnkleDetail = new HisAnkleDetail();
            List<HisAnkleTemplate> hisAnkleTemplateList = hisAnkleTemplateService.selectAllTemplate(templateNumber);
            for (int i = 0;i<hisAnkleTemplateList.size();++i){
                hisAnkleDetail.setOrderNum(1);
                hisAnkleDetail.setNumber(number);
                hisAnkleDetail.setIsApproved(2);
                hisAnkleDetail.setIsProofreading(2);
                hisAnkleDetail.setName(hisAnkleTemplateList.get(i).getName());
                hisAnkleDetail.setTotalAmount(hisAnkleTemplateList.get(i).getTotalAmount());
                hisAnkleDetail.setIntervals(hisAnkleTemplateList.get(i).getIntervals());
                hisAnkleDetail.setRemarks(hisAnkleTemplateList.get(i).getRemarks());
                hisAnkleDetail.setDosage(hisAnkleTemplateList.get(i).getDosage());
                hisAnkleDetail.setIsSkinTest(hisAnkleTemplateList.get(i).getIsSkinTest());
                hisAnkleDetail.setUnit(hisAnkleTemplateList.get(i).getUnit());
                hisAnkleDetail.setUsages(hisAnkleTemplateList.get(i).getUsages());
                hisAnkleDetail.setSpecification(hisAnkleTemplateList.get(i).getSpecification());

                hisAnkleDetailService.saveOrUpdate(hisAnkleDetail);
            }
            return MessageUtil.createMessage(true,"添加成功");

        }else  return MessageUtil.createMessage(false,"参数异常");
    }


    /**
     *@Description delete
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/24
     *@Time 9:48
    */
    @RequestMapping(value = "delete.ahsj")
    @ResponseBody
    public Message delete (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
            , @RequestParam(value="recordId", required=true) String recordId
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisAnkleDetailService.delete(ids,recordId);
        }else  return MessageUtil.createMessage(false,"参数异常");
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
//            , @RequestParam(value="recordId", required=true) String recordId
//            , @RequestParam(value="userId", required=true) Long userId
    ) throws Exception {
        if(null != request.getParameter("ids")){
            Long nurseId = getId();
            return  hisAnkleDetailService.proofreading(ids,nurseId);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }

    @RequestMapping(value = "isApproved.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message isApproved (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
//            , @RequestParam(value="recordId", required=true) String recordId
//            , @RequestParam(value="userId", required=true) Long userId
    ) throws Exception {
        if(null != request.getParameter("ids")){
            Long nurseId = getId();
            return  hisAnkleDetailService.isApproved(ids,nurseId);

        }else  return MessageUtil.createMessage(false,"参数异常");
    }
    
    /**
     *@Description 停嘱校对和核准  stopProofreading  stopApproved
     *@Params 
     *@return 
     *@Author jin
     *@Date 2019/7/29
     *@Time 0:28
    */
    @RequestMapping(value = "stopProofreading.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message stopProofreading (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
//            , @RequestParam(value="recordId", required=true) String recordId
//            , @RequestParam(value="userId", required=true) Long userId
    ) throws Exception {
        if(null != request.getParameter("ids")){

            Long nurseId = getId();
            return  hisAnkleDetailService.stopProofreading(ids,nurseId);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }


    @RequestMapping(value = "stopApproved.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message stopApproved (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
//            , @RequestParam(value="recordId", required=true) String recordId
//            , @RequestParam(value="userId", required=true) Long userId
    ) throws Exception {
        if(null != request.getParameter("ids")){
            Long nurseId = getId();
            return  hisAnkleDetailService.stopApproved(ids,nurseId);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }

    /**
     *@Description 停嘱
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/21
     *@Time 22:02
    */
    @RequestMapping(value = "stop.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message stop (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
//            , @RequestParam(value="recordId", required=true) String recordId
//            , @RequestParam(value="userId", required=true) Long userId
    ) throws Exception {
        if(null != request.getParameter("ids")){

//            Long nurseId = getId();
            return  hisAnkleDetailService.stop(ids);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }


    /**
     *@Description 医嘱校对核准
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/21
     *@Time 22:53
    */
    @RequestMapping(value = "stopProofreadingD.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message stopProofreadingD (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
//            , @RequestParam(value="recordId", required=true) String recordId
//            , @RequestParam(value="userId", required=true) Long userId
    ) throws Exception {
        if(null != request.getParameter("ids")){

            Long nurseId = getId();
            return  hisAnkleDetailService.stopProofreadingD(ids,nurseId);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }


    @RequestMapping(value = "stopApprovedD.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message stopApprovedD (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
//            , @RequestParam(value="recordId", required=true) String recordId
//            , @RequestParam(value="userId", required=true) Long userId
    ) throws Exception {
        if(null != request.getParameter("ids")){
            Long nurseId = getId();
            return  hisAnkleDetailService.stopApprovedD(ids,nurseId);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }
    @RequestMapping(value = "proofreadingD.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message proofreadingD (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
//            , @RequestParam(value="recordId", required=true) String recordId
//            , @RequestParam(value="userId", required=true) Long userId
    ) throws Exception {
        if(null != request.getParameter("ids")){
            Long nurseId = getId();
            return  hisAnkleDetailService.proofreadingD(ids,nurseId);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }

    @RequestMapping(value = "isApprovedD.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message isApprovedD (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
//            , @RequestParam(value="recordId", required=true) String recordId
//            , @RequestParam(value="userId", required=true) Long userId
    ) throws Exception {
        if(null != request.getParameter("ids")){
            Long nurseId = getId();
            return  hisAnkleDetailService.isApprovedD(ids,nurseId);

        }else  return MessageUtil.createMessage(false,"参数异常");
    }
}
