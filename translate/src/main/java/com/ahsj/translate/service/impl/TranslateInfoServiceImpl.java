package com.ahsj.translate.service.impl;

import com.ahsj.translate.common.Constants;
import com.ahsj.translate.common.google.GoogleApi;
import com.ahsj.translate.dao.TranslateInfoMapper;
import com.ahsj.translate.entity.TranslateInfo;
import com.ahsj.translate.entity.TranslateModel.*;
import com.ahsj.translate.entity.model.TranslateInfoModel;
import com.ahsj.translate.service.TranslateInfoService;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: TranslateInfoServiceImpl
 * <p>
 * Date:     2019/8/8 16:14
 *
 * @author XJP
 * @create 2019/8/8
 * @since 1.0.0
 */

@Service
public class TranslateInfoServiceImpl implements TranslateInfoService {

    private Logger log = LoggerFactory.getLogger(TranslateInfoServiceImpl.class.getSimpleName());

    @Autowired
    TranslateInfoMapper translateInfoMapper;

    @Autowired
    AmqpTemplate amqpTemplat;

    private static final String AHSJ_TRANSLATE = "123456";


    @Override
    @Transactional(readOnly = false)
    public Message insert(TranslateInfo translateInfo) {
        if (EmptyUtil.Companion.isNullOrEmpty(translateInfo)) {
            return MessageUtil.createMessage(false, "添加失败");
        }
        translateInfoMapper.insert(translateInfo);
        return MessageUtil.createMessage(true, "添加成功");
    }

