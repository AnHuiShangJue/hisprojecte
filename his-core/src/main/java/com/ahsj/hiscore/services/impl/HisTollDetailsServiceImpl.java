package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisTollDetailsMapper;
import com.ahsj.hiscore.entity.HisTollDetails;
import com.ahsj.hiscore.entity.Translate;
import com.ahsj.hiscore.feign.ITranslateService;
import com.ahsj.hiscore.services.HisTollDetailsService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

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
        if(EmptyUtil.Companion.isNullOrEmpty(hisTollDetails) || hisTollDetails.size() ==0)
            return MessageUtil.createMessage(false,"无可付费信息");
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
        for (HisTollDetails h : hisTollDetails) {
            Translate translate = new Translate();//翻译
            if (h.getType() == 1 || h.getType() == 4) {//药品
                translate.setTranslateId(h.getId().longValue());
                translate.setTranslateType(Constants.TRANSLATE_HIS_MEDICINEINFO);
                translate.setTranslateChina(h.getName());
                List<Translate> translates = iTranslateService.queryTranslate(translate);
                if (!EmptyUtil.Companion.isNullOrEmpty(translates)) {
                    translates.stream().forEach(t -> h.setTranName(t.getTranslateKhmer()));
                }
            }
            if (h.getType() == 2) {//项目
                translate.setTranslateId(h.getId().longValue());
                translate.setTranslateType(Constants.TRANSLATE_HIS_PROJECT);
                translate.setTranslateChina(h.getName());
                List<Translate> translates = iTranslateService.queryTranslate(translate);
                if (!EmptyUtil.Companion.isNullOrEmpty(translates)) {
                    translates.stream().forEach(t -> h.setTranName(t.getTranslateKhmer()));
                }
            }
            if (h.getType() == 3) {//住院费用
                if (!EmptyUtil.Companion.isNullOrEmpty(h.getName())) {
                    h.setTranName("មន្ទីរពេទ្យ។" + h.getName() + "ថ្លៃគ្រែ។");
                    h.setName("住院" + h.getName() + "号病床费用");
                }
            }
        }
        return hisTollDetails;
    }

    /**
     *@Description 批量插入
     *@Params [hisTollDetailsList]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-11
     *@Time 11:28
    **/
    @Override
    @Transactional(readOnly = false)
    public void insertBatch(List<HisTollDetails> hisTollDetailsList) throws Exception {
        if(!EmptyUtil.Companion.isNullOrEmpty(hisTollDetailsList) || hisTollDetailsList.size() != 0){
            hisTollDetailsMapper.saveForHospi(hisTollDetailsList);
        }
    }

    /**
     *@Description 根据交易流水号查询当次所付住院费用的明细
     *@Params [tollRecordNumber]
     *@return com.ahsj.hiscore.entity.HisTollDetails
     *@Author zhushixiang
     *@Date 2019-09-12
     *@Time 16:58
    **/
    @Override
    @Transactional(readOnly = true)
    public HisTollDetails selectByTollNumberForBedAmount(String tollRecordNumber) throws Exception {
        if(!StringUtils.isEmpty(tollRecordNumber))
            return hisTollDetailsMapper.selectByTollNumberForBedAmount(tollRecordNumber);
        return new HisTollDetails();
    }

    /**
     *@Description 根据公共编号 搜索出对应的消费明细
     *@Params [hisTollDetailsPageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisTollDetails>
     *@Author zhushixiang
     *@Date 2019-09-13
     *@Time 16:34
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisTollDetails> listForcommonSwipeByCommonNumber(PageBean<HisTollDetails> hisTollDetailsPageBean) throws Exception {
        hisTollDetailsPageBean.setData(CodeHelper.getInstance().setCodeValue(hisTollDetailsMapper.listForcommonSwipeByCommonNumber(hisTollDetailsPageBean)));
        return hisTollDetailsPageBean;
    }
}
