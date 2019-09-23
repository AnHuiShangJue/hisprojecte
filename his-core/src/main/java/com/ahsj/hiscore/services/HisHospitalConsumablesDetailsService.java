package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisHospitalConsumablesDetails;
import core.message.Message;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisHospitalConsumablesDetailsService
 * <p>
 * Date:     2019/8/7 9:56
 *
 * @author XJP
 * @create 2019/8/7
 * @since 1.0.0
 */

public interface HisHospitalConsumablesDetailsService {
    /**
     * @return void
     * @功能说明 批量增加
     * @Params [hospitalConsumablesDetailsList]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 13:35
     **/
    Message insertHisHospitalConsumablesDetails(List<HisHospitalConsumablesDetails> hospitalConsumablesDetailsList) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisHospitalConsumablesDetails
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:02
     **/
    HisHospitalConsumablesDetails selectByPrimaryKey(Long id) throws Exception;

    /**
     * @return java.util.List<java.lang.Integer>
     * @功能说明
     * @Params [ids]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:02
     **/
    List<Integer> selectHisHospitalConsumablesDetailsByIds(Long[] ids) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [hisHospitalConsumablesDetails]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:02
     **/
    Message updateByPrimaryKeySelective(HisHospitalConsumablesDetails hisHospitalConsumablesDetails) throws Exception;

    /**
     * @return void
     * @功能说明 批量更新
     * @Params [hospitalConsumablesDetailsList]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 13:35
     **/
    Message updateHisHospitalConsumablesDetails(List<HisHospitalConsumablesDetails> hospitalConsumablesDetailsList);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisHospitalConsumablesDetails>
     * @功能说明
     * @Params [consumableNumber]
     * @Author XJP
     * @Date 2019/8/15
     * @Time 19:11
     **/
    List<HisHospitalConsumablesDetails> getHisHospitalConsumablesDetailsConsumableNumber(String consumableNumber);
}
