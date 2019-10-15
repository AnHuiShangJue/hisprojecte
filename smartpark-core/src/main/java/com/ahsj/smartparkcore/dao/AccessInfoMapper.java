package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.po.AccessInfo;
import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccessInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AccessInfo record);

    int insertSelective(AccessInfo record);

    AccessInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccessInfo record);

    int updateByPrimaryKey(AccessInfo record);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.AccessInfo>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 13:01
     **/
    List<AccessInfo> queryList(PageBean<AccessInfo> pageBean);


    AccessInfo queryAccessInfo(AccessInfo accessInfo);
}