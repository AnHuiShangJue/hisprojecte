package com.ahsj.translate.service.impl;

import com.ahsj.translate.common.Constants;
import com.ahsj.translate.common.google.GoogleApi;
import com.ahsj.translate.dao.TranslateMapper;
import com.ahsj.translate.entity.Translate;
import com.ahsj.translate.entity.TranslateModel.TranslateDelete;
import com.ahsj.translate.entity.model.TranslateModel;
import com.ahsj.translate.service.TranslateService;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: TranslateServiceImpl
 * <p>
 * Date:     2019/7/24 10:27
 *
 * @author XJP
 * @create 2019/7/24
 * @since 1.0.0
 */

@Service
public class TranslateServiceImpl implements TranslateService {

    @Autowired
    TranslateMapper translateMapper;

    @Autowired
    AmqpTemplate amqpTemplat;

    private static final String AHSJ_TRANSLATE = "123456";


    /**
     * @return core.message.Message
     * @功能说明
     * @Params [translate]
     * @Author XJP
     * @Date 2019/7/24
     * @Time 11:08
     **/
    @Override
    @Transactional(readOnly = false)
    public Message insert(Translate translate) {
        if (EmptyUtil.Companion.isNullOrEmpty(translate)) {
            return MessageUtil.createMessage(false, "添加失败");
        }
        translateMapper.insert(translate);
        return MessageUtil.createMessage(true, "添加成功");
    }

    /**
     * @return com.ahsj.translate.entity.Translate
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/7/24
     * @Time 11:08
     **/
    @Override
    @Transactional(readOnly = true)
    public Translate selectByPrimaryKey(Long id) {
        return translateMapper.selectByPrimaryKey(id);
    }

