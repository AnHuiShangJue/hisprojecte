package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisHandover;
import core.entity.PageBean;
import core.message.Message;

/**
 * @program: hisprojecte
 * @description:交班管理
 * @author: chenzhicai
 * @create: 2019-09-15-13-18
 **/
public interface HandoverService {

    /**
     * @return core.message.Message
     * @Description 保存
     * @Params [hisHandover]
     * @Author chenzhicai
     * @Date 2019-09-15
     * @Time 13:21
     **/
    Message saveOrUpdate(HisHandover hisHandover) throws Exception;

    /**
     * @return core.message.Message
     * @Description 查询
     * @Params [hisHandover]
     * @Author chenzhicai
     * @Date 2019-09-15
     * @Time 13:21
     **/
    PageBean<HisHandover> list(PageBean<HisHandover> pageBean) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisRegistered
     * @Description 根据id查询
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019/6/21
     * @Time 11:49 PM
     **/
    HisHandover selectById(Long id);


    /**
     *@Description 根据住院编号查找
     *@Params [number]
     *@return com.ahsj.hiscore.entity.HisHandover
     *@Author chenzhicai
     *@Date 2019-09-16
     *@Time 12:35
    **/
    HisHandover selectByHosNumber(String number);
}
