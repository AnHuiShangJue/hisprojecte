package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.*;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.*;
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
public class HisMediExitDetailsServicelmpl implements HisMediExitDetailsService {
    @Autowired
    HisMediExitDetailsMapper hisMediExitDetailsMapper;

    @Autowired
    HisPharmacyDetailMapper hisPharmacyDetailMapper;

    @Autowired
    HisMediEnterDetailsMapper hisMediEnterDetailsMapper;

    @Autowired
    HisPharmacyDetailService hisPharmacyDetailService;

    @Autowired
    HisMediEnterDetailsService hisMediEnterDetailsService;

    @Autowired
    HisMedicationDetailsMapper hisMedicationDetailsMapper;

    @Autowired
    HisMedicationDetailsService hisMedicationDetailsService;

    @Autowired
    HisMedicalRecordMapper hisMedicalRecordMapper;

    @Autowired
    HisTollRecordMapper hisTollRecordMapper;

    @Autowired
    HisHospitalManageMapper hisHospitalManageMapper;

    @Autowired
    HisRelatedMedicationandexitService hisRelatedMedicationandexitService;

    @Autowired

    ITranslateService iTranslateService;


    /**
     * @return core.message.Message
     * @Description 药品出库
     * @Params [hisMediExitDetails]
     * @Author zhushixiang
     * @Date 2019-06-26
     * @Time 12:01
     **/
    @Override
    @Transactional(readOnly = false)
    public Message mediExit(HisMediExitDetails hisMediExitDetails) throws Exception {
        //判断药库中有无此药品信息
        HisPharmacyDetail checkForPharmacyId = hisPharmacyDetailMapper.selectByPharmacyId(hisMediExitDetails.getPharmacyId());
        if (!EmptyUtil.Companion.isNullOrEmpty(checkForPharmacyId)) {
            hisMediExitDetailsMapper.insert(hisMediExitDetails);
            HisPharmacyDetail checkPharmacyIdAndVali = hisPharmacyDetailMapper.selectByPharmacyIdAndVali(hisMediExitDetails);
            //核对有效期以及药品ID，以便出库时扣除相应的药品库存数目
            if (EmptyUtil.Companion.isNullOrEmpty(checkPharmacyIdAndVali)) {
                hisMediExitDetailsMapper.deleteByPrimaryKey(hisMediExitDetails.getId());
                return MessageUtil.createMessage(false, "药库无此药品信息!有此药品ID，但无有效期与其匹配的药品");
            }
            //判断药品数量是否足够，不够则返回出库失败，并返回库存量
            if (checkPharmacyIdAndVali.getCount() >= hisMediExitDetails.getExitCount()) {
                checkPharmacyIdAndVali.setStock(checkPharmacyIdAndVali.getStock() - hisMediExitDetails.getExitCount());
                hisPharmacyDetailService.saveOrUpdate(checkPharmacyIdAndVali);
                return MessageUtil.createMessage(true, "药品出库成功!");
            } else {
                hisMediExitDetailsMapper.deleteByPrimaryKey(hisMediExitDetails.getId());
                return MessageUtil.createMessage(false, "药品库存不足!当前库存量为：" + checkPharmacyIdAndVali.getCount());
            }
        } else
            return MessageUtil.createMessage(false, "医院无此药品信息!");
    }

