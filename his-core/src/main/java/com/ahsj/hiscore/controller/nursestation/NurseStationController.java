package com.ahsj.hiscore.controller.nursestation;

import com.ahsj.hiscore.controller.BaseMedicineController;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.*;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api/nursestation/")
public class NurseStationController extends BaseMedicineController {

    @Autowired
    HisDoctorInfoService hisDoctorInfoService;

    @Autowired
    HisPatientService hisPatientService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisRegisteredService hisRegisteredService;

    @Autowired
    HisBedService hisBedService;

    @Autowired
    HisNewbornService hisNewbornService;

    @Autowired
    HisInoutTwentyfourHoursService hisInoutTwentyfourHoursService;



    /**
     * @return
     * @Description list页面跳转 M & V
     * @Params
     * @Author jin
     * @Date 2019/7/15
     * @Time 10:45
     */
    @RequestMapping(value = "list/index.ahsj")
    ModelAndView BackenterconsumablesIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/list.html");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return
     * @Description 就诊病历管理 M & V
     * @Params
     * @Author jin
     * @Date 2019/7/15
     * @Time 11:18
     */

    @RequestMapping(value = "manage/index.ahsj")
    ModelAndView ManageIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/manage.html");
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     * @return
     * @Description 病历详情查看 M & V
     * @Params
     * @Author jin
     * @Date 2019/7/16
     * @Time 17:04
     */

