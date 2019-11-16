package com.ahsj.wis.service;

import com.ahsj.wis.entity.Smartpark;
import core.message.Message;

import java.util.List;

public interface SmartparkService {


    /**
     * @Description 获取所有人工智能实验室信息
     * @Author  muxu
     * @Date  2019/11/15
     * @Time 15:26
     * @Return
     * @Params
     **/

    List<Smartpark> selectAll() throws Exception;


    /**
     * @Description 更新人工智能实验室信息
     * @Author  muxu
     * @Date  2019/11/15
     * @Time 15:36
     * @Return
     * @Params
     **/

    Message updateSmartpark(Smartpark record);
}
