package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Mapper
public interface HisApplicationForDrugReturnDetailsMapper extends BaseMapper<HisApplicationForDrugReturnDetails> {
    int deleteByPrimaryKey(Long id);

    int insert(HisApplicationForDrugReturnDetails record);

    int insertSelective(HisApplicationForDrugReturnDetails record);

    HisApplicationForDrugReturnDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisApplicationForDrugReturnDetails record);

    int updateByPrimaryKey(HisApplicationForDrugReturnDetails record);

    /**
     *@Description 根据退药凭证查找 返回的是List
     *@Params [voucher]
     *@return java.util.List<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:22
    **/
    List<HisApplicationForDrugReturnDetails> selectByVoucher(String voucher);

    /**
     *@Description 根据退药凭证查找，为适应自定义表格把  用药明细与出药关联表ID 起别名为ID
     *@Params [voucher]
     *@return java.util.List<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:23
    **/
    List<HisApplicationForDrugReturnDetails> selectByVoucherForReturnDrug(String voucher);

    /**
     *@Description 根据退药凭证查找 返回的是PageBean
     *@Params [pageBean]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:25
    **/
    List<? extends HisApplicationForDrugReturnDetails> listForVoucher(PageBean<HisApplicationForDrugReturnDetails> pageBean);

    /**
     *@Description 根据voucher删除所有与其关联的退药详细申请
     *@Params [voucher]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:25
    **/
    void deleteByVoucher(String voucher);

    /**
     *@Description 批量插入
     *@Params [hisApplicationForDrugReturnDetailsList]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:25
    **/
    void insertBatch(@NotNull List<HisApplicationForDrugReturnDetails> hisApplicationForDrugReturnDetailsList);

    /**
     *@Description  根据交易流水号 拿到所有的退药详情
     *@Params [pageBean]
     *@return java.util.List<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:25
    **/
    List<HisApplicationForDrugReturnDetails> byNumber(PageBean<HisApplicationForDrugReturnDetails> pageBean);

    /**
     *@Description 据交易流水号查看退费信息
     *@Params [pageBean]
     *@return java.util.List<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:25
    **/
    List<HisApplicationForDrugReturnDetails> ByRecordNumber(PageBean<HisApplicationForDrugReturnDetails> pageBean);

    /**
     *@Description
     *@Params [recordNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:25
    **/
    List<HisApplicationForDrugReturnDetails> selectByRecordNumber(String recordNumber );

    /**
     *@Description  根据就诊编号查出退药明细
     *@Params [pageBean]
     *@return java.util.List<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:25
    **/
    List<HisApplicationForDrugReturnDetails> byRecordNumber(PageBean<HisApplicationForDrugReturnDetails> pageBean);

}