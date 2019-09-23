package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisApplicationForDrugReturn;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisApplicationForDrugReturnMapper extends BaseMapper<HisApplicationForDrugReturn>{
    int deleteByPrimaryKey(Long id);

    int insert(HisApplicationForDrugReturn record);

    int insertSelective(HisApplicationForDrugReturn record);

    HisApplicationForDrugReturn selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisApplicationForDrugReturn record);

    int updateByPrimaryKey(HisApplicationForDrugReturn record);

    /**
     *@Description 药品报损表的分页查询
     *@Params [pageBean]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisApplicationForDrugReturn>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:26
    **/
    List<? extends HisApplicationForDrugReturn> list(PageBean<HisApplicationForDrugReturn> pageBean);

    /**
     *@Description 为药品审核的list查询 只查询出未审核的申请
     *@Params [pageBean]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisApplicationForDrugReturn>
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:26
    **/
    List<? extends HisApplicationForDrugReturn> listForReview(PageBean<HisApplicationForDrugReturn> pageBean);

    /**
     *@Description 根据凭证查找
     *@Params [voucher]
     *@return com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:27
    **/
    HisApplicationForDrugReturn selectByVoucher(String voucher);

    /**
     *@Description
     *@Params [number]
     *@return com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:27
    **/
    HisApplicationForDrugReturn getByNumber(String number);

    /**
     *@Description 查询今日已经生成的退药申请数目
     *@Params [createdate]
     *@return int
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:28
    **/
    int selectNumbCount(String createdate);

    /**
     *@Description
     *@Params [number]
     *@return int
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:28
    **/
    int count(String number);

    /**
     *@Description 根据就诊编号查询退药明细
     *@Params [recordNumber]
     *@return com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:28
    **/
    HisApplicationForDrugReturn showDrugByRecordNumber(String recordNumber);

    /**
     *@Description 根据就诊编号查看退药明细
     *@Params [recordNumber]
     *@return com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:28
    **/
    HisApplicationForDrugReturn selectByRecordNumber(String recordNumber);

    /**
     *@Description 根据就诊编号查看未审核的退药明细
     *@Params [recordNumber]
     *@return com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 9:28
    **/
    HisApplicationForDrugReturn selectByRecordNumberNotIsReview(String recordNumber);
}