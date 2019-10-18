package com.ahsj.wxapplet.services;

import com.ahsj.wxapplet.entity.SysCode;
import com.ahsj.wxapplet.entity.SysCodeDetail;
import com.ahsj.wxapplet.entity.TreeEntity;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface CodeService {

    /**
     * @return core.message.Message
     * @Description 查询字典表树
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 18:32
     **/
    public List<TreeEntity> selectTreeCode(Integer id) throws Exception;


    /**
     * @return core.message.Message
     * @Description 查询
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 18:32
     **/
    public PageBean<SysCodeDetail> list(PageBean<SysCodeDetail> pageEntity) throws Exception;

    /**
     * @return core.message.Message
     * @Description 更新初始化
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 18:32
     **/
    public SysCode updateInit(Long id) throws Exception;


    /**
     * @return core.message.Message
     * @Description 新增或者更新
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 18:32
     **/
    public Message saveOrUpdate(SysCode sysCode) throws Exception;


    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 18:32
     **/
    public Message delete(Long[] id) throws Exception;


    /**
     * @return core.message.Message
     * @Description 更新初始化
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 18:32
     **/
    public SysCodeDetail updateInitDetail(Long id) throws Exception;

    /**
     * @return core.message.Message
     * @Description 新增或者更新
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 18:32
     **/
    public Message saveOrUpdateDetail(SysCodeDetail sysCodeDetail) throws Exception;


    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 18:32
     **/
    public Message deleteDetail(Long[] id) throws Exception;


    /**
     * @return core.message.Message
     * @Description list
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 18:32
     **/
    public List<SysCodeDetail> lstSysCodeDetail() throws Exception;

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.SysCodeDetail>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/7/29
     * @Time 17:08
     **/
    SysCodeDetail SysCodeDetail(SysCodeDetail sysCodeDetail);

    /**
     * @return java.util.List<HisMedicineInfo>
     * @功能说明
     * @Params [hisMedicineInfo]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:35
     **/
    List<SysCodeDetail> queryTranslateInfoByDates(SysCodeDetail sysCodeDetail) throws Exception;

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.SysCode>
     * @功能说明
     * @Params [sysCode]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:50
     **/
    List<SysCode> queryTranslateInfoByDate(SysCode sysCode) throws Exception;

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.SysCode>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 14:49
     **/
    List<SysCode> getSysCodeAll() throws Exception;

    /**
     * @return com.ahsj.userinfor.entity.SysCodeDetail
     * @功能说明
     * @Params [sysCodeDetail]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 15:02
     **/
    SysCodeDetail getSysCodeName(SysCodeDetail sysCodeDetail);

    /**
     * @param sysCodeDetail
     * @Description 根据字段，值查询是否存在
     * @Author: dingli
     * @return: com.ahsj.userinfor.entity.SysCodeDetail
     * @Date 2019/8/30
     * @Time 16:13
     **/
    SysCodeDetail selectSysCode(SysCodeDetail sysCodeDetail);
}
