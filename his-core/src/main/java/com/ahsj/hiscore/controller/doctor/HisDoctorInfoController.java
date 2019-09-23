package com.ahsj.hiscore.controller.doctor;


import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.utils.*;
import com.ahsj.hiscore.controller.BaseMedicineController;
import com.ahsj.hiscore.dao.HisDoctorInfoMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.dto.ExcelUploadFile;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.services.*;
import com.alibaba.fastjson.JSON;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctor/")
public class HisDoctorInfoController extends BaseMedicineController {

    @Autowired
    HisDoctorInfoService hisDoctorInfoService;

    @Autowired
    HisMedicalOrderService hisMedicalOrderService;

    @Autowired
    HisAnkleService hisAnkleService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisRegisteredService hisRegisteredService;

    @Autowired
    HisPatientService hisPatientService;

    @Autowired
    IOrganizationService iOrganizationService;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    HisImportInfoService hisImportInfoService;

    @Autowired
    HisDoctorInfoMapper hisDoctorInfoMapper;

    @Autowired
    HisBedService hisBedService;



    private Logger logger = LoggerFactory.getLogger(HisDoctorInfoController.class.getSimpleName());

    /**
     * @Description 新增或更新
     * @Author   muxu
     * @Date 2019/6/20
     * @Time 18:58
     * @Return core.message.Message
     * @Params [model, request, hisDoctorInfo]
    **/
    @RequestMapping(value = "saveOrUpdate.ahsj", method = {RequestMethod.POST})
    public Message saveOrUpdate (Map<String, Object> model, HisDoctorInfo hisDoctorInfo,HttpServletRequest request) throws Exception {
        return  hisDoctorInfoService.saveOrUpdate(hisDoctorInfo);
    }

    /**
     * @Description 删除
     * @Author   muxu
     * @Date 2019/6/20
     * @Time 18:58
     * @Return core.message.Message
     * @Params [model, request, ids]
    **/
    //@ApiOperation(value = "删除")
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    public Message delete (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisDoctorInfoService.delete(ids);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }

