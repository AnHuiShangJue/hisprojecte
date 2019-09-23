package com.ahsj.baseuserinfo.services.impl;

import com.ahsj.baseuserinfo.dao.OperateMapper;
import com.ahsj.baseuserinfo.entity.Operate;
import com.ahsj.baseuserinfo.services.OperateService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

@Service
@Slf4j
public class OperateServiceImpl implements OperateService {

    @Autowired
    private com.ahsj.baseuserinfo.dao.OperateMapper OperateMapper;


    @Transactional(readOnly = true)
    public PageBean<Operate> list(PageBean<Operate> pageEntity) throws Exception {
        pageEntity.setData(OperateMapper.list(pageEntity));
        return pageEntity;
    }

    @Transactional(readOnly = false)
    @Override
    public Operate updateInit(Long id) throws Exception {
        return  OperateMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = false)
    @Override
    public Message saveOrUpdate(Operate sysOperate) throws Exception {
        // 如果主健为空 则为新增
        if (EmptyUtil.Companion.isNullOrEmpty(sysOperate.getId())) {
            Operate check = OperateMapper.selectByCode(sysOperate.getCode());
            if (!EmptyUtil.Companion.isNullOrEmpty(check) && !EmptyUtil.Companion.isNullOrEmpty(check.getId())) {
                return MessageUtil.createMessage(false, "此操作ID已存在！");
            }
            check = OperateMapper.selectByName(sysOperate.getName());
            if (!EmptyUtil.Companion.isNullOrEmpty(check) && !EmptyUtil.Companion.isNullOrEmpty(check.getId())) {
                return MessageUtil.createMessage(false, "此操作名称已存在！");
            }
            OperateMapper.insert(sysOperate);
            return MessageUtil.createMessage(true, "保存成功。");
        } else {
            // 如果主健不为空 则为更新
            OperateMapper.update(sysOperate);
            return MessageUtil.createMessage(true, "更新成功。");
        }
    }

    @Transactional(readOnly = false)
    @Override
    public Message delete(Long[] id) throws Exception {
        for (int i = 0; i < id.length; i++) {
            OperateMapper.deleteByPrimaryKey(id[i]);
        }
        return MessageUtil.createMessage(true, "删除成功。");
    }
}