    /**
     * @return core.message.Message
     * @Description 药品出库按钮
     * @Params [ids, numbers, prices]
     * @Author zhushixiang
     * @Date 2019-07-06
     * @Time 21:11
     **/
    @Override
    @Transactional(readOnly = false)
    public Message medicineExit(Long[] ids, Long[] numbers, Double[] prices) throws Exception {
        //此出库操作默认同一药品在入库记录表中有效期不会相同，若需要改动需要多加几次判断
        for (int i = 0; i < ids.length; i++) {
            double unitSalePrice = 0;
            //根据入库表id获得此次要出库药品的相关信息(从入库表中获得)
            HisMediEnterDetails hisMediEnterDetails = hisMediEnterDetailsMapper.selectByPrimaryKey(ids[i]);
            //单条记录的价格，此次出库的该药品的总价
            unitSalePrice = numbers[i] * prices[i];
            //向出库记录中设置一些从入库表中获得的基本信息
            HisMediExitDetails hisMediExitDetails = new HisMediExitDetails();
            //设置出库编号，与入库编号相同
            hisMediExitDetails.setExitNumber(hisMediEnterDetails.getEnterNumber());
            hisMediExitDetails.setPharmacyId(hisMediEnterDetails.getPharmacyId());
            hisMediExitDetails.setDrugsNumb(hisMediEnterDetails.getDrugsNumb());
            hisMediExitDetails.setDrugsName(hisMediEnterDetails.getDrugsName());
            hisMediExitDetails.setDrugsSpec(hisMediEnterDetails.getDrugsSpec());
            hisMediExitDetails.setValidityPeriod(hisMediEnterDetails.getValidityPeriod());
            hisMediExitDetails.setMediEnterId(ids[i]);
            //向出库记录设置出库数目与药品项总价
            hisMediExitDetails.setTotalPrice(BigDecimal.valueOf(unitSalePrice));
            //防止药品数量不足以出库所做的判断，此步在前端应已经设置过一次拦截,intValue()将Long型转化为integer型数据
            if (numbers[i] > hisMediEnterDetails.getSurplus())
                return MessageUtil.createMessage(false, "药品余量不足，请重新输入");
            hisMediExitDetails.setExitCount(numbers[i].intValue());
            //当设置药品出库数量后，入库的余量以及药库库存需要级联更改 同时增加药库中药品的售出数目
            hisMediEnterDetails.setSurplus(hisMediEnterDetails.getSurplus() - numbers[i]);
            HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailMapper.selectByPharmacyId(hisMediEnterDetails.getPharmacyId());
            hisPharmacyDetail.setStock(hisPharmacyDetail.getStock() - numbers[i].intValue());
            hisPharmacyDetail.setSaleCounts(hisPharmacyDetail.getSaleCounts() + numbers[i].intValue());
            //保存出库记录，更新入库表中本次库存剩余数目以及药库中的库存
            hisMediExitDetailsMapper.insert(hisMediExitDetails);
            hisMediEnterDetailsService.saveOrUpdate(hisMediEnterDetails);
            hisPharmacyDetailService.saveOrUpdate(hisPharmacyDetail);
        }
        return MessageUtil.createMessage(true, "药品出库成功");
    }

