package com.ahsj.hiscore.controller.SurgicalRecord;

import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.SurgicalRecord;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.services.HisBedService;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.SurgicalRecordService;
import core.controller.BaseController;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SurgicalRecordController
 * <p>
 * Date:     2019/9/17 16:09
 *
 * @author XJP
 * @create 2019/9/17
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/surgicalRecord")
public class SurgicalRecordController extends BaseController {

    @Autowired
    SurgicalRecordService surgicalRecordService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    IOrganizationService iOrganizationService;

    @Autowired
    HisBedService hisBedService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 进入手术记录新增和修改页面
     * @Params [token, surgicalRecord]
     * @Author XJP
     * @Date 2019/9/17
     * @Time 16:14
     **/
    @GetMapping("/add/index.ahsj")
    public ModelAndView index(String token, SurgicalRecord surgicalRecord) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/surgicalRecord/addOrUpdate");
        HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByNumber(surgicalRecord.getMedicalNumber());
        Organization organization = iOrganizationService.getOrganizationById(hisHospitalManage.getDepartmentId());
        if (EmptyUtil.Companion.isNullOrEmpty(surgicalRecord.getId())) {
            surgicalRecord.setAge(hisHospitalManage.getAge());
            surgicalRecord.setPatientName(hisHospitalManage.getPatientName());
            surgicalRecord.setSexName(hisHospitalManage.getSexName());
            surgicalRecord.setSex(hisHospitalManage.getSex());
            surgicalRecord.setDepartmentName(organization.getName());
            surgicalRecord.setDepartment(hisHospitalManage.getDepartmentId().intValue());
            surgicalRecord.setBedNo(hisBedService.selectByPrimaryKey(hisHospitalManage.getBedId()).getNumber().toString());
            modelAndView.addObject("surgicalRecord", surgicalRecord);
            modelAndView.addObject("token", token);
            return modelAndView;
        } else {
            surgicalRecord = surgicalRecordService.selectByPrimaryKey(surgicalRecord.getId());
            surgicalRecord.setSexName(hisHospitalManage.getSexName());
            surgicalRecord.setDepartmentName(organization.getName());
            modelAndView.addObject("surgicalRecord", surgicalRecord);
            modelAndView.addObject("token", token);
            return modelAndView;
        }
    }

    /**
     * @return core.message.Message
     * @功能说明  手术记录新增和修改
     * @Params [surgicalRecord]
     * @Author XJP
     * @Date 2019/9/17
     * @Time 16:15
     **/
    @PostMapping("/addOrUpdateSurgicalRecord.ahsj")
    public Message addOrUpdateSurgicalRecord(SurgicalRecord surgicalRecord) throws Exception {
        return surgicalRecordService.addOrUpdateSurgicalRecord(surgicalRecord);
    }


}
