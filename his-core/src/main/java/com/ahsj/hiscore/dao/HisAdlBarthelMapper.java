package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisAdlBarthel;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisAdlBarthelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisAdlBarthel record);

    int insertSelective(HisAdlBarthel record);

    HisAdlBarthel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisAdlBarthel record);

    int updateByPrimaryKey(HisAdlBarthel record);

    /**
     *@Description list
     *@Params
     *@return
     *@Author jin
     *@Date 2019/9/19
     *@Time 17:53
    */
    List<HisAdlBarthel> list(PageBean<HisAdlBarthel> pageBean);



}