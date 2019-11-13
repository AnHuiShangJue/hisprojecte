package com.ahsj.wis.service;

import com.ahsj.wis.entity.WisdomIndex;
import core.message.Message;

import java.util.List;

/**
 * @Description
 * @Author dingli
 * @Date 2019/11/4
 * @Time 16:08
 **/
public interface WisdomIndexService {
    /**
     * className WisdomIndexService
     *
     * @author dingli
     * @date 2019/11/4 16:08
     */
    List<WisdomIndex> selectAll() throws Exception;

    /**
     * @Description 根据name修改value
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/11/4
     * @Time 16:43
     **/
    int updateByName(WisdomIndex record) throws Exception;

    /**
     * @Description 修改智慧湾首页
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/11/6
     * @Time 10:21
     **/
    Message updateByPrimaryKeySelective(WisdomIndex record);
}
