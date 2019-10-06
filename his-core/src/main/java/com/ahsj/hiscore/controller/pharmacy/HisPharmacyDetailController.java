package com.ahsj.hiscore.controller.pharmacy;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.utils.ExcelUtils;
import com.ahsj.hiscore.common.utils.FileUtil;
import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.common.utils.Result;
import com.ahsj.hiscore.controller.BaseMedicineController;
import com.ahsj.hiscore.entity.HisImportInfo;
import com.ahsj.hiscore.entity.HisMediEnterDetails;
import com.ahsj.hiscore.entity.HisMedicationDetails;
import com.ahsj.hiscore.entity.HisPharmacyDetail;
import com.ahsj.hiscore.entity.dto.ExcelUploadFile;
import com.ahsj.hiscore.error.GlobalExceptionHandler;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.services.HisImportInfoService;
import com.ahsj.hiscore.services.HisMediEnterDetailsService;
import com.ahsj.hiscore.services.HisMedicineInfoService;
import com.ahsj.hiscore.services.HisPharmacyDetailService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/pharmacy/")
public class HisPharmacyDetailController extends BaseMedicineController {
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    HisPharmacyDetailService hisPharmacyDetailService;

    @Autowired
    HisMediEnterDetailsService hisMediEnterDetailsService;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    HisMedicineInfoService hisMedicineInfoService;

    @Autowired
    HisImportInfoService hisImportInfoService;


    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Description 分页查询
     * @Params [model, request, hisPharmacyDetail]
     * @Author zhushixiang
     * @Date 2019-06-22
     * @Time 20:54
     **/
    @ResponseBody
    @RequestMapping(value = "list.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<HisPharmacyDetail> list(Map<String, Object> model, HttpServletRequest request, HisPharmacyDetail hisPharmacyDetail) throws Exception {
        PageBean<HisPharmacyDetail> pharmacyDetailPageBean = new PageBean<HisPharmacyDetail>();
        pharmacyDetailPageBean.setParameter(hisPharmacyDetail);
        return hisPharmacyDetailService.list(pharmacyDetailPageBean);
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 药库信息汇总界面跳转
     * @Params [token]
     * @Author zhushixiang
     * @Date 2019-06-22
     * @Time 21:05
     **/
    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex(String token, boolean isSameDepart) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/list");
        modelAndView.addObject("title", "药库信息汇总模块");
        modelAndView.addObject("token", token);
        modelAndView.addObject("isSameDepart", !isSameDepart);
        return modelAndView;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Description 分页查询为医生开药
     * @Params [model, request, hisPharmacyDetail]
     * @Author zhushixiang
     * @Date 2019-06-22
     * @Time 20:54
     **/
    @ResponseBody
    @RequestMapping(value = "listForMedication.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<HisPharmacyDetail> listForMedication(Map<String, Object> model, HttpServletRequest request, HisPharmacyDetail hisPharmacyDetail) throws Exception {
        PageBean<HisPharmacyDetail> pharmacyDetailPageBean = new PageBean<HisPharmacyDetail>();
        pharmacyDetailPageBean.setParameter(hisPharmacyDetail);
        return hisPharmacyDetailService.listForMedication(pharmacyDetailPageBean);
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 分页查询为医生开药
     * @Params [token]
     * @Author zhushixiang
     * @Date 2019-06-22
     * @Time 21:05
     **/
    @RequestMapping("/listForMedication/index.ahsj")
    ModelAndView listForMedicationIndex(String token, @RequestParam(value = "medicalOrderNumber", required = false) String medicalOrderNumber) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/listForMedication");
        modelAndView.addObject("title", "医生开药");
        modelAndView.addObject("token", token);
        if (!EmptyUtil.Companion.isNullOrEmpty(medicalOrderNumber))
            modelAndView.addObject("medicalOrderNumber", medicalOrderNumber);
        return modelAndView;
    }

    /**
     * @return core.message.Message
     * @Description 新增或更新
     * @Params [model, hisPharmacyDetail]
     * @Author zhushixiang
     * @Date 2019-06-25
     * @Time 10:20
     **/
    @RequestMapping(value = "saveOrUpdate.ahsj")
    @ResponseBody
    public Message saveOrUpdate(Map<String, Object> model, HisPharmacyDetail hisPharmacyDetail) throws Exception {
        return hisPharmacyDetailService.saveOrUpdate(hisPharmacyDetail);
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 药库药品信息增加与修改（前端界面）
     * @Params [hisPharmacyDetail, token]
     * @Author zhushixiang
     * @Date 2019-06-25
     * @Time 11:29
     **/
    @RequestMapping("/update/index.ahsj")
    ModelAndView UpdateIndex(HisPharmacyDetail hisPharmacyDetail, String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/update");
        if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getId())) {
            modelAndView.addObject("hisPharmacyDetail", hisPharmacyDetailService.selectById(hisPharmacyDetail.getId()));
        } else {
            modelAndView.addObject("hisPharmacyDetail", hisPharmacyDetail);
        }
        modelAndView.addObject("title", "药库药品信息修改");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [model, request, ids]
     * @Author zhushixiang
     * @Date 2019-06-25
     * @Time 11:30
     **/
    @ResponseBody
    @RequestMapping(value = "delete.ahsj", method = {RequestMethod.POST})
    public Message delete(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "ids", required = true) Long[] ids
    ) throws Exception {
        if (null != request.getParameter("ids")) {
            return hisPharmacyDetailService.delete(ids);
        } else return MessageUtil.createMessage(false, "参数异常");
    }