    /**
     * @return core.message.Message
     * @Description 智能出库
     * @Params [ids, numbers]
     * @Author zhushixiang
     * @Date 2019-07-08
     * @Time 17:39
     **/
    @Override
    @Transactional(readOnly = false)
    public Message IntelligenceExit(Long[] ids, Integer[] numbers) throws Exception {
        if (ids.length == 0) {
            return MessageUtil.createMessage(false, "请选择要出库的药品");
        } else {
            //循环ids
            for (int i = 0; i < ids.length; ++i) {
                //根据 主键id，查找 库存表 的记录,保存在 库存对象 中
                HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailMapper.selectByPrimaryKey(ids[i]);
                //判断 要出库数量（numbers[i]) 与 当前库存量 的大小
                if (numbers[i] > hisPharmacyDetail.getStock()) {
                    //出库量 > 库存量时，出库失败
                    return MessageUtil.createMessage(false, "库存不足");
                } else {
                    //库存量足够时
                    hisPharmacyDetail.setStock(hisPharmacyDetail.getStock() - numbers[i]);
                    hisPharmacyDetail.setSaleCounts(hisPharmacyDetail.getSaleCounts() + numbers[i]);
                    //更新库存表的数据
                    hisPharmacyDetailService.saveOrUpdate(hisPharmacyDetail);
                    //当前要出库的数量
                    int remain = numbers[i];
                    //new 一个 出库详情 对象
                    HisMediExitDetails hisMediExitDetails = new HisMediExitDetails();
                    //添加数据到出库表
                    //循环出库，优先出库有效期较早的耗材
                    //取出该耗材对应的入库表的记录
                    List<HisMediEnterDetails> hisMediEnterDetailsList = hisMediEnterDetailsMapper.selectByPharmacyIdOrder(hisPharmacyDetail.getMedicineId());
                    for (int j = 0; remain > 0; ++j) {
                        //给出库记录添加数据
                        hisMediExitDetails.setPharmacyId(hisMediEnterDetailsList.get(j).getPharmacyId());
                        hisMediExitDetails.setDrugsNumb(hisMediEnterDetailsList.get(j).getDrugsNumb());
                        hisMediExitDetails.setDrugsName(hisMediEnterDetailsList.get(j).getDrugsName());
                        hisMediExitDetails.setDrugsSpec(hisMediEnterDetailsList.get(j).getDrugsSpec());
                        //价格设置需要思考
                        hisMediExitDetails.setTotalPrice(hisPharmacyDetail.getSalePrice());
                        hisMediExitDetails.setExitNumber(hisMediEnterDetailsList.get(j).getEnterNumber());

                        //判断要出库的量在当前 入库记录 的数量的大小，如果出库较大
                        if (remain > hisMediEnterDetailsList.get(j).getSurplus()) {
                            if (hisMediEnterDetailsList.get(j).getSurplus() <= 0)
                                continue;
                            //插入出库记录到出库表
                            hisMediExitDetails.setValidityPeriod(hisMediEnterDetailsList.get(j).getValidityPeriod());
                            hisMediExitDetails.setExitCount(hisMediEnterDetailsList.get(j).getSurplus().intValue());
                            //更新出库量
                            remain = remain - hisMediEnterDetailsList.get(j).getSurplus().intValue();
                            //设置该条记录的可用余量为0
                            hisMediEnterDetailsList.get(j).setSurplus(0L);
                            //更新入库表
                            hisMediEnterDetailsService.saveOrUpdate(hisMediEnterDetailsList.get(j));
                            hisMediExitDetailsMapper.insert(hisMediExitDetails);
                        } else {//只需要一次出库
                            //更新入库记录的可用余额
                            hisMediEnterDetailsList.get(j).setSurplus(hisMediEnterDetailsList.get(j).getSurplus() - remain);
                            hisMediEnterDetailsService.saveOrUpdate(hisMediEnterDetailsList.get(j));
                            //插入出库记录到出库表
                            hisMediExitDetails.setValidityPeriod(hisMediEnterDetailsList.get(j).getValidityPeriod());
                            hisMediExitDetails.setExitCount(remain);
                            remain = 0;
                            hisMediExitDetailsMapper.insert(hisMediExitDetails);
                        }
                    }
                }

            }
            return MessageUtil.createMessage(true, "出库成功");
        }
    }

