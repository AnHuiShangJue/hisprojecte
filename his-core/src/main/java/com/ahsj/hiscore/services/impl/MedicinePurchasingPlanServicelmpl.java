package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisMedicineInfoMapper;
import com.ahsj.hiscore.dao.HisMedicinePurchasingPlanRecordMapper;
import com.ahsj.hiscore.dao.MedicinePurchasingPlanMapper;
import com.ahsj.hiscore.entity.HisMedicineInfo;
import com.ahsj.hiscore.entity.HisMedicinePurchasingPlanRecord;
import com.ahsj.hiscore.entity.MedicinePurchasingPlan;
import com.ahsj.hiscore.services.HisMedicinePurchasingPlanRecordService;
import com.ahsj.hiscore.services.MedicinePurchasingPlanService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MedicinePurchasingPlanServicelmpl implements MedicinePurchasingPlanService {
    @Autowired
    MedicinePurchasingPlanMapper medicinePurchasingPlanMapper;

    @Autowired
    HisMedicineInfoMapper hisMedicineInfoMapper;

    @Autowired
    HisMedicinePurchasingPlanRecordMapper hisMedicinePurchasingPlanRecordMapper;

    @Autowired
    HisMedicinePurchasingPlanRecordService hisMedicinePurchasingPlanRecordService;
    /**
     *@Description 药品采购计划增加详细录入
     *@Params [medicinePurchasingPlan]
     *@return sun.plugin2.message.Message
     *@Author zhushixiang
     *@Date 2019-07-02
     *@Time 16:23
    **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(Long[] ids , Long[] numbers, String personInCharge, String expectedTime,Double[] prices) throws Exception {
        HisMedicinePurchasingPlanRecord hisMedicinePurchasingPlanRecord=new HisMedicinePurchasingPlanRecord();
        //采购计划编号生成 按照当前时间生成，保证独一无二
        //AR对应application return drug申请退药//MPP：medicine purchase plan药品采购计划
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String createdate = sdf.format(date);
        int count = medicinePurchasingPlanMapper.selectNumbCount(createdate) + 1;
        boolean flag = false;
        String planNumber = "";
        while(true){
            planNumber = "MPP"+createdate + String.format("%05d", count);
            HisMedicinePurchasingPlanRecord checkPlanNumber = hisMedicinePurchasingPlanRecordMapper.selectByPlanNumber(planNumber);
            if(EmptyUtil.Companion.isNullOrEmpty(checkPlanNumber))
                break;
            else
                count++;
        }
        //allBudget：预算
        double allBudget=0;
        for (int i = 0; i < ids.length; i++) {
            HisMedicineInfo hisMedicineInfo = hisMedicineInfoMapper.selectByPrimaryKey(ids[i]);
            MedicinePurchasingPlan medicinePurchasingPlan = new MedicinePurchasingPlan();
            medicinePurchasingPlan.setPlanNumber(planNumber);
            medicinePurchasingPlan.setPharmacyId(hisMedicineInfo.getId());
            medicinePurchasingPlan.setEnterCountPlan(numbers[i]);
            medicinePurchasingPlan.setPrice(BigDecimal.valueOf(prices[i]));
            medicinePurchasingPlan.setDrugsNumb(hisMedicineInfo.getDrugsNumb());
            medicinePurchasingPlan.setDrugsName(hisMedicineInfo.getDrugsName());
            allBudget += prices[i] * numbers[i];
            medicinePurchasingPlan.setDrugsSpec(hisMedicineInfo.getDrugsSpec());
            medicinePurchasingPlanMapper.insert(medicinePurchasingPlan);
        }
        hisMedicinePurchasingPlanRecord.setPlanNumber(planNumber);
        hisMedicinePurchasingPlanRecord.setPersonInCharge(personInCharge);
        //将expectedTime格式化yyyy/MM/dd日期格式
        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
        Date expectedDate=null;
        expectedDate=format.parse(expectedTime);
        hisMedicinePurchasingPlanRecord.setExpectedTime(expectedDate);
        hisMedicinePurchasingPlanRecord.setBudget(BigDecimal.valueOf(allBudget));
        if(hisMedicinePurchasingPlanRecord.getCompletion()==null)
            hisMedicinePurchasingPlanRecord.setCompletion(2);
        hisMedicinePurchasingPlanRecordService.saveOrUpdate(hisMedicinePurchasingPlanRecord);
        return MessageUtil.createMessage(true,"药品采购计划生成成功");
    }

    /**
     *@Description 根据采购计划编号显示此次采购的详细信息
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.MedicinePurchasingPlan>
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 13:09
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<MedicinePurchasingPlan> details(PageBean<MedicinePurchasingPlan> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(medicinePurchasingPlanMapper.details(pageBean)));
        return pageBean;
    }

    /**
     *@Description 删除
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 15:46
    **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id:ids) {
            MedicinePurchasingPlan medicinePurchasingPlan=medicinePurchasingPlanMapper.selectByPrimaryKey(id);
            HisMedicinePurchasingPlanRecord hisMedicinePurchasingPlanRecord=hisMedicinePurchasingPlanRecordMapper.selectByPlanNumber(medicinePurchasingPlan.getPlanNumber());
            //计算删除之后的预算
            double num=medicinePurchasingPlan.getEnterCountPlan()*medicinePurchasingPlan.getPrice().doubleValue();
            hisMedicinePurchasingPlanRecord.setBudget(hisMedicinePurchasingPlanRecord.getBudget().subtract(BigDecimal.valueOf(num)));
            hisMedicinePurchasingPlanRecordService.saveOrUpdate(hisMedicinePurchasingPlanRecord);
            medicinePurchasingPlanMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true,"删除成功!");
    }

    /**
     *@Description 根据计划编号查找(pharmacyId as id  为适应动态表格)
     *@Params [planNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.MedicinePurchasingPlan>
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 20:19
    **/
    @Override
    @Transactional(readOnly = true)
    public List<MedicinePurchasingPlan> selectByPlanNumber(String planNumber) {
        return CodeHelper.getInstance().setCodeValue(medicinePurchasingPlanMapper.selectByPlanNumber(planNumber));
    }

    /**
     *@Description 药品采购计划明细编辑后提交
     *@Params [model, request, ids, numbers, personInCharge, expectedTime, prices]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-05
     *@Time 9:22
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveDetails(Long[] ids, Long[] numbers, Double[] prices,String planNumber) throws Exception {
       List<MedicinePurchasingPlan> medicinePurchasingPlanList=medicinePurchasingPlanMapper.selectByPlanNumberforList(planNumber);
       medicinePurchasingPlanMapper.deleteByPlanNumber(planNumber);
        HisMedicinePurchasingPlanRecord hisMedicinePurchasingPlanRecord=hisMedicinePurchasingPlanRecordMapper.selectByPlanNumber(planNumber);
        //allBudget：预算
        double allBudget=0;
        for (int i = 0; i < ids.length; i++) {
            HisMedicineInfo hisMedicineInfo = hisMedicineInfoMapper.selectByPrimaryKey(ids[i]);
            MedicinePurchasingPlan medicinePurchasingPlan = new MedicinePurchasingPlan();
            medicinePurchasingPlan.setPlanNumber(planNumber);
            medicinePurchasingPlan.setPharmacyId(hisMedicineInfo.getId());
            medicinePurchasingPlan.setEnterCountPlan(numbers[i]);
            medicinePurchasingPlan.setPrice(BigDecimal.valueOf(prices[i]));
            medicinePurchasingPlan.setDrugsNumb(hisMedicineInfo.getDrugsNumb());
            medicinePurchasingPlan.setDrugsName(hisMedicineInfo.getDrugsName());
            allBudget += prices[i] * numbers[i];
            medicinePurchasingPlan.setDrugsSpec(hisMedicineInfo.getDrugsSpec());
            medicinePurchasingPlanMapper.insert(medicinePurchasingPlan);
        }
        hisMedicinePurchasingPlanRecord.setBudget(BigDecimal.valueOf(allBudget));
        hisMedicinePurchasingPlanRecordService.saveOrUpdate(hisMedicinePurchasingPlanRecord);
        return MessageUtil.createMessage(true,"药品采购计划更新成功");
    }


    /**
     *@Description 根据计划编号查找
     *@Params [planNumber]
     *@return java.util.List<com.ahsj.hiscore.entity.MedicinePurchasingPlan>
     *@Author zhushixiang
     *@Date 2019-07-05
     *@Time 13:45
     **/
    @Override
    @Transactional(readOnly = true)
    public List<MedicinePurchasingPlan> selectByPlanNumberforList(String planNumber) {
        return CodeHelper.getInstance().setCodeValue(medicinePurchasingPlanMapper.selectByPlanNumberforList(planNumber));
    }

    /**
     *@Description 根据planNumber删除所有其下采购计划明细
     *@Params [planNumber]
     *@return void
     *@Author zhushixiang
     *@Date 2019-07-12
     *@Time 10:48
    **/
    @Override
    @Transactional(readOnly = false)
    public void deleteByPlanNumber(String planNumber) throws Exception {
        medicinePurchasingPlanMapper.deleteByPlanNumber(planNumber);
    }
}
