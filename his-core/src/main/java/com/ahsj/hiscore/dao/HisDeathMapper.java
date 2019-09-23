package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisDeath;

public interface HisDeathMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisDeath record);

    int insertSelective(HisDeath record);

    HisDeath selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisDeath record);

    int updateByPrimaryKey(HisDeath record);


    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisDeath
     * @Date 2019/9/15
     * @Time 11:17
     **/
    HisDeath selectByNumber(String number);
}