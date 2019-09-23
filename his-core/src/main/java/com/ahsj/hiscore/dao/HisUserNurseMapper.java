package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisUserNurse;

public interface HisUserNurseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisUserNurse record);

    int insertSelective(HisUserNurse record);

    HisUserNurse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisUserNurse record);

    int updateByPrimaryKey(HisUserNurse record);

    /**
     * @Description 根据userId查询
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:40
     * @Return com.ahsj.hiscore.entity.HisUserNurse
     * @Params [id]
    **/
    HisUserNurse selectByUserId(Long id);
}