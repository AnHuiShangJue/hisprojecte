package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisVitalSigns;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisVitalSignsMapper extends BaseMapper<HisVitalSigns>{
    int deleteByPrimaryKey(Long id);

    int insert(HisVitalSigns record);

    int insertSelective(HisVitalSigns record);

    HisVitalSigns selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisVitalSigns record);

    int updateByPrimaryKey(HisVitalSigns record);

    /**
     * @Description list
     * @Author  muxu
     * @Date  2019/9/19
     * @Time 10:14
     * @Return
     * @Params
    **/
    List<HisVitalSigns> list (PageBean<HisVitalSigns> pageBean);
}