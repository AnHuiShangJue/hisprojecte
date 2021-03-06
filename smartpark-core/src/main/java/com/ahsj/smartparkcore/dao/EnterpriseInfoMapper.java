package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.vo.EnterpriseInfoVO;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EnterpriseInfoMapper extends BaseMapper<EnterpriseInfo> {

    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseInfo enterpriseInfo);

    int insertSelective(EnterpriseInfo enterpriseInfo);

    EnterpriseInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EnterpriseInfo enterpriseInfo);

    int updateByPrimaryKey(EnterpriseInfo enterpriseInfo);

    /**
     * @return com.ahsj.smartparkcore.entity.EnterpriseInfo
     * @功能说明
     * @Params [enterpriseInfo]
     * @Author XJP
     * @Date 2019/9/2
     * @Time 16:28
     **/
    EnterpriseInfo queryEnterpriseInfo(EnterpriseInfo enterpriseInfo);

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.EnterpriseInfo>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/9/2
     * @Time 16:50
     **/
    List<EnterpriseInfo> queryList(PageBean<EnterpriseInfo> pageBean);

    /**
     * @return com.ahsj.smartparkcore.entity.vo.EnterpriseInfoVO
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/10/14
     * @Time 14:05
     **/
    EnterpriseInfo selectById(Long id);

    int updateByCompanyId(EnterpriseInfo enterpriseInfo);

    List<EnterpriseInfo> enterpriseInfoAll();

    List<EnterpriseInfo> queryListAll(EnterpriseInfo enterpriseInfo);
}