package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.po.ActivityPersonnelInfo;
import com.ahsj.smartparkcore.entity.vo.ActivityPersonnelInfoVO;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityPersonnelInfoMapper extends BaseMapper<ActivityPersonnelInfo> {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityPersonnelInfo record);

    int insertSelective(ActivityPersonnelInfo record);

    ActivityPersonnelInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityPersonnelInfo record);

    int updateByPrimaryKey(ActivityPersonnelInfo record);

    List<? extends ActivityPersonnelInfo> list(PageBean<ActivityPersonnelInfo> activityInfoPageBean);

    List<? extends ActivityPersonnelInfo> listMyActivity(PageBean<ActivityPersonnelInfo> activityInfoPageBean);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.vo.ActivityPersonnelInfoVO>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/30
     * @Time 13:48
     **/
    List<ActivityPersonnelInfoVO> listForView(ActivityPersonnelInfo activityPersonnelInfo);
}