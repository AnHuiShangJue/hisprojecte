package com.ahsj.wis.service.impl;

import com.ahsj.wis.dao.SysMapper;
import com.ahsj.wis.entity.Sys;
import com.ahsj.wis.service.SysService;
import core.message.Message;
import core.message.MessageUtil;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SysServiceImpl
 * <p>
 * Date:     2019/11/15 17:32
 *
 * @author XJP
 * @create 2019/11/15
 * @since 1.0.0
 */

@Service
public class SysServiceImpl implements SysService {

    @Autowired
    SysMapper sysMapper;

    /**
     * @return java.util.List<com.ahsj.wis.entity.Sys>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/11/15
     * @Time 18:12
     **/
    @Override
    @Transactional(readOnly = true)
    public List<Sys> getSysAll() throws Exception {
        List<Sys> sysAll = sysMapper.getSysAll();
        if (EmptyUtil.Companion.isNullOrEmpty(sysAll)) {
            return new ArrayList<>();
        } else {
            return sysAll;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Message addSys(Sys sys) throws Exception {
        sys.setCreateDate(new Date());
        if (EmptyUtil.Companion.isNullOrEmpty(sys.getName()) || EmptyUtil.Companion.isNullOrEmpty(sys.getUrl())){
            return MessageUtil.createMessage(false, "添加失败！！");
        }
        sysMapper.insert(sys);
        return MessageUtil.createMessage(true, "添加成功！！");
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [sys]
     * @Author XJP
     * @Date 2019/11/16
     * @Time 15:23
     **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Sys sys) throws Exception {
        sysMapper.deleteByPrimaryKey(sys.getId());
        return MessageUtil.createMessage(true, "删除成功！！");
    }
}
