package com.ahsj.hiscore.controller.medicine;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.utils.*;
import com.ahsj.hiscore.entity.HisImportInfo;
import com.ahsj.hiscore.entity.HisMedicineInfo;
import com.ahsj.hiscore.entity.Translate;
import com.ahsj.hiscore.entity.dto.ExcelUploadFile;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisImportInfoService;
import com.ahsj.hiscore.services.HisMedicineInfoService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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

@Controller
@RequestMapping("/api/hismedicineinfo/")
public class HisMedicineInfoController extends BaseController {

    private Logger log = LoggerFactory.getLogger(HisMedicineInfoController.class.getSimpleName());


    @Autowired
    HisMedicineInfoService hisMedicineInfoService;

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    HisImportInfoService hisImportInfoService;

    @Value("${file.word_name}")
    private String wordName;

    /**
     *@Description 新增或更新
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-12
     *@Time 22:12
    **/
    @RequestMapping(value = "saveOrUpdate.ahsj", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Message saveOrUpdate (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="id", required=false) Long id
            , @RequestParam(value="drugsNumb", required=true) String drugsNumb
            , @RequestParam(value="drugsName", required=true) String drugsName
            , @RequestParam(value="drugsAlias", required=false) String drugsAlias
            , @RequestParam(value="drugsSpec", required=true) String drugsSpec
            , @RequestParam(value="prescription", required=true) Integer prescription
            , @RequestParam(value="mentalMedicine", required=true) Integer mentalMedicine
            , @RequestParam(value="largeUnit", required=false) String largeUnit
            , @RequestParam(value="smallUnit", required=false) String smallUnit
            , @RequestParam(value="conversionRate", required=false) Short conversionRate
            , @RequestParam(value="disable", required=false) Integer disable
            , @RequestParam(value="level", required=true) Integer level
            , @RequestParam(value="medicalInsuranceSign", required=true) Integer medicalInsuranceSign
            , @RequestParam(value="placeorigin", required=true) Integer placeorigin
            , @RequestParam(value="baseMedicine", required=true) Integer baseMedicine
            , @RequestParam(value="narcoticDrugs", required=true) Integer narcoticDrugs
            , @RequestParam(value="remarks", required=false) String remarks
            , @RequestParam(value="enterPrice", required=false) Double enterPrice
            , @RequestParam(value="departmentId", required=false) Long departmentId
    ) throws Exception {
        HisMedicineInfo hisMedicineInfo = new HisMedicineInfo();
        if(null != request.getParameter("id")){
            hisMedicineInfo.setId(id);
        }
        if(null != request.getParameter("drugsNumb")){
            hisMedicineInfo.setDrugsNumb(drugsNumb);
        }
        if(null != request.getParameter("drugsName")){
            hisMedicineInfo.setDrugsName(drugsName);
        }
        if(null != request.getParameter("drugsAlias")){
            hisMedicineInfo.setDrugsAlias(drugsAlias);
        }
        if(null != request.getParameter("drugsSpec")){
            hisMedicineInfo.setDrugsSpec(drugsSpec);
        }
        if(null != request.getParameter("prescription")){
            hisMedicineInfo.setPrescription(prescription);
        }
        if(null != request.getParameter("mentalMedicine")){
            hisMedicineInfo.setMentalMedicine(mentalMedicine);
        }
        if(null != request.getParameter("largeUnit")){
            hisMedicineInfo.setLargeUnit(largeUnit);
        }
        if(null != request.getParameter("smallUnit")){
            hisMedicineInfo.setSmallUnit(smallUnit);
        }
        if(null != request.getParameter("conversionRate")){
            hisMedicineInfo.setConversionRate(conversionRate);
        }
        if(null != request.getParameter("disable")){
            hisMedicineInfo.setDisable(disable);
        }
        if(null != request.getParameter("level")){
            hisMedicineInfo.setLevel(level);
        }
        if(null != request.getParameter("medicalInsuranceSign")){
            hisMedicineInfo.setMedicalInsuranceSign(medicalInsuranceSign);
        }
        if(null != request.getParameter("placeorigin")){
            hisMedicineInfo.setPlaceorigin(placeorigin);
        }
        if(null != request.getParameter("baseMedicine")){
            hisMedicineInfo.setBaseMedicine(baseMedicine);
        }
        if(null != request.getParameter("narcoticDrugs")){
            hisMedicineInfo.setNarcoticDrugs(narcoticDrugs);
        }
        if(null != request.getParameter("remarks")){
            hisMedicineInfo.setRemarks(remarks);
        }
        if(null != request.getParameter("enterPrice")){
            hisMedicineInfo.setEnterPrice(enterPrice);
        }
        if(null != request.getParameter("departmentId")){
            hisMedicineInfo.setDepartmentId(departmentId);
        }
     return  hisMedicineInfoService.saveOrUpdate(hisMedicineInfo);
    }