    @Override
    @Transactional(readOnly = true)
    public TranslateInfo selectByPrimaryKey(Long id) {
        return translateInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * @return core.message.Message
     * @功能说明 批量添加  调用服务版
     * @Params [translateInfoModel]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:32
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addTranslateList(TranslateInfoModels translateInfoModels) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(translateInfoModels)) {
            log.info("添加失败！ 传递参数不能为空");
            return MessageUtil.createMessage(false, "添加失败！传递参数不能为空");
        } else {
            GoogleApi googleApi = new GoogleApi();
            List<HisAnkleTemplateTranslate> hisMedicineInfoTranslateArrayList = translateInfoModels.getHisMedicineInfoTranslateArrayList();
            if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfoTranslateArrayList)) {
                for (HisAnkleTemplateTranslate hisAnkleTemplateTranslate : hisMedicineInfoTranslateArrayList) {
                    TranslateInfoModel translateInfoModel = toTranslate(hisAnkleTemplateTranslate, HisAnkleTemplateTranslate.class, hisAnkleTemplateTranslate.getId().longValue(), Constants.TRANSLATE_HIS_HISANKLETEMPLATE);
                    List<TranslateInfo> translateList = translateInfoModel.getTranslateList();
                    List<TranslateInfo> list = new ArrayList<>();
                    for (TranslateInfo translateInfo : translateList) {
                        TranslateInfo translateOne = new TranslateInfo();
                        String china = translateInfo.getTranslateChina();
                        String km = googleApi.translate(china, Constants.TRANSLATE_KHMER_EN);
                        translateOne.setTranslateChina(translateInfo.getTranslateChina());
                        if (km.length() > 255) {
                            String substringOne = StringUtils.substring(km, 0, 255);
                            String substringTwo = StringUtils.substring(km, 255, km.length());
                            translateOne.setTranslateKhmer(substringOne);
                            translateOne.setTranslateJoin(substringTwo);
                            translateOne.setTranslateId(translateInfo.getTranslateId());
                            translateOne.setTranslateType(translateInfo.getTranslateType());
                            translateOne.setTranslateColumn(translateInfo.getTranslateColumn());
                            translateOne.setCreateDate(new Date());
                            list.add(translateOne);
                        } else {
                            translateOne.setTranslateKhmer(km);
                            translateOne.setTranslateId(translateInfo.getTranslateId());
                            translateOne.setTranslateType(translateInfo.getTranslateType());
                            translateOne.setCreateDate(new Date());
                            System.out.println(translateInfo.getTranslateColumn());
                            translateOne.setTranslateColumn(translateInfo.getTranslateColumn());
                            list.add(translateOne);
                        }
                    }
                }
            }
            List<HisConsumablesBuyplanDetailsTranslate> hisMedicineInfoTranslateList = translateInfoModels.getHisMedicineInfoTranslateList();
            List<HisMedicineInfoTranslate> hisMedicineInfoTranslates = translateInfoModels.getHisMedicineInfoTranslates();
            List<HisConsumablesTranslate> infoTranslateList = translateInfoModels.getInfoTranslateList();
            List<HisConsumablesDetailsTranslate> medicineInfoTranslateArrayList = translateInfoModels.getMedicineInfoTranslateArrayList();
            List<HisConsumablesBuyplanTranslate> medicineInfoTranslateList = translateInfoModels.getMedicineInfoTranslateList();
            List<HisProjectTranslate> projectTranslateList = translateInfoModels.getProjectTranslateList();
            List<HisMedicationDetailsTranslate> medicineInfoTranslates = translateInfoModels.getMedicineInfoTranslates();
        }

        return null;
    }

    /**
     * @return core.message.Message
     * @功能说明 批量添加 消息队列  定时任务
     * @Params [TranslateInfoModel]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 10:51
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addTranslateInfoList(TranslateInfoModel TranslateInfoModel) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(TranslateInfoModel)) {
            return MessageUtil.createMessage(false, "操作失败 ， 请重新操作");
        } else {
            GoogleApi googleApi = new GoogleApi();
            List<TranslateInfo> translateList = TranslateInfoModel.getTranslateList();
            for (TranslateInfo translate : translateList) {
                //先删除后新增操作
                if (!EmptyUtil.Companion.isNullOrEmpty(translate.getTranslateId()) && !EmptyUtil.Companion.isNullOrEmpty(translate.getTranslateType())) {
                    translateInfoMapper.deleteByTranslate(translate);
                }
            }
            List<TranslateInfo> list = new ArrayList<>();
            for (TranslateInfo translate : translateList) {
                TranslateInfo translateOne = new TranslateInfo();
                String china = translate.getTranslateChina();
                String km = googleApi.translate(china, Constants.TRANSLATE_KHMER_EN);
                translateOne.setTranslateChina(translate.getTranslateChina());
                if (km.length() > 255) {
                    String substringOne = StringUtils.substring(km, 0, 255);
                    String substringTwo = StringUtils.substring(km, 255, km.length());
                    translateOne.setTranslateKhmer(substringOne);
                    translateOne.setTranslateJoin(substringTwo);
                    translateOne.setTranslateId(translate.getTranslateId());
                    translateOne.setTranslateType(translate.getTranslateType());
                    translateOne.setTranslateColumn(translate.getTranslateColumn());
                    translateOne.setCreateDate(new Date());
                    list.add(translateOne);
                } else {
                    translateOne.setTranslateKhmer(km);
                    translateOne.setTranslateId(translate.getTranslateId());
                    translateOne.setTranslateType(translate.getTranslateType());
                    translateOne.setCreateDate(new Date());
                    System.out.println(translate.getTranslateColumn());
                    translateOne.setTranslateColumn(translate.getTranslateColumn());
                    list.add(translateOne);
                }
            }
            translateInfoMapper.addTranslateList(list);
           // amqpTemplat.convertAndSend("com.ahsj.addTranslateInfo", AHSJ_TRANSLATE);
           // amqpTemplat.convertAndSend("com.ahsj.deleteTranslateInfo", AHSJ_TRANSLATE);
            return MessageUtil.createMessage(true, "操作成功 ");
        }
    }

    /**
     * @return java.util.List<com.ahsj.translate.entity.TranslateInfo>
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 13:34
     **/
    @Override
    @Transactional(readOnly = true)
    public List<TranslateInfo> queryTranslateList(TranslateInfo translateInfo) {

        if (EmptyUtil.Companion.isNullOrEmpty(translateInfo.getTranslateType()) /*|| EmptyUtil.Companion.isNullOrEmpty(translate.getTranslateId())*/) {
            log.info("查询失败 ！  参数不能为空");
            return new ArrayList<>();
        } else {
            List<TranslateInfo> translateInfos = translateInfoMapper.queryTranslateList(translateInfo);
            List<TranslateInfo> list = new ArrayList<>();
            if (CollectionUtils.isEmpty(translateInfos)) {
                log.info("查询失败 ！ 无对应数据");
                return new ArrayList<>();
            } else {
                for (TranslateInfo translateOne : translateInfos) {
                    TranslateInfo translateTwo = new TranslateInfo();
                    translateTwo.setId(translateOne.getId());
                    translateTwo.setTranslateId(translateOne.getTranslateId());
                    translateTwo.setTranslateType(translateOne.getTranslateType());
                    translateTwo.setTranslateChina(translateOne.getTranslateChina());
                    translateTwo.setCreateUserId(translateOne.getCreateUserId());
                    translateOne.setTranslateColumn(translateInfo.getTranslateColumn());
                    StringBuffer stringBuffer = new StringBuffer();
                    if (StringUtils.isEmpty(translateInfo.getTranslateJoin())) {
                        stringBuffer.append(translateOne.getTranslateKhmer());
                        translateTwo.setTranslateKhmer(stringBuffer.toString());
                        list.add(translateTwo);
                    } else {
                        stringBuffer.append(translateOne.getTranslateKhmer());
                        stringBuffer.append(translateOne.getTranslateJoin());
                        translateTwo.setTranslateKhmer(stringBuffer.toString());
                        list.add(translateTwo);
                    }
                }
                // amqpTemplat.convertAndSend("com.ahsj.msgTwo", 123456);
                return list;
            }
        }
    }

    /**
     * @return java.util.List<com.ahsj.translate.entity.TranslateInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/9
     * @Time 13:25
     **/
    @Override
    @Transactional(readOnly = true)
    public List<TranslateInfo> queryTranslateListAll() {
        List<TranslateInfo> translateInfos = translateInfoMapper.queryTranslateListAll();
        if (CollectionUtils.isEmpty(translateInfos)) {
            log.info("查询失败, 无对应数据");
            return new ArrayList<>();
        } else {
            return translateInfos;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public TranslateInfo queryTranslate(TranslateInfo translateInfo) {
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public Message deleteByTranslate(TranslateInfo translateInfo) {
        if (EmptyUtil.Companion.isNullOrEmpty(translateInfo.getTranslateType()) || EmptyUtil.Companion.isNullOrEmpty(translateInfo.getTranslateId())) {
            log.info("删除失败 , 无对应删除数据");
            return MessageUtil.createMessage(false, "删除失败 , 无对应删除数据");
        } else {
            translateInfoMapper.deleteByTranslate(translateInfo);
            return MessageUtil.createMessage(true, "删除成功 ");
        }

    }

    /**
     * @return java.util.List<com.ahsj.translate.entity.TranslateInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:29
     **/
    @Override
    @Transactional(readOnly = true)
    public List<TranslateInfo> selectByTranslateType() {
        List<TranslateInfo> translateInfos = translateInfoMapper.selectByTranslateType();
        if (EmptyUtil.Companion.isNullOrEmpty(translateInfos)) {
            log.info("查询失败, 无对应数据");
            return new ArrayList<>();
        } else {
            return translateInfos;
        }
    }

    /**
     * @return java.util.List<com.ahsj.translate.entity.TranslateInfo>
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:29
     **/
    @Override
    @Transactional(readOnly = true)
    public List<TranslateInfo> selectByTranslateTypeAndTranslateId(TranslateInfo translateInfo) {
        if (EmptyUtil.Companion.isNullOrEmpty(translateInfo)) {
            log.info("查询失败, 对应参数数据不能为空");
            return new ArrayList<>();
        } else {
            List<TranslateInfo> translateInfos = translateInfoMapper.selectByTranslateTypeAndTranslateId(translateInfo);
            if (EmptyUtil.Companion.isNullOrEmpty(translateInfos)) {
                log.info("查询失败, 无对应数据");
                return new ArrayList<>();
            } else {
                return translateInfos;
            }
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
    public TranslateInfoModel toTranslate(Object obj, Class<?> clazz, Long id, String constants) {
        if (obj == null) {
            return null;
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
            // translateService.addTranslateList(model);
        } catch (Exception e) {
        }
        return model;
    }

}
