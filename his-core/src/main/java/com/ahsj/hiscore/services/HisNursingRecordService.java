package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisNursingRecord;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisNursingRecordService {

    /**
     *@Description list
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/29
     *@Time 17:20
    */
    PageBean<HisNursingRecord> listByManageNumber(PageBean<HisNursingRecord> pageBean,String manageNumber)throws Exception;


    /**
     *@Description saveOrUpdate
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/29
     *@Time 17:23
    */
    Message saveOrUpdate(HisNursingRecord hisNursingRecord) throws Exception;

    /**
     *@Description
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/30
     *@Time 10:22
    */
    HisNursingRecord selectByPrimaryId(Long id) throws  Exception;

    /**
     *@Description delete
     *@Params
     *@return
     *@Author jin
     *@Date 2019/7/30
     *@Time 14:59
    */
    Message delete(Long[] ids) throws Exception;

/**
     *@Description 根据条件做温度折线图
      * @Param hisNursingRecord:
     *@Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisNursingRecord>
     *@Date 2019/8/7
     *@Time 10:12
    **/
    List<HisNursingRecord> list(HisNursingRecord hisNursingRecord)throws Exception;

}
