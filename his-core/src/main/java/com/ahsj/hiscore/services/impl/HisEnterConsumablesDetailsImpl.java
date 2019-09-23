package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.*;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.HisEnterConsumablesDetailsService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class HisEnterConsumablesDetailsImpl implements HisEnterConsumablesDetailsService {
    @Autowired
    HisEnterConsumablesDetailsMapper hisEnterConsumablesDetailsMapper;

    @Autowired
    HisConsumablesDetailsMapper hisConsumablesDetailsMapper;

    @Autowired
    HisConsumablesMapper hisConsumablesMapper;

    @Autowired
    HisConsumablesBuyplanDetailsMapper hisConsumablesBuyplanDetailsMapper;

    @Autowired
    HisConsumablesBuyplanMapper hisConsumablesBuyplanMapper;


    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(@NotNull List<HisEnterConsumablesDetails> hisEnterConsumablesDetailsList) throws Exception {

        List<HisConsumablesDetails> hisConsumablesDetailsListInsert = new ArrayList<>();
        List<HisConsumablesDetails> hisConsumablesDetailsListUpdate = new ArrayList<>();

        for (int i = 0;i<hisEnterConsumablesDetailsList.size();++i){
            //根据传入的 入库详情 对象的 耗材id，在 耗材信息表 中查找
            HisConsumables checkId = hisConsumablesMapper.selectByPrimaryKey(hisEnterConsumablesDetailsList.get(i).getConsumablesId());
            //判断是否存在
            if (!EmptyUtil.Companion.isNullOrEmpty(checkId)) {
                //非空，即存在该耗材
                //在耗材库存表中 根据传入的 耗材id 找到对应的 耗材库存 信息（list
                List<HisConsumablesDetails> checkConsumablesId = hisConsumablesDetailsMapper.selectByConsumablesId(checkId.getId());
                //判断是否存在
                if(EmptyUtil.Companion.isNullOrEmpty(checkConsumablesId)){
                    //根据 入库耗材id 在耗材信息表 中查找对应的 耗材信息
                    HisConsumables hisConsumablesGetinformation = hisConsumablesMapper.selectByPrimaryKey(hisEnterConsumablesDetailsList.get(i).getConsumablesId());
                    //new 一个耗材库存对象
                    HisConsumablesDetails hisConsumablesDetailsInsert = new HisConsumablesDetails();

                    //给库存表set字段信息
                    hisConsumablesDetailsInsert.setConsumablesId(hisConsumablesGetinformation.getId());
                    hisConsumablesDetailsInsert.setName(hisConsumablesGetinformation.getName());
                    hisConsumablesDetailsInsert.setType(hisConsumablesGetinformation.getType());
                    hisConsumablesDetailsInsert.setSpec(hisConsumablesGetinformation.getSpec());
                    hisConsumablesDetailsInsert.setStock(hisEnterConsumablesDetailsList.get(i).getEnterCount());
                    hisConsumablesDetailsInsert.setPrice(hisEnterConsumablesDetailsList.get(i).getPrice());

                    //查找库存警告
                    List<HisConsumablesDetails> hisConsumablesDetails = hisConsumablesDetailsMapper.selectByConsumablesId(hisConsumablesGetinformation.getId());
                        //第一次入库，设置库存表中的warn值为0
                        hisConsumablesDetailsInsert.setStockWarn(hisConsumablesGetinformation.getStock());

                    //添加到list
                    hisConsumablesDetailsListInsert.add(hisConsumablesDetailsInsert);
                }else {
                    checkConsumablesId.get(0).setStock(checkConsumablesId.get(0).getStock()+hisEnterConsumablesDetailsList.get(i).getEnterCount());
                    hisConsumablesDetailsMapper.updateByPrimaryKeySelective(checkConsumablesId.get(0));
                    //新增入库记录到入库记录表
                }
            }
        }

        //新增入库记录表（list
        hisEnterConsumablesDetailsMapper.insertBatch(hisEnterConsumablesDetailsList);
        //第一次入库时才新增耗材库存表（list
        if (hisConsumablesDetailsListInsert.size()>0){
            hisConsumablesDetailsMapper.insertBatch(hisConsumablesDetailsListInsert);
        }

    }




    /**
     *@Description 耗材入库，set前端传过来的值
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/5
     *@Time 21:21
    */
    @Override
    @Transactional(readOnly = false)
    public Message mediEnter(Long[] ids, Integer[] numbers, Double[] prices, Long buyplanId,String[] validityPeriods) throws Exception {

        //从采购计划详细表中取得该计划编号对应的耗材信息
        List<HisConsumablesBuyplanDetails> hisConsumablesBuyplanDetailsList=hisConsumablesBuyplanDetailsMapper.selectByBuyplanId(buyplanId);
        //判断是否取到值
        if(EmptyUtil.Companion.isNullOrEmpty(hisConsumablesBuyplanDetailsList)){
            //未取到
            return MessageUtil.createMessage(false,"无可添加数据");
        }else {
            //取到值
            //设置变量存储实际支出值
            double actualExpengiture=0;

            //声明list
            List<HisEnterConsumablesDetails> hisEnterConsumablesDetailsList = new ArrayList<>();

            //循环遍历前端出过来的数组变量
            for (int i = 0; i <ids.length ; i++) {
                //new一个入库详细对象
                HisEnterConsumablesDetails hisEnterConsumablesDetails=new HisEnterConsumablesDetails();
                //根据计划详情的记录id(耗材的id) 在采购计划详情表 中查找 采购计划详情对象
                HisConsumablesBuyplanDetails hisConsumablesBuyplanDetails=hisConsumablesBuyplanDetailsMapper.selectByPrimaryKey(ids[i]);
                //set采购计划详情对象的consumablesId到入库对象
                hisEnterConsumablesDetails.setConsumablesId(hisConsumablesBuyplanDetails.getConsumablesId());
                //set 名称、规格、价格到入库表
                hisEnterConsumablesDetails.setName(hisConsumablesBuyplanDetails.getName());

                //在 耗材信息表 中查找对应 耗材consumablesId 的 type
                HisConsumables hisConsumables = hisConsumablesMapper.selectByPrimaryKey(hisConsumablesBuyplanDetails.getConsumablesId());
                    //该type设置到入库表里
                hisEnterConsumablesDetails.setType(hisConsumables.getType());
                hisEnterConsumablesDetails.setSpec(hisConsumablesBuyplanDetails.getSpec());
                hisEnterConsumablesDetails.setPrice(prices[i]);
                //设置入库数量
                hisEnterConsumablesDetails.setEnterCount(numbers[i]);
                hisEnterConsumablesDetails.setSurplus(numbers[i]);



                //日期格式化
                SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
                Date validityPeriod=null;
                validityPeriod=format.parse(validityPeriods[i]);
                //设置有效期到入库表
                hisEnterConsumablesDetails.setValidityPeriod(validityPeriod);


                //计算实际预算
                actualExpengiture += prices[i]*numbers[i];

                hisEnterConsumablesDetailsList.add(hisEnterConsumablesDetails);


            }
            //入库同时更新药库记录（传入 入库详情 对象）
            saveOrUpdate(hisEnterConsumablesDetailsList);

            //查找该 计划编号 对应的 采购计划
            HisConsumablesBuyplan hisConsumablesBuyplan=hisConsumablesBuyplanMapper.selectByBuyplanId(buyplanId);
            //设置新的 完成情况
            hisConsumablesBuyplan.setCompletion(1);
            //设置 完成时间
            hisConsumablesBuyplan.setCompletionTime(new Date());
            //设置实际预算
            hisConsumablesBuyplan.setActualExpenditure(BigDecimal.valueOf(actualExpengiture));
            //更新采购计划记录表
            hisConsumablesBuyplanMapper.updateByPrimaryKeySelective(hisConsumablesBuyplan);
            return MessageUtil.createMessage(true,"入库成功");
        }
    }

    /**
     *@Description list
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/8
     *@Time 20:54
    */
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisEnterConsumablesDetails> list(PageBean<HisEnterConsumablesDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisEnterConsumablesDetailsMapper.list(pageBean)));
        return pageBean;
    }

    /**
     *@Description 单种耗材记录
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/9
     *@Time 10:10
    */
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisEnterConsumablesDetails> onelist(Long consumablesId) throws Exception {
        PageBean<HisEnterConsumablesDetails> pageBean = new PageBean<HisEnterConsumablesDetails>();
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisEnterConsumablesDetailsMapper.onelist(consumablesId)));
        return pageBean;
    }

}
