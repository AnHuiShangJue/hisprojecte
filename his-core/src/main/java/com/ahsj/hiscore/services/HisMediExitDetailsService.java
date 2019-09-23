package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisMediExitDetails;
import com.ahsj.hiscore.entity.HisMedicationDetails;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisMediExitDetailsService {
    /**
     * @return core.message.Message
     * @Description 药品出库
     * @Params [hisMediExitDetails]
     * @Author zhushixiang
     * @Date 2019-06-26
     * @Time 12:01
     **/
    Message mediExit(HisMediExitDetails hisMediExitDetails) throws Exception;

    /**
     * @return core.message.Message
     * @Description 药品出库按钮
     * @Params [ids, numbers, prices]
     * @Author zhushixiang
     * @Date 2019-07-06
     * @Time 21:10
     **/
    Message medicineExit(Long[] ids, Long[] numbers, Double[] prices) throws Exception;

    /**
     * @return core.message.Message
     * @Description 智能出库
     * @Params [ids, numbers]
     * @Author zhushixiang
     * @Date 2019-07-08
     * @Time 17:37
     **/
    Message IntelligenceExit(Long[] ids, Integer[] numbers) throws Exception;

    /**
     * @return core.message.Message
     * @Description 新增或更新
     * @Params [hisNurse]
     * @Author zhushixiang
     * @Date 2019-06-18
     * @Time 21:43
     **/
    Message saveOrUpdate(HisMediExitDetails hisMediExitDetails) throws Exception;

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [ids]
     * @Author zhushixiang
     * @Date 2019-06-18
     * @Time 21:43
     **/
    Message delete(Long[] ids) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisNurse>
     * @Description list查询
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-06-18
     * @Time 21:43
     **/
    PageBean<HisMediExitDetails> list(PageBean<HisMediExitDetails> pageBean) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisNurse
     * @Description 初始化
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-06-18
     * @Time 21:43
     **/
    HisMediExitDetails updateInit(Long id) throws Exception;

    /**
     * @return core.message.Message
     * @Description 批量新增
     * @Params [hisMediExitDetails]
     * @Author zhushixiang
     * @Date 2019-07-12
     * @Time 11:09
     **/
    Message saveForMultiple(List<HisMediExitDetails> hisMediExitDetailsList) throws Exception;

    /**
     * @return core.message.Message
     * @Description 根据就诊编号/住院编号/交易流水号药品出库
     * @Params [number]
     * @Author zhushixiang
     * @Date 2019-07-23
     * @Time 16:39
     **/
    Message mediExitByNumber(List<HisMedicationDetails> hisMedicationDetailsList) throws Exception;

    /**
     * @param ids
     * @Description 查询出药明细
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisMediExitDetails>
     * @Date 2019/8/17
     * @Time 13:17
     **/
    List<HisMediExitDetails> listByIds(Long[] ids) throws Exception;

    /**
     * @Description 查询历史出药
     * @Params: [ids]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisMediExitDetails>
     * @Date 2019/9/16
     * @Time 9:49
     **/
    List<HisMediExitDetails> listByIdsHistory(Long[] ids) throws Exception;
}