    /**
     * @return core.message.Message
     * @Description 新增或更新
     * @Params [hisMediExitDetails]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 20:08
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisMediExitDetails hisMediExitDetails) throws Exception {
        // 如果主健为空 则为新增
        if (EmptyUtil.Companion.isNullOrEmpty(hisMediExitDetails.getId())) {
            hisMediExitDetailsMapper.insert(hisMediExitDetails);
            return MessageUtil.createMessage(true, "新增成功");
        } else {
            hisMediExitDetailsMapper.updateByPrimaryKey(hisMediExitDetails);
            return MessageUtil.createMessage(false, "更新成功");
        }

    }

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [ids]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 20:08
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id : ids) {
            hisMediExitDetailsMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true, "删除成功!");
    }

    /**
     * @return com.ahsj.hiscore.entity.HisMediExitDetails
     * @Description 初始化（根据主键ID查找）
     * @Params [id]
     * @Author zhushixiang
     * @Date 2019-07-10
     * @Time 20:08
     **/
    @Override
    @Transactional(readOnly = true)
    public HisMediExitDetails updateInit(Long id) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisMediExitDetailsMapper.selectByPrimaryKey(id));
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMediExitDetails> list(PageBean<HisMediExitDetails> pageBean) throws Exception {
        return null;
    }

    /**
     * @return core.message.Message
     * @Description 批量新增
     * @Params [hisMediExitDetailsList]
     * @Author zhushixiang
     * @Date 2019-07-12
     * @Time 11:12
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveForMultiple(List<HisMediExitDetails> hisMediExitDetailsList) throws Exception {
        hisMediExitDetailsMapper.insertBatch(hisMediExitDetailsList);
        return MessageUtil.createMessage(true, "批量出库成功");
    }

    /**
     * @return core.message.Message
     * @Description 根据就诊编号/住院编号/交易流水号药品出库
     * @Params [number]
     * @Author zhushixiang
     * @Date 2019-07-23
     * @Time 16:40
     **/
    @Override
    @Transactional(readOnly = false)
    public Message mediExitByNumber(List<HisMedicationDetails> hisMedicationDetailsList) throws Exception {
        if (hisMedicationDetailsList.size() == 0)
            return MessageUtil.createMessage(false, "没有对应的用药明细！");
        int flag = 0;//0代表正常，1代表未付，2代表已结算，不可重复结算
        for (int i = 0; i < hisMedicationDetailsList.size(); i++) {
            if (hisMedicationDetailsList.get(i).getIsPay() == 2) {
                flag = 1;
                break;
            } else if (hisMedicationDetailsList.get(i).getIsOut() == 1) {
                flag = 2;
                break;
            }
        }
        if (flag == 1)
            return MessageUtil.createMessage(false, "存在药品未付费");
        else if (flag == 2)
            return MessageUtil.createMessage(false, "此用药明细已经结算");

        List<Integer> checkNum = new ArrayList<>();//定义此数组是为了保存药品不足的索引
        int continueCounts = 0;
        Outer:
        for (int i = 0; i < hisMedicationDetailsList.size(); i++) {
            if (continueCounts > 0) {
                continueCounts--;
                continue Outer;
            }
            List<HisMedicationDetails> tmps = new ArrayList<HisMedicationDetails>();
            inner:
            for (int j = i; j < hisMedicationDetailsList.size(); j++) {
                if (hisMedicationDetailsList.get(i).getDrugsNumb().equals(hisMedicationDetailsList.get(j).getDrugsNumb())) {
                    tmps.add(hisMedicationDetailsList.get(j));
                    if (i != j)
                        continueCounts++;
                } else {
                    break inner;
                }
            }
            int restCounts = tmps.get(0).getCount();
            secondInner:
            for (int k = 0; k < tmps.size(); k++) {
                Long s = tmps.get(k).getSurplus();
                HisMedicationDetails hisMedicationDetails = tmps.get(k);
                //如果第一条数据符合要求，则返回第一条，以后的数据不做处理
                if (s.intValue() >= restCounts) {
                    restCounts = 0;
                    break secondInner;
                } else {
                    //剩余出库数目减少
                    restCounts -= hisMedicationDetails.getActualCount();
                }
            }
            if (restCounts > 0)
                checkNum.add(i);
        }
        if (checkNum.size() != 0) {
            String str = "";
            for (int i = 0; i < checkNum.size(); i++) {
                str += String.format("%s号药品不足   %n", hisMedicationDetailsList.get(checkNum.get(i)).getDrugsNumb());
            }
            return MessageUtil.createMessage(false, str);
        }


        //第一个List用于批量新增出库记录，第二个List用于批量更新入库数目，第三个List用于批量更新药库表的数目，
        List<HisMediExitDetails> hisMediExitDetailsList = new ArrayList<>();
        List<HisMediEnterDetails> hisMediEnterDetailsList = new ArrayList<>();
        List<HisPharmacyDetail> hisPharmacyDetailList = new ArrayList<>();
        List<HisMedicationDetails> medicationDetailsList = new ArrayList<>();
        List<HisRelatedMedicationandexit> hisRelatedMedicationandexitList = new ArrayList<>();
        for (int i = 0; i < hisMedicationDetailsList.size(); i++) {
            HisMediEnterDetails hisMediEnterDetails = null;
            if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetailsList.get(i).getMediEnterId()))
                hisMediEnterDetails = hisMediEnterDetailsMapper.selectByPrimaryKey(hisMedicationDetailsList.get(i).getMediEnterId());
            hisMediEnterDetails.setSurplus(hisMediEnterDetails.getSurplus() - hisMedicationDetailsList.get(i).getActualCount());
            hisMediEnterDetailsList.add(hisMediEnterDetails);
            HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailMapper.selectByDrugsNumb(hisMedicationDetailsList.get(i).getDrugsNumb());
            hisPharmacyDetail.setStock(hisPharmacyDetail.getStock() - hisMedicationDetailsList.get(i).getActualCount());
            hisPharmacyDetail.setSaleCounts(hisPharmacyDetail.getSaleCounts() + hisMedicationDetailsList.get(i).getActualCount());
            hisPharmacyDetailList.add(hisPharmacyDetail);
            HisMediExitDetails hisMediExitDetails = new HisMediExitDetails();
            hisMediExitDetails.setPharmacyId(hisPharmacyDetail.getMedicineId());
            hisMediExitDetails.setExitCount(hisMedicationDetailsList.get(i).getActualCount());
            hisMediExitDetails.setValidityPeriod(hisMedicationDetailsList.get(i).getValidityPeriod());
            if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetailsList.get(i).getTollNumber()))
                hisMediExitDetails.setTollNumber(hisMedicationDetailsList.get(i).getTollNumber());
            hisMediExitDetails.setIsPay(1);
            if (!EmptyUtil.Companion.isNullOrEmpty(hisMedicationDetailsList.get(i).getTotalPrice()))
                hisMediExitDetails.setTotalPrice(hisMedicationDetailsList.get(i).getTotalPrice());
            hisMediExitDetails.setDrugsNumb(hisMedicationDetailsList.get(i).getDrugsNumb());
            hisMediExitDetails.setDrugsName(hisMedicationDetailsList.get(i).getDrugsName());
            hisMediExitDetails.setDrugsSpec(hisMedicationDetailsList.get(i).getDrugsSpec());
            if (!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getDrugsAlias()))
                hisMediExitDetails.setDrugsAlias(hisPharmacyDetail.getDrugsAlias());
            hisMediExitDetails.setExitNumber(hisMediEnterDetails.getEnterNumber());
            hisMediExitDetails.setRecordNumber(hisMedicationDetailsList.get(i).getRecordNumber());
            hisMediExitDetails.setMediEnterId(hisMedicationDetailsList.get(i).getMediEnterId());
            hisMediExitDetailsList.add(hisMediExitDetails);
            HisMedicationDetails medicationDetails = hisMedicationDetailsMapper.selectByPrimaryKey(hisMedicationDetailsList.get(i).getId());
            medicationDetails.setIsOut(1);
            medicationDetailsList.add(medicationDetails);
            //定义一个对象用于将用药明细与出药表关联起来
            HisRelatedMedicationandexit hisRelatedMedicationandexit = new HisRelatedMedicationandexit();
            hisRelatedMedicationandexit.setMedicationDetailId(medicationDetails.getId());
            hisRelatedMedicationandexit.setDrugsNumb(medicationDetails.getDrugsNumb());
            hisRelatedMedicationandexit.setValidityPeriod(hisMedicationDetailsList.get(i).getValidityPeriod());
            hisRelatedMedicationandexit.setCount(hisMedicationDetailsList.get(i).getActualCount());
            hisRelatedMedicationandexit.setPrice(hisPharmacyDetail.getSalePrice());
            hisRelatedMedicationandexitList.add(hisRelatedMedicationandexit);
        }
        hisMediEnterDetailsService.updateBatch(hisMediEnterDetailsList);
        hisPharmacyDetailService.updateBatch(hisPharmacyDetailList);
        saveForMultiple(hisMediExitDetailsList);
        for (int i = 0; i < hisRelatedMedicationandexitList.size(); i++) {
            for (int j = 0; j < hisMediExitDetailsList.size(); j++) {
                if (hisMediExitDetailsList.get(j).getDrugsNumb().equals(medicationDetailsList.get(i).getDrugsNumb()) && hisMediExitDetailsList.get(j).getValidityPeriod().equals(hisRelatedMedicationandexitList.get(i).getValidityPeriod())) {
                    List<HisMediExitDetails> hisMediExitDetails = hisMediExitDetailsMapper.selectByRecordNumberAndDrugsnumbAndValidityPeriod(hisMediExitDetailsList.get(j));
                    hisRelatedMedicationandexitList.get(i).setExitId(hisMediExitDetails.get(0).getId());
                    break;
                }
            }
        }
        hisMedicationDetailsService.updateBatch(medicationDetailsList);
        hisRelatedMedicationandexitService.saveForMultiple(hisRelatedMedicationandexitList);
        return MessageUtil.createMessage(true, "出库成功");
    }

    /**
     * @param ids
     * @Description 查询出药明细
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisMediExitDetails>
     * @Date 2019/8/17
     * @Time 13:19
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMediExitDetails> listByIds(Long[] ids) throws Exception {
        List<HisMediExitDetails> hisMediExitDetails = hisMediExitDetailsMapper.listByIds(ids);
        for (HisMediExitDetails h : hisMediExitDetails) {
            Translate translate = new Translate();
            translate.setTranslateId(h.getId());
            translate.setTranslateType(Constants.TRANSLATE_HIS_MEDICINEINFO);
            List<Translate> translates = iTranslateService.queryTranslate(translate);
            if (!EmptyUtil.Companion.isNullOrEmpty(translates)) {
                translates.stream().filter(f -> f.getTranslateChina().equals(h.getDrugsName())).forEach(e -> h.setTdrugsName(e.getTranslateKhmer()));
                translates.stream().filter(f -> f.getTranslateChina().equals(h.getDrugsSpec())).forEach(e -> h.setTdrugsSpec(e.getTranslateKhmer()));
            }
        }
        return CodeHelper.getInstance().setCodeValue(hisMediExitDetails);

    }
    /**
     * @param ids
     * @Description 查询历史出药明细
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisMediExitDetails>
     * @Date 2019/8/17
     * @Time 13:19
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisMediExitDetails> listByIdsHistory(Long[] ids)throws Exception {
        List<HisMediExitDetails> hisMediExitDetails = hisMediExitDetailsMapper.listByIdsHistory(ids);
        for (HisMediExitDetails h : hisMediExitDetails) {
            Translate translate = new Translate();
            translate.setTranslateId(h.getId());
            translate.setTranslateType(Constants.TRANSLATE_HIS_MEDICINEINFO);
            List<Translate> translates = iTranslateService.queryTranslate(translate);
            if (!EmptyUtil.Companion.isNullOrEmpty(translates)) {
                translates.stream().filter(f -> f.getTranslateChina().equals(h.getDrugsName())).forEach(e -> h.setTdrugsName(e.getTranslateKhmer()));
                translates.stream().filter(f -> f.getTranslateChina().equals(h.getDrugsSpec())).forEach(e -> h.setTdrugsSpec(e.getTranslateKhmer()));
            }
        }
        return CodeHelper.getInstance().setCodeValue(hisMediExitDetails);
    }
}

