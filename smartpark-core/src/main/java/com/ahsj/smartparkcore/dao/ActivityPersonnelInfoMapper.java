package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.po.ActivityPersonnelInfo;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityPersonnelInfoMapper extends BaseMapper<ActivityPersonnelInfo>{
    int deleteByPrimaryKey(Long id);

    int insert(ActivityPersonnelInfo record);

    int insertSelective(ActivityPersonnelInfo record);

    ActivityPersonnelInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityPersonnelInfo record);

    int updateByPrimaryKey(ActivityPersonnelInfo record);

    List<? extends ActivityPersonnelInfo> list(PageBean<ActivityPersonnelInfo> activityInfoPageBean);

}