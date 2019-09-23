package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisStageSummary;
import com.ahsj.hiscore.entity.HisSuccession;
import core.entity.PageBean;
import core.message.Message;

/**
 * @program: hisprojecte
 * @description:
 * @author: chenzhicai
 * @create: 2019-09-17-09-14
 **/
public interface HisStageSummaryService {
    /**
     * @return core.message.Message
     * @Description 保存
     * @Params [hisHandover]
     * @Author chenzhicai
     * @Date 2019-09-15
     * @Time 13:21
     **/
    Message saveOrUpdate(HisStageSummary hisStageSummary) throws Exception;



    /**
     * @return com.ahsj.hiscore.entity.HisRegistered
     * @Description 根据id查询
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019/6/21
     * @Time 11:49 PM
     **/
    HisStageSummary selectById(Long id);


    /**
     *@Description 根据住院编号查找
     *@Params [number]
     *@return com.ahsj.hiscore.entity.HisSuccession
     *@Author chenzhicai
     *@Date 2019-09-16
     *@Time 12:35
     **/
    HisStageSummary selectByHosNumber(String number);
}
