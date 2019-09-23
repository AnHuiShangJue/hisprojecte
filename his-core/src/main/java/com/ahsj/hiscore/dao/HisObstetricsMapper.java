package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisObstetrics;

public interface HisObstetricsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisObstetrics record);

    int insertSelective(HisObstetrics record);

    HisObstetrics selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisObstetrics record);

    int updateByPrimaryKey(HisObstetrics record);
    /**
     * @Description 根据住院编号查询
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisObstetrics
     * @Date 2019/9/16
     * @Time 15:03
     **/
    HisObstetrics selectByNumber(String number) throws Exception;

    /**
     * @Description 根据住院id查询是否存在
     * @Params: [hisHospitalManageId]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisObstetrics
     * @Date 2019/9/16
     * @Time 16:40
     **/
    HisObstetrics selectByhisHospitalManageId(Long hisHospitalManageId) throws Exception;
}