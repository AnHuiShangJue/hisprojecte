package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisDailyCourse;
import core.message.Message;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/17
 * @Time 13:31
 **/
public interface HisDailyCourseService {

    /**
     * @Description 日常病程新增或修改
     * @Params: [hisDailyCourse]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/17
     * @Time 13:39
     **/
    Message saveOrUpdate(HisDailyCourse hisDailyCourse) throws Exception;


    /**
     * @Description 日常病程签字
     * @Params: [hisDailyCourse]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/17
     * @Time 13:39
     **/
    Message sign(HisDailyCourse hisDailyCourse) throws Exception;

    /**
     * @Description 通过id查询日常病程信息
     * @Params: [id]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisDailyCourse
     * @Date 2019/9/19
     * @Time 15:55
     **/
    HisDailyCourse getDailyCourse(Long id) throws Exception;


}
