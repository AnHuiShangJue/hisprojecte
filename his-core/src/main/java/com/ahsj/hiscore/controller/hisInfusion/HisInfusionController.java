package com.ahsj.hiscore.controller.hisInfusion;

import com.ahsj.hiscore.entity.HisInfusion;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.entity.HisPatientInfo;
import com.ahsj.hiscore.entity.HisPharmacyDetail;
import com.ahsj.hiscore.services.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
@RequestMapping("api/hisInfusion/")
public class HisInfusionController extends BaseController {
    @Autowired
    HisInfusionService hisInfusionService;

    @Autowired
    HisPatientService hisPatientService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisPharmacyDetailService hisPharmacyDetailService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;
    /**
     * @Description 新增更新
     * @Author  muxu
     * @Date  2019/8/28
     * @Time 11:43
     * @Return core.message.Message
     * @Params [model, hisInfusion]
    **/
    @RequestMapping(value = "saveOrUpdate.ahsj", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Message saveOrUpdate(Map<String, Object> model
    ) throws Exception{
        HisInfusion hisInfusion = new HisInfusion();
        return hisInfusionService.saveOrUpdate(hisInfusion);
    }


    /**
     * @Description 门诊输液单 list
     * @Author  muxu
     * @Date  2019/8/28
     * @Time 11:43
     * @Return core.entity.PageBean<com.ahsj.hiscore.entity.HisInfusion>
     * @Params [model, request, hisInfusion]
    **/
    @ResponseBody
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.POST})
    public PageBean<HisInfusion> selectByRecordIdList(Map<String, Object> model, HttpServletRequest request, HisInfusion hisInfusion) throws Exception {
        PageBean<HisInfusion> pageBean = new PageBean<HisInfusion>();
        pageBean.setParameter(hisInfusion);
        return hisInfusionService.selectByRecordIdList(pageBean);
    }

    /**
     * @Description 门诊输液单药品详细
     * @Author  muxu
     * @Date  2019/8/31
     * @Time 16:29
     * @Return core.entity.PageBean<com.ahsj.hiscore.entity.HisInfusion>
     * @Params [model, request, hisInfusion]
    **/
    @ResponseBody
    @RequestMapping(value = "infusionDrugDetailsList.ahsj", method = {RequestMethod.POST})
    public PageBean<HisInfusion> infusionDrugDetails(Map<String, Object> model, HttpServletRequest request, HisInfusion hisInfusion) throws Exception {
        PageBean<HisInfusion> pageBean = new PageBean<HisInfusion>();
        pageBean.setParameter(hisInfusion);
        return hisInfusionService.infusionDrugDetailsList(pageBean);
    }

    /**
     * @Description 删除
     * @Author  muxu
     * @Date  2019/8/28
     * @Time 11:43
     * @Return core.message.Message
     * @Params [model, request, ids]
    **/
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message delete (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisInfusionService.delete(ids);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }

    /**
     * @Description list M&V
     * @Author  muxu
     * @Date  2019/8/28
     * @Time 11:44
     * @Return org.springframework.web.servlet.ModelAndView
     * @Params [token]
    **/
    @RequestMapping("selectByRecordIdListIndex/index.ahsj")
    ModelAndView selectByRecordIdListIndex(String token,Long id,Long patientId,Integer isNurseOpreate) throws Exception{
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/outpatient_infusion_list");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("medicalRecordId", id);
        modelAndView.addObject("patientId", patientId);
        if (EmptyUtil.Companion.isNullOrEmpty(isNurseOpreate)){
            modelAndView.addObject("isOpreate", false);
        }else {
            modelAndView.addObject("isOpreate", true);

        }
        return modelAndView;
    }

    /**
     * @Description 门诊输液单药品详细
     * @Author  muxu
     * @Date  2019/8/31
     * @Time 16:32
     * @Return org.springframework.web.servlet.ModelAndView
     * @Params [token, recordId]
    **/
    @RequestMapping("infusionDrugDetailsList/index.ahsj")
    ModelAndView infusionDrugDetailsListIndex(String token,String recordId,String number,String medicalRecordId) throws Exception{
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/infusion_drug_details");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        modelAndView.addObject("recordId", recordId);
        modelAndView.addObject("medicalRecordId", medicalRecordId);//HM
        modelAndView.addObject("number", number);
        return modelAndView;
    }

    /**
     *@Description 保存输液单save
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/29
     *@Time 11:21
    */
    @RequestMapping(value = "save.ahsj", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Message save(Map<String, Object> model,HttpServletRequest request
            , @RequestParam(value="ids", required=false) Long[] ids    //id
            , @RequestParam(value="medicationIds", required=true) String[] medicationIds  //药品id
            , @RequestParam(value="singledoses", required=false) Integer[] singledoses   //单次用量
            , @RequestParam(value="singleunits", required=false) String[] singleunits   //单次用量
            , @RequestParam(value="units", required=false) String[] units   //单位
            , @RequestParam(value="usages", required=false) String usages  //用法
            , @RequestParam(value="duration", required=false) String duration  //持续时间
            , @RequestParam(value="intervals", required=false) String intervals  //间隔
            , @RequestParam(value="dosages", required=false) String dosages   //总用量
            , @RequestParam(value="isSkinTest", required=false) Integer isSkinTest   //总用量
            , @RequestParam(value="nurseId", required=false) Long nurseId //护士id
            , @RequestParam(value="startTime", required=false) Date startTime //执行时间
            , @RequestParam(value="hospitalManageId", required=false) String hospitalManageId //就诊记录编号
            , @RequestParam(value="recordId", required=false) String recordId
            , @RequestParam(value="number", required=false) String number
            , @RequestParam(value="prices", required=false) BigDecimal prices
            , @RequestParam(value="patientId", required=false) Long patientId
//            , @RequestParam(value="id", required=false) Long id
    ) throws Exception{
        List<HisInfusion> hisInfusionList = new ArrayList<HisInfusion>();
        if (medicationIds.length>0){
            for (int i = 0;i<medicationIds.length;++i){
                HisInfusion hisInfusion = new HisInfusion();
                if(null != request.getParameter("ids")){
                    hisInfusion.setId(ids[i]);
                }
                if(null != request.getParameter("medicationIds")){
                    hisInfusion.setDrugsNumb(medicationIds[i]);

                }
                if(null != request.getParameter("singledoses")){
                    hisInfusion.setSingleDose(singledoses[i]);

                }
                if(null != request.getParameter("singleunits")){
                    hisInfusion.setSingleUnit(singleunits[i]);

                }
                if(null != request.getParameter("units")){
                    hisInfusion.setUnit(units[i]);
                }
                if(null != request.getParameter("usages")){
                    hisInfusion.setUsages(usages);
                }
                if(null != request.getParameter("duration")){
                    hisInfusion.setDuration(duration);
                }
                if(null != request.getParameter("intervals")){
                    hisInfusion.setIntervals(intervals);
                }
                if(null != request.getParameter("dosages")){
                    hisInfusion.setDosage(dosages);
                }
                if(null != request.getParameter("nurseId")){
                    hisInfusion.setNurseId(nurseId);
                }
                if(null != request.getParameter("startTime")){
                    hisInfusion.setStartTime(startTime);
                }
                if(null != request.getParameter("isSkinTest")){
                    hisInfusion.setIsSkinTest(isSkinTest);
                }
                if(null != request.getParameter("hospitalManageId")){
                    hisInfusion.setHosptalregistNumber(hospitalManageId);
                }
                if(null != request.getParameter("recordId")){
                    hisInfusion.setRecordId(recordId);
                }
                if(null != request.getParameter("prices")){
                    hisInfusion.setPrice(prices);
                }
                if(null != request.getParameter("patientId")){
                    hisInfusion.setPatientId(patientId);
                }
                if(null != request.getParameter("number")){
                    hisInfusion.setNumber(number);
                }else {
                    String name="SYD";
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
                    String createdate = sdf.format(date);
                    hisInfusion.setNumber(name + createdate);
                }
                hisInfusion.setType(1);
                hisInfusionList.add(hisInfusion);

            }

            return hisInfusionService.save(hisInfusionList);


        }else {
            return MessageUtil.createMessage(false,"未选择药品");
        }

    }

    /**
     *@Description listByHM
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/30
     *@Time 10:47
    */
    @ResponseBody
    @RequestMapping(value = "listByHM.ahsj", method = {RequestMethod.POST})
    public PageBean<HisInfusion> listByHM(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="hospitalManageId", required=false) String hospitalManageId //就诊记录编号
    ) throws Exception {
        PageBean<HisInfusion> pageBean = new PageBean<HisInfusion>();
        HisInfusion hisInfusion = new HisInfusion();
        if(null != request.getParameter("hospitalManageId")){
            hisInfusion.setHosptalregistNumber(hospitalManageId);
        }
        pageBean.setParameter(hisInfusion);
        return hisInfusionService.listByHM(pageBean);
    }

    /**
     *@Description listAllByNumber
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/2
     *@Time 11:10
    */
    @ResponseBody
    @RequestMapping(value = "listAllByNumber.ahsj", method = {RequestMethod.POST})
    public PageBean<HisInfusion> listAllByNumber(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="hospitalManageId", required=false) String hospitalManageId //输液单
    ) throws Exception {
        PageBean<HisInfusion> pageBean = new PageBean<HisInfusion>();
        HisInfusion hisInfusion = new HisInfusion();
        if(null != request.getParameter("hospitalManageId")){
            hisInfusion.setNumber(hospitalManageId);
        }
        pageBean.setParameter(hisInfusion);
        return hisInfusionService.listAllByNumber(pageBean);
    }

    /**
     *@Description 根据id查询  --infusion表
     *@Params
     *@return
     *@Author jin
     *@Date 2019/10/6
     *@Time 10:28
    */
    @ResponseBody
    @RequestMapping(value = "listByNumbers.ahsj", method = {RequestMethod.POST})
    public List<HisInfusion> listById(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="numbers", required=false) String[] numbers //输液单
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return hisInfusionService.listByNumbers(numbers);
        }else {
            List<HisInfusion> hisInfusionList = new ArrayList<>();
            return hisInfusionList;
        }
    }
    /**
     *@Description 根据分组编号查询
     *@Params
     *@return
     *@Author jin
     *@Date 2019/10/6
     *@Time 16:56
    */
    @ResponseBody
    @RequestMapping(value = "listByRemark.ahsj", method = {RequestMethod.POST})
    public PageBean<HisInfusion> listByRemark(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="remark", required=true) String remark //输液单
    ) throws Exception {
        PageBean<HisInfusion> pageBean = new PageBean<HisInfusion>();
        HisInfusion hisInfusion = new HisInfusion();
        if(null != request.getParameter("remark")){
            hisInfusion.setRemarks(remark);
        }
        pageBean.setParameter(hisInfusion);
        return hisInfusionService.listByRemark(pageBean);
    }

    /**
     *@Description 跳转输液单页面infusion
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/28
     *@Time 11:07
     */
    @RequestMapping(value = "infusionlist/index.ahsj")
    public ModelAndView infusion(String token, String hospitalManage,String patientId,Integer flag) throws Exception {
        //HM开头的编号
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/infusion/list");
        modelAndView.addObject("title", "输液单列表");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hospitalManage",hospitalManage);
        if(!EmptyUtil.Companion.isNullOrEmpty(flag))
            modelAndView.addObject("flag",flag);
        modelAndView.addObject("patientId",patientId);
        return modelAndView;
    }

    /**
     *@Description 跳转到save页面
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/30
     *@Time 14:13
    */
    @RequestMapping(value = "save/index.ahsj")
    public ModelAndView save(String token, String hospitalManageId,Long patientId,String number,Long nurseId,String startTime) throws Exception {
        //HM开头的编号
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/infusion/infusion");
        modelAndView.addObject("token", token);
        modelAndView.addObject("title", "输液单列表");
        modelAndView.addObject("hospitalManage",hospitalManageId);
        modelAndView.addObject("patientId",patientId);
        if (!EmptyUtil.Companion.isNullOrEmpty(number)){
            modelAndView.addObject("number",number);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(nurseId)){
            modelAndView.addObject("nurseId",nurseId);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(startTime)){
            modelAndView.addObject("startTime",startTime);
        }
        return modelAndView;
    }

    /**
     *@Description edit and update
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/30
     *@Time 17:31
    */
    @RequestMapping(value = "details/index.ahsj")
    public ModelAndView edit(String token, String hospitalManageId,String[] number,Long patientId,String startTime,Integer flag) throws Exception {
//        HM开头的编号
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/infusion/infusionDetails");
        modelAndView.addObject("token", token);

        //更新和打印
        modelAndView.addObject("title", "更新输液单");
        modelAndView.addObject("hospitalManage",hospitalManageId);
        modelAndView.addObject("patientId",patientId);
        if(!EmptyUtil.Companion.isNullOrEmpty(flag))
            modelAndView.addObject("flag",flag);
        modelAndView.addObject("startTime",startTime);

        //输液单分组
        HisInfusion hisInfusion = new HisInfusion();
        String name="Group";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String createdate = sdf.format(date);
        for (int i = 0; i < number.length; i++) {
            hisInfusion.setRemarks(name+createdate);
            hisInfusion.setNumber(number[i]);
            hisInfusionService.updateRemarks(hisInfusion);
        }

        modelAndView.addObject("remark",name+createdate);
        modelAndView.addObject("number",number[0]);

        return modelAndView;
    }


    /**
     *@Description 跳转打印界面
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/2
     *@Time 14:06
    */
    @RequestMapping(value = "printf/index.ahsj")
    public ModelAndView printf(String token, String hospitalManageId,String number,String remark) throws Exception {
        //HM开头的编号
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/infusion/newInfusion");
        modelAndView.addObject("token", token);
        //更新和打印
        modelAndView.addObject("hospitalManage",hospitalManageId);
        modelAndView.addObject("number",number);
        modelAndView.addObject("remark",remark);
        modelAndView.addObject("title", "打印输液单");
        List<HisInfusion> hisInfusionList = hisInfusionService.listByHMForPrint(number);

        HisPatientInfo hisPatientInfo = hisPatientService.selectByMedicalRecordId(hospitalManageId);
        if (hisPatientInfo.getSex() == 1){
            modelAndView.addObject("sex", "男");
        }else {
            modelAndView.addObject("sex", "女");

        }

            modelAndView.addObject("hisPatientInfo",hisPatientInfo);
            modelAndView.addObject("hisInfusionList",hisInfusionList);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time =formatter.format(hisInfusionList.get(0).getUpdateDate());
            modelAndView.addObject("time",time);
        return modelAndView;
    }


    /**
     *@Description 住院输液单打印
     *@Params [token, hospitalManageId, number]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-09-25
     *@Time 23:13
    **/
    @RequestMapping(value = "inHospitalInfusion/index.ahsj")
    public ModelAndView inHospitalInfusion(String token, String hospitalManageId,String remark,String number) throws Exception {
        //HM开头的编号
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/infusion/newInfusion");
        modelAndView.addObject("token", token);
        //更新和打印
        modelAndView.addObject("hospitalManage",hospitalManageId);
        modelAndView.addObject("remark",remark);
        modelAndView.addObject("title", "打印输液单");
        List<HisInfusion> hisInfusionList = hisInfusionService.listByHMForHospitalPrint(number);

        HisPatientInfo hisPatientInfo = hisPatientService.selectByMedicalRecordIdForInhospital(hospitalManageId);
        if (EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo.getSex())){
            modelAndView.addObject("sex", "");
        }else {
            if (hisPatientInfo.getSex() == 1){
                modelAndView.addObject("sex", "男");
            }else {
                modelAndView.addObject("sex", "女");

            }
        }


        modelAndView.addObject("hisPatientInfo",hisPatientInfo);
        modelAndView.addObject("hisInfusionList",hisInfusionList);

      /*  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time =formatter.format(hisInfusionList.get(0).getUpdateDate());
        modelAndView.addObject("time",time);*/
        return modelAndView;
    }


    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/3
     * @Time 17:25
     * @Return org.springframework.web.servlet.ModelAndView
     * @Params [token, hospitalManageId, medicalRecordId]
    **/
    @RequestMapping(value = "outpatientprintf/index.ahsj")
    public ModelAndView outpatientprintf(String token,String recordId,String medicalRecordId) throws Exception {
        //HM开头的编号
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/outpatient_infusion_print");
        modelAndView.addObject("token", token);
        //更新和打印
        modelAndView.addObject("title", "打印输液单");
            List<HisInfusion> hisInfusionList = hisInfusionService.listByRecordForPrint(recordId);
            HisPatientInfo hisPatientInfo = hisPatientService.selectByMedicalRecordId(medicalRecordId);
            if (EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo.getSex())){
                modelAndView.addObject("sex", null);
            }else {
                if (hisPatientInfo.getSex() == 1){
                    modelAndView.addObject("sex", "男");
                }else {
                    modelAndView.addObject("sex", "女");

                }
            }


            modelAndView.addObject("recordId",recordId);
            modelAndView.addObject("hisPatientInfo",hisPatientInfo);
            modelAndView.addObject("hisInfusionList",hisInfusionList);
        return modelAndView;
    }

    /**
     *@Description list For Print
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/2
     *@Time 15:57
    */
    @ResponseBody
    @RequestMapping(value = "listByRemarkForPrint.ahsj", method = {RequestMethod.POST})
    public List<HisInfusion> listByHMForPrint(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="remark", required=true) String remark //就诊记录编号
    ) throws Exception {
        return hisInfusionService.listByRemarkForPrint(remark);
    }

    /**
     *@Description
     *@Params [model, request, hospitalManageId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisInfusion>
     *@Author zhushixiang
     *@Date 2019-09-26
     *@Time 0:15
    **/
    @ResponseBody
    @RequestMapping(value = "listByHMForHospitalPrint.ahsj", method = {RequestMethod.POST})
    public List<HisInfusion> listByHMForHospitalPrint(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="hospitalManageId", required=false) String hospitalManageId //就诊记录编号
    ) throws Exception {

        return hisInfusionService.listByHMForHospitalPrint(hospitalManageId);
    }


    @ResponseBody
    @RequestMapping(value = "listByRecordForPrint.ahsj", method = {RequestMethod.POST})
    public List<HisInfusion> listByRecordForPrint(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="recordId", required=false) String recordId //就诊id
    ) throws Exception {

        return hisInfusionService.listByRecordForPrint(recordId);
    }
    
    /**
     *@Description delete by number SYD
     *@Params 
     *@return 
     *@Author jin
     *@Date 2019/9/2
     *@Time 17:24
    */
    @RequestMapping(value = "deleteByNumber.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message deleteByNumber (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="numbers", required=true) String[] numbers
    ) throws Exception {

        if (numbers.length >= 0){
                for (int i = 0; i<numbers.length;++i){
                    hisInfusionService.deleteByNumber(numbers[i]);

                }
                return MessageUtil.createMessage(true,"删除成功");

        }
        else  return MessageUtil.createMessage(false,"参数异常");
    }

    /**
     *@Description 跳转添加输液单界面
     *@Params [token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-10-06
     *@Time 7:43
    **/
    @RequestMapping(value = "addInfusionMedicine/index.ahsj")
    //recordId代表的是就诊记录表的ID
    public ModelAndView addInfusionMedicine(String token,Long recordId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/infusion/addInfusionMedicine");
        modelAndView.addObject("token", token);
        modelAndView.addObject("recordId", recordId);
        return modelAndView;
    }


    
}
