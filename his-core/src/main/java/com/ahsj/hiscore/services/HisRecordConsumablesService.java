package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisRecordConsumables;
import com.ahsj.hiscore.entity.HisRefundConsumables;
import core.entity.PageBean;
import core.message.Message;

/**
 * @ClassName : HisRecordConsumablesService
 * @Description :
 * @Author : XJP
 * @Date: 2020-04-27 14:15
 */
public interface HisRecordConsumablesService  {

    /**
     *@Description
     *@MethodName insert
     *@Params [hisRecordConsumables]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/27
     *@Time 14:20
    **/
    Message insert(HisRecordConsumables hisRecordConsumables);

    /**
     *@Description
     *@MethodName queryAddList
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordConsumables>
     *@Author XJP
     *@Date 2020/4/28
     *@Time 17:05
    **/
    PageBean<HisRecordConsumables> queryAddList(PageBean<HisRecordConsumables> pageBean);

    /**
     *@Description
     *@MethodName selectByPrimaryKey
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisRefundConsumables
     *@Author XJP
     *@Date 2020/4/29
     *@Time 11:35
    **/
    HisRecordConsumables selectByPrimaryKey(Long id);

    /**
     *@Description
     *@MethodName queryByConsumablesCodeAndMedicalRecordNumber
     *@Params [hisRecordConsumables]
     *@return com.ahsj.hiscore.entity.HisRecordConsumables
     *@Author XJP
     *@Date 2020/4/29
     *@Time 11:33
    **/
    HisRecordConsumables queryByConsumablesCodeAndMedicalRecordNumber(HisRecordConsumables hisRecordConsumables);

    /**
     *@Description
     *@MethodName consumablesInfo
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordConsumables>
     *@Author XJP
     *@Date 2020/4/29
     *@Time 14:52
    **/
    PageBean<HisRecordConsumables> consumablesInfo(PageBean<HisRecordConsumables> pageBean)throws Exception;

    /**
     *@Description
     *@MethodName pageBeanList
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisRecordConsumables>
     *@Author XJP
     *@Date 2020/4/29
     *@Time 17:09
    **/
    PageBean<HisRecordConsumables> pageBeanList(PageBean<HisRecordConsumables> pageBean) throws Exception;
}
