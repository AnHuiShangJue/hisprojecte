package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisHospitalConsumablesDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisHospitalConsumablesDetailsMapper extends BaseMapper<HisHospitalConsumablesDetails>{

    int deleteByPrimaryKey(Long id);

    int insert(HisHospitalConsumablesDetails hisHospitalConsumablesDetails);

    int insertSelective(HisHospitalConsumablesDetails hisHospitalConsumablesDetails);

    HisHospitalConsumablesDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisHospitalConsumablesDetails hisHospitalConsumablesDetails);

    int updateByPrimaryKey(HisHospitalConsumablesDetails hisHospitalConsumablesDetails);

    /**
     * @return void
     * @功能说明 批量增加
     * @Params [hospitalConsumablesDetailsList]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 13:35
     **/
    void insertHisHospitalConsumablesDetails(List<HisHospitalConsumablesDetails> hospitalConsumablesDetailsList);
    /**
     *@功能说明 批量更新
     *@Params [hospitalConsumablesDetailsList]
     *@return void
     *@Author XJP
     *@Date 2019/8/8
     *@Time 13:35
    **/
    void updateHisHospitalConsumablesDetails(List<HisHospitalConsumablesDetails> hospitalConsumablesDetailsList);

    HisHospitalConsumablesDetails selectByConsumablesId(long hisConsumablesDetailsId);

    List<HisHospitalConsumablesDetails> getHisHospitalConsumablesDetailsConsumableNumber(String consumableNumber);

    /**
     *@Description
     *@MethodName updateByIsDelete
     *@Params [id]
     *@return int
     *@Author XJP
     *@Date 2020/4/27
     *@Time 9:14
    **/
    int updateByIsDelete(Long id);
}