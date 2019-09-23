package com.ahsj.translate.listener;


import com.ahsj.translate.common.Constants;
import com.ahsj.translate.common.dto.MqObjectModel;
import com.ahsj.translate.common.dto.OrganizationModel;
import com.ahsj.translate.common.utils.JsonUtils;
import com.ahsj.translate.entity.*;
import com.ahsj.translate.entity.TranslateModel.*;
import com.ahsj.translate.entity.model.TranslateInfoModel;
import com.ahsj.translate.entity.model.TranslateModel;
import com.ahsj.translate.service.TranslateInfoService;
import com.ahsj.translate.service.TranslateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.EmptyUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XJP
 * @date 2019/07/25
 */
@Slf4j
@Component
public class MqListener {

    @Autowired
    AmqpTemplate amqpTemplat;

    private static final String AHSJ_TRANSLATE = "123456";

    @Autowired
    TranslateService translateService;

    @Autowired
    TranslateInfoService translateInfoService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue1Enlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = "com.ahsj.msgTwo"
    ))
    public void listenMq(Integer num) {
        if (EmptyUtil.Companion.isNullOrEmpty(num)){
            return;
        }
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.iOrganizationListEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.iOrganizationServicesss"}
    ))
    public void listenMqiOrganizationService(String  model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)){
            return;
        }else {
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            List<OrganizationTranslate> organizationTranslateList = m.getOrganizationTranslateList();
            for (OrganizationTranslate organizationModel : organizationTranslateList) {
                toTranslate(organizationModel, OrganizationTranslate.class, organizationModel.getId(), Constants.TRANSLATE_SYS_ORGANIZATION, m.getUserId());
            }

        }
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.sysCodeDetailsEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.sysCodeDetails"}
    ))
    public void listenMqsysCodeDetailse(String  model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)){
            return;
        }else {
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            List<SysCodeDetailTranslate> sysCodeDetailTranslateList = m.getSysCodeDetailTranslateList();
            for (SysCodeDetailTranslate sysCodeDetail : sysCodeDetailTranslateList) {
                toTranslate(sysCodeDetail, SysCodeDetailTranslate.class, sysCodeDetail.getId().longValue(), Constants.TRANSLATE_SYS_CODE_DETAIL, m.getUserId());
            }

        }
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.deleteEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.delete"}
    ))
    public void listenMqDelete(String  model) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(model)){
            return;
        }else {
            TranslateDelete m = JsonUtils.parse(model, TranslateDelete.class);
            translateService.deleteTranslate(m);
        }
    }

    /**
     *@功能说明  定时任务 翻译模式
     *@Params [model]
     *@return void
     *@Author XJP
     *@Date 2019/8/9
     *@Time 10:39
    **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.addTranslateInfoModelstEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addTranslateInfoModels"}
    ))
    public void listenMqTranslateInfoModels(String  model) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(model)){
            return;
        }else {
            TranslateInfoModels translateInfoModels = JsonUtils.parse(model, TranslateInfoModels.class);

            List<HisAnkleTemplateTranslate> hisMedicineInfoTranslateArrayList = translateInfoModels.getHisMedicineInfoTranslateArrayList();
            for (HisAnkleTemplateTranslate hisAnkleTemplateTranslate : hisMedicineInfoTranslateArrayList) {
                toTranslateInfo(hisAnkleTemplateTranslate, HisAnkleTemplateTranslate.class, hisAnkleTemplateTranslate.getId().longValue(), Constants.TRANSLATE_HIS_HISANKLETEMPLATE);
            }
            List<HisConsumablesBuyplanDetailsTranslate> hisMedicineInfoTranslateList = translateInfoModels.getHisMedicineInfoTranslateList();
            for (HisConsumablesBuyplanDetailsTranslate hisConsumablesBuyplanDetailsTranslate : hisMedicineInfoTranslateList) {
                toTranslateInfo(hisConsumablesBuyplanDetailsTranslate, HisConsumablesBuyplanDetailsTranslate.class, hisConsumablesBuyplanDetailsTranslate.getId().longValue(), Constants.TRANSLATE_HIS_CONSUMABLESBUYPLANDETAILS);
            }
            List<HisMedicineInfoTranslate> hisMedicineInfoTranslates = translateInfoModels.getHisMedicineInfoTranslates();
            for (HisMedicineInfoTranslate hisMedicineInfoTranslate : hisMedicineInfoTranslates) {
                toTranslateInfo(hisMedicineInfoTranslate, HisMedicineInfoTranslate.class, hisMedicineInfoTranslate.getId().longValue(), Constants.TRANSLATE_HIS_MEDICINEINFO);
            }
            List<HisConsumablesTranslate> infoTranslateList = translateInfoModels.getInfoTranslateList();
            for (HisConsumablesTranslate hisConsumablesTranslate : infoTranslateList) {
                toTranslateInfo(hisConsumablesTranslate, HisConsumablesTranslate.class, hisConsumablesTranslate.getId().longValue(), Constants.TRANSLATE_HIS_HISCONSUMABLES);
            }
            List<HisConsumablesDetailsTranslate> medicineInfoTranslateArrayList = translateInfoModels.getMedicineInfoTranslateArrayList();
            for (HisConsumablesDetailsTranslate hisConsumablesDetailsTranslate : medicineInfoTranslateArrayList) {
                toTranslateInfo(hisConsumablesDetailsTranslate, HisConsumablesDetailsTranslate.class, hisConsumablesDetailsTranslate.getId().longValue(), Constants.TRANSLATE_HIS_CONSUMABLESDETAILS);
            }
            List<HisConsumablesBuyplanTranslate> medicineInfoTranslateList = translateInfoModels.getMedicineInfoTranslateList();
            for (HisConsumablesBuyplanTranslate hisConsumablesBuyplanTranslate : medicineInfoTranslateList) {
                toTranslateInfo(hisConsumablesBuyplanTranslate, HisConsumablesBuyplanTranslate.class, hisConsumablesBuyplanTranslate.getId().longValue(), Constants.TRANSLATE_HIS_CONSUMABLESBUYPLAN);
            }
            List<HisProjectTranslate> projectTranslateList = translateInfoModels.getProjectTranslateList();
            for (HisProjectTranslate hisProjectTranslate : projectTranslateList) {
                toTranslateInfo(hisProjectTranslate, HisProjectTranslate.class, hisProjectTranslate.getId().longValue(), Constants.TRANSLATE_HIS_PROJECT);
            }
            List<HisMedicationDetailsTranslate> medicineInfoTranslates = translateInfoModels.getMedicineInfoTranslates();
            for (HisMedicationDetailsTranslate medicineInfoTranslate : medicineInfoTranslates) {
                toTranslateInfo(medicineInfoTranslate, HisMedicationDetailsTranslate.class, medicineInfoTranslate.getId().longValue(), Constants.TRANSLATE_HIS_MEDICATIONDETAILS);
            }

            List<OrganizationTranslate> organizationTranslates = translateInfoModels.getOrganizationTranslates();
            for (OrganizationTranslate organizationTranslate : organizationTranslates) {
                toTranslateInfo(organizationTranslate, OrganizationTranslate.class, organizationTranslate.getId().longValue(), Constants.TRANSLATE_SYS_ORGANIZATION);
            }
            List<SysCodeDetailTranslate> sysCodeDetailTranslates = translateInfoModels.getSysCodeDetailTranslates();
            for (SysCodeDetailTranslate sysCodeDetailTranslate : sysCodeDetailTranslates) {
                toTranslateInfo(sysCodeDetailTranslate, SysCodeDetailTranslate.class, sysCodeDetailTranslate.getId().longValue(), Constants.TRANSLATE_SYS_CODE_DETAIL);
            }
            List<SysCodeTranslate> sysCodeTranslates = translateInfoModels.getSysCodeTranslates();
            for (SysCodeTranslate sysCodeTranslate : sysCodeTranslates) {
                toTranslateInfo(sysCodeTranslate, SysCodeTranslate.class, sysCodeTranslate.getId().longValue(), Constants.TRANSLATE_SYS_CODE);
            }

            amqpTemplat.convertAndSend("com.ahsj.deleteTranslateInfo", AHSJ_TRANSLATE);
            amqpTemplat.convertAndSend("com.ahsj.addTranslateInfo", AHSJ_TRANSLATE);

        }
    }






    /**
     * @return void
     * @功能说明 批量添加 消息队列
     * @Params [obj, clazz, id, constants, userId]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 11:37
     **/
    public void toTranslate(Object obj, Class<?> clazz, Long id, String constants, Long userId) {
        if (obj == null) {
            return;
        }
        List<Translate> list = new ArrayList<>();
        TranslateModel model = new TranslateModel();
//        BaseLoginUser loginUser = new BaseLoginUser();
        model.setUserId(userId);
        Field[] fields = clazz.getDeclaredFields();// 根据Class对象获得属性 私有的也可以获得
        String s = "";
        try {
            for (Field f : fields) {
                Translate translate = new Translate();
                translate.setTranslateId(id);
                translate.setTranslateType(constants);
                translate.setTranslateColumn(f.getName());// 得到此属性的名称
                f.setAccessible(true); // 设置些属性是可以访问的
                Object china = f.get(obj);// 得到此属性的值
                if (china == null) {
                    continue;
                }
                translate.setTranslateChina(china.toString());
                list.add(translate);
            }
            model.setTranslateList(list);
            translateService.addTranslateList(model);
        } catch (Exception e) {
        }
        //return s;
    }



    /**
     * @return void
     * @功能说明  批量添加 消息队列  定时任务
     * @Params [obj, clazz, id, constants, userId]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 11:37
     **/
    public void toTranslateInfo(Object obj, Class<?> clazz, Long id, String constants) {
        if (obj == null) {
            return;
        }
        List<TranslateInfo> list = new ArrayList<>();
        TranslateInfoModel model = new TranslateInfoModel();

        Field[] fields = clazz.getDeclaredFields();// 根据Class对象获得属性 私有的也可以获得
        String s = "";
        try {
            for (Field f : fields) {
                TranslateInfo translate = new TranslateInfo();
                translate.setTranslateId(id);
                /*translate.setCreateUserId(userId.toString());*/
                translate.setTranslateType(constants);
                translate.setTranslateColumn(f.getName());// 得到此属性的名称
                f.setAccessible(true); // 设置些属性是可以访问的
                Object china = f.get(obj);// 得到此属性的值
                if (china == null) {
                    continue;
                }
                translate.setTranslateChina(china.toString());
                list.add(translate);
            }
            model.setTranslateList(list);
            translateInfoService.addTranslateInfoList(model);
            // translateService.addTranslateList(model);
        } catch (Exception e) {
        }
        //return model;
    }

}
