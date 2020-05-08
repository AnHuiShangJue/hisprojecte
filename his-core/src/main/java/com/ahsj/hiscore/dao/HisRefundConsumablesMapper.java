package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisRecordConsumables;
import com.ahsj.hiscore.entity.HisRefundConsumables;
import com.ahsj.hiscore.entity.HisRefundProject;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface HisRefundConsumablesMapper extends BaseMapper<HisRefundConsumables> {

    int deleteByPrimaryKey(Long id);

    int insert(HisRefundConsumables hisRefundConsumables);

    int insertSelective(HisRefundConsumables hisRefundConsumables);

    HisRefundConsumables selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisRefundConsumables hisRefundConsumables);

    int updateByPrimaryKey(HisRefundConsumables hisRefundConsumables);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisRefundConsumables>
     * @Description
     * @MethodName list
     * @Params [pageBean]
     * @Author XJP
     * @Date 2020/4/28
     * @Time 10:36
     **/
    List<HisRefundConsumables> list(PageBean<HisRefundConsumables> pageBean);

    /**
     * @return int
     * @Description
     * @MethodName insertList
     * @Params [consumablesList]
     * @Author XJP
     * @Date 2020/4/29
     * @Time 13:52
     **/
    int insertList(List<HisRefundConsumables> consumablesList);
/**
 *@Description
 *@MethodName queryHisHisRefundConsumablesVoucher
 *@Params [hisRefundConsumables]
 *@return java.util.List<com.ahsj.hiscore.entity.HisRefundConsumables>
 *@Author XJP
 *@Date 2020/4/29
 *@Time 16:21
**/
    List<HisRefundConsumables> queryHisHisRefundConsumablesVoucher(HisRefundConsumables hisRefundConsumables);


    /**
     *@Description
     *@MethodName queryHisHisRefundConsumablesVoucher
     *@Params [hisRefundConsumables]
     *@return java.util.List<com.ahsj.hiscore.entity.HisRefundConsumables>
     *@Author XJP
     *@Date 2020/4/30
     *@Time 14:32
    **/
    List<HisRefundConsumables> queryHisHisRefundConsumablesVoucherList(HisRefundConsumables hisRefundConsumables);

    /**
     *@Description
     *@MethodName updateByIsDelete
     *@Params [hisRefundConsumablesList]
     *@return int
     *@Author XJP
     *@Date 2020/4/29
     *@Time 16:35
    **/
    int updateByIsDelete(List<HisRefundConsumables> hisRefundConsumablesList);
    
    /**
     *@Description 
     *@MethodName updateByVoucherIsDelete
     *@Params [hisRefundConsumables]
     *@return int
     *@Author XJP
     *@Date 2020/5/8
     *@Time 16:55
    **/
    int updateByVoucherIsDelete(HisRefundConsumables hisRefundConsumables);

    /**
     *@Description
     *@MethodName queryByNotBack
     *@Params [recordNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.HisRefundConsumables>
     *@Author XJP
     *@Date 2020/4/30
     *@Time 12:41
    **/
    List<HisRefundConsumables> queryByNotBack(String recordNumber);

    /**
     *@Description
     *@MethodName isDelete
     *@Params [hisRefundConsumables]
     *@return int
     *@Author XJP
     *@Date 2020/5/8
     *@Time 11:02
    **/
    int isDelete(@Param("vouchers") String[] vouchers);
}