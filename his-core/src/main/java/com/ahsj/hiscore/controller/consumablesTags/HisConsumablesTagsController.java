package com.ahsj.hiscore.controller.consumablesTags;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.utils.*;
import com.ahsj.hiscore.entity.HisConsumables;
import com.ahsj.hiscore.entity.HisImportInfo;
import com.ahsj.hiscore.entity.dto.ExcelUploadFile;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.services.HisConsumablesService;
import com.ahsj.hiscore.services.HisImportInfoService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName : HisConsumablesTagsController
 * @Description :
 * @Author : XJP
 * @Date: 2020-04-23 15:52
 */

@Controller
@RequestMapping("/api/hisconsumablesTags")
public class HisConsumablesTagsController {

    @Autowired
    HisConsumablesService hisConsumablesService;

    @Autowired
    ICodeService iCodeService;


    @Autowired
    HisImportInfoService hisImportInfoService;

    /**
     *@Description 新增或更新
     *@Params
     *@returnapi/menu/
     *@Author jin
     *@Date 2019/6/19
     *@Time 14:47
     */
    @RequestMapping(value = "/saveOrUpdate.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message saveOrUpdate ( HisConsumables hisConsumables) throws Exception {
        return  hisConsumablesService.saveOrUpdate(hisConsumables);
    }


    /**
     *@Description delete
     *@Params
     *@return
     *@Author jin
     *@Date 2019/6/19
     *@Time 15:01
     */

    @RequestMapping(value = "/delete.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message delete (Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisConsumablesService.delete(ids);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }

    //@ApiOperation(value = "设置失效")
    /**
     *@Description 更改可用状态
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/7
     *@Time 18:14
     */
    @RequestMapping(value = "/setDisable.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public Message setDisable(Map<String, Object> model, HttpServletRequest request
            , @RequestParam(value="ids", required=true) Long[] ids
    ) throws Exception {
        if(null != request.getParameter("ids")){
            return  hisConsumablesService.setDisable(ids);
        }else  return MessageUtil.createMessage(false,"参数异常");
    }



    /**
     *@Description 分页查询
     *@Params
     *@return
     *@Author jin
     *@Date 2019/6/19
     *@Time 15:10
     */
    @RequestMapping(value = "/list.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public PageBean<HisConsumables> list (Map<String, Object> model, HttpServletRequest request, HisConsumables hisConsumables) throws Exception{
        PageBean<HisConsumables> pageBean = new PageBean<HisConsumables>();
        pageBean.setParameter(hisConsumables);
        pageBean = hisConsumablesService.list(pageBean);
        return pageBean;
    }

    /**
     *@Description list查询不含不可用状态的耗材
     *@Params
     *@return
     *@Author jin
     *@Date 2019/8/8
     *@Time 15:56
     */
    @RequestMapping(value = "/listEnable.ahsj", method = {RequestMethod.POST})
    @ResponseBody
    public PageBean<HisConsumables> listEnable (Map<String, Object> model, HttpServletRequest request, HisConsumables hisConsumables) throws Exception{
        PageBean<HisConsumables> pageBean = new PageBean<HisConsumables>();
        pageBean.setParameter(hisConsumables);
        pageBean = hisConsumablesService.listEnable(pageBean);
        return pageBean;
    }

    /**
     *@Description M&V
     *@Params
     *@return
     *@Author jin
     *@Date 2019/6/20
     *@Time 22:48
     */

    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/list");
        modelAndView.addObject("title","耗材信息列表");
        modelAndView.addObject("token",token);
        return modelAndView;
    }


