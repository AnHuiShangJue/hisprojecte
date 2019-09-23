package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.controller.BaseLoginUser;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisConsumablesDestoryMapper;
import com.ahsj.hiscore.dao.HisConsumablesDetailsMapper;
import com.ahsj.hiscore.dao.HisEnterConsumablesDetailsMapper;
import com.ahsj.hiscore.entity.HisConsumablesDestory;
import com.ahsj.hiscore.entity.HisConsumablesDetails;
import com.ahsj.hiscore.entity.HisEnterConsumablesDetails;
import com.ahsj.hiscore.entity.TranslateModel.HisConsumablesDetailsTranslate;
import com.ahsj.hiscore.entity.TranslateModel.TranslateModels;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisConsumablesDestoryService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HisConsumablesDestoryImpl implements HisConsumablesDestoryService {

    private Logger log = LoggerFactory.getLogger(HisConsumablesDestoryImpl.class.getSimpleName());


    @Autowired
    HisConsumablesDestoryMapper hisConsumablesDestoryMapper;
    @Autowired
    HisEnterConsumablesDetailsMapper hisEnterConsumablesDetailsMapper;

    @Autowired
    HisConsumablesDetailsMapper hisConsumablesDetailsMapper;

    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    AmqpTemplate amqpTemplat;


    @Override
    @Transactional(readOnly = false)
    public Message index(Long[] ids, Integer[] numbers, String[] reasons,String[] destoryTypes) {

        //获取当前时间
        Date nowTime = new Date();

        HisConsumablesDestory hisConsumablesDestory = new HisConsumablesDestory();



        List<String> failName = new ArrayList<>();

        //循环
        for (int i = 0;i < ids.length;++i){
            //根据主键id取出入库表的数据
            HisEnterConsumablesDetails hisEnterConsumablesDetails = hisEnterConsumablesDetailsMapper.selectByPrimaryKey(ids[i]);


            //判断要报损的值与库存可用量的大小
            if (numbers[i] <= hisEnterConsumablesDetails.getSurplus()){

                hisConsumablesDestory.setConsumablesId(hisEnterConsumablesDetails.getConsumablesId());
                hisConsumablesDestory.setDestoryId(hisEnterConsumablesDetails.getId());
                hisConsumablesDestory.setName(hisEnterConsumablesDetails.getName());
                hisConsumablesDestory.setType(hisEnterConsumablesDetails.getType());
                hisConsumablesDestory.setSpec(hisEnterConsumablesDetails.getSpec());
                hisConsumablesDestory.setPrice(hisEnterConsumablesDetails.getPrice());
                hisConsumablesDestory.setValidityPeriod(hisEnterConsumablesDetails.getValidityPeriod());
                hisConsumablesDestory.setDestoryNumber(numbers[i]);
                if (!EmptyUtil.Companion.isNullOrEmpty(reasons)){
                    hisConsumablesDestory.setReason(reasons[i]);
                }
                hisConsumablesDestory.setIsReview(3);//未审核

                if (destoryTypes[i].equals("失效")){
                    hisConsumablesDestory.setDestoryType(1);
                }
                if (destoryTypes[i].equals("过期")){
                    hisConsumablesDestory.setDestoryType(2);
                }
                if (destoryTypes[i].equals("退回")){
                    hisConsumablesDestory.setDestoryType(3);
                }

                hisConsumablesDestory.setDestoryPeriod(nowTime);


                //存入报损记录表
                hisConsumablesDestoryMapper.insert(hisConsumablesDestory);


            }else {
                failName.add(hisEnterConsumablesDetails.getName());
            }
        }

        if (failName.size() == 0){
            return MessageUtil.createMessage(true,"提交报损申请成功");

        }else {
            return MessageUtil.createMessage(false,"有"+failName.size()+"条记录申请报损失败，失败耗材为："+failName.toString());
        }

    }




    @Override
    @Transactional(readOnly=true)
    public PageBean<HisConsumablesDestory> list(PageBean<HisConsumablesDestory> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisConsumablesDestoryMapper.list(pageBean)));
        return pageBean;
    }

    @Override
    @Transactional(readOnly = false)
    public Message review(Long[] ids) {

        int allNumber = 0;

        List<String> failName = new ArrayList<>();


        for (int i = 0 ; i < ids.length ; ++i){

            //对应的审核记录
            HisConsumablesDestory hisConsumablesDestory = hisConsumablesDestoryMapper.selectByPrimaryKey(ids[i]);

            //用destory_id（enter表的主键） 查找对应入库记录
            HisEnterConsumablesDetails hisEnterConsumablesDetails = hisEnterConsumablesDetailsMapper.selectByPrimaryKey(hisConsumablesDestory.getDestoryId());

            if (hisConsumablesDestory.getDestoryNumber() <= hisEnterConsumablesDetails.getSurplus()){
                //修改入库表中的可用量
                hisEnterConsumablesDetails.setSurplus(hisEnterConsumablesDetails.getSurplus() - hisConsumablesDestory.getDestoryNumber());
                hisEnterConsumablesDetailsMapper.updateByPrimaryKey(hisEnterConsumablesDetails);

                //修改审核记录的审核字段---通过
                hisConsumablesDestory.setIsReview(1);
                hisConsumablesDestoryMapper.updateByPrimaryKey(hisConsumablesDestory);

                allNumber = hisConsumablesDestory.getDestoryNumber() + allNumber;

            }else {
                failName.add(hisEnterConsumablesDetails.getName());
            }

            //最后一次循环时修改库存表的耗材库存
            if (i == (ids.length -1)){
            //修改库存量
                    HisConsumablesDetails hisConsumablesDetails = hisConsumablesDetailsMapper.selectConsumablesId(hisEnterConsumablesDetails.getConsumablesId());
                    hisConsumablesDetails.setStock(hisConsumablesDetails.getStock()-allNumber);
                    hisConsumablesDetailsMapper.updateByPrimaryKey(hisConsumablesDetails);
                log.info("--------------------耗材库存修改翻译发送开始--------------------------");
                BaseLoginUser loginUser = new BaseLoginUser();
                TranslateModels translateModels = new TranslateModels();
                HisConsumablesDetailsTranslate translate = new HisConsumablesDetailsTranslate();
                BeanUtils.copyProperties(hisConsumablesDetails, translate);
                translateModels.setUserId(loginUser.getId());
                translateModels.setHisConsumablesDetailsTranslate(translate);
                amqpTemplat.convertAndSend("com.ahsj.updateHisConsumablesDetails", JsonUtils.serialize(translateModels));
                log.info(JsonUtils.serialize(translateModels));
                log.info("--------------------耗材库存修改翻译发送结束--------------------------");
                }

        }

        if (failName.size() == 0){
            return MessageUtil.createMessage(true,"审核通过，已完成报损");

        }else {
            return MessageUtil.createMessage(false,"有"+failName.size()+"条申请报损失败，失败耗材为："+failName.toString());
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Message unreview(Long[] ids) {
        for (int i = 0;i < ids.length ;++i){
            //对应的审核记录
            HisConsumablesDestory hisConsumablesDestory = hisConsumablesDestoryMapper.selectByPrimaryKey(ids[i]);
            hisConsumablesDestory.setIsReview(2);
            hisConsumablesDestoryMapper.updateByPrimaryKey(hisConsumablesDestory);
        }
        return  MessageUtil.createMessage(true,"未审核通过");
    }

    @Override
    @Transactional(readOnly = false)
    public HisConsumablesDestory selectByPrimaryId(Long id) {
        return hisConsumablesDestoryMapper.selectByPrimaryKey(id);
    }



}