    /**
     * @Description excel导入
     * @Author  muxu
     * @Date  2019/9/3
     * @Time 9:18
     * @Return java.lang.String
     * @Params [excelFile, request]
    **/
    @PostMapping("/importExcel.ahsj")
    @ResponseBody
    public String readExcel(@RequestParam(value = "excelFile") MultipartFile excelFile, HttpServletRequest request) throws Exception {
        List<String> errorFilelist = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        SimpleDateFormat formatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        String dateOne = formatOne.format(new Date());
        String voucher = "ERROR"+date;
        String filename = excelFile.getOriginalFilename();
        List<HisImportInfo> importInfoList = new ArrayList<>();
        List<HisDoctorInfo> doctorInfoList = ExcelUtils.readExcel("", HisDoctorInfo.class, excelFile);
        //判断项目名是否为空
        for (int i = 0; i < doctorInfoList.size(); i++) {
            if (EmptyUtil.Companion.isNullOrEmpty(doctorInfoList.get(i).getIdcard())){
                HisImportInfo hisImportInfo = new HisImportInfo();
                String errors = dateOne + " : 第"+(i+1)+"条记录的身份证号存在空值";
                doctorInfoList.get(i).setIdcard(Constants.HIS_IDCARD);
                errorFilelist.add(errors);
                hisImportInfo.setUploadErrorInfo(errors);
                hisImportInfo.setUploadFileName(filename);
                hisImportInfo.setVoucher(voucher);
                importInfoList.add(hisImportInfo);
            }
        }
        List<HisDoctorInfo> list = new ArrayList<>();
        Integer errorNum = 0;
        List<String> collect = doctorInfoList.stream().map(HisDoctorInfo::getIdcard).collect(Collectors.toList());
        //记录重复的日志记录
        CheckRepeatUtils checkRepeatUtils = new CheckRepeatUtils();
        Map<String, List<Integer>> allRepeatedElementsString = checkRepeatUtils.findAllRepeatedElementsString(collect);
        if (!EmptyUtil.Companion.isNullOrEmpty(allRepeatedElementsString)) {
            for (Map.Entry<String, List<Integer>> errorInfo : allRepeatedElementsString.entrySet()) {
                HisImportInfo hisImportInfo = new HisImportInfo();
                String key = errorInfo.getKey();
                List<Integer> errorRepeaNum = new ArrayList<>();
                List<Integer> values = errorInfo.getValue();
                for (Integer value : values) {
                    errorRepeaNum.add(value + 1);
                }
                String errors = null;
                if (StringUtils.equals(key,Constants.HIS_IDCARD)){
                    errors = dateOne + " : 医生信息存在重复 , " + "具体在 " + errorRepeaNum + "条";
                }
                if (!StringUtils.equals(key,Constants.HIS_IDCARD)){
                   errors = dateOne + " : 医生名为 : " + key + " 重复 , " + "具体在 " + errorRepeaNum + "条";
                }

                errorFilelist.add(errors);
                hisImportInfo.setUploadErrorInfo(errors);
                hisImportInfo.setUploadFileName(filename);
                hisImportInfo.setVoucher(voucher);
                importInfoList.add(hisImportInfo);
            }
        }

        //根据身份证号去重
        List<HisDoctorInfo> arrayList = doctorInfoList.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(e -> e.getIdcard()))), ArrayList::new)
        );
        //验证数据输入是否合法，并记录日志
        for (HisDoctorInfo doctorInfo : arrayList) {
            if (checkHisProject(doctorInfo)) {
                String errors = dateOne + " : 医生姓名为 : " + doctorInfo.getName() + "的那一条记录信息存在没填写数据";
                errorFilelist.add(errors);
                HisImportInfo hisImportInfo = new HisImportInfo();
                hisImportInfo.setUploadErrorInfo(errors);
                hisImportInfo.setUploadFileName(filename);
                hisImportInfo.setVoucher(voucher);
                importInfoList.add(hisImportInfo);
                errorNum++;
            } else {

                //部门
                Organization organization = new Organization();
                organization.setName(doctorInfo.getDeptName());
                organization = iOrganizationService.getOrganizationName(organization);
                //字典
                SysCodeDetail sysCodeDetail = new SysCodeDetail();
                sysCodeDetail.setValue(doctorInfo.getSexName());
//                sysCodeDetail = iCodeService.getSysCodeName(sysCodeDetail);
                if (EmptyUtil.Companion.isNullOrEmpty(organization.getId()) || EmptyUtil.Companion.isNullOrEmpty(iCodeService.getSysCodeName(sysCodeDetail).getId())) {
                    String errors = null;
                    if (StringUtils.equals(doctorInfo.getIdcard(),Constants.HIS_IDCARD)){
                        errors = dateOne + " : 身份证号为空的这一条记录信息部门和类型信息填写有误";
                    }
                    if (!StringUtils.equals(doctorInfo.getIdcard(),Constants.HIS_IDCARD)){

                         errors = dateOne + " : 身份证号为 : " + doctorInfo.getIdcard() + "的那一条记录信息部门和类型信息填写有误";
                    }

                    errorFilelist.add(errors);
                    HisImportInfo hisImportInfo = new HisImportInfo();
                    hisImportInfo.setUploadErrorInfo(errors);
                    hisImportInfo.setUploadFileName(filename);
                    hisImportInfo.setVoucher(voucher);
                    importInfoList.add(hisImportInfo);
                    errorNum++;
                    continue;
                } else {
                    list.add(doctorInfo);
                }
            }
        }
        List<HisDoctorInfo> hisDoctorInfoList1 = hisDoctorInfoMapper.queryDoctorAll();//数据库的所有病人信息
        List<HisDoctorInfo> successList = new ArrayList<>(); //可以导入的信息
        HashMap<String, HisDoctorInfo> StringMap = new HashMap<String, HisDoctorInfo>();
        hisDoctorInfoList1.stream().forEach(e -> StringMap.put(e.getIdcard(), e));  //将需要导入的添加到IntegerMap集合,身份证号为key
        list.stream().filter(f -> !StringMap.containsKey(f.getIdcard())).forEach(e -> successList.add(e));  //身份证号不同的添加到successList
        List<HisDoctorInfo> collect1 = list.stream().filter(f -> StringMap.containsKey(f.getIdcard())).collect(Collectors.toList());//与数据库相同的元素
        for (HisDoctorInfo hisDoctorInfo : collect1) {
            String errors = dateOne + " : 身份证号为 : " + hisDoctorInfo.getIdcard() + "已存在";
            logger.info("---------已存在的身份证号为" + hisDoctorInfo.getIdcard() + "------------");
            errorFilelist.add(errors);
            HisImportInfo hisImportInfo = new HisImportInfo();
            hisImportInfo.setUploadErrorInfo(errors);
            hisImportInfo.setUploadFileName(filename);
            hisImportInfo.setVoucher(voucher);
            importInfoList.add(hisImportInfo);
            errorNum++;
        }
        if (!CollectionUtils.isEmpty(successList)) {
            List<HisDoctorInfo> lists =  successList.stream().filter(e -> !StringUtils.equals(e.getIdcard(), Constants.HIS_IDCARD)).collect(Collectors.toList());
            hisDoctorInfoService.importExcel(lists);
        }
        //将错误日志存库
        File file = new File(Constants.HIS_SYS_EXCEL_DOCTOR_ERROR_FILE_URL);
        hisImportInfoService.inserHisImportInfo(importInfoList);
        System.out.println(importInfoList.size());
        //错误日志集合
        List<String> errorList = importInfoList.stream().map(HisImportInfo::getUploadErrorInfo).collect(Collectors.toList());

        FileUtil.listToFile(errorFilelist, file);

        Result result = new Result();
        ExcelUploadFile excelUploadFile = new ExcelUploadFile();
        excelUploadFile.setFaildNum(doctorInfoList.size());
        excelUploadFile.setSuccessNum(successList.size());
        excelUploadFile.setErrorList(errorList);
        if (!EmptyUtil.Companion.isNullOrEmpty(importInfoList))
        {
            String vouchers= importInfoList.get(0).getVoucher();
        excelUploadFile.setVouchers(vouchers);
        }
        excelUploadFile.setImportListSize(doctorInfoList.size());
        excelUploadFile.setErrorListSize( doctorInfoList.size() - successList.size());
        result.setStatus(200);
        result.setData(excelUploadFile);
        return JsonUtils.serialize(result);



    }
    /**
     * @Description excel导出
     * @Author  muxu
     * @Date  2019/8/28
     * @Time 17:23
     * @Return
     * @Params
    **/
    @GetMapping("/exportExcel.ahsj")
    @ResponseBody
    public void exportExcels(HttpServletRequest request, @RequestParam("param") String param, @RequestParam("idsData") Long[] idsData, HisDoctorInfo hisDoctorInfo,
                             HttpServletResponse response, HttpSession session, String token, String name, String idcard, String numb, Integer sex, Date joinDate,Date joinLowDate,Date leaveDate,Date leaveLowDate) throws Exception {
        if (!StringUtils.isEmpty(name)) {
            hisDoctorInfo.setName(name);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(idcard)) {
            hisDoctorInfo.setIdcard(idcard);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(numb)) {
            hisDoctorInfo.setNumb(numb);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(sex)) {
            hisDoctorInfo.setSex(sex);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(joinDate)) {
            hisDoctorInfo.setJoinDate(joinDate);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(joinLowDate)) {
            hisDoctorInfo.setJoinLowDate(joinLowDate);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(leaveDate)) {
            hisDoctorInfo.setLeaveDate(leaveDate);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(leaveLowDate)) {
            hisDoctorInfo.setLeaveLowDate(leaveLowDate);
        }
        hisDoctorInfoService.exportExcels(idsData, request, response, param,session, hisDoctorInfo);
    }

    /**
     * @Description list
     * @Author   muxu
     * @Date 2019/6/20
     * @Time 18:59
     * @Return core.entity.PageBean<com.ahsj.hiscore.entity.HisDoctorInfo>
     * @Params [model, request, hisDoctorInfo]
    **/
    //@ApiOperation(value = "分页查询")
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public PageBean<HisDoctorInfo> list (Map<String, Object> model, HttpServletRequest request, HisDoctorInfo hisDoctorInfo) throws Exception{
        PageBean<HisDoctorInfo> pageBean = new PageBean<HisDoctorInfo>();
        pageBean.setParameter(hisDoctorInfo);
        return hisDoctorInfoService.list(pageBean);
    }
    @RequestMapping("/update/index.ahsj")
    ModelAndView UpdateIndex(HisDoctorInfo hisDoctorInfo, String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/doctor/update");
        if(!EmptyUtil.Companion.isNullOrEmpty(hisDoctorInfo.getId())){
            modelAndView.addObject("hisDoctorInfo",hisDoctorInfoService.selectById(hisDoctorInfo.getId()));
        }else{
            modelAndView.addObject("hisDoctorInfo",hisDoctorInfo);
        }
        modelAndView.addObject("title","人员信息添加");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/doctor/list");
//        modelAndView.addObject("userName",getUserName());
        modelAndView.addObject("title","医疗信息系统");
        modelAndView.addObject("token",token);
//        modelAndView.addObject("roleName",getRoleName());
        return modelAndView;
    }

    /**
     * @Description id
     * @Author   dingli
     * @Date 2019/7/9
     * @Time 16:56
     * @Return json
     * @Params [id]
     **/
    @RequestMapping(value = "getDoctor.ahsj", method = {RequestMethod.GET})
    @ResponseBody
    public String  getDoctor(Long departmentId,String token) throws Exception{
        List<HisDoctorInfo> hisDoctorInfo = hisDoctorInfoService.getHisDoctorInfoBydepartmentId(departmentId);
        return  JSON.toJSONString(hisDoctorInfo);
    }

    /**
     *@Description 住院病人管理首页跳转
     *@Params [token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-15
     *@Time 11:25
    **/
    @RequestMapping("/listForInpatient/index.ahsj")
    ModelAndView listForInpatient(String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/doctor/listForInpatient");
//        modelAndView.addObject("userName",getUserName());
        modelAndView.addObject("title","住院病人管理");
        modelAndView.addObject("token",token);
//        modelAndView.addObject("roleName",getRoleName());
        return modelAndView;
    }

    /**
     *@Description 病人住院信息查看（护嘱查看，医嘱填写）medicalRecordId对应的是就诊记录编号（也就是medical_record表中的medicalRecordId）
     *@Params [token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-16
     *@Time 17:47
    **/
    @RequestMapping("/inpatientInformation/index.ahsj")
    ModelAndView inpatientInformation(String token,
                                      @RequestParam(value = "medicalRecordId",required = true)String medicalRecordId,
                                      @RequestParam(value = "hospitalManageId",required = true)Long hospitalManageId,
                                      @RequestParam(value = "patientName",required = false)String patientName)throws Exception{
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/doctor/inpatientInformation");

        HisHospitalManage hisHospitalManage =hisHospitalManageService.selectByNumber(medicalRecordId);
        modelAndView.addObject("title","住院病人管理");
        modelAndView.addObject("token",token);
        modelAndView.addObject("medicalRecordId",medicalRecordId);
        HisBed hisBed = hisBedService.selectByPrimaryKey(hisHospitalManage.getBedId());
        if(!EmptyUtil.Companion.isNullOrEmpty(hisBed))
            hisHospitalManage.setBedsNumber(hisBed.getNumber());
        modelAndView.addObject("hisHospitalManage",hisHospitalManage);
        if(isSameUser(hisHospitalManage.getDoctorId()) && hisHospitalManage.getIsDischarged() == 1)
            modelAndView.addObject("isSameUser",true);
        else
            modelAndView.addObject("isSameUser",false);
        modelAndView.addObject("patientName",patientName);
//        modelAndView.addObject("roleName",getRoleName()
        return modelAndView;
        
    }

    /**
     *@Description 接诊信息查看+住院开药
     *@Params [token, medicalRecordId, hospitalManageId, patientName]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-20
     *@Time 10:06
    **/
    @RequestMapping("/notInpatientInformation/index.ahsj")
    ModelAndView notInpatientInformation(String token,
                                      @RequestParam(value = "medicalRecordId",required = true)String medicalRecordId,
                                      @RequestParam(value = "isNurseOpreate",required = false)Integer isNurseOpreate,
                                      @RequestParam(value = "patientName",required = false)String patientName)throws Exception {
        //根据就诊编号查询当次就诊记录
        HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(medicalRecordId);
        //根据就诊编号查询出住院信息
        HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByNumber(medicalRecordId);
        boolean isOpreate;//按钮   标记就诊信息是否可操作 true:可操作 false： 不可操作
        if(EmptyUtil.Companion.isNullOrEmpty(hisHospitalManage)){
            isOpreate = false;
        }else {
            if(hisHospitalManage.getIsDischarged() == 1 )
                isOpreate = true;
            else
                isOpreate = false;
        }
        if (isNurseOpreate == 1){
            isOpreate = false;
        }
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/doctor/notInpatientInformation");
        modelAndView.addObject("title", "门诊病历查看");
        modelAndView.addObject("token", token);
        modelAndView.addObject("isOpreate", isOpreate);
        modelAndView.addObject("hisMedicalRecord", hisMedicalRecordService.selectById(hisMedicalRecord.getId()));
        if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalRecord.getRegisteredId())){
            modelAndView.addObject("hisRegistered", new HisRegistered());
        }else {
            modelAndView.addObject("hisRegistered", hisRegisteredService.selectById(hisMedicalRecord.getRegisteredId()));
        }
        modelAndView.addObject("hisPatientInfo", hisPatientService.selectById(hisMedicalRecord.getPatientId()));
        return modelAndView;
    }

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/2
     * @Time 17:41
     * @Return java.lang.Boolean
     * @Params [hisProject]
    **/
    public Boolean checkHisProject(HisDoctorInfo hisDoctorInfo) {
        if (EmptyUtil.Companion.isNullOrEmpty(hisDoctorInfo.getSexName()) || EmptyUtil.Companion.isNullOrEmpty(hisDoctorInfo.getName()) ||
                EmptyUtil.Companion.isNullOrEmpty(hisDoctorInfo.getDeptName()) || EmptyUtil.Companion.isNullOrEmpty(hisDoctorInfo.getIdcard())) {
            return true;
        }
        return false;
    }

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/4
     * @Time 9:50
     * @Return
     * @Params
    **/

    @GetMapping("/error.ahsj")
    @ResponseBody
    public void error(HttpServletRequest request, @RequestParam("vouchers") String vouchers, HttpServletResponse response) throws Exception {
        HisImportInfo hisImportInfo = new HisImportInfo();
        hisImportInfo.setVoucher(vouchers);
        List<HisImportInfo>hisImportInfoList =  hisImportInfoService.queryHisImportInfo(hisImportInfo);
        List<String> collect = hisImportInfoList.stream().map(HisImportInfo::getUploadErrorInfo).collect(Collectors.toList());
        String path = null;
        path = this.getClass().getClassLoader().getResource("templates/errorImport/errorInfo.txt").getPath();
        path = Constants.HIS_SYS_EXCEL_DOCTOR_ERROR_FILE_URL;
        //清空txt文件数据
        FileUtil.clearInfoForFile(path);
        //将错误数据存到txt文件里
        File file = new File(path);
        FileUtil.listToFile(collect, file);
        //导出
        FileUtil.download(response,path);
    }

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/4
     * @Time 9:51
     * @Return
     * @Params
    **/

    @GetMapping("/download.ahsj")
    @ResponseBody
    public void exportExcel(HttpServletResponse response) throws Exception {
        String psth = this.getClass().getClassLoader().getResource("templates/excel/import/hisDoctor.xlsx").getPath();
        psth = Constants.HIS_SYS_EXCEL_DOCTOR_IMPORT_FILE_URL;
        FileUtil.download(response,psth);
    }


    /**
     *@Description 获取医生信息
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/14
     *@Time 16:29
    */
    @RequestMapping(value = "getAllDoctor.ahsj", method = {RequestMethod.GET})
    @ResponseBody
    public List<HisDoctorInfo>  getAllDoctor() throws Exception{
        List<HisDoctorInfo> hisDoctorInfoList = hisDoctorInfoService.getAllDoctor();
        return  hisDoctorInfoList;
    }

}
