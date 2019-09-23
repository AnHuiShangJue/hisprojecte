package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisRegisteredpay;
import core.entity.PageBean;

import java.util.List;

public interface HisRegisteredpayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisRegisteredpay record);

    int insertSelective(HisRegisteredpay record);

    HisRegisteredpay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisRegisteredpay record);

    int updateByPrimaryKey(HisRegisteredpay record);

    HisRegisteredpay selectByRegisteredId(Long registeredId);
    /**
         *@Description 分页查询
           * @param HisRegisteredpay
         *@Author: dingli
         *@return
         *@Date 2019/7/20
         *@Time 9:31
        **/

    List<HisRegisteredpay> getAllHisRegisteredpay(PageBean<HisRegisteredpay> pageBean) throws Exception;
/**
     *@Description 根据挂号查看详细信息
       * @param null
     *@Author: dingli
     *@return
     *@Date 2019/7/20
     *@Time 18:57
    **/
    HisRegisteredpay getHisRegisteredpay(String number);
    /**
     *@Description 根据日期查询
     *@Params [createdate]
     *@return java.util.List<com.ahsj.hiscore.entity.HisRegisteredpay>
     *@Author chenzhicai
     *@Date 2019-07-20
     *@Time 16:28
     **/
    List<HisRegisteredpay> selectByDate();

    HisRegisteredpay getPrice(HisRegisteredpay hisRegisteredpay)throws Exception;
}