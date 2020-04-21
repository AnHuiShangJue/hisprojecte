package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisTollDetails;
import core.entity.PageBean;
import core.message.Message;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author dingli
 * @Date 2019/7/17
 * @Time 17:52
 **/
public interface HisTollDetailsService {
    int insertSelective(HisTollDetails record) throws Exception;


    PageBean<HisTollDetails> listByMecordId(PageBean<HisTollDetails> pageBean);


    PageBean<HisTollDetails> nurselistByMecordId(PageBean<HisTollDetails> pageBean);

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisTollRecord>
     * @Description 门诊收费结算
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-20
     * @Time 23:40
     **/
    PageBean<HisTollDetails> listForOutpatientCharges(PageBean<HisTollDetails> pageBean) throws Exception;

    Message saveForHospi(List<HisTollDetails> hisTollDetails);

    PageBean<HisTollDetails> listByMecordIdForSave(PageBean<HisTollDetails> pageBean);

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     * @Description 根据就诊记录编号查到对应的门诊收费
     * @Params [medicalRecordId]
     * @Author zhushixiang
     * @Date 2019-07-21
     * @Time 21:33
     **/
    List<HisTollDetails> selectForOutpatientChargesByMedicalRecordId(String medicalRecordId) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisTollDetails>
     * @Description 查询门诊收费列表
     * @Params [hisTollDetailsPageBean]
     * @Author zhushixiang
     * @Date 2019-07-22
     * @Time 10:39
     **/
    PageBean<HisTollDetails> listForOutpatientByMecordId(PageBean<HisTollDetails> hisTollDetailsPageBean);

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisTollDetails>
     * @Description 查询门诊收费数据为保存至收费记录中
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-22
     * @Time 15:59
     **/
    PageBean<HisTollDetails> listByMecordIdForOutpatientSave(PageBean<HisTollDetails> pageBean);

    /**
     * @Description 根据 toll_record_id 查询明细
     * @Param pageBean:
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisTollDetails>
     * @Date 2019/7/24
     * @Time 16:46
     **/
    PageBean<HisTollDetails> listHisTollDetails(PageBean<HisTollDetails> pageBean) throws Exception;

    /**
     * @Description 插入明细
     * @Param record:
     * @Author: dingli
     * @return: int
     * @Date 2019/7/26
     * @Time 14:04
     **/
    int insert(HisTollDetails record) throws Exception;

    /**
     * @Description 根据交易流水号打印
     * @Param number:
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     * @Date 2019/8/3
     * @Time 16:38
     **/
    List<HisTollDetails> listByNumber(String number) throws Exception;

    /**
     * @return void
     * @Description 批量插入
     * @Params []
     * @Author zhushixiang
     * @Date 2019-09-11
     * @Time 11:27
     **/
    void insertBatch(List<HisTollDetails> hisTollDetailsList) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisTollDetails
     * @Description 根据交易流水号查询当次所付住院费用的明细
     * @Params [tollRecordNumber]
     * @Author zhushixiang
     * @Date 2019-09-12
     * @Time 16:58
     **/
    HisTollDetails selectByTollNumberForBedAmount(String tollRecordNumber) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisTollDetails>
     * @Description 根据公共编号 搜索出对应的消费明细
     * @Params [hisTollDetailsPageBean]
     * @Author zhushixiang
     * @Date 2019-09-13
     * @Time 16:33
     **/
    PageBean<HisTollDetails> listForcommonSwipeByCommonNumber(PageBean<HisTollDetails> hisTollDetailsPageBean) throws Exception;

    /**
     * @Description 出院结算
     * @Params: [number]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     * @Date 2019/9/26
     * @Time 18:49
     **/
    List<HisTollDetails> listByNumberLeave(String number) throws Exception;

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     * @Date 2019/9/26
     * @Time 20:21
     **/
   HisTollDetails listByNumberFor(String number) throws Exception;

    HisTollDetails printShowThere(String number) throws Exception;

    BigDecimal listByNumberForLeave(String number);
}