    /**
     *@Description 删除
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-12
     *@Time 22:12
     **/
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Message delete (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisMedicineInfoService.delete(ids);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }


    /**
     *@Description list
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-13
     *@Time 0:40
    **/
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public PageBean<HisMedicineInfo> list (Map<String, Object> model, HttpServletRequest request, HisMedicineInfo hisMedicineInfo) throws Exception{
        PageBean<HisMedicineInfo> pageBean = new PageBean<HisMedicineInfo>();
        pageBean.setParameter(hisMedicineInfo);
        return hisMedicineInfoService.list(pageBean);
    }

    /**
     *@Description 药品信息分页查询
     *@Params [token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-06-27
     *@Time 21:08
    **/
    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex(String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicine/list");
        Translate translate = new Translate();
        translate.setTranslateId(474L);
        translate.setTranslateType(Constants.TRANSLATE_HIS_HISCONSUMABLES_LIST);
        translate.setTranslateColumn("value");
        List<Translate> translates = iTranslateService.queryTranslate(translate);
        modelAndView.addObject("title","药品信息模块");
        modelAndView.addObject("token",token);
        return modelAndView;
    }

    /**
     *@Description 药品新增与修改界面跳转
     *@Params [hisMedicineInfo, token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-08-12
     *@Time 9:54
    **/
    @RequestMapping("/update/index.ahsj")
    ModelAndView UpdateIndex(HisMedicineInfo hisMedicineInfo,String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicine/update");
        if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getId())){
            modelAndView.addObject("hisMedicineInfo",hisMedicineInfoService.selectById(hisMedicineInfo.getId()));
        }else{
            modelAndView.addObject("hisMedicineInfo",hisMedicineInfo);
        }
        modelAndView.addObject("title","药品信息管理");
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     *@Description      * 导出 excel
     *@Params [hisTypographyUserInfo, response]
     *@return void
     *@Author zhushixiang
     *@Date 2019/7/22
     *@Time 19:20
     **/
    @GetMapping("exportExcel.ahsj")
    @ResponseBody
    public void exportExcels(HttpServletRequest request, @RequestParam("param") String param, @RequestParam("idsData") Long[] idsData, HisMedicineInfo hisMedicineInfo,
                             HttpServletResponse response, HttpSession session, String token, String drugsNumb,String drugsName,String drugsAlias, Integer medicalInsuranceSign,Integer level,
                             Integer prescription,Integer mentalMedicine,Integer baseMedicine,Integer narcoticDrugs) throws Exception {
        if (!StringUtils.isEmpty(drugsNumb)) {
            hisMedicineInfo.setDrugsNumb(drugsNumb);
        }
        if (!StringUtils.isEmpty(drugsName)) {
            hisMedicineInfo.setDrugsName(drugsName);
        }
        if (!StringUtils.isEmpty(drugsAlias)) {
            hisMedicineInfo.setDrugsAlias(drugsAlias);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(medicalInsuranceSign)) {
            hisMedicineInfo.setMedicalInsuranceSign(medicalInsuranceSign);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(level)) {
            hisMedicineInfo.setLevel(level);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(prescription)) {
            hisMedicineInfo.setPrescription(prescription);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(mentalMedicine)) {
            hisMedicineInfo.setMentalMedicine(mentalMedicine);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(baseMedicine)) {
            hisMedicineInfo.setBaseMedicine(baseMedicine);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(narcoticDrugs)) {
            hisMedicineInfo.setNarcoticDrugs(narcoticDrugs);
        }
        List<HisMedicineInfo> hisMedicineInfoList = hisMedicineInfoService.selectByExportExcel(hisMedicineInfo);
        Long[] ids=new Long[hisMedicineInfoList.size()];
        if(EmptyUtil.Companion.isNullOrEmpty(idsData)||idsData.length==0){
            //根据导出的条件搜索出对应的值
            for (int i = 0; i <hisMedicineInfoList.size() ; i++) {
                ids[i]=hisMedicineInfoList.get(i).getId();
            }
        }else
            ids =idsData;

        hisMedicineInfoService.exportExcels(ids, request, response, session,param);
    }

    /**
     *@Description 药品基本信息excel导入
     *@Params [excelFile, request]
     *@return void
     *@Author zhushixiang
     *@Date 2019-08-29
     *@Time 14:49
    **/
    @RequestMapping("/importExcel.ahsj")
    @ResponseBody
    public String readExcel(@RequestParam(value = "excelFile") MultipartFile excelFile, HttpServletRequest request) throws Exception {
        List<String> errorFilelist = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        String date1 = format1.format(new Date());
        String voucher = "ERROR"+date;
        String filename = excelFile.getOriginalFilename();
        List<HisImportInfo> importInfoList = new ArrayList<>();
        List<HisMedicineInfo> hisMedicineInfoList = ExcelUtils.readExcel("", HisMedicineInfo.class, excelFile);
        List<HisMedicineInfo> list = new ArrayList<>();
        Integer errorNum = 0;
        for (HisMedicineInfo hisMedicineInfo : hisMedicineInfoList) {
            if(EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getDrugsNumb())){
                HisMedicineInfo setDrugsNumb = hisMedicineInfoService.selectByDrugsNameAndSpec(hisMedicineInfo);
                if(!EmptyUtil.Companion.isNullOrEmpty(setDrugsNumb))
                    hisMedicineInfo.setDrugsNumb(setDrugsNumb.getDrugsNumb());
                else
                    hisMedicineInfo.setDrugsNumb("");
            }
        }
        List<String> collect = hisMedicineInfoList.stream().map(HisMedicineInfo::getDrugsNumb).collect(Collectors.toList());
        //记录重复的日志记录
        CheckRepeatUtils checkRepeatUtils = new CheckRepeatUtils();
        Map<String, List<Integer>> allRepeatedElementsString = checkRepeatUtils.findAllRepeatedElementsString(collect);
        if (!EmptyUtil.Companion.isNullOrEmpty(allRepeatedElementsString)) {
            for (Map.Entry<String, List<Integer>> errorInfo : allRepeatedElementsString.entrySet()) {
                HisImportInfo hisImportInfo = new HisImportInfo();
                String key = errorInfo.getKey();
                List<Integer> errorRepeaNum = new ArrayList<>();
                List<Integer> values = errorInfo.getValue();
                if(EmptyUtil.Companion.isNullOrEmpty(key)){
                    for (Integer value : values) {
                        errorRepeaNum.add(value + 1);
                    }
                    String errors = date1 + " : 第 : " + errorRepeaNum + " 条数据未填写药品编号  ";
                    errorFilelist.add(errors);
                    hisImportInfo.setUploadErrorInfo(errors);
                    hisImportInfo.setUploadFileName(filename);
                    hisImportInfo.setVoucher(voucher);
                    importInfoList.add(hisImportInfo);
                }else {
                    for (Integer value : values) {
                        errorRepeaNum.add(value + 1);
                    }
                    String errors = date1 + " : 药品编号为 : " + key + " 重复 , " + "具体在 " + errorRepeaNum + "条";
                    errorFilelist.add(errors);
                    hisImportInfo.setUploadErrorInfo(errors);
                    hisImportInfo.setUploadFileName(filename);
                    hisImportInfo.setVoucher(voucher);
                    importInfoList.add(hisImportInfo);
                }
            }
        }
        //根据药品编号去重
        List<HisMedicineInfo> arrayList = hisMedicineInfoList.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(e ->e.getDrugsNumb() ))), ArrayList::new)
        );

        for (HisMedicineInfo hisMedicineInfo : arrayList) {
            if (EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getDrugsNumb())  ||
                    EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getPrecriptionName()) ||EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getMentalmedicine())|| EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getLeveld())||
                    EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getMedicalinsurancesign())||EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getPlaceoriginName())||EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getBasemedicine())||
                    EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getNarcoticdrugs())){
                String errors = date1 + " : 药品编号为 : " + hisMedicineInfo.getDrugsNumb() + "的那一条记录信息存在没填写数据";
                errorFilelist.add(errors);
                HisImportInfo hisImportInfo = new HisImportInfo();
                hisImportInfo.setUploadErrorInfo(errors);
                hisImportInfo.setUploadFileName(filename);
                hisImportInfo.setVoucher(voucher);
                importInfoList.add(hisImportInfo);
                errorNum++;
            }else {
                //字典
                SysCodeDetail sysCodeDetail = new SysCodeDetail();
                sysCodeDetail.setValue(hisMedicineInfo.getPrecriptionName());
                sysCodeDetail.setSysCodeType(Constants.HIS_SYS_CODE_PRESCRIPTION);
                sysCodeDetail = iCodeService.getSysCodeName(sysCodeDetail);

                SysCodeDetail sysCodeDetail2 = new SysCodeDetail();
                sysCodeDetail2.setValue(hisMedicineInfo.getMentalmedicine());
                sysCodeDetail2.setSysCodeType(Constants.HIS_SYS_CODE_MENTAL_MEDICINE);
                sysCodeDetail2 = iCodeService.getSysCodeName(sysCodeDetail2);

                SysCodeDetail sysCodeDetail3 = new SysCodeDetail();
                sysCodeDetail3.setValue(hisMedicineInfo.getLeveld());
                sysCodeDetail3.setSysCodeType(Constants.HIS_SYS_CODE_LEVEL);
                sysCodeDetail3 = iCodeService.getSysCodeName(sysCodeDetail3);

                SysCodeDetail sysCodeDetail4 = new SysCodeDetail();
                sysCodeDetail4.setValue(hisMedicineInfo.getMedicalinsurancesign());
                sysCodeDetail4.setSysCodeType(Constants.HIS_SYS_CODE_MEDICAL_INSURANCE_SIGN);
                sysCodeDetail4 = iCodeService.getSysCodeName(sysCodeDetail4);

                SysCodeDetail sysCodeDetail5 = new SysCodeDetail();
                sysCodeDetail5.setValue(hisMedicineInfo.getPlaceoriginName());
                sysCodeDetail5.setSysCodeType(Constants.HIS_SYS_CODE_PLACEORIGIN);
                sysCodeDetail5 = iCodeService.getSysCodeName(sysCodeDetail5);

                SysCodeDetail sysCodeDetail6 = new SysCodeDetail();
                sysCodeDetail6.setValue(hisMedicineInfo.getBasemedicine());
                sysCodeDetail6.setSysCodeType(Constants.HIS_SYS_CODE_BASE_MEDICINE);
                sysCodeDetail6 = iCodeService.getSysCodeName(sysCodeDetail6);

                SysCodeDetail sysCodeDetail7 = new SysCodeDetail();
                sysCodeDetail7.setValue(hisMedicineInfo.getNarcoticdrugs());
                sysCodeDetail7.setSysCodeType(Constants.HIS_SYS_CODE_NARCOTIC_DRUGS);
                sysCodeDetail7 = iCodeService.getSysCodeName(sysCodeDetail7);
                if (EmptyUtil.Companion.isNullOrEmpty(sysCodeDetail.getId())||EmptyUtil.Companion.isNullOrEmpty(sysCodeDetail2.getId())||EmptyUtil.Companion.isNullOrEmpty(sysCodeDetail3.getId())
                        ||EmptyUtil.Companion.isNullOrEmpty(sysCodeDetail4.getId())||EmptyUtil.Companion.isNullOrEmpty(sysCodeDetail5.getId())||EmptyUtil.Companion.isNullOrEmpty(sysCodeDetail6.getId())
                        ||EmptyUtil.Companion.isNullOrEmpty(sysCodeDetail7.getId())){
                    String errors = date1 + " : 药品编号为 : " + hisMedicineInfo.getDrugsNumb() + "的那一条字典信息填写有误";
                    errorFilelist.add(errors);
                    HisImportInfo hisImportInfo = new HisImportInfo();
                    hisImportInfo.setUploadErrorInfo(errors);
                    hisImportInfo.setUploadFileName(filename);
                    hisImportInfo.setVoucher(voucher);
                    importInfoList.add(hisImportInfo);
                    errorNum++;
                    continue;
                }else {
                    if(EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getEnterPrice()))
                        hisMedicineInfo.setEnterPrice(0.00);
                    list.add(hisMedicineInfo);
                }
            }
        }
        if (!CollectionUtils.isEmpty(list)) {
            hisMedicineInfoService.importExcel(list);
        }
        //将错误日志存库
        String psth = "templates/errorImport/HisMedicineInfoErrorInfo.txt";
        psth = Constants.HIS_SYS_EXCEL_MEDICINE_ERROR_FILE_URL;
        File file = new File(psth);
        hisImportInfoService.inserHisImportInfo(importInfoList);
        String vouchers= "";
        if(importInfoList.size() > 0)
            vouchers = importInfoList.get(0).getVoucher();

        //错误日志集合
        List<String> errorList = importInfoList.stream().map(HisImportInfo::getUploadErrorInfo).collect(Collectors.toList());

        FileUtil.listToFile(errorFilelist, file);

        Result result = new Result();
        ExcelUploadFile excelUploadFile = new ExcelUploadFile();
        excelUploadFile.setFaildNum(hisMedicineInfoList.size() - list.size());
        excelUploadFile.setSuccessNum(list.size());
        excelUploadFile.setErrorList(errorList);
        if(!EmptyUtil.Companion.isNullOrEmpty(vouchers) || !StringUtils.equals(vouchers,""))
            excelUploadFile.setVouchers(vouchers);
        excelUploadFile.setImportListSize(hisMedicineInfoList.size());
        excelUploadFile.setErrorListSize( errorList.size());
        result.setStatus(200);
        result.setData(excelUploadFile);
        return JsonUtils.serialize(result);
}

    /**
     *@功能说明  下载导入错误信息
     *@Params [request, param, idsData, hisProject, response, session, token, pinyinCode, isEnable]
     *@return void
     *@Author XJPapi/hismedicineinfo
     *@Date 2019/9/3
     *@Time 15:19
     **/
    @GetMapping("/error.ahsj")
    @ResponseBody
    public void error(HttpServletRequest request, @RequestParam("vouchers") String vouchers, HttpServletResponse response) throws Exception {
        HisImportInfo hisImportInfo = new HisImportInfo();
        hisImportInfo.setVoucher(vouchers);
        List<HisImportInfo> hisImportInfoList =  hisImportInfoService.queryHisImportInfo(hisImportInfo);
        List<String> collect = hisImportInfoList.stream().map(HisImportInfo::getUploadErrorInfo).collect(Collectors.toList());
        log.info(collect.size()+"--------------------------------------------");
        log.info(vouchers+"--------------------------------------------");
        //清空txt文件数据
        String psth = this.getClass().getClassLoader().getResource("templates/errorImport/errorInfo.txt").getPath();
        psth = Constants.HIS_SYS_EXCEL_DOCTOR_ERROR_FILE_URL;
        FileUtil.clearInfoForFile(psth);
        //将错误数据存到txt文件里
        File file = new File(psth);
        FileUtil.listToFile(collect, file);
        //导出
        FileUtil.download(response,psth);
    }

    /**
     *@功能说明  下载导入文件模板
     *@Params [response]
     *@return void
     *@Author XJP
     *@Date 2019/9/3
     *@Time 15:19
     **/
    @GetMapping("/download.ahsj")
    @ResponseBody
    public void download(HttpServletResponse response) throws Exception {
        String psth = this.getClass().getClassLoader().getResource("templates/excel/import/hismedicine.xlsx").getPath();
        psth = Constants.HIS_SYS_EXCEL_MEDICINE_IMPORT_FILE_URL;
        FileUtil.download(response,psth);
    }




}
