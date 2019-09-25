package com.ahsj.hiscore.controller.patient;


import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.utils.*;
import com.ahsj.hiscore.dao.HisPatientInfoMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.dto.ExcelUploadFile;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.*;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;
import utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/patient/")
public class HisPatientController extends BaseController {


    @Autowired
    HisPatientService hisPatientService;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    HisImportInfoService hisImportInfoService;

    @Autowired
    HisPatientInfoMapper hisPatientInfoMapper;

    @Autowired
    HisRegisteredService hisRegisteredService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisMedicalService hisMedicalService;

    @Autowired
    ITranslateService iTranslateService;



    private Logger logger = LoggerFactory.getLogger(HisPatientController.class.getSimpleName());

    /**
     * @return core.message.Message
     * @Description 新增更新
     * @Params [model, hisPatientInfo]
     * @Author zhushixiang
     * @Date 2019-06-21
     * @Time 19:13
     **/
//    @ApiOperation(value = "新增更新")
    @RequestMapping(value = "saveOrUpdate.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Message saveOrUpdate(Map<String, Object> model, HisPatientInfo hisPatientInfo) throws Exception {
        HisPatientInfo patientEntity = hisPatientInfo.toPatientEntity();
        return hisPatientService.saveOrUpdate(patientEntity);
    }


    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisPatientInfo>
     * @Description 分页查询
     * @Params [model, request, hisPatientInfo]
     * @Author zhushixiang
     * @Date 2019-06-21
     * @Time 20:32
     **/
    // @ApiOperation(value = "分页查询")
    @ResponseBody
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<HisPatientInfo> list(Map<String, Object> model, HttpServletRequest request, HisPatientInfo hisPatientInfo) throws Exception {
        PageBean<HisPatientInfo> patientInfoPageBean = new PageBean<HisPatientInfo>();
        patientInfoPageBean.setParameter(hisPatientInfo);
        return hisPatientService.list(patientInfoPageBean);
    }

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [model, request, ids]
     * @Author zhushixiang
     * @Date 2019-06-21
     * @Time 20:34
     **/
    // @ApiOperation(value = "删除")
    @ResponseBody
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    public Message delete(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "ids", required = true) Long[] ids
    ) throws Exception {
        if (null != request.getParameter("ids")) {
            return hisPatientService.delete(ids);
        } else return MessageUtil.createMessage(false, "参数异常");
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 病人信息浏览（前端）
     * @Params [token]
     * @Author zhushixiang
     * @Date 2019-06-21
     * @Time 20:33
     **/
    @RequestMapping("list/index.ahsj")
    ModelAndView listIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/patient/list");
        modelAndView.addObject("title", "病人信息模块");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 病人信息修改
     * @Params [hisPatientInfo, token]
     * @Author zhushixiang
     * @Date 2019-06-22
     * @Time 13:27
     **/
    @RequestMapping("update/index.ahsj")
    ModelAndView UpdateIndex(HisPatientInfo hisPatientInfo, String token, Integer isOpreate) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/patient/update");
        if (!EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo.getId())) {
            modelAndView.addObject("hisPatientInfo", hisPatientService.selectById(hisPatientInfo.getId()));
        } else {
            modelAndView.addObject("hisPatientInfo", hisPatientInfo);
        }
        modelAndView.addObject("title", "病人信息添加");
        modelAndView.addObject("token", token);
        if (EmptyUtil.Companion.isNullOrEmpty(isOpreate)) {
            modelAndView.addObject("isView", true);
            modelAndView.addObject("isBtnView", "display:none");

        }
        return modelAndView;
    }

    /**
     * @return
     * @Description 护士站护士账号跳转页面
     * @Params
     * @Author jin
     * @Date 2019/8/26
     * @Time 18:30
     */
    @RequestMapping("nurseupdate/index.ahsj")
    ModelAndView nurseUpdateIndex(HisPatientInfo hisPatientInfo, String token, Integer isOpreate) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/patient/update");
        if (!EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo.getId())) {
            modelAndView.addObject("hisPatientInfo", hisPatientService.selectById(hisPatientInfo.getId()));
        } else {
            modelAndView.addObject("hisPatientInfo", hisPatientInfo);
        }
        modelAndView.addObject("title", "病人信息添加");
        modelAndView.addObject("token", token);
        if (EmptyUtil.Companion.isNullOrEmpty(isOpreate)) {
            modelAndView.addObject("isView", true);
            modelAndView.addObject("isBtnView", "display:none");

        }
        return modelAndView;
    }

    /**
     * @Description 自动填充病人信息
     * @Author muxu
     * @Date 2019/7/22
     * @Time 11:05
     * @Return com.ahsj.hiscore.entity.HisPatientInfo
     * @Params [medicalRecordId, token]
     **/
    @ResponseBody
    @RequestMapping("getPatient.ahsj")
    public HisPatientInfo getPatient(Map<String, Object> model, HttpServletRequest request,
                                     @RequestParam(value = "medicalRecordId", required = true) String medicalRecordId
    ) throws Exception {
        if (null != request.getParameter("medicalRecordId")) {
            return hisPatientService.selectByMedicalRecordId(medicalRecordId);
        } else {
            return null;
        }
    }


    @RequestMapping("upload/index.ahsj")
    ModelAndView UploadIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/patient/uploads");
        modelAndView.addObject("title", "上传导入excel文件");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @param idcard
     * @param name
     * @param request
     * @param response
     * @param session
     * @Description 病人信息导出
     * @Author: dingli
     * @return: void
     * @Date 2019/8/29
     * @Time 15:56
     **/
    @RequestMapping("exportExcel")
    @ResponseBody
    public void exportExcels(@RequestParam(value = "ids", required = false) Long[] ids, String param, String name, String idcard, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        HisPatientInfo hisPatientInfo = new HisPatientInfo();
        hisPatientInfo.setName(name);
        hisPatientInfo.setIdcard(idcard);
        hisPatientService.exportExcels(ids, param, hisPatientInfo, request, response, session);
    }

    /**
     * @param request
     * @param fileName
     * @Description 导入病人信息
     * @Author: dingli
     * @return: void
     * @Date 2019/8/30
     * @Time 16:29
     **/
    @RequestMapping(value = "importExcel.ahsj", method = RequestMethod.POST)
    @ResponseBody
    public String importExcel(HttpServletRequest request, @RequestParam(value = "excelFile") MultipartFile fileName) throws Exception {
        List<String> errorFilelist = new ArrayList<>();
        SimpleDateFormat formatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String date = format.format(new Date());
        String dateOne = formatOne.format(new Date());
        String voucher = "ERROR" + date;
        String filename = fileName.getOriginalFilename();
        List<HisImportInfo> importInfoList = new ArrayList<>();
        CheckRepeatUtils checkRepeatUtils = new CheckRepeatUtils();
        List<HisPatientInfo> hisPatientInfoAll = ExcelUtils.readExcel("", HisPatientInfo.class, fileName);//获取导入的内容
        for (int i = 0; i < hisPatientInfoAll.size(); i++) {
            if (EmptyUtil.Companion.isNullOrEmpty(hisPatientInfoAll.get(i).getIdcard())) {
                HisImportInfo hisImportInfo = new HisImportInfo();
                String errors = dateOne + " : 第" + (i + 1) + "条记录的身份证号存在空值";
                hisPatientInfoAll.get(i).setIdcard(Constants.HIS_NAME + voucher);
                errorFilelist.add(errors);
                hisImportInfo.setUploadErrorInfo(errors);
                hisImportInfo.setUploadFileName(filename);
                hisImportInfo.setVoucher(voucher);
                importInfoList.add(hisImportInfo);
            }
        }
        List<String> collect = hisPatientInfoAll.stream().map(HisPatientInfo::getIdcard).collect(Collectors.toList());//所有的身份证号集合
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
                String errors = dateOne + " : 身份证号为 : " + key + " 重复 , " + "具体在 " + errorRepeaNum + "条";
                errorFilelist.add(errors);
                hisImportInfo.setUploadErrorInfo(errors);
                hisImportInfo.setUploadFileName(filename);
                hisImportInfo.setVoucher(voucher);
                importInfoList.add(hisImportInfo);
            }
        }
        List<HisPatientInfo> hisPatientInfoList = hisPatientInfoAll.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(HisPatientInfo::getIdcard))), ArrayList::new)
        );//去取身份证号相同的导入信息
        List<HisPatientInfo> HisPatientInfoAdd = new ArrayList<>();  //可以添加的信息
        for (HisPatientInfo hisPatientInfo : hisPatientInfoList) {
            if (EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo.getAge()) || EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo.getHeight())
                    || EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo.getWeight()) || EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo.getPhonenumber())
                    || EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo.getSexName()) || EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo.getMarried())
                    || EmptyUtil.Companion.isNullOrEmpty(hisPatientInfo.getName())
            ) {
                String errors = "";
                if (StringUtil.equals(hisPatientInfo.getIdcard(), Constants.HIS_NAME + voucher)) {
                    errors = dateOne + "身份证号未填写和必填字段未填写或者填写类型有误";
                } else {
                    errors = dateOne + " : 身份证号为 : " + hisPatientInfo.getIdcard() + "的记录信息必填字段未填写或者填写类型有误";
                }
                errorFilelist.add(errors);
                HisImportInfo hisImportInfo = new HisImportInfo();
                hisImportInfo.setUploadErrorInfo(errors);
                hisImportInfo.setUploadFileName(filename);
                hisImportInfo.setVoucher(voucher);
                importInfoList.add(hisImportInfo);
            } else {
                SysCodeDetail marriedS = new SysCodeDetail();
                marriedS.setValue(hisPatientInfo.getMarried());
                marriedS.setType(Constants.HIS_SYS_CODE_IS_MARRIED);
                marriedS = iCodeService.selectSysCode(marriedS);
                SysCodeDetail sexS = new SysCodeDetail();
                sexS.setValue(hisPatientInfo.getSexName());
                sexS.setType(Constants.HIS_SYS_CODE_SEX);
                sexS = iCodeService.selectSysCode(sexS);
                if (EmptyUtil.Companion.isNullOrEmpty(marriedS.getId()) || EmptyUtil.Companion.isNullOrEmpty(sexS.getId())) {
                    String errors = dateOne + " : 身份证号为 : " + hisPatientInfo.getIdcard() + "的那一条记录信息性别或者婚烟状态填写有误";
                    errorFilelist.add(errors);
                    HisImportInfo hisImportInfo = new HisImportInfo();
                    hisImportInfo.setUploadErrorInfo(errors);
                    hisImportInfo.setUploadFileName(filename);
                    hisImportInfo.setVoucher(voucher);
                    importInfoList.add(hisImportInfo);
                    continue;
                } else {
                    HisPatientInfoAdd.add(hisPatientInfo);
                }
            }
        }
        List<HisPatientInfo> hisPatientInfoList1 = hisPatientInfoMapper.hisPatientInfoAll();//数据库的所有病人信息
        List<HisPatientInfo> successList = new ArrayList<>(); //可以导入的信息
        HashMap<String, HisPatientInfo> StringMap = new HashMap<String, HisPatientInfo>();
        hisPatientInfoList1.stream().forEach(e -> StringMap.put(e.getIdcard(), e));  //将需要导入的添加到IntegerMap集合,身份证号为key
        HisPatientInfoAdd.stream().filter(f -> !StringMap.containsKey(f.getIdcard())).forEach(e -> successList.add(e));  //身份证号不同的添加到successList
        List<HisPatientInfo> collect1 = HisPatientInfoAdd.stream().filter(f -> StringMap.containsKey(f.getIdcard())).collect(Collectors.toList());//与数据库相同的元素
        for (HisPatientInfo hisPatientInfo : collect1) {
            String errors = dateOne + " : 身份证号为 : " + hisPatientInfo.getIdcard() + "已存在";
            logger.info("---------已存在的身份证号为" + hisPatientInfo.getIdcard() + "------------");
            errorFilelist.add(errors);
            HisImportInfo hisImportInfo = new HisImportInfo();
            hisImportInfo.setUploadErrorInfo(errors);
            hisImportInfo.setUploadFileName(filename);
            hisImportInfo.setVoucher(voucher);
            importInfoList.add(hisImportInfo);
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        if (!CollectionUtils.isEmpty(successList)) {
            List<HisPatientInfo> collect2 = successList.stream().filter(e -> !StringUtils.equals(e.getIdcard(), Constants.HIS_NAME + voucher)).collect(Collectors.toList());
            map = hisPatientService.importExcel(collect2, filename);
        }
        File file = new File(Constants.HIS_SYS_EXCEL_DOCTOR_ERROR_FILE_URL);
        hisImportInfoService.inserHisImportInfo(importInfoList);
        Result result = new Result();
        ExcelUploadFile excelUploadFile = new ExcelUploadFile();
        if (!EmptyUtil.Companion.isNullOrEmpty(importInfoList)) {
            String vouchers = importInfoList.get(0).getVoucher();
            //错误日志集合
            List<String> errorList = importInfoList.stream().map(HisImportInfo::getUploadErrorInfo).collect(Collectors.toList());
            excelUploadFile.setErrorList(errorList);
            excelUploadFile.setVouchers(vouchers);
            excelUploadFile.setErrorListSize(errorList.size());
        }
        List<String> errorList = importInfoList.stream().map(HisImportInfo::getUploadErrorInfo).collect(Collectors.toList());//错误日志
        FileUtil.listToFile(errorFilelist, file);
        int success = EmptyUtil.Companion.isNullOrEmpty(map.get("success")) ? 0 : map.get("success").intValue();
        int error = hisPatientInfoAll.size() - success;
        logger.info("病人信息导入成功" + success + "条");
        logger.info("病人信息导入失败" + error + "条");
        logger.info(Constants.HIS_NAME + voucher);
        excelUploadFile.setFaildNum(error);
        excelUploadFile.setSuccessNum(success);
        excelUploadFile.setErrorList(errorList);
        excelUploadFile.setImportListSize(hisPatientInfoAll.size());
        excelUploadFile.setErrorListSize(errorList.size());
        result.setStatus(200);
        result.setData(excelUploadFile);
        return JsonUtils.serialize(result);

    }

    /**
     * @param response
     * @Description
     * @Author: dingli
     * @return: void
     * @Date 2019/9/3
     * @Time 17:02
     **/
    @GetMapping("download.ahsj")
    @ResponseBody
    public void exportExcel(HttpServletResponse response) throws Exception {
        String psth = this.getClass().getClassLoader().getResource("templates/excel/import/HisPatientInfo.xlsx").getPath();
        psth = Constants.HIS_SYS_EXCEL_HIS_PATIENT_INFO_IMPORT_FILE_URL;
        FileUtil.download(response, psth);
    }

    @GetMapping("error.ahsj")
    @ResponseBody
    public void error(HttpServletRequest request, @RequestParam("vouchers") String vouchers, HttpServletResponse response) throws Exception {
        HisImportInfo hisImportInfo = new HisImportInfo();
        hisImportInfo.setVoucher(vouchers);
        List<HisImportInfo> hisImportInfoList = hisImportInfoService.queryHisImportInfo(hisImportInfo);
        List<String> collect = hisImportInfoList.stream().map(HisImportInfo::getUploadErrorInfo).collect(Collectors.toList());
        String path = null;
        path = this.getClass().getClassLoader().getResource("templates/errorImport/errorInfo.txt").getPath();
        path = Constants.HIS_SYS_EXCEL_PROJECT_ERROR_FILE_URL;
        //清空txt文件数据
        FileUtil.clearInfoForFile(path);
        //将错误数据存到txt文件里
        File file = new File(path);
        FileUtil.listToFile(collect, file);
        //导出
        FileUtil.download(response, path);
    }

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/9/7
     * @Time 11:42
     **/
    @RequestMapping("print/index.ahsj")
    ModelAndView print(String number,String token)throws Exception{
        //根据挂号编号number 查询出挂号ID
        HisRegistered hisRegistered = hisRegisteredService.selectByNumber(number);
        //根据挂号ID查询出就诊记录ID
        HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByRegisterId(hisRegistered.getId());
        //根据就诊记录ID查询出就诊判断HisMedical这张表
        HisMedical hisMedical = hisMedicalService.selectByMedicalRecordId(hisMedicalRecord.getId());
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedical)){
        Translate check = new Translate();
        check.setTranslateType(Constants.TRANSLATE_HIS_HHISMEDICAL);
        check.setTranslateId(hisMedical.getId());
        String column =null;
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedical.getOther()))
            column = "other";
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedical.getFamilyHistory()))
            column = "familyHistory";
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedical.getAllergies()))
            column = "allergies";
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedical.getPersonalHistory()))
            column = "personalHistory";
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedical.getChiefcomplaint()))
            column = "chiefcomplaint";
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedical.getHiscondition()))
            column = "hiscondition";
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedical.getNowCondition()))
            column = "nowCondition";
        if(!StringUtils.isEmpty(column)) {
            check.setTranslateColumn(column);
            boolean flag = true;//标记变量 标记循环结束
            List<Translate> translateList = new ArrayList<>();
            while (flag) {
                translateList = iTranslateService.queryTranslate(check);
                if( EmptyUtil.Companion.isNullOrEmpty(translateList)|| translateList.size() ==0 )
                    continue;
                if (translateList.size() > 0)
                    flag = false;
            }
        }}
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/print");
        modelAndView.addObject("title", "打印治疗诊疗单");
        modelAndView.addObject("token", token);
        modelAndView.addObject("number", number);
        return modelAndView;
    }
}
