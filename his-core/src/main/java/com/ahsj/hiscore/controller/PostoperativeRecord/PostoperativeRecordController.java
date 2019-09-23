package com.ahsj.hiscore.controller.PostoperativeRecord;

import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.PostoperativeRecord;
import com.ahsj.hiscore.entity.SurgicalRecord;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.services.HisBedService;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.PostoperativeRecordService;
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
 * FileName: PostoperativeRecordController
 * <p>
 * Date:     2019/9/18 10:19
 * 术后记录
 *
 * @author XJP
 * @create 2019/9/18
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/postoperativeRecord")
public class PostoperativeRecordController extends BaseController {

    @Autowired
    PostoperativeRecordService postoperativeRecordService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    IOrganizationService iOrganizationService;

    @Autowired
    HisBedService hisBedService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明  进入术后记录新增和修改页面
     * @Params [token, postoperativeRecord]
     * @Author XJP
     * @Date 2019/9/18
     * @Time 10:24
     **/
    @GetMapping("/add/index.ahsj")
    public ModelAndView index(String token, PostoperativeRecord postoperativeRecord) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/postoperativeRecord/addOrUpdate");
        HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByNumber(postoperativeRecord.getMedicalNumber());
        Organization organization = iOrganizationService.getOrganizationById(hisHospitalManage.getDepartmentId());
        if (EmptyUtil.Companion.isNullOrEmpty(postoperativeRecord.getId())) {
            postoperativeRecord.setAge(hisHospitalManage.getAge());
            postoperativeRecord.setPatientName(hisHospitalManage.getPatientName());
            postoperativeRecord.setSexName(hisHospitalManage.getSexName());
            postoperativeRecord.setSex(hisHospitalManage.getSex());
            postoperativeRecord.setDepartmentName(organization.getName());
            postoperativeRecord.setDepartment(hisHospitalManage.getDepartmentId().intValue());
            postoperativeRecord.setBedNo(hisBedService.selectByPrimaryKey(hisHospitalManage.getBedId()).getNumber().toString());
            modelAndView.addObject("postoperativeRecord", postoperativeRecord);
            modelAndView.addObject("token", token);
            return modelAndView;
        } else {
            postoperativeRecord = postoperativeRecordService.selectByPrimaryKey(postoperativeRecord.getId());
            postoperativeRecord.setSexName(hisHospitalManage.getSexName());
            postoperativeRecord.setDepartmentName(organization.getName());
            modelAndView.addObject("postoperativeRecord", postoperativeRecord);
            modelAndView.addObject("token", token);
            return modelAndView;
        }
    }


    /**
     * @return core.message.Message
     * @功能说明  术后记录新增和修改
     * @Params [surgicalRecord]
     * @Author XJP
     * @Date 2019/9/17
     * @Time 16:15
     **/
    @PostMapping("/addOrUpdatePostoperativeRecord.ahsj")
    public Message addOrUpdatePostoperativeRecord(PostoperativeRecord postoperativeRecord) throws Exception {
        return postoperativeRecordService.addOrUpdatePostoperativeRecord(postoperativeRecord);
    }

}
