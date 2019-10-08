package com.ahsj.translate.listener;

import com.ahsj.translate.common.Constants;
import com.ahsj.translate.common.utils.JsonUtils;
import com.ahsj.translate.common.utils.StringUtils;
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
 * FileName: TranslateListener
 * <p>
 * Date:     2019/7/29 9:10
 *
 * @author XJP
 * @create 2019/7/29
 * @since 1.0.0
 */


@Component
public class TranslateListener {

    private Logger log = LoggerFactory.getLogger(TranslateListener.class.getSimpleName());

    @Autowired
    TranslateService translateService;


    /**
     * @return void
     * @功能说明
     * @Params [num]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 9:11
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.addProjectEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addProject", "com.ahsj.updateProject"}
    ))
    public void listenMqProject(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
            //    log.info("--------------------收费模块翻译接受信息开始--------------------------");
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            // log.info(model);
            toTranslate(m.getHisProjectTranslate(), HisProjectTranslate.class, m.getHisProjectTranslate().getId(), Constants.TRANSLATE_HIS_PROJECT, m.getUserId());
            //  log.info("--------------------收费模块翻译接受信息结束--------------------------");
        }
    }


    /**
     * @return void
     * @功能说明
     * @Params [num]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 9:11
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.hisMedicineInfoEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addHisMedicineInfo", "com.ahsj.updateHisMedicineInfo"}
    ))
    public void listenMqHisMedicineInfo(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
            // log.info("--------------------药品模块翻译接受信息开始--------------------------");
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            toTranslate(m.getHisMedicineInfoTranslate(), HisMedicineInfoTranslate.class, m.getHisMedicineInfoTranslate().getId(), Constants.TRANSLATE_HIS_MEDICINEINFO, m.getUserId());
            //   log.info("--------------------药品模块翻译接受信息结束--------------------------");



        }
    }

    /**
     * @return void
     * @功能说明
     * @Params [num]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 9:11
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.hisMedicationDetailsEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addHisMedicationDetails"}
    ))
    public void listenMqHisMedicationDetails(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
        }
    }


    /**
     * @return void
     * @功能说明
     * @Params [num]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 9:11
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.hisAnkleTemplateEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addHisAnkleTemplate", "com.ahsj.updateHisAnkleTemplate"}
    ))
    public void listenMqHisAnkleTemplate(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
            //  log.info("--------------------互属模块翻译接受信息开始--------------------------");
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            //  log.info(model);
            toTranslate(m.getHisAnkleTemplateTranslate(), HisAnkleTemplateTranslate.class, m.getHisAnkleTemplateTranslate().getId(), Constants.TRANSLATE_HIS_HISANKLETEMPLATE, m.getUserId());
            //  log.info("--------------------互属模块翻译接受信息结束--------------------------");
        }
    }


    /**
     * @return void
     * @功能说明
     * @Params [num]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 9:11
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.hisConsumablesBuyplanDetailsEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addHisConsumablesBuyplanDetails", "com.ahsj.updateHisConsumablesBuyplanDetails"}
    ))
    public void listenMqHisConsumablesBuyplanDetails(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
            //  log.info("--------------------采购耗材明细模块翻译接受信息开始--------------------------");
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            //   log.info(model);
            toTranslate(m.getHisConsumablesBuyplanDetailsTranslate(), HisConsumablesBuyplanDetailsTranslate.class, m.getHisConsumablesBuyplanDetailsTranslate().getId(), Constants.TRANSLATE_HIS_CONSUMABLESBUYPLANDETAILS, m.getUserId());
            // log.info("--------------------采购耗材明细模块翻译接受信息结束--------------------------");
        }
    }


    /**
     * @return void
     * @功能说明
     * @Params [num]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 9:11
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.hisConsumablesBuyplanEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addHisConsumablesBuyplan", "com.ahsj.updateHisConsumablesBuyplan"}
    ))
    public void listenMqHisConsumablesBuyplan(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
            // log.info("--------------------采购耗材模块翻译接受信息开始--------------------------");
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            //  log.info(model);
            toTranslate(m.getHisConsumablesBuyplanTranslate(), HisConsumablesBuyplanTranslate.class, m.getHisConsumablesBuyplanTranslate().getId(), Constants.TRANSLATE_HIS_CONSUMABLESBUYPLAN, m.getUserId());
            //  log.info("--------------------采购耗材模块翻译接受信息结束--------------------------");
        }
    }


    /**
     * @return void
     * @功能说明
     * @Params [num]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 9:11
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.hisConsumablesBuyplanEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addHisConsumablesDetails", "com.ahsj.updateHisConsumablesDetails"}
    ))
    public void listenMqHisConsumablesDetails(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
            //  log.info("--------------------耗材库存模块翻译接受信息开始--------------------------");
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            //  log.info(model);
            toTranslate(m.getHisConsumablesDetailsTranslate(), HisConsumablesDetailsTranslate.class, m.getHisConsumablesDetailsTranslate().getId(), Constants.TRANSLATE_HIS_CONSUMABLESDETAILS, m.getUserId());
            // log.info("--------------------耗材库存模块翻译接受信息结束--------------------------");
        }
    }


    /**
     * @return void
     * @功能说明
     * @Params [num]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 9:11
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.hisConsumablesEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addHisConsumables", "com.ahsj.updateHisConsumables"}
    ))
    public void listenMqHisConsumables(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
           // log.info("--------------------耗材模块翻译接受信息开始--------------------------");
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
           // log.info(model);
            toTranslate(m.getHisConsumablesTranslate(), HisConsumablesTranslate.class, m.getHisConsumablesTranslate().getId(), Constants.TRANSLATE_HIS_HISCONSUMABLES, m.getUserId());
           // log.info("--------------------耗材模块翻译接受信息结束--------------------------");
        }
    }


    /**
     * @return void
     * @功能说明
     * @Params [num]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 9:11
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.addOrganizationTranslateEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addOrganizationTranslate", "com.ahsj.updateOrganizationTranslate"}
    ))
    public void listenMqOrganizationTranslate(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
          //  log.info("--------------------组织模块翻译接受信息开始--------------------------");
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
          //  log.info(model);
            toTranslate(m.getOrganizationTranslate(), OrganizationTranslate.class, m.getOrganizationTranslate().getId(), Constants.TRANSLATE_SYS_ORGANIZATION, m.getUserId());
           // log.info("--------------------组织模块翻译接受信息结束--------------------------");
        }
    }


    /**
     * @return void
     * @功能说明
     * @Params [num]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 9:11
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.sysCodeTranslateEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addSysCodeTranslate", "com.ahsj.updateSysCodeTranslate"}
    ))
    public void listenMqSysCodeTranslate(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
          //  log.info("--------------------字典模块翻译接受信息开始--------------------------");
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            log.info(model);
            toTranslate(m.getSysCodeTranslate(), SysCodeTranslate.class, m.getSysCodeTranslate().getId(), Constants.TRANSLATE_SYS_CODE, m.getUserId());
          //  log.info("--------------------字典模块翻译接受信息结束--------------------------");
        }
    }


    /**
     * @return void
     * @功能说明
     * @Params [num]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 9:11
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.sysCodeDetailTranslateEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addSysCodeDetailTranslate", "com.ahsj.updateSysCodeDetailTranslate"}
    ))
    public void listenMqSysCodeDetailTranslate(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
           // log.info("--------------------字典明细模块翻译接受信息开始--------------------------");
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            log.info(model);
            toTranslate(m.getSysCodeDetailTranslate(), SysCodeDetailTranslate.class, m.getSysCodeDetailTranslate().getId().longValue(), Constants.TRANSLATE_SYS_CODE_DETAIL, m.getUserId());
          //  log.info("--------------------字典明细模块翻译接受信息结束--------------------------");
        }
    }


    /**
     * @return void
     * @功能说明
     * @Params [num]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 9:11
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.addListEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addList"}
    ))
    public void addList(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            List<SysCodeDetailTranslate> list = m.getSysCodeDetailTranslateList();
            for (SysCodeDetailTranslate detail : list) {
                toTranslate(detail, SysCodeDetailTranslate.class, detail.getId().longValue(), Constants.TRANSLATE_HIS_HISCONSUMABLES_LIST, m.getUserId());
            }
        }
    }

    /**
     * @return void
     * @功能说明
     * @Params [model]
     * @Author XJP
     * @Date 2019/8/30
     * @Time 15:26
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.hisPrescriptionMedicineEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.add.hisPrescriptionMedicine","com.ahsj.update.hisPrescriptionMedicine"}
    ))
    public void hisPrescriptionMedicine(String model) {
        if (StringUtils.isBlank(model)) {
            return;
        } else {
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            List<HisPrescriptionMedicineTranslate> hisPrescriptionMedicineTranslateList = m.getHisPrescriptionMedicineTranslateList();
            if (EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicineTranslateList)){
                return;
            }else {
                for (HisPrescriptionMedicineTranslate hisPrescriptionMedicineTranslate : hisPrescriptionMedicineTranslateList) {
                    if (EmptyUtil.Companion.isNullOrEmpty(hisPrescriptionMedicineTranslate)){
                        continue;
                    }else {
                        toTranslate(hisPrescriptionMedicineTranslate, HisPrescriptionMedicineTranslate.class, hisPrescriptionMedicineTranslate.getId().longValue(), Constants.TRANSLATE_HIS_HISPRESCRIPTIONMEDICINE, m.getUserId());
                    }
                }
            }

        }
    }

    /**
     * @return void
     * @功能说明
     * @Params [model]
     * @Author XJP
     * @Date 2019/8/30
     * @Time 15:26
     **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.hisPrescriptionEnglish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.add.hisPrescription","com.ahsj.update.hisPrescription"}
    ))
    public void hisPrescription(String model) {
        System.out.println(model);
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            System.out.println(model);
        } else {
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            HisPrescriptionTranslate hisPrescriptionTranslate = m.getHisPrescriptionTranslate();
            toTranslate(hisPrescriptionTranslate, HisPrescriptionTranslate.class, hisPrescriptionTranslate.getId().longValue(), Constants.TRANSLATE_HIS_HISPRESCRIPTION, m.getUserId());
        }
    }

    /**
     *@功能说明
     *@Params [model]
     *@return void
     *@Author XJP
     *@Date 2019/9/7
     *@Time 11:16
    **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.hisMedicalEnglish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.add.hisMedical","com.ahsj.update.hisMedical"}
    ))
    public void hisMedical(String model) {
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            return;
        } else {
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            HisMedicalTranslate hisMedicalTranslate = m.getHisMedicalTranslate();
            if (EmptyUtil.Companion.isNullOrEmpty(hisMedicalTranslate.getId().longValue())){
                return;
            }else {
                toTranslate(hisMedicalTranslate, HisMedicalTranslate.class, hisMedicalTranslate.getId().longValue(), Constants.TRANSLATE_HIS_HHISMEDICAL, m.getUserId());
            }
        }
    }



    /**
     *@功能说明
     *@Params [model]
     *@return void
     *@Author XJP
     *@Date 2019/10/7
     *@Time 10:00
    **/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "com.mq.verify.queue.hisMedicalOrderDetailEnlish"),
            exchange = @Exchange(name = "com.ahsj.exchange", type = ExchangeTypes.TOPIC),
            key = {"com.ahsj.addHisMedicalOrderDetail","com.ahsj.updateHisMedicalOrderDetail"}
    ))
    public void HisMedicalOrderDetail(String model) {
        System.out.println(model);
        if (EmptyUtil.Companion.isNullOrEmpty(model)) {
            System.out.println(model);
        } else {
            TranslateModels m = JsonUtils.parse(model, TranslateModels.class);
            HisMedicalOrderDetailTranslate hisMedicalOrderDetailTranslate = m.getHisMedicalOrderDetailTranslate();
            toTranslate(hisMedicalOrderDetailTranslate, HisMedicalOrderDetailTranslate.class, hisMedicalOrderDetailTranslate.getId().longValue(), Constants.TRANSLATE_HIS_MEDICALORDERDETAIL, m.getUserId());
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
