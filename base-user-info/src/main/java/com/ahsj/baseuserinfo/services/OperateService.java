package com.ahsj.baseuserinfo.services;

import com.ahsj.baseuserinfo.entity.Operate;
import core.entity.PageBean;
import core.message.Message;

public interface OperateService {
    /**
     *@Description 查询
     *@Params [id]
     *@return com.ahsj.userinfor.entity.Operate
     *@Author chenzhicai
     *@Date 2019-04-09
     *@Time 14:51
    **/
    public PageBean<Operate> list(PageBean<Operate> pageEntity) throws Exception;


   /**
    *@Description 更新初始化
    *@Params [sysOperate]
    *@return core.message.Message
    *@Author chenzhicai
    *@Date 2019-04-09
    *@Time 14:51
   **/
    public Operate updateInit(Long id) throws Exception;

   /**
    *@Description 保存或者更新
    *@Params [id]
    *@return core.message.Message
    *@Author chenzhicai
    *@Date 2019-04-09
    *@Time 14:51
   **/
    public Message saveOrUpdate(Operate sysOperate) throws Exception;

    /**
     *@Description 删除
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-04-09
     *@Time 14:51
    **/
    public Message delete(Long[] id) throws Exception;
}
