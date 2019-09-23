package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisMediEnterDetails;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisMediEnterDetailsService {

    /**
     *@Description 药品入库（前版本）
     *@Params [hisMediEnterDetails]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-06-26
     *@Time 11:57
    **/

    void unitMdeiEnter(HisMediEnterDetails hisMediEnterDetails) throws Exception;

    /**
     *@Description 药品入库（后版本）
     *@Params [ids, numbers, prices, planNumber]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-05
     *@Time 14:16
    **/
    Message mediEnter(Long[] ids, Long[] numbers, Double[] prices,String planNumber,String[] validityPeriods)throws Exception;

    /**
     *@Description 根据药品编号从入库表中将所有此编号的药品查询出（根据有效期分好组）
     *@Params [pharmacyId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMediEnterDetails>
     *@Author zhushixiang
     *@Date 2019-07-06
     *@Time 16:11
    **/
    List<HisMediEnterDetails> selectByPharmacyId(Long pharmacyId);

    /**
     *@Description 分页查询
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMediEnterDetails>
     *@Author zhushixiang
     *@Date 2019-07-06
     *@Time 16:17
    **/
    PageBean<HisMediEnterDetails> list(PageBean<HisMediEnterDetails> pageBean)throws Exception;

    /**
     *@Description 通过药品id查询入库表中所有数据以pageBean分页对象传回查到的数据
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMediEnterDetails>
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 10:22
    **/
    PageBean<HisMediEnterDetails> listByPharmacyId(PageBean<HisMediEnterDetails> pageBean)throws Exception;

    /**
     *@Description
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisMediEnterDetails
     *@Author zhushixiang
     *@Date 2019-07-10
     *@Time 20:17
    **/
    HisMediEnterDetails selectByPrimaryKey(Long id);

    /**
     *@Description 新增或更新
     *@Params [hisMediEnterDetails]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-10
     *@Time 20:40
    **/
    Message saveOrUpdate(HisMediEnterDetails hisMediEnterDetails)throws Exception;

    /**
     *@Description 批量新增
     *@Params [hisMediEnterDetails]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-12
     *@Time 11:08
    **/
    void saveForMultiple(List<HisMediEnterDetails> hisMediEnterDetailsList)throws Exception;

    /**
     *@Description 批量更新剩余入库量
     *@Params [hisMediEnterDetailsList]
     *@return void
     *@Author zhushixiang
     *@Date 2019-07-23
     *@Time 19:52
    **/
    void updateBatch(List<HisMediEnterDetails> hisMediEnterDetailsList);
}
