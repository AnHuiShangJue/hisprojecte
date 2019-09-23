package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.TranslateModel.*;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.entity.sys.SysCode;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: TranslateInfoServiceImpl
 * <p>
 * Date:     2019/8/8 16:41
 *
 * @author XJP
 * @create 2019/8/8
 * @since 1.0.0
 */

@Service
public class TranslateInfoServiceImpl implements TranslateInfoService {

    private Logger log = LoggerFactory.getLogger(TranslateInfoServiceImpl.class.getSimpleName());

    @Autowired
    AmqpTemplate amqpTemplat;

    @Autowired
    IOrganizationService iOrganizationService;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    HisProjectService hisProjectService;

    @Autowired
    HisMedicineInfoService hisMedicineInfoService;

    @Autowired
    HisMedicationDetailsService hisMedicationDetailsService;

    @Autowired
    HisAnkleTemplateService hisAnkleTemplateService;

    @Autowired
    HisConsumablesBuyplanDetailsService hisConsumablesBuyplanDetailsService;

    @Autowired
    HisConsumablesBuyplanService hisConsumablesBuyplanService;

    @Autowired
    HisConsumablesDetailsService hisConsumablesDetailsService;

    @Autowired
    HisConsumablesService hisConsumablesService;

    /**
     * @return void
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:55
     **/
    @Override
    @Transactional(readOnly = true)
    public void TranslateInfojob() throws Exception {
        Date date = new Date();//当前时间
        Date beforeDate = new Date();

        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -1); //设置为前一天
        beforeDate = calendar.getTime(); //得到前一天的时间

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式

        String defaultStartDate = sdf.format(beforeDate); //格式化前一天
        Date parse = sdf.parse(defaultStartDate);
        String defaultEndDate = sdf.format(date); //格式化当前时间

        HisProject project = new HisProject();
        project.setCreateDate(date);
        List<HisProject> projectList = hisProjectService.queryTranslateInfoByDate(project);
        log.info("HisProject集合数量是 : {}",projectList.size());
        List<HisProjectTranslate> projectTranslateList = new ArrayList<>();
        for (HisProject hisProject : projectList) {
            HisProjectTranslate hisProjectTranslate = new HisProjectTranslate();
            BeanUtils.copyProperties(hisProject, hisProjectTranslate);
            projectTranslateList.add(hisProjectTranslate);
        }

        HisMedicineInfo hisMedicineInfo = new HisMedicineInfo();
        hisMedicineInfo.setCreateDate(date);
        List<HisMedicineInfo> hisMedicineInfos = hisMedicineInfoService.queryTranslateInfoByDate(hisMedicineInfo);
        log.info("HisMedicineInfo集合数量是 : {}",hisMedicineInfos.size());
        List<HisMedicineInfoTranslate> hisMedicineInfoTranslates = new ArrayList<>();
        for (HisMedicineInfo medicineInfo : hisMedicineInfos) {
            HisMedicineInfoTranslate hisMedicineInfoTranslate = new HisMedicineInfoTranslate();
            BeanUtils.copyProperties(medicineInfo, hisMedicineInfoTranslate);
            hisMedicineInfoTranslates.add(hisMedicineInfoTranslate);
        }

        HisMedicationDetails hisMedicationDetails = new HisMedicationDetails();
        hisMedicationDetails.setCreateDate(date);
        List<HisMedicationDetails> hisMedicationDetails1 = hisMedicationDetailsService.queryTranslateInfoByDate(hisMedicationDetails);
        log.info("HisMedicationDetails集合数量是 : {}",hisMedicationDetails1.size());
        List<HisMedicationDetailsTranslate> medicineInfoTranslates = new ArrayList<>();
        for (HisMedicationDetails medicationDetails : hisMedicationDetails1) {
            HisMedicationDetailsTranslate hisMedicationDetailsTranslate = new HisMedicationDetailsTranslate();
            BeanUtils.copyProperties(medicationDetails, hisMedicationDetailsTranslate);
            medicineInfoTranslates.add(hisMedicationDetailsTranslate);
        }

