package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisAnkleDetail;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisAnkleDetailMapper extends BaseMapper<HisAnkleDetail>{
    int deleteByPrimaryKey(Long id);

    int deleteByNumber(String number);

    int insert(HisAnkleDetail record);

    int insertSelective(HisAnkleDetail record);

    HisAnkleDetail selectByPrimaryKey(Long id);

    HisAnkleDetail selectByNumber(String number);

    int updateByPrimaryKeySelective(HisAnkleDetail record);

    int updateByPrimaryKey(HisAnkleDetail record);

    List<? extends HisAnkleDetail> listByNumber(PageBean<HisAnkleDetail> pageBean);

    List<HisAnkleDetail> selectListByNumber(String number);

    HisAnkleDetail selectWithNumber(String number);

    void updateBatch(List<HisAnkleDetail> hisAnkleDetailList);

}