    /**
     * @return core.message.Message
     * @Description 设置下架
     * @Params [model, request, ids]
     * @Author zhushixiang
     * @Date 2019-07-01
     * @Time 10:54
     **/
    @ResponseBody
    @RequestMapping(value = "setObtained.ahsj")
    public Message setObtained(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "ids", required = true) Long[] ids) throws Exception {
        if (null != request.getParameter("ids")) {
            return hisPharmacyDetailService.setObtained(ids);
        } else return MessageUtil.createMessage(false, "参数异常");
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 药库药品详细入库信息查看
     * @Params [token, request, medicineId]
     * @Author zhushixiang
     * @Date 2019-07-07
     * @Time 14:43
     **/
    @RequestMapping("/visitMediEnter/index.ahsj")
    ModelAndView visitMediEnter(String token, HttpServletRequest request,
                                @RequestParam(value = "medicineId", required = true) Long medicineId) {
        List<HisMediEnterDetails> hisMediEnterDetailsList = hisMediEnterDetailsService.selectByPharmacyId(medicineId);
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/visitMediEnter");
        modelAndView.addObject("title", "药库药品详细入库信息查看");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hisMediEnterDetailsList", hisMediEnterDetailsList);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 根据就诊编号/住院编号/交易流水号出库界面前端
     * @Params [token, request]
     * @Author zhushixiang
     * @Date 2019-07-08
     * @Time 14:12
     **/
    @RequestMapping("/listForMediExitByNumber/index.ahsj")
    ModelAndView listForMediExitByNumber(String token, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/listForMediExitByNumber");
        modelAndView.addObject("title", "根据就诊编号出库");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return core.message.Message
     * @Description 查询出对应就诊编号/住院编号/交易流水号的用药明细
     * @Params [model, hisPharmacyDetail]
     * @Author zhushixiang
     * @Date 2019-07-22
     * @Time 20:28
     **/
    @RequestMapping(value = "listForMedicationDetailsByNumber.ahsj")
    @ResponseBody
    public List<HisMedicationDetails> listForMedicationDetailsByNumber(Map<String, Object> model, HttpServletRequest request, String number) throws Exception {
        return hisPharmacyDetailService.listForMedicationDetailsByNumber(number);
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 根据就诊编号手动出库
     * @Params [token, request]
     * @Author zhushixiang
     * @Date 2019-07-08
     * @Time 14:50
     **/
    @RequestMapping("/mannulExitforMedicationRecord/index.ahsj")
    ModelAndView mannulExitforMedicationRecord(String token, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/mannulExitforMedicationRecord");
        modelAndView.addObject("title", "根据就诊编号手动出库");
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 药品报损（药库界面）
     * @Params [token, request, id]
     * @Author zhushixiang
     * @Date 2019-07-09
     * @Time 10:04
     **/
    @RequestMapping("/drugLossReporting/index.ahsj")
    ModelAndView drugLossReporting(String token, HttpServletRequest request,
                                   @RequestParam(value = "pharmacyId", required = true) Long pharmacyId) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/pharmacy/drugLossReporting");
        modelAndView.addObject("title", "药品报损");
        modelAndView.addObject("pharmacyId", pharmacyId);
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @return
     * @Description 设置停用状态
     * @Params
     * @Author zhushixiang
     * @Date 2019-06-28
     * @Time 20:20
     **/
    @ResponseBody
    @RequestMapping(value = "setDisable.ahsj")
    public Message setDisable(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value = "ids", required = true) Long[] ids) throws Exception {
        if (null != request.getParameter("ids")) {
            return hisPharmacyDetailService.setDisable(ids);
        } else return MessageUtil.createMessage(false, "参数异常");
    }

    @RequestMapping(value = "listForMedicationDetailsByNumberPrint.ahsj")
    @ResponseBody
    public List<HisMedicationDetails> listForMedicationDetailsByNumberPrint(String number) throws Exception {
        List<HisMedicationDetails> hisMedicationDetails = hisPharmacyDetailService.listForMedicationDetailsByNumber(number);
        hisMedicationDetails.get(0).setCreateDate(new Date());
        return hisMedicationDetails;
    }


    /**
     * @return void
     * @Description * 导出 excel
     * @Params [hisTypographyUserInfo, response]
     * @Author zhushixiang
     * @Date 2019/7/22
     * @Time 19:20
     **/
    @GetMapping("/exportExcel.ahsj")
    @ResponseBody
    public void exportExcels(HttpServletRequest request, @RequestParam("param") String param, @RequestParam("idsData") Long[] idsData, HisPharmacyDetail hisPharmacyDetail,
                             HttpServletResponse response, HttpSession session, String token, String drugsNumb, String drugsName, Integer lowPrice, Integer highPrice, Integer lowCount,
                             Integer highCount, Integer medicalInsuranceSign, Integer level,
                             Integer prescription, Integer mentalMedicine, Integer baseMedicine, Integer narcoticDrugs, Integer disable, Integer isObtained, Integer isWarning) throws Exception {
        if (!StringUtils.isEmpty(drugsNumb)) {
            hisPharmacyDetail.setDrugsNumb(drugsNumb);
        }
        if (!StringUtils.isEmpty(drugsName)) {
            hisPharmacyDetail.setDrugsName(drugsName);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(lowPrice)) {
            hisPharmacyDetail.setLowPrice(BigDecimal.valueOf(lowPrice));
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(highPrice)) {
            hisPharmacyDetail.setHighPrice(BigDecimal.valueOf(highPrice));
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(lowCount)) {
            hisPharmacyDetail.setLowCount(lowCount);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(highCount)) {
            hisPharmacyDetail.setHighCount(highCount);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(medicalInsuranceSign)) {
            hisPharmacyDetail.setMedicalInsuranceSign(medicalInsuranceSign);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(level)) {
            hisPharmacyDetail.setLevel(level);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(prescription)) {
            hisPharmacyDetail.setPrescription(prescription);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(mentalMedicine)) {
            hisPharmacyDetail.setMentalMedicine(mentalMedicine);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(baseMedicine)) {
            hisPharmacyDetail.setBaseMedicine(baseMedicine);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(narcoticDrugs)) {
            hisPharmacyDetail.setNarcoticDrugs(narcoticDrugs);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(disable)) {
            hisPharmacyDetail.setDisable(disable);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(isObtained)) {
            hisPharmacyDetail.setIsObtained(isObtained);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(isWarning)) {
            hisPharmacyDetail.setIsWarning(isWarning);
        }
        List<HisPharmacyDetail> hisPharmacyDetailList = hisPharmacyDetailService.selectByExportExcel(hisPharmacyDetail);
        Long[] ids = new Long[hisPharmacyDetailList.size()];
        if (EmptyUtil.Companion.isNullOrEmpty(idsData) || idsData.length == 0) {
            //根据导出的条件搜索出对应的值
            for (int i = 0; i < hisPharmacyDetailList.size(); i++) {
                ids[i] = hisPharmacyDetailList.get(i).getId();
            }
        } else
            ids = idsData;

        hisPharmacyDetailService.exportExcels(ids, request, response, session, param);
    }


    /**
     * @return void
     * @Description 药库基本信息excel导入
     * @Params [excelFile, request]
     * @Author zhushixiang
     * @Date 2019-08-29
     * @Time 14:49
     **/
    @RequestMapping("/importExcel.ahsj")
    @ResponseBody
    public String readExcel(@RequestParam(value = "excelFile") MultipartFile excelFile, HttpServletRequest request) throws Exception {
        List<String> errorFilelist = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        String date1 = format1.format(new Date());
        String voucher = "ERROR" + date;
        String filename = excelFile.getOriginalFilename();
        List<HisImportInfo> importInfoList = new ArrayList<>();
        List<HisPharmacyDetail> hisPharmacyDetailList = ExcelUtils.readExcel("", HisPharmacyDetail.class, excelFile);
        List<HisPharmacyDetail> list = new ArrayList<>();
        Integer errorNum = 0;
        for (HisPharmacyDetail hisPharmacyDetail : hisPharmacyDetailList) {
            if (EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getDrugsNumb())) {
                HisPharmacyDetail setDrugsNumb = hisPharmacyDetailService.selectByDrugsNameAndSpec(hisPharmacyDetail);
                if (!EmptyUtil.Companion.isNullOrEmpty(setDrugsNumb))
                    hisPharmacyDetail.setDrugsNumb(setDrugsNumb.getDrugsNumb());
                else
                    hisPharmacyDetail.setDrugsNumb("");
            }
        }
        for (HisPharmacyDetail hisPharmacyDetail : hisPharmacyDetailList) {
            //有效期未填写必导入失败
            if (EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getValidityPeriod())) {
                String errors = date1 + " : 药品编号为 : " + hisPharmacyDetail.getDrugsNumb() + "的那一条记录信息有效期未填写";
                errorFilelist.add(errors);
                HisImportInfo hisImportInfo = new HisImportInfo();
                hisImportInfo.setUploadErrorInfo(errors);
                hisImportInfo.setUploadFileName(filename);
                hisImportInfo.setVoucher(voucher);
                importInfoList.add(hisImportInfo);
                errorNum++;
            } else {
                //药品编号/（药品名称与规格）必须二选一填写，否则错误
                if (EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfoService.selectByDrugsNumb(hisPharmacyDetail.getDrugsNumb()))) {
                    String errors = date1 + " : 药品编号为 : " + hisPharmacyDetail.getDrugsNumb() + "的药品在药品表中没有对应记录";
                    errorFilelist.add(errors);
                    HisImportInfo hisImportInfo = new HisImportInfo();
                    hisImportInfo.setUploadErrorInfo(errors);
                    hisImportInfo.setUploadFileName(filename);
                    hisImportInfo.setVoucher(voucher);
                    importInfoList.add(hisImportInfo);
                    errorNum++;
                } else {
                    if ((EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getDrugsNumb()) && (EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getDrugsName()) && EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getDrugsSpec())))) {
                        String errors = date1 + " : 药品名称为 : " + hisPharmacyDetail.getDrugsName() + " : 药品规格为 : " + hisPharmacyDetail.getDrugsSpec() + "的药品在药品表中没有对应记录";
                        errorFilelist.add(errors);
                        HisImportInfo hisImportInfo = new HisImportInfo();
                        hisImportInfo.setUploadErrorInfo(errors);
                        hisImportInfo.setUploadFileName(filename);
                        hisImportInfo.setVoucher(voucher);
                        importInfoList.add(hisImportInfo);
                        errorNum++;
                    } else {
                        list.add(hisPharmacyDetail);
                    }
                }
            }
        }
        if (!CollectionUtils.isEmpty(list)) {
            hisPharmacyDetailService.importExcel(list);
        }
        //将错误日志存库
        String psth = "templates/errorImport/HisPharmacyDetailErrorInfo.txt";
        psth = Constants.HIS_SYS_EXCEL_DOCTOR_ERROR_FILE_URL;
        File file = new File(psth);
        hisImportInfoService.inserHisImportInfo(importInfoList);
        String vouchers = "";
        if (importInfoList.size() > 0)
            vouchers = importInfoList.get(0).getVoucher();

        //错误日志集合
        List<String> errorList = importInfoList.stream().map(HisImportInfo::getUploadErrorInfo).collect(Collectors.toList());

        FileUtil.listToFile(errorFilelist, file);

        Result result = new Result();
        ExcelUploadFile excelUploadFile = new ExcelUploadFile();
        excelUploadFile.setFaildNum(hisPharmacyDetailList.size() - list.size());
        excelUploadFile.setSuccessNum(list.size());
        excelUploadFile.setErrorList(errorList);
        if (!EmptyUtil.Companion.isNullOrEmpty(vouchers) || !StringUtils.equals(vouchers, ""))
            excelUploadFile.setVouchers(vouchers);
        excelUploadFile.setImportListSize(hisPharmacyDetailList.size());
        excelUploadFile.setErrorListSize(errorList.size());
        result.setStatus(200);
        result.setData(excelUploadFile);
        return JsonUtils.serialize(result);
    }


    /**
     * @return void
     * @功能说明 下载导入错误信息
     * @Params [request, param, idsData, hisProject, response, session, token, pinyinCode, isEnable]
     * @Author XJP
     * @Date 2019/9/3
     * @Time 15:19
     **/
    @GetMapping("/error.ahsj")
    @ResponseBody
    public void error(HttpServletRequest request, @RequestParam("vouchers") String vouchers, HttpServletResponse response) throws Exception {
        HisImportInfo hisImportInfo = new HisImportInfo();
        hisImportInfo.setVoucher(vouchers);
        List<HisImportInfo> hisImportInfoList = hisImportInfoService.queryHisImportInfo(hisImportInfo);
        List<String> collect = hisImportInfoList.stream().map(HisImportInfo::getUploadErrorInfo).collect(Collectors.toList());
        //清空txt文件数据
        log.info(collect.toString());
        String psth = this.getClass().getClassLoader().getResource("templates/errorImport/errorInfo.txt").getPath();
        psth = Constants.HIS_SYS_EXCEL_DOCTOR_ERROR_FILE_URL;
        FileUtil.clearInfoForFile(psth);
        //将错误数据存到txt文件里
        File file = new File(psth);
        FileUtil.listToFile(collect, file);
        //导出
        FileUtil.download(response, psth);
    }

    /**
     * @return void
     * @功能说明 下载导入文件模板
     * @Params [response]
     * @Author XJP
     * @Date 2019/9/3
     * @Time 15:19
     **/
    @GetMapping("/download.ahsj")
    @ResponseBody
    public void exportExcel(HttpServletResponse response) throws Exception {
        String psth = this.getClass().getClassLoader().getResource("templates/excel/import/hisPharmacy.xlsx").getPath();
        psth = Constants.HIS_SYS_EXCEL_PHARMACY_IMPORT_FILE_URL;
        FileUtil.download(response, psth);
    }

    /**
     *@Description 查询出未停用未下架的药品
     *@Params [model, request, hisPharmacyDetail]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisPharmacyDetail>
     *@Author zhushixiang
     *@Date 2019-09-18
     *@Time 18:20
    **/
    @ResponseBody
    @RequestMapping(value = "listForIsDisableAndObtained.ahsj", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<HisPharmacyDetail> listForIsDisableAndObtained(Map<String, Object> model, HttpServletRequest request, HisPharmacyDetail hisPharmacyDetail) throws Exception {
        PageBean<HisPharmacyDetail> pharmacyDetailPageBean = new PageBean<HisPharmacyDetail>();
        pharmacyDetailPageBean.setParameter(hisPharmacyDetail);
        return hisPharmacyDetailService.listForIsDisableAndObtained(pharmacyDetailPageBean);
    }



    /**
     * @Description 药库盘点
     * @Params: [hisPharmacyDetail]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Date 2019/9/18
     * @Time 17:58
     **/
    @ResponseBody
    @RequestMapping(value = "pharmacyInventory.ahsj")
    public PageBean<HisPharmacyDetail> pharmacyInventory(HisPharmacyDetail hisPharmacyDetail) throws Exception {
        PageBean<HisPharmacyDetail> pharmacyDetailPageBean = new PageBean<HisPharmacyDetail>();
        pharmacyDetailPageBean.setParameter(hisPharmacyDetail);
        return hisPharmacyDetailService.list(pharmacyDetailPageBean);
    }


}