    /**
     * @return core.message.Message
     * @功能说明 批量插入 并翻译
     * @Params [translateModel]
     * @Author XJP
     * @Date 2019/7/24
     * @Time 11:08
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addTranslateList(TranslateModel translateModel) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(translateModel) || EmptyUtil.Companion.isNullOrEmpty(translateModel.getUserId())) {
            return MessageUtil.createMessage(false, "操作失败 ， 请重新操作");
        } else {
            GoogleApi googleApi = new GoogleApi();
            List<Translate> translateList = translateModel.getTranslateList();
            List<Translate> insertList = new ArrayList<>();
            List<Translate> updateList = new ArrayList<>();
            for (Translate translate : translateList) {
                List<Translate> select = translateMapper.selectByTranslateCount(translate);
                if (select.size()>0){
                    System.out.println("-->修改");
                    Translate translateOne = new Translate();
                    String china = translate.getTranslateChina();
                    if (StringUtils.isEmpty(china)){
                        continue;
                    }
                    String km = googleApi.translate(china, Constants.TRANSLATE_KHMER_EN);
                    translateOne.setTranslateChina(translate.getTranslateChina());
                    if (km.length() > 0 && km.length() <= 255) {
                        translateOne.setTranslateKhmer(km);
                        translateOne.setTranslateId(translate.getTranslateId());
                        translateOne.setTranslateType(translate.getTranslateType());
                        translateOne.setCreateDate(new Date());
                        System.out.println(translate.getTranslateColumn());
                        translateOne.setTranslateColumn(translate.getTranslateColumn());
                        translateOne.setCreateUserId(translateModel.getUserId());
                        updateList.add(translateOne);
                    } else if(km.length() >255 && km.length() <=510){
                        String substringOne = StringUtils.substring(km, 0, 255);
                        String substringTwo = StringUtils.substring(km, 255, km.length());
                        translateOne.setTranslateKhmer(substringOne);
                        translateOne.setTranslateJoin(substringTwo);
                        translateOne.setTranslateId(translate.getTranslateId());
                        translateOne.setTranslateType(translate.getTranslateType());
                        translateOne.setTranslateColumn(translate.getTranslateColumn());
                        translateOne.setCreateDate(new Date());
                        translateOne.setCreateUserId(translateModel.getUserId());
                        updateList.add(translateOne);
                    }else if(km.length() > 510 && km.length() <= 765){
                        String substringOne = StringUtils.substring(km, 0, 255);
                        String substringTwo = StringUtils.substring(km, 255, 510);
                        String substringThree = StringUtils.substring(km, 510, km.length());
                        translateOne.setTranslateKhmer(substringOne);
                        translateOne.setTranslateJoin(substringTwo);
                        translateOne.setTranslateJoin2(substringThree);
                        translateOne.setTranslateId(translate.getTranslateId());
                        translateOne.setTranslateType(translate.getTranslateType());
                        translateOne.setTranslateColumn(translate.getTranslateColumn());
                        translateOne.setCreateDate(new Date());
                        translateOne.setCreateUserId(translateModel.getUserId());
                        updateList.add(translateOne);
                    }else if(km.length() > 765 && km.length() <=1020){
                        String substringOne = StringUtils.substring(km, 0, 255);
                        String substringTwo = StringUtils.substring(km, 255, 510);
                        String substringThree = StringUtils.substring(km, 510, 765);
                        String substringFour = StringUtils.substring(km, 765, km.length());
                        translateOne.setTranslateKhmer(substringOne);
                        translateOne.setTranslateJoin(substringTwo);
                        translateOne.setTranslateJoin2(substringThree);
                        translateOne.setTranslateJoin3(substringFour);
                        translateOne.setTranslateId(translate.getTranslateId());
                        translateOne.setTranslateType(translate.getTranslateType());
                        translateOne.setTranslateColumn(translate.getTranslateColumn());
                        translateOne.setCreateDate(new Date());
                        translateOne.setCreateUserId(translateModel.getUserId());
                        updateList.add(translateOne);
                    }

//                    translateMapper.updateByTranslates(translate);
                }else {
                    Translate translateOne = new Translate();
                    String china = translate.getTranslateChina();
                    if (StringUtils.isEmpty(china)){
                        continue;
                    }
                    String km = googleApi.translate(china, Constants.TRANSLATE_KHMER_EN);
                    translateOne.setTranslateChina(translate.getTranslateChina());
                    if (km.length() > 0 && km.length() <= 255) {
                        translateOne.setTranslateKhmer(km);
                        translateOne.setTranslateId(translate.getTranslateId());
                        translateOne.setTranslateType(translate.getTranslateType());
                        translateOne.setCreateDate(new Date());
                        System.out.println(translate.getTranslateColumn());
                        translateOne.setTranslateColumn(translate.getTranslateColumn());
                        translateOne.setCreateUserId(translateModel.getUserId());
                        insertList.add(translateOne);
                    } else if(km.length() >255 && km.length() <=510){
                        String substringOne = StringUtils.substring(km, 0, 255);
                        String substringTwo = StringUtils.substring(km, 255, km.length());
                        translateOne.setTranslateKhmer(substringOne);
                        translateOne.setTranslateJoin(substringTwo);
                        translateOne.setTranslateId(translate.getTranslateId());
                        translateOne.setTranslateType(translate.getTranslateType());
                        translateOne.setTranslateColumn(translate.getTranslateColumn());
                        translateOne.setCreateDate(new Date());
                        translateOne.setCreateUserId(translateModel.getUserId());
                        insertList.add(translateOne);
                    }else if(km.length() > 510 && km.length() <= 765){
                        String substringOne = StringUtils.substring(km, 0, 255);
                        String substringTwo = StringUtils.substring(km, 255, 510);
                        String substringThree = StringUtils.substring(km, 510, km.length());
                        translateOne.setTranslateKhmer(substringOne);
                        translateOne.setTranslateJoin(substringTwo);
                        translateOne.setTranslateJoin2(substringThree);
                        translateOne.setTranslateId(translate.getTranslateId());
                        translateOne.setTranslateType(translate.getTranslateType());
                        translateOne.setTranslateColumn(translate.getTranslateColumn());
                        translateOne.setCreateDate(new Date());
                        translateOne.setCreateUserId(translateModel.getUserId());
                        insertList.add(translateOne);
                    }else if(km.length() > 765 && km.length() <=1020){
                        String substringOne = StringUtils.substring(km, 0, 255);
                        String substringTwo = StringUtils.substring(km, 255, 510);
                        String substringThree = StringUtils.substring(km, 510, 765);
                        String substringFour = StringUtils.substring(km, 765, km.length());
                        translateOne.setTranslateKhmer(substringOne);
                        translateOne.setTranslateJoin(substringTwo);
                        translateOne.setTranslateJoin2(substringThree);
                        translateOne.setTranslateJoin3(substringFour);
                        translateOne.setTranslateId(translate.getTranslateId());
                        translateOne.setTranslateType(translate.getTranslateType());
                        translateOne.setTranslateColumn(translate.getTranslateColumn());
                        translateOne.setCreateDate(new Date());
                        translateOne.setCreateUserId(translateModel.getUserId());
                        insertList.add(translateOne);
                    }
                }
//                //先删除后新增操作
//                if (!EmptyUtil.Companion.isNullOrEmpty(translate.getTranslateId()) && !EmptyUtil.Companion.isNullOrEmpty(translate.getTranslateType())) {
//                    translateMapper.deleteByTranslate(translate);
//                }
            }

          /*  for (Translate translate : translateList) {
                Translate translateOne = new Translate();
                String china = translate.getTranslateChina();
                if (StringUtils.isEmpty(china)){
                    continue;
                }
                String km = googleApi.translate(china, Constants.TRANSLATE_KHMER_EN);
                translateOne.setTranslateChina(translate.getTranslateChina());
                if (km.length() > 0 && km.length() <= 255) {
                    translateOne.setTranslateKhmer(km);
                    translateOne.setTranslateId(translate.getTranslateId());
                    translateOne.setTranslateType(translate.getTranslateType());
                    translateOne.setCreateDate(new Date());
                    System.out.println(translate.getTranslateColumn());
                    translateOne.setTranslateColumn(translate.getTranslateColumn());
                    translateOne.setCreateUserId(translateModel.getUserId());
                    list.add(translateOne);
                } else if(km.length() >255 && km.length() <=510){
                    String substringOne = StringUtils.substring(km, 0, 255);
                    String substringTwo = StringUtils.substring(km, 255, km.length());
                    translateOne.setTranslateKhmer(substringOne);
                    translateOne.setTranslateJoin(substringTwo);
                    translateOne.setTranslateId(translate.getTranslateId());
                    translateOne.setTranslateType(translate.getTranslateType());
                    translateOne.setTranslateColumn(translate.getTranslateColumn());
                    translateOne.setCreateDate(new Date());
                    translateOne.setCreateUserId(translateModel.getUserId());
                    list.add(translateOne);
                }else if(km.length() > 510 && km.length() <= 765){
                    String substringOne = StringUtils.substring(km, 0, 255);
                    String substringTwo = StringUtils.substring(km, 255, 510);
                    String substringThree = StringUtils.substring(km, 510, km.length());
                    translateOne.setTranslateKhmer(substringOne);
                    translateOne.setTranslateJoin(substringTwo);
                    translateOne.setTranslateJoin2(substringThree);
                    translateOne.setTranslateId(translate.getTranslateId());
                    translateOne.setTranslateType(translate.getTranslateType());
                    translateOne.setTranslateColumn(translate.getTranslateColumn());
                    translateOne.setCreateDate(new Date());
                    translateOne.setCreateUserId(translateModel.getUserId());
                    list.add(translateOne);
                }else if(km.length() > 765 && km.length() <=1020){
                    String substringOne = StringUtils.substring(km, 0, 255);
                    String substringTwo = StringUtils.substring(km, 255, 510);
                    String substringThree = StringUtils.substring(km, 510, 765);
                    String substringFour = StringUtils.substring(km, 765, km.length());
                    translateOne.setTranslateKhmer(substringOne);
                    translateOne.setTranslateJoin(substringTwo);
                    translateOne.setTranslateJoin2(substringThree);
                    translateOne.setTranslateJoin3(substringFour);
                    translateOne.setTranslateId(translate.getTranslateId());
                    translateOne.setTranslateType(translate.getTranslateType());
                    translateOne.setTranslateColumn(translate.getTranslateColumn());
                    translateOne.setCreateDate(new Date());
                    translateOne.setCreateUserId(translateModel.getUserId());
                    list.add(translateOne);
                }
            }*/
            if (!EmptyUtil.Companion.isNullOrEmpty(insertList) || !EmptyUtil.Companion.isNullOrEmpty(insertList.size())){
                translateMapper.addTranslateList(insertList);
            }
            if (!EmptyUtil.Companion.isNullOrEmpty(updateList) || !EmptyUtil.Companion.isNullOrEmpty(updateList.size())){
                for (Translate translate : updateList) {
                    translateMapper.updateByTranslates(translate);
                }
            }
            amqpTemplat.convertAndSend("com.ahsj.addTranslate", AHSJ_TRANSLATE);
            return MessageUtil.createMessage(true, "操作成功 ");
        }
    }

    /**
     * @return java.util.List<com.ahsj.translate.entity.Translate>
     * @功能说明 通过 模块id type Column 查询
     * @Params [translate]
     * @Author XJP
     * @Date 2019/7/24
     * @Time 16:03
     **/
    @Override
    @Transactional(readOnly = true)
    public List<Translate> queryTranslateList(Translate translate) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(translate.getTranslateType()) /*|| EmptyUtil.Companion.isNullOrEmpty(translate.getTranslateId())*/) {
            return null;
        } else {
            List<Translate> translateList = translateMapper.queryTranslateList(translate);
            List<Translate> list = new ArrayList<>();
            if (CollectionUtils.isEmpty(translateList)) {
                return null;
            } else {
                for (Translate translateOne : translateList) {
                    Translate translateTwo = new Translate();
                    translateTwo.setId(translateOne.getId());
                    translateTwo.setTranslateId(translateOne.getTranslateId());
                    translateTwo.setTranslateType(translateOne.getTranslateType());
                    translateTwo.setTranslateChina(translateOne.getTranslateChina());
                    translateTwo.setCreateUserId(translateOne.getCreateUserId());
                    translateOne.setTranslateColumn(translateOne.getTranslateColumn());
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(translateOne.getTranslateKhmer());
                    if (!StringUtils.isEmpty(translateOne.getTranslateJoin())) {
                        stringBuffer.append(translateOne.getTranslateJoin());
                    }
                    if (!StringUtils.isEmpty(translateOne.getTranslateJoin2())) {
                        stringBuffer.append(translateOne.getTranslateJoin2());
                    }
                    if (!StringUtils.isEmpty(translateOne.getTranslateJoin3())) {
                        stringBuffer.append(translateOne.getTranslateJoin3());
                    }
                    translateTwo.setTranslateKhmer(stringBuffer.toString());
                    list.add(translateTwo);
                }
                return list;
            }
        }
    }

    /**
     * @return java.util.List<com.ahsj.translate.entity.Translate>
     * @功能说明 获得翻译表所有数据
     * @Params []
     * @Author XJP
     * @Date 2019/7/24
     * @Time 16:34
     **/
    @Override
    @Transactional(readOnly = true)
    public List<Translate> queryTranslateListAll() {
        List<Translate> translateList = translateMapper.queryTranslateListAll();
        if (CollectionUtils.isEmpty(translateList)) {
            return null;
        } else {
            return translateList;
        }
    }

    /**
     * @return com.ahsj.translate.entity.Translate
     * @功能说明
     * @Params [translate]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:59
     **/
    @Override
    @Transactional(readOnly = true)
    public Translate queryTranslate(Translate translate) {
        if (EmptyUtil.Companion.isNullOrEmpty(translate)) {
            return null;
        } else {
            Translate translate1 = translateMapper.queryTranslate(translate);
            if (EmptyUtil.Companion.isNullOrEmpty(translate1)) {
                return null;
            } else {
                return translate1;
            }
        }
    }

    /**
     * @return void
     * @功能说明
     * @Params [translateDelete]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:59
     **/
    @Override
    @Transactional(readOnly = false)
    public void deleteTranslate(TranslateDelete translateDelete) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(translateDelete)) {
            return;
        } else {
            Translate translate = new Translate();
            translate.setTranslateId(translateDelete.getId());
            translate.setTranslateType(translateDelete.getModel());
            translateMapper.deleteByTranslate(translate);
        }


    }
}
