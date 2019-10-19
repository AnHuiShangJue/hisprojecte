package com.ahsj.wxapplet.dao;


import com.ahsj.wxapplet.entity.SysCodeDetail;
import com.ahsj.wxapplet.entity.TreeEntity;
import core.entity.PageBean;

import java.util.List;

public interface SysCodeDetailMapper extends BaseMapper<SysCodeDetail> {


    List<TreeEntity> selectTreeCode(Integer id);

    SysCodeDetail selectByCode(SysCodeDetail sysCodeDetail);

    SysCodeDetail selectByValue(SysCodeDetail sysCodeDetail);

    List<SysCodeDetail> lstAllSysCodeDetail();

    List<SysCodeDetail> list(PageBean<SysCodeDetail> pageEntity);

    void update(SysCodeDetail sysCodeDetail);

    int deleteByPrimaryKey(Long id);

    int insert(SysCodeDetail sysCodeDetail);

    int insertSelective(SysCodeDetail sysCodeDetail);

    SysCodeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysCodeDetail sysCodeDetail);

    int updateByPrimaryKey(SysCodeDetail sysCodeDetail);

    SysCodeDetail SysCodeDetail(SysCodeDetail sysCodeDetail);

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.SysCodeDetail>
     * @功能说明
     * @Params [sysCodeDetail]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:40
     **/
    List<SysCodeDetail> queryTranslateInfoByDate(SysCodeDetail sysCodeDetail);

    /**
     * @return com.ahsj.userinfor.entity.SysCodeDetail
     * @功能说明
     * @Params [sysCodeDetail]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 15:03
     **/
    SysCodeDetail getSysCodeName(SysCodeDetail sysCodeDetail);

    /**
     * @param sysCodeDetail
     * @Description
     * @Author: dingli
     * @return: com.ahsj.userinfor.entity.SysCodeDetail
     * @Date 2019/8/30
     * @Time 15:59
     **/
    SysCodeDetail selectSysCode(SysCodeDetail sysCodeDetail);
}