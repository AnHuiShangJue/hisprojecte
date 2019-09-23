package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.*;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisApplicationForDrugReturnDetailsService;
import com.ahsj.hiscore.services.HisApplicationForDrugReturnService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class HisApplicationForDrugReturnDetailsServicelmpl implements HisApplicationForDrugReturnDetailsService {
    @Autowired
    HisApplicationForDrugReturnDetailsMapper hisApplicationForDrugReturnDetailsMapper;

    @Autowired
    HisApplicationForDrugReturnService hisApplicationForDrugReturnService;

    @Autowired
    HisMedicationDetailsMapper hisMedicationDetailsMapper;

    @Autowired
    HisMediEnterDetailsMapper hisMediEnterDetailsMapper;

    @Autowired
    HisRelatedMedicationandexitMapper hisRelatedMedicationandexitMapper;

    @Autowired
    HisPharmacyDetailMapper hisPharmacyDetailMapper;

    @Autowired
    ITranslateService iTranslateService;


    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     * @Description 药品退回申请详细信息（根据凭证编号获得与此凭证编号下所有申请退回的药品信息）
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 17:04
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisApplicationForDrugReturnDetails> listForVoucher(PageBean<HisApplicationForDrugReturnDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisApplicationForDrugReturnDetailsMapper.listForVoucher(pageBean)));
        return pageBean;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     * @Description 分页查询
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 20:19
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisApplicationForDrugReturnDetails> list(PageBean<HisApplicationForDrugReturnDetails> pageBean) throws Exception {
        return null;
    }

    /**
     * @return core.message.Message
     * @Description 新增或修改
     * @Params [hisApplicationForDrugReturnDetails]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 20:20
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(List<HisApplicationForDrugReturnDetails> hisApplicationForDrugReturnDetailsList) throws Exception {
        hisApplicationForDrugReturnDetailsMapper.deleteByVoucher(hisApplicationForDrugReturnDetailsList.get(0).getVoucher());
        hisApplicationForDrugReturnDetailsMapper.insertBatch(hisApplicationForDrugReturnDetailsList);
        return MessageUtil.createMessage(true, "批量新增成功");
    }

    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id : ids) {
            hisApplicationForDrugReturnDetailsMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true, "删除成功!");
    }

    /**
     * @return com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails
     * @Description 根据voucher查找
     * @Params [voucher]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 10:10
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisApplicationForDrugReturnDetails> selectByVoucher(String voucher) {
        return CodeHelper.getInstance().setCodeValue(hisApplicationForDrugReturnDetailsMapper.selectByVoucher(voucher));
    }

    /**
     * @return core.message.Message
     * @Description 药品退回申请详细编辑后提交（更改多条药品退回记录）
     * @Params [ids, numbers, validityPeriods, voucher]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 10:33
     **/
    @Override
    @Transactional(readOnly = false)
    //此ids[]为就诊记录ID的数组集合，为适应自定义表格，具体请搜索selectByVoucherForReturnDrug查看对应sql语句
    public Message saveForMultiple(Long[] ids, Long[] numbers, String voucher) throws Exception {
        hisApplicationForDrugReturnDetailsMapper.deleteByVoucher(voucher);
        HisApplicationForDrugReturn hisApplicationForDrugReturn = hisApplicationForDrugReturnService.selectByVoucher(voucher);
        List<HisApplicationForDrugReturnDetails> hisApplicationForDrugReturnDetailsList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            HisRelatedMedicationandexit hisRelatedMedicationandexit = hisRelatedMedicationandexitMapper.selectByPrimaryKey(ids[i]);
            HisMedicationDetails hisMedicationDetails = hisMedicationDetailsMapper.selectByPrimaryKey(hisRelatedMedicationandexit.getMedicationDetailId());
            HisApplicationForDrugReturnDetails hisApplicationForDrugReturnDetails = new HisApplicationForDrugReturnDetails();
            hisApplicationForDrugReturnDetails.setVoucher(voucher);
            hisApplicationForDrugReturnDetails.setReturnCount(numbers[i].intValue());
            hisApplicationForDrugReturnDetails.setValidityPeriod(hisRelatedMedicationandexit.getValidityPeriod());
            hisApplicationForDrugReturnDetails.setDrugsNumb(hisMedicationDetails.getDrugsNumb());
            hisApplicationForDrugReturnDetails.setDrugsName(hisMedicationDetails.getDrugsName());
            hisApplicationForDrugReturnDetails.setDrugsSpec(hisMedicationDetails.getDrugsSpec());
            /*if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetails.getPlaceorigin()))
                hisApplicationForDrugReturnDetails.setPlaceorigin(hisMedicationDetails.getPlaceorigin());*/
            hisApplicationForDrugReturnDetails.setMedicationDetailId(hisMedicationDetails.getId());
            hisApplicationForDrugReturnDetails.setPrice(hisRelatedMedicationandexit.getPrice());
            hisApplicationForDrugReturnDetails.setTotalPrice(hisRelatedMedicationandexit.getPrice().multiply(BigDecimal.valueOf(numbers[i])));
            hisApplicationForDrugReturnDetails.setRelatedId(hisRelatedMedicationandexit.getId());
            hisApplicationForDrugReturnDetailsList.add(hisApplicationForDrugReturnDetails);
        }
        saveOrUpdate(hisApplicationForDrugReturnDetailsList);
        return MessageUtil.createMessage(true, "药品退回信息更新成功");
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     * @Description 根据voucher查找（将就诊记录ID 起别名为ID 为适应自定义表格selfTable）
     * @Params [voucher]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 14:50
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisApplicationForDrugReturnDetails> selectByVoucherForReturnDrug(String voucher) {
        return CodeHelper.getInstance().setCodeValue(hisApplicationForDrugReturnDetailsMapper.selectByVoucherForReturnDrug(voucher));
    }

    /**
     * @return core.message.Message
     * @Description 审核
     * @Params [voucher]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 18:00
     **/
    @Override
    @Transactional(readOnly = false)
    public Message reviewSuccess(String voucher) throws Exception {
        HisApplicationForDrugReturn hisApplicationForDrugReturn = hisApplicationForDrugReturnService.selectByVoucher(voucher);
        if (EmptyUtil.Companion.isNullOrEmpty(hisApplicationForDrugReturn))
            return MessageUtil.createMessage(false, "输入错误，无此申请");
        hisApplicationForDrugReturn.setIsReview(1);
        hisApplicationForDrugReturn.setIsPayed(2);//is_payed是否退款
        List<HisApplicationForDrugReturnDetails> hisApplicationForDrugReturnDetailsList = hisApplicationForDrugReturnDetailsMapper.selectByVoucher(voucher);
        List<HisPharmacyDetail> hisPharmacyDetailList = new ArrayList<>();
        List<HisMediEnterDetails> hisMediEnterDetailsList = new ArrayList<>();
        List<HisMedicationDetails> hisMedicationDetailsList = new ArrayList<>();
        for (int i = 0; i < hisApplicationForDrugReturnDetailsList.size(); i++) {
            HisMediEnterDetails h = new HisMediEnterDetails();
            h.setDrugsNumb(hisApplicationForDrugReturnDetailsList.get(i).getDrugsNumb());
            h.setValidityPeriod(hisApplicationForDrugReturnDetailsList.get(i).getValidityPeriod());
            h.setId(hisApplicationForDrugReturnDetailsList.get(i).getMediEnterId());
           HisMediEnterDetails enterDetails = hisMediEnterDetailsMapper.selectByPrimaryKey(h.getId());
            enterDetails.setSurplus(enterDetails.getSurplus() + hisApplicationForDrugReturnDetailsList.get(i).getReturnCount());
            hisMediEnterDetailsList.add(enterDetails);

            HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailMapper.selectByDrugsNumb(hisApplicationForDrugReturnDetailsList.get(i).getDrugsNumb());
            hisPharmacyDetail.setStock(hisPharmacyDetail.getStock() + hisApplicationForDrugReturnDetailsList.get(i).getReturnCount());
            hisPharmacyDetailList.add(hisPharmacyDetail);

            HisMedicationDetails hisMedicationDetails = hisMedicationDetailsMapper.selectByPrimaryKey(hisApplicationForDrugReturnDetailsList.get(i).getMedicationDetailId());
            hisMedicationDetails.setIsBack(1);
            hisMedicationDetailsList.add(hisMedicationDetails);
        }
        hisMediEnterDetailsMapper.updateBatch(hisMediEnterDetailsList);
        hisPharmacyDetailMapper.updateBatchForStock(hisPharmacyDetailList);
        hisMedicationDetailsMapper.updateBatchForIsBack(hisMedicationDetailsList);
        hisApplicationForDrugReturnService.saveOrUpdate(hisApplicationForDrugReturn);
        return MessageUtil.createMessage(true, "审核通过");
    }

    /**
     * @return core.message.Message
     * @Description 审核驳回
     * @Params [voucher]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 20:57
     **/
    @Override
    @Transactional(readOnly = false)
    public Message reviewFail(String voucher) throws Exception {
        HisApplicationForDrugReturn hisApplicationForDrugReturn = hisApplicationForDrugReturnService.selectByVoucher(voucher);
        if (EmptyUtil.Companion.isNullOrEmpty(hisApplicationForDrugReturn))
            return MessageUtil.createMessage(false, "输入错误，无此申请");
        hisApplicationForDrugReturn.setIsReview(2);
        hisApplicationForDrugReturn.setIsPayed(2);
        hisApplicationForDrugReturnService.saveOrUpdate(hisApplicationForDrugReturn);
        return MessageUtil.createMessage(true, "审核驳回成功");
    }

    /**
     * @return void
     * @Description 根据voucher删除所有
     * @Params [voucher]
     * @Author zhushixiang
     * @Date 2019-07-12
     * @Time 10:37
     **/
    @Override
    @Transactional(readOnly = false)
    public void deleteByVoucher(String voucher) throws Exception {
        hisApplicationForDrugReturnDetailsMapper.deleteByVoucher(voucher);
    }

    /**
     * @Description 根据交易流水号拿到所有的退药详情
     * @Param pageBean:
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     * @Date 2019/7/25
     * @Time 13:09
     **/
    @Transactional(readOnly = true)
    @Override
    public PageBean<HisApplicationForDrugReturnDetails> byNumber(PageBean<HisApplicationForDrugReturnDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisApplicationForDrugReturnDetailsMapper.byNumber(pageBean)));
        return pageBean;
    }

    /**
     * @param pageBean
     * @Description 分页查询退药明细
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     * @Date 2019/8/19
     * @Time 9:50
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisApplicationForDrugReturnDetails> ByRecordNumber(PageBean<HisApplicationForDrugReturnDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisApplicationForDrugReturnDetailsMapper.ByRecordNumber(pageBean)));
        return pageBean;
    }

    /**
     * @param recordNumber
     * @Description 根据就诊编号查询打印退药明细
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     * @Date 2019/8/19
     * @Time 9:49
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisApplicationForDrugReturnDetails> selectByRecordNumber(String recordNumber) throws Exception {
        List<HisApplicationForDrugReturnDetails> list = hisApplicationForDrugReturnDetailsMapper.selectByRecordNumber(recordNumber);
        for (HisApplicationForDrugReturnDetails h : list) {
            Translate translate = new Translate();
            translate.setTranslateId(h.getmId());//药品基本表ID
            translate.setTranslateType(Constants.TRANSLATE_HIS_MEDICINEINFO);
            List<Translate> translates = iTranslateService.queryTranslate(translate);
            if (!EmptyUtil.Companion.isNullOrEmpty(translates)) {
                translates.stream().filter(f -> f.getTranslateChina().equals(h.getDrugsName())).forEach(e -> h.setTdrugsName(e.getTranslateKhmer()));//赋翻译字段
            }
        }
        return list;
    }

    /**
     * @param pageBean
     * @Description  根据就诊编号查看退药明细
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails>
     * @Date 2019/8/20
     * @Time 9:42
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisApplicationForDrugReturnDetails> byByRecordNumber(PageBean<HisApplicationForDrugReturnDetails> pageBean) throws Exception {
        pageBean.setData(hisApplicationForDrugReturnDetailsMapper.byRecordNumber(pageBean));
        return CodeHelper.getInstance().setCodeValue(pageBean);
    }
}
