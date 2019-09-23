package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisProject;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

/**
 * @program: his
 * @description:项目服务
 * @author: chenzhicai
 * @create: 2019-07-04-15-51
 **/
public interface HisProjectSerivce {

    /**
     * @return
     * @Description 新增或更新
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-18
     * @Time 14:59
     **/
    Message saveOrUpdate(HisProject his) throws Exception;

    /**
     * @return
     * @Description 删除
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-18
     * @Time 14:59
     **/
    Message delete(Long[] ids) throws Exception;

    /**
     * @return
     * @Description list
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-18
     * @Time 14:59
     **/
    PageBean<HisProject> list(PageBean<HisProject> pageBean) throws Exception;

    /**
     * @return
     * @Description 初始化
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-18
     * @Time 14:59
     **/
    HisProject updateInit(Long id) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisRegisteredType
     * @Description 查询
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019/6/21
     * @Time 4:08 PM
     **/
    HisProject selectById(Long id);

}
