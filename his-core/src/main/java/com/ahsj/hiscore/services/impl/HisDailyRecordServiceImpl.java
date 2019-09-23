package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.*;
import com.ahsj.hiscore.entity.HisDailyRecord;
import com.ahsj.hiscore.entity.HisRegistered;
import com.ahsj.hiscore.entity.HisRegisteredpay;
import com.ahsj.hiscore.entity.HisTollRecord;
import com.ahsj.hiscore.services.HisDailyRecordService;
import core.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: his
 * @description:HisDailyRecordService
 * @author: chenzhicai
 * @create: 2019-07-20-16-18
 **/
@Service
public class HisDailyRecordServiceImpl implements HisDailyRecordService {

    Logger logger = LoggerFactory.getLogger(HisDailyRecordServiceImpl.class.getSimpleName());

    @Autowired
    HisDailyRecordMapper hisDailyRecordMapper;

    @Autowired
    HisRegisteredpayMapper hisRegisteredpayMapper;

    @Autowired
    HisTollDetailsMapper hisTollDetailsMapper;
    @Autowired
    HisTollRecordMapper hisTollRecordMapper;

    @Autowired
    HisRegisteredMapper hisRegisteredMapper;


    /**
     * @return core.message.Message
     * @Description 开始结算
     * @Params []
     * @Author chenzhicai
     * @Date 2019-07-20
     * @Time 16:18
     **/
    @Override
    @Transactional(readOnly = false)
    public Message startCalculate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createdate = sdf.format(date);
        logger.info(createdate);
        //挂号日结
        List<HisRegisteredpay> hisRegisteredpayList = hisRegisteredpayMapper.selectByDate();
        if (!EmptyUtil.Companion.isNullOrEmpty(hisRegisteredpayList) && !EmptyUtil.Companion.isNullOrEmpty(hisRegisteredpayList.size())) {
            Long[] ids = new Long[hisRegisteredpayList.size()];
            int i = 0;
            for (HisRegisteredpay h : hisRegisteredpayList
            ) {
                ids[i] = h.getRegisteredId();
                i++;
            }
            List<HisRegistered> hisRegistereds = hisRegisteredMapper.selectForDaily(ids);
            if (!EmptyUtil.Companion.isNullOrEmpty(hisRegistereds) && !EmptyUtil.Companion.isNullOrEmpty(hisRegisteredpayList.size())) {
                logger.info("-------------------开始挂号日结-----------------------");
                BigDecimal registerMoney = startRegisterDaily(hisRegisteredpayList, hisRegistereds);
                HisDailyRecord hisDailyRecord = new HisDailyRecord();
                hisDailyRecord.setCreateDate(date);
                hisDailyRecord.setTotalMoney(registerMoney);
                //1对应挂号结算
                hisDailyRecord.setType(1);
                hisDailyRecordMapper.insert(hisDailyRecord);
                hisRegisteredMapper.updateBatchForDaily(hisRegistereds);
                logger.info("-----------------挂号日结总计为：" + registerMoney + "-----------------------");
                logger.info("-------------------结束挂号日结-----------------------");
            } else {
                logger.info("-------------------挂号日结结束，今天无可结算数目-----------------------");
            }
        } else {
            logger.warn("挂号日结有异常！");
        }
        //门诊住院收费日结
        List<HisTollRecord> hisTollRecordList = hisTollRecordMapper.selectByDate();
        if (!EmptyUtil.Companion.isNullOrEmpty(hisTollRecordList) && !EmptyUtil.Companion.isNullOrEmpty(hisTollRecordList.size())) {
            Long[] ids = new Long[hisTollRecordList.size()];
            int i = 0;
            for (HisTollRecord h : hisTollRecordList
            ) {
                if(h.getAttenchType().equals(1)){
                    ids[i] = h.getAttenchId();
                    i++;
                }
            }
                logger.info("-------------------开始门诊住院日结-----------------------");
                BigDecimal registerMoney = startMedicineDaily(hisTollRecordList);
                HisDailyRecord hisDailyRecord = new HisDailyRecord();
                hisDailyRecord.setCreateDate(date);
                hisDailyRecord.setTotalMoney(registerMoney);
                //2对应门诊住院结算
                hisDailyRecord.setType(2);
                hisDailyRecordMapper.insert(hisDailyRecord);
                hisTollRecordMapper.updateBatchForDaily(hisTollRecordList);
                logger.info("-----------------门诊住院日结总计为：" + registerMoney + "-----------------------");
                logger.info("-------------------结束门诊住院日结-----------------------");
        } else {
        logger.info("-------------------门诊住院日结结束，今天无可结算数目-----------------------");
        }

        return null;
    }

    
    private BigDecimal startMedicineDaily(List<HisTollRecord> hisTollRecordList) {
        BigDecimal total = new BigDecimal(0.00);
        for (HisTollRecord hrp : hisTollRecordList) {
            total = total.add(hrp.getMoney());
            hrp.setIsSettlement(1);
        }
        return total;
    }

    /**
     * @return core.message.Message
     * @Description 计算挂号日结
     * @Params []
     * @Author chenzhicai
     * @Date 2019-07-20
     * @Time 16:18
     **/
    private BigDecimal startRegisterDaily(List<HisRegisteredpay> hisRegisteredpayList, List<HisRegistered> hisRegistereds) {
        BigDecimal total = new BigDecimal(0.00);
        for (HisRegisteredpay hrp : hisRegisteredpayList) {
            for (HisRegistered hr : hisRegistereds) {
                //如果id相同则说明已经付费
                if (hrp.getRegisteredId().equals(hr.getId())) {
                    //累加数额
                    total = total.add(hrp.getAmountReceivable());
                    //设置该条为结算
                    hr.setIsSettlement(1);
                    //说明收费了但是没有接诊
                    if(hr.getIsReceived().equals(2)){
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String createdate = sdf.format(date);
                        hr.setIsObsolete(1);
                        hr.setRemark(hr.getRemark()+",该挂号单由于过了失效期，所以作废,作废时间为："+createdate);
                    }
                }
            }
        }
        return total;
    }
}

    