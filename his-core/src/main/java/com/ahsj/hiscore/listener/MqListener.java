package com.ahsj.hiscore.listener;


import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.core.WebContextUtil;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.entity.sys.SysCode;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import com.ahsj.hiscore.entity.sys.TranslateInfo;
import com.ahsj.hiscore.feign.ICodeService;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.*;
import com.ahsj.hiscore.services.impl.TranslateInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author XJP
 * @date 2019/07/25
 */
@Component
public class MqListener {

    private Logger log = LoggerFactory.getLogger(MqListener.class.getSimpleName());


    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    IOrganizationService iOrganizationService;

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

    private static final String AHSJ_TRANSLATE = "123456";


    /**
     * @return void
     * @功能说明 监听翻译模块的新增功能
     * @Params [msg]
     * @Author XJP
     * @Date 2019/7/25
     * @Time 15:11
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.addTranslate"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addTranslate"}
    ))
    public void listenMqMsg(String msg) throws Exception {
        if (StringUtils.equals(msg, AHSJ_TRANSLATE)) {
            //  System.out.println("-------------------监听成功-----------------" + msg);
            WebContextUtil.setTranslate();
        }
    }


    /**
     * @return void
     * @功能说明 监听翻译模块的新增功能 定时任务
     * @Params [msg]
     * @Author XJP
     * @Date 2019/7/25
     * @Time 15:11
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.addTranslateInfo"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addTranslateInfo"}
    ))
    public void listenMqaddTranslateInfo(String msg) throws Exception {
        if (StringUtils.equals(msg, AHSJ_TRANSLATE)) {
            //System.out.println("-------------------监听成功-----------------" + msg);
            WebContextUtil.setTranslateInfo();
        }
    }


    /**
     * @return void
     * @功能说明 监听字典模块的增删改功能
     * @Params [msg]
     * @Author XJP
     * @Date 2019/7/25
     * @Time 17:08
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue_four"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addCode", "com.ahsj.updateCode", "com.ahsj.deleteCode"}
    ))
    public void listenMqMsgCode(String msg) {
        if (StringUtils.equals(msg, AHSJ_TRANSLATE)) {
            WebContextUtil.setCodeData();
        }
    }


    /**
     * @return void
     * @功能说明 监听组织模块的增删改功能
     * @Params [msg]
     * @Author XJP
     * @Date 2019/7/25
     * @Time 17:08
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue_thress"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addOrganization", "com.ahsj.updateOrganization", "com.ahsj.deleteOrganization"}
    ))
    public void listenMqMsgOrganization(String msg) throws Exception {
        if (StringUtils.equals(msg, AHSJ_TRANSLATE)) {
            WebContextUtil.setOrganization();
        }
    }

    /**
     * @return void
     * @功能说明 监听字典明细模块的增删改功能
     * @Params [msg]
     * @Author XJP
     * @Date 2019/7/25
     * @Time 17:08
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue_two"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addCodeDetail", "com.ahsj.updateCodeDetail", "com.ahsj.deleteCodeDetail"}
    ))
    public void listenMqMsgCodeDetail(String msg) {
        if (StringUtils.equals(msg, AHSJ_TRANSLATE)) {
            WebContextUtil.setCodeData();
        }
    }


    /**
     * @return void
     * @功能说明 监听翻译模块的删除功能 定时任务
     * @Params [msg]
     * @Author XJP
     * @Date 2019/7/25
     * @Time 15:11
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.deleteTranslateInfo"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.deleteTranslateInfo"}
    ))
    public void listenMqdeleteTranslateInfo(String msg) throws Exception {

        if (StringUtils.equals(msg, AHSJ_TRANSLATE)) {
            log.info("---------删除翻译开始----------------");
            // List<String> transInfoType = Constants.getTransInfoType();
            List<TranslateInfo> translateInfos = iTranslateService.selectByTranslateType();
            for (TranslateInfo translateInfo : translateInfos) {
                if (StringUtils.equals(translateInfo.getTranslateType(), Constants.TRANSLATE_SYS_CODE)) {
                    List<SysCode> sysCodeAll = iCodeService.getSysCodeAll();
                    List<Long> collect = sysCodeAll.stream().map(SysCode::getId).collect(Collectors.toList());
                    TranslateInfo info = new TranslateInfo();
                    info.setTranslateType(Constants.TRANSLATE_SYS_CODE);
                    List<TranslateInfo> infoList = iTranslateService.selectByTranslateTypeAndTranslateId(info);
                    List<Long> collect1 = infoList.stream().map(TranslateInfo::getTranslateId).collect(Collectors.toList());
                    List<Long> longList = getLongList(collect1, collect);
                    for (Long aLong : longList) {
                        TranslateInfo translateInfo1 = new TranslateInfo();
                        translateInfo1.setTranslateId(aLong);
                        translateInfo1.setTranslateType(Constants.TRANSLATE_SYS_CODE);
                        iTranslateService.deleteByTranslate(translateInfo1);
                    }

                }
                if (StringUtils.equals(translateInfo.getTranslateType(), Constants.TRANSLATE_SYS_CODE_DETAIL)) {
                    List<SysCodeDetail> sysCodeDetails = iCodeService.lstSysCodeDetail();
                    List<Integer> collect = sysCodeDetails.stream().map(SysCodeDetail::getId).collect(Collectors.toList());
                    List<Long> longList1 = collect.stream().map((e) -> e.longValue()).collect(Collectors.toList());
                    TranslateInfo info = new TranslateInfo();
                    info.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                    List<TranslateInfo> infoList = iTranslateService.selectByTranslateTypeAndTranslateId(info);
                    List<Long> collect1 = infoList.stream().map(TranslateInfo::getTranslateId).collect(Collectors.toList());
                    List<Long> longList = getLongList(collect1, longList1);
                    for (Long aLong : longList) {
                        TranslateInfo translateInfo1 = new TranslateInfo();
                        translateInfo1.setTranslateId(aLong);
                        translateInfo1.setTranslateType(Constants.TRANSLATE_SYS_CODE_DETAIL);
                        iTranslateService.deleteByTranslate(translateInfo1);
                    }
                }
                if (StringUtils.equals(translateInfo.getTranslateType(), Constants.TRANSLATE_SYS_ORGANIZATION)) {
                    List<Organization> organizationAll = iOrganizationService.getOrganizationAll();
                    List<Long> collect = organizationAll.stream().map(Organization::getId).collect(Collectors.toList());
                    TranslateInfo info = new TranslateInfo();
                    info.setTranslateType(Constants.TRANSLATE_SYS_ORGANIZATION);
                    List<TranslateInfo> infoList = iTranslateService.selectByTranslateTypeAndTranslateId(info);
                    List<Long> collect1 = infoList.stream().map(TranslateInfo::getTranslateId).collect(Collectors.toList());
                    List<Long> longList = getLongList(collect, collect1);
                    for (Long aLong : longList) {
                        TranslateInfo translateInfo1 = new TranslateInfo();
                        translateInfo1.setTranslateId(aLong);
                        translateInfo1.setTranslateType(Constants.TRANSLATE_SYS_ORGANIZATION);
                        iTranslateService.deleteByTranslate(translateInfo1);
                    }
                }
                if (StringUtils.equals(translateInfo.getTranslateType(), Constants.TRANSLATE_HIS_PROJECT)) {
                    List<HisProject> projectList = hisProjectService.queryAll();
                    List<Long> collect = projectList.stream().map(HisProject::getId).collect(Collectors.toList());
                    TranslateInfo info = new TranslateInfo();
                    info.setTranslateType(Constants.TRANSLATE_HIS_PROJECT);
                    List<TranslateInfo> infoList = iTranslateService.selectByTranslateTypeAndTranslateId(info);
                    List<Long> collect1 = infoList.stream().map(TranslateInfo::getTranslateId).collect(Collectors.toList());
                    List<Long> longList = getLongList(collect, collect1);
                    for (Long aLong : longList) {
                        TranslateInfo translateInfo1 = new TranslateInfo();
                        translateInfo1.setTranslateId(aLong);
                        translateInfo1.setTranslateType(Constants.TRANSLATE_HIS_PROJECT);
                        iTranslateService.deleteByTranslate(translateInfo1);
                    }
                }
                if (StringUtils.equals(translateInfo.getTranslateType(), Constants.TRANSLATE_HIS_CONSUMABLESBUYPLAN)) {
                    List<HisConsumablesBuyplan> hisConsumablesBuyplans = hisConsumablesBuyplanService.queryAll();
                    List<Long> collect = hisConsumablesBuyplans.stream().map(HisConsumablesBuyplan::getId).collect(Collectors.toList());
                    TranslateInfo info = new TranslateInfo();
                    info.setTranslateType(Constants.TRANSLATE_HIS_CONSUMABLESBUYPLAN);
                    List<TranslateInfo> infoList = iTranslateService.selectByTranslateTypeAndTranslateId(info);
                    List<Long> collect1 = infoList.stream().map(TranslateInfo::getTranslateId).collect(Collectors.toList());
                    List<Long> longList = getLongList(collect, collect1);
                    for (Long aLong : longList) {
                        TranslateInfo translateInfo1 = new TranslateInfo();
                        translateInfo1.setTranslateId(aLong);
                        translateInfo1.setTranslateType(Constants.TRANSLATE_HIS_CONSUMABLESBUYPLAN);
                        iTranslateService.deleteByTranslate(translateInfo1);
                    }
                }
                if (StringUtils.equals(translateInfo.getTranslateType(), Constants.TRANSLATE_HIS_CONSUMABLESDETAILS)) {
                    List<HisConsumablesDetails> consumablesDetails = hisConsumablesDetailsService.queryAll();
                    List<Long> collect = consumablesDetails.stream().map(HisConsumablesDetails::getId).collect(Collectors.toList());
                    TranslateInfo info = new TranslateInfo();
                    info.setTranslateType(Constants.TRANSLATE_HIS_CONSUMABLESDETAILS);
                    List<TranslateInfo> infoList = iTranslateService.selectByTranslateTypeAndTranslateId(info);
                    List<Long> collect1 = infoList.stream().map(TranslateInfo::getTranslateId).collect(Collectors.toList());
                    List<Long> longList = getLongList(collect, collect1);
                    for (Long aLong : longList) {
                        TranslateInfo translateInfo1 = new TranslateInfo();
                        translateInfo1.setTranslateId(aLong);
                        translateInfo1.setTranslateType(Constants.TRANSLATE_HIS_CONSUMABLESDETAILS);
                        iTranslateService.deleteByTranslate(translateInfo1);
                    }
                }
                if (StringUtils.equals(translateInfo.getTranslateType(), Constants.TRANSLATE_HIS_CONSUMABLESBUYPLANDETAILS)) {
                    List<HisConsumablesBuyplanDetails> hisConsumablesBuyplanDetails = hisConsumablesBuyplanDetailsService.queryAll();
                    List<Long> collect = hisConsumablesBuyplanDetails.stream().map(HisConsumablesBuyplanDetails::getId).collect(Collectors.toList());
                    TranslateInfo info = new TranslateInfo();
                    info.setTranslateType(Constants.TRANSLATE_HIS_CONSUMABLESBUYPLANDETAILS);
                    List<TranslateInfo> infoList = iTranslateService.selectByTranslateTypeAndTranslateId(info);
                    List<Long> collect1 = infoList.stream().map(TranslateInfo::getTranslateId).collect(Collectors.toList());
                    List<Long> longList = getLongList(collect, collect1);
                    for (Long aLong : longList) {
                        TranslateInfo translateInfo1 = new TranslateInfo();
                        translateInfo1.setTranslateId(aLong);
                        translateInfo1.setTranslateType(Constants.TRANSLATE_HIS_CONSUMABLESBUYPLANDETAILS);
                        iTranslateService.deleteByTranslate(translateInfo1);
                    }
                }
                if (StringUtils.equals(translateInfo.getTranslateType(), Constants.TRANSLATE_HIS_MEDICINEINFO)) {
                    List<HisMedicineInfo> hisMedicineInfos = hisMedicineInfoService.queryAll();
                    List<Long> collect = hisMedicineInfos.stream().map(HisMedicineInfo::getId).collect(Collectors.toList());
                    TranslateInfo info = new TranslateInfo();
                    info.setTranslateType(Constants.TRANSLATE_HIS_MEDICINEINFO);
                    List<TranslateInfo> infoList = iTranslateService.selectByTranslateTypeAndTranslateId(info);
                    List<Long> collect1 = infoList.stream().map(TranslateInfo::getTranslateId).collect(Collectors.toList());
                    List<Long> longList = getLongList(collect, collect1);
                    for (Long aLong : longList) {
                        TranslateInfo translateInfo1 = new TranslateInfo();
                        translateInfo1.setTranslateId(aLong);
                        translateInfo1.setTranslateType(Constants.TRANSLATE_HIS_MEDICINEINFO);
                        iTranslateService.deleteByTranslate(translateInfo1);
                    }
                }
                if (StringUtils.equals(translateInfo.getTranslateType(), Constants.TRANSLATE_HIS_MEDICATIONDETAILS)) {
                    List<HisMedicationDetails> hisMedicationDetails = hisMedicationDetailsService.queryAll();
                    List<Long> collect = hisMedicationDetails.stream().map(HisMedicationDetails::getId).collect(Collectors.toList());
                    TranslateInfo info = new TranslateInfo();
                    info.setTranslateType(Constants.TRANSLATE_HIS_MEDICATIONDETAILS);
                    List<TranslateInfo> infoList = iTranslateService.selectByTranslateTypeAndTranslateId(info);
                    List<Long> collect1 = infoList.stream().map(TranslateInfo::getTranslateId).collect(Collectors.toList());
                    List<Long> longList = getLongList(collect, collect1);
                    for (Long aLong : longList) {
                        TranslateInfo translateInfo1 = new TranslateInfo();
                        translateInfo1.setTranslateId(aLong);
                        translateInfo1.setTranslateType(Constants.TRANSLATE_HIS_MEDICATIONDETAILS);
                        iTranslateService.deleteByTranslate(translateInfo1);
                    }
                }
                if (StringUtils.equals(translateInfo.getTranslateType(), Constants.TRANSLATE_HIS_HISANKLETEMPLATE)) {
                    List<HisAnkleTemplate> hisAnkleTemplates = hisAnkleTemplateService.queryAll();
                    List<Long> collect = hisAnkleTemplates.stream().map(HisAnkleTemplate::getId).collect(Collectors.toList());
                    TranslateInfo info = new TranslateInfo();
                    info.setTranslateType(Constants.TRANSLATE_HIS_HISANKLETEMPLATE);
                    List<TranslateInfo> infoList = iTranslateService.selectByTranslateTypeAndTranslateId(info);
                    List<Long> collect1 = infoList.stream().map(TranslateInfo::getTranslateId).collect(Collectors.toList());
                    List<Long> longList = getLongList(collect, collect1);
                    for (Long aLong : longList) {
                        TranslateInfo translateInfo1 = new TranslateInfo();
                        translateInfo1.setTranslateId(aLong);
                        translateInfo1.setTranslateType(Constants.TRANSLATE_HIS_HISANKLETEMPLATE);
                        iTranslateService.deleteByTranslate(translateInfo1);
                    }

                }
                if (StringUtils.equals(translateInfo.getTranslateType(), Constants.TRANSLATE_HIS_HISCONSUMABLES)) {
                    List<HisConsumables> hisConsumablesList = hisConsumablesService.queryAll();
                    List<Long> collect = hisConsumablesList.stream().map(HisConsumables::getId).collect(Collectors.toList());
                    TranslateInfo info = new TranslateInfo();
                    info.setTranslateType(Constants.TRANSLATE_HIS_HISCONSUMABLES);
                    List<TranslateInfo> infoList = iTranslateService.selectByTranslateTypeAndTranslateId(info);
                    List<Long> collect1 = infoList.stream().map(TranslateInfo::getTranslateId).collect(Collectors.toList());
                    List<Long> longList = getLongList(collect, collect1);
                    for (Long aLong : longList) {
                        TranslateInfo translateInfo1 = new TranslateInfo();
                        translateInfo1.setTranslateId(aLong);
                        translateInfo1.setTranslateType(Constants.TRANSLATE_HIS_HISCONSUMABLES);
                        iTranslateService.deleteByTranslate(translateInfo1);
                    }

                }
            }
        }
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
