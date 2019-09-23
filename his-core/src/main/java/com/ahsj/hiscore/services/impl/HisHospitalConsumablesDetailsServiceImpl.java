package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisHospitalConsumablesDetailsMapper;
import com.ahsj.hiscore.entity.HisHospitalConsumablesDetails;
import com.ahsj.hiscore.services.HisHospitalConsumablesDetailsService;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisHospitalConsumablesDetailsServiceImpl
 * <p>
 * Date:     2019/8/7 9:57
 *
 * @author XJP
 * @create 2019/8/7
 * @since 1.0.0
 */
@Service
public class HisHospitalConsumablesDetailsServiceImpl implements HisHospitalConsumablesDetailsService {

    @Autowired
    HisHospitalConsumablesDetailsMapper hisHospitalConsumablesDetailsMapper;

    private static final Integer HISHOSPITAL_CONSUMABLESDETAILS_IS_OUTBOUND = 1;


    private Logger log = LoggerFactory.getLogger(HisHospitalConsumablesDetailsServiceImpl.class.getSimpleName());

    /**
     * @return core.message.Message
     * @功能说明 保存住院病人-耗材库关联数据
     * @Params [hospitalConsumablesDetailsList]
     * @Author XJP
     * @Date 2019/8/7
     * @Time 14:26
     **/
    @Override
    @Transactional(readOnly = false)
    public Message insertHisHospitalConsumablesDetails(List<HisHospitalConsumablesDetails> hospitalConsumablesDetailsList) throws Exception {
        if (CollectionUtils.isEmpty(hospitalConsumablesDetailsList)) {
            log.info("保存失败 , 保存数据不能为空！");
            return MessageUtil.createMessage(false, " 保存失败 , 保存数据不能为空！");
        } else {
            hisHospitalConsumablesDetailsMapper.insertHisHospitalConsumablesDetails(hospitalConsumablesDetailsList);
            log.info("保存数据成功 ！！！！！");
            return MessageUtil.createMessage(true, " 保存成功 ！！！！");
        }
    }

    /**
     * @return com.ahsj.hiscore.entity.HisHospitalConsumablesDetails
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 13:18
     **/
    @Override
    @Transactional(readOnly = true)
    public HisHospitalConsumablesDetails selectByPrimaryKey(Long id) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(id)) {
            log.info("查询失败 , 请求参数数据不能为空！");
            return null;
        } else {
            HisHospitalConsumablesDetails hisHospitalConsumablesDetails = hisHospitalConsumablesDetailsMapper.selectByPrimaryKey(id);
            if (EmptyUtil.Companion.isNullOrEmpty(hisHospitalConsumablesDetails)) {
                log.info("查询失败 , 查询结果数据不存在 ！");
                return null;
            } else {
                log.info("查询成功 , 查询结果数据 为 --> " + hisHospitalConsumablesDetails.toString());
                return hisHospitalConsumablesDetails;
            }
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisHospitalConsumablesDetails>
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 15:45
     **/
    @Override
    @Transactional(readOnly = true)
    public List<Integer> selectHisHospitalConsumablesDetailsByIds(Long[] ids) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(ids)) {
            log.info("查询失败 , 请求参数数据不能为空！");
            return null;
        }else {
            Arrays.stream(ids).forEach(System.out::println);

            Stream<HisHospitalConsumablesDetails> hospitalConsumablesDetailsList = Arrays.stream(ids).map((e) -> hisHospitalConsumablesDetailsMapper.selectByPrimaryKey(e.longValue()));
            List<Integer> listNum = hospitalConsumablesDetailsList.map(HisHospitalConsumablesDetails::getComsumablesNum).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(listNum)){
            return null;
        }
        return listNum;
        }
    }

    /**
     * @return int
     * @功能说明 修改出库状态
     * @Params [hisHospitalConsumablesDetails]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 13:19
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateByPrimaryKeySelective(HisHospitalConsumablesDetails hisHospitalConsumablesDetails) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisHospitalConsumablesDetails) || EmptyUtil.Companion.isNullOrEmpty(hisHospitalConsumablesDetails.getId())) {
            log.info("修改失败 , 修改参数数据不能为空！");
            return MessageUtil.createMessage(false, " 修改失败 , 修改参数数据不能为空！");
        } else {
            hisHospitalConsumablesDetailsMapper.updateByPrimaryKeySelective(hisHospitalConsumablesDetails);
            return MessageUtil.createMessage(true, " 修改成功 ！");
        }
    }

    /**
     * @return void
     * @功能说明 批量更新
     * @Params [hospitalConsumablesDetailsList]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 13:35
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateHisHospitalConsumablesDetails(List<HisHospitalConsumablesDetails> hospitalConsumablesDetailsList) {
        if (EmptyUtil.Companion.isNullOrEmpty(hospitalConsumablesDetailsList)) {
            log.info("修改失败 , 修改参数数据不能为空！");
            return MessageUtil.createMessage(false, " 修改失败 , 修改参数数据不能为空！");
        } else {
            List<Long> list = hospitalConsumablesDetailsList.stream().map(HisHospitalConsumablesDetails::getId).collect(Collectors.toList());
            List<Integer> integerList = hospitalConsumablesDetailsList.stream().map(HisHospitalConsumablesDetails::getIsOutbound).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(list) || CollectionUtils.isEmpty(integerList)) {
                log.info("修改失败 , 修改参数数据不能为空！");
                return MessageUtil.createMessage(false, " 修改失败 , 修改参数数据不能为空！");
            } else {
                for (HisHospitalConsumablesDetails consumablesDetails : hospitalConsumablesDetailsList) {
                    consumablesDetails.setIsOutbound(HISHOSPITAL_CONSUMABLESDETAILS_IS_OUTBOUND);
                }
                hisHospitalConsumablesDetailsMapper.updateHisHospitalConsumablesDetails(hospitalConsumablesDetailsList);
                return MessageUtil.createMessage(true, " 修改成功 ！");
            }
        }
    }

    @Transactional(readOnly = true)
    public List<HisHospitalConsumablesDetails> getHisHospitalConsumablesDetailsConsumableNumber(String consumableNumber) {
        if (EmptyUtil.Companion.isNullOrEmpty(consumableNumber)){
            return  new ArrayList<>();
        }else {
            List<HisHospitalConsumablesDetails> list= hisHospitalConsumablesDetailsMapper.getHisHospitalConsumablesDetailsConsumableNumber(consumableNumber);
            if (EmptyUtil.Companion.isNullOrEmpty(list)){
                return new ArrayList<>();
            }else {
                return list;
            }
        }
    }
}