        HisAnkleTemplate hisAnkleTemplate = new HisAnkleTemplate();
        hisAnkleTemplate.setCreateDate(date);
        List<HisAnkleTemplate> hisAnkleTemplates = hisAnkleTemplateService.queryTranslateInfoByDate(hisAnkleTemplate);
        log.info("HisAnkleTemplate集合数量是 : {}",hisAnkleTemplates.size());
        List<HisAnkleTemplateTranslate> hisMedicineInfoTranslateArrayList = new ArrayList<>();
        for (HisAnkleTemplate ankleTemplate : hisAnkleTemplates) {
            HisAnkleTemplateTranslate hisAnkleTemplateTranslate = new HisAnkleTemplateTranslate();
            BeanUtils.copyProperties(ankleTemplate, hisAnkleTemplateTranslate);
            hisMedicineInfoTranslateArrayList.add(hisAnkleTemplateTranslate);
        }


        HisConsumablesBuyplanDetails hisConsumablesBuyplanDetails = new HisConsumablesBuyplanDetails();
        hisConsumablesBuyplanDetails.setCreateDate(date);
        List<HisConsumablesBuyplanDetails> hisConsumablesBuyplanDetails1 = hisConsumablesBuyplanDetailsService.queryTranslateInfoByDate(hisConsumablesBuyplanDetails);
        log.info("HisConsumablesBuyplanDetails集合数量是 : {}",hisConsumablesBuyplanDetails1.size());
        List<HisConsumablesBuyplanDetailsTranslate> hisMedicineInfoTranslateList = new ArrayList<>();
        for (HisConsumablesBuyplanDetails consumablesBuyplanDetails : hisConsumablesBuyplanDetails1) {
            HisConsumablesBuyplanDetailsTranslate hisConsumablesBuyplanDetailsTranslate = new HisConsumablesBuyplanDetailsTranslate();
            BeanUtils.copyProperties(consumablesBuyplanDetails, hisConsumablesBuyplanDetailsTranslate);
            hisMedicineInfoTranslateList.add(hisConsumablesBuyplanDetailsTranslate);
        }

        HisConsumablesBuyplan hisConsumablesBuyplan = new HisConsumablesBuyplan();
        hisConsumablesBuyplan.setCreateDate(date);
        List<HisConsumablesBuyplan> hisConsumablesBuyplans = hisConsumablesBuyplanService.queryTranslateInfoByDate(hisConsumablesBuyplan);
        log.info("HisConsumablesBuyplan集合数量是 : {}",hisConsumablesBuyplans.size());
        List<HisConsumablesBuyplanTranslate> medicineInfoTranslateList = new ArrayList<>();
        for (HisConsumablesBuyplan consumablesBuyplan : hisConsumablesBuyplans) {
            HisConsumablesBuyplanTranslate hisConsumablesBuyplanTranslate = new HisConsumablesBuyplanTranslate();
            BeanUtils.copyProperties(consumablesBuyplan, hisConsumablesBuyplanTranslate);
            medicineInfoTranslateList.add(hisConsumablesBuyplanTranslate);
        }

        HisConsumablesDetails hisConsumablesDetails = new HisConsumablesDetails();
        hisConsumablesDetails.setCreateDate(date);
        List<HisConsumablesDetails> consumablesDetailsList = hisConsumablesDetailsService.queryTranslateInfoByDate(hisConsumablesDetails);
        log.info("HisConsumablesDetails集合数量是 : {}",consumablesDetailsList.size());
        List<HisConsumablesDetailsTranslate> medicineInfoTranslateArrayList = new ArrayList<>();
        for (HisConsumablesDetails consumablesDetails : consumablesDetailsList) {
            HisConsumablesDetailsTranslate hisConsumablesDetailsTranslate = new HisConsumablesDetailsTranslate();
            BeanUtils.copyProperties(consumablesDetails, hisConsumablesDetailsTranslate);
            medicineInfoTranslateArrayList.add(hisConsumablesDetailsTranslate);
        }

