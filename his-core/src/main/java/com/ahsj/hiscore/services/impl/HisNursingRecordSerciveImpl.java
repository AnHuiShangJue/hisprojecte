package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisNursingRecordMapper;
import com.ahsj.hiscore.entity.HisNursingRecord;
import com.ahsj.hiscore.services.HisNursingRecordService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.List;

@Service
public class HisNursingRecordSerciveImpl implements HisNursingRecordService {
    @Autowired
    HisNursingRecordMapper hisNursingRecordMapper;


    @Override
    @Transactional(readOnly = true)
    public PageBean<HisNursingRecord> listByManageNumber(PageBean<HisNursingRecord> pageBean, String manageNumber) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisNursingRecordMapper.listByManageNumber(manageNumber)));
        return pageBean;
    }

    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisNursingRecord hisNursingRecord) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisNursingRecord.getId())) {
            //id为空，新增
            hisNursingRecordMapper.insert(hisNursingRecord);
            return MessageUtil.createMessage(true, "添加记录成功!");
        } else {
            //id不为空，更新
            hisNursingRecordMapper.updateByPrimaryKey(hisNursingRecord);
            return MessageUtil.createMessage(true, "更新记录成功!");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public HisNursingRecord selectByPrimaryId(Long id) {
        return hisNursingRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id : ids) {
            hisNursingRecordMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true, "删除成功!");
    }

    /**
     * @Description 根据条件做温度折线图
     * @Param hisNursingRecord:
     * @Author: dingli
     * @return: java.util.List<com.ahsj.hiscore.entity.HisNursingRecord>
     * @Date 2019/8/7
     * @Time 10:12
     **/
    @Transactional(readOnly = true)
    @Override
    public List<HisNursingRecord> list(HisNursingRecord hisNursingRecord) throws Exception {
        List<HisNursingRecord> list = hisNursingRecordMapper.list(hisNursingRecord);
        list.stream().forEach(e->e.setDateOther(e.getDates()));
        return list;
    }
}
