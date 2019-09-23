package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisRescue;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisRescueMapper {

    int deleteByPrimaryKey(Long id);

    int insert(HisRescue hisRescue);

    int insertSelective(HisRescue hisRescue);

    HisRescue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisRescue hisRescue);

    int updateByPrimaryKey(HisRescue hisRescue);

    /**
     * @return java.lang.Object
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/9/15
     * @Time 11:00
     **/
    List<HisRescue> queryList(PageBean<HisRescue> pageBean);

    /**
     * @return com.ahsj.hiscore.entity.HisRescue
     * @功能说明
     * @Params [hisRescue]
     * @Author XJP
     * @Date 2019/9/15
     * @Time 14:11
     **/
    List<HisRescue> queryHisRescue(HisRescue hisRescue);
}