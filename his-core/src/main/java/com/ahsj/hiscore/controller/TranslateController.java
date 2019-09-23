package com.ahsj.hiscore.controller;

import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.entity.TranslateModel.HisConsumablesTranslate;
import com.ahsj.hiscore.entity.TranslateModel.OrganizationTranslate;
import com.ahsj.hiscore.entity.TranslateModel.SysCodeDetailTranslate;
import com.ahsj.hiscore.entity.TranslateModel.TranslateModels;
import com.ahsj.hiscore.entity.dto.MqObjectModel;
import com.ahsj.hiscore.entity.dto.OrganizationModel;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.feign.ITranslateService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    ICodeService iCodeService;

    @Autowired
    AmqpTemplate amqpTemplat;


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


}
