package com.ahsj.hiscore.controller;

import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.dao.HisMedicineInfoMapper;
import com.ahsj.hiscore.dao.HisProjectMapper;
import com.ahsj.hiscore.entity.HisMedicineInfo;
import com.ahsj.hiscore.entity.HisProject;
import com.ahsj.hiscore.entity.Translate;
import com.ahsj.hiscore.entity.TranslateModel.*;
import com.ahsj.hiscore.entity.dto.MqObjectModel;
import com.ahsj.hiscore.entity.dto.OrganizationModel;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisProjectService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: TranslateController
 * <p>
 * Date:     2019/8/1 10:20
 *
 * @author XJP
 * @create 2019/8/1
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/translate")
public class TranslateController {

    @Autowired
    IOrganizationService iOrganizationService;

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    HisProjectService  hisProjectService;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    AmqpTemplate amqpTemplat;

    @Autowired
    HisProjectMapper hisProjectMapper;

    @Autowired
    HisMedicineInfoMapper hisMedicineInfoMapper;


    @PostMapping("/code.ahsj")
    public String getCode() throws Exception {
        List<SysCodeDetail> sysCodeDetails = iCodeService.lstSysCodeDetail();

        BaseLoginUser loginUser = new BaseLoginUser();
        TranslateModels translateModels = new TranslateModels();
        List<SysCodeDetailTranslate> sysCodeDetailTranslates = new ArrayList<>();
        for (SysCodeDetail sysCodeDetail : sysCodeDetails) {
            SysCodeDetailTranslate translate = new SysCodeDetailTranslate();
            BeanUtils.copyProperties(sysCodeDetail, translate);
            sysCodeDetailTranslates.add(translate);
        }
        translateModels.setUserId(loginUser.getId());
        translateModels.setSysCodeDetailTranslateList(sysCodeDetailTranslates);
        amqpTemplat.convertAndSend("com.ahsj.sysCodeDetails", JsonUtils.serialize(translateModels));
        return null;
    }

    /**
     * @return java.lang.String
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:50
     **/
    @PostMapping("/organization.ahsj")
    public String  getOrganization() throws Exception {
        List<Organization> list = iOrganizationService.getOrganizationAll();
        System.out.println("------list----->"+list.size());
        BaseLoginUser loginUser = new BaseLoginUser();
        TranslateModels translateModels = new TranslateModels();
        List<OrganizationTranslate> organizationTranslates = new ArrayList<>();
        for (Organization organization : list) {
            OrganizationTranslate translate = new OrganizationTranslate();
            BeanUtils.copyProperties(organization, translate);
            organizationTranslates.add(translate);
        }
        translateModels.setUserId(loginUser.getId());
        translateModels.setOrganizationTranslateList(organizationTranslates);
        amqpTemplat.convertAndSend("com.ahsj.iOrganizationServicesss", JsonUtils.serialize(translateModels));
        return null;
    }


    @PostMapping("/project.ahsj")
    public String  project() throws Exception {
        List<HisProject> hisProjectList1 = hisProjectMapper.queryProjectAll();
      //  List<HisProject> hisProjectList = hisProjectService.queryAll();
        System.out.println("------->"+hisProjectList1.size());
        List<Long> collect = hisProjectList1.stream().map(HisProject::getId)
                .collect(Collectors.toList());
        Translate translate = new Translate();
        translate.setTranslateType("10004");
        List<Translate> translates = iTranslateService.queryTranslate(translate);
        //根据TranslateId去重
    List<Translate> arrayList = translates.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(e -> e.getTranslateId()))), ArrayList::new)
        );
        List<Long> list = arrayList.stream().map(Translate::getTranslateId).collect(Collectors.toList());
        List<Long> plist = getLongList(collect, list);

       List<HisProject> subtractList  =  new ArrayList<>();
        for (Long aLong : plist) {
            HisProject hisProject = hisProjectMapper.selectByPrimaryKey(aLong);
            subtractList.add(hisProject);
        }
        BaseLoginUser loginUser = new BaseLoginUser();
        TranslateModels translateModels = new TranslateModels();
        List<HisProjectTranslate> translateList = new ArrayList<>();
        for (HisProject project : subtractList) {
            HisProjectTranslate projectTranslate = new HisProjectTranslate();
            BeanUtils.copyProperties(project, projectTranslate);
            translateList.add(projectTranslate);
        }
        translateModels.setUserId(loginUser.getId());
        translateModels.setHisProjectTranslates(translateList);
        amqpTemplat.convertAndSend("com.ahsj.addProjectList", JsonUtils.serialize(translateModels));
        return null;
    }


    @PostMapping("/medicineInfo.ahsj")
    public String  HisMedicineInfo() throws Exception {
        List<HisMedicineInfo> hisMedicineInfos = hisMedicineInfoMapper.queryAll();
        //  List<HisProject> hisProjectList = hisProjectService.queryAll();
        System.out.println("------->"+hisMedicineInfos.size());
        List<Long> collect = hisMedicineInfos.stream().map(HisMedicineInfo::getId)
                .collect(Collectors.toList());
        Translate translate = new Translate();
        translate.setTranslateType("10008");
        List<Translate> translates = iTranslateService.queryTranslate(translate);
        //根据TranslateId去重
        List<Translate> arrayList = translates.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(e -> e.getTranslateId()))), ArrayList::new)
        );
        List<Long> list = arrayList.stream().map(Translate::getTranslateId).collect(Collectors.toList());
        List<Long> plist = getLongList(collect, list);

        List<HisMedicineInfo> subtractList  =  new ArrayList<>();
        for (Long aLong : plist) {
            HisMedicineInfo hisMedicineInfo = hisMedicineInfoMapper.selectByPrimaryKey(aLong);
            subtractList.add(hisMedicineInfo);
        }
        BaseLoginUser loginUser = new BaseLoginUser();
        TranslateModels translateModels = new TranslateModels();
        List<HisMedicineInfoTranslate> translateList = new ArrayList<>();
        for (HisMedicineInfo hisMedicineInfo : subtractList) {
            HisMedicineInfoTranslate hisMedicineInfoTranslate = new HisMedicineInfoTranslate();
            BeanUtils.copyProperties(hisMedicineInfo, hisMedicineInfoTranslate);
            translateList.add(hisMedicineInfoTranslate);
        }
        translateModels.setUserId(loginUser.getId());
        translateModels.setHisMedicineInfoTranslates(translateList);
        amqpTemplat.convertAndSend("com.ahsj.addHisMedicineInfoList", JsonUtils.serialize(translateModels));
        return null;
    }


    /**
     * @return java.util.List<java.lang.Long>
     * @功能说明
     * @Params [listA, listB]
     * @Author XJP
     * @Date 2019/8/16
     * @Time 9:52
     **/
    public static List<Long> getLongList(List<Long> listA, List<Long> listB) {
        // 差集（扣除）
        Collection disjunction = CollectionUtils.subtract(listA, listB);
        List<Long> collect = (List<Long>) disjunction.stream().collect(Collectors.toList());
        return collect;
    }
}
