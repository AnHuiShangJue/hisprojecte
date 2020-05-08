package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisRefundConsumables;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

/**
 * @ClassName : HisRefundConsumablesService
 * @Description :
 * @Author : XJP
 * @Date: 2020-04-28 10:30
 */
public interface HisRefundConsumablesService {

    /**
     *@Description
     *@MethodName list
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisRefundConsumables>
     *@Author XJP
     *@Date 2020/4/28
     *@Time 10:34
    **/
    PageBean<HisRefundConsumables> list(PageBean<HisRefundConsumables> pageBean) throws Exception;

    /**
     *@Description
     *@MethodName saveForAppEdit
     *@Params [hisRefundConsumables, nums, ids, userName]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/29
     *@Time 11:15
    **/
    Message saveForAppEdit(HisRefundConsumables hisRefundConsumables, Integer[] nums, Long[] ids, String userName)throws Exception;

    /**
     *@Description
     *@MethodName queryHisHisRefundConsumablesVoucher
     *@Params [hisRefundConsumables]
     *@return java.util.List<com.ahsj.hiscore.entity.HisRefundConsumables>
     *@Author XJP
     *@Date 2020/4/29
     *@Time 16:20
    **/
    List<HisRefundConsumables> queryHisHisRefundConsumablesVoucher(HisRefundConsumables hisRefundConsumables)throws Exception;

    /**
     *@Description
     *@MethodName reviewSuccess
     *@Params [hisRefundConsumables]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/29
     *@Time 16:28
    **/
    Message reviewSuccess(HisRefundConsumables hisRefundConsumables);

    /**
     *@Description
     *@MethodName reviewFail
     *@Params [hisRefundConsumables]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/29
     *@Time 16:30
    **/
    Message reviewFail(HisRefundConsumables hisRefundConsumables);

    /**
     *@Description 
     *@MethodName saveHisRefundConsumablesInfo
     *@Params [hisRefundConsumables]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/4/30
     *@Time 10:33
    **/
    Message saveHisRefundConsumablesInfo(HisRefundConsumables hisRefundConsumables);

    /**
     *@Description 
     *@MethodName queryByNotBack
     *@Params [recordNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.HisRefundConsumables>
     *@Author XJP
     *@Date 2020/4/30
     *@Time 12:39
    **/
    List<HisRefundConsumables> queryByNotBack(String recordNumber)throws Exception;

    /**
     *@Description
     *@MethodName updateByIsDelete
     *@Params [hisRefundConsumablesList]
     *@return int
     *@Author XJP
     *@Date 2020/4/29
     *@Time 16:35
     **/
    int updateByIsDelete(List<HisRefundConsumables> hisRefundConsumablesList) throws Exception;




    /**
     *@Description
     *@MethodName insertList
     *@Params [consumablesList]
     *@return int
     *@Author XJP
     *@Date 2020/4/30
     *@Time 12:48
    **/
    int insertList(List<HisRefundConsumables> consumablesList) throws Exception;

    /**
     *@Description
     *@MethodName delete
     *@Params [hisRefundConsumables]
     *@return core.message.Message
     *@Author XJP
     *@Date 2020/5/8
     *@Time 11:05
    **/
    Message delete( String[] vouchers);
}
