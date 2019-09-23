package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisApplicationForDrugReturn;
import core.entity.PageBean;
import core.message.Message;

public interface HisApplicationForDrugReturnService {

    /**
     * @return core.entity.PageBean<HisPharmacyDetail>
     * @Description 药品报损表的分页查询
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 12:07
     **/
    PageBean<HisApplicationForDrugReturn> list(PageBean<HisApplicationForDrugReturn> pageBean) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisApplicationForDrugReturn>
     * @Description 为药品审核的list查询 只查询出未审核的申请
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 17:32
     **/
    PageBean<HisApplicationForDrugReturn> listForReview(PageBean<HisApplicationForDrugReturn> pageBean) throws Exception;

    /**
     * @return core.message.Message
     * @Description 药品退回申请及其详细信息保存
     * @Params [ids, numbers, validityPeriods, patientName, phonenumber, idcard, prescriptionNumber, reason]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 15:14
     **/
    Message saveForApply(Long[] ids, Long[] numbers, String patientName, Long phonenumber, String idcard, String reason) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     * @Description
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 15:54
     **/
    HisApplicationForDrugReturn selectById(Long id);

    /**
     * @return core.message.Message
     * @Description 药品退回基本信息修改
     * @Params [hisApplicationForDrugReturn]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 16:16
     **/
    Message Update(HisApplicationForDrugReturn hisApplicationForDrugReturn) throws Exception;

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [ids]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 16:28
     **/
    Message delete(Long[] ids) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     * @Description 根据凭证查找
     * @Params [voucher]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 17:15
     **/
    HisApplicationForDrugReturn selectByVoucher(String voucher);

    /**
     * @return core.message.Message
     * @Description 新增或更新
     * @Params [hisApplicationForDrugReturnDetails]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 9:47
     **/
    Message saveOrUpdate(HisApplicationForDrugReturn hisApplicationForDrugReturnDetails) throws Exception;

    /**
     * @Description 根据交易流水号查询退药信息
     * @Param number:
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     * @Date 2019/7/25
     * @Time 10:12
     **/
    HisApplicationForDrugReturn getNumber(String number) throws Exception;

    /**
     * @Description 修改成已退款
     * @Param record:
     * @Author: dingli
     * @return: int
     * @Date 2019/7/25
     * @Time 15:26
     **/

    int updateByPrimaryKeySelective(HisApplicationForDrugReturn record) throws Exception;

    /**
     * @Description 根据就诊编号查询退药明细
     * @Param RecordNumber:
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     * @Date 2019/8/1
     * @Time 10:22
     **/
    HisApplicationForDrugReturn showDrugByRecordNumber(String recordNumber) throws Exception;

    /**
     * @param recordNumber
     * @Description 根据就诊编号查看退药明细
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     * @Date 2019/8/20
     * @Time 10:44
     **/
    HisApplicationForDrugReturn selectByRecordNumber(String recordNumber);
}
