package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisTollDetailsMapper;
import com.ahsj.hiscore.entity.HisMedicineInfo;
import com.ahsj.hiscore.entity.HisTollDetails;
import com.ahsj.hiscore.entity.Translate;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisMedicineInfoService;
import com.ahsj.hiscore.services.HisTollDetailsService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author dingli
 * @Date 2019/7/17
 * @Time 17:53
 **/
@Service
public class HisTollDetailsServiceImpl implements HisTollDetailsService {
    @Autowired
    HisTollDetailsMapper hisTollDetailsMapper;


    @Autowired
    ITranslateService iTranslateService;

    @Autowired
    HisMedicineInfoService hisMedicineInfoService;

    @Override
    public int insertSelective(HisTollDetails record) throws Exception {
        return hisTollDetailsMapper.insertSelective(record);
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisTollDetails>
     * @Description 根据就诊编号查询
     * @Params [pageBean]
     * @Author chenzhicai
     * @Date 2019-07-21
     * @Time 12:30
     **/
    @Override
    public PageBean<HisTollDetails> listByMecordId(PageBean<HisTollDetails> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisTollDetailsMapper.listByMecordIdForHospital(pageBean)));
        return pageBean;
    }


    @Override
    public PageBean<HisTollDetails> nurselistByMecordId(PageBean<HisTollDetails> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisTollDetailsMapper.nurselistByMecordIdForHospital(pageBean)));
        return pageBean;
    }


    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisTollRecord>
     * @Description 门诊收费结算
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-20
     * @Time 23:41
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisTollDetails> listForOutpatientCharges(PageBean<HisTollDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisTollDetailsMapper.listForOutpatientCharges(pageBean)));
        return pageBean;
    }

    @Override
    public Message saveForHospi(List<HisTollDetails> hisTollDetails) {
        if (EmptyUtil.Companion.isNullOrEmpty(hisTollDetails) || hisTollDetails.size() == 0)
            return MessageUtil.createMessage(false, "无可付费信息");
        hisTollDetailsMapper.saveForHospi(hisTollDetails);
        return MessageUtil.createMessage(true, "保存成功！");
    }

    @Override
    public PageBean<HisTollDetails> listByMecordIdForSave(PageBean<HisTollDetails> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisTollDetailsMapper.listByMecordIdForSave(pageBean)));
        return pageBean;
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     * @Description 根据就诊记录编号查到对应的门诊收费
     * @Params [medicalRecordId]
     * @Author zhushixiang
     * @Date 2019-07-21
     * @Time 21:34
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisTollDetails> selectForOutpatientChargesByMedicalRecordId(String medicalRecordId) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisTollDetailsMapper.selectForOutpatientChargesByMedicalRecordId(medicalRecordId));
    }


    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisTollDetails>
     * @Description 查询门诊收费记录
     * @Params [hisTollDetailsPageBean]
     * @Author zhushixiang
     * @Date 2019-07-22
     * @Time 10:40
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisTollDetails> listForOutpatientByMecordId(PageBean<HisTollDetails> hisTollDetailsPageBean) {
        hisTollDetailsPageBean.setData(CodeHelper.getInstance().setCodeValue(hisTollDetailsMapper.listForOutpatientByMecordId(hisTollDetailsPageBean)));
        return hisTollDetailsPageBean;
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisTollDetails> listByMecordIdForOutpatientSave(PageBean<HisTollDetails> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisTollDetailsMapper.listByMecordIdForOutpatientSave(pageBean)));
        return pageBean;
    }

    /**
     * @Description 查询收费明细
     * @Param pageBean:
     * @Author: dingli
     * @return: core.entity.PageBean<com.ahsj.hiscore.entity.HisTollDetails>
     * @Date 2019/7/24
     * @Time 16:49
     **/
    @Transactional(readOnly = true)
    @Override
    public PageBean<HisTollDetails> listHisTollDetails(PageBean<HisTollDetails> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisTollDetailsMapper.listHisTollDetails(pageBean)));
        return pageBean;
    }

    /**
     * @Description 插入明细
     * @Param record:
     * @Author: dingli
     * @return: int
     * @Date 2019/7/26
     * @Time 14:04
     **/
    @Override
    @Transactional(readOnly = false)
    public int insert(HisTollDetails record) throws Exception {
        return hisTollDetailsMapper.insert(record);
    }

    /**
     * @Description 根据交易流水号打印
     * @Param number:
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     * @Date 2019/8/3
     * @Time 16:38
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisTollDetails> listByNumber(String number) throws Exception {
        List<HisTollDetails> hisTollDetails = hisTollDetailsMapper.listByNumber(number);//所有收费明细
        BigDecimal drugFee = new BigDecimal("0");//药品费用
        if (EmptyUtil.Companion.isNullOrEmpty(hisTollDetails)) {//没有收费明细
            HisTollDetails priceByNumber = hisTollDetailsMapper.getPriceByNumber(number);
            hisTollDetails.add(priceByNumber);
            return hisTollDetails;
        }
        for (HisTollDetails h : hisTollDetails) {
            Translate translate = new Translate();//翻译
            if (h.getType() == 1 || h.getType() == 4) {//药品
                drugFee = h.getMoneys().add(drugFee);
                HisMedicineInfo hisMedicineInfo = hisMedicineInfoService.selectById(h.getId().longValue());
                h.setDrugsSpec(hisMedicineInfo.getDrugsSpec());
                translate.setTranslateId(h.getId().longValue());
                translate.setTranslateType(Constants.TRANSLATE_HIS_MEDICINEINFO);
                List<Translate> translates = iTranslateService.queryTranslate(translate);
                if (!EmptyUtil.Companion.isNullOrEmpty(translates)) {
                    for (Translate translate1 : translates) {
                        if (StringUtils.equals(h.getName(), translate1.getTranslateChina())) {
                            h.setTranName(translate1.getTranslateKhmer());
                        }
                        if (StringUtils.equals(h.getDrugsSpec(), translate1.getTranslateChina())) {
                            h.setTdrugsSpec(translate1.getTranslateKhmer());
                        }
                    }
                }
            }
            if (h.getType() == 2 || h.getType() == 5) {//项目
                translate.setTranslateId(h.getId().longValue());
                translate.setTranslateType(Constants.TRANSLATE_HIS_PROJECT);
                List<Translate> translates = iTranslateService.queryTranslate(translate);
                if (!EmptyUtil.Companion.isNullOrEmpty(translates)) {
                    translates.stream().filter(e -> StringUtils.equals(h.getName(), e.getTranslateChina())).forEach(t -> h.setTranName(t.getTranslateKhmer()));
                }
            }
            if (h.getType() == 3) {//住院费用
                if (!EmptyUtil.Companion.isNullOrEmpty(h.getName())) {
                    if (h.getName().contains(",")) {
                        String[] split = h.getName().trim().split(",");
                        String wardNumber = split[0];
                        String bedNumber = split[1];
                        h.setTranName("Charge for ward " + wardNumber + " bed " + bedNumber);
                        h.setName("住院" + wardNumber + "号病房" + bedNumber + "号病床费用");
                    } else {
                        h.setTranName("Hospital bed number" + h.getName());
                        h.setName("住院" + h.getName() + "号病床费用");
                    }
                }
            }
        }
        hisTollDetails.get(0).setDrugFee(drugFee);
        return hisTollDetails;
    }

    /**
     * @return void
     * @Description 批量插入
     * @Params [hisTollDetailsList]
     * @Author zhushixiang
     * @Date 2019-09-11
     * @Time 11:28
     **/
    @Override
    @Transactional(readOnly = false)
    public void insertBatch(List<HisTollDetails> hisTollDetailsList) throws Exception {
        if (!EmptyUtil.Companion.isNullOrEmpty(hisTollDetailsList) || hisTollDetailsList.size() != 0) {
            hisTollDetailsMapper.saveForHospi(hisTollDetailsList);
        }
    }

    /**
     * @return com.ahsj.hiscore.entity.HisTollDetails
     * @Description 根据交易流水号查询当次所付住院费用的明细
     * @Params [tollRecordNumber]
     * @Author zhushixiang
     * @Date 2019-09-12
     * @Time 16:58
     **/
    @Override
    @Transactional(readOnly = true)
    public HisTollDetails selectByTollNumberForBedAmount(String tollRecordNumber) throws Exception {
        if (!StringUtils.isEmpty(tollRecordNumber))
            return hisTollDetailsMapper.selectByTollNumberForBedAmount(tollRecordNumber);
        return new HisTollDetails();
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisTollDetails>
     * @Description 根据公共编号 搜索出对应的消费明细
     * @Params [hisTollDetailsPageBean]
     * @Author zhushixiang
     * @Date 2019-09-13
     * @Time 16:34
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisTollDetails> listForcommonSwipeByCommonNumber(PageBean<HisTollDetails> hisTollDetailsPageBean) throws Exception {
        hisTollDetailsPageBean.setData(CodeHelper.getInstance().setCodeValue(hisTollDetailsMapper.listForcommonSwipeByCommonNumber(hisTollDetailsPageBean)));
        return hisTollDetailsPageBean;
    }

    /**
     * @Description 出院打印
     * @Params: [number]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     * @Date 2019/9/26
     * @Time 18:50
     **/
    @Override
    @Transactional(readOnly = true)
    public List<HisTollDetails> listByNumberLeave(String number) throws Exception {
        List<HisTollDetails> hisTollDetails = hisTollDetailsMapper.listByNumberLeave(number);//所有收费明细
        BigDecimal drugFee = new BigDecimal("0");//药品费用
        BigDecimal toll = new BigDecimal("0");//住院总费用
        BigDecimal a = new BigDecimal("0");//退费
        if (!EmptyUtil.Companion.isNullOrEmpty(hisTollDetails)) {
            for (HisTollDetails h : hisTollDetails) {
                toll = toll.add(h.getMoneys());
                Translate translate = new Translate();//翻译
                if (h.getType() == 1 || h.getType() == 4) {//药品
                    drugFee = h.getMoneys().add(drugFee);
                    HisMedicineInfo hisMedicineInfo = hisMedicineInfoService.selectById(h.getId().longValue());
                    h.setDrugsSpec(hisMedicineInfo.getDrugsSpec());
                    translate.setTranslateId(h.getId().longValue());
                    translate.setTranslateType(Constants.TRANSLATE_HIS_MEDICINEINFO);
                    List<Translate> translates = iTranslateService.queryTranslate(translate);
                    if (!EmptyUtil.Companion.isNullOrEmpty(translates)) {
                        for (Translate translate1 : translates) {
                            if (StringUtils.equals(h.getName(), translate1.getTranslateChina())) {
                                h.setTranName(translate1.getTranslateKhmer());
                            }
                            if (StringUtils.equals(h.getDrugsSpec(), translate1.getTranslateChina())) {
                                h.setTdrugsSpec(translate1.getTranslateKhmer());
                            }
                        }
                    }
                }
                if (h.getType() == 2 || h.getType() == 5) {//项目
                    translate.setTranslateId(h.getId().longValue());
                    translate.setTranslateType(Constants.TRANSLATE_HIS_PROJECT);
                    List<Translate> translates = iTranslateService.queryTranslate(translate);
                    if (!EmptyUtil.Companion.isNullOrEmpty(translates)) {
                        translates.stream().filter(e -> StringUtils.equals(h.getName(), e.getTranslateChina())).forEach(t -> h.setTranName(t.getTranslateKhmer()));
                    }
                }
                if (h.getType() == 3) {//住院费用
                    if (!EmptyUtil.Companion.isNullOrEmpty(h.getName())) {
                        if (h.getName().contains(",")) {
                            String[] split = h.getName().trim().split(",");
                            String wardNumber = split[0];
                            String bedNumber = split[1];
                            h.setTranName("Charge for ward " + wardNumber + " bed " + bedNumber);
                            h.setName("住院" + wardNumber + "号病房" + bedNumber + "号病床费用");
                        } else {
                            h.setTranName("Hospital bed number" + h.getName());
                            h.setName("住院" + h.getName() + "号病床费用");
                        }
                    }
                }
                if (h.getType() == 4 || h.getType() == 5) {//退钱
                    a = a.add(h.getMoneys());
                }
            }
        }
        hisTollDetails.get(0).setToll(toll.subtract(a));
        hisTollDetails.get(0).setDrugFee(drugFee);
        return hisTollDetails;
    }

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: java.util.List<com.ahsj.hiscore.entity.HisTollDetails>
     * @Date 2019/9/26
     * @Time 20:24
     **/
    @Override
    @Transactional(readOnly = true)
    public HisTollDetails listByNumberFor(String number) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisTollDetailsMapper.selectNumber(number))) {//未住院
            return CodeHelper.getInstance().setCodeValue(hisTollDetailsMapper.listByNumberFors(number));
        }
        return CodeHelper.getInstance().setCodeValue(hisTollDetailsMapper.listByNumberFor(number)); //住院
    }

    @Override
    @Transactional(readOnly = false)
    public HisTollDetails printShowThere(String number) throws Exception {
        HisTollDetails hs = new HisTollDetails();
        hs.setNursingFee(new BigDecimal(0));
        hs.setExaminationFee(new BigDecimal(0));
        hs.setObserveFee(new BigDecimal(0));
        List<HisTollDetails> hisTollDetails = hisTollDetailsMapper.printShowThere(number);
        if (hisTollDetails.size() == 0) {
            return hs;
        } else {
            for (HisTollDetails hisTollDetail : hisTollDetails) {
                if (hisTollDetail.getType() == 12) {//护理
                    hs.setNursingFee(hs.getNursingFee().add(hisTollDetail.getPrice()));
                }
                if (hisTollDetail.getType() == 13) {//观察
                    hs.setObserveFee(hs.getObserveFee().add(hisTollDetail.getPrice()));
                }
                if (hisTollDetail.getType() == 14) {//检查
                }
            }
        }
        return hs;
    }
}