        HisConsumables hisConsumables = new HisConsumables();
        hisConsumables.setCreateDate(date);
        List<HisConsumables> hisConsumables1 = hisConsumablesService.queryTranslateInfoByDate(hisConsumables);
        log.info("HisConsumables集合数量是 : {}",hisConsumables1.size());
        List<HisConsumablesTranslate> infoTranslateList = new ArrayList<>();
        for (HisConsumables consumables : hisConsumables1) {
            HisConsumablesTranslate translate = new HisConsumablesTranslate();
            BeanUtils.copyProperties(consumables, translate);
            infoTranslateList.add(translate);
        }

        Organization organization = new Organization();
        organization.setCreateDate(date);
        List<Organization> organizations = iOrganizationService.queryTranslateInfoByDate(organization);
        log.info("Organization集合数量是 : {}",organizations.size());
        List<OrganizationTranslate> organizationTranslates = new ArrayList<>();
        for (Organization organization1 : organizations) {
            OrganizationTranslate translate = new OrganizationTranslate();
            BeanUtils.copyProperties(organization1, translate);
            organizationTranslates.add(translate);
        }

        SysCode sysCode = new SysCode();
        sysCode.setCreateDate(date);
        List<SysCode> sysCodes = iCodeService.queryTranslateInfoByDate(sysCode);
        log.info("SysCode集合数量是 : {}",sysCodes.size());
        List<SysCodeTranslate> sysCodeTranslates = new ArrayList<>();
        for (SysCode sysCode1 : sysCodes) {
            SysCodeTranslate translate = new SysCodeTranslate();
            BeanUtils.copyProperties(sysCode1, translate);
            sysCodeTranslates.add(translate);
        }

        SysCodeDetail sysCodeDetail = new SysCodeDetail();
        sysCodeDetail.setCreateDate(date);
        List<SysCodeDetail> sysCodeDetails = iCodeService.queryTranslateInfoByDate(sysCodeDetail);
        log.info("SysCodeDetail集合数量是 : {}",sysCodeDetails.size());
        List<SysCodeDetailTranslate> sysCodeDetailTranslates = new ArrayList<>();
        for (SysCodeDetail sysCodeDetail1 : sysCodeDetails) {
            SysCodeDetailTranslate translate = new SysCodeDetailTranslate();
            BeanUtils.copyProperties(sysCodeDetail1, translate);
            sysCodeDetailTranslates.add(translate);
        }

        TranslateInfoModels translateInfoModels = new TranslateInfoModels();
        translateInfoModels.setHisMedicineInfoTranslateArrayList(hisMedicineInfoTranslateArrayList);
        translateInfoModels.setHisMedicineInfoTranslateList(hisMedicineInfoTranslateList);
        translateInfoModels.setHisMedicineInfoTranslates(hisMedicineInfoTranslates);
        translateInfoModels.setInfoTranslateList(infoTranslateList);
        translateInfoModels.setMedicineInfoTranslateList(medicineInfoTranslateList);
        translateInfoModels.setMedicineInfoTranslates(medicineInfoTranslates);
        translateInfoModels.setProjectTranslateList(projectTranslateList);
        translateInfoModels.setMedicineInfoTranslateArrayList(medicineInfoTranslateArrayList);
        translateInfoModels.setOrganizationTranslates(organizationTranslates);
        translateInfoModels.setSysCodeDetailTranslates(sysCodeDetailTranslates);
        translateInfoModels.setSysCodeTranslates(sysCodeTranslates);
        translateInfoModels.setUserId(1L);

        amqpTemplat.convertAndSend("com.ahsj.addTranslateInfoModels", JsonUtils.serialize(translateInfoModels));

    }
}
