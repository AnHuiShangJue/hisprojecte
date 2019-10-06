/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisPprojectController
 * Author:   anhuishangjue
 * Date:     2019/7/10 12:01
 * Description: HisPprojectController
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.ahsj.hiscore.controller.hisProject;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.utils.*;
import com.ahsj.hiscore.entity.HisBed;
import com.ahsj.hiscore.entity.HisImportInfo;
import com.ahsj.hiscore.entity.HisProject;
import com.ahsj.hiscore.entity.HisRecordProject;
import com.ahsj.hiscore.entity.dto.ExcelUploadFile;
import com.ahsj.hiscore.entity.dto.HisProjectRes;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.services.HisImportInfoService;
import com.ahsj.hiscore.services.HisProjectService;
import com.ahsj.hiscore.services.HisRecordProjectService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
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
 * 〈一句话功能简述〉<br>
 * 〈HisPprojectController〉
 *
 * @author XJP
 * @create 2019/7/10
 * @since 1.0.0
 */

@Controller
@RequestMapping("/api/hisProject")
public class HisPprojectController extends BaseController {

    @Autowired
    HisProjectService hisProjectService;

    @Autowired
    IOrganizationService iOrganizationService;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    HisImportInfoService hisImportInfoService;

    @Autowired
    HisRecordProjectService hisRecordProjectService;


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 進入初始化頁面
     * @Params [token]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:18
     **/
    @GetMapping("/index.ahsj")
    public ModelAndView getIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/list");
        modelAndView.addObject("token", token);
        return modelAndView;
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 进新增頁面
     * @Params [token]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:18
     **/
    @GetMapping("/add.ahsj")
    public ModelAndView addView(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/add");
        modelAndView.addObject("token", token);
        modelAndView.addObject("hisProject", new HisProject());
        return modelAndView;
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 进修改页面
     * @Params [token, id]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:18
     **/
    @GetMapping("/update.ahsj")
    public ModelAndView addView(String token, @RequestParam("id") String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/hisProject/update");
        Long aLong = Long.valueOf(id);
        HisProject hisProject = hisProjectService.selectByPrimaryKey(aLong);
        modelAndView.addObject("token", token);
        modelAndView.addObject("title", "收费信息修改");
        modelAndView.addObject("hisProject", hisProject);
        return modelAndView;
    }


    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisProject>
     * @功能说明 分頁
     * @Params [model, request, hisProject]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:19
     **/
    @ResponseBody
    @PostMapping("/list.ahsj")
    public PageBean<HisProject> queryList(HisProject hisProject) throws Exception {

        if (EmptyUtil.Companion.isNullOrEmpty(hisProject.getType())) {
            PageBean<HisProject> pageBean = new PageBean<>();
            pageBean.setParameter(hisProject);
            PageBean<HisProject> hisProjectPageBean = hisProjectService.queryList(pageBean);
            return hisProjectPageBean;
        } else if (hisProject.getType() == (short) 1) {
            hisProject.setType(null);
            PageBean<HisProject> pageBean = new PageBean<>();
            pageBean.setParameter(hisProject);
            PageBean<HisProject> hisProjectPageBean = hisProjectService.queryList(pageBean);
            return hisProjectPageBean;
        } else {
            PageBean<HisProject> pageBean = new PageBean<>();
            pageBean.setParameter(hisProject);
            PageBean<HisProject> hisProjectPageBean = hisProjectService.queryList(pageBean);
            return hisProjectPageBean;
        }
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisProject>
     * @功能说明 项收费目组合 添加项目
     * @Params [model, request, hisProject]
     * @Author XJP
     * @Date 2019/8/12
     * @Time 11:34
     **/
    @ResponseBody
    @PostMapping("/lists.ahsj")
    public PageBean<HisProject> queryLists(HisProject hisProject) throws Exception {
        PageBean<HisProject> pageBean = new PageBean<>();
        pageBean.setParameter(hisProject);
        PageBean<HisProject> hisProjectPageBean = hisProjectService.queryLists(pageBean);
        return hisProjectPageBean;
    }


    /**
     * @return core.message.Message
     * @功能说明 增 加
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:19
     **/
    @ResponseBody
    @PostMapping("/addHisProject.ahsj")
    public Message addHisProject(HisProject hisProject) throws Exception {
        hisProject.setCreateUserId(getUserId());
        return hisProjectService.insertSelective(hisProject);
    }


    /**
     * @return core.message.Message
     * @功能说明 修 改
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:20
     **/
    @ResponseBody
    @PostMapping("/updateHisProject.ahsj")
    public Message updateHisProject(HisProject hisProject) throws Exception {
        hisProject.setUpdateUserId(getUserId());
        Message message = hisProjectService.updateByPrimaryKeySelective(hisProject);
        return message;
    }


    /**
     * @return core.message.Message
     * @功能说明 删 除
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:20
     **/
    @ResponseBody
    @PostMapping("/deleteHisProject.ahsj")
    public Message deleteHisProject(@RequestParam("ids[]") Long[] ids) throws Exception {
        return hisProjectService.deleteByPrimaryKey(ids);
    }

    /**
     * @return core.message.Message
     * @功能说明 项目修改状态
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/12
     * @Time 12:51
     **/
    @ResponseBody
    @PostMapping("/setDisable.ahsj")
    public Message updateSetDisable(@RequestParam("ids[]") Long[] ids) throws Exception {
        return hisProjectService.updateSetDisable(ids);
    }

    /**
     * @return java.lang.String
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:30
     **/
    @ResponseBody
    @PostMapping("/select/HisProject/id.ahsj")
    public String addHisProject(@RequestParam("id") Long id) throws Exception {
        HisProject hisProject = hisProjectService.selectByPrimaryKey(id);
        Result result = new Result();
        if (hisProject == null) {
            result.setStatus(204);
            result.setMsg("查詢失敗 无数据");
            return JsonUtils.serialize(result);
        } else {
            result.setStatus(200);
            result.setData(hisProject);
            result.setMsg("查询成功");
            return JsonUtils.serialize(result);
        }
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
        String psth = this.getClass().getClassLoader().getResource("templates/excel/import/hisproject.xlsx").getPath();
        psth = Constants.HIS_SYS_EXCEL_PROJECT_IMPORT_FILE_URL;
        FileUtil.download(response, psth);
    }

    /**
     * @return void
     * @功能说明 项目的导出 格式 excel表格
     * @Params [ids, response]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 10:17
     **/
    @GetMapping("/exportExcel.ahsj")
    @ResponseBody
    public void exportExcels(HttpServletRequest request, @RequestParam("param") String param, @RequestParam("idsData") Long[] idsData, HisProject hisProject,
                             HttpServletResponse response, HttpSession session, String token, String pinyinCode, Integer isEnable, String name, String number, Short type) throws Exception {
        if (!StringUtils.isEmpty(pinyinCode)) {
            hisProject.setPinyinCode(pinyinCode);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(isEnable)) {
            hisProject.setIsEnable(isEnable);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(name)) {
            hisProject.setName(name);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(number)) {
            hisProject.setNumber(number);
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(type)) {
            hisProject.setType(type);
        }
        hisProjectService.exportExcels(idsData, request, response, session, param, hisProject);
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
     * @return core.message.Message
     * @功能说明 项目导入 格式 excel表格
     * @Params [file]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 12:48
     **/
    @PostMapping("/importExcel.ahsj")
    @ResponseBody
    public String readExcel(@RequestParam(value = "excelFile") MultipartFile excelFile, HttpServletRequest request) throws Exception {
        List<String> errorFilelist = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        SimpleDateFormat formatOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        String dateOne = formatOne.format(new Date());
        String voucher = "ERROR" + date;
        String filename = excelFile.getOriginalFilename();
        List<HisImportInfo> importInfoList = new ArrayList<>();
        List<HisProject> projectList = ExcelUtils.readExcel("", HisProject.class, excelFile);
        //判断项目名是否为空
        for (int i = 0; i < projectList.size(); i++) {
            if (EmptyUtil.Companion.isNullOrEmpty(projectList.get(i).getName())) {
                HisImportInfo hisImportInfo = new HisImportInfo();
                String errors = dateOne + " : 第" + (i + 1) + "条记录的项目名存在空值     " + projectList.get(i).getNumber();
                projectList.get(i).setName(Constants.HIS_NAME);
                errorFilelist.add(errors);
                hisImportInfo.setUploadErrorInfo(errors);
                hisImportInfo.setUploadFileName(filename);
                hisImportInfo.setVoucher(voucher);
                importInfoList.add(hisImportInfo);
            }
        }

        //根据项目名转化简码
        for (HisProject hisProject : projectList) {
            checkHisProject(hisProject);
            if (EmptyUtil.Companion.isNullOrEmpty(hisProject.getDeptName())) {
                hisProject.setDeptName("其他");
            }
            if (StringUtils.isEmpty(hisProject.getName())) {
            }
            String firstChar = PinyinUtils.ToFirstChar(hisProject.getName());
            hisProject.setPinyinCode(firstChar);
        }

        //处理收费类型和字典空值
        for (HisProject hisProject : projectList) {
            if (hisProject.getName().indexOf("治疗") != -1) {
                hisProject.setAssitTypeName("治疗");
            } else if (hisProject.getName().indexOf("术") != -1) {
                hisProject.setAssitTypeName("手术");
            } else if (StringUtils.isEmpty(hisProject.getDeptName())) {
                hisProject.setDeptName("其他");
            } else {
                hisProject.setAssitTypeName("其他");
            }
        }

        List<HisProject> list = new ArrayList<>();
        Integer errorNum = 0;
/*        List<String> collect = projectList.stream().map(HisProject::getNumber).collect(Collectors.toList());
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
                if (StringUtils.equals(key, Constants.HIS_NAME)) {
                    errors = dateOne + " : 项目存在重复 , " + "具体在 " + errorRepeaNum + "条";
                }
                if (!StringUtils.equals(key, Constants.HIS_NAME)) {
                    errors = dateOne + " : 项目名为 : " + key + " 重复 , " + "具体在 " + errorRepeaNum + "条";
                }
                errorFilelist.add(errors);
                hisImportInfo.setUploadErrorInfo(errors);
                hisImportInfo.setUploadFileName(filename);
                hisImportInfo.setVoucher(voucher);
                importInfoList.add(hisImportInfo);
            }
        }*/

        //根据项目名称去重
 /*     List<HisProject> arrayList = projectList.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(e -> e.getNumber()))), ArrayList::new)
        );*/
        //验证数据输入是否合法，并记录日志
        for (HisProject project : projectList) {
            if (checkHisProject(project)) {
                String errors = null;
                if (StringUtils.equals(project.getName(), Constants.HIS_NAME)) {
                    errors = dateOne + " : 项目名为空的这一条记录信息存在没填写数据或者填写有误";
                }
                if (!StringUtils.equals(project.getName(), Constants.HIS_NAME)) {
                    errors = dateOne + " : 项目名为 : " + project.getName() + "的这一条记录信息存在没填写数据或者填写有误";
                }
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
                organization.setName(project.getDeptName());
                organization = iOrganizationService.getOrganizationName(organization);
                //字典
                SysCodeDetail sysCodeDetail = new SysCodeDetail();
                sysCodeDetail.setValue(project.getAssitTypeName());
                sysCodeDetail.setSysCodeType(Constants.HIS_SYS_CODE_TYPE);
                sysCodeDetail = iCodeService.getSysCodeName(sysCodeDetail);
                if (EmptyUtil.Companion.isNullOrEmpty(organization.getId()) || EmptyUtil.Companion.isNullOrEmpty(sysCodeDetail.getId())) {
                    String errors = null;
                    if (StringUtils.equals(project.getName(), Constants.HIS_NAME)) {
                        errors = dateOne + " : 项目名为空的这一条记录信息部门和类型信息填写有误";
                    }
                    if (!StringUtils.equals(project.getName(), Constants.HIS_NAME)) {
                        errors = dateOne + " : 项目名为 : " + project.getName() + "的这一条记录信息部门和类型信息填写有误";
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
                    list.add(project);
                }
            }
        }
        if (!CollectionUtils.isEmpty(list)) {
            list = list.stream().filter(e -> !StringUtils.equals(e.getNumber(), Constants.HIS_NAME)).collect(Collectors.toList());

            hisProjectService.importExcel(list);
        }
        //将错误日志存库
        File file = new File(Constants.HIS_SYS_EXCEL_PROJECT_ERROR_FILE_URL);
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
        FileUtil.listToFile(errorFilelist, file);
        excelUploadFile.setFaildNum(projectList.size() - list.size());
        excelUploadFile.setSuccessNum(list.size());
        excelUploadFile.setImportListSize(projectList.size());
        result.setStatus(200);
        result.setData(excelUploadFile);
        return JsonUtils.serialize(result);


    }

    /**
     * @return java.util.List<java.lang.Long>
     * @功能说明
     * @Params [hisProjectRes]
     * @Author XJP
     * @Date 2019/8/28
     * @Time 16:31
     **/
    @PostMapping("/search/HisProject.ahsj")
    @ResponseBody
    public List<Long> search(@RequestBody HisProjectRes hisProjectRes) throws Exception {
        List<Long> list = hisProjectService.search(hisProjectRes);
        return list;
    }

    /**
     * @return java.lang.Boolean
     * @功能说明
     * @Params [hisProject]
     * @Author XJP
     * @Date 2019/8/30
     * @Time 11:30
     **/
    public Boolean checkHisProject(HisProject hisProject) {
        if (EmptyUtil.Companion.isNullOrEmpty(hisProject.getPrice()) || EmptyUtil.Companion.isNullOrEmpty(hisProject.getName()) || EmptyUtil.Companion.isNullOrEmpty(hisProject.getNumber())
        ) {
//           System.out.println("-------"+hisProject.getNumber()+"----"+hisProject.getName()+"-----------"+hisProject.getId()+"------");
            return true;
        }
        return false;
    }

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisRecordProject>
     * @Date 2019/9/7
     * @Time 12:13
     **/
    @RequestMapping("/selectPrint.ahsj")
    @ResponseBody
    public List<HisRecordProject> selectPrint(String number) throws Exception {
        return hisRecordProjectService.selectPrint(number);
    }

    /**
     * @Description 根据就诊编号查看历史项目
     * @Params: [token]  medicalRecordId
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/9/24
     * @Time 13:57
     **/
    @RequestMapping("showProjects/index.ahsj")
    public ModelAndView showProjects(String token, String medicalRecordId) {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/medicalrecord/useProject");
        modelAndView.addObject("title", "历史项目");
        modelAndView.addObject("token", token);
        modelAndView.addObject("medicalRecordId", medicalRecordId);
        return modelAndView;
    }

    /**
     * @Description 根据就诊编号查看历史项目
     * @Params: [hisRecordProject]
     * @Author: dingli
     * @Return: core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordProject>
     * @Date 2019/9/24
     * @Time 14:33
     **/
    @RequestMapping("/getProjects/index.ahsj")
    @ResponseBody
    public PageBean<HisRecordProject> getProjects(String medicalNumber) throws Exception {
        PageBean<HisRecordProject> pageBean = new PageBean<HisRecordProject>();
        if (!EmptyUtil.Companion.isNullOrEmpty(medicalNumber)) {//查找
            HisRecordProject hisRecordProject = new HisRecordProject();
            hisRecordProject.setMedicalNumber(medicalNumber);
            pageBean.setParameter(hisRecordProject);
            pageBean = hisRecordProjectService.selectByMedicalNumber(pageBean);
        } else {
            pageBean.setData(new ArrayList<HisRecordProject>());
        }
        return pageBean;
    }
}
