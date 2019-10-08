package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.*;
import com.ahsj.hiscore.entity.HisApplicationForDrugReturn;
import com.ahsj.hiscore.entity.HisApplicationForDrugReturnDetails;
import com.ahsj.hiscore.entity.HisMedicationDetails;
import com.ahsj.hiscore.entity.HisRelatedMedicationandexit;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HisApplicationForDrugReturnServicelmpl implements HisApplicationForDrugReturnService {

    @Autowired
    HisApplicationForDrugReturnMapper hisApplicationForDrugReturnMapper;

    @Autowired
    HisMedicationDetailsMapper hisMedicationDetailsMapper;

    @Autowired
    HisApplicationForDrugReturnDetailsService hisApplicationForDrugReturnDetailsService;

    @Autowired
    HisApplicationForDrugReturnDetailsMapper hisApplicationForDrugReturnDetailsMapper;

    @Autowired
    HisMediEnterDetailsMapper hisMediEnterDetailsMapper;

    @Autowired
    HisMedicalRecordMapper hisMedicalRecordMapper;


    @Autowired
    HisRelatedMedicationandexitMapper hisRelatedMedicationandexitMapper;

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisApplicationForDrugReturn>
     * @Description 药品报损表的分页查询
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 12:08
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisApplicationForDrugReturn> list(PageBean<HisApplicationForDrugReturn> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisApplicationForDrugReturnMapper.list(pageBean)));
        return pageBean;
    }

    /**
     * @return core.message.Message
     * @Description 药品退回申请及其详细信息保存
     * @Params [ids, numbers, validityPeriods, patientName, phonenumber, idcard, prescriptionNumber, reason]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 15:14
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveForApply(Long[] ids, Long[] numbers, String patientName, Long phonenumber, String idcard, String reason) throws Exception {
        if (ids.length == 0)
            return MessageUtil.createMessage(false, "请至少选择一个药品信息");
        HisApplicationForDrugReturn hisApplicationForDrugReturn = new HisApplicationForDrugReturn();
        //药品退回凭证生成 按照当前时间生成，保证独一无二
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String createdate = sdf.format(date);
        int count = hisApplicationForDrugReturnMapper.selectNumbCount(createdate) + 1;
        boolean flag = false;
        String voucher = "";
        while(true){
            voucher = "ARD"+createdate + String.format("%05d", count);
            HisApplicationForDrugReturn checkVoucher = hisApplicationForDrugReturnMapper.selectByVoucher(voucher);
            if(EmptyUtil.Companion.isNullOrEmpty(checkVoucher))
                break;
            else
                count++;
        }
        String medicalRecordId = "";
        List<HisApplicationForDrugReturnDetails> hisApplicationForDrugReturnDetailsList = new ArrayList<>();
        List<Integer> listForRecord = new ArrayList<>();
        //d为用药明细表与出库表关联的的主键ID
        for (int i = 0; i < ids.length; i++) {
            HisRelatedMedicationandexit hisRelatedMedicationandexit = hisRelatedMedicationandexitMapper.selectByPrimaryKey(ids[i]);
            HisMedicationDetails hisMedicationDetails = hisMedicationDetailsMapper.selectByPrimaryKey(hisRelatedMedicationandexit.getMedicationDetailId());
            if (hisMedicationDetails.getCount() < numbers[i]) {
                listForRecord.add(i);
                continue;
            }
            medicalRecordId = hisMedicalRecordMapper.selectByPrimaryKey(hisMedicationDetails.getMedicalRecordId()).getMedicalRecordId();
            HisApplicationForDrugReturnDetails hisApplicationForDrugReturnDetails = new HisApplicationForDrugReturnDetails();
            hisApplicationForDrugReturnDetails.setVoucher(voucher);
            hisApplicationForDrugReturnDetails.setReturnCount(numbers[i].intValue());
            hisApplicationForDrugReturnDetails.setDrugsNumb(hisMedicationDetails.getDrugsNumb());
            hisApplicationForDrugReturnDetails.setDrugsName(hisMedicationDetails.getDrugsName());
            hisApplicationForDrugReturnDetails.setDrugsSpec(hisMedicationDetails.getDrugsSpec());
            if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetails.getPlaceorigin()))
                hisApplicationForDrugReturnDetails.setPlaceorigin(hisMedicationDetails.getPlaceorigin());
            hisApplicationForDrugReturnDetails.setMedicationDetailId(hisMedicationDetails.getId());
            hisApplicationForDrugReturnDetails.setValidityPeriod(hisRelatedMedicationandexit.getValidityPeriod());
            hisApplicationForDrugReturnDetails.setPrice(hisRelatedMedicationandexit.getPrice());
            hisApplicationForDrugReturnDetails.setTotalPrice(hisRelatedMedicationandexit.getPrice().multiply(BigDecimal.valueOf(numbers[i])));
            hisApplicationForDrugReturnDetails.setRelatedId(hisRelatedMedicationandexit.getId());
            hisApplicationForDrugReturnDetailsList.add(hisApplicationForDrugReturnDetails);
        }
        HisApplicationForDrugReturn hisApplicationForDrugReturn1 = hisApplicationForDrugReturnMapper.selectByRecordNumberNotIsReview(medicalRecordId);
        if(!EmptyUtil.Companion.isNullOrEmpty(hisApplicationForDrugReturn1)){
            return MessageUtil.createMessage(false,"此编号已经提交过申请，正在等待审核，请勿重复提交申请");
        }
        if (!EmptyUtil.Companion.isNullOrEmpty(listForRecord)) {
            String str = "";
            for (int i = 0; i < listForRecord.size(); i++) {
                str += String.format("第%d个药品退药数目大于当时出药数目%n", listForRecord.get(i) + 1);
            }
            return MessageUtil.createMessage(false, str);
        }
        hisApplicationForDrugReturnDetailsService.saveOrUpdate(hisApplicationForDrugReturnDetailsList);
        hisApplicationForDrugReturn.setVoucher(voucher);
        hisApplicationForDrugReturn.setPatientName(patientName);
        hisApplicationForDrugReturn.setPhonenumber(phonenumber);
        hisApplicationForDrugReturn.setIdcard(idcard);
        hisApplicationForDrugReturn.setRecordNumber(medicalRecordId);
        hisApplicationForDrugReturn.setReason(reason);
        if (hisApplicationForDrugReturn.getIsReview() == null)
            hisApplicationForDrugReturn.setIsReview(3);
        hisApplicationForDrugReturn.setIsPayed(2);
        saveOrUpdate(hisApplicationForDrugReturn);
        return MessageUtil.createMessage(true, "药品退回申请提交成功");
    }

    /**
     * @return com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     * @Description
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 15:54
     **/
    @Override
    @Transactional(readOnly = true)
    public HisApplicationForDrugReturn selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisApplicationForDrugReturnMapper.selectByPrimaryKey(id));
    }

    /**
     * @return core.message.Message
     * @Description
     * @Params [hisApplicationForDrugReturn]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 16:16
     **/
    @Override
    @Transactional(readOnly = false)
    public Message Update(HisApplicationForDrugReturn hisApplicationForDrugReturn) throws Exception {
        HisApplicationForDrugReturn checkId = hisApplicationForDrugReturnMapper.selectByPrimaryKey(hisApplicationForDrugReturn.getId());
        if (EmptyUtil.Companion.isNullOrEmpty(checkId)) {
            return MessageUtil.createMessage(false, "参数错误");
        } else {
            checkId.setRecordNumber(hisApplicationForDrugReturn.getRecordNumber());
            checkId.setPatientName(hisApplicationForDrugReturn.getPatientName());
            checkId.setPhonenumber(hisApplicationForDrugReturn.getPhonenumber());
            checkId.setIdcard(hisApplicationForDrugReturn.getIdcard());
            checkId.setCreateDate(hisApplicationForDrugReturn.getCreateDate());
            checkId.setReason(hisApplicationForDrugReturn.getReason());
            checkId.setRemarks(hisApplicationForDrugReturn.getRemarks());
            hisApplicationForDrugReturnMapper.updateByPrimaryKeySelective(checkId);
            return MessageUtil.createMessage(true, "更新成功");
        }
    }

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [ids]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 16:28
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id : ids) {
            hisApplicationForDrugReturnDetailsService.deleteByVoucher(hisApplicationForDrugReturnMapper.selectByPrimaryKey(id).getVoucher());
            hisApplicationForDrugReturnMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true, "删除成功!");
    }

    /**
     * @return com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     * @Description 根据凭证查找
     * @Params [voucher]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 17:13
     **/
    @Override
    @Transactional(readOnly = true)
    public HisApplicationForDrugReturn selectByVoucher(String voucher) {
        return hisApplicationForDrugReturnMapper.selectByVoucher(voucher);
    }


    /**
     * @return core.message.Message
     * @Description 新增或更新
     * @Params [hisApplicationForDrugReturnDetails]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 9:47
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisApplicationForDrugReturn hisApplicationForDrugReturn) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisApplicationForDrugReturn.getId())) {
            hisApplicationForDrugReturnMapper.insert(hisApplicationForDrugReturn);
            return MessageUtil.createMessage(true, "新增成功");
        } else {
            hisApplicationForDrugReturnMapper.updateByPrimaryKey(hisApplicationForDrugReturn);
            return MessageUtil.createMessage(true, "更新成功");
        }
    }

    /**
     * @Description 根据交易流水号查询退药信息
     * @Param number:
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     * @Date 2019/7/25
     * @Time 10:13
     **/
    @Transactional(readOnly = true)
    @Override
    public HisApplicationForDrugReturn getNumber(String number) throws Exception {
        return hisApplicationForDrugReturnMapper.getByNumber(number);
    }

    /**
     * @Description 修改退款状态
     * @Param record:
     * @Author: dingli
     * @return: int
     * @Date 2019/7/25
     * @Time 15:27
     **/
    @Transactional(readOnly = false)
    @Override
    public int updateByPrimaryKeySelective(HisApplicationForDrugReturn record) throws Exception {
        return hisApplicationForDrugReturnMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @Description 根据就诊编号查看退药明细
     * @Param RecordNumber:
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     * @Date 2019/8/1
     * @Time 10:24
     **/
    @Override
    @Transactional(readOnly = true)
    public HisApplicationForDrugReturn showDrugByRecordNumber(String recordNumber) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisApplicationForDrugReturnMapper.showDrugByRecordNumber(recordNumber))) {
            return CodeHelper.getInstance().setCodeValue(hisApplicationForDrugReturnMapper.showDrugByRecordNumber(recordNumber));
        }
        return new HisApplicationForDrugReturn();
    }

    /**
     * @param recordNumber
     * @Description 根据就诊编号查出用药明细
     * @Author: dingli
     * @return: com.ahsj.hiscore.entity.HisApplicationForDrugReturn
     * @Date 2019/8/20
     * @Time 10:45
     **/
    @Override
    @Transactional(readOnly = true)
    public HisApplicationForDrugReturn selectByRecordNumber(String recordNumber) {
        return hisApplicationForDrugReturnMapper.selectByRecordNumber(recordNumber);
    }

    /**
     * /**
     *
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisApplicationForDrugReturn>
     * @Description 为药品审核的list查询 只查询出未审核的申请
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-11
     * @Time 17:32
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisApplicationForDrugReturn> listForReview(PageBean<HisApplicationForDrugReturn> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisApplicationForDrugReturnMapper.listForReview(pageBean)));
        return pageBean;
    }
}
