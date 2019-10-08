package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.*;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.services.HisMediEnterDetailsService;
import com.ahsj.hiscore.services.HisMedicinePurchasingPlanRecordService;
import com.ahsj.hiscore.services.HisPharmacyDetailService;
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
public class HisMediEnterDetailsServicelmpl implements HisMediEnterDetailsService {
    @Autowired
    HisMediEnterDetailsMapper hisMediEnterDetailsMapper;

    @Autowired
    HisPharmacyDetailMapper hisPharmacyDetailMapper;

    @Autowired
    HisMedicineInfoMapper hisMedicineInfoMapper;

    @Autowired
    MedicinePurchasingPlanMapper medicinePurchasingPlanMapper;

    @Autowired
    HisMedicinePurchasingPlanRecordMapper hisMedicinePurchasingPlanRecordMapper;

    @Autowired
    HisPharmacyDetailService hisPharmacyDetailService;

    @Autowired
    HisMedicinePurchasingPlanRecordService hisMedicinePurchasingPlanRecordService;



    /**
     * @return core.message.Message
     * @Description 药品入库（单个入库函数）
     * @Params [hisMediEnterDetails]
     * @Author zhushixiang
     * @Date 2019-06-26
     * @Time 11:57
     **/
    @Override
    @Transactional(readOnly = false)
    public void unitMdeiEnter(HisMediEnterDetails hisMediEnterDetails) throws Exception {
        //判断药品基本表中有无此药品id
        HisMedicineInfo checkId = hisMedicineInfoMapper.selectByPrimaryKey(hisMediEnterDetails.getPharmacyId());

        if (!EmptyUtil.Companion.isNullOrEmpty(checkId)) {
            //判断药库是否有此药品id
            HisPharmacyDetail checkPharmacyId = hisPharmacyDetailMapper.selectByPharmacyId(hisMediEnterDetails.getPharmacyId());
            if(EmptyUtil.Companion.isNullOrEmpty(checkPharmacyId)){
                HisPharmacyDetail hisPharmacyDetailInsert = new HisPharmacyDetail();
                hisPharmacyDetailInsert.setMedicineId(checkPharmacyId.getMedicineId());
                hisPharmacyDetailInsert.setStock(hisMediEnterDetails.getEnterCount().intValue());
                hisPharmacyDetailInsert.setSaleCounts(0);
                hisPharmacyDetailInsert.setEnterPrice(hisMediEnterDetails.getPrice());
                hisPharmacyDetailInsert.setDrugsNumb(checkPharmacyId.getDrugsNumb());
                hisPharmacyDetailInsert.setDrugsName(checkPharmacyId.getDrugsName());
                hisPharmacyDetailInsert.setDrugsAlias(checkPharmacyId.getDrugsAlias());
                hisPharmacyDetailInsert.setDrugsSpec(checkPharmacyId.getDrugsSpec());
                hisPharmacyDetailInsert.setPrescription(checkPharmacyId.getPrescription());
                hisPharmacyDetailInsert.setMentalMedicine(checkPharmacyId.getMentalMedicine());
                hisPharmacyDetailInsert.setDisable(checkPharmacyId.getDisable());
                hisPharmacyDetailInsert.setLevel(checkPharmacyId.getLevel());
                hisPharmacyDetailInsert.setIsObtained(2);
                hisPharmacyDetailInsert.setMedicalInsuranceSign(checkPharmacyId.getMedicalInsuranceSign());
                hisPharmacyDetailInsert.setPlaceorigin(checkPharmacyId.getPlaceorigin());
                hisPharmacyDetailInsert.setBaseMedicine(checkPharmacyId.getBaseMedicine());
                hisPharmacyDetailInsert.setNarcoticDrugs(checkPharmacyId.getNarcoticDrugs());
                hisPharmacyDetailInsert.setRemarks(checkPharmacyId.getRemarks());
                hisMediEnterDetailsMapper.insert(hisMediEnterDetails);
                hisPharmacyDetailService.saveOrUpdate(hisPharmacyDetailInsert);
            }else {
                checkPharmacyId.setStock(checkPharmacyId.getStock()+hisMediEnterDetails.getEnterCount().intValue());
                hisMediEnterDetailsMapper.insert(hisMediEnterDetails);
                hisPharmacyDetailService.saveOrUpdate(checkPharmacyId);
                //新增入库记录到入库记录表
            }
        }
    }



