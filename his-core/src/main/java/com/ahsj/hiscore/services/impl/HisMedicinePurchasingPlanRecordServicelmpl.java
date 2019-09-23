package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisMedicinePurchasingPlanRecordMapper;
import com.ahsj.hiscore.dao.MedicinePurchasingPlanMapper;
import com.ahsj.hiscore.entity.HisMedicinePurchasingPlanRecord;
import com.ahsj.hiscore.services.HisMedicinePurchasingPlanRecordService;
import com.ahsj.hiscore.services.MedicinePurchasingPlanService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

@Service
public class HisMedicinePurchasingPlanRecordServicelmpl implements HisMedicinePurchasingPlanRecordService {

    @Autowired
    HisMedicinePurchasingPlanRecordMapper hisMedicinePurchasingPlanRecordMapper;

    @Autowired
    MedicinePurchasingPlanMapper medicinePurchasingPlanMapper;

    @Autowired
    MedicinePurchasingPlanService medicinePurchasingPlanService;
    /**
     *@Description list
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicinePurchasingPlanRecord>
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 15:24
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMedicinePurchasingPlanRecord> list(PageBean<HisMedicinePurchasingPlanRecord> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisMedicinePurchasingPlanRecordMapper.list(pageBean)));
        return pageBean;
    }

    /**
     *@Description 新增或更新
     *@Params [hisMedicinePurchasingPlanRecord]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 15:25
    **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisMedicinePurchasingPlanRecord hisMedicinePurchasingPlanRecord) throws Exception {
        HisMedicinePurchasingPlanRecord checkId=hisMedicinePurchasingPlanRecordMapper.selectByPrimaryKey(hisMedicinePurchasingPlanRecord.getId());
        if(EmptyUtil.Companion.isNullOrEmpty(checkId)){
            hisMedicinePurchasingPlanRecordMapper.insert(hisMedicinePurchasingPlanRecord);
            return MessageUtil.createMessage(true,"新增成功");
        }else {
            hisMedicinePurchasingPlanRecordMapper.updateByPrimaryKey(hisMedicinePurchasingPlanRecord);
            return  MessageUtil.createMessage(true,"更新成功");
        }
    }

    /**
     *@Description delete
     *@Params [ids]
     *@return core.message.Message
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 15:25
    **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id:ids) {
            medicinePurchasingPlanService.deleteByPlanNumber(hisMedicinePurchasingPlanRecordMapper.selectByPrimaryKey(id).getPlanNumber());
            hisMedicinePurchasingPlanRecordMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true,"删除成功!");
    }

    /**
     *@Description 根据ID查找
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisMedicinePurchasingPlanRecord
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 15:25
    **/
    @Override
    @Transactional(readOnly = true)
    public HisMedicinePurchasingPlanRecord selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisMedicinePurchasingPlanRecordMapper.selectByPrimaryKey(id));
    }

    /**
     *@Description 根据计划编号查找
     *@Params [planNumber]
     *@return com.ahsj.hiscore.entity.HisMedicinePurchasingPlanRecord
     *@Author zhushixiang
     *@Date 2019-07-04
     *@Time 15:25
    **/
    @Override
    @Transactional(readOnly = true)
    public HisMedicinePurchasingPlanRecord selectByPlanNumber(String planNumber) {
        return CodeHelper.getInstance().setCodeValue(hisMedicinePurchasingPlanRecordMapper.selectByPlanNumber(planNumber));
    }

}
