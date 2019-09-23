package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface HisApplicationForDrugReturnDetailsService {
    /**
     * @return core.entity.PageBean<MedicinePurchasingPlan>
     * @Description 药品退回申请详细信息
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 17:04
     **/
    PageBean<HisApplicationForDrugReturnDetails> listForVoucher(PageBean<HisApplicationForDrugReturnDetails> pageBean) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisPharmacyDetail>
     * @Description 分页查询
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-06-22
     * @Time 21:22
     **/
    PageBean<HisApplicationForDrugReturnDetails> list(PageBean<HisApplicationForDrugReturnDetails> pageBean) throws Exception;

    /**
     * @return core.message.Message
     * @Description （批量）新增
     * @Params [hisPharmacyDetail]
     * @Author zhushixiang
     * @Date 2019-06-25
     * @Time 10:23
     **/
    Message saveOrUpdate(List<HisApplicationForDrugReturnDetails> hisApplicationForDrugReturnDetailsList) throws Exception;

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [ids]
     * @Author zhushixiang
     * @Date 2019-06-25
     * @Time 11:31
     **/
    Message delete(Long[] ids) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails
     * @Description 根据voucher查找
     * @Params [voucher]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 10:10
     **/
    List<HisApplicationForDrugReturnDetails> selectByVoucher(String voucher);

    /**
     * @return core.message.Message
     * @Description 药品退回申请详细编辑后提交（更改多条药品退回记录）
     * @Params [ids, numbers, validityPeriods, voucher]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 10:33
     **/
    Message saveForMultiple(Long[] ids, Long[] numbers, String voucher) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     * @Description 根据退药凭证查找，为适应自定义表格把  用药明细与出药关联表ID 起别名为ID
     * @Params [voucher]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 17:58
     **/
    List<HisApplicationForDrugReturnDetails> selectByVoucherForReturnDrug(String voucher);

    /**
     * @return core.message.Message
     * @Description 审核通过
     * @Params [voucher]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 17:59
     **/
    Message reviewSuccess(String voucher) throws Exception;

    /**
     * @return core.message.Message
     * @Description 审核驳回
     * @Params [voucher]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 21:56
     **/
    Message reviewFail(String voucher) throws Exception;

    /**
     * @return void
     * @Description 根据voucher删除所有与其关联的退药详细申请
     * @Params [voucher]
     * @Author zhushixiang
     * @Date 2019-07-12
     * @Time 10:43
     **/
    void deleteByVoucher(String voucher) throws Exception;

    /**
     * @Description 根据交易流水号 拿到所有的退药详情
     * @Param pageBean:
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     * @Date 2019/7/25
     * @Time 13:06
     **/
    PageBean<HisApplicationForDrugReturnDetails> byNumber(PageBean<HisApplicationForDrugReturnDetails> pageBean) throws Exception;

    /**
     * @Description 根据交易流水号查看退费信息
     * @Param pageBean:
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     * @Date 2019/8/1
     * @Time 13:46
     **/
    PageBean<HisApplicationForDrugReturnDetails> ByRecordNumber(PageBean<HisApplicationForDrugReturnDetails> pageBean) throws Exception;

    /**
     * @param recordNumber
     * @Description
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     * @Date 2019/8/19
     * @Time 9:48
     **/
    List<HisApplicationForDrugReturnDetails> selectByRecordNumber(String recordNumber) throws Exception;

    /**
     * @param pageBean
     * @Description 根据就诊编号查出退药明细
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     * @Date 2019/8/20
     * @Time 9:41
     **/
    PageBean<HisApplicationForDrugReturnDetails> byByRecordNumber(PageBean<HisApplicationForDrugReturnDetails> pageBean) throws Exception;
}