    /**
     *@Description 药品入库(根据采购计划入库)
     *@Params [ids, numbers, prices, planNumber]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-05
     *@Time 14:17
    **/
    @Override
    @Transactional(readOnly = false)
    public Message mediEnter(Long[] ids, Long[] numbers, Double[] prices, String planNumber,String[] validityPeriods) throws Exception {
        List<MedicinePurchasingPlan> medicinePurchasingPlanList=medicinePurchasingPlanMapper.selectByPlanNumber(planNumber);
        if(EmptyUtil.Companion.isNullOrEmpty(medicinePurchasingPlanList))
            return MessageUtil.createMessage(false,"无可添加数据");
        double actualExpengiture=0;
        List<HisMediEnterDetails> hisMediEnterDetailsList=new ArrayList<>();
        List<HisPharmacyDetail> hisPharmacyDetailList=new ArrayList<>();
        for (int i = 0; i <ids.length ; i++) {
            HisMediEnterDetails hisMediEnterDetails=new HisMediEnterDetails();
            MedicinePurchasingPlan medicinePurchasingPlan=medicinePurchasingPlanMapper.selectByPrimaryKey(ids[i]);
            hisMediEnterDetails.setPharmacyId(medicinePurchasingPlan.getPharmacyId());
            hisMediEnterDetails.setEnterCount((numbers[i]));
            hisMediEnterDetails.setSurplus(numbers[i]);
            hisMediEnterDetails.setEnterNumber(planNumber);
            //日期格式化
            SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
            Date validityPeriod=null;
            validityPeriod=format.parse(validityPeriods[i]);
            hisMediEnterDetails.setValidityPeriod(validityPeriod);
            hisMediEnterDetails.setDrugsNumb(medicinePurchasingPlan.getDrugsNumb());
            hisMediEnterDetails.setDrugsName(medicinePurchasingPlan.getDrugsName());
            hisMediEnterDetails.setDrugsSpec(medicinePurchasingPlan.getDrugsSpec());
            hisMediEnterDetails.setPrice(BigDecimal.valueOf(prices[i]));
            actualExpengiture += prices[i]*numbers[i];
            HisPharmacyDetail checkPharmacyId = hisPharmacyDetailMapper.selectByPharmacyId(hisMediEnterDetails.getPharmacyId());
            if(EmptyUtil.Companion.isNullOrEmpty(checkPharmacyId)){
                HisMedicineInfo hisMedicineInfo=hisMedicineInfoMapper.selectByPrimaryKey(medicinePurchasingPlan.getPharmacyId());
                HisPharmacyDetail hisPharmacyDetailInsert = new HisPharmacyDetail();
                hisPharmacyDetailInsert.setMedicineId(hisMedicineInfo.getId());
                hisPharmacyDetailInsert.setStock(hisMediEnterDetails.getEnterCount().intValue());
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMediEnterDetails.getPrice()))
                    hisPharmacyDetailInsert.setEnterPrice(hisMediEnterDetails.getPrice());
                hisPharmacyDetailInsert.setDrugsNumb(hisMedicineInfo.getDrugsNumb());
                hisPharmacyDetailInsert.setDrugsName(hisMedicineInfo.getDrugsName());
                hisPharmacyDetailInsert.setSaleCounts(0);
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getDrugsAlias()))
                    hisPharmacyDetailInsert.setDrugsAlias(hisMedicineInfo.getDrugsAlias());
                hisPharmacyDetailInsert.setDrugsSpec(hisMedicineInfo.getDrugsSpec());
                hisPharmacyDetailInsert.setPrescription(hisMedicineInfo.getPrescription());
                hisPharmacyDetailInsert.setMentalMedicine(hisMedicineInfo.getMentalMedicine());
                hisPharmacyDetailInsert.setDisable(hisMedicineInfo.getDisable());
                hisPharmacyDetailInsert.setLevel(hisMedicineInfo.getLevel());
                hisPharmacyDetailInsert.setIsObtained(2);
                hisPharmacyDetailInsert.setDisable(2);
                hisPharmacyDetailInsert.setMedicalInsuranceSign(hisMedicineInfo.getMedicalInsuranceSign());
                hisPharmacyDetailInsert.setPlaceorigin(hisMedicineInfo.getPlaceorigin());
                hisPharmacyDetailInsert.setBaseMedicine(hisMedicineInfo.getBaseMedicine());
                hisPharmacyDetailInsert.setNarcoticDrugs(hisMedicineInfo.getNarcoticDrugs());
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getLargeUnit()))
                    hisPharmacyDetailInsert.setLargeUnit(hisMedicineInfo.getLargeUnit());
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getSmallUnit()))
                    hisPharmacyDetailInsert.setSmallUnit(hisMedicineInfo.getSmallUnit());
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMedicineInfo.getConversionRate()))
                    hisPharmacyDetailInsert.setConversionRate(hisMedicineInfo.getConversionRate());
                hisMediEnterDetailsList.add(hisMediEnterDetails);
                hisPharmacyDetailList.add(hisPharmacyDetailInsert);

            }else {
                checkPharmacyId.setStock(checkPharmacyId.getStock()+hisMediEnterDetails.getEnterCount().intValue());
                hisMediEnterDetailsList.add(hisMediEnterDetails);
                hisPharmacyDetailService.UpdateForPart(checkPharmacyId);
                //新增入库记录到入库记录表
            }
        }
        saveForMultiple(hisMediEnterDetailsList);
        if(hisPharmacyDetailList.size()>0)
            hisPharmacyDetailService.saveForMultiple(hisPharmacyDetailList);
        HisMedicinePurchasingPlanRecord hisMedicinePurchasingPlanRecord=hisMedicinePurchasingPlanRecordMapper.selectByPlanNumber(planNumber);
        hisMedicinePurchasingPlanRecord.setCompletion(1);
        hisMedicinePurchasingPlanRecord.setCompletionTime(new Date());
        hisMedicinePurchasingPlanRecord.setActualExpenditure(BigDecimal.valueOf(actualExpengiture));
        hisMedicinePurchasingPlanRecordService.saveOrUpdate(hisMedicinePurchasingPlanRecord);
        return MessageUtil.createMessage(true,"入库成功");
    }

    /**
     *@Description 根据药品编号从入库表中将所有此编号的药品查询出（根据有效期分好组）
     *@Params [pharmacyId]
     *@return java.util.List<com.ahsj.hiscore.entity.HisMediEnterDetails>
     *@Author zhushixiang
     *@Date 2019-07-06
     *@Time 11:29
    **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMediEnterDetails> selectByPharmacyId(Long pharmacyId) {
        return CodeHelper.getInstance().setCodeValue(hisMediEnterDetailsMapper.selectByPharmacyId(pharmacyId));
    }

    /**
     *@Description 分页查询
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMediEnterDetails>
     *@Author zhushixiang
     *@Date 2019-07-06
     *@Time 16:18
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMediEnterDetails> list(PageBean<HisMediEnterDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisMediEnterDetailsMapper.list(pageBean)));
        return pageBean;
    }

    /**
     *@Description 通过药品id查询入库表中所有数据以pageBean分页对象传回查到的数据
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMediEnterDetails>
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 10:22
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMediEnterDetails> listByPharmacyId(PageBean<HisMediEnterDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisMediEnterDetailsMapper.listByPharmacyId(pageBean)));
        return pageBean;
    }

    /**
     *@Description 根据ID查找
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisMediEnterDetails
     *@Author zhushixiang
     *@Date 2019-07-10
     *@Time 20:12
    **/
    @Override
    @Transactional(readOnly = true)
    public HisMediEnterDetails selectByPrimaryKey(Long id) {
        return hisMediEnterDetailsMapper.selectByPrimaryKey(id);
    }

    /**
     *@Description 新增或更新
     *@Params [hisMediEnterDetails]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-10
     *@Time 20:41
    **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisMediEnterDetails hisMediEnterDetails) throws Exception {
        if(EmptyUtil.Companion.isNullOrEmpty(hisMediEnterDetails.getId())){
            hisMediEnterDetailsMapper.insert(hisMediEnterDetails);
            return MessageUtil.createMessage(true,"新增成功");
        }else{
            hisMediEnterDetailsMapper.updateByPrimaryKeySelective(hisMediEnterDetails);
            return MessageUtil.createMessage(true,"更新成功");
        }
    }

    /**
     *@Description 批量新增
     *@Params [hisMediEnterDetailsList]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-12
     *@Time 11:11
    **/
    @Override
    @Transactional(readOnly = false)
    public void saveForMultiple(List<HisMediEnterDetails> hisMediEnterDetailsList) throws Exception {
        hisMediEnterDetailsMapper.insertBatch(hisMediEnterDetailsList);
    }

    /**
     *@Description 批量更新剩余入库量
     *@Params [hisMediEnterDetailsList]
     *@return void
     *@Author zhushixiang
     *@Date 2019-07-23
     *@Time 19:52
    **/
    @Override
    @Transactional(readOnly = false)
    public void updateBatch(List<HisMediEnterDetails> hisMediEnterDetailsList) {
        hisMediEnterDetailsMapper.updateBatch(hisMediEnterDetailsList);
    }
}
