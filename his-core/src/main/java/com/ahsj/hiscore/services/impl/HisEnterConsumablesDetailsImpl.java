package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.utils.DateNumber;
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
import java.util.*;
import java.util.stream.Collectors;


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
/*

        List<HisConsumablesDetails> hisConsumablesDetailsListInsert = new ArrayList<>();
        List<HisConsumablesDetails> hisConsumablesDetailsListUpdate = new ArrayList<>();

        for (int i = 0;i<hisEnterConsumablesDetailsList.size();++i){
            //根据传入的 入库详情 对象的 耗材id，在 耗材信息表 中查找
            HisConsumables checkId = hisConsumablesMapper.selectByHisConsumablesCode(hisEnterConsumablesDetailsList.get(i).getConsumablesCode());
            //判断是否存在
            if (!EmptyUtil.Companion.isNullOrEmpty(checkId)) {
                //非空，即存在该耗材
                //在耗材库存表中 根据传入的 耗材id 找到对应的 耗材库存 信息（list
                List<HisConsumablesDetails> checkConsumablesId = hisConsumablesDetailsMapper.selectByConsumablesId(checkId.getId());
                //判断是否存在
                if(EmptyUtil.Companion.isNullOrEmpty(checkConsumablesId)){
                    //根据 入库耗材id 在耗材信息表 中查找对应的 耗材信息
                    HisConsumables hisConsumablesGetinformation = hisConsumablesMapper.selectByHisConsumablesCode(hisEnterConsumablesDetailsList.get(i).getConsumablesCode());
                    //new 一个耗材库存对象
                    HisConsumablesDetails hisConsumablesDetailsInsert = new HisConsumablesDetails();

                    //给库存表set字段信息
                    hisConsumablesDetailsInsert.setConsumablesCode(hisConsumablesGetinformation.getConsumablesCode());
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
*/

    }






    /**
     *@Description 耗材入库
     *@MethodName mediEnter
     *@Params [ids, numbers, prices, buyplanCode, validityPeriods]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/25
     *@Time 13:13
    **/
    @Override
    @Transactional(readOnly = false)
    public Message mediEnter(Long[] ids, Integer[] numbers, Double[] prices, String buyplanCode,String[] validityPeriods) throws Exception {

        //从采购计划详细表中取得该计划编号对应的耗材信息
        List<HisConsumablesBuyplanDetails> hisConsumablesBuyplanDetailsList = hisConsumablesBuyplanDetailsMapper.selectByBuyplanCode(buyplanCode);
       if (EmptyUtil.Companion.isNullOrEmpty(validityPeriods)){
           return MessageUtil.createMessage(false,"请填写有效时间");
       }
       if (ids.length != validityPeriods.length || ids.length != numbers.length || ids.length != prices.length){
           return MessageUtil.createMessage(false,"请填写完整数据");
       }
        //判断是否取到值
        if(EmptyUtil.Companion.isNullOrEmpty(hisConsumablesBuyplanDetailsList)){
            //未取到
            return MessageUtil.createMessage(false,"无可添加数据");
        }else {
            //取到值
            //设置变量存储实际支出值
            double actualExpengiture=0;

            //声明入库详细对象集合
            List<HisEnterConsumablesDetails> hisEnterConsumablesDetailsList = new ArrayList<>();

            for (int i = 0; i < hisConsumablesBuyplanDetailsList.size(); i++) {
                HisConsumablesBuyplanDetails hisConsumablesBuyplanDetails = hisConsumablesBuyplanDetailsList.get(i);
                //new一个入库详细对象
                HisEnterConsumablesDetails hisEnterConsumablesDetails=new HisEnterConsumablesDetails();
                //set采购计划详情对象的consumablesId到入库对象
                hisEnterConsumablesDetails.setConsumablesCode(hisConsumablesBuyplanDetails.getConsumablesCode());
                //set 名称、规格、价格到入库表
                hisEnterConsumablesDetails.setName(hisConsumablesBuyplanDetails.getName());

                //在 耗材信息表 中查找对应 耗材consumablesId 的 type
                HisConsumables hisConsumables = hisConsumablesMapper.selectByHisConsumablesCode(hisConsumablesBuyplanDetails.getConsumablesCode());
                //该type设置到入库表里
                hisEnterConsumablesDetails.setType(hisConsumables.getType());
                hisEnterConsumablesDetails.setSpec(hisConsumablesBuyplanDetails.getSpec());
                hisEnterConsumablesDetails.setPrice(prices[i]);
                //设置入库数量
                hisEnterConsumablesDetails.setEnterCount(numbers[i]);
                hisEnterConsumablesDetails.setSurplus(numbers[i]);
                hisEnterConsumablesDetails.setEnterCdoe(DateNumber.getNumbenr(Constants.HIS_HCRK));

                //日期格式化
                SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
                Date validityPeriod=null;
                validityPeriod=format.parse(validityPeriods[i]);
                //设置有效期到入库表
                hisEnterConsumablesDetails.setValidityPeriod(validityPeriod);
                hisEnterConsumablesDetails.setSellingPrice(hisConsumables.getPrice());
                hisEnterConsumablesDetails.setIsDelete(Constants.HIS_DELETE_FALSE);


                //计算实际预算
                actualExpengiture += prices[i]*numbers[i];

                hisEnterConsumablesDetailsList.add(hisEnterConsumablesDetails);

            }
            //循环遍历前端出过来的数组变量
         /*   for (int i = 0; i <ids.length ; i++) {
                //new一个入库详细对象
                HisEnterConsumablesDetails hisEnterConsumablesDetails=new HisEnterConsumablesDetails();
                //根据计划详情的记录id(耗材的id) 在采购计划详情表 中查找 采购计划详情对象
                HisConsumablesBuyplanDetails hisConsumablesBuyplanDetails=hisConsumablesBuyplanDetailsMapper.selectByPrimaryKey(ids[i]);
                //set采购计划详情对象的consumablesId到入库对象
                hisEnterConsumablesDetails.setConsumablesCode(hisConsumablesBuyplanDetails.getConsumablesCode());
                //set 名称、规格、价格到入库表
                hisEnterConsumablesDetails.setName(hisConsumablesBuyplanDetails.getName());

                //在 耗材信息表 中查找对应 耗材consumablesId 的 type
                HisConsumables hisConsumables = hisConsumablesMapper.selectByHisConsumablesCode(hisConsumablesBuyplanDetails.getConsumablesCode());
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


            }*/
            //入库同时更新药库记录（传入 入库详情 对象）

//            saveOrUpdate(hisEnterConsumablesDetailsList);
            hisEnterConsumablesDetailsMapper.insertBatch(hisEnterConsumablesDetailsList);

            //查找该 计划编号 对应的 采购计划
            HisConsumablesBuyplan hisConsumablesBuyplan=hisConsumablesBuyplanMapper.selectBybuyplanCode(buyplanCode);
            //设置新的 完成情况
            hisConsumablesBuyplan.setCompletion(1);
            //设置 完成时间
            hisConsumablesBuyplan.setCompletionTime(new Date());
            //设置实际预算
            hisConsumablesBuyplan.setActualExpenditure(BigDecimal.valueOf(actualExpengiture));
            //更新采购计划记录表
            //伪删
            hisConsumablesBuyplanMapper.updateByIsDelete(hisConsumablesBuyplan);
            //新增
            hisConsumablesBuyplan.setId(null);
            hisConsumablesBuyplan.setIsDelete(Constants.HIS_DELETE_FALSE);
            hisConsumablesBuyplanMapper.insert(hisConsumablesBuyplan);
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
        List<HisEnterConsumablesDetails> list = hisEnterConsumablesDetailsMapper.list(pageBean);


       /* List<HisEnterConsumablesDetails> collect = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(HisEnterConsumablesDetails::getConsumablesCode))), ArrayList::new));*/

        pageBean.setData(CodeHelper.getInstance().setCodeValue(list));

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
    public PageBean<HisEnterConsumablesDetails> onelist(String consumablesCode) throws Exception {
        PageBean<HisEnterConsumablesDetails> pageBean = new PageBean<HisEnterConsumablesDetails>();
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisEnterConsumablesDetailsMapper.onelist(consumablesCode)));
        return pageBean;
    }

    /**
     *@Description
     *@MethodName selectByEnterConsumablesDetailsId
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisEnterConsumablesDetails
     *@Author XJP
     *@Date 2020/4/26
     *@Time 16:19
    **/
    @Override
    @Transactional(readOnly = true)
    public HisEnterConsumablesDetails selectByEnterConsumablesDetailsId(Long id) throws Exception {
        return hisEnterConsumablesDetailsMapper.selectByPrimaryKey(id);
    }

}