    @RequestMapping(value = "details/index.ahsj")
    ModelAndView DetailsIndex(String token, Long patientId) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/historyRecord.html");
        modelAndView.addObject("token", token);
        modelAndView.addObject("title", "历史病历记录");
        //传入记录的id
        modelAndView.addObject("patientId", patientId);
        return modelAndView;
    }

    /**
     * @return
     * @Description 当次对应病历查看 M&V
     * @Params
     * @Author jin
     * @Date 2019/7/16
     * @Time 17:53
     */
    @RequestMapping(value = "onerecorddetails/index.ahsj")
    ModelAndView OneRecordDetails(String token, String hosNumber, String hospitalManageId, String medicalRecordId, Long nurseId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/oneRecordDetails.html");
        modelAndView.addObject("token", token);
        modelAndView.addObject("title", "当次对应病历查看");
//        HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByNumber(medicalRecordId);
//        modelAndView.addObject("hisPatientInfo", hisPatientService.selectById(hisHospitalManage.getPatientId()));
//        modelAndView.addObject("hisDoctorInfo", hisDoctorInfoService.selectById(hisHospitalManage.getDoctorId()));
        //传入记录的id
//        modelAndView.addObject("recordId",recordId);
        modelAndView.addObject("medicalRecordId", medicalRecordId);
        modelAndView.addObject("hisHospitalManage", hisHospitalManageService.selectByNumber(medicalRecordId));
        modelAndView.addObject("hosNumber", hosNumber);
        modelAndView.addObject("hospitalManageId", hospitalManageId);
        modelAndView.addObject("isSameNurse", isSameUser(nurseId));

        modelAndView.addObject("nowUserId", getId());

        modelAndView.addObject("nurseId", nurseId);

        return modelAndView;
    }


    /**
     * @return
     * @Description 当此对应护理记录查看 页面跳转
     * @Params
     * @Author jin
     * @Date 2019/7/29
     * @Time 17:58
     */
    @RequestMapping(value = "ankleRecord/index.ahsj")
    ModelAndView ankleRecord(String token, String hosNumber, String hospitalManageId, String medicalRecordId, Long nurseId) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/ankleRecord/list.html");
        modelAndView.addObject("token", token);
        modelAndView.addObject("title", "当次护理记录查看");
        modelAndView.addObject("medicalRecordId", medicalRecordId);//就诊记录编号
        modelAndView.addObject("hosNumber", hosNumber);//住院编号
        modelAndView.addObject("hospitalManageId", hospitalManageId);
        modelAndView.addObject("isSameNurse", isSameUser(nurseId));

        modelAndView.addObject("nowUserId", getId());

        modelAndView.addObject("nurseId", nurseId);

        return modelAndView;
    }

    /**
     * @return
     * @Description 模板列表页面
     * @Params
     * @Author jin
     * @Date 2019/7/22
     * @Time 14:12
     */

    @RequestMapping(value = "template/index.ahsj")
    ModelAndView templateIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/template.html");
        modelAndView.addObject("token", token);
        modelAndView.addObject("title", "护嘱模板管理");
        return modelAndView;
    }

    /**
     * @return
     * @Description 查看当前住院病人的收费详情
     * @Params
     * @Author jin
     * @Date 2019/7/25
     * @Time 14:51
     */

    @RequestMapping(value = "hosToll/index.ahsj")
    public ModelAndView hosIndex(String token, String medicalNumber, BigDecimal depositWarning) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/listHospital");
        modelAndView.addObject("title", "收费明细");
        modelAndView.addObject("token", token);
        modelAndView.addObject("loginName", getUserName());
        modelAndView.addObject("depositWarning", depositWarning);
        if (!EmptyUtil.Companion.isNullOrEmpty(medicalNumber)) {
            modelAndView.addObject("medicalNumber", medicalNumber);
        }
        return modelAndView;
    }

    /**
     * @return
     * @Description 跳转打印催款单页面
     * @Params
     * @Author jin
     * @Date 2019/8/5
     * @Time 13:28
     */
    @RequestMapping(value = "reminder/index.ahsj")
    public ModelAndView reminder(String token, String MedicalRecordId, BigDecimal money, BigDecimal restDeposit, String patientName ,BigDecimal depositWarning) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/reminder");
        modelAndView.addObject("title", "打印催款单");
        modelAndView.addObject("token", token);
        modelAndView.addObject("MedicalRecordId", MedicalRecordId);//住院编号
        modelAndView.addObject("money", money);//费用
        modelAndView.addObject("restDeposit", restDeposit.abs());//余额
        modelAndView.addObject("patientName", patientName);//名字
        modelAndView.addObject("depositWarning", depositWarning);//最高欠款额度

        //余额减收费的钱
        BigDecimal a = restDeposit.subtract(money);
        //考虑到欠款额度--------应该缴费的钱
        BigDecimal b = a.add(depositWarning);

        modelAndView.addObject("shouldPay", b.abs());//应缴费用
        modelAndView.addObject("operator", getUserName());//操作员

        String createdate = new SimpleDateFormat("yyyyMMddhhmmsszzz").format(new Date());//当前时间年月日
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());//当前时间年月日
        modelAndView.addObject("number", "JFD" + createdate);//缴费单编号
        modelAndView.addObject("date", date);

        return modelAndView;
    }

    /**
     *@Description 跳转到申请耗材页面
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/7
     *@Time 9:59
     */
  /*  @RequestMapping(value = "consumables/index.ahsj")
    public ModelAndView consumables(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumablesDetails/consumablesDetails");
        modelAndView.addObject("title", "申请耗材");
        modelAndView.addObject("token", token);

        return modelAndView;
    }*/

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 跳转到申请耗材页面
     * @Params [token, medicalRecordId, patientName]
     * @Author XJP
     * @Date 2019/8/7
     * @Time 10:43
     **/
    @RequestMapping("consumables/index.ahsj")
    public ModelAndView notInpatientInformation(String token,
                                                @RequestParam("medicalRecordId") String medicalRecordId, @RequestParam("id") Long id
    ) throws Exception {
        HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(medicalRecordId);
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumablesDetails/consumablesDetails");
        modelAndView.addObject("title", "申请耗材");
        modelAndView.addObject("token", token);
        modelAndView.addObject("id", id);
        modelAndView.addObject("medicalRecordId", medicalRecordId);
        modelAndView.addObject("hisMedicalRecord", hisMedicalRecordService.selectById(hisMedicalRecord.getId()));
        if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalRecord.getRegisteredId())) {
            modelAndView.addObject("hisRegistered", new HisRegistered());
        } else {
            modelAndView.addObject("hisRegistered", hisRegisteredService.selectById(hisMedicalRecord.getRegisteredId()));

        }
        modelAndView.addObject("hisPatientInfo", hisPatientService.selectById(hisMedicalRecord.getPatientId()));
        return modelAndView;
    }

    /**
     * @param token
     * @param medicalNumber
     * @Description 住院病床使用情况
     * @Author: dingli
     * @return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/8/13
     * @Time 17:02
     **/
    @RequestMapping(value = "useInpatientBeds/index.ahsj")
    public ModelAndView useInpatientBeds(String token, String medicalNumber) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/useInpatientBeds");
        modelAndView.addObject("title", "住院病床使用情况");
        modelAndView.addObject("countUseBed", hisHospitalManageService.countUseBed());
        modelAndView.addObject("countBed", hisBedService.countBed());
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @param
     * @Description 获取所有的病床信息
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisHospitalManage>
     * @Date 2019/8/14
     * @Time 9:30
     **/
    @PostMapping("getBedAll/index.ahsj")
    @ResponseBody
    public List<HisHospitalManage> getBedAll(HisHospitalManage hisHospitalManage) throws Exception {
        List<HisHospitalManage> hisHospitalManageAll = hisHospitalManageService.getHisHospitalManageAll(hisHospitalManage);
        return hisHospitalManageAll;
    }

    /**
     * @param number
     * @Description 根据病床号获取住院信息
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisHospitalManage
     * @Date 2019/8/14
     * @Time 9:42
     **/
    @RequestMapping(value = "getBedByNumber/index.ahsj")
    @ResponseBody
    public HisHospitalManage getBedByNumber(String number) throws Exception {
        return hisHospitalManageService.getHisHospitalManageByNumber(number);
    }


    /**
     * @return
     * @Description 新生儿
     * @Params
     * @Author jin
     * @Date 2019/9/10
     * @Time 11:21
     */
    @RequestMapping(value = "newBorn/index.ahsj")
    public ModelAndView newBorn(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/newborn");
        modelAndView.addObject("title", "新生儿");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return
     * @Description 跳转新生儿编辑页面
     * @Params
     * @Author jin
     * @Date 2019/9/10
     * @Time 15:39
     */
    @RequestMapping(value = "newBornEdit/index.ahsj")
    public ModelAndView newBornEdit(String token, Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/newborn-saveOrupdate");
        modelAndView.addObject("title", "新生儿");
        modelAndView.addObject("token", token);
        HisNewborn hisNewborn = new HisNewborn();
        if (EmptyUtil.Companion.isNullOrEmpty(id)) {
            modelAndView.addObject("hisNewborn", hisNewborn);
            modelAndView.addObject("one", 0);
            return modelAndView;
        } else {
            hisNewborn = hisNewbornService.selectByPrimaryKey(id);
            hisNewborn.setHidden(1);
            hisNewborn.setParam(2);
            modelAndView.addObject("hisNewborn", hisNewborn);
            modelAndView.addObject("one", 1);
            return modelAndView;
        }
    }

    @RequestMapping(value = "newBornEdit/update/index.ahsj")
    public ModelAndView updateNewBornEdit(String token, Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/newborn-saveOrupdate");
        modelAndView.addObject("title", "新生儿");
        modelAndView.addObject("token", token);
        HisNewborn hisNewborn = new HisNewborn();
        hisNewborn = hisNewbornService.selectByPrimaryKey(id);
        hisNewborn.setParam(2);
        modelAndView.addObject("hisNewborn", hisNewborn);
        modelAndView.addObject("one", 1);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明
     * @Params [token, id]
     * @Author XJP
     * @Date 2019/9/11
     * @Time 17:41
     **/
    @RequestMapping(value = "newBornEdit/print.ahsj")
    public ModelAndView print(String token, Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/print-born");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        HisNewborn hisNewborn = hisNewbornService.selectByPrimaryKey(id);
        hisNewborn.setSexName(hisNewborn.getBornSex() == 1 ? "男" : "女");
        modelAndView.addObject("hisNewborn", hisNewborn);
        modelAndView.addObject("createDate", simpleDateFormat.format(hisNewborn.getCreateDate()));
        return modelAndView;
    }

    /**
     * @return
     * @Description 跳转编辑病人住院信息页面
     * @Params
     * @Author jin
     * @Date 2019/9/10
     * @Time 17:26
     */
    @RequestMapping(value = "inpatientUpdate/index.ahsj")
    public ModelAndView inpatientUpdate(String token, Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hospitalmanage/inpatient_update");
        modelAndView.addObject("title", "病人住院信息编辑");
        modelAndView.addObject("token", token);
        if (!EmptyUtil.Companion.isNullOrEmpty(id)) {
            HisHospitalManage hospitalManage = hisHospitalManageService.selectById(id);
            modelAndView.addObject("hisHospitalManage", hospitalManage);
        }
        return modelAndView;
    }

    //跳转到24小时内出入院记录填写页面
    @RequestMapping(value = "inOut24hour/index.ahsj")
    public ModelAndView inOut24hour(String token, Long patientId, String hospitalnumber, Long doctorId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/inOut24hour");
        modelAndView.addObject("title", "24小时内出入院记录");
        modelAndView.addObject("token", token);
        modelAndView.addObject("doctorId", doctorId);
        System.out.println("=============doctorId===============" + doctorId);

        modelAndView.addObject("userId", getId());
        modelAndView.addObject("hospitalnumber", hospitalnumber);
        if (!EmptyUtil.Companion.isNullOrEmpty(patientId)) {
            HisPatientInfo hisPatientInfo = hisPatientService.selectById(patientId);
            modelAndView.addObject("hisPatientInfo", hisPatientInfo);

        }

        return modelAndView;
    }

    /**
     * @return
     * @Description 保存24小时内出入院记录
     * @Params
     * @Author jin
     * @Date 2019/9/14
     * @Time 16:05
     */
    @RequestMapping(value = "inOut24hourSave.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message inOut24hourSave(Map<String, Object> model, HttpServletRequest request, HisInoutTwentyfourHours hisInoutTwentyfourHours
//            , @RequestParam(value="id", required=false) Long id
            , @RequestParam(value = "patientId", required = false) Long patientId
            , @RequestParam(value = "doctorId", required = false) Long doctorId
//            , @RequestParam(value="name", required=false) String name
//            , @RequestParam(value="type", required=false) Integer type
//            , @RequestParam(value="spec", required=false) String spec
//            , @RequestParam(value="stock", required=false) Integer stock
//            , @RequestParam(value="price", required=false) Double price
//            , @RequestParam(value="isEnable", required=false) Integer isEnable
    ) throws Exception {
        hisInoutTwentyfourHours.setDoctorid(doctorId);
        hisInoutTwentyfourHours.setPatientId(patientId);
        Message aa = hisInoutTwentyfourHoursService.save(hisInoutTwentyfourHours);

        return aa;
    }


    /**
     * @Description 入院护理评估
     * @Params: [token, number]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/9/15
     * @Time 15:01
     **/
    @RequestMapping(value = "care/index.ahsj")
    public ModelAndView care(String token, String number) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hospitalmanage/hospitalized_record");
        modelAndView.addObject("title", "日常病程");
        modelAndView.addObject("loginName", getUserName());
        modelAndView.addObject("token", token);
        modelAndView.addObject("id", 1L);
        modelAndView.addObject("number", number);
        return modelAndView;
    }


    /**
     *@Description 跳转到护理记录页面
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/19
     *@Time 10:15
    */
    @RequestMapping(value = "nursingRecord/index.ahsj")
    public ModelAndView nursingRecord(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/nursestation/nursingRecord");
        modelAndView.addObject("title", "护理记录");
        modelAndView.addObject("token", token);
        return modelAndView;
    }



}
