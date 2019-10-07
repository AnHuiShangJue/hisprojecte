package com.ahsj.translate.listener;

import com.ahsj.translate.common.Constants;
import com.ahsj.translate.common.utils.JsonUtils;
import com.ahsj.translate.entity.HisMedicationDetails;
import com.ahsj.translate.entity.Translate;
import com.ahsj.translate.entity.TranslateModel.*;
import com.ahsj.translate.entity.model.TranslateModel;
import com.ahsj.translate.service.TranslateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.EmptyUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: TranslateListListener
 * <p>
 * Date:     2019/9/16 14:12
 *
 * @author XJP
 * @create 2019/9/16
 * @since 1.0.0
 */

@Component
public class TranslateListListener {

    private Logger log = LoggerFactory.getLogger(TranslateListListener.class.getSimpleName());

    @Autowired
    TranslateService translateService;

    /**
     * @return void
     * @功能说明 药品导入翻译
     * @Params [model]
     * @Author XJP
     * @Date 2019/9/16
     * @Time 14:21
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.hisMedicineInfoListEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addHisMedicineInfoList", "com.ahsj.updateHisMedicineInfoList"}
    ))
    public void listenMqHisMedicineInfo(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
            log.info("--------------------药品模块翻译接受信息开始--------------------------");
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            List<HisMedicineInfoTranslate> hisMedicineInfoTranslates = m.getHisMedicineInfoTranslates();
            for (HisMedicineInfoTranslate hisMedicineInfoTranslate : hisMedicineInfoTranslates) {
                toTranslate(hisMedicineInfoTranslate, HisMedicineInfoTranslate.class, hisMedicineInfoTranslate.getId(), Constants.TRANSLATE_HIS_MEDICINEINFO, m.getUserId());
            }
            log.info("--------------------药品模块翻译接受信息结束--------------------------");
        }
    }


    /**
     * @return void
     * @功能说明 收费项目导入翻译
     * @Params [model]
     * @Author XJP
     * @Date 2019/9/16
     * @Time 14:21
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.addProjectListEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addProjectList", "com.ahsj.updateProjectList"}
    ))
    public void listenMqProject(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
            log.info("--------------------收费模块翻译接受信息开始--------------------------");
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            List<HisProjectTranslate> hisProjectTranslates = m.getHisProjectTranslates();
            for (HisProjectTranslate hisProjectTranslate : hisProjectTranslates) {
                toTranslate(hisProjectTranslate, HisProjectTranslate.class, hisProjectTranslate.getId(), Constants.TRANSLATE_HIS_PROJECT, m.getUserId());
            }
            log.info("--------------------收费模块翻译接受信息结束--------------------------");
        }
    }


    /**
     * @return void
     * @功能说明 耗材导入翻译
     * @Params [model]
     * @Author XJP
     * @Date 2019/9/16
     * @Time 14:41
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.HisConsumablesListListEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addHisConsumablesList", "com.ahsj.updateHisConsumablesList"}
    ))
    public void HisConsumablesListListEnlish(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
            log.info("--------------------耗材模块翻译接受信息开始--------------------------");
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            List<HisConsumablesTranslate> hisConsumablesTranslates = m.getHisConsumablesTranslates();
            for (HisConsumablesTranslate hisConsumablesTranslate : hisConsumablesTranslates) {
                toTranslate(hisConsumablesTranslate, HisConsumablesTranslate.class, hisConsumablesTranslate.getId(), Constants.TRANSLATE_HIS_HISCONSUMABLES, m.getUserId());
            }
            log.info("--------------------耗材模块翻译接受信息结束--------------------------");
        }
    }

    /**
     * @return void
     * @功能说明
     * @Params [model]
     * @Author XJP
     * @Date 2019/10/7
     * @Time 10:01
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.HisMedicationDetailsListEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addHisMedicationDetailsList"}
    ))
    public void addHisMedicationDetailsList(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
            log.info("--------------------药品明细模块翻译接受信息开始--------------------------");
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            List<HisMedicationDetailsTranslate> hisMedicationDetails = m.getHisMedicationDetails();
            for (HisMedicationDetailsTranslate medicationDetails : hisMedicationDetails) {
                toTranslate(medicationDetails, HisMedicationDetailsTranslate.class, medicationDetails.getId(), Constants.TRANSLATE_HIS_MEDICATIONDETAILS, m.getUserId());
            }
            log.info("--------------------药品明细模块翻译接受信息结束--------------------------");
        }
    }


    /**
     * @return void
     * @功能说明
     * @Params [model]
     * @Author XJP
     * @Date 2019/10/7
     * @Time 10:05
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.HisMedicalOrderDetailListEnglish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addHisMedicalOrderDetailList"}
    ))
    public void HisMedicalOrderDetailList(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
            log.info("--------------------医嘱模块翻译接受信息开始--------------------------");
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            List<HisMedicalOrderDetailTranslate> hisMedicalOrderDetailTranslates = m.getHisMedicalOrderDetailTranslates();
            for (HisMedicalOrderDetailTranslate medicationDetails : hisMedicalOrderDetailTranslates) {
                toTranslate(medicationDetails, HisMedicalOrderDetailTranslate.class, medicationDetails.getId(), Constants.TRANSLATE_HIS_MEDICALORDERDETAIL, m.getUserId());
            }
            log.info("--------------------医嘱模块翻译接受信息结束--------------------------");
        }
    }


    /**
     * @return void
     * @功能说明
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
            model.setUserId(userId);
            translateService.addTranslateList(model);
        } catch (Exception e) {
        }
    }


}
