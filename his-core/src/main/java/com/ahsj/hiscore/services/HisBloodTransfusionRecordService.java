package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisBloodTransfusionRecord;
import core.entity.PageBean;
import core.message.Message;

public interface HisBloodTransfusionRecordService {
    /**
     *@Description 根据住院ID分页查询
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisBloodTransfusionRecord>
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 10:25
    **/
    PageBean<HisBloodTransfusionRecord> listByHospitalManageId(PageBean<HisBloodTransfusionRecord> pageBean)throws Exception;

    /**
     *@Description
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisBloodTransfusionRecord
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 11:13
    **/
    HisBloodTransfusionRecord selectById(Long id)throws Exception;

    /**
     *@Description 新增或更新
     *@Params [hisBloodTransfusionRecord]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 11:44
    **/
    Message saveOrUpdate(HisBloodTransfusionRecord hisBloodTransfusionRecord)throws Exception;

    /**
     *@Description 签字
     *@Params [id, loginUserId]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 15:08
    **/
    Message sign(Long id, Long loginUserId,String signName)throws Exception;

    /**
     *@Description 删除
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 15:53
    **/
    Message delete(Long[] ids)throws Exception;
}
