package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisRegisteredType;
import core.entity.PageBean;
import core.message.Message;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: his
 * @description:HisRegisteredType
 * @author: chenzhicai
 * @create: 2019-07-01-17-34
 **/


public interface HisRegisteredTypeService {


    /**
     *@Description  新增或更新
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-18
     *@Time 14:59
     **/
    Message saveOrUpdate(HisRegisteredType his) throws Exception;
    /**
     *@Description  删除
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-18
     *@Time 14:59
     **/
    Message delete(Long[] ids)throws Exception;
    /**
     *@Description  list
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-18
     *@Time 14:59
     **/
    PageBean<HisRegisteredType> list(PageBean<HisRegisteredType> pageBean)throws Exception;

    /**
     *@Description  初始化
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-18
     *@Time 14:59
     **/
    HisRegisteredType updateInit(Long id)throws Exception;

    /**
     *@Description 查询
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisRegisteredType
     *@Author chenzhicai
     *@Date 2019/6/21
     *@Time 4:08 PM
     **/
    HisRegisteredType selectById(Long id);


    HisRegisteredType selectByRegisterId(Long id);


    /**
     *@Description  返回所有的挂号类型
     *@Params []
     *@return java.util.List<com.ahsj.hiscore.entity.HisRegisteredType>
     *@Author chenzhicai
     *@Date 2019/7/4
     *@Time 11:56 AM
    **/
    List<HisRegisteredType> listByAll() throws Exception;
}

    