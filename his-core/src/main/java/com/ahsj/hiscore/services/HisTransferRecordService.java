package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisTransferRecord;
import core.entity.PageBean;
import core.message.Message;

public interface HisTransferRecordService{
    
    
    /**
     * @Description list
     * @Author  muxu
     * @Date  2019/9/15
     * @Time 16:42
     * @Return core.entity.PageBean<com.ahsj.hiscore.entity.HisTransferRecord>
     * @Params [pageBean]
    **/
    PageBean<HisTransferRecord> list(PageBean<HisTransferRecord> pageBean) throws Exception;
    
    /**
     * @Description 转出记录保存
     * @Author  muxu
     * @Date  2019/9/15
     * @Time 16:42
     * @Return 
     * @Params 
    **/
    Message saveOrUpdate(HisTransferRecord hisTransferRecord) throws Exception;

    /**
     * @Description 入科记录保存
     * @Author  muxu
     * @Date  2019/9/16
     * @Time 10:37
     * @Return core.message.Message
     * @Params [hisTransferRecord]
    **/
    Message InSaveOrUpdate(HisTransferRecord hisTransferRecord) throws Exception;

    /**
     * @Description 删除
     * @Author  muxu
     * @Date  2019/9/15
     * @Time 16:42
     * @Return 
     * @Params 
    **/

    Message delete(Long[] ids) throws Exception;


    /**
     * @Description 医师签字
     * @Author  muxu
     * @Date  2019/9/16
     * @Time 11:39
     * @Return core.message.Message
     * @Params [ids]
    **/
    Message sign(HisTransferRecord hisTransferRecord) throws Exception;

    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/16
     * @Time 16:10
     * @Return
     * @Params
    **/

    HisTransferRecord selectById (Long id);


}
