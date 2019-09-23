package com.ahsj.hiscore.services;

import com.ahsj.hiscore.common.utils.ZipUtils;
import com.ahsj.hiscore.entity.HisDeath;
import core.message.Message;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/14
 * @Time 20:24
 **/
public interface HisDeathService {

    /**
     * @Description 查看死亡记录
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisDeath
     * @Date 2019/9/14
     * @Time 20:56
     **/
    HisDeath selectByNumber(String number) throws Exception;

    /**
     * @Description 添加死亡记录
     * @Params: [hisDeath]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/14
     * @Time 20:56
     **/

    Message insert(HisDeath hisDeath) throws Exception;

    /**
     * @Description 死亡记录确认
     * @Params: [hisDeath]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/14
     * @Time 20:56
     **/
    Message update(HisDeath hisDeath) throws Exception;

    /**
     *@Description 
     *@Params [targetId]
     *@return com.ahsj.hiscore.entity.HisDeath
     *@Author zhushixiang
     *@Date 2019-09-16
     *@Time 15:42
    **/
    HisDeath selectById(Long targetId)throws Exception;
}
