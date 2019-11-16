package com.ahsj.wis.service;

import com.ahsj.wis.entity.macroe;
import core.message.Message;

import java.util.List;

public interface macroeService {

    /**
     * @Description 获取所有人工智能实验室信息
     * @Author  muxu
     * @Date  2019/11/15
     * @Time 15:26
     * @Return
     * @Params
     **/

    List<macroe> selectAll() throws Exception;


    /**
     * @Description 更新人工智能实验室信息
     * @Author  muxu
     * @Date  2019/11/15
     * @Time 15:36
     * @Return
     * @Params
     **/

    Message updateMacroe(macroe record);
}
