package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisDrugLossReportingMapper;
import com.ahsj.hiscore.dao.HisMediEnterDetailsMapper;
import com.ahsj.hiscore.dao.HisPharmacyDetailMapper;
import com.ahsj.hiscore.entity.HisDrugLossReporting;
import com.ahsj.hiscore.entity.HisMediEnterDetails;
import com.ahsj.hiscore.entity.HisPharmacyDetail;
import com.ahsj.hiscore.services.HisDrugLossReportingService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HisDrugLossReportingServicelmpl implements HisDrugLossReportingService {
    Logger logger = LoggerFactory.getLogger(HisDailyRecordServiceImpl.class.getSimpleName());

    @Autowired
    HisDrugLossReportingMapper hisDrugLossReportingMapper;

    @Autowired
    HisMediEnterDetailsMapper hisMediEnterDetailsMapper;

    @Autowired
    HisPharmacyDetailMapper hisPharmacyDetailMapper;
    /**
     *@Description 药品报损
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 11:21
     **/
    @Override
    @Transactional(readOnly = false)
    public Message lossDrug(HisDrugLossReporting hisDrugLossReporting) throws Exception {
        //根据入库表ID找到对应的入库记录
        HisMediEnterDetails hisMediEnterDetails=hisMediEnterDetailsMapper.selectByPrimaryKey(hisDrugLossReporting.getMediEnterId());
        //根据药品ID，找到药库中对应药品的信息
        HisPharmacyDetail hisPharmacyDetail=hisPharmacyDetailMapper.selectByPharmacyId(hisDrugLossReporting.getPharmacyId());
        hisDrugLossReporting.setId(null);
        if(EmptyUtil.Companion.isNullOrEmpty(hisDrugLossReporting.getType())||EmptyUtil.Companion.isNullOrEmpty(hisDrugLossReporting.getNumber()))
            return MessageUtil.createMessage(false,"请将信息输入完整");
        if(hisDrugLossReporting.getNumber()>hisMediEnterDetails.getSurplus() || hisDrugLossReporting.getNumber()>hisPharmacyDetail.getStock())
            return MessageUtil.createMessage(false,"药品不足，无法报损！");
        hisDrugLossReporting.setIsReview(3);
        hisDrugLossReportingMapper.insert(hisDrugLossReporting);
        return MessageUtil.createMessage(true,"药品报损成功!");
    }

    /**
     *@Description 分页查询
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisDrugLossReporting>
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 14:54
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisDrugLossReporting> list(PageBean<HisDrugLossReporting> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisDrugLossReportingMapper.list(pageBean)));
        return pageBean;
    }

    /**
     *@Description
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisDrugLossReporting
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 17:36
    **/
    @Override
    @Transactional(readOnly = true)
    public HisDrugLossReporting selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisDrugLossReportingMapper.selectByPrimaryKey(id));
    }

    /**
     *@Description 药品报损审核
     *@Params [hisDrugLossReporting]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-09
     *@Time 21:12
    **/
    @Override
    @Transactional(readOnly = false)
    public Message reviewSuccess(HisDrugLossReporting hisDrugLossReporting) throws Exception {
        if(EmptyUtil.Companion.isNullOrEmpty(hisDrugLossReporting.getId()))
            return MessageUtil.createMessage(false,"无此报损记录");
        HisDrugLossReporting hisDrugLossReportingUpdate=hisDrugLossReportingMapper.selectByPrimaryKey(hisDrugLossReporting.getId());
        //此处代码需要的是入库表的ID已供去扣除入库表中药品的数目
        HisMediEnterDetails hisMediEnterDetails=hisMediEnterDetailsMapper.selectByPrimaryKey(hisDrugLossReportingUpdate.getMediEnterId());
        if(EmptyUtil.Companion.isNullOrEmpty(hisMediEnterDetails))
            return MessageUtil.createMessage(false,"无此进货记录");
        HisPharmacyDetail hisPharmacyDetail=hisPharmacyDetailMapper.selectByPharmacyId(hisDrugLossReportingUpdate.getPharmacyId());
        if(EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail))
            return MessageUtil.createMessage(false,"药库无此药品");
        if(hisDrugLossReportingUpdate.getNumber()>hisMediEnterDetails.getSurplus() || hisDrugLossReportingUpdate.getNumber()>hisPharmacyDetail.getStock())
            return MessageUtil.createMessage(false,"药品不足，无法报损！");
        hisMediEnterDetails.setSurplus(hisMediEnterDetails.getSurplus()-hisDrugLossReportingUpdate.getNumber());
        hisMediEnterDetailsMapper.updateByPrimaryKeySelective(hisMediEnterDetails);
        hisPharmacyDetail.setStock(hisPharmacyDetail.getStock()-hisDrugLossReportingUpdate.getNumber());
        hisPharmacyDetailMapper.updateByPrimaryKeySelective(hisPharmacyDetail);
        hisDrugLossReportingUpdate.setIsReview(1);
        hisDrugLossReportingMapper.updateByPrimaryKeySelective(hisDrugLossReportingUpdate);
        return MessageUtil.createMessage(true,"药品报损审核成功");
    }

    /**
     *@Description 药品报损审核未通过
     *@Params [hisDrugLossReporting]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-11
     *@Time 20:33
    **/
    @Override
    @Transactional(readOnly = false)
    public Message reviewFail(HisDrugLossReporting hisDrugLossReporting) throws Exception {
        HisDrugLossReporting hisDrugLossReportingUpdate=hisDrugLossReportingMapper.selectByPrimaryKey(hisDrugLossReporting.getId());
        hisDrugLossReportingUpdate.setIsReview(2);
        hisDrugLossReportingMapper.updateByPrimaryKeySelective(hisDrugLossReportingUpdate);
        return MessageUtil.createMessage(true,"药品报损审核未通过");
    }

    /**
     *@Description 药品报损定时任务
     *@Params []
     *@return void
     *@Author zhushixiang
     *@Date 2019-08-02
     *@Time 10:49
    **/
    @Override
    @Transactional(readOnly = false)
    public void startScanDrug() throws Exception{
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createdate = sdf.format(date);
        logger.info(createdate);
        //搜索出入库表中已过期的药品
        List<HisMediEnterDetails> hisMediEnterDetailsList = hisMediEnterDetailsMapper.selectByDate();
        if(hisMediEnterDetailsList.size() == 0)
            logger.info("-------------------无可报损药品-----------------------");
        else {
            logger.info("-------------------扫描到" + hisMediEnterDetailsList.size() + "种药品需要报损-----------------------");
            List<HisDrugLossReporting> hisDrugLossReportingList = new ArrayList<>();
            List<HisPharmacyDetail> hisPharmacyDetailList = new ArrayList<>();
            for (int i = 0; i < hisMediEnterDetailsList.size(); i++) {
                HisPharmacyDetail hisPharmacyDetail = hisPharmacyDetailMapper.selectByDrugsNumb(hisMediEnterDetailsList.get(i).getDrugsNumb());
                HisDrugLossReporting hisDrugLossReporting = new HisDrugLossReporting();
                hisDrugLossReporting.setType(2);
                hisDrugLossReporting.setReason(createdate+"过期，系统自动报损");
                hisDrugLossReporting.setPharmacyId(hisMediEnterDetailsList.get(i).getPharmacyId());
                hisDrugLossReporting.setDrugsNumb(hisMediEnterDetailsList.get(i).getDrugsNumb());
                hisDrugLossReporting.setDrugsName(hisMediEnterDetailsList.get(i).getDrugsName());
                hisDrugLossReporting.setDrugsSpec(hisMediEnterDetailsList.get(i).getDrugsSpec() );
                if(!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getSalePrice()))
                    hisDrugLossReporting.setSalePrice(hisPharmacyDetail.getSalePrice().doubleValue());
                hisDrugLossReporting.setValidityPeriod(hisMediEnterDetailsList.get(i).getValidityPeriod());
                if(!EmptyUtil.Companion.isNullOrEmpty(hisMediEnterDetailsList.get(i).getPrice()))
                    hisDrugLossReporting.setEnterPrice(hisMediEnterDetailsList.get(i).getPrice().doubleValue());
                if(!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getPlaceorigin()))
                    hisDrugLossReporting.setManufacturers(hisPharmacyDetail.getPlaceorigin());
                hisDrugLossReporting.setNumber(hisMediEnterDetailsList.get(i).getSurplus().intValue());
                if(!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getLargeUnit()))
                    hisDrugLossReporting.setLargeUnit(hisPharmacyDetail.getLargeUnit());
                if(!EmptyUtil.Companion.isNullOrEmpty(hisPharmacyDetail.getSmallUnit()))
                    hisDrugLossReporting.setSmallUnit(hisPharmacyDetail.getSmallUnit());
                hisDrugLossReporting.setTreatmentMeasures("过期弃用");
                hisDrugLossReporting.setIsReview(1);//默认审核成功
                hisDrugLossReporting.setMediEnterId(hisMediEnterDetailsList.get(i).getId());
                hisDrugLossReportingList.add(hisDrugLossReporting);
                if(hisPharmacyDetail.getStock() <= hisMediEnterDetailsList.get(i).getSurplus())
                    hisPharmacyDetail.setStock(0);
                else
                    hisPharmacyDetail.setStock(hisPharmacyDetail.getStock()- hisMediEnterDetailsList.get(i).getSurplus().intValue());
                hisPharmacyDetailList.add(hisPharmacyDetail);
                hisMediEnterDetailsList.get(i).setSurplus(0L);
            }
            hisDrugLossReportingMapper.insertBatch(hisDrugLossReportingList);
            hisMediEnterDetailsMapper.updateBatch(hisMediEnterDetailsList);
            hisPharmacyDetailMapper.updateBatchForStock(hisPharmacyDetailList);
            logger.info("-------------------报损成功，药品数量已更新-----------------------");
        }
    }
}
