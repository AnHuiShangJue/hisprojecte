package com.ahsj.hiscore.controller.prescription;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.utils.ExcelUtils;
import com.ahsj.hiscore.common.utils.FileUtil;
import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.common.utils.Result;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.dto.ExcelUploadFile;
import com.ahsj.hiscore.services.HisImportInfoService;
import com.ahsj.hiscore.services.HisPharmacyDetailService;
import com.ahsj.hiscore.services.HisPrescriptionMedicineService;
import com.ahsj.hiscore.services.HisPrescriptionService;
import com.alibaba.fastjson.JSON;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/prescription/")
public class HisPrescriptionController extends BaseController {

    @Autowired
    HisPrescriptionService hisPrescriptionService;

    @Autowired
    HisPrescriptionMedicineService hisPrescriptionMedicineService;

    @Autowired
    HisImportInfoService hisImportInfoService;

    @Autowired
    HisPharmacyDetailService hisPharmacyDetailService;

    /**
     * 功能说明：list初始化
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-20 22:20
     */
    @RequestMapping("list/index.ahsj")
    ModelAndView UserIndex(String token)throws Exception {
        hisPrescriptionService.searchIsAvailable();
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/prescription/list");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * 功能说明：返回药方及节点
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-21 20:58
     */
    @RequestMapping("treeCode.ahsj")
    @ResponseBody
    public List<TreeEntity> treeCode(Integer lv) throws Exception {
        hisPrescriptionService.searchIsAvailable();
        if (lv == null) {
            TreeEntity treeEntity = new TreeEntity();
            treeEntity.setId("1");
            treeEntity.setpId("0");
            treeEntity.setName("药方菜单");
            treeEntity.setIsParent(true);
            treeEntity.setOpen(false);
            List<TreeEntity> lst = new ArrayList<>();
            lst.add(treeEntity);
            return lst;
        }
        return hisPrescriptionService.selectTrreCode();
    }

    /**
     * 功能说明：列表初始化
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-23 11:20
     */
    @RequestMapping("list.ahsj")
    @ResponseBody
    public PageBean<HisPrescription> list(HisPrescription hisPrescription) throws Exception {
        hisPrescriptionService.searchIsAvailable();
        PageBean<HisPrescription> pageEntity = new PageBean<HisPrescription>();
        pageEntity.setParameter(hisPrescription);
        return hisPrescriptionService.list(pageEntity);
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescription>
     * @Description 根据药方ID显示对应药品详细信息
     * @Params [hisPrescription]
     * @Author zhushixiang
     * @Date 2019-07-13
     * @Time 17:13
     **/
    @RequestMapping("listForDetails.ahsj")
    @ResponseBody
    public PageBean<HisPrescriptionMedicine> listForDetails(HisPrescriptionMedicine hisPrescriptionMedicine) throws Exception {
        hisPrescriptionService.searchIsAvailable();
        PageBean<HisPrescriptionMedicine> pageBean = new PageBean<HisPrescriptionMedicine>();
        pageBean.setParameter(hisPrescriptionMedicine);
        return hisPrescriptionMedicineService.listForDetails(pageBean);
    }

    /**
     * 功能说明：新增导航
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-23 13:13
     */
    @RequestMapping("update/index.ahsj")
    public ModelAndView saveIndex(HisPrescription hisPrescription) throws Exception {
        hisPrescriptionService.searchIsAvailable();
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/prescription/update");
        HisPrescription tmp = new HisPrescription();
        if (!EmptyUtil.Companion.isNullOrEmpty(hisPrescription.getId())) {
            tmp = hisPrescriptionService.updateInit(hisPrescription.getId().toString());
        } else {
            tmp.setParentId(hisPrescription.getParentId());
        }
        modelAndView.addObject("prescriptionInfo", tmp);
        modelAndView.addObject("token", hisPrescription.getToken());
        return modelAndView;
    }


    /**
     * 功能说明：新增、更新药方
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-19 15:05
     */
    @RequestMapping("saveOrUpdate.ahsj")
    @ResponseBody
    public Message saveOrUpdate(HisPrescription hisPrescription) {
        return hisPrescriptionService.saveOrUpdate(hisPrescription);
    }

    /**
     * 功能说明：查找所有药方
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-19 16:05
     */
    @RequestMapping("selectAll.ahsj")
    @ResponseBody
    public List<HisPrescription> selectAll()throws Exception {
        hisPrescriptionService.searchIsAvailable();
        return hisPrescriptionService.selectAll();
    }

    /**
     * 功能说明：根据id 查找药方
     *
     * @param [id]
     * @return com.ahsj.hiscore.entity.HisPrescription
     * @author Eric
     * @data 2019-06-19 16:10
     */

    @RequestMapping("selectByPrescriptionId.ahsj")
    @ResponseBody
    public HisPrescription selectByPrescriptionId(Long id) throws Exception{
        hisPrescriptionService.searchIsAvailable();
        return hisPrescriptionService.selectByPrescriptionId(id);
    }

    /**
     * 功能说明：删除药方
     *
     * @param [id]
     * @return core.message.Message
     * @author Eric
     * @data 2019-06-19 17:02
     */

    @RequestMapping("delete.ahsj")
    @ResponseBody
    public Message delete(Long[] id) {
        return hisPrescriptionService.delete(id);
    }


    /**
     * 功能说明：药品初始化
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-23 14:58
     */
    @RequestMapping("medicine/list.ahsj")
    public ModelAndView medicineIndex(String token)throws Exception{
        hisPrescriptionService.searchIsAvailable();
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/prescription/medicine/list");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * 功能说明：药品节点
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-23 16:09
     */
    @RequestMapping("medicine/treeCode.ahsj")
    @ResponseBody
    public List<TreeEntity> medicineTreeCode(Integer lv) throws Exception {
        hisPrescriptionService.searchIsAvailable();
        return hisPrescriptionMedicineService.selectTreeCode();
    }

    /**
     * 功能说明：药品列表初始化
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-23 17:56
     */
    @RequestMapping("medicine/page/list.ahsj")
    @ResponseBody
    public PageBean<HisPrescriptionMedicine> list(HisPrescriptionMedicine hisPrescriptionMedicine) throws Exception {
        hisPrescriptionService.searchIsAvailable();
        PageBean<HisPrescriptionMedicine> pageEntity = new PageBean<HisPrescriptionMedicine>();
        pageEntity.setParameter(hisPrescriptionMedicine);
        return hisPrescriptionMedicineService.list(pageEntity);
    }

    /**
     * 功能说明：药品添加导航
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-24 11:42
     */
    @RequestMapping("medicine/update/index.ahsj")
    public ModelAndView medicineIndex(HisPrescriptionMedicine hisPrescriptionMedicine)throws Exception{
        hisPrescriptionService.searchIsAvailable();
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/prescription/medicine/update");
        HisPrescriptionMedicine tmp = new HisPrescriptionMedicine();

        if (!EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getId())) {
            tmp = hisPrescriptionMedicineService.updateInit(hisPrescriptionMedicine.getId());
        } else {
            tmp.setParentId(hisPrescriptionMedicine.getParentId());
        }
        modelAndView.addObject("medicineInfo", tmp);
        modelAndView.addObject("token", hisPrescriptionMedicine.getToken());
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Description 为药方添加药品信息
     * @Params [token, prescriptionId]
     * @Author zhushixiang
     * @Date 2019-07-13
     * @Time 14:09
     **/
    @RequestMapping("medicine/saveOrUpdate/index.ahsj")
    public ModelAndView saveOrUpdateMedicineDetails(String token,
                                                    @RequestParam(value = "parentId", required = true) String prescriptionId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/prescription/medicine/saveOrUpdate");
        List<HisPrescriptionMedicine> hisPrescriptionMedicineList = hisPrescriptionMedicineService.selectByPrescriptionIdForTable(prescriptionId);

        if (hisPrescriptionMedicineList.size() == 0) {
            modelAndView.addObject("prescriptionId", prescriptionId);
        }
        modelAndView.addObject("hisPrescriptionMedicineList", hisPrescriptionMedicineList);
        modelAndView.addObject("prescriptionId", prescriptionId);
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     * 功能说明：添加、更新药品
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-24 13:02
     */
    @RequestMapping("medicine/saveOrUpdate.ahsj")
    @ResponseBody
    public Message medicineSaveOrUpdate(HisPrescriptionMedicine hisPrescriptionMedicine)throws Exception {
        return hisPrescriptionMedicineService.saveOrUpdate(hisPrescriptionMedicine);
    }

    /**
     * 为药方添加药品信息
     *
     * @return core.message.Message
     * @Description
     * @Params [hisPrescriptionMedicine]
     * @Author zhushixiang
     * @Date 2019-07-13
     * @Time 14:07
     **/
    @RequestMapping("medicine/saveOrUpdateForDetails.ahsj")
    @ResponseBody
    public Message saveOrUpdateForDetails(String token,
                                          @RequestParam(value = "ids", required = true) Long[] ids,
                                          @RequestParam(value = "prescriptionId", required = true) String prescriptionId,
                                          @RequestParam(value = "nums", required = true) Integer[] nums,
                                          @RequestParam(value = "descriptions", required = true) String[] descriptions,
                                          @RequestParam(value = "remark", required = true) String[] remark
    ) throws Exception {
        boolean flag = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || EmptyUtil.Companion.isNullOrEmpty(nums[i])) {
                flag = false;
                break;
            }
        }
        if (!flag)
            return MessageUtil.createMessage(false, "请输入大于0的数字");
        return hisPrescriptionMedicineService.saveOrUpdateForDetails(ids, prescriptionId, nums, descriptions, remark);
    }

    /**
     * 功能说明：给药方添加药瓶
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-19 17:03
     */
    @RequestMapping("addMedical.ahsj")
    @ResponseBody
    public Message addMedical(Long prescriptionId, HisPrescriptionMedicine[] hisPrescriptionMedicines) {
        return hisPrescriptionService.addMedical(prescriptionId, hisPrescriptionMedicines);
    }

    /**
     * 功能说明：删除药方中药品
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-19 18:07
     */
    @RequestMapping("deleteMedicine.ahsj")
    @ResponseBody
    public Message deleteMedicine(Long[] id) {
        return hisPrescriptionService.deleteMedicine(id);
    }

    /**
     * @param hisPrescriptionMedicine
     * @Description 根据药方id查询明细
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescriptionMedicine>
     * @Date 2019/8/27
     * @Time 17:48
     **/
    @RequestMapping("listDetails.ahsj")
    @ResponseBody
    public PageBean<HisPrescriptionMedicine> listDetails(HisPrescriptionMedicine hisPrescriptionMedicine, PageBean<HisPrescriptionMedicine> pageBean) throws Exception {
        hisPrescriptionService.searchIsAvailable();
        pageBean.setParameter(hisPrescriptionMedicine);
        return hisPrescriptionMedicineService.listDetail(pageBean);
    }

    /**
     * @param request
     * @param response
     * @param session
     * @Description 导出药方
     * @Author: dingli
     * @return: void
     * @Date 2019/9/2
     * @Time 10:33
     **/
    @RequestMapping("exportExcel")
    @ResponseBody
    public void exportExcels(@RequestParam(value = "ids", required = false) Long[] ids, String param, Long parentId,
                             HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        HisPrescriptionMedicine hisPrescriptionMedicine = new HisPrescriptionMedicine();
        hisPrescriptionMedicine.setPrescriptionId(parentId.toString());
        if(!EmptyUtil.Companion.isNullOrEmpty(ids)){hisPrescriptionMedicine.setIds(ids);}
        hisPrescriptionMedicineService.exportExcels(hisPrescriptionMedicine,param,request,response,session);

    }

    /**
     *@Description 药方信息excel导入
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
        List<HisPrescriptionMedicine> hisPrescriptionMedicineList = ExcelUtils.readExcel("", HisPrescriptionMedicine.class, excelFile);
        List<HisPrescriptionMedicine> listForParentMenu = new ArrayList<>();//药方父菜单集合
        List<HisPrescriptionMedicine> listForName = new ArrayList<>();//药方名称集合
        List<HisPrescriptionMedicine> list = new ArrayList<>();//药方明细集合
        Integer errorNum = 0;
        String parentMenu="";//记录最近父菜单名称
        String name="";//记录最近药方名称
        for (HisPrescriptionMedicine hisPrescriptionMedicine : hisPrescriptionMedicineList) {
            //当父菜单不为空时，其他同行的输入框均不能输入值
            if(!EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getParentMenu()) ){
                if(!EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getName())||!EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getDescrption())||!EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getDrugsNumb())
                        ||!EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getDrugsName())||!EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getDrugsSpec())
                        ||!EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getCount()) ||!EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getDescription())) {
                    String errors = date1 + " : 药方父菜单为 : " + hisPrescriptionMedicine.getParentMenu() + "的那一条记录信息不能填写其他信息";
                    errorFilelist.add(errors);
                    HisImportInfo hisImportInfo = new HisImportInfo();
                    hisImportInfo.setUploadErrorInfo(errors);
                    hisImportInfo.setUploadFileName(filename);
                    hisImportInfo.setVoucher(voucher);
                    importInfoList.add(hisImportInfo);
                    errorNum++;
                }else {
                    parentMenu = hisPrescriptionMedicine.getParentMenu();
                    listForParentMenu.add(hisPrescriptionMedicine);
                }
            }else {
                //当父菜单为空，且药方名称不为空时，除药方名称与药方描述外其他同行输入框均不能输入值
                if(!EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getName())){
                    if(!EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getDrugsNumb()) ||!EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getDrugsName())
                            ||!EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getDrugsSpec()) ||!EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getCount()) ||!EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getDescription())){
                        String errors = date1 + " : 药方名称为 : " + hisPrescriptionMedicine.getName() + "的那一条记录信息只能填写药方名称与药方描述";
                        errorFilelist.add(errors);
                        HisImportInfo hisImportInfo = new HisImportInfo();
                        hisImportInfo.setUploadErrorInfo(errors);
                        hisImportInfo.setUploadFileName(filename);
                        hisImportInfo.setVoucher(voucher);
                        importInfoList.add(hisImportInfo);
                        errorNum++;
                    }else {
                        if(EmptyUtil.Companion.isNullOrEmpty(parentMenu) || StringUtils.equals(parentMenu,"")){
                            String errors = date1 + " : 药方名称为 : " + hisPrescriptionMedicine.getName() + "的那一条记录信息找不到匹配的父菜单";
                            errorFilelist.add(errors);
                            HisImportInfo hisImportInfo = new HisImportInfo();
                            hisImportInfo.setUploadErrorInfo(errors);
                            hisImportInfo.setUploadFileName(filename);
                            hisImportInfo.setVoucher(voucher);
                            importInfoList.add(hisImportInfo);
                            errorNum++;
                        }else {
                            name = hisPrescriptionMedicine.getName();
                            hisPrescriptionMedicine.setParentMenu(parentMenu);
                            listForName.add(hisPrescriptionMedicine);
                        }

                    }
                    //药方描述未输入时为其赋值“无”
                    if(EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getDescrption()))
                        hisPrescriptionMedicine.setDescrption("无");
                }else {
                    HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailService.selectByDrugsNumb(hisPrescriptionMedicine.getDrugsNumb());
                    if(EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail)){
                        String errors = date1 + " : 药库表中不存在药品编号为 : " + hisPrescriptionMedicine.getDrugsNumb() + "的药品";
                        errorFilelist.add(errors);
                        HisImportInfo hisImportInfo = new HisImportInfo();
                        hisImportInfo.setUploadErrorInfo(errors);
                        hisImportInfo.setUploadFileName(filename);
                        hisImportInfo.setVoucher(voucher);
                        importInfoList.add(hisImportInfo);
                        errorNum++;
                    }else {
                        if (EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getDrugsNumb()) || EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getCount()) || EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicine.getDescription())) {
                            String errors = date1 + " : 药品编号为 : " + hisPrescriptionMedicine.getDrugsNumb() + "的那一条记录信息药品信息填写不完整";
                            errorFilelist.add(errors);
                            HisImportInfo hisImportInfo = new HisImportInfo();
                            hisImportInfo.setUploadErrorInfo(errors);
                            hisImportInfo.setUploadFileName(filename);
                            hisImportInfo.setVoucher(voucher);
                            importInfoList.add(hisImportInfo);
                            errorNum++;
                        } else {
                            if (EmptyUtil.Companion.isNullOrEmpty(name) || StringUtils.equals(name, "")) {
                                String errors = date1 + " : 药品编号为 : " + hisPrescriptionMedicine.getDrugsNumb() + "的那一条记录信息找不到匹配的药方";
                                errorFilelist.add(errors);
                                HisImportInfo hisImportInfo = new HisImportInfo();
                                hisImportInfo.setUploadErrorInfo(errors);
                                hisImportInfo.setUploadFileName(filename);
                                hisImportInfo.setVoucher(voucher);
                                importInfoList.add(hisImportInfo);
                                errorNum++;
                            } else {
                                hisPrescriptionMedicine.setParentMenu(parentMenu);
                                hisPrescriptionMedicine.setName(name);
                                list.add(hisPrescriptionMedicine);
                            }
                        }
                    }

                }
            }
        }
        if (!CollectionUtils.isEmpty(list)||!CollectionUtils.isEmpty(listForParentMenu)||!CollectionUtils.isEmpty(listForName)) {
            hisPrescriptionMedicineService.importExcel(listForParentMenu,listForName,list);
        }
        //将错误日志存库
        String psth =this.getClass().getClassLoader().getResource("templates/errorImport/HisPrescriptionMedicineErrorInfo.txt").getPath();
        psth = Constants.HIS_SYS_EXCEL_DOCTOR_ERROR_FILE_URL;
        File file = new File(psth);
        hisImportInfoService.inserHisImportInfo(importInfoList);
        String vouchers = "";
        if(importInfoList.size() > 0)
             vouchers= importInfoList.get(0).getVoucher();
        //错误日志集合
        List<String> errorList = importInfoList.stream().map(HisImportInfo::getUploadErrorInfo).collect(Collectors.toList());

        FileUtil.listToFile(errorFilelist, file);

        Result result = new Result();
        ExcelUploadFile excelUploadFile = new ExcelUploadFile();
        excelUploadFile.setFaildNum(hisPrescriptionMedicineList.size() - list.size()-listForName.size()-listForParentMenu.size());
        excelUploadFile.setSuccessNum(list.size()+listForName.size()+listForParentMenu.size());
        excelUploadFile.setErrorList(errorList);
        if(!EmptyUtil.Companion.isNullOrEmpty(vouchers) || !StringUtils.equals(vouchers,""))
            excelUploadFile.setVouchers(vouchers);
        excelUploadFile.setImportListSize(hisPrescriptionMedicineList.size());
        excelUploadFile.setErrorListSize( errorList.size());
        result.setStatus(200);
        result.setData(excelUploadFile);
        return JsonUtils.serialize(result);
    }

    /**
     *@功能说明  下载导入错误信息
     *@Params [request, param, idsData, hisProject, response, session, token, pinyinCode, isEnable]
     *@return void
     *@Author XJP
     *@Date 2019/9/3
     *@Time 15:19
     **/
    @GetMapping("/error.ahsj")
    @ResponseBody
    public void error(HttpServletRequest request, @RequestParam("vouchers") String vouchers, HttpServletResponse response) throws Exception {
        HisImportInfo hisImportInfo = new HisImportInfo();
        hisImportInfo.setVoucher(vouchers);
        List<HisImportInfo>hisImportInfoList =  hisImportInfoService.queryHisImportInfo(hisImportInfo);
        List<String> collect = hisImportInfoList.stream().map(HisImportInfo::getUploadErrorInfo).collect(Collectors.toList());
        String psth = this.getClass().getClassLoader().getResource("templates/errorImport/errorInfo.txt").getPath() ;
        psth = Constants.HIS_SYS_EXCEL_DOCTOR_ERROR_FILE_URL;
        //清空txt文件数据
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
    public void exportExcel(HttpServletResponse response) throws Exception {
        String psth = this.getClass().getClassLoader().getResource("templates/excel/import/hisPrescriptionMedicine.xlsx").getPath();
        psth = Constants.HIS_SYS_EXCEL_PRESCRIPTION_IMPORT_FILE_URL;
        FileUtil.download(response,psth);
    }

    /**
     *@Description 查询所有药方
     *@Params [departmentId, token]
     *@return java.lang.String
     *@Author zhushixiang
     *@Date 2019-09-14
     *@Time 14:09
    **/
    @RequestMapping(value = "getPrescription.ahsj", method = {RequestMethod.GET})
    @ResponseBody
    public String  getPrescription(String token) throws Exception{
        hisPrescriptionService.searchIsAvailable();
        String ab = JSON.toJSONString(hisPrescriptionService.selectPrescription());
        return  ab;
    }

    /**
     *@Description 查看门诊病历模板中的药方明细
     *@Params [token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-09-19
     *@Time 17:33
    **/
    @RequestMapping("medicine/viewDetailInMedicalTemplate/index.ahsj")
    ModelAndView viewDetailInMedicalTemplate(String token,@RequestParam(value = "prescriptionId",required = true)Long prescriptionId)throws Exception {
        hisPrescriptionService.searchIsAvailable();
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/prescription/medicine/viewDetailInMedicalTemplate");
        modelAndView.addObject("title", "查看药方明细（View prescription details）");
        modelAndView.addObject("token", token);
        modelAndView.addObject("prescriptionId", prescriptionId);
        return modelAndView;
    }

    /**
     *@Description 查看门诊病历模板中的药方明细
     *@Params [hisPrescription]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisPrescription>
     *@Author zhushixiang
     *@Date 2019-09-19
     *@Time 17:41
    **/
    @RequestMapping("medicine/viewDetailInMedicalTemplate.ahsj")
    @ResponseBody
    public PageBean<HisPrescriptionMedicine> viewDetailInMedicalTemplate(HisPrescriptionMedicine hisPrescriptionMedicine) throws Exception {
        hisPrescriptionService.searchIsAvailable();
        PageBean<HisPrescriptionMedicine> pageEntity = new PageBean<HisPrescriptionMedicine>();
        pageEntity.setParameter(hisPrescriptionMedicine);
        return hisPrescriptionMedicineService.viewDetailInMedicalTemplate(pageEntity);
    }


}
