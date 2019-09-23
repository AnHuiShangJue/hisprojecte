package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisRegisteredTypeMapper;
import com.ahsj.hiscore.entity.HisRegisteredType;
import com.ahsj.hiscore.services.HisRegisteredTypeService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.List;


/**
 * @program: his
 * @description:HisRegisteredType
 * @author: chenzhicai
 * @create: 2019-07-01-17-34
 **/
@Service
public class HisRegisteredTypeServiceImpl implements HisRegisteredTypeService {

    @Autowired
    HisRegisteredTypeMapper hisRegisteredTypeMapper;


    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisRegisteredType hisRegisteredType) throws Exception {
        System.out.println(hisRegisteredType.toString());
        //id是null就是新增，否则是更新
        if (EmptyUtil.Companion.isNullOrEmpty(hisRegisteredType.getId())) {
            HisRegisteredType check = hisRegisteredTypeMapper.selectByRegisterId(hisRegisteredType.getRegisterId());
            //检查名称规格是否相同
            if (EmptyUtil.Companion.isNullOrEmpty(check)) {
                //是null就插入
                hisRegisteredTypeMapper.insert(hisRegisteredType);
                return MessageUtil.createMessage(true, "插入成功!");
            } else {
                return MessageUtil.createMessage(false, "插入失败!,此编号已存在");
            }
        } else {
            HisRegisteredType check = hisRegisteredTypeMapper.selectByRegisterId(hisRegisteredType.getRegisterId());
            //先检测id是否存在
            if (EmptyUtil.Companion.isNullOrEmpty(check)) {
                hisRegisteredTypeMapper.updateByPrimaryKey(hisRegisteredType);
                return MessageUtil.createMessage(true, "更新成功!");

            } else {
                if(check.getId() ==hisRegisteredType.getId()){
                    hisRegisteredTypeMapper.updateByPrimaryKey(hisRegisteredType);
                    return MessageUtil.createMessage(true, "更新成功!");
                }
                else{
                    return MessageUtil.createMessage(false, "编号已有更新失败!");
                }
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id:ids) {
            hisRegisteredTypeMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true,"删除成功!");
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRegisteredType> list(PageBean<HisRegisteredType> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRegisteredTypeMapper.list(pageBean)));
        return pageBean;
    }

    @Override
    public HisRegisteredType updateInit(Long id) throws Exception {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public HisRegisteredType selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisRegisteredTypeMapper.myselectByPrimaryKey(id));
    }

    @Override
    public HisRegisteredType selectByRegisterId(Long id) {
        return null;
    }

    @Override
    public List<HisRegisteredType> listByAll() {
        return CodeHelper.getInstance().setCodeValue(hisRegisteredTypeMapper.listByAll());
    }
}

    