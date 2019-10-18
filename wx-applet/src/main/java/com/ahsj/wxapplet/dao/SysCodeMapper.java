package com.ahsj.wxapplet.dao;


import com.ahsj.wxapplet.entity.SysCode;
import com.ahsj.wxapplet.entity.TreeEntity;

import java.util.List;

public interface SysCodeMapper extends BaseMapper<SysCode> {


    List<TreeEntity> selectTreeCode(Integer id);

    SysCode selectByType(String type);

    SysCode selectByName(String name);

    void update(SysCode sysCode);

    int deleteByPrimaryKey(Long id);

    int insert(SysCode sysCode);

    int insertSelective(SysCode sysCode);

    SysCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysCode sysCode);

    int updateByPrimaryKey(SysCode sysCode);

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.SysCode>
     * @功能说明
     * @Params [sysCode]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:39
     **/
    List<SysCode> queryTranslateInfoByDate(SysCode sysCode);

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.SysCode>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:51
     **/
    List<SysCode> getSysCodeAll() throws Exception;
}