    @RequestMapping("/choose/index.ahsj")
    ModelAndView chooseIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/consumableschoose");
        modelAndView.addObject("title","选择耗材");
        modelAndView.addObject("token",token);
        return modelAndView;
    }


    //返回到
    @RequestMapping(value = "/backenterconsumables/index.ahsj")
    ModelAndView BackenterconsumablesIndex(String token,Long[] ids){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/enterconsumables.html");
        modelAndView.addObject("token",token);
        modelAndView.addObject("ids",ids);
        return modelAndView;
    }

    @RequestMapping("/update/index.ahsj")
    ModelAndView updateIndex(Long id,String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/consumables/update");
        modelAndView.addObject("title","耗材信息更新");
        modelAndView.addObject("token",token);
        //        modelAndView.addObject("id",id);
        if(!EmptyUtil.Companion.isNullOrEmpty(id)){
            modelAndView.addObject("consumable",hisConsumablesService.updateInit(id));
        }else{
            modelAndView.addObject("consumable",new HisConsumables());
        }
        return modelAndView;
    }

    @RequestMapping("/setDisable/index.ahsj")
    @ResponseBody
    ModelAndView setDisableIndex(Long[] ids,String token){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","耗材信息更新");
        modelAndView.addObject("token",token);
        if(!EmptyUtil.Companion.isNullOrEmpty(ids)){
            for (int i = 0;i<ids.length;i++){
                modelAndView.addObject("consumable",hisConsumablesService.updateInit(ids[i]));
            }
        }else{
            modelAndView.addObject("consumable",new HisConsumables());

        }
        return modelAndView;

    }


    /**
     *@Description 导入
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/3
     *@Time 13:54
     */
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
        List<HisConsumables> hisConsumablesList = ExcelUtils.readExcel("", HisConsumables.class, excelFile);
        List<HisConsumables> list = new ArrayList<>();
        Integer errorNum = 0;


        //            for (HisMedicineInfo hisMedicineInfo : hisMedicineInfoList) {
        //                if(EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getDrugsNumb())){
        //                    HisMedicineInfo setDrugsNumb = hisMedicineInfoService.selectByDrugsNameAndSpec(hisMedicineInfo);
        //                    if(!EmptyUtil.Companion.isNullOrEmpty(setDrugsNumb))
        //                        hisMedicineInfo.setDrugsNumb(setDrugsNumb.getDrugsNumb());
        //                    else
        //                        hisMedicineInfo.setDrugsNumb("");
        //                }
        //            }


        //暂留
        //            for (HisConsumables hisConsumables : hisConsumablesList) {
        //                if(EmptyUtil.Companion.isNullOrEmpty(hisConsumables.getName())){
        //                    HisConsumables sethisConsumables = hisConsumablesService.selectByNameandSpec(hisConsumables);
        //                        hisConsumables.setId(sethisConsumables.getId());
        //
        //                }
        //            }

        //            根据耗材名称去重
        List<HisConsumables> arrayList = hisConsumablesList.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(e ->e.getName() ))), ArrayList::new)
        );

        List<String> collect = hisConsumablesList.stream().map(HisConsumables::getName).collect(Collectors.toList());

        CheckRepeatUtils checkRepeatUtils = new CheckRepeatUtils();
        Map<String, List<Integer>> allRepeatedElementsString = CheckRepeatUtils.findAllRepeatedElementsString(collect);
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
                    String errors = date1 + " : 第 : " + errorRepeaNum + " 条数据未填写耗材名称  ";
                    errorFilelist.add(errors);
                    hisImportInfo.setUploadErrorInfo(errors);
                    hisImportInfo.setUploadFileName(filename);
                    hisImportInfo.setVoucher(voucher);
                    importInfoList.add(hisImportInfo);
                }else {
                    for (Integer value : values) {
                        errorRepeaNum.add(value + 1);
                    }
                    String errors = date1 + " : 耗材名称为 : " + key + " 重复 , " + "具体在 " + errorRepeaNum + "条";
                    errorFilelist.add(errors);
                    hisImportInfo.setUploadErrorInfo(errors);
                    hisImportInfo.setUploadFileName(filename);
                    hisImportInfo.setVoucher(voucher);
                    importInfoList.add(hisImportInfo);
                }
            }
        }


        for (HisConsumables hisConsumables : hisConsumablesList) {
            if (EmptyUtil.Companion.isNullOrEmpty(hisConsumables.getName()) ||  EmptyUtil.Companion.isNullOrEmpty(hisConsumables.getSpec())||
                    EmptyUtil.Companion.isNullOrEmpty(hisConsumables.getPrice()) ||EmptyUtil.Companion.isNullOrEmpty(hisConsumables.getEnable())|| EmptyUtil.Companion.isNullOrEmpty(hisConsumables.getTypeName())){
                errorNum ++;
            }else {
                //字典
                SysCodeDetail sysCodeDetail = new SysCodeDetail();
                sysCodeDetail.setValue(hisConsumables.getTypeName());
                sysCodeDetail.setSysCodeType(Constants.TRANSLATE_HIS_HISCONSUMABLES);
                sysCodeDetail = iCodeService.getSysCodeName(sysCodeDetail);

                SysCodeDetail sysCodeDetail2 = new SysCodeDetail();
                sysCodeDetail2.setValue(hisConsumables.getEnable());
                sysCodeDetail2.setSysCodeType(Constants.TRANSLATE_HIS_HISCONSUMABLES);
                sysCodeDetail2 = iCodeService.getSysCodeName(sysCodeDetail2);


                //                    if (EmptyUtil.Companion.isNullOrEmpty(sysCodeDetail.getId())){
                //                        errorNum ++;
                //                        continue;
                //                    }

                if (EmptyUtil.Companion.isNullOrEmpty(hisConsumables.getName()) ||  EmptyUtil.Companion.isNullOrEmpty(hisConsumables.getSpec())||
                        EmptyUtil.Companion.isNullOrEmpty(hisConsumables.getPrice()) ||EmptyUtil.Companion.isNullOrEmpty(hisConsumables.getEnable())|| EmptyUtil.Companion.isNullOrEmpty(hisConsumables.getTypeName())){
                    String errors = date1 + " : 耗材名称为 : " + hisConsumables.getName() + "的那一条字典信息填写有误";
                    errorFilelist.add(errors);
                    HisImportInfo hisImportInfo = new HisImportInfo();
                    hisImportInfo.setUploadErrorInfo(errors);
                    hisImportInfo.setUploadFileName(filename);
                    hisImportInfo.setVoucher(voucher);
                    importInfoList.add(hisImportInfo);
                    errorNum ++;
                    continue;
                }else {
                    list.add(hisConsumables);
                }
            }
        }
        if (!CollectionUtils.isEmpty(list)) {
            hisConsumablesService.importExcel(list);
        }

        //将错误日志存库
        File file = new File(Constants.HIS_SYS_EXCEL_DOCTOR_ERROR_FILE_URL);
        hisImportInfoService.inserHisImportInfo(importInfoList);
        String vouchers= "";
        if(importInfoList.size() > 0)
            vouchers = importInfoList.get(0).getVoucher();

        //错误日志集合
        List<String> errorList = importInfoList.stream().map(HisImportInfo::getUploadErrorInfo).collect(Collectors.toList());

        FileUtil.listToFile(errorFilelist, file);

        Result result = new Result();
        ExcelUploadFile excelUploadFile = new ExcelUploadFile();
        excelUploadFile.setFaildNum(hisConsumablesList.size() - list.size());
        excelUploadFile.setSuccessNum(list.size());
        excelUploadFile.setErrorList(errorList);
        if(EmptyUtil.Companion.isNullOrEmpty(vouchers) || StringUtils.equals(vouchers,""))
            excelUploadFile.setVouchers(vouchers);
        excelUploadFile.setImportListSize(hisConsumablesList.size());
        excelUploadFile.setErrorListSize( errorList.size());
        result.setStatus(200);
        result.setData(excelUploadFile);
        return JsonUtils.serialize(result);
    }

    /**
     *@Description 下载导入文件模板
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/4
     *@Time 12:03
     */
    @GetMapping("/download.ahsj")
    @ResponseBody
    public void exportExcel(HttpServletResponse response) throws Exception {
        String psth = this.getClass().getClassLoader().getResource("templates/excel/import/hisConsumables.xlsx").getPath();
        psth = Constants.HIS_SYS_EXCEL_HIS_CONSUMABLES_IMPORT_FILE_URL;
        FileUtil.download(response,psth);
    }
    /**
     *@Description 导入下载错误信息
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/4
     *@Time 12:03
     */
    @GetMapping("/error.ahsj")
    @ResponseBody
    public void error(HttpServletRequest request, @RequestParam("vouchers") String vouchers, HttpServletResponse response) throws Exception {
        HisImportInfo hisImportInfo = new HisImportInfo();
        hisImportInfo.setVoucher(vouchers);
        List<HisImportInfo>hisImportInfoList =  hisImportInfoService.queryHisImportInfo(hisImportInfo);
        List<String> collect = hisImportInfoList.stream().map(HisImportInfo::getUploadErrorInfo).collect(Collectors.toList());
        String path  = null;
        path = this.getClass().getClassLoader().getResource("templates/errorImport/errorInfo.txt").getPath();
        path = Constants.HIS_SYS_EXCEL_PROJECT_ERROR_FILE_URL;
        //清空txt文件数据
        FileUtil.clearInfoForFile(path);
        //将错误数据存到txt文件里
        File file = new File(path);
        FileUtil.listToFile(collect, file);
        //导出
        FileUtil.download(response,path);
    }

    /**
     *@Description 导出
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/4
     *@Time 15:18
     */

    @GetMapping("exportExcel.ahsj")
    @ResponseBody
    public void exportExcels(HttpServletRequest request, @RequestParam("param") String param, @RequestParam("idsData") Long[] idsData, HisConsumables hisConsumables,
                             HttpServletResponse response, HttpSession session, String token, String name, Double highPrice, Double lowPrice, Integer isEnable) throws Exception {
        //        Long[] ids = {1L, 2L, 4L};
        //        String param = "km";
        if (!StringUtils.isEmpty(name)) {
            hisConsumables.setName(name);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(highPrice)) {
            hisConsumables.setHighPrice(highPrice);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(lowPrice)) {
            hisConsumables.setLowPrice(lowPrice);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(isEnable)) {
            hisConsumables.setIsEnable(isEnable);
        }
        List<HisConsumables> hisConsumablesList = hisConsumablesService.selectByExportExcel(hisConsumables);
        Long[] ids=new Long[hisConsumablesList.size()];
        if(EmptyUtil.Companion.isNullOrEmpty(idsData)||idsData.length==0){
            //根据导出的条件搜索出对应的值
            for (int i = 0; i <hisConsumablesList.size() ; i++) {
                ids[i]=hisConsumablesList.get(i).getId();
            }
        }else
            ids =idsData;

        hisConsumablesService.exportExcels(ids, request, response, session,param);
    }

}
