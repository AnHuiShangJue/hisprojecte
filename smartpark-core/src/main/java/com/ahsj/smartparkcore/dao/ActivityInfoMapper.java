package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.po.ActivityInfo;
import com.ahsj.smartparkcore.entity.vo.ActivityInfoVO;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityInfoMapper extends BaseMapper<ActivityInfo> {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityInfo record);

    int insertSelective(ActivityInfo record);

    ActivityInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityInfo record);

    int updateByPrimaryKey(ActivityInfo record);

    List<? extends ActivityInfo> list(PageBean<ActivityInfo> activityInfoPageBean);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.vo.ActivityInfoVO>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/30
     * @Time 14:09
     **/
    List<ActivityInfoVO> listForView();
}