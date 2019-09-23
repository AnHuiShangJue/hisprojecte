package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.*;
import core.entity.PageBean;
import core.message.Message;

import java.util.Date;

public interface HisRegisteredService {
    /**
     *@Description  患者挂号
     *@Params
     *@return
     *@Author shenbuqing
     *@Date 2019/6/14
     *@Time 11:22
    **/
    Message registered(HisRegistered hisRegistered, HisPatientInfo hisPatientInfo,HisRegisteredpay hisRegisteredpay)throws Exception;

    /**
     *@Description  是否付费接口
     *@Params
     *@return
     *@Author shenbuqing
     *@Date 2019/6/14
     *@Time 12:15
    **/
    Message isPay(HisRegistered hisRegistered) throws Exception;

    /**
     *@Description  付费操作
     *@Params
     *@return
     *@Author shenbuqing
     *@Date 2019/6/14
     *@Time 12:26
    **/
    Message payOperation(HisRegisteredpay hisRegisteredpay)throws Exception;

    /**
     *@Description  报废操作
     *@Params
     *@return
     *@Author shenbuqing
     *@Date 2019/6/14       
     *@Time 18:00
    **/
    Message isObsolete(Long[] id);

    /**
     *@Description 打印操作
     *@Params [id]
     *@return core.message.Message
     *@Author chenzhicai
     *@Date 2019/6/21
     *@Time 11:49 PM
    **/
    Message print(Long id);

    /**
     *@Description list
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisRegistered>
     *@Author chenzhicai
     *@Date 2019/6/21
     *@Time 11:49 PM
    **/
    PageBean<HisRegistered> list(PageBean<HisRegistered> pageBean)throws Exception;

    /**
     *@Description 根据id查询
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisRegistered
     *@Author chenzhicai
     *@Date 2019/6/21
     *@Time 11:49 PM
    **/
    HisRegistered selectById(Long id);

    /**
     *@Description 计算今天的挂号
     *@Params [id]
     *@return com.ahsj.hiscore.entity.RegisterdCount
     *@Author chenzhicai
     *@Date 2019/6/21
     *@Time 11:50 PM
    **/
    RegisterdCount countForToday(Long id);

    /**
     *@Description 查看挂号明细
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisRegistered
     *@Author chenzhicai
     *@Date 2019/6/30
     *@Time 7:03 PM
    **/
    HisRegistered detailInit(Long id);

    HisRegistered selectByPatientId(Long id);


    /**
     * @Description 挂号数量统计
     * @Author   muxu
     * @Date 2019/7/28
     * @Time 17:09
     * @Return com.ahsj.hiscore.entity.RegisterdCount
     * @Params [id]
    **/
    Integer[] selectByReistDataCount(Date createDate)throws Exception;

    /**
     *@Description 根据挂号编号查找
     *@Params [number]
     *@return com.ahsj.hiscore.entity.HisRegistered
     *@Author zhushixiang
     *@Date 2019-09-09
     *@Time 17:35
    **/
    HisRegistered selectByNumber(String number)throws Exception;
}
