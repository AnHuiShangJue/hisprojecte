package com.ahsj.hiscore.services;


import com.ahsj.hiscore.entity.HisMedicationDetails;
import com.ahsj.hiscore.entity.HisPharmacyDetail;
import core.entity.PageBean;
import core.message.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


public interface HisPharmacyDetailService {

    /**
     *@Description 设置药品停用状态
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-06-29
     *@Time 14:04
     **/
    Message setDisable(Long[] ids)throws Exception;

    /**
     *@Description 分页查询
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisPharmacyDetail>
     *@Author zhushixiang
     *@Date 2019-06-22
     *@Time 21:22
    **/
    PageBean<HisPharmacyDetail> list(PageBean<HisPharmacyDetail> pageBean)throws Exception;

    /**
     *@Description 新增或更新
     *@Params [hisPharmacyDetail]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-06-25
     *@Time 10:23
    **/
    Message saveOrUpdate(HisPharmacyDetail hisPharmacyDetail) throws Exception;

    /**
     *@Description 删除
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-06-25
     *@Time 11:31
    **/
    Message delete(Long[] ids)throws Exception;

    /**
     *@Description
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisPharmacyDetail
     *@Author zhushixiang
     *@Date 2019-06-25
     *@Time 11:29
    **/
    HisPharmacyDetail selectById(Long id);



    /**
     *@Description 设置药品下架状态
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-06-29
     *@Time 14:04
    **/
    Message setObtained(Long[] ids)throws Exception;

    /**
     *@Description 查询出药库中所有库存小于预警值的药品
     *@Params []
     *@return java.util.List<com.ahsj.hiscore.entity.HisPharmacyDetail>
     *@Author zhushixiang
     *@Date 2019-07-08
     *@Time 14:50
    **/
    List<HisPharmacyDetail> selectForStock()throws Exception;


    /**
     *@Description 根据药品ID查找
     *@Params [pharmacyId]
     *@return com.ahsj.hiscore.entity.HisPharmacyDetail
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 21:20
    **/
    HisPharmacyDetail selectByPharmacyId(Long pharmacyId);
    /**
     *@Description 批量新增
     *@Params [hisPharmacyDetail]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-12
     *@Time 11:10
    **/
    void saveForMultiple(List<HisPharmacyDetail> hisPharmacyDetailList) throws Exception;



    /**
     *@Description 根据用药明细的id查询药房药品
     *@Params [detailsList]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPharmacyDetail>
     *@Author chenzhicai
     *@Date 2019-07-11
     *@Time 17:39
    **/
    List<HisPharmacyDetail> selectForTreatPlan(List<HisMedicationDetails> detailsList);


    /**
     *@Description 部分信息修改（如仅在出入库时候修改药品的价格，等少量信息其他不修改）
     *@Params []
     *@return void
     *@Author zhushixiang
     *@Date 2019-07-12
     *@Time 13:38
    **/
    void UpdateForPart(HisPharmacyDetail hisPharmacyDetail)throws Exception;

    /**
     *@Description 查询出对应就诊编号/住院编号/交易流水号的用药明细
     *@Params [medicationRecordId]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-22
     *@Time 20:47
     **/
    List<HisMedicationDetails> listForMedicationDetailsByNumber(String number) throws Exception;

    /**
     *@Description 批量更新价格与销售数目
     *@Params [hisPharmacyDetailList]
     *@return void
     *@Author zhushixiang
     *@Date 2019-07-23
     *@Time 19:51
    **/
    void updateBatch(List<HisPharmacyDetail> hisPharmacyDetailList);

    /**
     *@Description 分页查询为医生开药
     *@Params [pharmacyDetailPageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisPharmacyDetail>
     *@Author zhushixiang
     *@Date 2019-08-14
     *@Time 13:29
    **/
    PageBean<HisPharmacyDetail> listForMedication(PageBean<HisPharmacyDetail> pharmacyDetailPageBean);

    /**
     *@Description 导出excel
     *@Params [ids, request, response, session]
     *@return void
     *@Author zhushixiang
     *@Date 2019-08-28
     *@Time 17:22
    **/
    void exportExcels(Long[] ids, HttpServletRequest request, HttpServletResponse response, HttpSession session,String param)throws Exception;

    /**
     *@Description 药库信息excel导入
     *@Params [list]
     *@return void
     *@Author zhushixiang
     *@Date 2019-08-30
     *@Time 15:37
    **/
    Map importExcel(List<HisPharmacyDetail> list)throws Exception;

    HisPharmacyDetail selectByDrugsNameAndSpec(HisPharmacyDetail hisPharmacyDetail);

    /**
     *@Description 根据导入时传来的条件搜索出对应的药库药品信息
     *@Params [hisPharmacyDetail]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPharmacyDetail>
     *@Author zhushixiang
     *@Date 2019-09-02
     *@Time 16:34
    **/
    List<HisPharmacyDetail> selectByExportExcel(HisPharmacyDetail hisPharmacyDetail);

    /**
     *@Description
     *@Params [drugsNumb]
     *@return com.ahsj.hiscore.entity.HisPharmacyDetail
     *@Author zhushixiang
     *@Date 2019-09-03
     *@Time 10:34
    **/
    HisPharmacyDetail selectByDrugsNumb(String drugsNumb)throws Exception;

    /**
     *@Description 查询出未停用未下架的药品
     *@Params [pharmacyDetailPageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisPharmacyDetail>
     *@Author zhushixiang
     *@Date 2019-09-18
     *@Time 18:16
    **/
    PageBean<HisPharmacyDetail> listForIsDisableAndObtained(PageBean<HisPharmacyDetail> pharmacyDetailPageBean)throws Exception;

    /**
     *@Description 根据IDs查询药库药品信息
     *@Params [ids]
     *@return java.util.List<com.ahsj.hiscore.entity.HisPharmacyDetail>
     *@Author zhushixiang
     *@Date 2019-10-05
     *@Time 14:57
    **/
    List<HisPharmacyDetail> selectForListForMedicationByIds(Long[] ids)throws Exception;

    /**
     *@Description 为门诊用药增加输液单
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-10-05
     *@Time 23:47
    **/
    Message addCombinationMedicine(Long[] ids)throws Exception;
}
